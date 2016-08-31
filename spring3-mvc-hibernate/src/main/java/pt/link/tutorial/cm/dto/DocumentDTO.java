package pt.link.tutorial.cm.dto;

import java.io.Serializable;

import pt.link.tutorial.cm.util.CustomDate;

public class DocumentDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String author;
	private Integer length;
	private String extention;
	private CustomDate creationDate;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}
	/**
	 * @return the extention
	 */
	public String getExtention() {
		return extention;
	}
	/**
	 * @param extention the extention to set
	 */
	public void setExtention(String extention) {
		this.extention = extention;
	}
	/**
	 * @return the creationDate
	 */
	public CustomDate getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(CustomDate creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
