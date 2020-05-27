package workflowsystem.web.wfguide;

import java.io.IOException;
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

import workflowsystem.data.wfguide.TaskPrereq;
import workflowsystem.data.wfguide.TaskPrereqDAO;

public class TaskPrereqDeleteServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/delete-item.jsp");
	}

	/**
	 * Intercept HTTP GET request and performs deletion
	 * 
	 * @param req				HTTP Servlet Request
	 * @param resp				HTTP Servlet Response		
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doGet()");

		/* Retrieve current session data */
		HttpSession session = req.getSession();
		Map<String, Boolean> accessInfo = (Map<String, Boolean>) session.getAttribute("accessInfo");
		
		if (accessInfo.get("task-view-page")) {
			/* Retrieve Task Prerequisite ID */
			String idString = req.getParameter("taskPrereqId");

			/* Initialize Task Prerequisite object and retrieve Project to be deleted */
			TaskPrereq taskprereq = new TaskPrereqDAO().find(new Long(idString));

			/* Perform Deletion */
			new TaskPrereqDAO().delete(taskprereq);
			logger.debug("taskprereq successfully deleted.");

			/* Return to Task View page */
			resp.sendRedirect("task-view.do?taskId=" + taskprereq.getTaskIdParent());
			logger.info("task-view redirected.");
		}
		else {
			/* Access denied page */
			logger.info("access denied.");
			resp.sendRedirect("access-denied.do");
		}
		
	}
}