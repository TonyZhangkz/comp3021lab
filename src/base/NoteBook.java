package base;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.String;
public class NoteBook {

	private ArrayList<Folder> folders;
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
		return search_results;
	}
}
