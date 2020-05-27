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

public class ProgramDAO extends DataAccessObject {
	/**
	 * Reads resulting Company entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return company 		Company data
	 * @throws SQLException SQL Error
	 */
	private Program read(ResultSet rs) throws SQLException {
		Long programId = new Long(rs.getLong("PROGRAMID"));
		String programName = rs.getString("PROGRAMNAME");
		String moduleName = rs.getString("MODULENAME");
		String javaServletReference = rs.getString("JAVASERVLETREFERENCE");
		String urlPattern = rs.getString("URLPATTERN");
		Boolean withinTheMenu = WfUtilCommon.setStrToBool(rs.getString("WITHINTHEMENU"));

		Program program = new Program();
		program.setProgramId(programId);
		program.setProgramName(programName);
		program.setModuleName(moduleName);
		program.setJavaServletReference(javaServletReference);
		program.setUrlPattern(urlPattern);
		program.setWithinTheMenu(withinTheMenu);

		return program;
	}

	/**
	 * Finds all Program entries from database
	 * 
	 * @return programs List of saved Program
	 */
	public List<Program> findAll() {
		LinkedList<Program> programs = new LinkedList<Program>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PROGRAM_001);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Program program = read(rs);
				programs.add(program);
			}
			return programs;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}

	/**
	 * Finds a specific Program by ID entry from database
	 * 
	 * @param id 		ID of the Program to search
	 * @return program 	Equivalent Program entry of the specified ID
	 */
	public Program find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PROGRAM_002);

			/* Set WHERE parameters */
			statement.setLong(1, id.longValue()); // Program Id

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
	 * Finds a specific Program by its corresponding Name entry from database
	 * 
	 * @param programName 	Unique Name of the Program to search
	 * @return program 		Equivalent Program entry of the specified Name
	 */
	public Program findByProgramName(String programName) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PROGRAM_003);

			/* Set WHERE parameters */
			statement.setString(1, programName); // Program Name

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
	 * Finds a Programs from database that are not yet selected
	 * 
	 * @param accessTypeId 		Access Type ID
	 * @return programs 		Equivalent Program entry of the specified ID
	 */
	public List<Program> findAllNotYetSelected(Long accessTypeId) {
		LinkedList<Program> programs = new LinkedList<Program>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PROGRAM_005);

			/* Set WHERE parameters */
			statement.setLong(1, accessTypeId.longValue()); // Access Type ID
			
			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Program program = read(rs);
				programs.add(program);
			}
			return programs;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}
	
	/**
	 * Finds a Programs from database that are not yet selected for Edit mode
	 * 
	 * @param accessTypeId 		Access Type ID
	 * @param programName		Program Name
	 * @return programs 		Equivalent Program entry of the specified ID
	 */
	public List<Program> findAllNotYetSelectedForEdit(Long accessTypeId, String programName) {
		LinkedList<Program> programs = new LinkedList<Program>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PROGRAM_005 + 
													WfSqlCommon.SELECTSQL_PROGRAM_005_ADD01);

			/* Set WHERE parameters */
			statement.setLong(1, accessTypeId.longValue()); // Access Type ID
			statement.setString(2, programName);
			
			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Program program = read(rs);
				programs.add(program);
			}
			return programs;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}
	
	/**
	 * Updates specified Program entry in database
	 * 
	 * @param program 	Program object to update
	 */
	public void update(Program program) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			Module module = new ModuleDAO().findByModuleName(program.getModuleName());
			
			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_PROGRAM_001);

			/* Set Parameters */
			statement.setString(1, program.getProgramName()); 			// Program Name
			statement.setLong(2, module.getModuleId().longValue()); 	// Module ID
			statement.setString(3, program.getJavaServletReference());	// Java Servlet Reference
			statement.setString(4, program.getUrlPattern());			// URL Pattern
			statement.setString(5, 
					WfUtilCommon.setBoolToStr(
							program.getWithinTheMenu()));				// Within the Menu
			statement.setLong(6, program.getProgramId().longValue()); 	// Program ID

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
	 * Creates a new Program entry in database
	 * 
	 * @param program 	Program object to create
	 */
	public void create(Program program) {
		/* Initialize Variables */
		Long id = getUniqueId();
		program.setProgramId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			Module module = new ModuleDAO().findByModuleName(program.getModuleName());
			
			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_PROGRAM_001);

			/* Set Parameters */
			statement.setLong(1, id.longValue()); 						// Program ID
			statement.setString(2, program.getProgramName()); 			// Program Name
			statement.setLong(3, module.getModuleId().longValue()); 	// Module ID
			statement.setString(4, program.getJavaServletReference());	// Java Servlet Reference
			statement.setString(5, program.getUrlPattern());			// URL Pattern
			statement.setString(6, 
					WfUtilCommon.setBoolToStr(
							program.getWithinTheMenu()));				// Within the Menu

			/* Execute SQL Statement */
			statement.executeUpdate();
			
			/* Create relation for User and Company */
			WfUtilCommon.createRelation(id, 							// Program ID
										WfConstCommon.MST_PROGRAM,		// Program Table Name
										module.getModuleId(), 			// Module ID
										WfConstCommon.MST_MODULE);		// Module Table Name

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Deletes a new Program entry in database
	 * 
	 * @param program 		Program object to delete
	 * @return isDeleted	Is record deleted
	 */
	public Boolean delete(Program program) {

		/* Initialize Variables, establish SQL Connection */
		PreparedStatement statement = null;
		Connection connection = getConnection();

		if(WfUtilCommon.isExistInRelation(program.getProgramId())) {
			return false;
		} else {
			try {
				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.DELETESQL_PROGRAM_001);

				/* Set Parameters */
				Long id = program.getProgramId();
				statement.setLong(1, id.longValue()); // Program ID

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
	 * Verify if Program is existing
	 * 
	 * @param programName 	Program Name
	 * @return isExisting	Is Program existing or not
	 */
	public static Boolean isProgramExisting(String programName) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_PROGRAM_003, // SQL
														 programName); 				  	 	// Program Name
		
		return isExisting;
	}
	
	/**
	 * Verify if Java Servlet Reference is existing
	 * 
	 * @param javaServletReference 	Java Servlet Reference
	 * @return isExisting			Is Java Servlet Reference existing or not
	 */
	public static Boolean isJavaServletReferenceExisting(String javaServletReference) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_PROGRAM_004, // SQL
														 javaServletReference); 			// Java Servlet Reference
		
		return isExisting;
	}
}
