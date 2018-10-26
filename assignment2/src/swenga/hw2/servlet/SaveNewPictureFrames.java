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
 * Servlet implementation class SaveNewPictureFrames
 */
@WebServlet("/saveNewPictureFrames")
public class SaveNewPictureFrames extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
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

		String productnumberString = request.getParameter("productnumber");
		String productname = request.getParameter("productname");
		String heightString = request.getParameter("height");
		String widthString = request.getParameter("width");
		String format = request.getParameter("format");
		String material = request.getParameter("material");
		String colour = request.getParameter("colour");
		String onlyOnePictureString = request.getParameter("onlyOnePicture");


		
		//Converting datatypes
				String errorMessage = "";
				int productnumber = 0;
				int height = 0;
				int width = 0;
				boolean onlyOnePicture = false;
				
				try {
					productnumber = Integer.parseInt(productnumberString);
				} catch (Exception e) {
						errorMessage += "Productnumber invalid";
				}
				
				try {
					height = Integer.parseInt(heightString);
				} catch (Exception e) {
						errorMessage += "Height invalid";
				}
				
				try {
					width = Integer.parseInt(widthString);
				} catch (Exception e) {
						errorMessage += "Width invalid";
				}
				
				try {
					onlyOnePicture = Boolean.parseBoolean(onlyOnePictureString);
				} catch (Exception e) {
						errorMessage += "Width invalid";
				}
		

		// Data Conversion ok? -> Change Picture Frame
		if ("".equals(errorMessage)) {
			HttpSession session = request.getSession(true);
			PictureFramesManager pictureFramesManager = (PictureFramesManager) session
					.getAttribute("pictureFramesManager");
			PictureFramesModell pictureFramesModell = pictureFramesManager.getPictureFramesByProductNumber(productnumber);

			if (pictureFramesModell != null) {
				errorMessage += "Picture Frame doesn't exist!";
			} else {
				PictureFramesModell pm = new PictureFramesModell(productnumber, productname, height, width, format, material, colour, onlyOnePicture);
				pictureFramesManager.add(pm);
			}
		
		}

		if ("".equals(errorMessage)) {
			request.setAttribute("message", "New Picture Frame" + productnumber + " added.");
		} else {
			request.setAttribute("errorMessage", errorMessage);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/listPictureFrames");
		rd.forward(request, response);
		return;
	}
}
