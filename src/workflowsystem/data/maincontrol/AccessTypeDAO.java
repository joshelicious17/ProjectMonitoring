package workflowsystem.data.maincontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import workflowsystem.data.DataAccessObject;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;
 
public class AccessTypeDAO extends DataAccessObject {
	/**
	 * Reads resulting Access Type entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return company 		Company data
	 * @throws SQLException SQL Error
	 */
	private AccessType read(ResultSet rs) throws SQLException {
		Long accessTypeId = new Long(rs.getLong("ACCESSTYPEID"));
		String accessTypeCode = rs.getString("ACCESSTYPECODE");
		String accessTypeName = rs.getString("ACCESSTYPENAME");

		AccessType accessType = new AccessType();
		accessType.setAccessTypeId(accessTypeId);
		accessType.setAccessTypeCode(accessTypeCode);
		accessType.setAccessTypeName(accessTypeName);

		return accessType;
	}

	/**
	 * Finds all Access Type entries from database
	 * 
	 * @return accessTypes List of saved Access Type
	 */
	public List<AccessType> findAll() {
		LinkedList<AccessType> accessTypes = new LinkedList<AccessType>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPE_001);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				AccessType accessType = read(rs);
				accessTypes.add(accessType);
			}
			return accessTypes;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}

	/**
	 * Finds a specific AccessType by ID entry from database
	 * 
	 * @param id 			ID of the Access Type to search
	 * @return accessType 	Equivalent Access Type entry of the specified ID
	 */
	public AccessType find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPE_002);

			/* Set WHERE parameters */
			statement.setLong(1, id.longValue()); // Access Type Id

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
	 * Finds a specific Access Type by its corresponding Code entry from database
	 * 
	 * @param accessTypeCode 	Unique Code of the Access Type to search
	 * @return accessType 		Equivalent Access Type entry of the specified Code
	 */
	public AccessType findByAccessTypeCode(String accessTypeCode) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_ACCESSTYPE_003);

			/* Set WHERE parameters */
			statement.setString(1, accessTypeCode); // AccessType Code

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
	 * Updates specified Access Type entry in database
	 * 
	 * @param accessType 	Access Type object to update
	 */
	public void update(AccessType accessType) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_ACCESSTYPE_001);

			/* Set Parameters */
			statement.setString(1, accessType.getAccessTypeCode()); 			// Access Type Code
			statement.setString(2, accessType.getAccessTypeName()); 			// Access Type Name
			statement.setLong(3, accessType.getAccessTypeId().longValue()); 	// Access Type ID

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
	 * Creates a new Access Type entry in database
	 * 
	 * @param accessType 	Access Type object to create
	 */
	public void create(AccessType accessType) {
		/* Initialize Variables */
		Long id = getUniqueId();
		accessType.setAccessTypeId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_ACCESSTYPE_001);

			/* Set Parameters */
			statement.setLong(1, id.longValue()); 						// Access Type ID
			statement.setString(2, accessType.getAccessTypeCode()); 	// Access Type Code
			statement.setString(3, accessType.getAccessTypeName()); 	// Access Type Name

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
	 * Deletes a new Access Type entry in database
	 * 
	 * @param accessType 		Access Type object to delete
	 * @return isDeleted		Is record deleted
	 */
	public Boolean delete(AccessType accessType) {

		/* Initialize Variables, establish SQL Connection */
		PreparedStatement statement = null;
		Connection connection = getConnection();

		if(WfUtilCommon.isExistInRelation(accessType.getAccessTypeId())) {
			return false;
		} else {
			try {
				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.DELETESQL_ACCESSTYPE_001);

				/* Set Parameters */
				Long id = accessType.getAccessTypeId();
				statement.setLong(1, id.longValue()); // Access Type ID

				/* Execute SQL Statement */
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
	 * Verify if Access Type is existing
	 * 
	 * @param accessTypeCode 	Access Type Code
	 * @return isExisting		Is Access Type existing or not
	 */
	public static Boolean isAccessTypeExisting(String accessTypeCode) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_ACCESSTYPE_003, 	// SQL
														 accessTypeCode); 				  	 	// Access Type Code
		
		return isExisting;
	}
}
