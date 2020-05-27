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

public class PhaseDAO extends DataAccessObject {
	/**
	 * Reads resulting Phase entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return phase 		Phase data
	 * @throws SQLException SQL Error
	 */
	private Phase read(ResultSet rs) throws SQLException {
		Long phaseId = new Long(rs.getLong("PHASEID"));
		String phaseCode = rs.getString("PHASECODE");
		String phaseName = rs.getString("PHASENAME");

		Phase phase = new Phase();
		phase.setPhaseId(phaseId);
		phase.setPhaseCode(phaseCode);
		phase.setPhaseName(phaseName);

		return phase;
	}

	/**
	 * Finds all Phase entries from database
	 * 
	 * @return phases List of saved Phase
	 */
	public List<Phase> findAll() {
		LinkedList<Phase> phases = new LinkedList<Phase>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PHASE_001);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Phase phase = read(rs);
				phases.add(phase);
			}
			return phases;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);

		}
	}

	/**
	 * Finds a specific Phase by ID entry from database
	 * 
	 * @param id 		ID of the Phase to search
	 * @return phase 	Equivalent Phase entry of the specified ID
	 */
	public Phase find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PHASE_002);

			/* Set Parameters */
			statement.setLong(1, id.longValue());	// Phase ID

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
	 * Finds a specific Phase by its corresponding Code entry from database
	 * 
	 * @param phaseCode 	Unique Code of the Phase to search
	 * @return phase 		Equivalent Phase entry of the specified Code
	 */
	public Phase findByPhaseCode(String phaseCode) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_PHASE_003);

			/* Set Parameters */
			statement.setString(1, phaseCode);	// Phase Code

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
	 * Updates specified Phase entry in database
	 * 
	 * @param phase 	Phase object to update
	 */
	public void update(Phase phase) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_PHASE_001);

			/* Set Parameters */
			statement.setString(1, phase.getPhaseCode());			// Phase Code
			statement.setString(2, phase.getPhaseName());			// Phase Name
			statement.setLong(3, phase.getPhaseId().longValue());	// Phase ID

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
	 * Creates a new Phase entry in database
	 * 
	 * @param phase 	Phase object to create
	 */
	public void create(Phase phase) {
		Long id = getUniqueId();
		phase.setPhaseId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_PHASE_001);

			/* Set Parameters */
			statement.setLong(1, id.longValue());			// Phase ID
			statement.setString(2, phase.getPhaseCode());	// Phase Code
			statement.setString(3, phase.getPhaseName());	// Phase Name

			/* Execute SQL Parameters */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}

	/**
	 * Deletes a new Phase entry in database
	 * 
	 * @param phase 		Phase object to delete
	 * @return isDeleted 	Is record deleted
	 */
	public Boolean delete(Phase phase) {
		PreparedStatement statement = null;
		Connection connection = null;
		
		if(WfUtilCommon.isExistInRelation(phase.getPhaseId())) {
			return false;
		} else {
			try {
				/* Establish SQL Connection */
				connection = getConnection();

				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.DELETESQL_PHASE_001);

				/* Set Parameters */
				Long id = phase.getPhaseId();
				statement.setLong(1, id.longValue());	// Phase ID

				/* Execute SQL Parameters */
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
	 * Verify if Phase is existing
	 * 
	 * @param phaseCode		Phase Code
	 * @return isExisting 	Is Phase existing or not
	 */
	public static Boolean isPhaseExisting(String phaseCode) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_PHASE_003, // SQL
														 phaseCode); 				  	  // Phase Code
		
		return isExisting;
	}
}
