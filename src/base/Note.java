package base;

import java.lang.String;
import java.util.Date;
import java.util.Objects;


public class Note {
	private Date date;
	private String title;
	public Note(String title) {
		this.title = title; 
		date = new Date();
	}
	public String getTitle() {
		return this.title;}
	
	public boolean equals(Note obj) {
		return obj.title == this.title;
	}

		


}
	
	
