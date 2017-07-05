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
				<td align="center"><input type="text" name="requesterFirstName"
					value="${requesterFirstName}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterFirstNameError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Prenume*:</td>
				<td align="center"><input type="text" name="requesterLastName"
					value="${requesterLastName}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterLastNameError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">E-mail*:</td>
				<td align="center"><input type="text" name="requesterEmail"
					id="requesterEmail" value="${requesterEmail}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${requesterEmailError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Numar Telefon:</td>
				<td align="center"><input type="text" name="requesterPhone"
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
				<td align="center"><input type="text" name="annoucementTitle"
					value="${annoucementTitle}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementTitleError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Adresa*:</td>
				<td align="center"><input type="text" name="annoucementAddress"
					value="${annoucementAddress}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementAddressError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Valabilitate
					(zile)*:</td>
				<td align="center"><input type="text"
					name="annoucementValidity" value="${annoucementValidity}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementValidityError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Categorie*:</td>
				<td align="center"><select style="width: 245px; height: 30"
					id="annoucementCategory" name="annoucementCategory">
						<option value="Altele">Altele</option>
						<option value="Animale">Animale</option>
						<option value="Arta">Arta</option>
						<option value="Auto">Auto</option>
						<option value="Casa si Gradina">Casa si Gradina</option>
						<option value="Cars">Cars</option>
						<option value="Electronice">Electronice</option>
						<option value="Imobiliare">Imobiliare</option>
						<option value="Imbracaminte">Imbracaminte</option>
						<option value="Servicii">Servicii</option>
						<option value="Sport">Sport</option>
				</select></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementCategoryError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3"><input type="submit"
					onclick="sendCode();"
					style="font-size: 12pt; color: black; background-color: Aquamarine;"
					value="Trimite Cod Confirmare pe Email"> <input
					name="contactEmail" id="contactEmail" type="hidden" value="">
					<script>
						function sendCode() {
							document.getElementById('contactEmail').value = document
									.getElementById('requesterEmail').value;
							document.getElementById('comanda').value = "sendConfCode";
						}
					</script></td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Cod confirmare*:</td>
				<td align="center"><input type="text"
					name="annoucementConfCode" value="${annoucementConfCode}" size="30" /></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementConfCodeError}</b></span>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="3"><h5>Pentru a primii acest
						cod trebuie sa selectati 'Trimitere cod pe Email' dupa ce ati
						comletat adresa de e-mail</h5></td>
			</tr>
			<tr>
				<td align="right" style="white-space: nowrap;">Continut*:</td>
				<td align="center"><textarea name="annoucementContent" rows="5"
						cols="50">${annoucementContent}</textarea></td>
				<td><span class="error"
					style="color: red; white-space: nowrap;"><b>${annoucementContentError}</b></span>
				</td>
			</tr>
		</table>
		<span> <input type="submit"
			style="font-size: 20pt; color: black; background-color: GreenYellow;"
			value="Publica Anuntul">
		</span>
	</form>
	<c:if test="${sendConfCode==true}"><script>alert("Codul a fost trimis cu succes pe adresa de e-mail\nVa rugam verificati atat in inbox cat si in spam/junk!");</script></c:if>
	<c:if test="${addAnnouncement==true}"><script>alert("Anuntul a fost postat cu succes!\nAnuntul va fi afisat in ziar in cel mai scurt timp!\nVa multumim!");</script></c:if>
</div>