package workflowsystem.util;

public class WfConstCommon {

	/** Constant Variables */

	/* Character Space 1 */
	public static final String CHAR_SPACE_1 = " ";

	/* Zero Value */
	public static final int ZERO = 0;

	/* Project Status Value 'Draft' */
	public static final String PRJ_STATUS_VALUE_DRAFT = "æ��æ¡ˆ";

	/* Project Status Value 'Planned' */
	public static final String PRJ_STATUS_VALUE_PLANNED = "è¨ˆç”»ä¸­";

	/* Project Status Value 'In Progress' */
	public static final String PRJ_STATUS_VALUE_INPROGRESS = "é€²è¡Œä¸­";

	/* Project Status Value 'Pending' */
	public static final String PRJ_STATUS_VALUE_PENDING = "ä¿�ç•™";

	/* Project Status Value 'Suspend' */
	public static final String PRJ_STATUS_VALUE_SUSPEND = "ä¸­æ­¢";

	/* Project Status Value 'Done' */
	public static final String PRJ_STATUS_VALUE_DONE = "å®Œäº†";

	/* Project Status Code '1' */
	public static final String PRJ_STATUS_CODE_1_DRAFT = "1";

	/* Project Status Code '2' */
	public static final String PRJ_STATUS_CODE_2_PLANNED = "2";

	/* Project Status Code '3' */
	public static final String PRJ_STATUS_CODE_3_INPROGRESS = "3";

	/* Project Status Code '4' */
	public static final String PRJ_STATUS_CODE_4_PENDING = "4";

	/* Project Status Code '5' */
	public static final String PRJ_STATUS_CODE_5_SUSPEND = "5";

	/* Project Status Code '6' */
	public static final String PRJ_STATUS_CODE_6_DONE = "6";

	/* Task Document Input or Output Value 'Input' */
	public static final String TASK_DOCUMENT_IO_VALUE_INPUT = "1";

	/* Task Document Input or Output Value 'Output' */
	public static final String TASK_DOCUMENT_IO_VALUE_OUTPUT = "2";

	/* Vocabulary Parent Node */
	public static final long PARENT_NODE = 0;

	/* Vocabulary Category Root Parent Name */
	public static final String PARENT_NAME = "ãƒ«ãƒ¼ãƒˆ";

	/* Maximum Memory Size */
	public static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;

	/* Maximum Request Size */
	public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;

	/* E-mail Subject: Password Reset */
	public static final String SUBJECT_PASSWORD_RESET = "ã€�é€šçŸ¥ã€‘ãƒ‘ã‚¹ãƒ¯ãƒ¼ãƒ‰ã�®ãƒªã‚»ãƒƒãƒˆã�«ã�¤ã�„ã�¦";

	/* Default E-mail Sender: r_flores@spl-net.com */
	public static final String DEFAULT_SENDER = "t_cambal@spl-net.com";
	
	public static final String DEFAULT_PW = "spl1037";

	/* Default SMTP */
	public static final String DEFAULT_SMTP = "mail.biglobe.ne.jp";

	/* Request Reset Password Message Line 01 */
	public static final String REQUEST_RESET_PASSWORD_MSG01 = "æ§˜<br/><br/>";

	/* Request Reset Password Message Line 02 */
	public static final String REQUEST_RESET_PASSWORD_MSG02 = " ã�Šä¸–è©±ã�«ã�ªã�£ã�¦ã�Šã‚Šã�¾ã�™ã€‚<br/><br/>";

	/* Request Reset Password Message Line 03 */
	public static final String REQUEST_RESET_PASSWORD_MSG03 = " ãƒ¯ãƒ¼ã‚¯ãƒ•ãƒ­ãƒ¼ã‚¬ã‚¤ãƒ‰ã‚·ã‚¹ãƒ†ãƒ ã�®ãƒ‘ã‚¹ãƒ¯ãƒ¼ãƒ‰ã‚’ãƒªã‚»ãƒƒãƒˆã�™ã‚‹ç‚ºã�«ã€�<br/>";

	/* Request Reset Password Message Line 04 */
	public static final String REQUEST_RESET_PASSWORD_MSG04 = " ä¸‹è¨˜ã�®ãƒªãƒ³ã‚¯ã‚’å�‚ç…§ã�—ã�¦ä¸‹ã�•ã�„ã€‚<br/><br/>";

