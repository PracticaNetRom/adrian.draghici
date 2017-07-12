<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table bgcolor="PaleTurquoise" align="center" cellspacing="0">
	<tr>
		<td><img style="display: block;" src="img/annc.png" alt=""
			border=0 height="50" width="50"></img></td>
		<td style="font-size: 20pt; white-space: nowrap;" width="300">ZIARUL
			ONLINE by Ady</td>
		<td width="300"></td>
		<td valign="middle" style="white-space: nowrap;" width="150"><a
			href="/index.html"
			style="font-size: 20pt; color: black; background-color: LightSeaGreen; text-decoration: none; border-collapse: collapse; border: 1px solid green;">&#9873;Anunturi</a>
		</td>
		<td valign="middle" style="white-space: nowrap;" width="200"><a
			href="/add.html"
			style="font-size: 20pt; color: black; background-color: Plum; text-decoration: none; border-collapse: collapse; border: 1px solid green;">&#10010;Adauga
				un anunt</a>&nbsp;&nbsp;&nbsp;</td>
	</tr>
</table>
<br />
<br />
<table id="searchBar" bgcolor="RoyalBlue" align="center"
	cellspacing="20">
	<tr>
		<td align="left"
			style="font-size: 20pt; white-space: nowrap; color: PapayaWhip;"
			width="250">Cautati anunturi in ziar:</td>
		<td align="left"
			style="font-size: 20pt; white-space: nowrap; color: Plum;"
			width="150"><span> <select id="criteria" name="criteria"
				style="font-size: 20pt;">
					<c:choose>
						<c:when test="${searchBy==1}">
							<option value="1" selected="selected">Adresa</option>
						</c:when>
						<c:otherwise>
							<option value="1">Adresa</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${searchBy==2}">
							<option value="2" selected="selected">Continut</option>
						</c:when>
						<c:otherwise>
							<option value="2">Continut</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${searchBy==3}">
							<option value="3" selected="selected">Proprietar</option>
						</c:when>
						<c:otherwise>
							<option value="3">Proprietar</option>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${searchBy==4}">
							<option value="4" selected="selected">Titlu</option>
						</c:when>
						<c:otherwise>
							<option value="4">Titlu</option>
						</c:otherwise>
					</c:choose>
			</select>
		</span></td>
		<td align="left" valign="middle" width="550">
			<div>
				<form method="GET" action="/index.html">
					<input name="searchBy" id="searchBy" type="hidden" value="${searchBy}" /> <input
						name="search" type="text" style="font-size: 20pt; width: 400"
						value="${search}" /> <span>&nbsp;&nbsp; <input type="submit"
						id="searchNow"
						style="font-size: 20pt; color: DarkSlateGrey; background-color: LawnGreen; width: 200"
						value="&nbsp;&nbsp;&#9758; Cautati &nbsp;&nbsp;" /></span>
				</form>
			</div>
		</td>
	</tr>
</table>
<script>
	jQuery(function($) {
		$("#criteria").change(function() {
			var searchBy = $(this).val();
			var searchByInput=$("#searchBy");
			searchByInput.val(searchBy);
		});
	});
</script>