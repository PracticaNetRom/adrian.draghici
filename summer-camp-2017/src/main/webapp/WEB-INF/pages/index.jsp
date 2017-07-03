<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body bgcolor="MediumSpringGreen">
	<%@ include file="include/header.jsp"%>
	<h1 align="center">Anunturi</h1>
	<div id="home">
		<c:forEach var="announcement" items="${announcements}"
			varStatus="loop">
			<div>
			<table align="center">
				<tr>
					<td width=100>
						Titlu Anunt:
					</td>
					<td width=100>
						${announcement.title}
					</td>
				</tr>
			</table>
			
			</div>
		</c:forEach>
	</div>
	<%@ include file="include/footer.jsp"%>
</body>
</html>