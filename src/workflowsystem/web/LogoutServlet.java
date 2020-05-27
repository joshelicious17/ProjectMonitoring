package workflowsystem.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

public class LogoutServlet extends HttpServlet
{
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Intercept HTTP GET request
	 * 
	 * @param req				HTTP Servlet Request
	 * @param resp				HTTP Servlet Response		
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		logger.debug("doGet()");
		HttpSession session = req.getSession();
		Long userId = (Long) session.getAttribute("userId");
		logger.info("user " +userId+ " logged out.");
		MDC.remove("userId");
		session.invalidate();
		String url = "login.do";
		resp.sendRedirect(url);
	}
}