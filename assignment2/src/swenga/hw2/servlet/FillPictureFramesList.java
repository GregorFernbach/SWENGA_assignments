package swenga.hw2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swenga.hw2.model.PictureFramesManager;
import swenga.hw2.model.PictureFramesModell;

/**
 * Servlet implementation class FillPictureFramesList
 */
@WebServlet("/fillPictureFramesList")
public class FillPictureFramesList extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		PictureFramesManager pictureFramesManager = (PictureFramesManager) session.getAttribute("pictureFramesManager");

		if (pictureFramesManager == null) {
			pictureFramesManager = new PictureFramesManager();
			session.setAttribute("pictureFramesManager", pictureFramesManager);
		}

		pictureFramesManager.addDummyPictureFrames();

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/listPictureFrames");
		rd.forward(request, response);
	}
}
