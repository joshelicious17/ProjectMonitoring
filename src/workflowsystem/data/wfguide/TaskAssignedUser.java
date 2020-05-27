package workflowsystem.data.wfguide;

public class TaskAssignedUser{
	/** Task Assigned User ID */
	private Long taskAssignedUserId;
	
	/** Task ID */
	private Long taskId;
	
	/** User ID */
	private Long userId;
	
	/** User Name */
	private String userName;
	
	/** Task Name */
	private String taskName;
	
	/** Project Name */
	private String projectName;
	
	/** Task Total Hours */
	private Double taskTotalHours;
	
	/** Task Progress */
	private Double taskProgress;
	
	/** Task Priority */
	private String taskPriority;
	
	/** Phase Code */
	private String phaseCode;
	
	/** Session Flag */
	private Boolean sessionFlag = false;
    
	/** Task AssignedUser Session ID */
	private Long sessionId;

	/**
	 * @return  taskAssignedUserId
	 */
	public Long getTaskAssignedUserId() {
		return taskAssignedUserId;
	}

	/**
	 * @param  taskAssignedUserId
	 */
	public void setTaskAssignedUserId(Long taskAssignedUserId) {
		this.taskAssignedUserId = taskAssignedUserId;
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
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	/**
	 * @return projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param taskName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/**
	 * @return taskTotalHours
	 */
	public Double getTaskTotalHours() {
		return taskTotalHours;
	}

	/**
	 * @param taskTotalHours
	 */
	public void setTaskTotalHours(Double taskTotalHours) {
		this.taskTotalHours = taskTotalHours;
	}
	
	/**
	 * @return taskProgress
	 */
	public Double getTaskProgress() {
		return taskProgress;
	}

	/**
	 * @param taskProgress
	 */
	public void setTaskProgress(Double taskProgress) {
		this.taskProgress = taskProgress;
	}

	/**
	 * @return taskPriority
	 */
	public String getTaskPriority() {
		return taskPriority;
	}

	/**
	 * @param taskPriority
	 */
	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	
	/**
	 * @return phaseCode
	 */
	public String getPhaseCode() {
	return phaseCode;
	}

	/**
	 * @param phaseCode
	 */
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
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