package workflowsystem.data.maincontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.data.maincontrol.AccessType;
import workflowsystem.data.maincontrol.AccessTypeDAO;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;

public class AccessTypeDetailDAO extends DataAccessObject {
	/**
	 * Reads resulting Access Type Detail entries from database
	 *  
	 * @param rs 					ResultSet
	 * @return accessTypeDetail		Access Type Detail data
	 * @throws SQLException 		SQL Error
	 */
	private AccessTypeDetail read(ResultSet rs) throws SQLException {
		Long accessTypeDetailId = new Long(rs.getLong("ACCESSTYPEDETAILID"));
		Long accessTypeId = new Long(rs.getLong("ACCESSTYPEID"));
		String programName = rs.getString("PROGRAMNAME");
		String accessRight = this.getAccessRightValue(rs.getString("ACCESSRIGHT"));
		String javaServletReference = rs.getString("JAVASERVLETREFERENCE");
		String moduleName = rs.getString("MODULENAME");
		String urlPattern = rs.getString("URLPATTERN");
		Boolean withinTheMenu = WfUtilCommon.setStrToBool(rs.getString("WITHINTHEMENU"));

		/* Initialize new Access Type Detail object */
		AccessTypeDetail accessTypeDetail = new AccessTypeDetail();

		/* Set values to Access Type Detail Object */
		accessTypeDetail.setAccessTypeDetailId(accessTypeDetailId);		// Access Type Detail ID
		accessTypeDetail.setAccessTypeId(accessTypeId);					// Access Type ID
		accessTypeDetail.setProgramName(programName);					// Program Name
		accessTypeDetail.setAccessRight(accessRight);					// Access Right
		accessTypeDetail.setJavaServletReference(javaServletReference);	// Java Servlet Reference
		accessTypeDetail.setModuleName(moduleName);						// Module Name
		accessTypeDetail.setUrlPattern(urlPattern);						// URL Pattern
		accessTypeDetail.setWithinTheMenu(withinTheMenu);				// Within the Menu

		return accessTypeDetail;
	}

	/**
	 * Obtain access right value
	 * 
	 * @param accessRightCode	Access Right Code
	 * @return accessRightValue	Access Right Value
	 */
	public String getAccessRightValue(String accessRightCode) {
		String accessRightValue = null;
		switch (accessRightCode) {
		/* if Access Right is equal to 'F', set value to 'Full' */
			case WfConstCommon.ACCESS_RIGHT_F:
				accessRightValue = WfConstCommon.ACCESS_RIGHT_FULLACCESS;
				break;
			/* if Access Right is equal to 'W', set value to 'Write' */
			case WfConstCommon.ACCESS_RIGHT_W:
				accessRightValue = WfConstCommon.ACCESS_RIGHT_WRITEONLY;
				break;
			/* if Access Right is equal to 'R', set value to 'Read' */
			case WfConstCommon.ACCESS_RIGHT_R:
				accessRightValue = WfConstCommon.ACCESS_RIGHT_READONLY;
				break;
			/* if Access Right is equal to 'N', set value to 'No Access' */
			case WfConstCommon.ACCESS_RIGHT_N:
				accessRightValue = WfConstCommon.ACCESS_RIGHT_NOACCESS;
				break;
		}
		return accessRightValue;
	}

	/**
	 * Obtain Access Right Code
	 * 
	 * @para accessRightValue	Access Right Value
	 * @return accessRightCode	Access Right Code
	 */
	public String getAccessRightCode(String accessRightValue) {
		String accessRightCode = null;
		switch (accessRightValue) {
			/* if Access Right is equal to 'Full', set value to 'F' */
			case WfConstCommon.ACCESS_RIGHT_FULLACCESS:
				accessRightCode = WfConstCommon.ACCESS_RIGHT_F;
				break;
			/* if Access Right is equal to 'Write', set value to 'W' */
			case WfConstCommon.ACCESS_RIGHT_WRITEONLY:
				accessRightCode = WfConstCommon.ACCESS_RIGHT_W;
				break;
			/* if Access Right is equal to 'Read', set value to 'R' */
			case WfConstCommon.ACCESS_RIGHT_READONLY:
				accessRightCode = WfConstCommon.ACCESS_RIGHT_R;
				break;
			/* if Access Right is equal to 'No Access', set value to 'N' */
			case WfConstCommon.ACCESS_RIGHT_NOACCESS:
				accessRightCode = WfConstCommon.ACCESS_RIGHT_N;
				break;
		}
		return accessRightCode;
	}
	
	/**
	 * Finds all Access Type Detail entries from database
	 * 
	 * @return accessTypeDetails 	List of saved Access Type Details
	 */
	public List<AccessTypeDetail> findAll(Long id) {
		LinkedList<AccessTypeDetail> accessTypeDetails = new LinkedList<AccessTypeDetail>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPEDETAIL_001);

