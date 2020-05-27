package workflowsystem.data.maincontrol;

public class Phase {
	/** Phase ID */
	private Long phaseId;

	/** Phase Code */
	private String phaseCode;

	/** Phase Name */
	private String phaseName;

	/**
	 * @return phaseId
	 */
	public Long getPhaseId() {
		return phaseId;
	}

	/**
	 * @param phaseId
	 */
	public void setPhaseId(Long phaseId) {
		this.phaseId = phaseId;
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
	 * @return phaseName
	 */
	public String getPhaseName() {
		return phaseName;
	}

	/**
	 * @param phaseName
	 */
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
}
