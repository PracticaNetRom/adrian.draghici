<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${announcementId!=null}">
	<h1 align="center">Anunt Nr #${announcementId}</h1>
</c:if>
<c:choose>
	<c:when test="${status==true}">
		<h2 align="center" style="font-size: 20pt; color: Green;">${result}<br />
		</h2>
		<div align="center" style="font-size: 60pt; color: Green;">&#10004;</div>
	</c:when>
	<c:otherwise>
		<h2 align="center" style="font-size: 20pt; color: Red;">${result}<br />
		</h2>
		<div align="center" style="font-size: 60pt; color: Red;">&#10006;</div>
	</c:otherwise>
</c:choose>