package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Comment;

public class CommentSender {

	public static void postComment(Comment comment,int announcementId) {
		final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/comments/save.do?announcementId="+announcementId;
		RestTemplate restTemplate = new RestTemplate();
		Comment result = restTemplate.postForObject(uri, comment,Comment.class);
		System.out.println("comment sent! API response: "+result.toString());
	}
	
}
