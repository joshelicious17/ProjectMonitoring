package workflowsystem.web;

import java.io.IOException;
import java.util.List;

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

public class AccessDeniedServlet extends HttpServlet {

	private Logger logger = Logger.getLogger(this.getClass());
	private RequestDispatcher jsp;

	/**
	 * Initialize Servlet and invoke JSP page
	 * 
	 * @param config			Servlet Configuration 			
	 * @throws ServletException	Servlet Exception
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/access-denied.jsp");
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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("access denied page requested");
		
		HttpSession session = req.getSession();
		List<AccessTypeDetail> accessTypeDetails = (List<AccessTypeDetail>) session.getAttribute("accessTypeDetails");
		List<Module> modules = (List<Module>) session.getAttribute("modules");
		
		req.setAttribute("modules", modules);
		req.setAttribute("accessTypeDetails", accessTypeDetails);
		jsp.forward(req, resp);
	}
}