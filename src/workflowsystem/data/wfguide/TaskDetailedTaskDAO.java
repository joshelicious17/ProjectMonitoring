package workflowsystem.data.wfguide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.data.maincontrol.User;
import workflowsystem.data.maincontrol.UserDAO;
import workflowsystem.data.prjstart.ProjectMember;
import workflowsystem.data.wfguide.Task;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;

public class TaskDetailedTaskDAO extends DataAccessObject {

	/**
	 * Reads resulting User entries from database
	 * 
	 * @param rs ResultSet
	 * @return user User data
	 * @throws SQLException SQL Error
	 */
	
	private TaskDetailedTask read(ResultSet rs) throws SQLException {
		Long taskDetailedTaskId = new Long(rs.getLong("TASKDETAILEDTASKID"));
		Long taskId = new Long(rs.getLong("TASKID"));
		Long userId = new Long(rs.getLong("USERID"));
		String assignedUserName = rs.getString("USERNAME");
		String taskDetailedTaskName = rs.getString("TASKDETAILEDTASKNAME");
		Double taskDetailedTaskHours = rs.getDouble("TASKDETAILEDTASKHOURS");
		String taskDetailedTaskDate = rs.getString("TASKDETAILEDTASKDATE");

		/* Initialize new Detailed Task object */
		TaskDetailedTask taskDetailedTask = new TaskDetailedTask();
		
		taskDetailedTask.setTaskDetailedTaskId(taskDetailedTaskId);			// Task Detailed Task ID
		taskDetailedTask.setTaskId(taskId);									// Task ID
		taskDetailedTask.setAssignedUserId(userId);					        // Assigned User ID
		taskDetailedTask.setAssignedUserName(assignedUserName);				// Assigned User Name
		taskDetailedTask.setTaskDetailedTaskName(taskDetailedTaskName);		// Task Detailed Task Name
		taskDetailedTask.setTaskDetailedTaskHours(taskDetailedTaskHours);	// Task Detailed Task Hours
		taskDetailedTask.setTaskDetailedTaskDate(taskDetailedTaskDate);		// Task Detailed Task Date
		
		return taskDetailedTask;
	}

	/**
	 * Finds all Task Detailed entries by Task ID from database
	 * 
	 * @return taskDetailedTasks 		List of saved Detailed Tasks
	 */
	public List<TaskDetailedTask> findByTaskId(Long taskId) {
		LinkedList<TaskDetailedTask> taskDetailedTasks = new LinkedList<TaskDetailedTask>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_DETAILEDTASK_001);
			
			/* Set Search Parameters */
			statement.setLong(1, taskId.longValue());				 // Task ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskDetailedTask taskDetailedTask = read(rs);
				taskDetailedTasks.add(taskDetailedTask);
			}
			return taskDetailedTasks;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
		
	/**
	 * Creates a new Task Detailed entry in database
	 * 
	 * @param taskDetailedTask 		TaskDetailedTask object to create
	 */
	public void create(TaskDetailedTask taskDetailedTask) {
		/* Generate unique id (primary key) */
		Long id = getUniqueId();

		/* Set Task Detailed Id */
		taskDetailedTask.setTaskDetailedTaskId(id);
		
		PreparedStatement statement = null;
		Connection connection = null;
		
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			User user = new UserDAO().findByUsername(taskDetailedTask.getAssignedUserName());
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_DETAILEDTASK_001);

			/* Set Insert Parameters */
			statement.setLong(1, id.longValue());													// Task Detailed Task ID
			statement.setLong(2, taskDetailedTask.getTaskId().longValue());							// Task ID		
			statement.setString(3, taskDetailedTask.getTaskDetailedTaskName());						// Task Detailed Task Name
			statement.setLong(4, user.getUserId().longValue());	                 					// Task Assigned User ID
			statement.setDouble(5, taskDetailedTask.getTaskDetailedTaskHours().doubleValue());		            // Task Detailed Task Hours
			statement.setString(6, taskDetailedTask.getTaskDetailedTaskDate());						// Task Detailed Task Hours

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
	 * Deletes a Task Detailed entry in database
	 * 
	 * @param taskDetailedTask 	TaskDetailedTask object to delete
	 */
	public void performDeleteInSave(Long taskDetailedTask) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_DETAILEDTASK_001);

			/* Set Delete Parameter */
			statement.setLong(1, taskDetailedTask.longValue());		// Task DetailedTask ID

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
	 * Finds all Task Detailed entries by Workflow ID from database
	 * 
	 * @return taskDetailedTasks 		List of saved Detailed Tasks
	 */
	public List<TaskDetailedTask> findByWorkflowId(Long workflowId) {
		LinkedList<TaskDetailedTask> taskDetailedTasks = new LinkedList<TaskDetailedTask>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_DETAILEDTASK_002);
			
			/* Set Search Parameters */
			statement.setLong(1, workflowId.longValue());				 // Workflow ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskDetailedTask taskDetailedTask = read(rs);
				taskDetailedTasks.add(taskDetailedTask);
			}
			return taskDetailedTasks;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Finds all Task Detailed entries by Project ID from database
	 * 
	 * @return taskDetailedTasks 		List of saved Detailed Tasks
	 */
	public List<TaskDetailedTask> findByProjectId(Long projectId) {
		LinkedList<TaskDetailedTask> taskDetailedTasks = new LinkedList<TaskDetailedTask>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_DETAILEDTASK_003);
			
			/* Set Search Parameters */
			statement.setLong(1, projectId.longValue());				 // Project ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskDetailedTask taskDetailedTask = read(rs);
				taskDetailedTasks.add(taskDetailedTask);
			}
			return taskDetailedTasks;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Deletes a Task Detailed entry in database
	 * 
	 * @param taskDetailedTask 	TaskDetailedTask object to delete
	 */
	public void delete(TaskDetailedTask taskDetailedTask) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_DETAILEDTASK_001);

			/* Set Delete Parameter */
			Long id = taskDetailedTask.getTaskDetailedTaskId();
			statement.setLong(1, id.longValue());		// Task DetailedTask ID

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