	/* Request Reset Password Message Line 05 */
	public static final String REQUEST_RESET_PASSWORD_MSG05 = " https://localhost:8443/workflowsystem/reset-password.do?rpk=";

	/* Request Reset Password Message Line 06 */
	public static final String REQUEST_RESET_PASSWORD_MSG06 = "<br/><br/> ä»¥ä¸Šã€�å®œã�—ã��ã�Šé¡˜ã�„ã�„ã�Ÿã�—ã�¾ã�™ã€‚";

	/* Success Page Type 1 = Request Reset Password */
	public static final String REQUEST_TYPE_01 = "1";

	/* Success Page Type 2 = Reset Password */
	public static final String REQUEST_TYPE_02 = "2";

	/** Tables */
	/* User Table */
	public static final String MST_USER = "mst_user";

	/* Company Table */
	public static final String MST_COMPANY = "mst_company";
	
	/* Phase Table */
	public static final String MST_PHASE = "mst_phase";
	
	/* Module Table */
	public static final String MST_MODULE = "mst_module";
	
	/* Program Table */
	public static final String MST_PROGRAM = "mst_program";
	
	/* Access Type Table */
	public static final String MST_ACCESSTYPE = "mst_accesstype";
	
	/* Project Table */
	public static final String WFG_PROJECT = "wfg_project";
	
	/* Workflow Table */
	public static final String WFG_WORKFLOW = "wfg_workflow";
	
	/* Project Member Access Value 'Full Access' */
	public static final String ACCESS_RIGHT_FULLACCESS = "ãƒ•ãƒ«ã‚¢ã‚¯ã‚»ã‚¹";

	/* Project Member Access Value 'Read Only' */
	public static final String ACCESS_RIGHT_READONLY = "èª­å‡ºå°‚ç”¨";

	/* Project Member Access Value 'Write only' */
	public static final String ACCESS_RIGHT_WRITEONLY = "æ›¸è¾¼ã�¿å°‚ç”¨";
	
	/* Project Member Access Value 'No Access' */
	public static final String ACCESS_RIGHT_NOACCESS = "å…¥æ‰‹å‡ºæ�¥ã�ªã�„";
	
	/* Project Member Access Code '1' */
	public static final String ACCESS_RIGHT_F = "F";

	/* Project Member Access Code '2' */
	public static final String ACCESS_RIGHT_R = "R";

	/* Project Member Access Code '3' */
	public static final String ACCESS_RIGHT_W = "W";
	
	/* Project Member Access Code '3' */
	public static final String ACCESS_RIGHT_N = "N";
	
	/* Task Priority  Status Code '1' */
	public static final String TSK_PRIORITY_CODE_1_HIGH = "1";

	/* Task Priority  Status Code '2' */
	public static final String TSK_PRIORITY_CODE_2_MEDIUM = "2";

	/* Task Priority  Status Code '3' */
	public static final String TSK_PRIORITY_CODE_3_LOW = "3";
	
	/* Task Priority Status Value 'Draft' */
	public static final String TSK_PRIORITY_VALUE_HIGH = "é«˜";

	/* Task Priority  Status Value 'Planned' */
	public static final String TSK_PRIORITY_VALUE_MEDIUM = "ä¸­";

	/* Task Priority  Status Value 'In Progress' */
	public static final String TSK_PRIORITY_VALUE_LOW = "ä½Ž";
	
	/* Task Status Code '1' */
	public static final String TSK_STATUS_CODE_1_DRAFT = "1";

	/* Task Status Code '2' */
	public static final String TSK_STATUS_CODE_2_NEW= "2";
	
	/* Task Status Code '3' */
	public static final String TSK_STATUS_CODE_3_INPROGRESS= "3";
	
	/* Task Status Code '4' */
	public static final String TSK_STATUS_CODE_4_ONHOLD = "4";
	
	/* Task Status Code '5' */
	public static final String TSK_STATUS_CODE_5_STOP = "5";

	/* Task Status Code '6' */
	public static final String TSK_STATUS_CODE_6_COMPLETED = "6";
	
