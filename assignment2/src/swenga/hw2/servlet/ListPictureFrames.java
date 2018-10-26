package swenga.hw2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.WebContext;

import swenga.hw2.model.PictureFramesManager;
import swenga.hw2.model.PictureFramesModell;

/**
 * Servlet implementation class ListPictureFrames
 */
@WebServlet("/")
public class ListPictureFrames extends ThymeleafServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		PictureFramesManager pictureFramesManager =(PictureFramesManager)session.getAttribute("pictureFramesManager");
		
		if (pictureFramesManager==null) {
			pictureFramesManager = new PictureFramesManager();
			session.setAttribute("pictureFramesManager", pictureFramesManager);
		}
		
		request.setAttribute("pictureFramesEntries", pictureFramesManager.getPictureFrameEntries());

        WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
        String templateName = "/listPictureFrames.html";
        
        request.setAttribute("active", templateName);
        
        String result = engine.process(templateName, ctx);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(result);
        } finally {
            out.close();
        }
	}

}
