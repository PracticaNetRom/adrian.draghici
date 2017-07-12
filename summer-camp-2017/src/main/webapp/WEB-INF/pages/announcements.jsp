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
			<h3 align="center" style="color: DeepPink;">Rezultate: ${from} - ${to} din ${total} anunturi gasite.</h3>
			<div>
				<table bgcolor="PaleTurquoise" align="center" border="0">
					<tr>
						<td width=200>
							<form method="GET">
								<input name="searchBy" type="hidden" value="${searchBy}" /> <input
									name="search" type="hidden" value="${search}" /> <input
									name="selectedCategory" type="hidden"
									value="${selectedCategory}" /> <input name="pageNr"
									type="hidden" value="${pageNr-1}" /> <input type="submit"
									id="backTop"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="&#9668; Pagina anterioara">
							</form>
						</td>
						<td width="500" align="center" style="font-size: 15pt;"><span>Pagina</span>
							<span> <select id="gotoPageTop" name="gotoPageTop"
								style="font-size: 15pt;">
									<c:forEach begin="1" end="${pages}" varStatus="loop">
										<c:choose>
											<c:when test="${loop.index==pageNr}">
												<option value="${loop.index}" selected="selected">${loop.index}</option>
											</c:when>
											<c:otherwise>
												<option value="${loop.index}">${loop.index}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select>
						</span> <span>din ${pages}</span></td>
						<td width=200>
							<form method="GET">
								<input name="searchBy" type="hidden" value="${searchBy}" /> <input
									name="search" type="hidden" value="${search}" /> <input
									name="selectedCategory" type="hidden"
									value="${selectedCategory}" /> <input name="pageNr"
									type="hidden" value="${pageNr+1}" /> <input type="submit"
									id="nextTop"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="Pagina urmatoare &#9658;">
							</form>
						</td>
					</tr>
				</table>
				<br />
				<table bgcolor="Wheat" align="center" border="0">
					<tr>
						<td width="260" style="font-size: 15pt;">Afisati anunturile
							din categoria:</td>
						<td><select id="category" name="category"
							style="font-size: 15pt;">
								<c:choose>
									<c:when test="${selectedCategory=='all'}">
										<option value="all" selected="selected">Toate</option>
									</c:when>
									<c:otherwise>
										<option value="all">Toate</option>
									</c:otherwise>
								</c:choose>
								<c:forEach var="category" items="${categories}" varStatus="loop">
									<c:choose>
										<c:when test="${category.name==selectedCategory}">
											<option value="${category.name}" selected="selected">${category.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${category.name}">${category.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
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
								<form method="get" action="announcement.html" target="_blank">
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
							<form method="GET">
								<input name="searchBy" type="hidden" value="${searchBy}" /> <input
									name="search" type="hidden" value="${search}" /> <input
									name="selectedCategory" type="hidden"
									value="${selectedCategory}" /> <input name="pageNr"
									type="hidden" value="${pageNr-1}" /> <input type="submit"
									id="backBot"
									style="font-size: 20pt; color: black; background-color: Orchid;"
									value="&#9668; Pagina anterioara">
							</form>
						</td>
						<td width="500" align="center" style="font-size: 15pt;">Pagina
							<span> <select id="gotoPageTop" name="gotoPageTop"
								style="font-size: 15pt;">
									<c:forEach begin="1" end="${pages}" varStatus="loop">
										<c:choose>
											<c:when test="${loop.index==pageNr}">
												<option value="${loop.index}" selected="selected">${loop.index}</option>
											</c:when>
											<c:otherwise>
												<option value="${loop.index}">${loop.index}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select>
						</span> <span>din ${pages}</span>
						</td>
						<td width=200>
							<form method="GET">
								<input name="searchBy" type="hidden" value="${searchBy}" /> <input
									name="search" type="hidden" value="${search}" /> <input
									name="selectedCategory" type="hidden"
									value="${selectedCategory}" /> <input name="pageNr"
									type="hidden" value="${pageNr-1}" /><input name="pageNr"
									type="hidden" value="${pageNr-1}" /> <input type="submit"
									id="nextBot"
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
			<c:choose>
				<c:when
					test="${selectedCategory!=null || (searchBy!=null && search!=null)}">
					<br />
				<table bgcolor="Wheat" align="center" border="0">
					<tr>
						<td width="260" style="font-size: 15pt;">Afisati anunturile
							din categoria:</td>
						<td><select id="category" name="category"
							style="font-size: 15pt;">
								<c:choose>
									<c:when test="${selectedCategory=='all'}">
										<option value="all" selected="selected">Toate</option>
									</c:when>
									<c:otherwise>
										<option value="all">Toate</option>
									</c:otherwise>
								</c:choose>
								<c:forEach var="category" items="${categories}" varStatus="loop">
									<c:choose>
										<c:when test="${category.name==selectedCategory}">
											<option value="${category.name}" selected="selected">${category.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${category.name}">${category.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
				</table>
				<br/>
					<h1 align="center">
						&#9785; Cautarea dumneavoastra nu a returnat nici-un rezultat!<br />Va
						asteptam mai tarziu!
					</h1>
				</c:when>
				<c:otherwise>
					<h1 align="center">
						&#9940; Momentan nu sunt anununturi in ziarul online!<br />Va
						asteptam mai tarziu!
					</h1>
				</c:otherwise>
			</c:choose>

		</c:otherwise>
	</c:choose>
</div>
<script>
	jQuery(function($) {
		$("#gotoPageTop, #gotoPageBot")
				.change(
						function() {
							var pageNumber = $(this).val();
							var selectedCategory = "${selectedCategory}";
							var searchBy = "${searchBy}";
							var search = "${search}";
							if (searchBy != null && search != null) {
								if (selectedCategory != null) {
									window.location = "index.html?searchBy="
											+ searchBy + "&search=" + search
											+ "&selectedCategory="
											+ selectedCategory + "&pageNr="
											+ pageNumber;
								} else {
									window.location = "index.html?searchBy="
											+ searchBy + "&search=" + search
											+ "&pageNr=" + pageNumber;
								}
							} else {
								if (selectedCategory != null) {
									window.location = "index.html?selectedCategory="
											+ selectedCategory
											+ "&pageNr="
											+ pageNumber;
								} else {
									window.location = "index.html?pageNr="
											+ pageNumber;
								}
							}
						});
	});
	jQuery(function($) {
		$("#category")
				.change(
						function() {
							var pageNumber = "${pageNumber}";
							var selectedCategory = $(this).val();
							var searchBy = "${searchBy}";
							var search = "${search}";
							if (searchBy != null && search != null) {
								if (selectedCategory != null) {
									window.location = "index.html?searchBy="
											+ searchBy + "&search=" + search
											+ "&selectedCategory="
											+ selectedCategory;
								} else {
									window.location = "index.html?searchBy="
											+ searchBy + "&search=" + search;
								}
							} else {
								if (selectedCategory != null) {
									window.location = "index.html?selectedCategory="
											+ selectedCategory;
								} else {
									window.location = "index.html?pageNr="
											+ pageNumber;
								}
							}
						});
	});
</script>