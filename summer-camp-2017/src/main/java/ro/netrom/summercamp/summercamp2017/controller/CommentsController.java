package ro.netrom.summercamp.summercamp2017.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ro.netrom.summercamp.summercamp2017.data.Comment;
import ro.netrom.summercamp.summercamp2017.services.CommentFetcher;

@Controller
@RequestMapping(value = "/comments")
public class CommentsController {
	
	@Autowired
	private CommentFetcher commentFetcher;
	
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public String getForAnnouncement(Model model, @RequestParam(value = "announcementId", required = true) Integer id) {
		List<Comment> comments = Arrays.asList(commentFetcher.getComments(id));
		model.addAttribute("comments", comments);
		model.addAttribute("content", "comments.jsp");
		return "index";
	}
	
}
