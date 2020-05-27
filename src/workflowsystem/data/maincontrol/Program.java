package workflowsystem.data.maincontrol;

public class Program
{
	/** Program Id */
	private Long programId;

	/** Program Name */
	private String programName;

	/** Module Name */
	private String moduleName;

	/** Java Servlet Reference */
	private String javaServletReference;
	
	/** URL Pattern */
	private String urlPattern;
	
	/** Within the Menu */
	private Boolean withinTheMenu;
	
	/**
	 * @return programId
	 */	
	public Long getProgramId() {
		return programId;
	}

	/**
	 * @param programId
	 */
	public void setProgramId(Long programId) {
		this.programId = programId;
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
