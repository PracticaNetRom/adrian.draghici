package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Comment;

@Component
public class CommentFetcher {

	public CommentFetcher(){
		
	}
	
	public Comment[] getComments(int announcementId){
		final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/comments/list.do?announcementId="+announcementId;
		RestTemplate restTemplate = new RestTemplate();
		Comment[] result = restTemplate.getForObject(uri, Comment[].class);
		System.out.println("comments fetched!: " + result.length + " items");
		return result;
	}
	
}
