package base;
import java.util.ArrayList;
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
}
