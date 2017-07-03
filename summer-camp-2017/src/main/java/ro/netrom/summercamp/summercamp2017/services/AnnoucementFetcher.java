package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;

public class AnnoucementFetcher {
	
	public static Annoucement[] getAnnoucement()
	{
		final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/announcement/list.do";
		RestTemplate restTemplate = new RestTemplate();
		Annoucement[] result = restTemplate.getForObject(uri, Annoucement[].class);
		System.out.println("data fetched!");
		return result;
	}
}
