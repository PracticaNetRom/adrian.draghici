package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnouncementSender {

	public static Annoucement postAnnoucement(Annoucement announcement) {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/save.do";
			System.out.println("uri: " + uri + "\nData:\ncontent: " + announcement.getContent() + "\nownerEmail: "
					+ announcement.getOwnerEmail() + "\nownerLastName: " + announcement.getOwnerLastName()
					+ "\nownerFirstName: " + announcement.getOwnerFirstName() + "\nexpireDate: "
					+ announcement.getExpireDate() + "\ncategoryName: " + announcement.getCategoryName() + "\ntitle: "
					+ announcement.getTitle() + "\nlocation: " + announcement.getLocation() + "\nownerPhone: "
					+ announcement.getOwnerPhone() + "\nconfirmationCode: " + announcement.getConfirmationCode());
			RestTemplate restTemplate = new RestTemplate();
			Annoucement result = restTemplate.postForObject(uri, announcement, Annoucement.class);
			if (result != null && result.getId() != null) {
				System.out.println("new announcement data sent!");
				return result;
			}
		} catch (Exception ex) {

		}
		System.out.println("new announcement data NOT sent!");
		return null;
	}

}
