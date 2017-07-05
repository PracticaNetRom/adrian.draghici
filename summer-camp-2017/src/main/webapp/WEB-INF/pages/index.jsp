<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>${title}</title>
</head>
<body bgcolor="MediumSpringGreen">
	<%@ include file="include/header.jsp"%>
	<jsp:include page="${content}"/>
    <div id="includedContent"></div>
	<%@ include file="include/footer.jsp"%>
</body>
</html>