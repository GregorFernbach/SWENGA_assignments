package SWENGA.firstHW;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PictureFramesServlet
 */
@WebServlet("/addEntry")
public class PictureFramesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PictureFramesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
		// -------------------------------------------------
		// get the Session for the user
		// and
		// get the ServletContext
		// -------------------------------------------------
 
		HttpSession session = request.getSession();
		ServletContext servletContext = getServletContext();
 
		// -------------------------------------------------
		// get data out of request
		// -------------------------------------------------
		String productname = request.getParameter("productname");
		int height = Integer.parseInt(request.getParameter("height"));
		int width = Integer.parseInt(request.getParameter("width"));
		String format = request.getParameter("format");
		String material= request.getParameter("material");
		String colour = request.getParameter("text");
		Boolean onlyOnePicture = Boolean.parseBoolean(request.getParameter("onlyOnePicture"));
		String scope = request.getParameter("scope");
		
		try {
		// -------------------------------------------------
		// Store data in a new PictureFrames object	
		// -------------------------------------------------
		PictureFrames pictureFrames = new PictureFrames(productname, height, width, format, material, colour, onlyOnePicture);
 
		// -------------------------------------------------
		// Try to find a GuestBook Manager
		// Such object could be in the Session or in the ServletContext
		// choose the right one based on users choice
		// -------------------------------------------------
 
		PictureFramesManager pfm = null;
 
		if (scope.equals("session")) {
			pfm=(PictureFramesManager)session.getAttribute("pfm");			
		} else {
			pfm=(PictureFramesManager)servletContext.getAttribute("pfm");			
		}
 
		// -------------------------------------------------
		// Hm, no GuestbookManager in the desired scope (session/servletContext) available???
		// So it must be the first time -> we have to create one
		// -------------------------------------------------
		if (pfm==null) {
			pfm = new PictureFramesManager();
 
			// -------------------------------------------------
			// OK, now we have a new GuestBookManager, store it for later use
			// -------------------------------------------------
			if (scope.equals("session")) {
				session.setAttribute("pfm",pfm);			
			} else {
				servletContext.setAttribute("pfm",pfm);			
			}
		}
 
		// -------------------------------------------------
		// add the new GuestBook entry to the other entries
		// GuestBookManager knows how to do it
		// -------------------------------------------------
 
		pfm.add(pictureFrames);
 
		// -------------------------------------------------
		// We are done, "forward" to the web page to display 
		// the content of both PictureFrames 
		// - the one in the session and  
		// - the one in the servlet context
		// -------------------------------------------------
 
		RequestDispatcher rd = request.getRequestDispatcher("/show.jsp");
		rd.forward(request, response);
		return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "There was something wrong "+e.getMessage());
			RequestDispatcher rq = request.getRequestDispatcher("/error.jsp");
			rq.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

