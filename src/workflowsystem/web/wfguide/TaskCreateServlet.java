package workflowsystem.web.wfguide;

import java.io.IOException;
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

import workflowsystem.data.maincontrol.AccessTypeDetail;
import workflowsystem.data.maincontrol.Module;
import workflowsystem.data.wfguide.Task;
import workflowsystem.data.wfguide.TaskDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;
import workflowsystem.util.WfMessageCommon;

public class TaskCreateServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/task-create.jsp");
	}

	/**
	 * Intercept HTTP GET request
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
		List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
		List<Module> modules = (List<Module>) session.getAttribute("modules");
		Map<String, Boolean> accessInfo = (Map<String, Boolean>) session.getAttribute("accessInfo");
		try {
			if (accessInfo.get("task-create-page")) {
				/* Retrieve Workflow ID */
				String idString = req.getParameter("workflowId");

				/* Retrieve Workflow information from the Database */
				Workflow workflow = new WorkflowDAO().find(new Long(idString));

				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("workflow", workflow);
				jsp.forward(req, resp);
			}
			else {
				/* Access denied page */
				logger.info("access denied.");
				resp.sendRedirect("access-denied.do");
			}
		}
		catch (NullPointerException e){
			/* Access denied page */
			logger.info("access denied.");
			resp.sendRedirect("access-denied.do");
		}


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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/* Get WorkflowID */
		String idString = req.getParameter("workflowId");
		req.getSession().setAttribute("viewID", idString);
		/* Check if cancel button was pressed */
		String cancelButton = req.getParameter("cancel-button");
		if (cancelButton != null) {
			logger.debug("cancel button pressed");
			req.getSession().setAttribute("viewID", null); 
			resp.sendRedirect("workflow-view.do?workflowId=" + idString);
			return;
		}

		/* Perform validation which sets necessary error messages */
		Map<String, String> errors = TaskEditServlet.validate(req);

		/* If Error object is not empty, return error information */
		if (!errors.isEmpty()) {
			logger.debug("validation errors");
			Long id = new Long(idString);

			/* Retrieve current session data */
			HttpSession session = req.getSession();
			List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
			List<Module> modules = (List<Module>) session.getAttribute("modules");

			Workflow workflow = new WorkflowDAO().find(id);

			req.setAttribute("modules", modules);
			req.setAttribute("accessTypeDetails", accessTypeDetails);

			req.setAttribute("workflow", workflow);
			
			jsp.forward(req, resp);
			return;
		}

		/* Create a new Task object, insert new entry in Database and return to previous view */
		Task task = (Task) req.getAttribute("task");
		new TaskDAO().create(task);
		logger.debug("validation successful.");
		logger.info("task successfully created.");
		resp.sendRedirect("task-view.do?taskId=" + task.getTaskId());
		logger.info("task-view redirected.");
		
	}
}