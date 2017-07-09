package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnouncementActivator {

	public Annoucement activateAnnoucement(int announcementId, String confirmationCode) {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/getByConfirmationCodeAndId.do?announcementId="
					+ announcementId + "&confirmationCode=" + confirmationCode;
			RestTemplate restTemplate = new RestTemplate();
			Annoucement result = restTemplate.getForObject(uri, Annoucement.class);
			if (result != null && result.getId() != null && result.getId() == announcementId) {
				System.out.println("announcement activated!");
				return result;
			} else {
				System.out.println("announcement NOT activated!");
			}
		} catch (Exception ex) {

		}
		System.out.println("announcement NOT activated!");
		return null;
	}

}
