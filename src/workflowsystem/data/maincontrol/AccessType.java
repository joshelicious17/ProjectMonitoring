
package workflowsystem.data.maincontrol;

public class AccessType
{ 
	/** Access Type Id */
	private Long accessTypeId;

	/** Access Type Code */
	private String accessTypeCode;

	/** Access Type Name */
	private String accessTypeName;

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
	 * @return accessTypeCode
	 */
	public String getAccessTypeCode() {
		return accessTypeCode;
	}

	/**
	 * @param accessTypeCode
	 */
	public void setAccessTypeCode(String accessTypeCode) {
		this.accessTypeCode = accessTypeCode;
	}

	/**
	 * @return accessTypeName
	 */
	public String getAccessTypeName() {
		return accessTypeName;
	}

	/**
	 * @param accessTypeName
	 */
	public void setAccessTypeName(String accessTypeName) {
		this.accessTypeName = accessTypeName;
	}
}
