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

public class TaskAssignedUserDAO extends DataAccessObject {

	/**
	 * Reads resulting User entries from database
	 * 
	 * @param rs ResultSet
	 * @return user User data
	 * @throws SQLException SQL Error
	 */
	
	private TaskAssignedUser read(ResultSet rs) throws SQLException {
		Long taskAssignedUserId = new Long(rs.getLong("TASKASSIGNEDUSERID"));
		Long taskId = new Long(rs.getLong("TASKID"));
		Long userId = new Long(rs.getLong("USERID"));
		String userName = rs.getString("USERNAME");
		String taskName = rs.getString("TASKNAME");
		String projectName = rs.getString("PROJECTNAME");
		Double taskTotalHours = rs.getDouble("TASKTOTALHOURS");
		Double taskProgress = rs.getDouble("TASKPROGRESS");
		String taskPriority = rs.getString("TASKPLANINFOPRIORITY");
		String phaseCode = rs.getString("PHASECODE");

		/* Initialize new Assigned User object */
		TaskAssignedUser taskAssignedUser = new TaskAssignedUser();
		
		taskAssignedUser.setTaskAssignedUserId(taskAssignedUserId);				// Task Assigned User ID
		taskAssignedUser.setTaskId(taskId);										// Task ID
		taskAssignedUser.setUserId(userId);										// User ID
		taskAssignedUser.setUserName(userName);				      				// User Name
		taskAssignedUser.setTaskName(taskName);										// Task Name		
		taskAssignedUser.setProjectName(projectName);								// Project Name
		taskAssignedUser.setTaskTotalHours(taskTotalHours);							// Task Total Hours
		taskAssignedUser.setTaskProgress(taskProgress);								// Task Progress
		taskAssignedUser.setTaskPriority(getTaskPriorityValue(taskPriority));		// Task Priority
		taskAssignedUser.setPhaseCode(phaseCode);					                // Phase Code
		
		return taskAssignedUser;
	}

	/**
	 * Obtain task priority value
	 * 
	 * @return taskPriorityValue Project Status Value
	 */
	public String getTaskPriorityValue(String taskPriority) {
		String priorityStatusValue = null;
		
		if (taskPriority != null) {
			switch (taskPriority) {
				/* if Task Priority is equal to '1', set value to 'High' */
				case WfConstCommon.TSK_PRIORITY_CODE_1_HIGH:
					priorityStatusValue = WfConstCommon.TSK_PRIORITY_VALUE_HIGH;
					break;
				/* if Task Priority is equal to '2', set value to 'Medium' */
				case WfConstCommon.TSK_PRIORITY_CODE_2_MEDIUM:
					priorityStatusValue = WfConstCommon.TSK_PRIORITY_VALUE_MEDIUM;
					break;
				/* if Task Priority is equal to '3', set value to 'Low' */
				case WfConstCommon.TSK_PRIORITY_CODE_3_LOW:
					priorityStatusValue = WfConstCommon.TSK_PRIORITY_VALUE_LOW;
					break;
			}
		}
		
		return priorityStatusValue;
	}

	/**
	 * Obtain task priority code
	 * 
	 * @return taskPriorityCode Project Status Code
	 */
	public String getTaskPriorityCode(String taskPriority) {
		String priorityStatusCode = null;
		
		if (taskPriority != null) {
			switch (taskPriority) {
				/* if Task Priority is equal to 'High', set value to '1' */
				case WfConstCommon.TSK_PRIORITY_VALUE_HIGH:
					priorityStatusCode = WfConstCommon.TSK_PRIORITY_CODE_1_HIGH;
					break;
				/* if Task Priority is equal to 'Medium', set value to '2' */
				case WfConstCommon.TSK_PRIORITY_VALUE_MEDIUM:
					priorityStatusCode = WfConstCommon.TSK_PRIORITY_CODE_2_MEDIUM;
					break;
				/* if Task Priority is equal to 'Low', set value to '3' */
				case WfConstCommon.TSK_PRIORITY_VALUE_LOW:
					priorityStatusCode = WfConstCommon.TSK_PRIORITY_CODE_3_LOW;
					break;
			}
		}
		
		return priorityStatusCode;
	}
	
