package workflowsystem.data.maincontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import workflowsystem.data.DataAccessObject;
import workflowsystem.util.WfMessageCommon;
import workflowsystem.util.WfSqlCommon;
import workflowsystem.util.WfUtilCommon;

public class ModuleDAO extends DataAccessObject {
	/**
	 * Reads resulting Company entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return module 		Module data
	 * @throws SQLException SQL Error
	 */
	private Module read(ResultSet rs) throws SQLException {
		Long moduleId = new Long(rs.getLong("MODULEID"));
		String moduleName = rs.getString("MODULENAME");
		Long moduleSequence = new Long(rs.getLong("MODULESEQUENCE"));
		String urlPattern = rs.getString("URLPATTERN");

		Module module = new Module();
		module.setModuleId(moduleId);				// Module ID
		module.setModuleName(moduleName);			// Module Name
		module.setModuleSequence(moduleSequence);	// Module Sequence
		module.setUrlPattern(urlPattern);			// URL Pattern

		return module;
	}

	/**
	 * Finds all Module entries from database
	 * 
	 * @return module List of saved Module
	 */
	public List<Module> findAll() {
		LinkedList<Module> modules = new LinkedList<Module>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_MODULE_001);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Module module = read(rs);
				modules.add(module);
			}
			return modules;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}

	/**
	 * Finds a specific Module by ID entry from database
	 * 
	 * @param id 		ID of the Module to search
	 * @return module 	Equivalent Module entry of the specified ID
	 */
	public Module find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_MODULE_002);

			/* Set WHERE parameters */
			statement.setLong(1, id.longValue()); // Module Id

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
	 * Finds a specific Module by its corresponding Name entry from database
	 * 
	 * @param moduleName 	Unique Name of the Module to search
	 * @return module  		Equivalent Module entry of the specified Name
	 */
	public Module findByModuleName(String moduleName) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_MODULE_003);

			/* Set WHERE parameters */
			statement.setString(1, moduleName); // Module Name

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
	 * Finds a all Module by User ID from database
	 * 
	 * @param userId 	User ID
	 * @return modules 	List of Modules by User ID
	 */
	public List<Module> findAllByUserId(Long userId) {
		LinkedList<Module> modules = new LinkedList<Module>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_MODULE_004);

			/* Set WHERE parameters */
			statement.setLong(1, userId.longValue()); // User ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Module module = read(rs);
				modules.add(module);
			}
			return modules;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}
	
	/**
	 * Updates specified Module entry in database
	 * 
	 * @param module 	Module object to update
	 */
	public void update(Module module) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_MODULE_001);

			/* Set Parameters */
			statement.setString(1, module.getModuleName()); 			// Module Name
			statement.setLong(2, module.getModuleSequence());			// Module Sequence
			statement.setString(3, module.getUrlPattern());				// URL Pattern
			statement.setLong(4, module.getModuleId().longValue()); 	// Module ID

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
	 * Creates a new Module entry in database
	 * 
	 * @param module 	Module object to create
	 */
	public void create(Module module) {
		/* Initialize Variables */
		Long id = getUniqueId();
		module.setModuleId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_MODULE_001);

			/* Set Parameters */
			statement.setLong(1, id.longValue()); 							// Module ID
			statement.setString(2, module.getModuleName()); 				// Module Name
			statement.setLong(3, module.getModuleSequence().longValue());	// Module Sequence
			statement.setString(4, module.getUrlPattern());					// URL Pattern

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
	 * Deletes a new Module entry in database
	 * 
	 * @param module 		Module object to delete
	 * @return errors		error information
	 */
	public Map<String, String> delete(HttpServletRequest req, Module module) {

		/* Initialize Variables, establish SQL Connection */
		HashMap<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		PreparedStatement statement = null;
		Connection connection = getConnection();

		if(WfUtilCommon.isExistInRelation(module.getModuleId())) {
			errors.put("relation", WfMessageCommon.EARE01);
		} else {
			try {
				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.DELETESQL_MODULE_001);

				/* Set Parameters */
				Long id = module.getModuleId();
				statement.setLong(1, id.longValue()); // Module ID

				/* Execute SQL Statement */
				statement.executeUpdate();

			} catch (SQLException e) {

				throw new RuntimeException(e);

			} finally {

				/* Close prepared statement and Database connection */
				close(statement, connection);
			}
		}
		return errors;
		
	}
	
	/**
	 * Verify if Module is existing
	 * 
	 * @param moduleName 	Module Name
	 * @return isExisting	Is Module existing or not
	 */
	public static Boolean isModuleExisting(String moduleName) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_MODULE_003,  // SQL
														 moduleName); 				  	 	// Module Name
		
		return isExisting;
	}
}
