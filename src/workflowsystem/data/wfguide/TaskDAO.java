package workflowsystem.data.wfguide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfSqlCommon;

public class TaskDAO extends DataAccessObject {
	/**
	 * Reads resulting Task entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return task 		Task data
	 * @throws SQLException SQL Error
	 */
	private Task read(ResultSet rs) throws SQLException {
		Long taskId = new Long(rs.getLong("TASKID"));
		Long workflowId = new Long(rs.getLong("WORKFLOWID"));
		Long projectId = new Long(rs.getLong("PROJECTID"));
		String taskName = rs.getString("TASKNAME");
		String taskWorkPoints = rs.getString("TASKWORKPOINTS");
		Double taskTotalHours = rs.getDouble("TASKTOTALHOURS");
		Double taskProgress = rs.getDouble("TASKPROGRESS");
		String taskDeadline = rs.getString("TASKDEADLINE");
		Double taskPlannedHours = rs.getDouble("TASKPLANINFOPLANNEDHOURS");
		String taskPriority = rs.getString("TASKPLANINFOPRIORITY");
		String taskPlanStartDate = rs.getString("TASKPLANINFOSTARTDATE");
		String taskPlanEndDate = rs.getString("TASKPLANINFOENDDATE");
		String taskPlanComment = rs.getString("TASKPLANINFOCOMMENT");
		String taskStatus= rs.getString("TASKACTIVITYINFOSTATUS");

		/* Initialize Task Object */
		Task task = new Task();

		/* Set values to Task Object */
		task.setTaskId(taskId);											// Task ID
		task.setWorkflowId(workflowId);									// Workflow ID
		task.setProjectId(projectId);									// Project ID
		task.setTaskName(taskName);										// Task Name
		task.setTaskWorkPoints(taskWorkPoints);							// Task Work Points
		task.setTaskTotalHours(taskTotalHours);							// Task Total Hours
		task.setTaskProgress(taskProgress);								// Task Progress
		task.setTaskDeadline(taskDeadline);								// Task Deadline
		task.setTaskPlannedHours(taskPlannedHours);						// Task PlannedHours
		task.setTaskPriority(getTaskPriorityValue(taskPriority));		// Task Priority
		task.setTaskPlanStartDate(taskPlanStartDate);					// Task Plan Start Date
		task.setTaskPlanEndDate(taskPlanEndDate);						// Task Plan End Date
		task.setTaskPlanComment(taskPlanComment);						// Task Plan Comment
		task.setTaskStatus(getTaskStatusValue(taskStatus));				// Task Priority
		
		return task;
	}

	private Task readDBoard(ResultSet rs) throws SQLException {
		Long taskId = new Long(rs.getLong("TASKID"));
		Long workflowId = new Long(rs.getLong("WORKFLOWID"));
		Long projectId = new Long(rs.getLong("PROJECTID"));
		String taskName = rs.getString("TASKNAME");
		String taskWorkPoints = rs.getString("TASKWORKPOINTS");
		Double taskTotalHours = rs.getDouble("TASKTOTALHOURS");
		Double taskProgress = rs.getDouble("TASKPROGRESS");
		String taskDeadline = rs.getString("TASKDEADLINE");
		Double taskPlannedHours = rs.getDouble("TASKPLANINFOPLANNEDHOURS");
		String taskPriority = rs.getString("TASKPLANINFOPRIORITY");
		String taskPlanStartDate = rs.getString("TASKPLANINFOSTARTDATE");
		String taskPlanEndDate = rs.getString("TASKPLANINFOENDDATE");
		String taskPlanComment = rs.getString("TASKPLANINFOCOMMENT");
		String taskStatus= rs.getString("TASKACTIVITYINFOSTATUS");
		String projectName = rs.getString("PROJECTNAME");
		String phaseCode = rs.getString("PHASECODE");

		/* Initialize Task Object */
		Task task = new Task();

		/* Set values to Task Object */
		task.setTaskId(taskId);											// Task ID
		task.setWorkflowId(workflowId);									// Workflow ID
		task.setProjectId(projectId);									// Project ID
		task.setTaskName(taskName);										// Task Name
		task.setTaskWorkPoints(taskWorkPoints);							// Task Work Points
		task.setTaskTotalHours(taskTotalHours);							// Task Total Hours
		task.setTaskProgress(taskProgress);								// Task Progress
		task.setTaskDeadline(taskDeadline);								// Task Deadline
		task.setTaskPlannedHours(taskPlannedHours);						// Task PlannedHours
		task.setTaskPriority(getTaskPriorityValue(taskPriority));		// Task Priority
		task.setTaskPlanStartDate(taskPlanStartDate);					// Task Plan Start Date
		task.setTaskPlanEndDate(taskPlanEndDate);						// Task Plan End Date
		task.setTaskPlanComment(taskPlanComment);						// Task Plan Comment
		task.setTaskStatus(getTaskStatusValue(taskStatus));				// Task Priority
		task.setProjectName(projectName);									// Project Name
		task.setPhaseCode(phaseCode);									// Phase Code
		
		return task;
	}

	
	/**
	 * Obtain task priority value
	 * 
	 * @return taskPriorityValue Task Priority Value
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
	 * @return taskPriorityCode Task Priority Code
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
	 * Obtain task status value
	 * 
	 * @return taskStatusValue Task Status Value
	 */
	public String getTaskStatusValue(String taskStatus) {
		String taskStatusValue = null;
		
		if (taskStatus != null) {
			switch (taskStatus) {
				/* if Project Status is equal to '1', set value to 'Draft' */
				case WfConstCommon.TSK_STATUS_CODE_1_DRAFT:
					taskStatusValue = WfConstCommon.TSK_STATUS_VALUE_DRAFT;
					break;
				/* if Project Status is equal to '2', set value to 'New' */
				case WfConstCommon.TSK_STATUS_CODE_2_NEW:
					taskStatusValue = WfConstCommon.TSK_STATUS_VALUE_NEW;
					break;	
				/* if Project Status is equal to '3', set value to 'In Progress' */
				case WfConstCommon.TSK_STATUS_CODE_3_INPROGRESS:
					taskStatusValue = WfConstCommon.TSK_STATUS_VALUE_INPROGRESS;
					break;
				/* if Project Status is equal to '4', set value to 'On-Hold' */
				case WfConstCommon.TSK_STATUS_CODE_4_ONHOLD:
					taskStatusValue = WfConstCommon.TSK_STATUS_VALUE_ONHOLD;
					break;
				/* if Project Status is equal to '5', set value to 'Stop' */
				case WfConstCommon.TSK_STATUS_CODE_5_STOP:
					taskStatusValue = WfConstCommon.TSK_STATUS_VALUE_STOP;
					break;
				/* if Project Status is equal to '6', set value to 'Completed' */
				case WfConstCommon.TSK_STATUS_CODE_6_COMPLETED:
					taskStatusValue = WfConstCommon.TSK_STATUS_VALUE_COMPLETED;
					break;			
			}
		}
		
		return taskStatusValue;
	}

