package ro.netrom.summercamp.summercamp2017.engines;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;
import ro.netrom.summercamp.summercamp2017.data.ConfirmationCode;
import ro.netrom.summercamp.summercamp2017.services.AnnouncementActivator;
import ro.netrom.summercamp.summercamp2017.services.AnnouncementDezactivator;
import ro.netrom.summercamp.summercamp2017.services.AnnouncementSender;
import ro.netrom.summercamp.summercamp2017.services.CategoryFetcher;

public class AddAnnoucementEngine {

	private ConfirmationCode confirmationCode = null;

	private Annoucement newAnnouncement;

	public AddAnnoucementEngine() {

	}

	private void validateAddAnnouncementForm(ModelMap model, HttpServletRequest request) {
		String firstName = request.getParameter("requesterFirstName");
		String lastName = request.getParameter("requesterLastName");
		String email = request.getParameter("requesterEmail");
		String phone = request.getParameter("requesterPhone");
		String title = request.getParameter("annoucementTitle");
		String address = request.getParameter("annoucementAddress");
		String validity = request.getParameter("annoucementValidity");
		String category = request.getParameter("annoucementCategory");
		String content = request.getParameter("annoucementContent");
		boolean valid = true;
		if (firstName == null) {
			model.addAttribute("requesterFirstNameError", "Nume obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isAlphaOnly(firstName, 20)) {
				model.addAttribute("requesterFirstNameError", "Nume invalid!");
				model.addAttribute("requesterFirstName", firstName);
				valid = false;
			} else {
				model.addAttribute("requesterFirstName", firstName);
			}
		}
		if (lastName == null) {
			model.addAttribute("requesterLastNameError", "Prenume obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isAlphaOnlyWithSpace(lastName, 40)) {
				model.addAttribute("requesterLastNameError", "Prenume invalid!");
				model.addAttribute("requesterLastName", lastName);
				valid = false;
			} else {
				model.addAttribute("requesterLastName", lastName);
			}
		}
		if (email == null) {
			model.addAttribute("requesterEmailError", "Email obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isEmailAddress(email)) {
				model.addAttribute("requesterEmailError", "Email invalid!");
				model.addAttribute("requesterEmail", email);
				valid = false;
			} else {
				model.addAttribute("requesterEmail", email);
				confirmationCode = new ConfirmationCode(email);
			}
		}
		if (phone != null && !Validators.isPhoneNumber(phone)) {
			model.addAttribute("requesterPhoneError", "Numar telefon invalid!");
			model.addAttribute("requesterPhone", phone);
			valid = false;
		} else {
			model.addAttribute("requesterPhone", phone);
		}
		if (title == null) {
			model.addAttribute("annoucementTitleError", "Titlu obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isAlphaNumericWithSpace(title, 40)) {
				model.addAttribute("annoucementTitleError", "Titlu invalid!");
				model.addAttribute("annoucementTitle", title);
				valid = false;
			} else {
				model.addAttribute("annoucementTitle", title);
			}
		}
		if (address != null && !Validators.isAlphaNumericWithSpace(address, 40)) {
			model.addAttribute("annoucementAddressError", "Adresa invalida!");
			model.addAttribute("annoucementAddress", address);
			valid = false;
		} else {
			model.addAttribute("annoucementAddress", address);
		}
		if (validity == null) {
			model.addAttribute("annoucementValidityError", "Valabilitate obligatorie!");
			valid = false;
		} else {
			if (!Validators.isNumber(validity)) {
				model.addAttribute("annoucementValidityError", "Valabilitate invalida!");
				model.addAttribute("annoucementValidity", validity);
				valid = false;
			} else {
				model.addAttribute("annoucementValidity", validity);
			}
		}
		if (category == null) {
			model.addAttribute("annoucementCategoryError", "Categorie obligatorie!");
			valid = false;
		} else {
			if (!Validators.isAlphaNumericWithSpace(category, 40)) {
				model.addAttribute("annoucementCategoryError", "Categorie invalida!");
				model.addAttribute("annoucementCategory", "");
				valid = false;
			} else {
				model.addAttribute("annoucementCategory", category);
			}
		}
		if (content == null) {
			model.addAttribute("annoucementContentError", "Continut obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isText(content, 4096)) {
				model.addAttribute("annoucementContentError", "Continut invalid!");
				model.addAttribute("annoucementContent", content);
				valid = false;
			} else {
				model.addAttribute("annoucementContent", content);
			}
		}
		if (valid) {
			newAnnouncement = new Annoucement(firstName, lastName, email, phone, title, address,
					Integer.parseInt(validity), category, confirmationCode.getVerificationCode(), content);
		} else {
			newAnnouncement = null;
		}
	}

