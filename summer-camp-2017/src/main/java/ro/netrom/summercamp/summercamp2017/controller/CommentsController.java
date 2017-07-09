package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ro.netrom.summercamp.summercamp2017.engines.AddCommentEngine;
import ro.netrom.summercamp.summercamp2017.engines.ShowCommentsEngine;

@Controller
public class CommentsController {
	
	@RequestMapping(value = { "comments.html" }, method = RequestMethod.POST)
	public String getComments(ModelMap model, HttpServletRequest request) {
		if (request.getParameter("announcementId") != null) {
			System.out.println("announcementId:"+request.getParameter("announcementId"));
			model.addAttribute("announcementId", request.getParameter("announcementId"));
			if (request.getParameter("reply") != null) {
				new AddCommentEngine().addComment(model, request);
			}
			if (request.getParameter("showComments") != null && request.getParameter("showComments").equals("true")) {
				model.addAttribute("showComments", true);
				new ShowCommentsEngine().showComments(model, request);
			} else {
				model.addAttribute("showComments", false);
			}
		}
		return "include/commentsRoot";
	}
	
}
