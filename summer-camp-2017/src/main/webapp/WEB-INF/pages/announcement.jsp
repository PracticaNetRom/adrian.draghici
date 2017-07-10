<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
						<td align="center" colspan="2" bgColor="GoldenRod">Date
							contact</td>
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
			</div>
			<div align="center">
				<br />
				<table align="center">
					<tr>
						<td>
							<form method="post">
								<input name="announcementId" type="hidden"
									value="${announcement.id}" /> <input name="showComments"
									id="showComments" type="hidden" value="${showComments}" /> <input
									id="commentsAction" type="button" onclick="getComments();"
									style="font-size: 20pt; color: black; background-color: Thistle;"
									value="&#10077;&#10078; Afisati comentarii">
							</form>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
							<form method="post">
								<input name="announcementId" type="hidden"
									value="${announcement.id}" /> <input
									name="ownerEmail" id="ownerEmail"
									type="hidden" value="${announcement.ownerEmail}" /> <input
									id="dezactivateAnnouncementAction" type="button"
									onclick="dezactivateAnnouncement();"
									style="font-size: 20pt; color: DarkRed; background-color: OrangeRed;"
									value="&#10008; Dezactivati Anuntul">
							</form>
						</td>
					</tr>
				</table>
				<div id="commentsRoot"></div>
				<div id="answ"></div>
			</div>
		</c:when>
		<c:otherwise>
			<h1 align="center">&#9940; Anuntul nu exista!</h1>
		</c:otherwise>
	</c:choose>
</div>
<script>
	function fetchComments(){
		var announcementId;
		if(${announcement.id!=null}){
			announcementId=${announcement.id};
		}else{
			announcementId=${announcementId};
		}
		$.ajax({
			url : 'comments.html',
			type : 'POST',
			data : {
				announcementId : announcementId,
				showComments: true
			},
			success : function(result, textStatus, request) {
				$("#commentsRoot").html(result);
			}
		});
	}

	function getComments() {
		if (document.getElementById('showComments').value == 'false') {
			document.getElementById('showComments').value = 'true';
			document.getElementById('commentsAction').value = "\u275D\u275E Ascundeti comentarii";
			fetchComments();
		} else {
			document.getElementById('commentsAction').value = "\u275D\u275E Afisati comentarii";
			document.getElementById('showComments').value = 'false';
			$("#commentsRoot").html("");
		}
	}
	
	function dezactivateAnnouncement(){
		if(confirm("Dezactivarea anuntului presupune stergerea acestuia din ziarul online!\nAceasta actiune necesita confirmare prin transmiterea unui e-mail proprietarului cu un link de confirmare.\n\nSunteti sigut ca doriti sa dezactivati anuntul?")==true){
			var ownerEmail=document.getElementById('ownerEmail').value;
			$.ajax({
				url : 'requestDezactivation.html',
				type : 'POST',
				data : {
					announcementId : ${announcement.id},
					ownerEmail: ownerEmail
				},
				success : function(result, textStatus, request) {
					alert("Raspuns: "+request.getResponseHeader('codeSent'));
					$("#answ").html(result);
					var codeSent = document.getElementById("codeSent").value;
					if(codeSent!=""){
						if(codeSent!=false){
							alert("E-mailul cu link-ul de dezactivare al anuntului a fost trimis cu succes!\nVa rugam sa verificati atat in inbox cat si in spam/junk.\nAnuntul nu va fi dezactivat din Ziarul Online pana cand nu confirmati dezactivarea!\nVa multumim!");
						}else{
							alert("E-mailul cu link-ul de dezactivare al anuntului NU a putut fi trimis!\nVa rugam sa contactati un administrator pentru suport!\nAnuntul nu va fi dezactivat din Ziarul Online pana cand nu confirmati dezactivarea!\nVa multumim!");
						}
					}else{
						alert("O solicitare de dezactivare a fost deja trimisa pe adresa de e-mail!")
					}
				}
			});
		}
	}
</script>