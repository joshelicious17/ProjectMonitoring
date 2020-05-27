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
import workflowsystem.data.wfguide.TaskDocument;
import workflowsystem.data.wfguide.TaskDocumentDAO;
import workflowsystem.data.wfguide.TaskPrereq;
import workflowsystem.data.wfguide.TaskPrereqDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;

public class TaskViewServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/task-view.jsp");
	}

	/**
	 * Intercept HTTP GET request and retrieve Task information
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
			if (accessInfo.get("task-view-page")) {
				/* Retrieve Task ID */
				String idString = req.getParameter("taskId");
				/* Initialize Task object and retrieve task for viewing */
				Task task = new TaskDAO().find(new Long(idString));				
				/* Initialize session for task id */
				req.getSession().setAttribute("taskid", idString);

				/* If Task is null, then return to workfloe task page */
				if (task == null) {
					logger.debug("task null; redirect");
					if(!req.getSession().getAttribute("workflowid").equals(null)) {
						resp.sendRedirect("workflow-view.do?workflowId=" + req.getSession().getAttribute("workflowid"));
						req.getSession().setAttribute("workflowid", null); }
				} else {
					List<TaskPrereq> taskPrereqs = new TaskPrereqDAO().findAllByTaskIdParent(new Long(idString));
					List<TaskDocument> taskDocumentInputs = new TaskDocumentDAO().findAllInput(new Long(idString));
					List<TaskDocument> taskDocumentOutputs = new TaskDocumentDAO().findAllOutput(new Long(idString));
					List<Task> tasks = new TaskDAO().findPrereq(task.getWorkflowId(),task.getTaskId());

					/* Remove tasks that are already added to prereq list */
					for (int i = 0; i < tasks.size(); i++) {
						Long taskId = tasks.get(i).getTaskId();
						for (int j = 0; j < taskPrereqs.size(); j++) {
							if (taskId.equals(taskPrereqs.get(j).getTaskIdChild())) {
								tasks.remove(i);
								/* Decrement index as well */
								i--;
								break;
							}
						}
					}
					req.setAttribute("modules", modules);
					req.setAttribute("accessTypeDetails", accessTypeDetails);
					req.setAttribute("accessInfo", accessInfo);
					req.setAttribute("tasks", tasks);
					req.setAttribute("task", task);
					req.setAttribute("taskPrereqs", taskPrereqs);
					req.setAttribute("taskDocumentInputs", taskDocumentInputs);
					req.setAttribute("taskDocumentOutputs", taskDocumentOutputs);

					jsp.forward(req, resp);
				}
			}
			else {
				/* Access denied page */
				logger.info("access denied.");
				resp.sendRedirect("access-denied.do");
			}
		}
		catch (NullPointerException e){
			try {
				/* Redirect to workflow viewpage */
				if(!req.getSession().getAttribute("viewID").equals(null)) {
					resp.sendRedirect("workflow-view.do?workflowId=" + req.getSession().getAttribute("viewID"));
					req.getSession().setAttribute("viewID", null); }
			}
			catch (NullPointerException err){
				/* Access denied page */
				logger.info("access denied.");
				resp.sendRedirect("access-denied.do");
			}
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doPost()");

		/* Retrieve Task ID */
		String idString = req.getParameter("taskId");

		/* Check if cancel button was pressed. */
		String cancelButton = req.getParameter("cancel-button");
		if (cancelButton != null) {
			logger.debug("cancel button pressed");
			resp.sendRedirect("task-view.do?taskId=" + idString);
			return;
		}

		/* Perform input data validation */
		Map<String, String> errors = TaskEditServlet.validate(req);
		if (!errors.isEmpty()) {
			logger.debug("validation errors");

			List<TaskPrereq> taskPrereqs = new TaskPrereqDAO().findAllByTaskIdParent(new Long(idString));
			List<TaskDocument> taskDocumentInputs = new TaskDocumentDAO().findAllInput(new Long(idString));
			List<TaskDocument> taskDocumentOutputs = new TaskDocumentDAO().findAllOutput(new Long(idString));

			req.setAttribute("taskPrereqs", taskPrereqs);
			req.setAttribute("taskDocumentInputs", taskDocumentInputs);
			req.setAttribute("taskDocumentOutputs", taskDocumentOutputs);
			jsp.forward(req, resp);
			return;
		}

		/* Fetch task attribute and cast to Task object */
		Task task = (Task) req.getAttribute("task");
		/* Fetch task prerequisite attribute and cast to Task Prerequisite object */
		TaskPrereq taskPrereq = (TaskPrereq) req.getAttribute("taskPrereq");
		
		/* Execute update statement passing the Task object */
		new TaskDAO().update(task);
		logger.debug("validation successful.");
		logger.info("task successfully updated.");

		/* Execute create/insert statement passing the Task Prerequisite object */
		if (taskPrereq.getTaskIdChild() != null) {
			new TaskPrereqDAO().create(taskPrereq);
			resp.sendRedirect("task-view.do?taskId=" + idString);
			logger.debug("taskPrereq successfully added.");
		} else {
			/* Redirect/go back to Task View screen */
			logger.debug("taskPrereq null; redirect.");
			resp.sendRedirect("task-view.do?taskId=" + idString);
		}
	}
}