package workflowsystem.data.wfguide;

import java.util.ArrayList;
import java.util.Date;


public class Task {
	
	public Task() {}
	/** Task Constructor */
	public Task (Long taskId, 
				Long workflowId, 
				Long projectId, 
				String taskName,
				String taskWorkPoints,
				Double taskTotalHours,
				Double taskProgress,
				String taskDeadline,	
				Double taskPlannedHours,
				String taskPriority,
				String taskPlanStartDate,
				String taskPlanEndDate,
				String taskPlanComment,
				String taskStatus ) {
				this.taskId = taskId;
				this.workflowId = workflowId;
				this.projectId = projectId;
				this.taskName = taskName;
				this.taskWorkPoints = taskWorkPoints;
				this.taskTotalHours =  taskTotalHours;
				this.taskProgress = taskProgress;
				this.taskDeadline = taskDeadline;
				this.taskPlannedHours = taskPlannedHours;
				this.taskPriority = taskPriority;
				this.taskPlanStartDate = taskPlanStartDate;
				this.taskPlanEndDate = taskPlanEndDate;
				this.taskPlanComment = taskPlanComment;
				this.taskStatus = taskStatus;
	}

	/** Task ID */
	private Long taskId;

	/** Workflow ID */
	private Long workflowId;

	/** Project ID */
	private Long projectId;

	/** Task Name */
	private String taskName;

	/** Task Work Points */
	private String taskWorkPoints;

	/** Task Total Hours */
	private Double taskTotalHours;

	/** Task Progress */
	private Double taskProgress;

	/** Task Deadline */
	private String taskDeadline;
	
	/** Task Request ID */
	private Long taskRequestId;
	
	/** Task Planned Hours */
	private Double taskPlannedHours;
	
	/** Task Priority */
	private String taskPriority;
	
	/** Task Plan Start Date */
	private String taskPlanStartDate;
	
	/** Task Plan End Date */
	private String taskPlanEndDate;
	
	/** Task Plan Comment */
	private String taskPlanComment;
	
	/** Task Status */
	private String taskStatus;
	
	/** Task Error */
	private String taskError;
	
	/**
	 * @return taskId
	 */
	public Long getTaskId() {
		return taskId;
	}
	
	/** Project Name */
	private String projectName;
	
	/** Phase Code */
	private String phaseCode;

	/**
	 * @param taskId
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return workflowId
	 */
	public Long getWorkflowId() {
		return workflowId;
	}

	/**
	 * @param workflowId
	 */
	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	
	/**
	 * @return projectId
	 */
	public Long getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId
	 */
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
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
	 * @return taskWorkPoints
	 */
	public String getTaskWorkPoints() {
		return taskWorkPoints;
	}

	/**
	 * @param taskWorkPoints
	 */
	public void setTaskWorkPoints(String taskWorkPoints) {
		this.taskWorkPoints = taskWorkPoints;
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
	 * @return taskDeadline
	 */
	public String getTaskDeadline() {
		return taskDeadline;
	}

	/**
	 * @param taskDeadline
	 */
	public void setTaskDeadline(String taskDeadline) {
		this.taskDeadline = taskDeadline;
	}
	
	/**
	 * @return taskRequestID
	 */
	public Long getTaskRequestId() {
		return taskRequestId;
	}

	/**
	 * @param taskRequestId
	 */
	public void setRequestId(Long requestId) {
		this.taskRequestId = taskRequestId;
	}
	
	/**
	 * @return taskPlannedhours
	 */
	public Double getTaskPlannedHours() {
		return taskPlannedHours;
	}

	/**
	 * @param taskPlannedhours
	 */
	public void setTaskPlannedHours(Double taskPlannedHours) {
		this.taskPlannedHours = taskPlannedHours;
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
	 * @return taskPlanStartDate
	 */
	public String getTaskPlanStartDate() {
		return taskPlanStartDate;
	}

	/**
	 * @param taskPlanStartDate
	 */
	public void setTaskPlanStartDate(String taskPlanStartDate) {
		this.taskPlanStartDate = taskPlanStartDate;
	}
	
	/**
	 * @return taskPlanEndDate
	 */
	public String getTaskPlanEndDate() {
		return taskPlanEndDate;
	}

	/**
	 * @param taskPlanEndDate
	 */
	public void setTaskPlanEndDate(String taskPlanEndDate) {
		this.taskPlanEndDate = taskPlanEndDate;
	}
	
	/**
	 * @return taskPlanComment
	 */
	public String getTaskPlanComment() {
		return taskPlanComment;
	}

	/**
	 * @param taskWorkPoints
	 */
	public void setTaskPlanComment(String taskPlanComment) {
		this.taskPlanComment = taskPlanComment;
	}
	
	/**
	 * @return taskStatus
	 */
	public String getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	/**
	 * @return taskStatus
	 */
	public String getTaskError() {
		return taskError;
	}

	/**
	 * @param taskStatus
	 */
	public void setTaskError(String taskError) {
		this.taskError = taskError;
	}
	
	/**
	 * @return projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

}
