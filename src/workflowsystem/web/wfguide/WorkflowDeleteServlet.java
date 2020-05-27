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

import workflowsystem.data.wfguide.Task;
import workflowsystem.data.wfguide.TaskAssignedUser;
import workflowsystem.data.wfguide.TaskAssignedUserDAO;
import workflowsystem.data.wfguide.TaskCheckpoint;
import workflowsystem.data.wfguide.TaskCheckpointDAO;
import workflowsystem.data.wfguide.TaskDAO;
import workflowsystem.data.wfguide.TaskDetailedTask;
import workflowsystem.data.wfguide.TaskDetailedTaskDAO;
import workflowsystem.data.wfguide.TaskDocument;
import workflowsystem.data.wfguide.TaskDocumentDAO;
import workflowsystem.data.wfguide.TaskPrereq;
import workflowsystem.data.wfguide.TaskPrereqDAO;
import workflowsystem.data.wfguide.Workflow;
import workflowsystem.data.wfguide.WorkflowDAO;
import workflowsystem.util.WfUtilCommon;

public class WorkflowDeleteServlet extends HttpServlet {
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
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/delete-item.jsp");
	}

	/**
	 * Intercept HTTP GET request and performs deletion
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
		Map<String, Boolean> accessInfo = (Map<String, Boolean>) session.getAttribute("accessInfo");
		
		if (accessInfo.get("project-view-page")) {
			/* Retrieve Workflow ID */
			String idString = req.getParameter("workflowId");

			/* Initialize Workflow object and retrieve Workflow to be deleted */
			Workflow workflow = new WorkflowDAO().find(new Long(idString));
			logger.debug("Found workflow " +workflow+ " id.");
			
			/* Retrieve Task Checkpoint List from the Database */
			List<TaskCheckpoint> taskCheckpoints = new TaskCheckpointDAO().findByWorkflowId(new Long (idString));
			/* Perform Task Checkpoint(s) Deletion */
			for (TaskCheckpoint taskCheckpoint : taskCheckpoints) {
				new TaskCheckpointDAO().delete(taskCheckpoint);
				logger.debug("taskCheckpoint successfully deleted.");
			}
			
			/* Retrieve Task Document List from the Database */
			List<TaskDocument> taskDocuments = new TaskDocumentDAO().findAllByWorkflowId(new Long(idString));
			/* Perform Task Document(s) Deletion */
			for (TaskDocument taskDocument : taskDocuments) {
				new TaskDocumentDAO().delete(taskDocument);
				logger.debug("taskDocument successfully deleted.");
			}
			
			/* Retrieve Task Detailed Task List from the Database */
			List<TaskDetailedTask> taskDetailedTasks = new TaskDetailedTaskDAO().findByWorkflowId(new Long(idString));
			/* Perform Task Detailed Task(s) Deletion */
			for (TaskDetailedTask taskDetailedTask : taskDetailedTasks) {
				new TaskDetailedTaskDAO().delete(taskDetailedTask);
				logger.debug("taskDetailedTask successfully deleted.");
			}
			
			/* Retrieve Task Assigned User List from the Database */
			List<TaskAssignedUser> taskAssignedUsers = new TaskAssignedUserDAO().findByWorkflowId(new Long(idString));
			/* Perform Task Assigned User(s) Deletion */
			for (TaskAssignedUser taskAssignedUser : taskAssignedUsers) {
				new TaskAssignedUserDAO().delete(taskAssignedUser);
				logger.debug("taskAssignedUser successfully deleted.");
			}
			
			/* Retrieve Prerequisite Task List from the Database */
			List<TaskPrereq> taskPrereqs = new TaskPrereqDAO().findAllByWorkflowId(new Long(idString));
			/* Perform Prerequisite Task(s) Deletion */
			for (TaskPrereq prereq : taskPrereqs) {
				new TaskPrereqDAO().delete(prereq);
				logger.debug("taskPrereq successfully deleted.");
			}
			
			/* Retrieve Task List from the Database */
			List<Task> tasks = new TaskDAO().findAll(new Long (idString));
			/* Perform Prerequisite Task(s) Deletion */
			for (Task task : tasks) {
				new TaskDAO().delete(task);
				logger.debug("task successfully deleted.");
			}
			/* Perform Deletion in Relation */
			WfUtilCommon.deleteRelation(new Long (idString));
			/* Perform Deletion */
			new WorkflowDAO().delete(workflow);
			logger.debug("workflow " +idString + " successfully deleted.");

			/* Return to Project View page */
			resp.sendRedirect("project-view.do?projectId=" + workflow.getProjectId());
			logger.info("project-view redirected.");
		}
		else {
			/* Access denied page */
			logger.info("access denied.");
			resp.sendRedirect("access-denied.do");
		}

	}
}