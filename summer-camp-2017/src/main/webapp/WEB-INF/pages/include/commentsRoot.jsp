<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
													onclick="toggleCommentForm(${comment.id});"
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
																				id="commerName${announcement.id}"
																				value="${commerName}" size="30" /><span
																				class="error"
																				style="color: red; white-space: nowrap;"><b>${commerNameNameError}</b></span>
																			</td>
																		</tr>
																		<tr>
																			<td colspan="2" width=700 align="left"
																				style="font-size: 15pt; color: black;"
																				valign="middle">Comentariu*: <textarea
																					id="commentContent${announcement.id}" rows="5"
																					cols="97">${commentContent}</textarea><span
																				class="error"
																				style="color: red; white-space: nowrap;"><b>${commentContentError}</b></span>
																			</td>
																		</tr>
																		<tr>
																			<td width=500 align="center"><input
																				id="announcementId${announcement.id}" type="hidden"
																				value="${announcement.id}" /> <input
																				id="parentId${announcement.id}" type="hidden"
																				value="${comment.id}" />
																				<button type="button"
																					onclick="submitComment(${comment.id});"
																					style="font-size: 15pt; color: DarkMagenta; background-color: Chartreuse; border-collapse: collapse; border: 1px solid green; width: 500px">&#10009;
																					Publicati</button></td>
																			<td width=200 align="center">
																				<button type="button"
																					onclick="toggleCommentForm(${comment.id});"
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
