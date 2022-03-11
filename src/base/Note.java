package base;

import java.lang.String;
import java.util.Date;


public class Note implements Comparable<Note>{
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
	
	@Override
	public int compareTo(Note o) {
		return this.date.compareTo(o.date);
	}
	
	public String toString() {
		return this.date.toString() + '\t' + this.title;
	}

		


}
	
	
