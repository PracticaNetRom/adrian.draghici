package ro.netrom.summercamp.summercamp2017.engines;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Comment;
import ro.netrom.summercamp.summercamp2017.services.CommentFetcher;

public class ShowCommentsEngine {

	public void showComments(ModelMap model, HttpServletRequest request){
		try {
			int announcementId = Integer.parseInt(request.getParameter("announcementId"));
			Comment[] comments= new CommentFetcher().getComments(announcementId);
			model.addAttribute("commentsRoot", comments);
			model.addAttribute("comments", null);
			System.out.println("comments loaded");
		} catch (Exception ex) {
			model.addAttribute("comments", null);
			System.out.println("comments NOT loaded. reason:"+ex);
		}
	}
	
}
