package workflowsystem.data.wfguide;

public class Project {
	/** Project ID */
	private Long projectId;

	/** Project Name */
	private String projectName;

	/** Project Description */
	private String projectDescription;

	/** Company Code */
	private String companyCode;
	
	/** Company Name */
	private String companyName;

	/** Project Status */
	private String projectStatus;
	
	/** Project Start Date */
	private String projectStartDate;

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
	 * @return projectDescription
	 */
	public String getProjectDescription() {
		return projectDescription;
	}

	/**
	 * @param projectDescription
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	/**
	 * @return companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	
	/**
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return projectStatus
	 */
	public String getProjectStatus() {
		return projectStatus;
	}

	/**
	 * @param projectStatus
	 */
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	/**
	 * @return projectStartDate
	 */
	public String getProjectStartDate() {
		return projectStartDate;
	}

	/**
	 * @param projectStartDate
	 */
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
}
