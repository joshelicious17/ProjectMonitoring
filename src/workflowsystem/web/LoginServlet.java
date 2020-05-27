package workflowsystem.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import workflowsystem.data.SecureDigester;
import workflowsystem.data.maincontrol.AccessTypeDetail;
import workflowsystem.data.maincontrol.AccessTypeDetailDAO;
import workflowsystem.data.maincontrol.User;
import workflowsystem.data.maincontrol.UserDAO;
import workflowsystem.data.maincontrol.Module;
import workflowsystem.data.maincontrol.ModuleDAO;
import workflowsystem.util.WfMessageCommon;
import workflowsystem.util.WfUtilCommon;

public class LoginServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(this.getClass());
	private RequestDispatcher jsp;

	/**
	 * Initialize Servlet and invoke JSP page
	 * 
	 * @param config			Servlet Configuration 			
	 * @throws ServletException	Servlet Exception
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.debug("init()");
		ServletContext context = config.getServletContext();
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
	}

	/**
	 * Intercept HTTP GET request
	 * 
	 * @param req				HTTP Servlet Request
	 * @param resp				HTTP Servlet Response		
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doGet()");
		jsp.forward(req, resp);
	}

	/**
	 * Intercept HTTP POST request, conduct necessary data check
	 * 
	 * @param req				HTTP Servlet Request 			
	 * @param resp				HTTP Servlet Response
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doPost()");

		/* User Name */
		String username = req.getParameter("username");
		User user = new UserDAO().findByUsername(username);
		if (user == null) {
			logger.debug("authentication failed: bad username");
			req.setAttribute("message", WfMessageCommon.EOTLG01);
			jsp.forward(req, resp);
			return;
		}
		else {
			/* User validity access */
			DateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
			Date convertedValidTo = null;
			Date convertedValidFrom = null;
			String userAccessValidTo = user.getValidTo();
			String UserAccessValidFrom = user.getValidFrom();
			
			try {
				convertedValidTo = formatter.parse(userAccessValidTo);
				Date today = new Date();
				Date todayWithZeroTime = formatter.parse(formatter.format(today));
				/* Account expiration validation */
				if (convertedValidTo.before(todayWithZeroTime)) {
					logger.debug("authentication failed: account is no longer active");
					req.setAttribute("message", WfMessageCommon.EOTLG04);
					jsp.forward(req, resp);
					return;
					
				}
				/* Account before access validation */
				convertedValidFrom = formatter.parse(UserAccessValidFrom);
				if (todayWithZeroTime.before(convertedValidFrom)){
					logger.debug("authentication failed: account is not yet active.");
					req.setAttribute("message", WfMessageCommon.EOTLG05);
					jsp.forward(req, resp);
					return;					
				}
			} catch (ParseException e) {
				jsp.forward(req, resp);
				return;
			}
		}

		/* Password */
		String password = req.getParameter("password");
		if (password == null) {
			logger.debug("authentication failed: no password");
			req.setAttribute("message", WfMessageCommon.EOTLG01);
			jsp.forward(req, resp);
			return;
		}
		
		/* Password encryption */
		String passwordDigest = SecureDigester.digest(password);
		if (!user.getUserPassword().equals(passwordDigest)) {
			logger.debug("authentication failed: bad password");
			req.setAttribute("message", WfMessageCommon.EOTLG01);
			jsp.forward(req, resp);
			return;
		}

		HttpSession session = req.getSession();
		Long userId = user.getUserId();
		session.setAttribute("userId", userId);
		session.setAttribute("userFullName", user.getUserFullname());
		
		List<AccessTypeDetail> accessTypeDetails = new AccessTypeDetailDAO().findAllByUserId(userId);
		session.setAttribute("accessTypeDetails", accessTypeDetails);
		
		List<Module> modules = new ModuleDAO().findAllByUserId(userId);
		session.setAttribute("modules", modules);
		
		Map<String, Boolean> accessInfo = WfUtilCommon.getAccessInfo(accessTypeDetails, this.getClass().getSimpleName());
		session.setAttribute("accessInfo", accessInfo);
		
		MDC.put("userId", userId);
		logger.debug("authenticated");
		logger.debug("user " +userId+ " logged in");
		String url = "home.do";
		resp.sendRedirect(url);
	}
}