	/* Task Status Value 'Draft' */
	public static final String TSK_STATUS_VALUE_DRAFT = "æ��æ¡ˆ";
	
	/* Task Status Value 'New' */
	public static final String TSK_STATUS_VALUE_NEW = "æ–°è¦�";
	
	/* Task Status Value 'In Progress' */
	public static final String TSK_STATUS_VALUE_INPROGRESS = "é€²è¡Œä¸­";
	
	/* Task Status Value 'On-hold' */
	public static final String TSK_STATUS_VALUE_ONHOLD = "ä¿�ç•™";
	
	/* Task Status Value 'Stop' */
	public static final String TSK_STATUS_VALUE_STOP = "ä¸­æ­¢";

	/* Task Status Value 'Completed' */
	public static final String TSK_STATUS_VALUE_COMPLETED = "å®Œäº†";
	
	/* Request Status Code '1' */
	public static final String RQST_STATUS_CODE_1_NEW = "1";

	/* Request Status Code '2' */
	public static final String RQST_STATUS_CODE_2_APPROVED= "2";
	
	/* Request Status Code '3' */
	public static final String RQST_STATUS_CODE_3_DISAPPROVED= "3";
	
	/* Request Status Code '4' */
	public static final String RQST_STATUS_CODE_4_INPROGRESS= "4";
	
	/* Request Status Value 'New' */
	public static final String RQST_STATUS_VALUE_NEW = "æ–°è¦�";
	
	/* Request Status Value 'Approved' */
	public static final String RQST_STATUS_VALUE_APPROVED = "æ‰¿èª�";
	
	/* Request Status Value 'Disapproved' */
	public static final String RQST_STATUS_VALUE_DISAPPROVED = "ä¸�æ‰¿èª�";
	
	/* Request Status Value 'In Progress' */
	public static final String RQST_STATUS_VALUE_INPROGRESS = "é€²æ�—ä¸­";
	
	/* Request Priority  Status Code '1' */
	public static final String RQST_PRIORITY_CODE_1_HIGH = "1";

	/* Request Priority  Status Code '2' */
	public static final String RQST_PRIORITY_CODE_2_MEDIUM = "2";

	/* Request Priority  Status Code '3' */
	public static final String RQST_PRIORITY_CODE_3_LOW = "3";
	
	/* Request Priority Status Value 'Draft' */
	public static final String RQST_PRIORITY_VALUE_HIGH = "é«˜";

	/* Request Priority  Status Value 'Planned' */
	public static final String RQST_PRIORITY_VALUE_MEDIUM = "ä¸­";

	/* Request Priority  Status Value 'In Progress' */
	public static final String RQST_PRIORITY_VALUE_LOW = "ä½Ž";
	
	/* Request Active Flag  Status Value 'Not Active' */
	public static final String RQST_ACTIVE_FLAG_NO = "N";
	
	/* Request Active Flag  Status Value 'Active' */
	public static final String RQST_ACTIVE_FLAG_YES = "Y";
	
	/* Request Complete Flag  Status Value 'Complete' */
	public static final String RQST_COMPLETE_FLAG_YES = "Y";
	
	/* Request Complete Flag  Status Value 'Not Complete' */
	public static final String RQST_COMPLETE_FLAG_NO = "N";
	
	/* Dashboard Id Task Daily Value '1' */
	public static final String DSBRD_ID_DAILY = "1";
	
	/* Dashboard Id Task Weekly Value '2' */
	public static final String DSBRD_ID_WEEKLY = "2";
	
	/* Request Id For Request Value '1' */
	public static final String DSBRD_ID_REQUEST = "1";

	/* Request Id For Approval Value '2' */
	public static final String DSBRD_ID_FOR_APPROVAL = "2";
	
	/* Edit Button ID is active Value '1' */
	public static final String DSBRD_EDIT_BTN_ACTIVE = "1";
	
	/* Edit Button ID is not active Value '2' */
	public static final String DSBRD_EDIT_BTN_INACTIVE = "2";
	
	/* Buttons are Active Value '1' */
	public static final String DSBRD_BTN_ACTIVE = "1";
	
	/* Buttons are not Active Value '2' */
	public static final String DSBRD_BTN_INACTIVE = "2";
	
}
