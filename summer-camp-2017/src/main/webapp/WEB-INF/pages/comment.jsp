<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
<c:forEach var="comment" items="${children}" varStatus="loop">
	<li>
		${comment.id}
		<c:if test="${not empty comment.children}">			
<!-- 			<ul> -->
<%-- 				<jsp:include page="comments.jsp"> --%>
<%-- 			        <jsp:param name="children" value="${comment.children}"/> --%>
<%-- 			    </jsp:include> --%>
<!-- 			</ul> -->
		</c:if>
	</li>
</c:forEach>
</ul>