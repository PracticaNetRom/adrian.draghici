package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

public class ShowCommentsEngine {

	public void showComment(ModelMap model, HttpServletRequest request){
		try {
			int index = Integer.parseInt(request.getParameter("announcementIndex"));
//			model.addAttribute("announcement", announcements[index]);
			System.out.println("object loaded");
		} catch (Exception ex) {
			model.addAttribute("announcement", null);
			System.out.println("object NOT loaded");
		}
	}
	
}
