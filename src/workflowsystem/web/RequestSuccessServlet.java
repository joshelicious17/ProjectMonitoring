package workflowsystem.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfMessageCommon;

public class RequestSuccessServlet extends HttpServlet
{
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/request-success.jsp");
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

		/* Retrieve Request Type */
		String requestType = req.getParameter("rt");
	
		if(requestType.equals(WfConstCommon.REQUEST_TYPE_01)) {
			req.setAttribute("message", WfMessageCommon.EOTLG02);
		} else if (requestType.equals(WfConstCommon.REQUEST_TYPE_02)) {
			req.setAttribute("message", WfMessageCommon.EOTLG03);
		}
		jsp.forward(req, resp);
	}
}