<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="announcementItem">
	<c:choose>
		<c:when test="${!empty announcement}">
			<h1 align="center">Anunt Nr #${announcement.id}</h1>
			<div>
				<table align="center" border="1" bgcolor="Aqua">
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
						<td width=200>Adresa:</td>
						<td width=200>${announcement.location}</td>
					</tr>
					<tr>
						<td width=200>Data publicare Anunt:</td>
						<td width=200>${announcement.createDate}</td>
					</tr>
					<tr>
						<td width=200>Data expirare Anunt:</td>
						<td width=200>${announcement.expireDate}</td>
					</tr>
					<tr>
						<td width=200>Categorie Anunt:</td>
						<td width=200>${announcement.categoryName},
							${announcement.categoryDescription}</td>
					</tr>
					<tr>
						<td width=200>Continut Anunt:</td>
						<td width=500>${announcement.content}</td>
					</tr>
					<tr>
						<td align="center" colspan="2" bgColor="GoldenRod">Date contact</td>
					</tr>
					<tr>
						<td width=200>Nume:</td>
						<td width=200>${announcement.ownerFirstName}</td>
					</tr>
					<tr>
						<td width=200>Prenume:</td>
						<td width=200>${announcement.ownerLastName}</td>
					</tr>
					<tr>
						<td width=200>Email:</td>
						<td width=200>${announcement.ownerEmail}</td>
					</tr>
					<tr>
						<td width=200>Numar Telefon:</td>
						<td width=200>${announcement.ownerPhone}</td>
					</tr>
				</table>
				<br /> <br />
			</div>
		</c:when>
		<c:otherwise>
			<h1 align="center">Anuntul nu exista!</h1>
		</c:otherwise>
	</c:choose>
</div>