<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${!empty comments && fn:length(comments) gt 0}">
		<table align="left">
			<c:set var="commentsLocal" scope="page" value="${comments}" />
			<c:forEach var="comment" items="${commentsLocal}" varStatus="loop">
				<tr>
					<td>
						<table align="left" border="1" cellspacing="0">
							<tr>
								<td>
									<table align="left" border="1" bgcolor="NavajoWhite">
										<tr>
											<td rowspan="3" width=90 align="center"
												style="font-size: 45pt; color: Lime; background-color: Violet;">&#9993;</td>
											<td colspan="2" width=700 align="center"
												style="font-size: 20pt; color: black; background-color: Khaki;">
												La ${comment.createDate}; ${comment.name} a raspuns:</td>
										</tr>
										<tr>
											<td colspan="2" width=700 align="left"
												style="font-size: 15pt; color: black;"><i>${comment.content}</i></td>
										</tr>
										<tr>
											<td width=500 align="center">
												<button type="button" onclick="toggleDiv(${comment.id});"
													style="font-size: 15pt; color: Yellow; background-color: DimGray; border-collapse: collapse; border: 1px solid green; width: 500px">&#10149;
													${fn:length(comment.children)} raspunsuri</button>
											</td>
											<td width=200 align="center">
												<button type="button" id="replyBtn${comment.id}"
													onclick="toggleCommentReplyForm(${comment.id});"
													style="font-size: 15pt; color: DarkMagenta; background-color: Chartreuse; border-collapse: collapse; border: 1px solid green; width: 200px">&#9997;
													Raspundeti</button>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<div id="divTable${comment.id}" style="display: none">
										<table>
											<tr>
												<td width=90 style="white-space: nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
												<td>
													<table align="center" cellspacing="0">
														<tr>
															<td>
																<div id="div${comment.id}" style="display: none">
																	<c:choose>
																		<c:when
																			test="${!empty comment.children && fn:length(comment.children) gt 0}">
																			<c:set var="comments" scope="session"
																				value="${comment.children}" />
																		</c:when>
																		<c:otherwise>
																			<c:set var="comments" scope="session" value="" />
																		</c:otherwise>
																	</c:choose>
																	<jsp:include page="comments.jsp"></jsp:include>
																</div>
															</td>
														</tr>
														<tr>
															<td><div id="commentForm${comment.id}"
																	style="display: none">
																	<table align="left" border="1" bgcolor="NavajoWhite">
																		<tr>
																			<td rowspan="3" width=90 align="center"
																				style="font-size: 45pt; color: Lime; background-color: Violet;">&#9998;</td>
																			<td colspan="2" width=700 align="center"
																				valign="middle"
																				style="font-size: 20pt; color: black; background-color: Khaki;">
																				Numele dumneavoastra*: <input type="text"
																				id="commerName${comment.id}" value="${commerName}"
																				size="30" /><span class="error"
																				style="font-size: 15pt; color: red; white-space: nowrap;"><b>${commerNameError}</b></span>
																			</td>
																		</tr>
																		<tr>
																			<td colspan="2" width=700 align="center"
																				style="font-size: 15pt; color: black;"
																				valign="middle">Comentariu*: <textarea
																					id="commentContent${comment.id}" rows="5" cols="97">${commentContent}</textarea><span
																				class="error"
																				style="font-size: 15pt; color: red; white-space: nowrap;"><b>${commentContentError}</b></span>
																			</td>
																		</tr>
																		<tr>
																			<td width=500 align="center"><input
																				id="announcementId${comment.id}" type="hidden"
																				value="${announcementId}" /> <input
																				id="parentId${comment.id}" type="hidden"
																				value="${comment.id}" />
																				<button type="button"
																					onclick="submitReply(${comment.id});"
																					style="font-size: 15pt; color: DarkMagenta; background-color: Chartreuse; border-collapse: collapse; border: 1px solid green; width: 500px">&#10009;
																					Publicati</button></td>
																			<td width=200 align="center">
																				<button type="button"
																					onclick="toggleCommentReplyForm(${comment.id});"
																					style="font-size: 15pt; color: DarkMagenta; background-color: Red; border-collapse: collapse; border: 1px solid green; width: 200px">&#10006;
																					Renuntati</button>
																			</td>
																		</tr>
																	</table>
																</div></td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
<script>
	function toggleCommentReplyForm(targetId){
		var divTable= document.getElementById('divTable'+targetId);
		var div = document.getElementById('div'+targetId);
		var commentForm = document.getElementById('commentForm'+targetId);
		var replyBtn = document.getElementById('replyBtn'+targetId);
		if (divTable.style.display === 'none') {
			divTable.style.display = 'block';
			if (commentForm.style.display === 'none') {
				commentForm.style.display = 'block';
				replyBtn.style.background='Red';
				replyBtn.innerHTML='\u2716 Renuntati';
			}
			location.href = "#commentForm"+targetId;
		}else {
			if (commentForm.style.display === 'none') {
				commentForm.style.display = 'block';
				replyBtn.style.background='Red';
				replyBtn.innerHTML='\u2716 Renuntati';
			}else{
				commentForm.style.display = 'none';
				replyBtn.style.background='Chartreuse';
				replyBtn.innerHTML='\u270D Raspundeti';
			}
			if (div.style.display === 'none') {
				divTable.style.display = 'none';
			}
		}
	}

	function toggleDiv(targetId) {
		var divTable= document.getElementById('divTable'+targetId);
		var div = document.getElementById('div'+targetId);
		var commentForm = document.getElementById('commentForm'+targetId);
		if (divTable.style.display === 'none') {
			divTable.style.display = 'block';
			if (div.style.display === 'none') {
				div.style.display = 'block';
			}
		}else {
			if (div.style.display === 'none') {
				div.style.display = 'block';
			}else{
				div.style.display = 'none';
			}
			if (commentForm.style.display === 'none') {
				divTable.style.display = 'none';
			}
		}
	}
	
	function submitReply(targetId){
		var announcementId=document.getElementById('announcementId'+targetId).value;
		var parentId=document.getElementById('parentId'+targetId).value;
		var commerName=document.getElementById('commerName'+targetId).value;
		var commentContent=document.getElementById('commentContent'+targetId).value;
		$.ajax({
			url : 'comments.html',
			type : 'POST',
			data : {
				showComments: true,
				reply: true,
				announcementId : announcementId,
				parentId: parentId,
				commerName : commerName,
				commentContent: commentContent
			},
			success : function(result) {
				$("#commentsRoot").html(result);
				checkReplyForm(targetId);
			},
			error: function(){
				alert("Comentariul nu a putut fi postat!");
			}
		});
	}
	
	function checkReplyForm(targetId){
		if(${succes!=null}){
			if(${succes=='true'}){
				alert("Comentariul a fost postat cu succes!");
			}else{
				alert("Comentariul a fost invalidat!\nVa rugam corectati!");
				toggleCommentReplyForm();
			}
		}
	}
</script>