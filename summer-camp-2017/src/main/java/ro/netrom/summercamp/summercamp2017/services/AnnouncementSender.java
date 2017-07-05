package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnouncementSender {

	public static void postAnnoucement(Annoucement announcement) {
		final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/save.do";
		RestTemplate restTemplate = new RestTemplate();
		Annoucement result = restTemplate.postForObject(uri, announcement,Annoucement.class);
		System.out.println("data sent! API response: "+result);
	}
	
}
