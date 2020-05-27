package workflowsystem.data.wfguide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.data.maincontrol.Company;
import workflowsystem.data.maincontrol.CompanyDAO;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;

public class ProjectDAO extends DataAccessObject {
	/**
	 * Reads resulting Project entries from database
	 * 
	 * @param rs ResultSet
	 * @return project Project data
	 * @throws SQLException SQL Error
	 */
	private Project read(ResultSet rs) throws SQLException {
		Long projectId = new Long(rs.getLong("PROJECTID"));
		String projectName = rs.getString("PROJECTNAME");
		String projectDescription = rs.getString("PROJECTDESCRIPTION");
		String companyCode = rs.getString("COMPANYCODE");
		String companyName = rs.getString("COMPANYNAME");
		String projectStatus = rs.getString("PROJECTSTATUS");
		String projectStartDate = rs.getString("PROJECTSTARTDATE");

		/* Initialize Project Object */
		Project project = new Project();

		/* Set values to Project Object */
		project.setProjectId(projectId); // Project ID
		project.setProjectName(projectName); // Project Name
		project.setProjectDescription(projectDescription); // Project
															// Description
		project.setCompanyCode(companyCode); // Company Code
		project.setCompanyName(companyName); // Company Name
		project.setProjectStatus(getProjectStatusValue(projectStatus)); // Project
																		// Status
		project.setProjectStartDate(projectStartDate);								// projectStart Date
		
		return project;
	}

	/**
	 * Obtain project status value
	 * 
	 * @return projectStatusValue Project Status Value
	 */
	public String getProjectStatusValue(String projectStatus) {
		String projectStatusValue = null;
		switch (projectStatus) {
		/* if Project Status is equal to '1', set value to 'Draft' */
			case WfConstCommon.PRJ_STATUS_CODE_1_DRAFT:
				projectStatusValue = WfConstCommon.PRJ_STATUS_VALUE_DRAFT;
				break;
			/* if Project Status is equal to '2', set value to 'Planned' */
			case WfConstCommon.PRJ_STATUS_CODE_2_PLANNED:
				projectStatusValue = WfConstCommon.PRJ_STATUS_VALUE_PLANNED;
				break;
			/* if Project Status is equal to '3', set value to 'In Progress' */
			case WfConstCommon.PRJ_STATUS_CODE_3_INPROGRESS:
				projectStatusValue = WfConstCommon.PRJ_STATUS_VALUE_INPROGRESS;
				break;
			/* if Project Status is equal to '4', set value to 'Pending' */
			case WfConstCommon.PRJ_STATUS_CODE_4_PENDING:
				projectStatusValue = WfConstCommon.PRJ_STATUS_VALUE_PENDING;
				break;
			/* if Project Status is equal to '5', set value to 'Suspend' */
			case WfConstCommon.PRJ_STATUS_CODE_5_SUSPEND:
				projectStatusValue = WfConstCommon.PRJ_STATUS_VALUE_SUSPEND;
				break;
			/* if Project Status is equal to '6', set value to 'Done' */
			case WfConstCommon.PRJ_STATUS_CODE_6_DONE:
				projectStatusValue = WfConstCommon.PRJ_STATUS_VALUE_DONE;
				break;
		}
		return projectStatusValue;
	}

	/**
	 * Obtain project status code
	 * 
	 * @return projectStatusCode Project Status Code
	 */
	public String getProjectStatusCode(String projectStatus) {
		String projectStatusCode = null;
		switch (projectStatus) {
		/* if Project Status is equal to 'Draft', set value to '1' */
			case WfConstCommon.PRJ_STATUS_VALUE_DRAFT:
				projectStatusCode = WfConstCommon.PRJ_STATUS_CODE_1_DRAFT;
				break;
			/* if Project Status is equal to 'Planned', set value to '2' */
			case WfConstCommon.PRJ_STATUS_VALUE_PLANNED:
				projectStatusCode = WfConstCommon.PRJ_STATUS_CODE_2_PLANNED;
				break;
			/* if Project Status is equal to 'In Progress', set value to '3' */
			case WfConstCommon.PRJ_STATUS_VALUE_INPROGRESS:
				projectStatusCode = WfConstCommon.PRJ_STATUS_CODE_3_INPROGRESS;
				break;
			/* if Project Status is equal to 'Pending', set value to '4' */
			case WfConstCommon.PRJ_STATUS_VALUE_PENDING:
				projectStatusCode = WfConstCommon.PRJ_STATUS_CODE_4_PENDING;
				break;
			/* if Project Status is equal to 'Suspend', set value to '5' */
			case WfConstCommon.PRJ_STATUS_VALUE_SUSPEND:
				projectStatusCode = WfConstCommon.PRJ_STATUS_CODE_5_SUSPEND;
				break;
			/* if Project Status is equal to 'Done', set value to '6' */
			case WfConstCommon.PRJ_STATUS_VALUE_DONE:
				projectStatusCode = WfConstCommon.PRJ_STATUS_CODE_6_DONE;
				break;
		}
		return projectStatusCode;
	}

