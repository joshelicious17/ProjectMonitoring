package workflowsystem.data.wfguide;

public class TaskDetailedTask{
	
	/** Task Detailed Task ID */
	private Long taskDetailedTaskId;
	
	/** Task ID */
	private Long taskId;
	
	/** Assigned User ID */
	private Long assignedUserId;
	
	/** Assigned User Name */
	private String assignedUserName;
	
	/** Task Detailed Task Name */
	private String taskDetailedTaskName;
	
	/** Task Detailed Task Hours*/
	private Double taskDetailedTaskHours;
	
	/** Task Detailed Task Date */
	private String taskDetailedTaskDate;
	
	/** Session Flag */
	private Boolean sessionFlag = false;
    
	/** Task AssignedUser Session ID */
	private Long sessionId;

	/**
	 * @return  taskDetailedTaskId
	 */
	public Long getTaskDetailedTaskId() {
		return taskDetailedTaskId;
	}

	/**
	 * @param  taskDetailedTaskId
	 */
	public void setTaskDetailedTaskId(Long taskDetailedTaskId) {
		this.taskDetailedTaskId = taskDetailedTaskId;
	}
	
	/**
	 * @return taskId
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * @return  assignedUserId
	 */
	public Long getAssignedUserId() {
		return assignedUserId;
	}

	/**
	 * @param  assignedUserId
	 */
	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}
	
	/**
	 * @return  assignedUserName
	 */
	public String getAssignedUserName() {
		return assignedUserName;
	}

	/**
	 * @param  assignedUserName
	 */
	public void setAssignedUserName(String assignedUserName) {
		this.assignedUserName = assignedUserName;
	}

	/**
	 * @return taskDetailedTaskName
	 */
	public String getTaskDetailedTaskName() {
		return taskDetailedTaskName;
	}

	/**
	 * @param taskDetailedTaskName
	 */
	public void setTaskDetailedTaskName(String taskDetailedTaskName) {
		this.taskDetailedTaskName = taskDetailedTaskName;
	}
	
	/**
	 * @return taskDetailedTaskHours
	 */
	public Double getTaskDetailedTaskHours() {
		return taskDetailedTaskHours;
	}

	/**
	 * @param taskDetailedTaskHours
	 */
	public void setTaskDetailedTaskHours(Double taskDetailedTaskHours) {
		this.taskDetailedTaskHours = taskDetailedTaskHours;
	}
	
	/**
	 * @return taskDetailedTaskDate
	 */
	public String getTaskDetailedTaskDate() {
		return taskDetailedTaskDate;
	}

	/**
	 * @param taskDetailedTaskDate
	 */
	public void setTaskDetailedTaskDate(String taskDetailedTaskDate) {
		this.taskDetailedTaskDate = taskDetailedTaskDate;
	}
	
	/**
	 * @return sessionFlag
	 */
	public Boolean getSessionFlag() {
		return sessionFlag;
	}

	/**
	 * @param sessionFlag
	 */
	public void setSessionFlag(Boolean sessionFlag) {
		this.sessionFlag = sessionFlag;
	}
	
	/**
	 * @param sessionId
	 */
	public void setSessionId (Long id){
		this.sessionId = id;
	}
	
	/**
	 * @return sessionId
	 */
	public Long getSessionId(){
		return sessionId;
	}
	
}