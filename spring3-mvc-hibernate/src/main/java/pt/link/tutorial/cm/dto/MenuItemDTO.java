package pt.link.tutorial.cm.dto;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDTO implements Comparable<MenuItemDTO>{
	
	private Integer id;
	
	private Integer position;
	
	private String href;
	
	private String label;
	
	private List<MenuItemDTO> childs = new ArrayList<MenuItemDTO>();
	
	private Integer parentId;

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
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the childs
	 */
	public List<MenuItemDTO> getChilds() {
		return childs;
	}

	/**
	 * @param childs the childs to set
	 */
	public void setChilds(List<MenuItemDTO> childs) {
		this.childs = childs;
	}
	
	/* Useful Methods */
	public boolean hasChilds() {
		return !childs.isEmpty();
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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

	public String toString(){
//		
		boolean selectable = !href.equals("#");
		
		String hrefOrSelectable = selectable ? "\"href\": \""+href+"\""
										: "\"selectable\": "+ selectable;
		
		String nodes = childs.isEmpty() ? "" : "," + getNodesToString();
		
		return "{"
	            + "\"text\": \""+label+"\","
	            + hrefOrSelectable
	            + nodes
	          +"}";
		
	}
	
	/* Useful private methods */
	private String getNodesToString(){
		
		StringBuffer toReturn = new StringBuffer("\"nodes\": [");
		
		String comma = "";
		for(MenuItemDTO listElement : childs){
			toReturn.append(comma);
			comma = ",";
			toReturn.append(listElement.toString());
		}
		
		toReturn.append("]");
		
		return toReturn.toString();
	}

	@Override
	public int compareTo(MenuItemDTO o) {
		   int result = 0;
		   
		   //First Position
		   result = Integer.compare(this.getPosition(), o.getPosition());
		   
		   if(result == 0) {
			   //Second Label
			   Collator stringComparetor = Collator.getInstance();
			   
			   result = stringComparetor.compare(this.getLabel(), o.getLabel());
			   
			   if(result == 0){
				   //Last Id
				   result =  Integer.compare(this.getId(), o.getId());
			   }
		   }
		   
		   return result;
	}
}
