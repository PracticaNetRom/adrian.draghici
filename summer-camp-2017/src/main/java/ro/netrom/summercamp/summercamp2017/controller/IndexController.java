package ro.netrom.summercamp.summercamp2017.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.netrom.summercamp.summercamp2017.services.AnnoucementFetcher;

@Controller
public class IndexController {

	@RequestMapping(value = { "/", "index.html" })
	public String getIndex(ModelMap model) {
		model.addAttribute("announcements", AnnoucementFetcher.getAnnoucement());
		return "index";
	}

	@RequestMapping(value = { "/resources" })
	public String getResources() {
		return "resources";
	}

	@RequestMapping(value = "/add.html")
	public String addAnnouncement(ModelMap model) {
		model.addAttribute("template", "new.jsp");
		return "index";
	}

}
