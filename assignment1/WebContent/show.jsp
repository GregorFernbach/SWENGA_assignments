<!DOCTYPE html>
<%@page import="SWENGA.firstHW.PictureFrames"%>
<%@page import="SWENGA.firstHW.PictureFramesManager"%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
	<p>
		<a href="index.jsp">BACK</a>
	</p>
 
	<table style="width: 100%; padding: 10px;">
		<thead>
			<tr>
				<td><h1>PictureFrames Session</h1></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="border: 1px solid;">
					<%
						PictureFramesManager pfmSession = (PictureFramesManager) session.getAttribute("pfm");
						if (pfmSession == null || pfmSession.isEmpty()) {
							out.println("no data available");
						} else {
							for (PictureFrames pictureFrames : pfmSession.getPictureFrameEntry()) {
								out.println("<h3>" + pictureFrames.getProductname() + "</h3>");
								out.println("<p>" + pictureFrames.getHeight() + "</p>");
								out.println("<p>" + pictureFrames.getWidth() + "</p>");
								out.println("<p>" + pictureFrames.getFormat() + "</p>");
								out.println("<p>" + pictureFrames.getMaterial() + "</p>");
								out.println("<p>" + pictureFrames.getColour() + "</p>");
								out.println("<p>" + pictureFrames.isOnlyOnePicture() + "</p>");
								out.println("<hr>");
							}
						}
					%>
				</td>
			</tr>
		</tbody>
	</table>
 
	<br>
	<hr>
	<br>
 
 
	<table style="width: 100%; padding: 10px;">
		<thead>
			<tr>
				<td><h1>Guestbook ServletContext</h1></td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td style="border: 1px solid;">
					<%
						PictureFramesManager pfmServletContext = (PictureFramesManager) application.getAttribute("pfm");
						if (pfmServletContext == null || pfmServletContext.isEmpty()) {
							out.println("no data available");
						} else {
							for (PictureFrames pictureFrames : pfmServletContext.getPictureFrameEntry()) {
								out.println("<h3>" + pictureFrames.getProductname() + "</h3>");
								out.println("<p>" + pictureFrames.getHeight() + "</p>");
								out.println("<p>" + pictureFrames.getWidth() + "</p>");
								out.println("<p>" + pictureFrames.getFormat() + "</p>");
								out.println("<p>" + pictureFrames.getMaterial() + "</p>");
								out.println("<p>" + pictureFrames.getColour() + "</p>");
								out.println("<p>" + pictureFrames.isOnlyOnePicture() + "</p>");
								out.println("<hr>");
							}
						}
					%>
				</td>
			</tr>
		</tbody>
	</table>
 
	<p>
		<a href="index.jsp">BACK</a>
	</p>
 
 
</body>
</html>