	/**
	 * Finds all Task Assigned User entries from database
	 * 
	 * @return users 	List of saved Task Assigned Users
	 */
	public List<TaskAssignedUser> findByTaskId(Long taskId) {
		LinkedList<TaskAssignedUser> taskAssignedUsers = new LinkedList<TaskAssignedUser>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_ASSIGNEDUSER_001);
			
			/* Set Search Parameters */
			statement.setLong(1, taskId.longValue());				 // Task ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskAssignedUser taskAssignedUser = read(rs);
				taskAssignedUsers.add(taskAssignedUser);
			}
			return taskAssignedUsers;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Finds a specific Task Assigned User by ID entry from database
	 * 
	 * @param taskAssignedUserId	 ID of the Task Assigned User to search
	 * @return task assigned user 	 Entry of the specified ID
	 */
	public TaskAssignedUser findByTaskAssignedUserId(Long taskAssignedUserId) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_ASSIGNEDUSER_002);

			/* Set Parameters */
			statement.setLong(1, taskAssignedUserId.longValue());	 	// Task Assigned User ID

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
	 * Finds all Task Assigned User entries from database by Workflow ID
	 * 
	 * @return users 	List of saved Task Assigned Users
	 */
	public List<TaskAssignedUser> findByWorkflowId (Long workflowId) {
		LinkedList<TaskAssignedUser> taskAssignedUsers = new LinkedList<TaskAssignedUser>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_ASSIGNEDUSER_003);
			
			/* Set Search Parameters */
			statement.setLong(1, workflowId.longValue());				 // Workflow ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskAssignedUser taskAssignedUser = read(rs);
				taskAssignedUsers.add(taskAssignedUser);
			}
			return taskAssignedUsers;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Finds all Task Assigned User entries from database by Project ID
	 * 
	 * @return users 	List of saved Task Assigned Users
	 */
	public List<TaskAssignedUser> findByProjectId(Long Id) {
		LinkedList<TaskAssignedUser> taskAssignedUsers = new LinkedList<TaskAssignedUser>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_ASSIGNEDUSER_004);
			
			/* Set Search Parameters */
			statement.setLong(1,Id.longValue());				 // Project ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				TaskAssignedUser taskAssignedUser = read(rs);
				taskAssignedUsers.add(taskAssignedUser);
			}
			return taskAssignedUsers;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
		
				
	/**
	 * Creates a new Task Assigned User entry in database
	 * 
	 * @param taskAssignedUser 		TaskAssignedUser object to create
	 */
	public void create(TaskAssignedUser taskAssignedUser) {
		/* Generate unique id (primary key) */
		Long id = getUniqueId();

		/* Set Task Prerequisite Id */
		taskAssignedUser.setTaskAssignedUserId(id);
		
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();
//			User user = new UserDAO().find(taskAssignedUser.getUserId());

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_ASSIGNEDUSER_001);

			/* Set Insert Parameters */
			statement.setLong(1, id.longValue());					// Task Assigned User ID
			statement.setLong(2, taskAssignedUser.getTaskId());		// Task ID
			statement.setLong(3, taskAssignedUser.getUserId());		// User ID

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
	 * Will delete all Task Assigned User entry in database when whole task deleted 
	 * 
	 * @param taskAssignedUserId	 	TaskAssignedUser object to delete
	 */
	public void performDeleteInSave(Long taskAssignedUserId) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_ASSIGNEDUSER_001);

			/* Set Delete Parameter */
			statement.setLong(1, taskAssignedUserId.longValue());	// Task Assigned User ID

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
	 * Deletes a new Task Checkpoint entry in database
	 * 
	 * @param taskCheckpoint	 	Task Checkpoint object to delete
	 */
	public void delete(TaskAssignedUser taskAssignedUser) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_ASSIGNEDUSER_001);

			/* Set Delete Parameters */
			Long id = taskAssignedUser.getTaskAssignedUserId();
			statement.setLong(1, id.longValue());			// Task Assigned User ID

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