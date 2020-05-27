package workflowsystem.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import workflowsystem.data.maincontrol.UserDAO;

public class SecurityFilter implements Filter {
	private Logger logger = Logger.getLogger(this.getClass());

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		logger.debug("doFilter()");
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();

		/* Allow access to login functionality. */
		if (servletPath.equals("/login.do")) {
			chain.doFilter(req, resp);
			return;
		}

		/* Allow access to Forget Password page. */
		if (servletPath.equals("/forget-password.do")) {
			chain.doFilter(req, resp);
			return;
		}

		/* Allow access to news feed. */
		if (servletPath.equals("/news.rss")) {
			chain.doFilter(req, resp);
			return;
		}

		/* Allow access to resource. */
		if (servletPath.equals("/resource")) {
			chain.doFilter(req, resp);
			return;
		}

		/* Initialize and retrieve HTTP Session */
		HttpSession session = req.getSession();

		/* Request Success page access check */
		String userEmail = (String) session.getAttribute("userEmail");
		if (userEmail != null) {
			chain.doFilter(req, resp);
			return;
		}

		/* Reset Password page access check */
		if(servletPath.equals("/reset-password.do")) {
			String resetPasswordKey = req.getParameter("rpk");
			new UserDAO();
			Boolean isValid = UserDAO.isValidResetPasswordKey(resetPasswordKey);
			if (isValid) {
				chain.doFilter(req, resp);
				return;
			}
		}

		/* All other functionality requires authentication. */
		Long userId = (Long) session.getAttribute("userId");
		if (userId != null) {
			resp.setHeader("Pragma", "no-cache");
			resp.setHeader("Cache-Control", "no-cache");
			resp.setDateHeader("Expires", 0);
			/* User is logged in. */
			chain.doFilter(req, resp);
			return;
		}

		/* Request is not authorized. */
		resp.sendRedirect("login.do");
	}
}