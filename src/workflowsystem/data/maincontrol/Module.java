package workflowsystem.data.maincontrol;

public class Module
{
	/** Module Id */
	private Long moduleId;

	/** Module Name */
	private String moduleName;
	
	/** Module Sequence */
	private Long moduleSequence;

	/** URL Pattern */
	private String urlPattern;
	
	/**
	 * @param moduleId
	 */
	public Long getModuleId() {
		return moduleId;
	}

	/**
	 * @return moduleId
	 */
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @param moduleId
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @return moduleName
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @param moduleSequence
	 */
	public Long getModuleSequence() {
		return moduleSequence;
	}

	/**
	 * @return moduleSequence
	 */
	public void setModuleSequence(Long moduleSequence) {
		this.moduleSequence = moduleSequence;
	}

	/**
	 * @param urlPattern
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @return urlPattern
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
}
