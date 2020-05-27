package workflowsystem.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import workflowsystem.data.SecureDigester;
import workflowsystem.data.maincontrol.UserDAO;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfMessageCommon;

public class ResetPasswordServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(this.getClass());
	private RequestDispatcher jsp;

	/**
	 * Initialize Servlet and invoke JSP page
	 * 
	 * @param config			Servlet Configuration 			
	 * @throws ServletException	Servlet Exception
	 */
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/reset-password.jsp");
	}

	/**
	 * Intercept HTTP GET request and retrieve User information
	 * 
	 * @param req				HTTP Servlet Request
	 * @param resp				HTTP Servlet Response		
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doGet()");
		String resetPasswordKey = req.getParameter("rpk");
		req.setAttribute("resetPasswordKey", resetPasswordKey);
		jsp.forward(req, resp);
	}

	/**
	 * Intercept HTTP POST request, conduct necessary data
	 * check and store to database
	 * 
	 * @param req				HTTP Servlet Request 			
	 * @param resp				HTTP Servlet Response
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		/* Retrieve Reset Password Key */
		String resetPasswordKey = req.getParameter("resetPasswordKey");
		
		/* User Password */
		String userPassword = req.getParameter("userPassword");
		if (userPassword == null || userPassword.trim().length() == 0) {
			logger.debug("authentication failed: no password");
			req.setAttribute("userPassword", WfMessageCommon.EOTLG01);
			jsp.forward(req, resp);
			return;
		}

		/* Password Confirmation */
		String confirmUserPassword = req.getParameter("confirmUserPassword");
		if (confirmUserPassword == null || confirmUserPassword.trim().length() == 0) {
			req.setAttribute("confirmUserPassword", WfMessageCommon.EADUS05);
			jsp.forward(req, resp);
			return;
		}

		/* If Password and Password Confirmation values does not match */
		if (userPassword.trim().length() != 0 && confirmUserPassword.trim().length() != 0) {
			if (!userPassword.equals(confirmUserPassword)) {
				req.setAttribute("confirmUserPassword", WfMessageCommon.EADUS06);
				jsp.forward(req, resp);
				return;
			}

		}

		/* Update entry in User Database and return to Login page */
		new UserDAO().updateUserPassword(SecureDigester.digest(userPassword), 
										 resetPasswordKey);

		String url = "request-success.do?rt=" + WfConstCommon.REQUEST_TYPE_02;
		resp.sendRedirect(url);
	}
}