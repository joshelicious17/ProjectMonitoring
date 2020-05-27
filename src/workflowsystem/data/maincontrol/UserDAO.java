package workflowsystem.data.maincontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;

public class UserDAO extends DataAccessObject {
	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	/**
	 * Reads resulting User entries from database
	 * 
	 * @param rs ResultSet
	 * @return user User data
	 * @throws SQLException SQL Error
	 */
	private User read(ResultSet rs) throws SQLException {
		Long userId = new Long(rs.getLong("USERID"));
		String userName = rs.getString("USERNAME");
		String userPassword = rs.getString("USERPASSWORD");
		String userFullname = rs.getString("USERFULLNAME");
		String companyCode = rs.getString("COMPANYCODE");
		String userEmail = rs.getString("USEREMAIL");
		String accessTypeCode = rs.getString("ACCESSTYPECODE");
		String validFrom = rs.getString("VALIDFROM");
		String validTo = rs.getString("VALIDTO");

		User user = new User();
		user.setUserId(userId);					// User ID
		user.setUserName(userName);				// User Name
		user.setUserPassword(userPassword);		// User Password
		user.setUserFullname(userFullname);		// User Full Name
		user.setCompanyCode(companyCode);		// Company Code
		user.setUserEmail(userEmail);			// User Email
		user.setAccessTypeCode(accessTypeCode);	// Access Type Code
		user.setValidFrom(validFrom);			// Valid From
		user.setValidTo(validTo);				// Valid To

		return user;
	}

