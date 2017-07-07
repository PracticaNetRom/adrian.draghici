package ro.netrom.summercamp.summercamp2017.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Comment;
import ro.netrom.summercamp.summercamp2017.services.CommentFetcher;

public class ShowCommentsEngine {

	public void showComments(ModelMap model, HttpServletRequest request){
		try {
			int index = Integer.parseInt(request.getParameter("announcementId"));
			Comment[] comments= new CommentFetcher().getComments(index);
			model.addAttribute("commentsRoot", comments);
			model.addAttribute("comments", null);
			System.out.println("object loaded");
		} catch (Exception ex) {
			model.addAttribute("comments", null);
			System.out.println("object NOT loaded");
		}
	}
	
}
