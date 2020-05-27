package workflowsystem.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import workflowsystem.data.DataAccessObject;
import workflowsystem.data.maincontrol.AccessType;
import workflowsystem.data.maincontrol.AccessTypeDAO;
import workflowsystem.data.maincontrol.AccessTypeDetail;
import workflowsystem.data.maincontrol.Company;
import workflowsystem.data.maincontrol.CompanyDAO;

public class WfUtilCommon extends DataAccessObject {

	public static Boolean verifyExisting(String sql, String code) {
				
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection conn = null;

		try {

			conn = getConnection();
			
			/* Prepare SQL Statement */
			statement = conn.prepareStatement(sql);

			/* Set Parameter */
			statement.setString(1, code); // Code

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			DataAccessObject.close(rs, statement, conn);

		}
	}

	public static Boolean verifyExisting(String sql, String code, Long subId) {
		
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection conn = null;

		try {

			conn = getConnection();
			
			/* Prepare SQL Statement */
			statement = conn.prepareStatement(sql);

			/* Set Parameter */
			statement.setString(1, code); // Code
			statement.setLong(2, subId);  // Sub ID	

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			DataAccessObject.close(rs, statement, conn);

		}
	}
	
	public static Boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static Boolean isDouble(String input) {
    	try {
    		Double.parseDouble(input);
    		return true;
    	} catch (NumberFormatException e) {
    		return false;
    	}
    }

	public static boolean isValidEmailAddress(String email) {
		try {
			new InternetAddress(email).validate();
		} catch (AddressException ex) {
			return false;
		}
		return true;
	}

	public static String generateRandomKey() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public static void createRelation(Long parentId, String parentTable,
									  Long childId, String childTable) {
		PreparedStatement statement = null;
		Connection conn = null;

		try {

			conn = getConnection();
			
			/* Prepare SQL Statement */
			statement = conn.prepareStatement(WfSqlCommon.INSERTSQL_RELATION_001);

			/* Set Parameter */
			statement.setLong(1, parentId.longValue()); 	// Parent ID
			statement.setString(2, parentTable);			// Parent Table
			statement.setLong(3, childId.longValue());		// Child ID
			statement.setString(4, childTable);				// Child Table

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			DataAccessObject.close(statement, conn);

		}
	}

