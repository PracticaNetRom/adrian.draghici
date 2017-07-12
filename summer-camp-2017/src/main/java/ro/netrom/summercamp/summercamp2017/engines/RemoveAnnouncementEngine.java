package ro.netrom.summercamp.summercamp2017.engines;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;
import ro.netrom.summercamp.summercamp2017.data.ConfirmationCode;
import ro.netrom.summercamp.summercamp2017.services.AnnoucementFetcher;
import ro.netrom.summercamp.summercamp2017.services.AnnouncementDezactivator;

public class RemoveAnnouncementEngine {

	private ConfirmationCode confirmationCode = null;

	private boolean sendConfCode(ModelMap model, HttpServletRequest request, int announcementId) {
		boolean sent = true;
		sent = confirmationCode.sendCodeToUser(1, announcementId);
		model.addAttribute("codeSent", sent);
		return true;
	}

	public void requestDezactivateAnnouncement(ModelMap model, HttpServletRequest request, ModelMap requests) {
		Integer announcementId = null;
		try {
			announcementId = Integer.parseInt(request.getParameter("announcementId"));
		} catch (Exception e) {
		}
		String ownerEmail = request.getParameter("ownerEmail");
		if (announcementId != null && ownerEmail != null) {
			if (Validators.isEmailAddress(ownerEmail)) {
				if (!requests.containsAttribute("" + announcementId)) {
					Annoucement annoucement = new AnnoucementFetcher().getAnnoucement(announcementId);
					if (annoucement != null && annoucement.getOwnerEmail().contentEquals(ownerEmail)) {
						confirmationCode = new ConfirmationCode(ownerEmail, annoucement.getConfirmationCode());
						if (sendConfCode(model, request, announcementId)) {
							requests.addAttribute("" + announcementId, announcementId);
						}
					} else {
						model.addAttribute("codeSent", false);
					}
				}
			}
		}
	}

	public void dezactivateAnnouncement(ModelMap model, HttpServletRequest request, ModelMap requests) {
		Integer announcementId = null;
		try {
			announcementId = Integer.parseInt(request.getParameter("Id"));
		} catch (Exception e) {
		}
		String ownerEmail = request.getParameter("ownerEmail");
		String confirmationCode = request.getParameter("confirmationCode");
		if (announcementId != null && ownerEmail != null && confirmationCode != null && confirmationCode != null) {
			requests.remove("" + announcementId, announcementId);
			Annoucement annoucement = new AnnoucementFetcher().getAnnoucement(announcementId);
			if (annoucement != null && !annoucement.isStatus()) {
				if (annoucement.getConfirmationCode().contentEquals(confirmationCode)) {
					Annoucement newAnnouncement = new AnnouncementDezactivator().dezactivateAnnoucement(announcementId,
							ownerEmail);
					if (newAnnouncement != null) {
						model.addAttribute("title", "Dezactivare anunt Nr: #" + announcementId);
						model.addAttribute("announcementId", announcementId);
						model.addAttribute("result",
								"Anuntul cu numarul: Nr: #" + announcementId + " a fost dezactivat cu succes!");
						model.addAttribute("status", true);
						return;
					}
				}
				model.addAttribute("title", "Dezactivare anunt Nr: #" + announcementId);
				model.addAttribute("announcementId", announcementId);
				model.addAttribute("result", "Anuntul cu numarul: Nr: #" + announcementId + " nu a fost dezactivat!");
				model.addAttribute("status", false);
				return;
			}
		}
		model.addAttribute("title", "Solicitare invalida");
		model.addAttribute("result", "Solicitarea dumneavoastra este invalida!");
		model.addAttribute("status", false);
	}

}
