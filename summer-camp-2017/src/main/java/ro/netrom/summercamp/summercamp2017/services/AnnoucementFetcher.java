package ro.netrom.summercamp.summercamp2017.services;

import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnoucementFetcher {

	private static Annoucement[] getActiveAnnoucements(Annoucement[] annoucements) {
		ArrayList<Annoucement> annoucementsList = new ArrayList<Annoucement>();
		for (Annoucement annoucement : annoucements) {
			if (!annoucement.isStatus()) {
				annoucementsList.add(annoucement);
			}
		}
		annoucementsList.sort(new Comparator<Annoucement>() {
			@Override
			public int compare(Annoucement arg0, Annoucement arg1) {
				if (arg0.isAvailable()) {
					if (arg1.isAvailable()) {
						if (arg0.getCreateDate().after(arg1.getCreateDate())) {
							return -1;
						}
					} else {
						return -1;
					}
				} else {
					if (!arg1.isAvailable()) {
						if (arg0.getCreateDate().after(arg1.getCreateDate())) {
							return -1;
						}
					}
				}
				return 1;
			}
		});
		Object[] listItems = annoucementsList.toArray();
		annoucements = new Annoucement[listItems.length];
		for (int index = 0; index < listItems.length; index++) {
			annoucements[index] = (Annoucement) listItems[index];
		}
		return annoucements;
	}

	public Annoucement[] getAnnoucements() {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/list.do";
			RestTemplate restTemplate = new RestTemplate();
			Annoucement[] result = restTemplate.getForObject(uri, Annoucement[].class);
			System.out.println("announcements fetched!: " + result.length + " items");
			result = getActiveAnnoucements(result);
			System.out.println("announcements filtered!: " + result.length + " items");
			return result;
		} catch (Exception ex) {

		}
		System.out.println("announcements NOT fetched!");
		return null;
	}

	public Annoucement getAnnoucement(int announcementId) {

		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/getById.do?announcementId="
					+ announcementId;
			RestTemplate restTemplate = new RestTemplate();
			Annoucement result = restTemplate.getForObject(uri, Annoucement.class);
			if (result != null && result.getId() != null) {
				System.out.println("announcement fetched!");
				return result;
			}
		} catch (Exception ex) {

		}
		System.out.println("announcement NOT fetched!");
		return null;
	}
}
