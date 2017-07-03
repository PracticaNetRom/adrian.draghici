package ro.netrom.summercamp.summercamp2017.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = { "/" })
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping(value = { "/resources" })
	public String getResources() {
		return "resources";
	}

}
