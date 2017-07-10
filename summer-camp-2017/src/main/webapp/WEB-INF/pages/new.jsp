<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="new" align="center">
	<form method="post">
		<input name="comanda" id="comanda" type="hidden" value="addAnunt" />
		<h1 align="center" style="white-space: nowrap;">Creare Anunt</h1>
		<table style="border: 0px; padding: 15px;">
			<tr>
				<td align="center" colspan="3">Date Contact</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Nume*:</td>
				<td align="left"><input type="text" name="requesterFirstName"
					value="${requesterFirstName}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterFirstNameError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Prenume*:</td>
				<td align="left"><input type="text" name="requesterLastName"
					value="${requesterLastName}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterLastNameError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">E-mail*:</td>
				<td align="left"><input type="text" name="requesterEmail"
					id="requesterEmail" value="${requesterEmail}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterEmailError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Numar Telefon:</td>
				<td align="left"><input type="text" name="requesterPhone"
					value="${requesterPhone}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterPhoneError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3">Date Anunt</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Titlu*:</td>
				<td align="left"><input type="text" name="annoucementTitle"
					value="${annoucementTitle}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementTitleError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Adresa:</td>
				<td align="left"><input type="text" name="annoucementAddress"
					value="${annoucementAddress}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementAddressError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Valabilitate
					(zile)*:</td>
				<td align="left"><input type="text" name="annoucementValidity"
					value="${annoucementValidity}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementValidityError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Categorie*:</td>
				<td align="left"><select style="width: 245px; height: 30"
					id="annoucementCategory" name="annoucementCategory">
						<c:forEach var="category" items="${categories}" varStatus="loop">
							<c:choose>
								<c:when test="${category==annoucementCategory}">
									<option value="${category.name}" selected="selected">${category.name}-
										${category.description}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.name}">${category.name}-
										${category.description}</option>
								</c:otherwise>
							</c:choose>

						</c:forEach>
				</select></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementCategoryError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Continut*:</td>
				<td align="left"><textarea name="annoucementContent" rows="5"
						cols="50">${annoucementContent}</textarea></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementContentError}</b></span>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="color: Magenta;" align="center"><b>&#10071;Campurile
						marcate cu * sunt obligatorii!</b></td>
			</tr>
		</table>
		<span> <input type="submit"
			style="font-size: 20pt; color: black; background-color: GreenYellow;"
			value="Publica Anuntul">
		</span>
	</form>
	<c:if test="${addAnnouncement==true}">
		<script>
			alert("Anuntul trimis cu succes!\nUn link de activare va fi trimis pe adresa de e-mail.");
		</script>
	</c:if>
	<c:if test="${!empty codeSent}">
		<c:choose>
			<c:when test="${codeSent==true}">
				<script>
					alert("E-mailul cu link-ul de activare al anuntului a fost trimis cu succes!\nVa rugam sa verificati atat in inbox cat si in spam/junk.\nAnuntul nu va fi publicat in Ziarul Online pana cand nu il activati!\nVa multumim!");
				</script>
			</c:when>
			<c:otherwise>
				<script>
					alert("E-mailul cu link-ul de activare al anuntului NU a putut fi trimis!\nVa rugam sa contactai un administrator pentru suport!\nAnuntul nu va fi publicat in Ziarul Online pana cand nu il activati!\nVa multumim!");
				</script>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>