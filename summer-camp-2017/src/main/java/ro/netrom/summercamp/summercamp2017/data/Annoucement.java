package ro.netrom.summercamp.summercamp2017.data;

import java.util.Calendar;
import java.util.Date;

public class Annoucement {

	private Integer id;
	private String title;
	private String content;
	private String location;
	private boolean status;
	private String confirmationCode;
	private Date createDate;
	private Date expireDate;
	private String ownerEmail;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerPhone;
	private String categoryName;
	private String categoryDescription;

	private boolean available;

	public Annoucement() {

	}

	public Annoucement(String ownerFirstName, String ownerLastName, String ownerEmail, String ownerPhone, String title,
			String location, int valability, String categoryName, String categoryDescription,
			String confirmationCode, String content) {
		this.title=title;
		this.location=location;
		this.confirmationCode=confirmationCode;
		Calendar cal = Calendar.getInstance();
		this.createDate=cal.getTime();
		cal.add(Calendar.DATE, valability);
		this.expireDate=cal.getTime();
		this.ownerEmail=ownerEmail;
		this.ownerFirstName=ownerFirstName;
		this.ownerLastName=ownerLastName;
		this.ownerPhone=ownerPhone;
		this.categoryName=categoryName;
		this.categoryDescription=categoryDescription;
		this.content=content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
		Calendar cal = Calendar.getInstance();
		available = expireDate.after(cal.getTime());
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public boolean isAvailable() {
		return available;
	}

}
