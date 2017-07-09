package ro.netrom.summercamp.summercamp2017.services;

import org.springframework.web.client.RestTemplate;

import ro.netrom.summercamp.summercamp2017.data.Category;

public class CategoryFetcher {

	public CategoryFetcher() {

	}

	public Category[] getCategoryes() {
		try {
			final String uri = "http://summercamp.api.stage03.netromsoftware.ro/api/categories/list.do";
			RestTemplate restTemplate = new RestTemplate();
			Category[] result = restTemplate.getForObject(uri, Category[].class);
			System.out.println("categoryes fetched!: " + result.length + " items");
			return result;
		} catch (Exception ex) {
			System.out.println("categoryes NOT fetched!");
		}
		return null;
	}

}
