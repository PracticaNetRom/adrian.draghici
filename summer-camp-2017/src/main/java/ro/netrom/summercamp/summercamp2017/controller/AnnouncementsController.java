package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.netrom.summercamp.summercamp2017.engines.AddAnnoucementEngine;
import ro.netrom.summercamp.summercamp2017.engines.RemoveAnnouncementEngine;
import ro.netrom.summercamp.summercamp2017.engines.ShowAnnouncementsEngine;
import ro.netrom.summercamp.summercamp2017.engines.ShowCommentsEngine;

@Controller
public class AnnouncementsController {

	private ModelMap requests=new ModelMap();
	
	@Autowired
	private ShowAnnouncementsEngine showAnnouncementsEngine;
	
	
	@RequestMapping(value = { "announcement.html" }, method = RequestMethod.GET)
	public String getAnnouncements(ModelMap model, HttpServletRequest request) {
		model.addAttribute("title", "Anunt #" + request.getParameter("announcementId"));
		model.addAttribute("content", "announcement.jsp");
		model.addAttribute("announcementId", request.getParameter("announcementId"));
		showAnnouncementsEngine.showAnnouncement(model, request);
		if (request.getParameter("showComments") != null && request.getParameter("showComments").equals("true")) {
			model.addAttribute("showComments", true);
			new ShowCommentsEngine().showComments(model, request);
		} else {
			model.addAttribute("showComments", false);
		}
		return "index";
	}
	
	@RequestMapping(value = { "requestDezactivation.html" }, method = RequestMethod.POST)
	public String requestAnnouncementDezactivation(ModelMap model, HttpServletRequest request) {
		new RemoveAnnouncementEngine().requestDezactivateAnnouncement(model, request,requests);
		return "include/resp";
	}

	@RequestMapping(value = "add.html")
	public String addAnnouncement(ModelMap model, HttpServletRequest request) {
		model.addAttribute("title", "Anunt nou");
		model.addAttribute("content", "new.jsp");
		new AddAnnoucementEngine().addAnnoucement(model, request);
		return "index";
	}
	
	@RequestMapping(value = "activate.html", method = RequestMethod.GET)
	public String activateAnnouncement(ModelMap model, HttpServletRequest request) {
		model.addAttribute("content", "include/result.jsp");
		new AddAnnoucementEngine().activateAnnouncement(model, request);
		return "index";
	}
	
	@RequestMapping(value = "dezactivate.html", method = RequestMethod.GET)
	public String dezactivateAnnouncement(ModelMap model, HttpServletRequest request) {
		model.addAttribute("content", "include/result.jsp");
		new RemoveAnnouncementEngine().dezactivateAnnouncement(model, request,requests);
		return "index";
	}
	
}
