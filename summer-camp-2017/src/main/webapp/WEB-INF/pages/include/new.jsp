<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	out.clear();
%>
<%
	session.setAttribute("title", "Anunt Nou");
%>
<%@include file="header.jsp"%>
<div id="new" align="center">
	<form method="post">
		<input name="comanda" type="hidden" value="addAnunt" />
		<h1 align="center">Creare Anunt</h1>
		<table style="border: 0px; padding: 15px;">
			<tr>
				<td align="center" colspan="2">Date Contact</td>
			</tr>
			<tr>
				<td align="right">Nume*:</td>
				<td><input type="text" name="requesterFirstName" value=""
					size="30" /><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right">Prenume*:</td>
				<td><input type="text" name="requesterLastName" value=""
					size="30" /><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right">E-mail*:</td>
				<td><input type="text" name="requesterEmail" value="" size="30" /><span
					class="error"></span></td>
				<td><span id="sendEmal"></span></td>
			</tr>
			<tr>
				<td align="right">Numar Telefon:</td>
				<td><input type="text" name="requesterPhone" value="" size="30" /><span
					class="error"></span></td>
			</tr>
			<tr>
				<td align="center" colspan="2">Date Anunt</td>
			</tr>
			<tr>
				<td align="right">Titlu*:</td>
				<td><input type="text" name="annoucementTitle" value=""
					size="30" /><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right">Adresa*:</td>
				<td><input type="text" name="annoucementAddress" value=""
					size="30" /><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right">Valabilitate (zile)*:</td>
				<td><input type="text" name="annoucementValidity" value=""
					size="30" /><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right">Categorie*:</td>
				<td><select style="width: 245px; height: 30"
					id="annoucementCategory" name="annoucementCategory">
						<option value="">Altele</option>
						<option value="">Animale</option>
						<option value="">Arta</option>
						<option value="">Auto</option>
						<option value="">Casa si Gradina</option>
						<option value="">Electronice</option>
						<option value="">Imobiliare</option>
						<option value="">Imbracaminte</option>
						<option value="">Servicii</option>
						<option value="">Sport</option>
				</select><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right">Cod confirmare*:</td>
				<td><input type="text" name="annoucementConfCode" value=""
					size="30" /><span class="error"></span></td>
			</tr>
			<tr>
				<td align="right" colspan="2"><h6>Pentru a primii acest
						cod trebuie sa selectati 'Trimitere cod pe Email' dupa ce ati
						comletat adresa de e-mail</h6></td>
			</tr>
			<tr>
				<td align="right">Continut*:</td>
				<td><textarea name="annoucementContent" rows="5" cols="50"></textarea><span
					class="error"></span></td>
			</tr>
		</table>
		<span> <input type="submit"
			style="font-size: 20pt; color: black; background-color: GreenYellow;"
			value="Publica Anuntul">
		</span>
	</form>
	<script>
		document.getElementById('sendEmal').innerHTML = ' <form action="new.html"> <input name="comanda" type="hidden" value="sendConfCode" /> <input name="contactEmail" type="hidden" value="" /> <script> document.getElementById(\'contactEmail\').value = document.getElementById(\'requesterEmail\').value <\/script> <input type="submit" style="font-size: 10pt; color: black; background-color: Aquamarine;" value="Trimite Cod Confirmare pe Email"> </form> '
	</script>
</div>
<%@include file="footer.jsp"%>