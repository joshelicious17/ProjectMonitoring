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
import workflowsystem.data.wfguide.Project;
import workflowsystem.data.wfguide.ProjectDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;

public class ProjectViewServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/project-view.jsp");
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

		/* Retrieve Project ID */
		String idString = req.getParameter("projectId");
		/* Initialize Project object and retrieve Project for viewing */
		Project project = new ProjectDAO().find(new Long(idString));		
		/* Initialize session for project id */
		req.getSession().setAttribute("projid", idString);

		/* If Project object is Null, redirect to List page */
		if (project == null) {
			logger.debug("project null; redirect.");
			resp.sendRedirect("project-list.do");
		} else {
			req.setAttribute("project", project);

			/* Retrieve current session data */
			HttpSession session = req.getSession();
			List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
			List<Module> modules = (List<Module>) session.getAttribute("modules");
			Map<String, Boolean> accessInfo = (Map<String, Boolean>) session.getAttribute("accessInfo");
			try {
				if (accessInfo.get("project-view-page")) {
					/* Retrieve related Workflow of the Project */
					List<Workflow> workflows = new WorkflowDAO().findAll(new Long(idString));

					req.setAttribute("modules", modules);
					req.setAttribute("accessTypeDetails", accessTypeDetails);
					req.setAttribute("workflows", workflows);
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
				try {
					/* Redirect to Module list page */
					if(!req.getSession().getAttribute("viewID").equals(null)) {
						resp.sendRedirect("project-list.do");
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
}