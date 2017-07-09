<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>${title}</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>

<body bgcolor="MediumSpringGreen">
	<%@ include file="include/header.jsp"%>
    <div id="includedContent"><jsp:include page="${content}"></jsp:include></div>
	<%@ include file="include/footer.jsp"%>
</body>
</html>