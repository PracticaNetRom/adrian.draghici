package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.netrom.summercamp.summercamp2017.engines.ShowAnnouncementsEngine;

@Controller
public class IndexController {

	private ShowAnnouncementsEngine showAnnouncementsEngine = null;

	@RequestMapping(value = { "/", "index.html" }, method = RequestMethod.GET)
	public String getIndexGet(ModelMap model) {
		model.addAttribute("title", "Anunturi");
		model.addAttribute("content", "announcements.jsp");
		if (showAnnouncementsEngine == null) {
			showAnnouncementsEngine = new ShowAnnouncementsEngine();
		}
		showAnnouncementsEngine.showAnnouncements(model, null);
		return "index";
	}

	@RequestMapping(value = { "/", "index.html" }, method = RequestMethod.POST)
	public String getIndexPost(ModelMap model, HttpServletRequest request) {
		model.addAttribute("title", "Anunturi");
		model.addAttribute("content", "announcements.jsp");
		if (showAnnouncementsEngine == null) {
			showAnnouncementsEngine = new ShowAnnouncementsEngine();
		}
		showAnnouncementsEngine.showAnnouncements(model, request);
		return "index";
	}

}
