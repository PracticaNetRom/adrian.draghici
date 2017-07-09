package ro.netrom.summercamp.summercamp2017.engines;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import ro.netrom.summercamp.summercamp2017.data.Comment;
import ro.netrom.summercamp.summercamp2017.services.CommentSender;

public class AddCommentEngine {

	private Comment newComment = null;

	public AddCommentEngine() {

	}

	private void validateCommentForm(ModelMap model, HttpServletRequest request) {
		try
		{
			boolean valid=true;
			Integer announcementId=null;
			if(request.getParameter("announcementId")!=null){
				announcementId=Integer.parseInt(request.getParameter("announcementId"));
			}
			Integer parentId=null;
			if(request.getParameter("parentId")!=null){
				parentId=Integer.parseInt(request.getParameter("parentId"));
			}
			String commerName=request.getParameter("commerName");
			String commentContent=request.getParameter("commentContent");
			
			if (commerName == null) {
				model.addAttribute("commerNameError", "Nume obligatoriu!");
				valid = false;
			} else {
				if (!Validators.isAlphaOnlyWithSpace(commerName, 40)) {
					model.addAttribute("commerNameError", "Nume invalid!");
					model.addAttribute("commerName", commerName);
					valid = false;
				} else {
					model.addAttribute("commerName", commerName);
				}
			}
			if (commentContent == null) {
				model.addAttribute("commentContentError", "Comentariu obligatoriu!");
				valid = false;
			} else {
				if (!Validators.isText(commentContent, 4096)) {
					model.addAttribute("commentContentError", "Comentariu invalid!");
					model.addAttribute("commentContent", commentContent);
					valid = false;
				} else {
					model.addAttribute("commentContent", commentContent);
				}
			}
			if(announcementId==null){
				valid = false;
			}
			if(valid){
				newComment=new Comment(announcementId,parentId,commerName,commentContent);
			}else{
				newComment=null;
			}
			System.out.println("valid: "+valid);
		}catch(Exception ex){
			System.out.println("ex at addCommentEngineValidator: "+ex);
			model.addAttribute("commentContentError", "Id anunt invalid!");
		}
	}

	private void sendCommentData(ModelMap model, HttpServletRequest request) {
		if(CommentSender.postComment(newComment)){
			model.addAttribute("succes", true);
		}else{
			model.addAttribute("succes", false);
		}
		model.remove("commerName");
		model.remove("commentContent");
	}

	public void addComment(ModelMap model, HttpServletRequest request) {
		if (request != null) {
			validateCommentForm(model, request);
			if (newComment != null) {
				sendCommentData(model, request);
			}else{
				model.addAttribute("succes", false);
			}
		}
	}

}
