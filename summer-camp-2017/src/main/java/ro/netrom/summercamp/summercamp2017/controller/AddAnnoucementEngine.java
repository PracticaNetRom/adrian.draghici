package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;
import ro.netrom.summercamp.summercamp2017.data.ConfirmationCode;
import ro.netrom.summercamp.summercamp2017.services.AnnouncementSender;

public class AddAnnoucementEngine {

	ConfirmationCode confirmationCode = null;
	
	Annoucement newAnnouncement;

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
		String confCode = request.getParameter("annoucementConfCode");
		String content = request.getParameter("annoucementContent");
		boolean valid = true;
		if (firstName == null) {
			model.addAttribute("requesterFirstNameError", "Nume obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isAlphaOnly(firstName, 20)) {
				model.addAttribute("requesterFirstNameError", "Nume invalid!");
				model.addAttribute("requesterFirstName", "");
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
				model.addAttribute("requesterLastName", "");
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
				model.addAttribute("requesterEmail", "");
				valid = false;
			} else {
				model.addAttribute("requesterEmail", email);
			}
		}
		if (phone == null) {
			model.addAttribute("requesterPhoneError", "Numar telefon obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isPhoneNumber(phone)) {
				model.addAttribute("requesterPhoneError", "Numar telefon invalid!");
				model.addAttribute("requesterPhone", "");
				valid = false;
			} else {
				model.addAttribute("requesterPhone", phone);
			}
		}
		if (title == null) {
			model.addAttribute("annoucementTitleError", "Titlu obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isAlphaNumericWithSpace(title, 40)) {
				model.addAttribute("annoucementTitleError", "Titlu invalid!");
				model.addAttribute("annoucementTitle", "");
				valid = false;
			} else {
				model.addAttribute("annoucementTitle", title);
			}
		}
		if (address == null) {
			model.addAttribute("annoucementAddressError", "Adresa obligatorie!");
			valid = false;
		} else {
			if (!Validators.isAlphaNumericWithSpace(address, 40)) {
				model.addAttribute("annoucementAddressError", "Adresa invalida!");
				model.addAttribute("annoucementAddress", "");
				valid = false;
			} else {
				model.addAttribute("annoucementAddress", address);
			}
		}
		if (validity == null) {
			model.addAttribute("annoucementValidityError", "Valabilitate obligatorie!");
			valid = false;
		} else {
			if (!Validators.isNumber(validity)) {
				model.addAttribute("annoucementValidityError", "Valabilitate invalida!");
				model.addAttribute("annoucementValidity", "");
				valid = false;
			} else {
				model.addAttribute("annoucementValidity", validity);
			}
		}
		if (category == null) {
			model.addAttribute("annoucementCategoryError", "Categorie obligatorie!");
			valid = false;
		} else {
			if (!Validators.isAlphaOnlyWithSpace(category, 40)) {
				model.addAttribute("annoucementCategoryError", "Categorie invalida!");
				model.addAttribute("annoucementCategory", "");
				valid = false;
			} else {
				model.addAttribute("annoucementCategory", category);
			}
		}
		if (confCode == null) {
			model.addAttribute("annoucementConfCodeError", "Cod confirmare obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isAlphaNumeric(confCode, 6)
					|| !(confirmationCode != null && confirmationCode.validateCode(confCode))) {
				model.addAttribute("annoucementConfCodeError", "Cod confirmare invalid!");
				model.addAttribute("annoucementConfCode", "");
				valid = false;
			} else {
				model.addAttribute("annoucementConfCode", confCode);
			}
		}
		if (content == null) {
			model.addAttribute("annoucementContentError", "Continut obligatoriu!");
			valid = false;
		} else {
			if (!Validators.isText(content, 4096)) {
				model.addAttribute("annoucementContentError", "Continut invalid!");
				model.addAttribute("annoucementContent", "");
				valid = false;
			} else {
				model.addAttribute("annoucementContent", content);
			}
		}
		if(valid){
			newAnnouncement=new Annoucement(firstName,lastName,email,phone,title,address,Integer.parseInt(validity),category,category+" description",confCode,content);
		}else{
			newAnnouncement=null;
		}
	}

	private void sendConfCode(ModelMap model, HttpServletRequest request) {
		String email = request.getParameter("contactEmail");
		boolean valid = false;
		if (email == null) {
			model.addAttribute("requesterEmailError", "Email obligatoriu!");
		} else {
			if (!Validators.isEmailAddress(email)) {
				model.addAttribute("requesterEmailError", "Email invalid!");
				model.addAttribute("requesterEmail", "");
			} else {
				model.addAttribute("requesterEmail", email);
				valid = true;
				confirmationCode = new ConfirmationCode(email);
			}
		}
		model.addAttribute("sendConfCode", valid);
		model.addAttribute("requesterFirstName", request.getParameter("requesterFirstName"));
		model.addAttribute("requesterLastName", request.getParameter("requesterLastName"));
		model.addAttribute("requesterPhone", request.getParameter("requesterPhone"));
		model.addAttribute("annoucementTitle", request.getParameter("annoucementTitle"));
		model.addAttribute("annoucementAddress", request.getParameter("annoucementAddress"));
		model.addAttribute("annoucementValidity", request.getParameter("annoucementValidity"));
		model.addAttribute("annoucementCategory", request.getParameter("annoucementCategory"));
		model.addAttribute("annoucementContent", request.getParameter("annoucementContent"));
	}

	private void sendNewAnnouncementData(ModelMap model, HttpServletRequest request) {
		AnnouncementSender.postAnnoucement(newAnnouncement);
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
			if (command != null) {
				switch (command) {
				case "addAnunt":
					validateAddAnnouncementForm(model, request);
					if (newAnnouncement!=null) {
						sendNewAnnouncementData(model, request);
						newAnnouncement=null;
					}
					break;
				case "sendConfCode":
					sendConfCode(model, request);
					break;
				}

			}
		}
	}
}
