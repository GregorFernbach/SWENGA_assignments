package swenga.hw2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swenga.hw2.model.PictureFramesModell;
import swenga.hw2.model.PictureFramesManager;

/**
 * Servlet implementation class DeletePictureFrame
 */
@WebServlet("/deletePictureFrame")
public class DeletePictureFrame extends HttpServlet {
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
		String productnumberString = request.getParameter("productnumber");
		
		try {
			//Old version -> we assume that the productname is unique!
			int productnumber = Integer.parseInt(productnumberString);
			
			HttpSession session = request.getSession(true);
			PictureFramesManager pictureFramesManager =(PictureFramesManager)session.getAttribute("pictureFramesManager");

			if (pictureFramesManager!=null) {
				pictureFramesManager.removeByProductNumber(productnumber);
				request.setAttribute("warningMessage", "PictureFrame "+productnumber+" deleted");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/listPictureFrames");
			rd.forward(request, response);

		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

}
