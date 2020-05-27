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
import workflowsystem.data.maincontrol.Company;
import workflowsystem.data.maincontrol.CompanyDAO;
import workflowsystem.data.maincontrol.Module;
import workflowsystem.data.wfguide.Project;
import workflowsystem.data.wfguide.ProjectDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;
import workflowsystem.util.WfMessageCommon;

public class ProjectEditServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/project-edit.jsp");
	}

	/**
	 * Intercept HTTP GET request and retrieve Project information
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
			if (accessInfo.get("project-edit-page")) {
				/* Retrieve Project ID */
				String idString = req.getParameter("projectId");

				/* Retrieve Project to be edited from the Database */
				Project project = new ProjectDAO().find(new Long(idString));


				/* Initialize List of Companies */
				List<Company> companies = new CompanyDAO().findAll();

				/* Initialize List of Workflows of the Project */
				List<Workflow> workflows = new WorkflowDAO().findAll(new Long(idString));

				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("companies", companies);
				req.setAttribute("workflows", workflows);
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
		logger.debug("doPost()");

		/* Retrieve Project ID */
		String idString = req.getParameter("projectId");

		/* Check if cancel button was pressed. */
		String cancelButton = req.getParameter("cancel-button");
		if (cancelButton != null) {
			logger.debug("cancel button pressed");
			resp.sendRedirect("project-view.do?projectId=" + idString);
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

			List<Company> companies = new CompanyDAO().findAll();
			List<Workflow> workflows = new WorkflowDAO().findAll(id);

			req.setAttribute("modules", modules);
			req.setAttribute("accessTypeDetails", accessTypeDetails);

			req.setAttribute("workflows", workflows);
			req.setAttribute("companies", companies);
			jsp.forward(req, resp);
			return;
		}

		Project project = (Project) req.getAttribute("project");

		/* Update Project entry in the database */
		new ProjectDAO().update(project);
		logger.debug("validation successful.");
		logger.info("project successfully updated.");
		resp.sendRedirect("project-view.do?projectId=" + idString);
		logger.info("project-view redirected.");
	}

	/**
	 * Perform data validation
	 * 
	 * @param req				HTTP Servlet Request
	 */
	public static Map<String, String> validate(HttpServletRequest req) {
		Logger logger = Logger.getLogger(ProjectEditServlet.class);	
		Project project = new Project();
		HashMap<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		req.setAttribute("project", project);
		
		/* Set default value for StartDate*/
		project.setProjectStartDate("0000-00-00");
		
		/* Project ID */
		String idString = req.getParameter("projectId");
		if (idString != null && idString.length() > 0) {
			Long id = new Long(idString);
			project.setProjectId(id);
			logger.debug("projectId not null.");
		}
		
		/* Add value in session*/
		req.getSession().setAttribute("viewID", "1");
		/* Project Name */
		String projectName = req.getParameter("projectName");
		if (projectName == null || projectName.trim().length() == 0) {
			errors.put("projectName", WfMessageCommon.EWGPR01);
			logger.warn("projectName null.");
		}
		else {
			project.setProjectName(projectName);
			logger.debug("projectName not null.");
		}

		/* Project Description */
		String projectDescription = req.getParameter("projectDescription");
		if (projectDescription == null
				|| projectDescription.trim().length() == 0) {
			errors.put("projectDescription", WfMessageCommon.EWGPR02);
			logger.warn("projectDesc null.");
		}
		else {
			project.setProjectDescription(projectDescription);
			logger.debug("projectName not null.");
		}

		/* Company Name */
		String companyCode = req.getParameter("companyCode");
		if (companyCode == null || companyCode.length() == 0) {
			errors.put("companyCode", WfMessageCommon.EADCO02);
			logger.warn("companyCode null.");
		}
		else {
			project.setCompanyCode(companyCode);
			logger.debug("companyCode not null.");
		}

		/* Project Status */
		String projectStatus = req.getParameter("projectStatus");
		if (projectStatus == null || projectStatus.trim().length() == 0) {
			errors.put("projectStatus", WfMessageCommon.EWGPR03);
			logger.warn("projectStatus null.");
		}
		else {
			project.setProjectStatus(projectStatus);
			logger.debug("projectStatus not null.");
		}

		return errors;
	}
}