package workflowsystem.data.maincontrol;

public class User {
	/** User ID */
	private Long userId;

	/** User Name */
	private String userName;

	/** User Password */
	private String userPassword;

	/** User Full Name */
	private String userFullname;

	/** Company Code */
	private String companyCode;

	/** User E-mail */
	private String userEmail;

	/** Access Type Code */
	private String accessTypeCode;
	
	/** Valid From */
	private String validFrom;
	
	/** Valid To */
	private String validTo; 
	
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
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return userFullname
	 */
	public String getUserFullname() {
		return userFullname;
	}

	/**
	 * @param userFullname
	 */
	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
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
	 * @return userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
	 * @return validFrom
	 */
	public String getValidFrom() {
		return validFrom;
	}

	/**
	 * @param validFrom
	 */
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	/**
	 * @return validTo
	 */
	public String getValidTo() {
		return validTo;
	}

	/**
	 * @param validTo
	 */
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
	
	
}