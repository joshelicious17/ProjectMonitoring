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

public class CompanyDAO extends DataAccessObject {
	/**
	 * Reads resulting Company entries from database
	 * 
	 * @param rs 			ResultSet
	 * @return company 		Company data
	 * @throws SQLException SQL Error
	 */
	private Company read(ResultSet rs) throws SQLException {
		Long companyId = new Long(rs.getLong("COMPANYID"));
		String companyCode = rs.getString("COMPANYCODE");
		String companyName = rs.getString("COMPANYNAME");

		Company company = new Company();
		company.setCompanyId(companyId);
		company.setCompanyCode(companyCode);
		company.setCompanyName(companyName);

		return company;
	}

	/**
	 * Finds all Company entries from database
	 * 
	 * @return companies List of saved Company
	 */
	public List<Company> findAll() {
		LinkedList<Company> companies = new LinkedList<Company>();
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_COMPANY_001);

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			while (rs.next()) {
				Company company = read(rs);
				companies.add(company);
			}
			return companies;

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			close(rs, statement, connection);
		}
	}

	/**
	 * Finds a specific Company by ID entry from database
	 * 
	 * @param id 		ID of the Company to search
	 * @return company 	Equivalent Company entry of the specified ID
	 */
	public Company find(Long id) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_COMPANY_002);

			/* Set WHERE parameters */
			statement.setLong(1, id.longValue()); // Company Id

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
	 * Finds a specific Company by its corresponding Code entry from database
	 * 
	 * @param companyCode 	Unique Code of the Company to search
	 * @return company 		Equivalent Company entry of the specified Code
	 */
	public Company findByCompanyCode(String companyCode) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.SELECTSQL_COMPANY_003);

			/* Set WHERE parameters */
			statement.setString(1, companyCode); // Company Code

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
	 * Updates specified Company entry in database
	 * 
	 * @param company 	Company object to update
	 */
	public void update(Company company) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_COMPANY_001);

			/* Set Parameters */
			statement.setString(1, company.getCompanyCode()); 			// Company Code
			statement.setString(2, company.getCompanyName()); 			// Company Name
			statement.setLong(3, company.getCompanyId().longValue()); 	// Company ID

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
	 * Creates a new Company entry in database
	 * 
	 * @param company 	Company object to create
	 */
	public void create(Company company) {
		/* Initialize Variables */
		Long id = getUniqueId();
		company.setCompanyId(id);
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.INSERTSQL_COMPANY_001);

			/* Set Parameters */
			statement.setLong(1, id.longValue()); 				// Company ID
			statement.setString(2, company.getCompanyCode()); 	// Company Code
			statement.setString(3, company.getCompanyName()); 	// Company Name

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
	 * Deletes a new Company entry in database
	 * 
	 * @param company 		Company object to delete
	 * @return isDeleted	Is record deleted
	 */
	public Boolean delete(Company company) {

		/* Initialize Variables, establish SQL Connection */
		PreparedStatement statement = null;
		Connection connection = getConnection();

		if(WfUtilCommon.isExistInRelation(company.getCompanyId())) {
			return false;
		} else {
			try {
				/* Prepare SQL Statement */
				statement = connection.prepareStatement(WfSqlCommon.DELETESQL_COMPANY_001);

				/* Set Parameters */
				Long id = company.getCompanyId();
				statement.setLong(1, id.longValue()); // Company ID

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
	 * Verify if Company is existing
	 * 
	 * @param companyCode 	Company Code
	 * @return isExisting	Is Company existing or not
	 */
	public static Boolean isCompanyExisting(String companyCode) {
		
		Boolean isExisting = WfUtilCommon.verifyExisting(WfSqlCommon.SELECTSQL_COMPANY_003, // SQL
														 companyCode); 				  	 	// Company Code
		
		return isExisting;
	}
}
