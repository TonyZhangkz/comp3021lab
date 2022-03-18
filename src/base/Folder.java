package base;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.List;
public class Folder implements Comparable<Folder>,Serializable{
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		notes = new ArrayList<Note>();
		this.name = name;
		
	}
	public void addNote(Note new_note){
		notes.add(new_note);
	}
	public String getName() {
		return this.name;
	}
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note note:this.notes) {
			if (note instanceof TextNote)
				nText +=1;
			else
				nImage +=1;
		}
		return name + ":" + nText + ":" + nImage;
	}
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	@Override 
	public int compareTo(Folder f){
	 return this.name.compareTo(f.name);	
	}
	public void sortNotes() {
		Collections.sort(this.notes);
	} public List<Note> searchNotes(String keywords){
		//Splitting the keywords
		String[] keyword_list  = keywords.split(" ");
		ArrayList<ArrayList<String>> checklist = new ArrayList<ArrayList<String>>();
		ArrayList<Note> search_results = new ArrayList<Note>(); 
		for (int i=0;i<keyword_list.length;) {
			ArrayList<String> tmp_arr = new ArrayList<String>();
			if (i<keyword_list.length-2 && (keyword_list[i+1].equals("or") || keyword_list[i+1].equals("OR"))  ) 
				{
				
				tmp_arr.add(keyword_list[i].toLowerCase());
				tmp_arr.add(keyword_list[i+2].toLowerCase());
				i+=3;
			}
			else {
				tmp_arr.add(keyword_list[i].toLowerCase());
				tmp_arr.add(keyword_list[i].toLowerCase());
				i++;
				}
			checklist.add(tmp_arr);
		}
		for (int i=0;i<this.notes.size();i++) {
			//text case
			if(notes.get(i) instanceof TextNote){
				//check title
				boolean tmp_bool_title = true;
				for (ArrayList<String> pair: checklist) {
					if (!(notes.get(i).getTitle().toLowerCase().contains(pair.get(0)) || notes.get(i).getTitle().toLowerCase().contains(pair.get(1))))
						tmp_bool_title = false;
				}
				//check content
				boolean tmp_bool_content = true;
				System.out.println(((TextNote)this.notes.get(i)).content);
				for (ArrayList<String> pair: checklist) {
					if (!(((TextNote)notes.get(i)).content.toLowerCase().contains(pair.get(0)) || ((TextNote)notes.get(i)).content.toLowerCase().contains(pair.get(1))))
						tmp_bool_content = false;
				}
				if (tmp_bool_title || tmp_bool_content)
					search_results.add(notes.get(i));
			}
			//image case
			else { 
				boolean tmp_bool = true;
				for (ArrayList<String> pair: checklist) {
					if (!(notes.get(i).getTitle().toLowerCase().contains(pair.get(0)) || notes.get(i).getTitle().toLowerCase().contains(pair.get(1))))
						tmp_bool = false;
				}
				if (tmp_bool)
					search_results.add(notes.get(i));
			}
			
		}
		return search_results;
		
	}
}
