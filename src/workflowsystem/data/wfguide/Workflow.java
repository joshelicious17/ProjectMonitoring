package workflowsystem.data.wfguide;

public class Workflow {
	/** Workflow ID */
	private Long workflowId;

	/** Project Name */
	private String projectName;

	/** Workflow Name */
	private String workflowName;

	/** Workflow Sequence */
	private Long workflowSequence;

	/** Phase Code */
	private String phaseCode;

	/** Project ID */
	private Long projectId;

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
	 * @return workflowName
	 */
	public String getWorkflowName() {
		return workflowName;
	}

	/**
	 * @param workflowName
	 */
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	/**
	 * @return workflowSequence
	 */
	public Long getWorkflowSequence() {
		return workflowSequence;
	}

	/**
	 * @param workflowSequence
	 */
	public void setWorkflowSequence(Long workflowSequence) {
		this.workflowSequence = workflowSequence;
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

}