	/**
	 * Obtain task status code
	 * 
	 * @return taskStatusCode Task Status Code
	 */
	public String getTaskStatusCode(String taskStatus) {
		String taskStatusCode = null;
		
		if (taskStatus != null) {
			switch (taskStatus) {
				/* if Project Status is equal to 'Draft', set value to '1' */
				case WfConstCommon.TSK_STATUS_VALUE_DRAFT:
					taskStatusCode = WfConstCommon.TSK_STATUS_CODE_1_DRAFT;
					break;
				/* if Project Status is equal to 'New', set value to '2' */
				case WfConstCommon.TSK_STATUS_VALUE_NEW:
					taskStatusCode = WfConstCommon.TSK_STATUS_CODE_2_NEW;
					break;
				/* if Project Status is equal to 'In Progress', set value to '3' */
				case WfConstCommon.TSK_STATUS_VALUE_INPROGRESS:
					taskStatusCode = WfConstCommon.TSK_STATUS_CODE_3_INPROGRESS;
					break;
				/* if Project Status is equal to 'On-Hold', set value to '4' */
				case WfConstCommon.TSK_STATUS_VALUE_ONHOLD:
					taskStatusCode = WfConstCommon.TSK_STATUS_CODE_4_ONHOLD;
					break;
				/* if Project Status is equal to 'Stop', set value to '5' */
				case WfConstCommon.TSK_STATUS_VALUE_STOP:
					taskStatusCode = WfConstCommon.TSK_STATUS_CODE_5_STOP;
					break;
				/* if Project Status is equal to 'Completed', set value to '6' */
				case WfConstCommon.TSK_STATUS_VALUE_COMPLETED:
					taskStatusCode = WfConstCommon.TSK_STATUS_CODE_6_COMPLETED;
					break;			
			}
		}
		
		return taskStatusCode;
	}
	

