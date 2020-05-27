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
import workflowsystem.data.maincontrol.Phase;
import workflowsystem.data.maincontrol.PhaseDAO;
import workflowsystem.data.wfguide.Project;
import workflowsystem.data.wfguide.ProjectDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;

public class WorkflowCreateServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/workflow-create.jsp");
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
			if (accessInfo.get("workflow-create-page")) {
				/* Retrieve Project ID */
				String idString = req.getParameter("projectId");

				/* Retrieve Project information from the Database */
				Project project = new ProjectDAO().find(new Long(idString));

				/* Retrieve List of Phases from the Database */
				List<Phase> phases = new PhaseDAO().findAll();

				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("phases", phases);
				req.setAttribute("project", project);
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
		/* Get project id */
		String idString = req.getParameter("projectId");
		req.getSession().setAttribute("viewID", idString);
		/* Check if cancel button was pressed. */
		String cancelButton = req.getParameter("cancel-button");
		if (cancelButton != null) {
			logger.debug("cancel button pressed");
			/* Set session to null */
			req.getSession().setAttribute("viewID", null);
			resp.sendRedirect("project-view.do?projectId=" + idString);
			return;
		}

		/* Perform validation which sets necessary error messages */
		Map<String, String> errors = WorkflowEditServlet.validate(req);

		/* If Error object is not empty, return error information */
		if (!errors.isEmpty()) {
			logger.debug("validation errors");

			/* Retrieve current session data */
			HttpSession session = req.getSession();
			List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
			List<Module> modules = (List<Module>) session.getAttribute("modules");

			Project project = new ProjectDAO().find(new Long(idString));
			List<Phase> phases = new PhaseDAO().findAll();

			req.setAttribute("modules", modules);
			req.setAttribute("accessTypeDetails", accessTypeDetails);

			req.setAttribute("phases", phases);
			req.setAttribute("project", project);
			jsp.forward(req, resp);
			return;
		}

		/* Create a new Workflow object, insert new entry in Database and return to view page */
		Workflow workflow = (Workflow) req.getAttribute("workflow");
		new WorkflowDAO().create(workflow);
		logger.debug("validation successful.");
		logger.info("workflow successfully created.");
		resp.sendRedirect("workflow-view.do?workflowId=" + workflow.getWorkflowId());
		logger.info("workflow-view redirected.");
	}
}