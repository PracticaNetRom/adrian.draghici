package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	private ShowAnnouncementsEngine showAnnouncementsEngine=null;
	private AddAnnoucementEngine addAnnoucementEngine=null;

	@RequestMapping(value = { "/", "index.html" }, method = RequestMethod.GET)
	public String getIndexGet(ModelMap model) {
		model.addAttribute("title", "Anunturi");
		model.addAttribute("content", "announcements.jsp");
		if(showAnnouncementsEngine==null){
			showAnnouncementsEngine=new ShowAnnouncementsEngine();
		}
		showAnnouncementsEngine.showAnnouncements(model, null);
		return "index";
	}
	
	@RequestMapping(value = { "/", "index.html" }, method = RequestMethod.POST)
	public String getIndexPost(ModelMap model, HttpServletRequest request) {
		model.addAttribute("title", "Anunturi");
		model.addAttribute("content", "announcements.jsp");
		if(showAnnouncementsEngine==null){
			showAnnouncementsEngine=new ShowAnnouncementsEngine();
		}
		showAnnouncementsEngine.showAnnouncements(model, request);
		return "index";
	}

	@RequestMapping(value = { "announcement.html" }, method = RequestMethod.POST)
	public String getAnnouncements(ModelMap model, HttpServletRequest request) {
		model.addAttribute("title", "Anunt #" + request.getParameter("announcementId"));
		model.addAttribute("content", "announcement.jsp");
		if(showAnnouncementsEngine==null){
			showAnnouncementsEngine=new ShowAnnouncementsEngine();
		}
		showAnnouncementsEngine.showAnnouncement(model, request);
		return "index";
	}

	@RequestMapping(value = { "/resources" })
	public String getResources() {
		return "resources";
	}

	@RequestMapping(value = "add.html")
	public String addAnnouncement(ModelMap model, HttpServletRequest request) {
		model.addAttribute("title", "Anunt nou");
		model.addAttribute("content", "new.jsp");
		if(addAnnoucementEngine==null){
			addAnnoucementEngine=new AddAnnoucementEngine();
		}
		addAnnoucementEngine.addAnnoucement(model, request);
		return "index";
	}

}
