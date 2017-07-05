package ro.netrom.summercamp.summercamp2017.data;

import java.util.Date;

public class Comment {

	private Integer id;
	private String content;
	private String name;
	private Integer announcementId;
	private Date createDate;
	private Integer parent;//comment id for quoted comment if this comment is comment for another comment
	private Comment[] children;

	public Comment() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Comment[] getChildren() {
		return children;
	}

	public void setChildren(Comment[] children) {
		this.children = children;
	}
	
}
