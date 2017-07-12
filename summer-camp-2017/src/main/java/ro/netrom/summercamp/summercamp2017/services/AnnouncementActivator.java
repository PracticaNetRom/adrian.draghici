package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnouncementActivator {

	private static boolean activate(Annoucement announcement) {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/activate.do?id="+announcement.getId();
			RestTemplate restTemplate = new RestTemplate();
			Annoucement result = restTemplate.postForObject(uri, null, Annoucement.class);
			if (result != null && result.getId() != null && result.getId()==announcement.getId()) {
				return true;
			}
		} catch (Exception ex) {

		}
		return false;
	}
	
	public Annoucement activateAnnoucement(int announcementId, String confirmationCode) {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/getByConfirmationCodeAndId.do?announcementId="
					+ announcementId + "&confirmationCode=" + confirmationCode;
			RestTemplate restTemplate = new RestTemplate();
			Annoucement result = restTemplate.getForObject(uri, Annoucement.class);
			if (result != null && result.getId() != null && result.getId() == announcementId) {
				if(activate(result)){
					System.out.println("announcement activated!");
					return result;
				}
			}
		} catch (Exception ex) {

		}
		System.out.println("announcement NOT activated!");
		return null;
	}

}
