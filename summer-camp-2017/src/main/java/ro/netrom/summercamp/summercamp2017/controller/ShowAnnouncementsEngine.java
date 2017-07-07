package ro.netrom.summercamp.summercamp2017.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;
import ro.netrom.summercamp.summercamp2017.services.AnnoucementFetcher;

public class ShowAnnouncementsEngine {

	private Annoucement[] announcements=null;
	private int pageNumber = 1;
	
	public ShowAnnouncementsEngine(){
		
	}
	
	public void showAnnouncements(ModelMap model, HttpServletRequest request){
		try {
			int pages = 0, from, to;
			if (announcements != null && request != null) {
				if (announcements.length % 10 > 0) {
					pages = announcements.length / 10 + 1;
				} else {
					pages = announcements.length / 10;
				}
				if (request.getParameter("pageNr") != null) {
					pageNumber = Integer.parseInt(request.getParameter("pageNr"));
				} else {
					if (request.getParameter("mode") != null && pageNumber <= pages && pageNumber > 0) {
						switch (request.getParameter("mode")) {
						case "next":
							pageNumber++;
							System.out.println("next");
							break;
						case "back":
							pageNumber--;
							System.out.println("back");
							break;
						}
					}
				}
			}else{
				pageNumber=1;
			}
			if (pageNumber == 1) {
				AnnoucementFetcher annoucementFetcher=new AnnoucementFetcher();
				announcements = annoucementFetcher.getAnnoucements();
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
			System.out.println("ex:"+ex);
			model.addAttribute("announcement", null);
		}
	}
	
	public void showAnnouncement(ModelMap model, HttpServletRequest request){
		try {
			int index = Integer.parseInt(request.getParameter("announcementIndex"));
			model.addAttribute("announcement", announcements[index]);
			model.addAttribute("index", request.getParameter("announcementIndex"));
			System.out.println("announcement loaded from server cache");
		} catch (Exception ex) {
			model.addAttribute("announcement", null);
			System.out.println("announcement NOT from server cache");
		}
	}
}
