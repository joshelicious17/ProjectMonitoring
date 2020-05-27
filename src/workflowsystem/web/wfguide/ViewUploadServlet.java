package workflowsystem.web.wfguide;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import workflowsystem.data.wfguide.TaskDocument;
import workflowsystem.data.wfguide.TaskDocumentDAO;

public class ViewUploadServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(this.getClass());
	private RequestDispatcher jsp;
	private ServletContext context;

	/**
	 * Initialize Servlet and invoke JSP page
	 * 
	 * @param config			Servlet Configuration 			
	 * @throws ServletException	Servlet Exception
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.debug("init()");
		context = config.getServletContext();
		/* Get the file location where it would be stored. */
		jsp = context.getRequestDispatcher("/WEB-INF/jsp/wfguide/taskdocument-view.jsp");
	}

	/**
	 * Intercept HTTP GET request and process request to access
	 * the uploaded Task Document File
	 * 
	 * @param req				HTTP Servlet Request
	 * @param resp				HTTP Servlet Response		
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	protected void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("processRequest()");

		String idString = req.getParameter("taskDocumentId");
		logger.debug("ViewUpload taskDocumentId = " + idString);
		if (idString == null || idString.length() <= 0) {
			logger.debug("No file.");
			return;
		}

		Long id = new Long(idString);
		TaskDocument taskDocument = new TaskDocumentDAO().find(id);

		/* Check if there's a file to view */
		String filename = taskDocument.getTaskDocumentPath();
		String mimetype = context.getMimeType(filename);
		logger.debug("filename = " + filename);
		resp.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

		try {
			/* Retrieve file length */
			int fileLength = new TaskDocumentDAO().getFileLength(id);
			if (fileLength <= 0) {
				logger.debug("ViewUpload No blob data");
				/* Redirect back to Task Document View screen */
				resp.sendRedirect("taskdocument-view.do?taskDocumentId=" + idString);
				return;
			}
			logger.debug("ViewUpload fileLength = " + fileLength);
			resp.setContentLength(fileLength);

			/* Retrieve Document File */
			Blob b = new TaskDocumentDAO().getFile(id);
			InputStream inStream = b.getBinaryStream();
			OutputStream outStream = resp.getOutputStream();
			byte buf[] = new byte[fileLength];
			inStream.read(buf);
			outStream.write(buf);
			outStream.close();
		} catch (Exception ex) {
			logger.debug("ViewUpload error");
			logger.debug(ex.getMessage());
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Intercept HTTP GET request
	 * 
	 * @param req				HTTP Servlet Request
	 * @param resp				HTTP Servlet Response		
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doGet()");
		processRequest(req, resp);
	}

	/**
	 * Intercept HTTP POST request
	 * 
	 * @param req				HTTP Servlet Request 			
	 * @param resp				HTTP Servlet Response
	 * @throws ServletException	Servlet Exception
	 * @throws IOException		Input/Output Exception
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("doPost()");
		processRequest(req, resp);
	}
}
