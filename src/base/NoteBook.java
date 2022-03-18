package base;
import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import java.lang.String;

//serialization
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements Serializable{

	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;
	public NoteBook() {
		this.folders = new ArrayList<Folder>();
	}
	public boolean createTextNote(String folderName,String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);

	}
	public boolean createTextNote(String folderName,String title,String content) {
		TextNote note = new TextNote(title,content);
		return insertNote(folderName,note);

	}
	public boolean createImageNote(String folderName,String title) {
		ImageNote note = new ImageNote(title);
		
		return insertNote(folderName,note);

	}
	public ArrayList<Folder> getFolders(){
		return this.folders;
	}
	public boolean insertNote(String folderName, Note note) {
		Folder f_test = null;
		for (Folder folder : this.folders) {
			if (folder.getName().equals(folderName))
				f_test = folder;
		}
		if (f_test==null) {
			f_test = new Folder(folderName);
			folders.add(f_test);
		}
		for (Note n : f_test.getNotes()) {
			if (n.equals(note)) {
				System.out.println("Creating note "+note.getTitle()+" under folder "+folderName+" failed");
				return false;
			}
		}
		f_test.addNote(note);
		return true;
		
	}
	public void sortFolders() {
		for (Folder f:this.folders)
			f.sortNotes();
		Collections.sort(this.folders);
	}
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> search_results = new ArrayList<Note>();
		for (Folder f:this.folders) {
			search_results.addAll(f.searchNotes(keywords));
		}
		return search_results;}
	//serialization
	/**
	 * method to save the NoteBook instance to file
	 * 
	 * @param file, the path of the file where to save the object serialization
	 * @return true if save on file is successful, false otherwise
	 */

	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			return true;
			
		}catch(Exception e) {
			 e.printStackTrace();
			 return false;
		}}
	//initialize loader
	/**
	 * 
	 * Constructor of an object NoteBook from an object serialization on disk
	 * 
	 * @param file, the path of the file for loading the object serialization
	 */

	public NoteBook(String file) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			NoteBook nb_copy =  (NoteBook)ois.readObject();
			//deep copy
			this.folders = nb_copy.getFolders();
			
			//close stream
			ois.close();
			}
		catch(Exception e) {
			e.printStackTrace();
			this.folders=null;
		}}
	
	
	
}
