package workflowsystem.data.wfguide;

import java.util.ArrayList;

public class TaskPrereq {

	/** Task Prerequisite ID */
	private Long taskPrereqId;

	/** Task ID Parent */
	private Long taskIdParent;

	/** Task ID Child */
	private Long taskIdChild;

	/** Task Name Child */
	private String taskNameChild;

	/** Session Flag */
	private Boolean sessionFlag = false;
    
	/** Task Prerequisite Session ID */
	private Long sessionId;
	
	/**
	 * @return taskPrereqId
	 */
	public Long getTaskPrereqId() {
		return taskPrereqId;
	}

	/**
	 * @param taskPrereqId
	 */
	public void setTaskPrereqId(Long taskPrereqId) {
		this.taskPrereqId = taskPrereqId;
	}

	/**
	 * @return taskIdParent
	 */
	public Long getTaskIdParent() {
		return taskIdParent;
	}

	/**
	 * @param taskIdParent
	 */
	public void setTaskIdParent(Long taskIdParent) {
		this.taskIdParent = taskIdParent;
	}

	/**
	 * @return taskIdChild
	 */
	public Long getTaskIdChild() {
		return taskIdChild;
	}

	/**
	 * @param taskIdChild
	 */
	public void setTaskIdChild(Long taskIdChild) {
		this.taskIdChild = taskIdChild;
	}

	/**
	 * @return taskNameChild
	 */
	public String getTaskNameChild() {
		return taskNameChild;
	}

	/**
	 * @param taskNameChild
	 */
	public void setTaskNameChild(String taskNameChild) {
		this.taskNameChild = taskNameChild;
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

