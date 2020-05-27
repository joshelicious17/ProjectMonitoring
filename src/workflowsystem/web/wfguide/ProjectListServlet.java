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

public class ProjectListServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/project-list.jsp");
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

		/* Get current session data */
		HttpSession session = req.getSession();
		List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
		List<Module> modules = (List<Module>) session.getAttribute("modules");		
		Map<String, Boolean> accessInfo = (Map<String, Boolean>) session.getAttribute("accessInfo");
		try {
			if (accessInfo.get("project-list-page")) {
				/* Retrieve all Project information from the Database */
				List<Project> projects = new ProjectDAO().findAll();

				req.setAttribute("modules", modules);
				req.setAttribute("accessTypeDetails", accessTypeDetails);
				req.setAttribute("projects", projects);
				req.setAttribute("accessInfo", accessInfo);
				jsp.forward(req, resp);
			} else {
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
}