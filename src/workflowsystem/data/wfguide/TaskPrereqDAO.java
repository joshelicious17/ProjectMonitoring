package workflowsystem.data.wfguide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.util.WfSqlCommon;

public class TaskPrereqDAO extends DataAccessObject {
	private TaskPrereq read(ResultSet rs) throws SQLException {
		/**
		 * Reads resulting Task Prerequisite entries from database
		 * 
		 * @param rs 			ResultSet
		 * @return taskPrereq	Task Prerequisite data
		 * @throws SQLException SQL Error
		 */
		Long taskPrereqId = new Long(rs.getLong("TASKPREREQID"));
		Long taskIdParent = new Long(rs.getLong("TASKIDPARENT"));
		Long taskIdChild = new Long(rs.getLong("TASKIDCHILD"));
		String taskNameChild = rs.getString("TASKNAME");

		/* Initialize new Task Prerequisite Object */
		TaskPrereq taskPrereq = new TaskPrereq();

		/* Set values to Task Prerequisite Object */
		taskPrereq.setTaskPrereqId(taskPrereqId);	// Task Prerequisite ID
		taskPrereq.setTaskIdParent(taskIdParent);	// Task ID Parent
		taskPrereq.setTaskIdChild(taskIdChild);		// Task ID Child
		taskPrereq.setTaskNameChild(taskNameChild);	// Task Name Child

		return taskPrereq;
	}

	/**
	 * Finds all Task Prerequisite entries from database by Task ID Parent
	 * 
	 * @return taskPrereqs 	List of saved Task Prerequisites
	 */
	public List<TaskPrereq> findAllByTaskIdParent(Long taskIdParent) {
		LinkedList<TaskPrereq> taskPrereqs = new LinkedList<TaskPrereq>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_PREREQUISITE_001);

			/* Set Search Parameter */
			statement.setLong(1, taskIdParent.longValue());		// Task ID Parent

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskPrereq taskPrereq = read(rs);
				taskPrereqs.add(taskPrereq);
			}
			return taskPrereqs;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
	
		}
	}

	/**
	 * Finds all Task Prerequisite entries by Task Prerequisite ID from database
	 * 
	 * @param  id			Task Prerequisite ID
	 * @return taskPrereq 	List of saved Tasks Prerequisite
	 */
	public TaskPrereq find(Long id) {
		System.out.println("find");
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_PREREQUISITE_002);

			/* Set Search Parameter */
			statement.setLong(1, id.longValue());	// Task Prerequisite ID

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
	 * Finds all Task Prerequisite entries from database by Workflow ID
	 * 
	 * @return taskPrereqs 	List of saved Task Prerequisites
	 */
	public List<TaskPrereq> findAllByWorkflowId(Long workflowId) {
		LinkedList<TaskPrereq> taskPrereqs = new LinkedList<TaskPrereq>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_PREREQUISITE_003);

			/* Set Search Parameter */
			statement.setLong(1, workflowId.longValue());		// Workflow ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskPrereq taskPrereq = read(rs);
				taskPrereqs.add(taskPrereq);
			}
			return taskPrereqs;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
	
		}
	}
	
	/**
	 * Finds all Task Prerequisite entries from database by Project ID
	 * 
	 * @return taskPrereqs 	List of saved Task Prerequisites
	 */
	public List<TaskPrereq> findAllByProjectId(Long Id) {
		LinkedList<TaskPrereq> taskPrereqs = new LinkedList<TaskPrereq>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_PREREQUISITE_004);

			/* Set Search Parameter */
			statement.setLong(1, Id.longValue());		// Project ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskPrereq taskPrereq = read(rs);
				taskPrereqs.add(taskPrereq);
			}
			return taskPrereqs;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
	
		}
	}
	
	/**
	 * Creates a new Task Prerequisite entry in database
	 * 
	 * @param taskPrereq 		Task Prerequisite object to create
	 */
	public void create(TaskPrereq taskPrereq) {
		/* Generate unique id (primary key) */
		Long id = getUniqueId();

		/* Set Task Prerequisite Id */
		taskPrereq.setTaskPrereqId(id);
		/* Get SQL Insert Values from Task Prerequisite object */
		Long taskIdParent = taskPrereq.getTaskIdParent();
		Long taskIdChild = taskPrereq.getTaskIdChild();

		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_PREREQUISITE_001);

			/* Set Insert Parameters */
			statement.setLong(1, id.longValue());			// Task Prerequisite ID
			statement.setLong(2, taskIdParent.longValue());	// Task ID Parent
			statement.setLong(3, taskIdChild.longValue());	// Task ID Child

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Deletes a Task Prerequisite entry in database
	 * 
	 * @param taskPrereq	 	Task Prerequisite object to delete
	 */
	public void delete(TaskPrereq taskPrereq) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_PREREQUISITE_001);

			/* Set Delete Parameter */
			Long id = taskPrereq.getTaskPrereqId();
			statement.setLong(1, id.longValue());	// Task Prerequisite ID

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}
	
	/**
	 * Deletes a Task Prerequisite from session entry in database
	 * 
	 * @param taskPrereq	 	Task Prerequisite object to delete
	 */
	public void performDeleteInSave(Long taskPrereq) {
		PreparedStatement statement = null;
		Connection connection = null;
	
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_PREREQUISITE_001);

			/* Set Delete Parameter */
			statement.setLong(1, taskPrereq.longValue());	// Task Prerequisite ID

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
