package ro.netrom.summercamp.summercamp2017.engines;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;
import ro.netrom.summercamp.summercamp2017.services.AnnoucementFetcher;

public class ShowAnnouncementsEngine {

	private Annoucement[] announcements = null;
	private int pageNumber = 1;

	public ShowAnnouncementsEngine() {

	}

	public void showAnnouncements(ModelMap model, HttpServletRequest request) {
		try {
			int pages = 0, from, to;
			pageNumber = ServletRequestUtils.getIntParameter(request, "pageNr", 1);
			pageNumber = pageNumber <=0 ? 1 : pageNumber;
			if (announcements == null || pageNumber == 1) {
				announcements = new AnnoucementFetcher().getAnnoucements();
			}
			if (announcements.length % 10 > 0) {
				pages = announcements.length / 10 + 1;
			} else {
				pages = announcements.length / 10;
			}
			if (announcements.length < (pageNumber - 1) * 10) {
				from = announcements.length;
			} else {
				from = (pageNumber - 1) * 10;
			}
			if (announcements.length < (pageNumber * 10) - 1) {
				to = announcements.length;
			} else {
				to = pageNumber * 10;
			}
			Annoucement[] announcementsOnPage = Arrays.copyOfRange(announcements, from, to);
			model.addAttribute("announcements", announcementsOnPage);
			model.addAttribute("pageNr", pageNumber);
			model.addAttribute("pages", pages);
		} catch (Exception ex) {
			System.out.println("ex:" + ex);
			model.addAttribute("announcement", null);
		}
	}

	public void showAnnouncement(ModelMap model, HttpServletRequest request) {
		try {
			int announcementId = Integer.parseInt(request.getParameter("announcementId"));
			model.addAttribute("announcement", new AnnoucementFetcher().getAnnoucement(announcementId));
		} catch (Exception ex) {
			model.addAttribute("announcement", null);
			System.out.println("invalid announcementId");
		}
	}
}
