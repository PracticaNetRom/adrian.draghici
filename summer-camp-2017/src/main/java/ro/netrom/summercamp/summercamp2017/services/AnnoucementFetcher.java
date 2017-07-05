package ro.netrom.summercamp.summercamp2017.services;

import java.util.ArrayList;
import java.util.Comparator;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnoucementFetcher {

	private static Annoucement[] getActiveAnnoucements(Annoucement[] annoucements) {
		ArrayList<Annoucement> annoucementsList = new ArrayList<Annoucement>();
		for (Annoucement annoucement : annoucements) {
			if (true) {
				annoucementsList.add(annoucement);
			}
		}
		annoucementsList.sort(new Comparator<Annoucement>() {
			@Override
			public int compare(Annoucement arg0, Annoucement arg1) {
				if(arg0.isAvailable()){
					if(arg1.isAvailable()){
						if (arg0.getCreateDate().after(arg1.getCreateDate())) {
							return -1;
						}
					}else{
						return -1;
					}
				}else{
					if(!arg1.isAvailable()){
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

	public static Annoucement[] getAnnoucements() {
		final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/list.do";
		RestTemplate restTemplate = new RestTemplate();
		Annoucement[] result = restTemplate.getForObject(uri, Annoucement[].class);
		System.out.println("data fetched!: " + result.length + " items");
		result = getActiveAnnoucements(result);
		System.out.println("data filtered!: " + result.length + " items");
		return result;
	}
}
