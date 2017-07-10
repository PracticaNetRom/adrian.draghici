<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div id="announcementsList">
	<c:choose>
		<c:when
			test="${!empty announcements && fn:length(announcements) gt 0}">
			<h1 align="center">Anunturi</h1>
			<h2 align="center">Pagina ${pageNr}</h2>
			<div>
				<table bgcolor="PaleTurquoise" align="center" border="0">
					<tr>
						<td width=200>
							<form method="post">
								<input name="mode" type="hidden" value="back" /> <input
									type="submit" id="backTop"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="&#9668; Pagina anterioara">
							</form>
						</td>
						<td width="500" align="center" style="font-size: 15pt;">Pagina

							<select id="gotoPageTop" name="gotoPageTop">
								<c:forEach begin="1" end="${pages}" varStatus="loop">
									<c:choose>
										<c:when test="${loop.index==pageNr}">
											<form method="post" id="pageChooserTop" name="pageChooserTop">
												<input name="pageNrTop" id="pageNrTop" type="hidden"
													value="${loop.index}" />
											</form>
											<option value="${loop.index}" selected="selected">${loop.index}</option>
										</c:when>
										<c:otherwise>
											<option value="${loop.index}">${loop.index}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select> din ${pages}
						</td>
						<td width=200>
							<form method="post">
								<input name="mode" type="hidden" value="next" /> <input
									type="submit" id="nextTop"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="Pagina urmatoare &#9658;">
							</form>
						</td>
					</tr>
				</table>
			</div>
			<br />
			<br />
			<c:forEach var="announcement" items="${announcements}"
				varStatus="loop">
				<div>
					<table align="center" border="1" bgcolor="PapayaWhip">
						<tr>
							<td align="center" colspan="2" bgColor="Yellow">Anunt Nr:
								#${announcement.id}</td>
						</tr>
						<tr>
							<td width=200>Stare Anunt:</td>
							<c:choose>
								<c:when test="${announcement.available==true}">
									<td width=200 align="center" bgColor="Lime">Valabil</td>
								</c:when>
								<c:otherwise>
									<td width=200 align="center" bgColor="Red">Expirat</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<td width=200>Titlu Anunt:</td>
							<td width=200>${announcement.title}</td>
						</tr>
						<tr>
							<td width=200>Data publicare Anunt:</td>
							<td width=200>${announcement.createDate}</td>
						</tr>
						<tr>
							<td width=200>Categorie Anunt:</td>
							<td width=200>${announcement.categoryName},
								${announcement.categoryDescription}</td>
						</tr>
						<tr>
							<td width=200>Publicat de:</td>
							<td width=200>${announcement.ownerFirstName}&nbsp;${announcement.ownerLastName}</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<form method="get" action="announcement.html">
									<input name="announcementId" type="hidden"
										value="${announcement.id}" /> <input type="submit"
										style="font-size: 20pt; color: black; background-color: Cyan; width: 700px"
										value="&#9971; Deschideti Anuntul">
								</form>
							</td>
						</tr>
					</table>
					<br /> <br />
				</div>
			</c:forEach>
			<div>
				<table bgcolor="PaleTurquoise" align="center" border="0">
					<tr>
						<td width=200>
							<form method="post">
								<input name="mode" type="hidden" value="back" /> <input
									type="submit" id="backBot"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="&#9668; Pagina anterioara">
							</form>
						</td>
						<td width="500" align="center" style="font-size: 15pt;">Pagina
							<form method="post" id="pageChooserBot" name="pageChooserBot">
								<select style="width: 30px; height: 30" id="gotoPageBot"
									name="gotoPageBot">
									<c:forEach begin="1" end="${pages}" varStatus="loop">
										<c:choose>
											<c:when test="${loop.index==pageNr}">
												<option value="${page}" selected="selected">${loop.index}</option>
												<input name="pageNrBot" id="pageNrBot" type="hidden"
													value="${loop.index}" />
											</c:when>
											<c:otherwise>
												<option value="${loop.index}">${loop.index}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</form> din ${pages}
						</td>
						<td width=200>
							<form method="post">
								<input name="mode" type="hidden" value="next" /> <input
									type="submit" id="nextBot"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="Pagina urmatoare &#9658;">
							</form>
						</td>
					</tr>
				</table>
			</div>
			<c:choose>
				<c:when test="${pageNr<=1}">
					<script>
						document.getElementById("backTop").disabled = true;
						document.getElementById("backBot").disabled = true;
					</script>
				</c:when>
				<c:otherwise>
					<script>
						document.getElementById("backTop").disabled = false;
						document.getElementById("backBot").disabled = false;
					</script>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pageNr>=pages}">
					<script>
						document.getElementById("nextTop").disabled = true;
						document.getElementById("nextBot").disabled = true;
					</script>
				</c:when>
				<c:otherwise>
					<script>
						document.getElementById("nextTop").disabled = false;
						document.getElementById("nextBot").disabled = false;
					</script>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h1 align="center">
				&#9940; Momentan nu sunt anununturi in ziarul online!<br />Va
				asteptam mai tarziu!
			</h1>
		</c:otherwise>
	</c:choose>
</div>
<script>
	jQuery(function($) {
		var gotoPage = $("#gotoPageTop").val();
		$("#gotoPageTop").change(function() {
			$("#pageNrTop").name = "pageNr";
			$("#pageNrTop").id = "pageNr";
			$('pageChooserTop').submit();
		});
	});
	jQuery(function($) {
		var gotoPage = $("#gotoPageBot").val();
		$("#gotoPageBot").change(function() {
			$("#pageNrBot").name = "pageNr";
			$("#pageNrBot").id = "pageNr";
			$('pageChooserBot').submit();
		});
	});
</script>