	private boolean sendConfCode(ModelMap model, HttpServletRequest request, int announcementId) {
		boolean sent = true;
		sent = confirmationCode.sendCodeToUser(0, announcementId);
		model.addAttribute("codeSent", sent);
		return true;
	}

	private void sendNewAnnouncementData(ModelMap model, HttpServletRequest request) {
		Annoucement newAnnoucement = AnnouncementSender.postAnnoucement(newAnnouncement);
		if (newAnnoucement != null) {
			sendConfCode(model, request, newAnnoucement.getId());
		}
		model.addAttribute("addAnnouncement", true);
		model.addAttribute("requesterFirstName", "");
		model.addAttribute("requesterLastName", "");
		model.addAttribute("requesterEmail", "");
		model.addAttribute("requesterPhone", "");
		model.addAttribute("annoucementTitle", "");
		model.addAttribute("annoucementAddress", "");
		model.addAttribute("annoucementValidity", "");
		model.addAttribute("annoucementCategory", "");
		model.addAttribute("annoucementConfCode", "");
		model.addAttribute("annoucementContent", "");
	}

	public void addAnnoucement(ModelMap model, HttpServletRequest request) {
		if (request != null) {
			String command = request.getParameter("comanda");
			if (command != null && command.contentEquals("addAnunt")) {
				validateAddAnnouncementForm(model, request);
				if (newAnnouncement != null) {
					sendNewAnnouncementData(model, request);
					newAnnouncement = null;
				}
			}
		}
		model.addAttribute("categories", new CategoryFetcher().getCategoryes());
	}

	public void activateAnnouncement(ModelMap model, HttpServletRequest request) {
		Integer announcementId = null;
		try {
			announcementId = Integer.parseInt(request.getParameter("announcementId"));
		} catch (Exception e) {
		}
		String confirmationCode = request.getParameter("confirmationCode");
		if (announcementId != null && confirmationCode != null) {
			newAnnouncement = new AnnouncementActivator().activateAnnoucement(announcementId, confirmationCode);
			if (newAnnouncement != null) {
				String html = "<br/><br/><span align=\"center\"><form method=\"get\" action=\"announcement.html\"> <input name=\"announcementId\" type=\"hidden\" value=\""
						+ announcementId
						+ "\" /> <input type=\"submit\" style=\"font-size: 20pt; color: black; background-color: Cyan; width: 700px\" value=\"&#9971; Deschideti Anuntul\"> </span></div>";
				model.addAttribute("title", "Activare anunt Nr: #" + announcementId);
				model.addAttribute("announcementId", announcementId);
				model.addAttribute("result",
						"Anuntul cu numarul: Nr: #" + announcementId + " a fost activat cu succes!" + html);
				model.addAttribute("status", true);
			} else {
				model.addAttribute("title", "Activare anunt Nr: #" + announcementId);
				model.addAttribute("announcementId", announcementId);
				model.addAttribute("result", "Anuntul cu numarul: Nr: #" + announcementId + " nu a fost activat!");
				model.addAttribute("status", false);
			}
		} else {
			model.addAttribute("title", "Solicitare invalida");
			model.addAttribute("result", "Solicitarea dumneavoastra este invalida!");
			model.addAttribute("status", false);
		}

	}

	public void dezactivateAnnouncement(ModelMap model, HttpServletRequest request) {
		Integer announcementId = null;
		try {
			announcementId = Integer.parseInt(request.getParameter("announcementId"));
		} catch (Exception e) {
		}
		String ownerEmail = request.getParameter("ownerEmail");
		String confirmationCode=request.getParameter("confirmationCode");
		System.out.println("announcementId: "+announcementId+"\nemail: "+ownerEmail+"\nconfirmationCode: "+confirmationCode);
		if (announcementId != null && ownerEmail!=null && confirmationCode != null) {
			newAnnouncement = new AnnouncementDezactivator().dezactivateAnnoucement(announcementId, ownerEmail);
			if (newAnnouncement != null) {
				model.addAttribute("title", "Dezactivare anunt Nr: #" + announcementId);
				model.addAttribute("announcementId", announcementId);
				model.addAttribute("result",
						"Anuntul cu numarul: Nr: #" + announcementId + " a fost dezactivat cu succes!");
				model.addAttribute("status", true);
			} else {
				model.addAttribute("title", "Dezactivare anunt Nr: #" + announcementId);
				model.addAttribute("announcementId", announcementId);
				model.addAttribute("result", "Anuntul cu numarul: Nr: #" + announcementId + " nu a fost dezactivat!");
				model.addAttribute("status", false);
			}
		} else {
			model.addAttribute("title", "Solicitare invalida");
			model.addAttribute("result", "Solicitarea dumneavoastra este invalida!");
			model.addAttribute("status", false);
		}
	}

}