	/**
	 * Finds all Task entries from database
	 * 
	 * @return tasks 	List of saved Tasks
	 */
	public List<Task> findAll(Long id) {
		LinkedList<Task> tasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_001);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	// Workflow ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = read(rs);
				tasks.add(task);
			}
			return tasks;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all Prerequisite Task entries from database
	 * 
	 * @return tasks 	List of saved Prerequisite Tasks
	 */
	public List<Task> findPrereq(Long id, Long taskId) {
		LinkedList<Task> tasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_002);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());		// Workflow ID
			statement.setLong(2, taskId.longValue());	// Task ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = read(rs);
				tasks.add(task);
			}
			return tasks;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all Task entries by Task ID from database
	 * 
	 * @param  id		Task ID
	 * @return tasks 	List of saved Tasks
	 */
	public Task find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		TaskDetailedTask detailedTask = new TaskDetailedTask();
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_003);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	// Task ID

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
	 * Finds all  Daily Task entries from database based on user id,
	 *     Task Info Start Date and Task Status
	 * @param userId 		User ID of the User to search
	 * @return tasks 	
	 */
	public List<Task> findAllTaskDaily(Long id) { 
		LinkedList<Task> tasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();	
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_004);
			

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	                                // user ID
			statement.setString(2, WfConstCommon.TSK_STATUS_CODE_2_NEW);	        // Task Status New
			statement.setString(3, WfConstCommon.TSK_STATUS_CODE_3_INPROGRESS);	   // Task Status In Progress
			statement.setString(4, WfConstCommon.TSK_STATUS_CODE_4_ONHOLD);	       // Task Status On Hold
			
			
			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = readDBoard(rs);
				tasks.add(task);
			}
			return tasks;


		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all  Weekly Task entries from database based on  user id,
	 *       Task Info Start and End Date and Task Status
	 * @param userId 		User ID of the User to search
	 * @return tasks 	
	 */
	public List<Task> findAllTaskWeekly(Long id) { 
		LinkedList<Task> tasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();	
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_005);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	                                // user ID
			statement.setString(2, WfConstCommon.TSK_STATUS_CODE_2_NEW);	        // Task Status New
			statement.setString(3, WfConstCommon.TSK_STATUS_CODE_3_INPROGRESS);	   // Task Status In Progress
			statement.setString(4, WfConstCommon.TSK_STATUS_CODE_4_ONHOLD);	       // Task Status On Hold
			

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = readDBoard(rs);
				tasks.add(task);
			}
			return tasks;


		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all Task entries from database based  user id  
	 *     
	 * @param userId 		User ID of the User to search
	 * @return tasks 	
	 */
	public List<Task> findAllTask(Long id) { 
		LinkedList<Task> tasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();	
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_006);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	                                // user ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = readDBoard(rs);
				tasks.add(task);
			}
			return tasks;


		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all pending  Task entries from database based on user id,
	 *     Task Deadline and Task Status
	 * @param userId 		User ID of the User to search
	 * @return tasks 	
	 */
	public List<Task> findAllPendingTask(Long id) { 
		LinkedList<Task> pendingTasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();	
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_007);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	                                 // user ID
			statement.setString(2, WfConstCommon.TSK_STATUS_CODE_1_DRAFT);	        // Task Status Draft
			statement.setString(3, WfConstCommon.TSK_STATUS_CODE_2_NEW);	        // Task Status New
			statement.setString(4, WfConstCommon.TSK_STATUS_CODE_3_INPROGRESS);	   // Task Status In Progress
			statement.setString(5, WfConstCommon.TSK_STATUS_CODE_4_ONHOLD);	       // Task Status On Hold

			
			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = readDBoard(rs);
				pendingTasks.add(task);
			}
			return pendingTasks;


		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}	
	
	/**
	 * Finds all Task entries from database by Project ID
	 * 
	 * @return tasks 	List of saved Tasks
	 */
	public List<Task> findAllByProjectId (Long id) {
		LinkedList<Task> tasks = new LinkedList<Task>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_TASK_008);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	// Project ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Task task = read(rs);
				tasks.add(task);
			}
			return tasks;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Updates specified Task entry in database
	 * 
	 * @param task	 	Task object to update
	 */
	public void update(Task task) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_TASK_001);

			/* Set Update Parameters */
			statement.setString(1, task.getTaskName());									// Task Name
			statement.setString(2, task.getTaskWorkPoints());							// Task Work Points
			statement.setDouble(3, task.getTaskTotalHours().doubleValue());				// Task Total Hours
			statement.setDouble(4, task.getTaskProgress().doubleValue());				// Task Progress
			statement.setString(5, task.getTaskDeadline());								// Task Deadline
			statement.setDouble(6, task.getTaskPlannedHours());    						// Task Planned Hours
			statement.setString(7, getTaskPriorityCode(task.getTaskPriority()));        // Task Priority
			statement.setString(8, task.getTaskPlanStartDate());						// Task Planned Start Date				
			statement.setString(9, task.getTaskPlanEndDate());						    // Task Planned End Date						
			statement.setString(10, task.getTaskPlanComment());      					// Task Plan Comment
			statement.setString(11, getTaskStatusCode(task.getTaskStatus()));			// Task Status
			statement.setLong(12, task.getTaskId().longValue());					    // Task ID
			
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
	 * Creates a new Task entry in database
	 * 
	 * @param task 		Task object to create
	 */
	public void create(Task task) {
		Long id = getUniqueId();
		task.setTaskId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_001);

			/* Set Insert Parameters */
			statement.setLong(1, id.longValue());										// Task ID
			statement.setLong(2, task.getWorkflowId().longValue());						// Workflow ID
			statement.setString(3, task.getTaskName());									// Task Name
			statement.setString(4, task.getTaskWorkPoints());							// Task Work Points
			statement.setString(5, task.getTaskDeadline());								// Task Deadline
            statement.setString(6, task.getTaskStatus());								// Task Activity Status
			
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
	 * Deletes a new Task entry in database
	 * 
	 * @param task	 	Task object to delete
	 */
	public void delete(Task task) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Prepare SQL Statement */
			connection = getConnection();
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_TASK_001);

			/* Set Delete Parameters */
			Long id = task.getTaskId();
			statement.setLong(1, id.longValue());	// Task ID

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
