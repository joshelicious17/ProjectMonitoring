package workflowsystem.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import workflowsystem.data.maincontrol.User;
import workflowsystem.data.maincontrol.UserDAO;
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfMessageCommon;
import workflowsystem.util.WfUtilCommon;

public class ForgetPasswordServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/forget-password.jsp");
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

		/* User E-mail */
		String userEmail = req.getParameter("userEmail");
		/* Verify if User E-mail is blank */
		if (userEmail == null || userEmail.trim().length() == 0) {
			req.setAttribute("message", WfMessageCommon.EADUS07);
			jsp.forward(req, resp);
			return;
		}
		/* Verify if User E-mail format is valid */
		else if (!WfUtilCommon.isValidEmailAddress(userEmail)) {
			req.setAttribute("message", WfMessageCommon.EADUS08);
			jsp.forward(req, resp);
			return;
		}
		/* Verify if User E-mail is existing in the Database */
		User user = new UserDAO().findByUserEmail(userEmail);
		if(user == null) {
			logger.debug("invalid user e-mail");
			req.setAttribute("message", WfMessageCommon.EADUS10);
			jsp.forward(req, resp);
			return;
		}
		else {
			/* User validity access */
			DateFormat formatter =  new SimpleDateFormat("yyyy-MM-dd");
			Date convertedValidTo = null;
			Date convertedValidFrom = null;
			String UserAccessValidTo = user.getValidTo();
			String UserAccessValidFrom = user.getValidFrom();
			
			try {
				convertedValidTo = formatter.parse(UserAccessValidTo);
				Date today = new Date();
				Date todayWithZeroTime =formatter.parse(formatter.format(today));
				/* Account expiration validation */
				if (convertedValidTo.before(todayWithZeroTime)) {
					logger.debug("authentication failed: user account expired.");
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
				req.setAttribute("message", WfMessageCommon.EOTLG04);
				jsp.forward(req, resp);
				return;
			}
		}

		/* Generate random Reset Password Key */
		String resetPasswordKey = WfUtilCommon.generateRandomKey();

		/* Update User table of generated Reset Password Key */
		new UserDAO().updateResetPasswordKey(user.getUserId(), resetPasswordKey);

		SendMail mailBean = new SendMail();

		/* Set message */
		String message = user.getUserFullname() + WfConstCommon.REQUEST_RESET_PASSWORD_MSG01;
		message += WfConstCommon.REQUEST_RESET_PASSWORD_MSG02;
		message += WfConstCommon.REQUEST_RESET_PASSWORD_MSG03;
		message += WfConstCommon.REQUEST_RESET_PASSWORD_MSG04;
		message += WfConstCommon.REQUEST_RESET_PASSWORD_MSG05 
				+ resetPasswordKey;
		message += WfConstCommon.REQUEST_RESET_PASSWORD_MSG06;

		/* Reset password notification Recipient */
		String[] recipient = new String[]{userEmail};

		try {
			mailBean.sendMail(recipient, 							// Recipient
							  WfConstCommon.SUBJECT_PASSWORD_RESET, // Subject
							  message, 								// Message
							  WfConstCommon.DEFAULT_SENDER);		// Sender

		} catch (Exception e) {
			System.out.println("Error in sending mail:" + e);
		}		

		HttpSession session = req.getSession();
		session.setAttribute("userEmail", userEmail);
		logger.debug("request is successful");
		String url = "request-success.do?rt=" + WfConstCommon.REQUEST_TYPE_01;
		resp.sendRedirect(url);
	}
}