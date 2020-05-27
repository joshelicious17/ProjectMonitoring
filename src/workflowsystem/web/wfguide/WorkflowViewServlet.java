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
import workflowsystem.data.wfguide.TaskPrereq;
import workflowsystem.data.wfguide.TaskPrereqDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;

public class WorkflowViewServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/workflow-view.jsp");
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
			if (accessInfo.get("workflow-view-page")) {		
				/* Retrieve Workflow ID */
				String idString = req.getParameter("workflowId");
				/* Initialize Workflow object and retrieve Workflow for viewing */
				Workflow workflow = new WorkflowDAO().find(new Long(idString));			
				/* Initialize session for workflow id */
				req.getSession().setAttribute("workflowid", idString);

				/* If Workflow object is Null, redirect to project view page */
				if (workflow == null) {
					logger.debug("workflow null; redirect.");
					if(!req.getSession().getAttribute("projid").equals(null)) {
						resp.sendRedirect("project-view.do?projectId=" +  req.getSession().getAttribute("projid"));
						req.getSession().setAttribute("projid", null); 
					}
				} else {
					req.setAttribute("workflow", workflow);

					/* Retrieve related Tasks of the Workflow */
					List<Task> tasks = new TaskDAO().findAll(new Long(idString));

					Long[][] taskPrereqs = new Long[tasks.size()][tasks.size()];
					/* Get prereqs of each tasks */
					for (int i = 0; i < tasks.size(); i++) {
						Long taskId = tasks.get(i).getTaskId();
						List<TaskPrereq> taskPrereqs1 = null;
						taskPrereqs1 = new TaskPrereqDAO()
						.findAllByTaskIdParent(taskId);
						for (int j = 0; j < taskPrereqs1.size(); j++) {
							taskPrereqs[i][j] = taskPrereqs1.get(j).getTaskIdChild();
						}
					}

					req.setAttribute("taskPrereqs", taskPrereqs);
					req.setAttribute("tasks", tasks);
					req.setAttribute("modules", modules);
					req.setAttribute("accessTypeDetails", accessTypeDetails);
					req.setAttribute("accessInfo", accessInfo);
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
				/* Redirect to project viewpage */
				if(!req.getSession().getAttribute("viewID").equals(null)) {
					resp.sendRedirect("project-view.do?projectId=" +  req.getSession().getAttribute("viewID"));
					req.getSession().setAttribute("viewID", null); }
			}
			catch (NullPointerException err){
				/* Access denied page */
				logger.info("access denied.");
				resp.sendRedirect("access-denied.do");
			}
		}	
	}
}