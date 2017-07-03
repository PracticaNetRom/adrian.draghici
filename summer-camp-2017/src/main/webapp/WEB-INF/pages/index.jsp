<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (request.getParameter("submit") != null) {
		switch (request.getParameter("submit")) {
		case "Adauga un anunt":
%><%@include file="include/new.jsp"%>
<%
	break;
		default:
%>
<%@include file="include/home.jsp"%>
<%
	break;
		}
	} else {
%>
<%@include file="include/home.jsp"%>
<%
	}
%>
