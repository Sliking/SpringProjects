package pt.link.tutorial.cm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.link.libraries.data.domain.AbstractPersistentDomain;

@Entity
@Table(name="DOCUMENTS")
public class Document extends AbstractPersistentDomain{
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="LENGTH")
	private Integer length;
	
	@Column(name="EXTENTION")
	private String extention;
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
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
	 * @return the size
	 */
	public Integer getLength() {
		return length;
	}
	/**
	 * @param size the size to set
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
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
