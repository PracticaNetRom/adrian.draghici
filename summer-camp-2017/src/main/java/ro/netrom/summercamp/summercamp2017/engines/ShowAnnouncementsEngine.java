package ro.netrom.summercamp.summercamp2017.engines;


import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;

import ro.netrom.summercamp.summercamp2017.data.Annoucement;
import ro.netrom.summercamp.summercamp2017.data.Category;
import ro.netrom.summercamp.summercamp2017.services.AnnoucementFetcher;
import ro.netrom.summercamp.summercamp2017.services.CategoryFetcher;

@Component
public class ShowAnnouncementsEngine {

	private Annoucement[] announcements = null;
	private int pageNumber = 1;

	public ShowAnnouncementsEngine() {

	}

	private boolean containsSearchKeywords(String searchBy, String search, Annoucement target) {
		if (searchBy != null && search != null && !searchBy.contentEquals("") && !search.contentEquals("")
				&& target != null) {
			switch (searchBy) {
			case "1":
				return target.getLocation() != null && target.getLocation().toLowerCase().contains(search.toLowerCase());
			case "3":
				return (target.getOwnerFirstName() != null && target.getOwnerFirstName().toLowerCase().contains(search.toLowerCase()))
						|| (target.getOwnerLastName() != null && target.getOwnerLastName().toLowerCase().contains(search.toLowerCase()));
			case "4":
				return target.getTitle() != null && target.getTitle().toLowerCase().contains(search.toLowerCase());
			default:
				return target.getContent() != null && target.getContent().toLowerCase().contains(search.toLowerCase());
			}
		}
		return false;
	}

	private Annoucement[] filterAnnouncements(ModelMap model, HttpServletRequest request) {
		String selectedCategory = request.getParameter("selectedCategory");
		String searchBy = request.getParameter("searchBy");
		String search = request.getParameter("search");
		model.addAttribute("selectedCategory", selectedCategory);
		if (searchBy != null && !searchBy.contentEquals("")) {
			model.addAttribute("searchBy", searchBy);
		} else {
			model.addAttribute("searchBy", "1");
		}
		model.addAttribute("search", search);
		if ((selectedCategory != null && !selectedCategory.contentEquals("all") && !selectedCategory.contentEquals(""))
				|| (searchBy != null && search != null && !searchBy.contentEquals("") && !search.contentEquals(""))) {
			ArrayList<Annoucement> annoucementsList = new ArrayList<Annoucement>();
			for (Annoucement annoucement : announcements) {
				if (searchBy != null && search != null && !searchBy.contentEquals("") && !search.contentEquals("")) {
					if (containsSearchKeywords(searchBy, search, annoucement)) {
						if (selectedCategory != null && !selectedCategory.contentEquals("all")
								&& !selectedCategory.contentEquals("")) {
							if (annoucement.getCategoryName()!=null && annoucement.getCategoryName().toLowerCase().contentEquals(selectedCategory.toLowerCase())) {
								annoucementsList.add(annoucement);
							}
						} else {
							annoucementsList.add(annoucement);
						}
					}
				} else {
					if (selectedCategory != null && !selectedCategory.contentEquals("all")
							&& !selectedCategory.contentEquals("")) {
						if (annoucement.getCategoryName()!=null && annoucement.getCategoryName().toLowerCase().contentEquals(selectedCategory.toLowerCase())) {
							annoucementsList.add(annoucement);
						}
					}
				}
			}
			Object[] listItems = annoucementsList.toArray();
			Annoucement[] filteredAnnoucements = new Annoucement[listItems.length];
			for (int index = 0; index < listItems.length; index++) {
				filteredAnnoucements[index] = (Annoucement) listItems[index];
			}
			System.out.println("filtered annoucements by category and/or search keywords: " + listItems.length);
			return filteredAnnoucements;
		}
		model.addAttribute("selectedCategory", "all");
		return announcements;
	}

	public void showAnnouncements(ModelMap model, HttpServletRequest request) {
		try {
			int pages = 0, from, to, total = 0;
			pageNumber = ServletRequestUtils.getIntParameter(request, "pageNr", 1);
			pageNumber = pageNumber <= 0 ? 1 : pageNumber;
			if (announcements == null || pageNumber == 1) {
				announcements = new AnnoucementFetcher().getAnnoucements();
			}
			Annoucement[] filteredAnnoucements = filterAnnouncements(model, request);
			total = filteredAnnoucements.length;
			if (total % 10 > 0) {
				pages = total / 10 + 1;
			} else {
				pages = total / 10;
			}
			if (total < (pageNumber - 1) * 10) {
				from = total;
			} else {
				from = (pageNumber - 1) * 10;
			}
			if (total < (pageNumber * 10) - 1) {
				to = total;
			} else {
				to = pageNumber * 10;
			}
			Annoucement[] announcementsOnPage = Arrays.copyOfRange(filteredAnnoucements, from, to);
			model.addAttribute("announcements", announcementsOnPage);
			Category[] categories = new CategoryFetcher().getCategoryes();
			model.addAttribute("categories", categories);
			model.addAttribute("pageNr", pageNumber);
			model.addAttribute("pages", pages);
			model.addAttribute("from", from + 1);
			model.addAttribute("to", to);
			model.addAttribute("total", total);
		} catch (Exception ex) {
			System.out.println("ex:" + ex);
			model.addAttribute("announcements", null);
		}
	}

	public void showAnnouncement(ModelMap model, HttpServletRequest request) {
		try {
			int announcementId = Integer.parseInt(request.getParameter("announcementId"));
			Annoucement annoucement = new AnnoucementFetcher().getAnnoucement(announcementId);
			if (annoucement != null && !annoucement.isStatus()) {
				model.addAttribute("announcement", annoucement);
			} else {
				model.addAttribute("announcement", null);
			}
		} catch (Exception ex) {
			model.addAttribute("announcement", null);
			System.out.println("invalid announcementId");
		}
	}
}
