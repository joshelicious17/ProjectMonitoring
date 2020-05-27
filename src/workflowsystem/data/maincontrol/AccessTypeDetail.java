package workflowsystem.data.maincontrol;

public class AccessTypeDetail {
	/** Access Type Detail ID */
	private Long accessTypeDetailId;
 
	/** Access Type ID */
	private Long accessTypeId;

	/** Program Name */
	private String programName;

	/** Access Right */
	private String accessRight;

	/** Java Servlet Reference */
	private String javaServletReference;

	/** Module Name */
	private String moduleName;
	
	/** URL Pattern */
	private String urlPattern;
	
	/** Within the Menu */
	private Boolean withinTheMenu;
	
	/**
	 * @return accessTypeDetailId
	 */
	public Long getAccessTypeDetailId() {
		return accessTypeDetailId;
	}

	/**
	 * @param accessTypeDetailId
	 */
	public void setAccessTypeDetailId(Long accessTypeDetailId) {
		this.accessTypeDetailId = accessTypeDetailId;
	}

	/**
	 * @return accessTypeId
	 */
	public Long getAccessTypeId() {
		return accessTypeId;
	}
	
	/**
	 * @param accessTypeId
	 */
	public void setAccessTypeId(Long accessTypeId) {
		this.accessTypeId = accessTypeId;
	}

	/**
	 * @return programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return accessRight
	 */
	public String getAccessRight() {
		return accessRight;
	}

	/**
	 * @param accessRight
	 */
	public void setAccessRight(String accessRight) {
		this.accessRight = accessRight;
	}

	/**
	 * @return javaServletReference
	 */
	public String getJavaServletReference() {
		return javaServletReference;
	}

	/**
	 * @param javaServletReference
	 */
	public void setJavaServletReference(String javaServletReference) {
		this.javaServletReference = javaServletReference;
	}

	/**
	 * @return moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return urlPattern
	 */
	public String getUrlPattern() {
		return urlPattern;
	}

	/**
	 * @param urlPattern
	 */
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}

	/**
	 * @return withinTheMenu
	 */
	public Boolean getWithinTheMenu() {
		return withinTheMenu;
	}

	/**
	 * @param withinTheMenu
	 */
	public void setWithinTheMenu(Boolean withinTheMenu) {
		this.withinTheMenu = withinTheMenu;
	}
}
