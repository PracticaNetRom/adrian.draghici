<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table align="left" border="1" bgcolor="NavajoWhite">
	<tr>
		<td rowspan="3" width=90 align="center"
			style="font-size: 45pt; color: Lime; background-color: Violet;">&#9998;</td>
		<td colspan="2" width=700 align="center" valign="middle"
			style="font-size: 20pt; color: black; background-color: Khaki;">
			Numele dumneavoastra*: <input type="text"
			id="commerName${announcement.id}" value="${commerName}" size="30" /><span
			class="error" style="color: red; white-space: nowrap;"><b>${commerNameNameError}</b></span>
		</td>
	</tr>
	<tr>
		<td colspan="2" width=700 align="left"
			style="font-size: 15pt; color: black;" valign="middle">Comentariu*:
			<textarea id="commentContent${announcement.id}" rows="5" cols="97">${commentContent}</textarea><span
			class="error" style="color: red; white-space: nowrap;"><b>${commentContentError}</b></span>
		</td>
	</tr>
	<tr>
		<td width=500 align="center"><input
			id="announcementId${announcement.id}" type="hidden"
			value="${announcement.id}" /> <input id="parentId${announcement.id}"
			type="hidden" value="${comment.id}" />
			<button type="button" onclick="submitComment(${comment.id});"
				style="font-size: 15pt; color: DarkMagenta; background-color: Chartreuse; border-collapse: collapse; border: 1px solid green; width: 500px">&#10009;
				Publicati</button></td>
		<td width=200 align="center">
			<button type="button" onclick="toggleCommentForm(${comment.id});"
				style="font-size: 15pt; color: DarkMagenta; background-color: Red; border-collapse: collapse; border: 1px solid green; width: 200px">&#10006;
				Renuntati</button>
		</td>
	</tr>
</table>