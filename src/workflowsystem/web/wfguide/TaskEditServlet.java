package workflowsystem.web.wfguide;

import java.io.IOException;
import java.util.HashMap;
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
import workflowsystem.util.WfConstCommon;
import workflowsystem.util.WfMessageCommon;

public class TaskEditServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/task-edit.jsp");
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
			if (accessInfo.get("task-edit-page")) {
				String idString = req.getParameter("taskId");
				Task task = new TaskDAO().find(new Long(idString));

				/* Retrieve List of Prerequisites, Task Document Input/Output and
				 * Tasks from the Database */
				List<TaskPrereq> taskPrereqs = new TaskPrereqDAO().findAllByTaskIdParent(new Long(idString));
				List<TaskDocument> taskDocumentInputs = new TaskDocumentDAO().findAllInput(new Long(idString));
				List<TaskDocument> taskDocumentOutputs = new TaskDocumentDAO().findAllOutput(new Long(idString));
				List<Task> tasks = new TaskDAO().findPrereq(task.getWorkflowId(),task.getTaskId());

				/* Remove tasks that are already added to Prerequisite list */
				for (int i = 0; i < tasks.size(); i++) {
					Long taskId = tasks.get(i).getTaskId();
					logger.debug("Available taskId " + taskId);
					for (int j = 0; j < taskPrereqs.size(); j++) {
						logger.debug("taskPrereqs taskId " + taskPrereqs.get(j).getTaskIdChild());
						if (taskId.equals(taskPrereqs.get(j).getTaskIdChild())) {
							tasks.remove(i);
							break;
						}
					}
				}

				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("task", task);
				req.setAttribute("taskPrereqs", taskPrereqs);
				req.setAttribute("taskDocumentInputs", taskDocumentInputs);
				req.setAttribute("taskDocumentOutputs", taskDocumentOutputs);
				req.setAttribute("tasks", tasks);
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
		Map<String, String> errors = validate(req);
		if (!errors.isEmpty()) {
			logger.debug("validation errors");

			/* Retrieve current session data */
			HttpSession session = req.getSession();
			List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
			List<Module> modules = (List<Module>) session.getAttribute("modules");

			/* Retrieve List of Prerequisites and Task Document Input/Output from the Database */
			List<TaskPrereq> taskPrereqs = new TaskPrereqDAO().findAllByTaskIdParent(new Long(idString));
			List<TaskDocument> taskDocumentInputs = new TaskDocumentDAO().findAllInput(new Long(idString));
			List<TaskDocument> taskDocumentOutputs = new TaskDocumentDAO().findAllOutput(new Long(idString));

			req.setAttribute("modules", modules);
			req.setAttribute("accessTypeDetails", accessTypeDetails);

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
			logger.debug("taskPrereq successfully added.");
			resp.sendRedirect("task-edit.do?taskId=" + idString);
		} else {
			/* Redirect/go back to Task View screen */
			logger.debug("taskPrereq null; redirect.");
			resp.sendRedirect("task-view.do?taskId=" + idString);
		}
	}

	/**
	 * Perform data validation
	 * 
	 * @param req				HTTP Servlet Request
	 */
	public static Map<String, String> validate(HttpServletRequest req) {
		Logger logger = Logger.getLogger(TaskEditServlet.class);
		Task task = new Task();
		TaskPrereq taskPrereq = new TaskPrereq();
		HashMap<String, String> errors = new HashMap<String, String>();

		req.setAttribute("errors", errors);
		req.setAttribute("task", task);
		req.setAttribute("taskPrereq", taskPrereq);

		/* Task ID */
		String idString = req.getParameter("taskId");
		if (idString != null && idString.length() > 0) {
			task.setTaskId(new Long(idString));				// Task ID
			taskPrereq.setTaskIdParent(new Long(idString));	// Task ID Parent
			logger.debug("taskId not null.");
		}
		
		/* Project ID */
		String projectIdString = req.getParameter("projectId");
		if (projectIdString != null && projectIdString.length() > 0) {
			task.setProjectId(new Long(projectIdString));
			logger.debug("projectId not null.");
		}
		else {
			logger.error("projectId null.");
		}

		/* Workflow ID */
		String workflowIdString = req.getParameter("workflowId");
		if (workflowIdString != null && workflowIdString.length() > 0) {
			task.setWorkflowId(new Long(workflowIdString));
			req.getSession().setAttribute("viewID", workflowIdString);
			logger.debug("workflowId not null.");
		}
		else {
			logger.error("workflowId null.");
		}

		/* Task Name */
		String taskName = req.getParameter("taskName");
		if (taskName == null || taskName.trim().length() == 0) {
			errors.put("taskName", WfMessageCommon.EWGTS05);
			logger.warn("taskName null.");
		}
		else {
			task.setTaskName(taskName);
			logger.debug("taskName not null.");
		}

		/* Work Points */
		String taskWorkPoints = req.getParameter("taskWorkPoints");
		if (taskWorkPoints == null || taskWorkPoints.trim().length() == 0) {
			logger.debug("taskWorkPoints null.");
		}
		else {
			task.setTaskWorkPoints(taskWorkPoints);
			logger.debug("taskWorkPoints not null.");
		}

		/* Task ID Child */
		String selectedTaskIdChild = req.getParameter("selectedTaskIdChild");
		if (selectedTaskIdChild != null && selectedTaskIdChild.trim().length() != 0) {
			Long taskIdChild = new Long(selectedTaskIdChild);
			taskPrereq.setTaskIdChild(taskIdChild);
			logger.debug("taskIdChild not null.");
		}
		else {
			logger.debug("taskIdChild null.");
		}
				
		/* Total Hours */
		String totalHours = req.getParameter("taskTotalHours");
		if (totalHours == null || totalHours.trim().length() == 0) {
			task.setTaskTotalHours(0.0);
			logger.debug("taskTotalHours null.");
		}
		else if(totalHours.matches("^\\d+([\\.]?\\d+)?$")) {
			task.setTaskTotalHours(new Double (totalHours));
			logger.debug("taskTotalHours not null.");
		}
		else {
			errors.put("taskTotalHours", WfMessageCommon.EPSTS03);
			logger.warn("taskTotalHours null.");
		}

		/* Task Progress */
		String progress = req.getParameter("taskProgress");
		if (progress == null || progress.trim().length() == 0) {
			task.setTaskProgress(0.0);
			logger.debug("taskProgress null.");
		}
		else if(progress.matches("^\\d+([\\.]?\\d+)?$")) {
			task.setTaskProgress(new Double (progress));
			logger.debug("taskProgress not null.");
		} 
		else {
			errors.put("taskProgress", WfMessageCommon.EPSTS03);
			logger.warn("taskProgress null.");
		}

		/* Task Planned Hours */
		String plannedHours = req.getParameter("taskPlannedHours");
		if (plannedHours == null || plannedHours.trim().length() == 0) {
			task.setTaskPlannedHours(0.0);
			logger.debug("taskPlannedHours null.");
		}
		else if(plannedHours.matches("^\\d+([\\.]?\\d+)?$")) {
			task.setTaskPlannedHours(new Double (plannedHours));
			logger.debug("taskPlannedHours not null.");
		}
		else {
			errors.put("taskPlannedHours", WfMessageCommon.EPSTS03);
			logger.warn("taskPlannedHours null.");
		}

		/* Task Status*/
		String status = req.getParameter("taskStatus");
		if (status == null || status.trim().length() == 0) {
			task.setTaskStatus(WfConstCommon.TSK_STATUS_CODE_2_NEW);
		}
		else {
			task.setTaskStatus(status);
		}
		logger.debug("taskStatus not null.");

		return errors;
	}
}