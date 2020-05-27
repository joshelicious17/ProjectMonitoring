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
import workflowsystem.data.maincontrol.Phase;
import workflowsystem.data.maincontrol.PhaseDAO;
import workflowsystem.data.wfguide.Task;
import workflowsystem.data.wfguide.TaskDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;
import workflowsystem.util.WfMessageCommon;
import workflowsystem.util.WfUtilCommon;

public class WorkflowEditServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/workflow-edit.jsp");
	}

	/**
	 * Intercept HTTP GET request and retrieve Workflow information
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
			if (accessInfo.get("workflow-edit-page")) {	
				/* Retrieve Workflow ID */
				String idString = req.getParameter("workflowId");

				/* Retrieve Workflow to be edited from the Database */
				Workflow workflow = new WorkflowDAO().find(new Long(idString));
				/* Project ID */
				req.getSession().setAttribute("viewID", workflow.getProjectId());
				/* Retrieve List of Phases from the Database */
				List<Phase> phases = new PhaseDAO().findAll();

				/* Retrieve related List of Tasks from the Database */
				List<Task> tasks = new TaskDAO().findAll(new Long(idString));

				req.setAttribute("phases", phases);
				req.setAttribute("tasks", tasks);
				req.setAttribute("workflow", workflow);
				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("accessInfo", accessInfo);

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
		String idString = req.getParameter("workflowId");

		/* Check if cancel button was pressed. */
		String cancelButton = req.getParameter("cancel-button");
		if (cancelButton != null) {
			logger.debug("cancel button pressed");
			resp.sendRedirect("workflow-view.do?workflowId=" + idString);
			return;
		}

		/* Perform input data validation */
		Map<String, String> errors = validate(req);

		/* If Error object is not empty, return error information */
		if (!errors.isEmpty()) {
			logger.debug("validation errors");
			Long id = new Long(idString);

			/* Retrieve current session data */
			HttpSession session = req.getSession();
			List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
			List<Module> modules = (List<Module>) session.getAttribute("modules");

			List<Phase> phases = new PhaseDAO().findAll();
			List<Task> tasks = new TaskDAO().findAll(id);

			req.setAttribute("modules", modules);
			req.setAttribute("accessTypeDetails", accessTypeDetails);
			req.setAttribute("phases", phases);
			req.setAttribute("tasks", tasks);
			jsp.forward(req, resp);
			return;
		}

		Workflow workflow = (Workflow) req.getAttribute("workflow");

		/* Update Workfow entry in the database */
		new WorkflowDAO().update(workflow);
		logger.debug("validation successful.");
		logger.info("workflow successfully updated.");
		resp.sendRedirect("workflow-view.do?workflowId=" + idString);
		logger.info("workflow-view redirected.");
	}

	/**
	 * Perform data validation
	 * 
	 * @param req				HTTP Servlet Request
	 */
	public static Map<String, String> validate(HttpServletRequest req) {
		Logger logger = Logger.getLogger(WorkflowEditServlet.class);
		Workflow workflow = new Workflow();
		HashMap<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		req.setAttribute("workflow", workflow);

		/* Workflow ID */
		String idString = req.getParameter("workflowId");
		if (idString != null && idString.length() > 0) {
			workflow.setWorkflowId(new Long(idString));
			logger.debug("workflowId not null.");
		}

		/* Workflow Name */
		String workflowName = req.getParameter("workflowName");
		if (workflowName == null || workflowName.trim().length() == 0) {
			errors.put("workflowName", WfMessageCommon.EWGWF01);
			logger.warn("workflowName null.");
		}
		else {
			workflow.setWorkflowName(workflowName);
			logger.debug("workflowName not null.");
		}

		/* Project Name */
		workflow.setProjectName(req.getParameter("projectName"));

		/* Workflow Sequence */
		String workflowSequenceString = req.getParameter("workflowSequence");
		/* Verify if Workflow Sequence is empty */
		if (workflowSequenceString == null
				|| workflowSequenceString.trim().length() == 0) {
			errors.put("workflowSequence", WfMessageCommon.EWGWF02);
			workflow.setWorkflowSequence(null);
			logger.warn("workflowSequence null.");
			/* Verify if entered Workflow Sequence is a number */
		} else if (WfUtilCommon.isInteger(workflowSequenceString) == false) {
			errors.put("workflowSequence", WfMessageCommon.EWGWF03);
			workflow.setWorkflowSequence(null);
			logger.warn("workflowSequence not integer.");
		} else {
			Long workflowSequence = new Long(workflowSequenceString);
			workflow.setWorkflowSequence(workflowSequence);
			logger.debug("workflowSequence not null.");
		}
		
		/* Phase Code */
		String workflowPhase = req.getParameter("phaseCode");
		if (workflowPhase == null) {
			errors.put("phaseCode", WfMessageCommon.EWGWF04);
			logger.warn("workflowPhase null.");
		}
		else {
			workflow.setPhaseCode(req.getParameter("phaseCode"));
			logger.debug("workflowPhase not null.");
		}

		return errors;
	}

}