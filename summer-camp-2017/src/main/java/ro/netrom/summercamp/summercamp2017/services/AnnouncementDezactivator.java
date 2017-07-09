package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnouncementDezactivator {

	public Annoucement dezactivateAnnoucement(int announcementId, String ownerEmail) {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/close.do?id="
					+ announcementId + "&ownerEmail=" + ownerEmail;
			RestTemplate restTemplate = new RestTemplate();
			Annoucement result = restTemplate.postForObject(uri, null, Annoucement.class);
			if (result != null && result.getId() != null && result.getId().intValue() == announcementId) {
				System.out.println("announcement dezactivated!");
				return result;
			}
		} catch (Exception ex) {
		}
		System.out.println("announcement NOT dezactivated!");
		return null;
	}

}
