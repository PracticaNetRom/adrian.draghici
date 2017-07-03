<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>
	<%
		out.print(session.getAttribute("title"));
	%>
</title>
</head>
<body bgcolor="MediumSpringGreen">
<table bgcolor="PaleTurquoise" align="center" border="0">
	<tr>
		<td>
			<img style="display:block;" src="img/annc.png" alt="" border=0 height="50" width="50"></img>
		</td>
		<td style="font-size: 20pt;">ZIARUL ONLINE by Ady</td>
		<td width="300">
		</td>
		<td valign="middle">
			<form method="post">
				<input type="submit"
					style="font-size: 20pt; color: black; background-color: LightSeaGreen;"
					value="Anunturi" name="submit">
			</form>
		</td>
		<td valign="middle">
			<form method="post">
				<input type="submit"
					style="font-size: 20pt; color: black; background-color: Plum;"
					value="Adauga un anunt" name="submit">
			</form>
		</td>
	</tr>
</table>