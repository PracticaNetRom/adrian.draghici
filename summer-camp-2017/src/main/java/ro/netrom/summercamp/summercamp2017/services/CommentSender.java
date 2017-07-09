package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Comment;

public class CommentSender {

	public static boolean postComment(Comment comment) {
		try {
			String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/comments/save.do?announcementId="
					+ comment.getAnnouncementId();
			if (comment.getParentId() != null) {
				uri += "&parentId=" + comment.getParentId().intValue();
			}
			System.out.println(
					"uri: " + uri + "\nData:\nname: " + comment.getName() + "\ncontent: " + comment.getContent());
			RestTemplate restTemplate = new RestTemplate();
			Comment result = restTemplate.postForObject(uri, comment, Comment.class);
			if (result != null) {
				System.out.println("comment sent!");
				return true;
			}
		} catch (Exception ex) {

		}
		System.out.println("comment NOT sent!");
		return false;
	}

}
