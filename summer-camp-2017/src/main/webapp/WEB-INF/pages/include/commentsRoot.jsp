<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<br />
<div align="Center">
	<button type="button" id="commentBtn" onclick="toggleCommentForm();"
		style="font-size: 15pt; color: DarkMagenta; background-color: Chartreuse; border-collapse: collapse; border: 1px solid green; width: 200px">&#9997;
		Comentati</button>
</div>
<c:choose>
	<c:when test="${!empty commentsRoot && fn:length(commentsRoot) gt 0}">
		<h3 align="center">Comentarii anunt</h3>
		<table align="center" border="0" cellspacing="0">
			<c:forEach var="comment" items="${commentsRoot}" varStatus="loop">
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
												La ${comment.createDate}; ${comment.name} a spus:</td>
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
																				id="commerName${comment.id}"
																				value="${commerName}" size="30" /><span
																				class="error"
																				style="color: red; white-space: nowrap;"><b>${commerNameError}</b></span>
																			</td>
																		</tr>
																		<tr>
																			<td colspan="2" width=700 align="left"
																				style="font-size: 15pt; color: black;"
																				valign="middle">Comentariu*: <textarea
																					id="commentContent${comment.id}" rows="5"
																					cols="97">${commentContent}</textarea><span
																				class="error"
																				style="color: red; white-space: nowrap;"><b>${commentContentError}</b></span>
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
				<tr>
					<td><br /></td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		<h3 align="center">&#9940; Momentan nu sunt comentarii.</h3>
	</c:otherwise>
</c:choose>
<div id="commentForm" style="display: none">
	<table align="center" cellspacing="0">
		<tr>
			<td><div>
					<table align="left" border="1" bgcolor="NavajoWhite">
						<tr>
							<td rowspan="3" width=90 align="center"
								style="font-size: 45pt; color: Lime; background-color: Violet;">&#9998;</td>
							<td colspan="2" width=700 align="center" valign="middle"
								style="font-size: 20pt; color: black; background-color: Khaki;">
								Numele dumneavoastra*: <input type="text" id="commerName" name="commerName"
								value="${commerName}" size="30" /><span class="error"
								style="font-size: 15pt; color: red; white-space: nowrap;"><b>${commerNameError}</b></span>
							</td>
						</tr>
						<tr>
							<td colspan="2" width=700 align="center"
								style="font-size: 15pt; color: black;" valign="middle">Comentariu*:
								<textarea id="commentContent" name="commentContent" rows="5" cols="97">${commentContent}</textarea><span
								class="error" style="font-size: 15pt; color: red; white-space: nowrap;"><b>${commentContentError}</b></span>
							</td>
						</tr>
						<tr>
							<td width=500 align="center"><input id="announcementId" name="announcementId"
								type="hidden" value="${announcementId}" />
								<button type="button" onclick="submitComment();"
									style="font-size: 15pt; color: DarkMagenta; background-color: Chartreuse; border-collapse: collapse; border: 1px solid green; width: 500px">&#10009;
									Publicati</button></td>
							<td width=200 align="center">
								<button type="button" onclick="toggleCommentForm();"
									style="font-size: 15pt; color: DarkMagenta; background-color: Red; border-collapse: collapse; border: 1px solid green; width: 200px">&#10006;
									Renuntati</button>
							</td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</div>
<script>
	function toggleCommentForm(){
		var commentForm = document.getElementById('commentForm');
		var commentBtn = document.getElementById('commentBtn');
		if (commentForm.style.display === 'none') {
			commentForm.style.display = 'block';
			commentBtn.style.background='Red';
			commentBtn.innerHTML='\u2716 Renuntati';
			location.href = "#commentForm";
		}else{
			commentForm.style.display = 'none';
			commentBtn.style.background='Chartreuse';
			commentBtn.innerHTML='\u270D Comentati';
			location.href = "#";
		}
	}
	
	function send(announcementId,commerName,commentContent){
		
	}
	
	function submitComment(){
		var announcementId=document.getElementById('announcementId').value;
		var commerName=document.getElementById('commerName').value;
		var commentContent=document.getElementById('commentContent').value;
		$.ajax({
			url : 'comments.html',
			type : 'POST',
			data : {
				showComments: true,
				reply: false,
				announcementId : announcementId,
				commerName : commerName,
				commentContent: commentContent
			},
			success : function(result) {
				$("#commentsRoot").html(result);
				checkCommentForm();
			},
			error: function(){
				alert("Comentariul nu a putut fi postat!");
			}
		});
	}
	
	function checkCommentForm(){
		if(${succes!=null}){
			if(${succes=='true'}){
				alert("Comentariul a fost postat cu succes!");
			}else{
				alert("Comentariul a fost invalidat!\nVa rugam corectati!");
				toggleCommentForm();
			}
		}
	}
</script>