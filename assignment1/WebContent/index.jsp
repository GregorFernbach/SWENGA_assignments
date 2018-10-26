<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create a new PictureFrame</title>
</head>
<body>
 
	<form method="post" action="addEntry">
 
		<table>
			<tr>
				<td>ProductName:</td>
				<td><input type="text" name="productname"></td>
			</tr>
			<tr>
				<td>Height:</td>
				<td><input type="text" name="height"></td>
			</tr>
			<tr>
				<td>Width:</td>
				<td><input type="text" name="width"></td>
			</tr>
			<tr>
				<td>Format:</td>
				<td><input type="text" name="format"></td>
			</tr>
 			<tr>
				<td>Material:</td>
				<td><input type="text" name="material"></td>
			</tr>
			<tr>
				<td>Colour:</td>
				<td><input type="text" name="colour"></td>
			</tr>
			<tr>
				<td>OnlyOnePicture:</td>
				<td><input type="checkbox" name="onlyOnePicture"></td>
			</tr>
						<tr>
				<td>Scope:</td>
				<td><select name="scope">
						<option value="session">Session</option>
						<option value="servletContext">ServletContext</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit"
					value="Submit"></td>
			</tr>
 
		</table>
 
	</form>
 
</body>
</html>