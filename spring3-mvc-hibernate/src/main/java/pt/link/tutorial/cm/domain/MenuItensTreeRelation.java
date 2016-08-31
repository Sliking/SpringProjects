package pt.link.tutorial.cm.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MENU_ITENS_TREE_RELATIONS")
public class MenuItensTreeRelation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="ID_PARENT")
	private Integer parentId;
	
	@Column(name="ID_CHILD")
	private Integer childId;
	
	@ManyToOne
    @JoinColumn(name="ID_PARENT", referencedColumnName = "ID", insertable = false, updatable = false)
	private MenuItem parentItem;
	
	@OneToMany
    @JoinColumn(name="ID", referencedColumnName = "ID_CHILD", insertable = false, updatable = false)
	private Set<MenuItem> childItems = new HashSet<MenuItem>(0);


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
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the childId
	 */
	public Integer getChildId() {
		return childId;
	}

	/**
	 * @param childId the childId to set
	 */
	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	/**
	 * @return the parentItem
	 */
	public MenuItem getParentItem() {
		return parentItem;
	}

	/**
	 * @param parentItem the parentItem to set
	 */
	public void setParentItem(MenuItem parentItem) {
		this.parentItem = parentItem;
	}

	/**
	 * @return the childItems
	 */
	public Set<MenuItem> getChildItems() {
		return childItems;
	}

	/**
	 * @param childItems the childItems to set
	 */
	public void setChildItems(Set<MenuItem> childItems) {
		this.childItems = childItems;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MenuItensTreeRelation [id=" + id + ", parentId=" + parentId + ", childId=" + childId + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((childId == null) ? 0 : childId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MenuItensTreeRelation))
			return false;
		MenuItensTreeRelation other = (MenuItensTreeRelation) obj;
		if (childId == null) {
			if (other.childId != null)
				return false;
		} else if (!childId.equals(other.childId))
			return false;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		return true;
	}
	
}