			/* Set Search Parameters */
			statement.setLong(1, id.longValue());	// Access Type ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				AccessTypeDetail accessTypeDetail = read(rs);
				accessTypeDetails.add(accessTypeDetail);
			}
			return accessTypeDetails;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds all Access Type Detail entries by Access Type Detail ID from database
	 * 
	 * @param  id				Access Type Detail ID
	 * @return accessTypeDetail Access Type Detail
	 */
	public AccessTypeDetail find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPEDETAIL_002);

			/* Set Search Parameter */
			statement.setLong(1, id.longValue());	// Access Type Detail ID

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
	 * Finds all Access Type Detail entries by User ID from database
	 * 
	 * @param  userId			User ID
	 * @return accessTypeDetail Access Type Detail
	 */
	public AccessTypeDetail findByUserId(Long userId) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPEDETAIL_004);

			/* Set Search Parameter */
			statement.setLong(1, userId.longValue());	// User ID

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
	 * Finds all Access Type Detail entries per user from database
	 * 
	 * @return userId 	User ID
	 */
	public List<AccessTypeDetail> findAllByUserId(Long userId) {
		LinkedList<AccessTypeDetail> accessTypeDetails = new LinkedList<AccessTypeDetail>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPEDETAIL_004);

			/* Set Search Parameters */
			statement.setLong(1, userId.longValue());	// User ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				AccessTypeDetail accessTypeDetail = read(rs);
				accessTypeDetails.add(accessTypeDetail);
			}
			return accessTypeDetails;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Updates specified Access Type Detail entry in database
	 * 
	 * @param accessTypeDetail	 	Access Type Detail object to update
	 */
	public void update(AccessTypeDetail accessTypeDetail) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_ACCESSTYPEDETAIL_001);

			/* Set Update Parameters */			
			Program program = new ProgramDAO().findByProgramName(accessTypeDetail.getProgramName());
			statement.setLong(1, program.getProgramId().longValue());			    			// Program ID
			
			statement.setString(2, this.getAccessRightCode(accessTypeDetail.getAccessRight()));	// Access Right
			statement.setLong(3, accessTypeDetail.getAccessTypeDetailId());						// Access Type Detail ID

			/* Execute SQL Statement */
			statement.execute();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Creates a new Access Type Detail entry in database
	 * 
	 * @param accessTypeDetail 		Access Type Detail object to create
	 */
	public void create(AccessTypeDetail accessTypeDetail) {
		Long id = getUniqueId();
		accessTypeDetail.setAccessTypeDetailId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_ACCESSTYPEDETAIL_001);

			/* Set Insert Parameters */
			Program program = new ProgramDAO().findByProgramName(accessTypeDetail.getProgramName());
			
			statement.setLong(1, id.longValue());												// Access Type Detail ID
			statement.setLong(2, accessTypeDetail.getAccessTypeId().longValue());				// Access Type ID
			statement.setLong(3, program.getProgramId().longValue());							// Program ID
			statement.setString(4, this.getAccessRightCode(accessTypeDetail.getAccessRight()));	// Access Right

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
	 * Creates a new Access Type Detail entries in database
	 * 
	 * @param accessTypeId 		Access Type ID
	 */
	public void createInitial(Long accessTypeId) {
		
		List<Program> programs = new ProgramDAO().findAll();
		
		for (int x = 0; x < programs.size(); x++)
		{
			PreparedStatement statement = null;
			Connection connection = null;
			
			AccessTypeDetail accessTypeDetail = new AccessTypeDetail();
			Program program = programs.get(x);
			
			Long id = getUniqueId();
			accessTypeDetail.setAccessTypeDetailId(id);
			
			try {
				/* Establish Database Connection */
				connection = getConnection();

				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_TASK_ACCESSTYPEDETAIL_001);
				
				statement.setLong(1, id.longValue());						// Access Type Detail ID
				statement.setLong(2, accessTypeId.longValue());				// Access Type ID
				statement.setLong(3, program.getProgramId().longValue());	// Program ID
				statement.setString(4, WfConstCommon.ACCESS_RIGHT_F);		// Access Right
				
				/* Execute SQL Statement */
				statement.executeUpdate();
			} catch (SQLException e) {

				throw new RuntimeException(e);

			} finally {

				/* Close prepared statement and Database connection */
				close(statement, connection);

			}
		}
			
		
	}

	/**
	 * Deletes a Access Type Detail entry in database
	 * 
	 * @param accessTypeDetail	Access Type Detail object to delete
	 * @return isDeleted		Is record deleted
	 */
	public void delete(AccessTypeDetail accessTypeDetail) {
		PreparedStatement statement = null;
		Connection connection = null;
		
		try {
			/* Establish Database Connection */
			connection = getConnection();

			/* Prepare SQL Connection */
			statement = connection.prepareStatement(WfSqlCommon.DELETESQL_ACCESSTYPEDETAIL_001);

			/* Set Delete Parameters */
			Long id = accessTypeDetail.getAccessTypeDetailId();
			statement.setLong(1, id.longValue());	// Access Type Detail ID

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
	 * Verify if Program is existing
	 * 
	 * @param programName 	Program Name
	 * @param accessTypeId	Access Type ID
	 * @return isExisting	Is Program existing or not
	 */
	public static Boolean isProgramExistByAccessTypeId(String programName, Long accessTypeId) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_ACCESSTYPEDETAIL_003, // SQL
														 programName, 				  	 			 // Program Name
														 accessTypeId);								 // Access Type ID
		
		return isExisting;
	}
}