	/**
	 * Finds a specific User by User ID entry from database
	 * 
	 * @param id 		User ID of the User to search
	 * @return user 	Equivalent User entry of the specified User ID
	 */
	public User find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_001);

			/* Set Parameter */
			statement.setLong(1, id.longValue()); // User ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}
			return read(rs);

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds a specific User by User Name entry from database
	 * 
	 * @param username 	User Name of the User to search
	 * @return user 	Equivalent User entry of the specified User Name
	 */
	public User findByUsername(String username) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_002);

			/* Set Parameters */
			statement.setString(1, username);	// User Name

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}
			return read(rs);

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds a specific User by User E-mail entry from database
	 * 
	 * @param userEmail 	User E-mail of the User to search
	 * @return user 		Equivalent User entry of the specified User E-mail
	 */
	public User findByUserEmail(String userEmail) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_004);

			/* Set Parameters */
			statement.setString(1, userEmail);	// User E-mail

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (!rs.next()) {
				return null;
			}
			return read(rs);

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all User entries from database
	 * 
	 * @return users 	List of saved User
	 */
	public List<User> findAll() {
		LinkedList<User> users = new LinkedList<User>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_003);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				User user = read(rs);
				users.add(user);
			}
			return users;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Finds all User entries from database
	 * 
	 * @return users 	List of saved User
	 */
	public List<User> findByAccessType() {
		LinkedList<User> users = new LinkedList<User>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_007);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				User user = read(rs);
				users.add(user);
			}
			return users;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Updates specified User entry in database
	 * 
	 * @param user 	User object to update
	 */
	public void update(User user) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			Company company = new CompanyDAO().findByCompanyCode(user.getCompanyCode());
			AccessType accessType = new AccessTypeDAO().findByAccessTypeCode(user.getAccessTypeCode());
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_USER_001);

			/* Set Parameters */
			statement.setString(1, user.getUserPassword());					// User Password
			statement.setString(2, user.getUserFullname());					// User Full Name
			statement.setLong(3, company.getCompanyId().longValue());		// Company ID
			statement.setString(4, user.getUserEmail());					// User E-mail
			statement.setString(5, user.getUserName());						// User Name
			statement.setLong(6, accessType.getAccessTypeId().longValue());	// Access Type ID
			statement.setString(7, user.getValidFrom());					// Valid From
			statement.setString(8, user.getValidTo());						// Valid To
			statement.setLong(9, user.getUserId().longValue());				// User ID

			/* Execute SQL Statement */
			statement.executeUpdate();
			
			/* Update relation for User and Company */
			WfUtilCommon.updateRelation(company.getCompanyId().longValue(), // Company ID
										user.getUserId().longValue(),		// User ID
										WfConstCommon.MST_COMPANY);			// Company Table
			
			/* Update relation for User and Access Type */
			WfUtilCommon.updateRelation(accessType.getAccessTypeId().longValue(), 	// Access Type ID
										user.getUserId().longValue(),				// User ID
										WfConstCommon.MST_ACCESSTYPE);				// Access Type Table

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Updates specified User Reset Password Key in database
	 * 
	 * @param userId 			User ID
	 * @param resetPasswordKey	Reset Password Key
	 */
	public void updateResetPasswordKey(Long userId, String resetPasswordKey) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_USER_002);

			/* Set Parameters */
			statement.setString(1, resetPasswordKey);	// Reset Password Key		
			statement.setLong(2, userId);				// User ID

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Updates specified User Password in database
	 * 
	 * @param userPassword		User Password
	 * @param resetPasswordKey	Reset Password Key
	 */
	public void updateUserPassword(String userPassword, String resetPasswordKey) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_USER_003);

			/* Set Parameters */
			statement.setString(1, userPassword);				// User Password
			statement.setString(2, WfConstCommon.CHAR_SPACE_1);	// Reset Password Key
			statement.setString(3, resetPasswordKey);			// Reset Password Key

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Creates a new User entry in database
	 * 
	 * @param user 	User object to create
	 */
	public void create(User user) {
		Long id = getUniqueId();
		user.setUserId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			Company company = new CompanyDAO().findByCompanyCode(user.getCompanyCode());
			AccessType accessType = new AccessTypeDAO().findByAccessTypeCode(user.getAccessTypeCode());
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_USER_001);

			/* Set Parameters */
			statement.setLong(1, id.longValue());							// User ID
			statement.setString(2, user.getUserName());						// User Name
			statement.setString(3, user.getUserPassword());					// User Password
			statement.setString(4, user.getUserFullname());					// User Full Name
			statement.setString(5, user.getUserEmail());					// User E-mail
			statement.setLong(6, company.getCompanyId().longValue());		// Company ID
			statement.setLong(7, accessType.getAccessTypeId().longValue());	// Access Type ID
			statement.setString(8, user.getValidFrom());					// Valid From
			statement.setString(9, user.getValidTo());						// Valid To

			/* Execute SQL Statement */
			statement.executeUpdate();

			/* Create relation for User and Company */
			WfUtilCommon.createRelation(id, 								// User ID
										WfConstCommon.MST_USER,				// User Table Name
										company.getCompanyId().longValue(), // Company ID
										WfConstCommon.MST_COMPANY);			// Company Table Name
			
			/* Create relation for User and Access Type */
			WfUtilCommon.createRelation(id, 										// User ID
										WfConstCommon.MST_USER,						// User Table Name
										accessType.getAccessTypeId().longValue(), 	// Access Type ID
										WfConstCommon.MST_ACCESSTYPE);				// Access Type Table Name

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Deletes a new User entry in database
	 * 
	 * @param user 			User object to delete
	 * @return isDeleted	Is record deleted
	 */
	public Boolean delete(User user) {
		PreparedStatement statement = null;
		Connection connection = null;
		
		if(WfUtilCommon.isExistInRelation(user.getUserId())) {
			return false;
		} else {
			try {
				/* Establish SQL Connection */
				connection = getConnection();

				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.DELETESQL_USER_001);

				/* Set Parameters */
				Long id = user.getUserId();
				statement.setLong(1, id.longValue());	// User ID

				/*Execute SQL Statement*/
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new RuntimeException(e);

			} finally {

				/* Close prepared statement and Database connection */
				close(statement, connection);
			}
			return true;
		}
	}

	/**
	 * Verify if if User Name/User E-mail is existing
	 * 
	 * @param strParam			User Name / User E-mail
	 * @param isSearchByEmail	Is Search By User Email	
	 * @return isExisting 		Is User Name existing or not
	 */
	public static Boolean isUserExisting(String strParam, Boolean isSearchByEmail) {

		String sql = "";
		if (isSearchByEmail)
			sql = WfSqlCommon.SELECTSQL_USER_004;
		else
			sql = WfSqlCommon.SELECTSQL_USER_002;

		Boolean isExisting = WfUtilCommon.verifyExisting(sql, 				// SQL
														 strParam);  		// User Name / User E-mail
		
		return isExisting;
	}

	/**
	 * Verify if if Reset Password Key is valid
	 * 
	 * @param strParam		Reset Password Key
	 * @return isValid	 	Is Reset Password Key valid or not
	 */
	public static Boolean isValidResetPasswordKey(String strParam) {
		
		Boolean isValid = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_USER_005,    // SQL
														 strParam); 					 // Reset Password Key
		
		return isValid;
	}
	
	/**
	 * Finds a Users from database that are not yet selected members for Edit mode
	 * 
	 * @param projectId 		Project Id Type ID
	 * @param userName		        User Name
	  * @return user 		Equivalent user entry of the specified ID
	 */
	public List<User> findAllUsersNotYetMembersForEdit(Long projectId, String userName) {
		LinkedList<User> user = new LinkedList<User>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_006 + 
													WfSqlCommon.SELECTSQL_USER_006_ADD01);

			/* Set WHERE parameters */
			statement.setLong(1, projectId.longValue());     // project  ID
			statement.setString(2, userName);                // user name
			
			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				User users = read(rs);
				user.add(users);
			}
			return user;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}
	
	/**
	 * Finds a Users from database that are not yet members
	 * 
	 * @param UserId 		        User ID
	 * @return projectMember 		Equivalent members entry of the specified ID
	 * @return user 		Equivalent user entry of the specified ID
	 */
	public List<User> findAllUsersNotYetMembers(Long projectId) {
		LinkedList<User> user = new LinkedList<User>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();
			
			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_USER_006);

			/* Set WHERE parameters */
			statement.setLong(1, projectId.longValue());          // project ID
			
			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				User projectmembers = read(rs);
				user.add(projectmembers);
			}
			return user;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}
}