	/**
	 * Finds all Project entries from database
	 * 
	 * @return projects List of saved Projects
	 */
	public List<Project> findAll() {
		LinkedList<Project> projects = new LinkedList<Project>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection
					.prepareStatement(WfSqlCommon.SELECTSQL_PROJECT_001);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Project project = read(rs);
				projects.add(project);
			}
			return projects;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds a specific Project by ID entry from database
	 * 
	 * @param id ID of the Project to search
	 * @return project Equivalent Project entry of the specified ID
	 */
	public Project find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection
					.prepareStatement(WfSqlCommon.SELECTSQL_PROJECT_002);

			/* Set Parameters */
			statement.setLong(1, id.longValue()); // Project ID

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
	 * Finds a specific Project by its corresponding Project Name entry from
	 * database
	 * 
	 * @param projectName Project Name of the Project to search
	 * @return project Equivalent Project entry of the specified Project Name
	 */
	public Project findByProjectName(String projectName) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection
					.prepareStatement(WfSqlCommon.SELECTSQL_PROJECT_003);

			/* Set Parameters */
			statement.setString(1, projectName); // Project Name

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
	 * Updates specified Project entry in database
	 * 
	 * @param project Project object to update
	 */
	public void update(Project project) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			Company company = new CompanyDAO().findByCompanyCode(project
					.getCompanyCode());
			statement = connection
					.prepareStatement(WfSqlCommon.UPDATESQL_PROJECT_001);

			/* Prepare Update Parameters */
			statement.setString(1, project.getProjectName()); 			// Project Name
			statement.setString(2, project.getProjectDescription()); 	// Project Description
			statement.setLong(3, company.getCompanyId().longValue()); 	// Company ID
			statement.setString(4,
					getProjectStatusCode(project.getProjectStatus())); 	// Project Status
			if (project.getProjectStartDate() == ""){
				statement.setString(5, null);			   		
			}
			else{
				statement.setString(5, project.getProjectStartDate());		// Project Start Date					
			}
			
			statement.setLong(6, project.getProjectId().longValue()); 	// Project ID

			
			
			/* Execute SQL Statement */
			statement.executeUpdate();
			
			/* Update relation for Project and Company */
			WfUtilCommon.updateRelation(company.getCompanyId().longValue(), // Company ID
										project.getProjectId().longValue(),	// Project ID
										WfConstCommon.MST_COMPANY);			// Company Table

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Creates a new Project entry in database
	 * 
	 * @param project Project object to create
	 */
	public void create(Project project) {
		Long id = getUniqueId();
		project.setProjectId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			Company company = new CompanyDAO().findByCompanyCode(project
					.getCompanyCode());
			statement = connection
					.prepareStatement(WfSqlCommon.INSERTSQL_PROJECT_001);

			/* Set Insert Parameters */
			statement.setLong(1, id.longValue()); 						// Project ID
			statement.setString(2, project.getProjectName()); 			// Project Name
			statement.setString(3, project.getProjectDescription()); 	// Project
																		// Description
			statement.setLong(4, company.getCompanyId().longValue()); 	// Company ID
			statement.setString(5,
					getProjectStatusCode(project.getProjectStatus())); 	// Project Status
			
			if (project.getProjectStartDate() == ""){
				statement.setString(6, null);			   		
			}
			else{
				statement.setString(6, project.getProjectStartDate());		// Project Start Date						
			}

			/* Execute SQL Statement */
			statement.executeUpdate();
			
			/* Create relation for Project and Company */
			WfUtilCommon.createRelation(id, 								// Project ID
										WfConstCommon.WFG_PROJECT,			// Project Table Name
										company.getCompanyId().longValue(), // Company ID
										WfConstCommon.MST_COMPANY);			// Company Table Name

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Deletes a new Project entry in database
	 * 
	 * @param project Project object to delete
	 */
	public void delete(Project project) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection
					.prepareStatement(WfSqlCommon.DELETESQL_PROJECT_001);

			/* Set Delete Parameters */
			Long id = project.getProjectId();
			statement.setLong(1, id.longValue()); // Project ID

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
