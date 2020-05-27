package workflowsystem.data.wfguide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.data.maincontrol.Phase;
import workflowsystem.data.maincontrol.PhaseDAO;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;

public class WorkflowDAO extends DataAccessObject {
	/**
	 * Reads resulting Workflow entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return workflow	 	Workflow data
	 * @throws SQLException SQL Error
	 */
	private Workflow read(ResultSet rs) throws SQLException {
		Long workflowId = new Long(rs.getLong("WORKFLOWID"));
		String projectName = rs.getString("PROJECTNAME");
		String workflowName = rs.getString("WORKFLOWNAME");
		Long workflowSequence = new Long(rs.getLong("WORKFLOWSEQUENCE"));
		String phaseCode = rs.getString("PHASECODE");
		Long projectId = new Long(rs.getLong("PROJECTID"));

		/* Initialize new Workflow object */
		Workflow workflow = new Workflow();

		/* Set values to Workflow Object */
		workflow.setWorkflowId(workflowId);					// Workflow ID
		workflow.setProjectName(projectName);				// Project Name
		workflow.setWorkflowName(workflowName);				// Workflow Name
		workflow.setWorkflowSequence(workflowSequence);		// Workflow Sequence
		workflow.setPhaseCode(phaseCode);					// Phase Code
		workflow.setProjectId(projectId);					// Project ID

		return workflow;
	}

	/**
	 * Finds all Workflow entries from database by Project ID
	 * 
	 * @return workflows 	List of saved Workflow
	 */
	public List<Workflow> findAll(Long id) {
		LinkedList<Workflow> workflows = new LinkedList<Workflow>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_WORKFLOW_001);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	// Project ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Workflow workflow = read(rs);
				workflows.add(workflow);
			}
			return workflows;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all Workflow entries by Workflow ID from database
	 * 
	 * @param  id				Workflow ID
	 * @return taskDocument 	List of saved Tasks Document
	 */
	public Workflow find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_WORKFLOW_002);

			/* Set Search Parameter */
			statement.setLong(1, id.longValue());	// Workflow ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (!rs.next()) {

				return null;

			}
			return read(rs);
	
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Updates specified Workflow entry in database
	 * 
	 * @param workflow	 	Workflow object to update
	 */
	public void update(Workflow workflow) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_WORKFLOW_001);

			/* Set Update Parameters */
			statement.setString(1, workflow.getWorkflowName());						// Workflow Name
			statement.setLong(2, workflow.getWorkflowSequence().longValue());		// Workflow Sequence

			Phase phase = new PhaseDAO().findByPhaseCode(workflow.getPhaseCode());
			statement.setLong(3, phase.getPhaseId().longValue());					// Phase ID
			statement.setLong(4, workflow.getWorkflowId().longValue());				// Workflow ID

			/* Execute SQL Statement */
			statement.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Creates a new Workflow entry in database
	 * 
	 * @param workflow 		Workflow object to create
	 */
	public void create(Workflow workflow) {
		Long id = getUniqueId();
		workflow.setWorkflowId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_WORKFLOW_001);

			/* Set Insert Parameters */
			Project project = new ProjectDAO().findByProjectName(workflow.getProjectName());
			Phase phase = new PhaseDAO().findByPhaseCode(workflow.getPhaseCode());
			
			statement.setLong(1, id.longValue());								// Workflow ID
			statement.setString(2, workflow.getWorkflowName());					// Workflow Name
			statement.setLong(3, project.getProjectId().longValue());			// Project ID
			statement.setLong(4, workflow.getWorkflowSequence().longValue());	// Workflow Sequence
			statement.setLong(5, phase.getPhaseId().longValue());				// Phase ID

			/* Execute SQL Statement */
			statement.executeUpdate();

			/* Create relation for Workflow and Phase */
			WfUtilCommon.createRelation(id, 									// Workflow ID
										WfConstCommon.WFG_WORKFLOW,				// Workflow Table Name
										phase.getPhaseId().longValue(), 		// Phase ID
										WfConstCommon.MST_PHASE);				// Phase Table Name
			
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Deletes a Workflow entry in database
	 * 
	 * @param workflow	 	Task Document object to delete
	 */
	public void delete(Workflow workflow) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Connection */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_WORKFLOW_001);

			/* Set Delete Parameters */
			Long id = workflow.getWorkflowId();
			statement.setLong(1, id.longValue());	// Workflow ID

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}
}