	public static void updateRelation(Long childId, Long parentId, String tableName) {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			/* Establish SQL Connection */
			connection = getConnection();

			/* Prepare SQL Statement */
			statement = connection.prepareStatement(WfSqlCommon.UPDATESQL_RELATION_001);

			/* Set Parameters */
			statement.setLong(1, childId.longValue());					// Child ID
			statement.setLong(2, parentId.longValue());					// Parent ID
			statement.setString(3, tableName);							// Table Name

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, connection);

		}
	}
	
	public static void deleteRelation(Long parentId)
	{
		PreparedStatement statement = null;
		Connection conn = null;

		try {
			
			conn = getConnection();
			
			/* Prepare SQL Statement */
			statement = conn.prepareStatement(WfSqlCommon.DELETESQL_RELATION_001);

			/* Set Parameters */
			statement.setLong(1, parentId); // Parent ID

			/* Execute SQL Statement */
			statement.executeUpdate();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close prepared statement and Database connection */
			close(statement, conn);
		}
	}
	
	public static Boolean isExistInRelation(Long childId) {
		
		ResultSet rs = null;
		PreparedStatement statement = null;
		Connection conn = null;

		try {
			
			conn = getConnection();
			
			/* Prepare SQL Statement */
			statement = conn.prepareStatement(WfSqlCommon.SELECTSQL_RELATION_001);

			/* Set Parameter */
			statement.setLong(1, childId.longValue()); // Child ID

			/* Execute SQL Statement */
			rs = statement.executeQuery();

			if (rs.next()) {
				/* Fetch result count */
				int resultCount = rs.getInt("COUNT");

				if (resultCount >= 1) {
					return true;
				} else {
					return false;
				}
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {

			throw new RuntimeException(e);

		} finally {

			/* Close result set, prepared statement and Database connection */
			DataAccessObject.close(rs, statement, conn);

		}
	}
	
	public static String setBoolToStr(Boolean value) {
		
		if (value)
			return "1";
		else
			return "0";		
	}
	
	public static Boolean setStrToBool(String value) {
		
		if (value.equals("1"))
			return true;
		else
			return false;		
	}
	
	public static Map<String, Boolean> getAccessInfo(List<AccessTypeDetail> accessTypeDetails, String servletName) {
		
		/* Initialize Hash Map for Access Information */
		HashMap<String, Boolean> accessInfo = new HashMap<String, Boolean>();
		
		for (int i = 0; i < accessTypeDetails.size(); i++ ) {
			AccessTypeDetail accessTypeDetail = accessTypeDetails.get(i);
			String urlPattern = accessTypeDetail.getUrlPattern().replace(".do", "");	// URL Pattern
			
			/* Check if Java Servlet Reference contains List Servlet keyword */
			if (accessTypeDetail.getJavaServletReference().contains("ListServlet")) {
				/* Check if Access Right is not equal to No Access */
				if (!accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_NOACCESS)) {
					accessInfo.put(urlPattern + "-page", true);
				} 
				else {
					accessInfo.put(urlPattern + "-page", false);
				}				
			}
			/* Check if Java Servlet Reference contains Create Servlet keyword */
			else if (accessTypeDetail.getJavaServletReference().contains("CreateServlet")) {
				/* Check if Access Right is not equal to No Access and Read Only */
				if (!accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_NOACCESS) &&
						!accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_READONLY)) {
					accessInfo.put(urlPattern + "-page", true);
					accessInfo.put(urlPattern + "-createbutton", true);
				}
				else {
					accessInfo.put(urlPattern + "-page", false);
					accessInfo.put(urlPattern + "-createbutton", false);
				}
			}
			/* Check if Java Servlet Reference contains View Servlet keyword */
			else if (accessTypeDetail.getJavaServletReference().contains("ViewServlet")) {
				/* Check if Access Right is equal to Full Access */
				if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_FULLACCESS)) {
					accessInfo.put(urlPattern + "-deletebutton", true);
					accessInfo.put(urlPattern + "-page", true);
					accessInfo.put(urlPattern + "-viewlink", true);
					accessInfo.put(urlPattern + "-prereqaddbutton", true);
					accessInfo.put(urlPattern + "-prereqdellink", true);
					accessInfo.put(urlPattern + "-approveraddbutton", true);
					accessInfo.put(urlPattern + "-approverbuttons", true);
				}
				else if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_READONLY) ||
						accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_WRITEONLY)) {
					accessInfo.put(urlPattern + "-deletebutton", false);
					accessInfo.put(urlPattern + "-page", true);
					accessInfo.put(urlPattern + "-viewlink", true);
					
					if (accessTypeDetail.getJavaServletReference().contains("TaskViewServlet")) {
						if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_WRITEONLY)) {
							accessInfo.put(urlPattern + "-prereqaddbutton", true);
							accessInfo.put(urlPattern + "-prereqdellink", false);
						}
						else {
							accessInfo.put(urlPattern + "-prereqaddbutton", false);
							accessInfo.put(urlPattern + "-prereqdellink", false);
						}
					}
					if (accessTypeDetail.getJavaServletReference().contains("DashboardRequestViewServlet")) {
						if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_READONLY)) {
							accessInfo.put(urlPattern + "-approveraddbutton", false);
							accessInfo.put(urlPattern + "-approverbuttons", false);
						}
						else {
							accessInfo.put(urlPattern + "-approveraddbutton", true);
							accessInfo.put(urlPattern + "-approverbuttons", true);
						}
					}
				}
				else {
					accessInfo.put(urlPattern + "-deletebutton", false);
					accessInfo.put(urlPattern + "-page", false);
					accessInfo.put(urlPattern + "-viewlink", false);
				}
			}
			/* Check if Java Servlet Reference contains Edit Servlet keyword */
			else if (accessTypeDetail.getJavaServletReference().contains("EditServlet")) {
				if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_FULLACCESS)) {
					accessInfo.put(urlPattern + "-page", true);
					accessInfo.put(urlPattern + "-editbutton", true);
					accessInfo.put(urlPattern + "-prereqaddbutton", true);
					accessInfo.put(urlPattern + "-prereqdellink", true);
					accessInfo.put(urlPattern + "-taskassigneddellink", true);
					accessInfo.put(urlPattern + "-taskdetailedtaskdellink", true);
				}
				else if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_WRITEONLY)) {
					accessInfo.put(urlPattern + "-editbutton", true);
					accessInfo.put(urlPattern + "-page", true);
					accessInfo.put(urlPattern + "-viewlink", true);
					accessInfo.put(urlPattern + "-prereqdellink", false);
					
					if (accessTypeDetail.getJavaServletReference().contains("PstrtTaskEditServlet")) {
						if (accessTypeDetail.getAccessRight().equals(WfConstCommon.ACCESS_RIGHT_WRITEONLY)) {
							accessInfo.put(urlPattern + "-prereqaddbutton", true);
							accessInfo.put(urlPattern + "-prereqdellink", false);
							accessInfo.put(urlPattern + "-taskassigneddellink", false);	
							accessInfo.put(urlPattern + "-taskdetailedtaskdellink", false);
						}
						else {
							accessInfo.put(urlPattern + "-prereqaddbutton", false);
							accessInfo.put(urlPattern + "-prereqdellink", false);
							accessInfo.put(urlPattern + "-taskassigneddellink", false);
							accessInfo.put(urlPattern + "-taskdetailedtaskdellink", false);
						}
					}
				}
				else {
					accessInfo.put(urlPattern + "-page", false);
					accessInfo.put(urlPattern + "-editbutton", false);
				}
			}
		}
		
		return accessInfo;
	}
	
	public static boolean isDateValid(String text) {
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    df.setLenient(false);
	    try {
	        df.parse(text);
	        return true;
	    } catch (ParseException ex) {
	        return false;
	    }
	}
	
	public static boolean isWithinRange(String startDate, String endDate) {
		
		try {
			Date sDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date eDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			  
	          // true if they start date and end date are equal
	          if (sDate.equals(eDate)) { return true; }
		      
		      // true if end date after the start date
		      if (eDate.after(sDate)) { return true; }
			
			return eDate.after(sDate);	
		} 
		
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean dateRangeFromStartDate(String startDate, String endDate) {	
		
		try {
			Date startdate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date enddate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			
			return enddate.equals(startdate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}
