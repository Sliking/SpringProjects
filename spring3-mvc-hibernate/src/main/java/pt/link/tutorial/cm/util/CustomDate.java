package pt.link.tutorial.cm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDate extends Date{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomDate(Date date){
		super(date.getTime());
	}
	
	public String toString(){
		
		SimpleDateFormat customFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		return customFormat.format(this);

	}
}
