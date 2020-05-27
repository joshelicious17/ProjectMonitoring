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
import workflowsystem.data.maincontrol.Company;
import workflowsystem.data.maincontrol.CompanyDAO;
import workflowsystem.data.maincontrol.Module;
import workflowsystem.data.wfguide.Project;
import workflowsystem.data.wfguide.ProjectDAO;

public class ProjectCreateServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/project-create.jsp");
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
			if (accessInfo.get("project-create-page")) {
				/* Retrieve List of Companies */
				List<Company> companies = new CompanyDAO().findAll();

				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("companies", companies);
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
		/* Add value in session*/
		req.getSession().setAttribute("viewID", "1");
		/* Check if cancel button was pressed. */
		String cancelButton = req.getParameter("cancel-button");
		if (cancelButton != null) {
			logger.debug("cancel button pressed");
			req.getSession().setAttribute("viewID", null); 
			resp.sendRedirect("project-list.do");
			return;
		}

		/* Perform validation which sets necessary error messages */
		Map<String, String> errors = ProjectEditServlet.validate(req);

		/* If Error object is not empty, return error information */
		if (!errors.isEmpty()) {
			logger.debug("validation errors");

			/* Retrieve current session data */
			HttpSession session = req.getSession();
			List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
			List<Module> modules = (List<Module>) session.getAttribute("modules");

			List<Company> companies = new CompanyDAO().findAll();

			req.setAttribute("modules", modules);
			req.setAttribute("accessTypeDetails", accessTypeDetails);

			req.setAttribute("companies", companies);
			jsp.forward(req, resp);
			return;
		}

		/* Create a new Project object, insert new entry in Database and return to previous view */
		Project project = (Project) req.getAttribute("project");
		new ProjectDAO().create(project);
		logger.debug("validation successful.");
		logger.info("project successfully created.");
		resp.sendRedirect("project-view.do?projectId=" + project.getProjectId());
		logger.info("project-view redirected.");
	}
}