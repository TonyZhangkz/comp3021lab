package base;

import java.io.File;
import java.lang.String;

//file i/o
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
public class TextNote extends Note implements Serializable{

	String content;
	private static final long serialVersionUID = 1L;
	public TextNote(String title) {
		super(title);
		this.content = new String();
	}
	public TextNote(String title,String content) {
		super(title);
		this.content=content;
	}
	//import constructor
	/**
	 * load a TextNote from File f
	 * 
	 * the tile of the TextNote is the name of the file
	 * the content of the TextNote is the content of the file
	 * 
	 * @param File f 
	 */

	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	//helper for import constructor
	/**
	 * get the content of a file
	 * 
	 * @param absolutePath of the file
	 * @return the content of the file
	 */

	private String getTextFromFile(String absolutePath) {
		String result = "";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(absolutePath);
			ois = new ObjectInputStream(fis);
			result = (String)ois.readObject();
			ois.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void exportTextToFile(String pathFolder) {
		if(pathFolder == "") {
			pathFolder = ".";
		}
		File file = new File(pathFolder+File.separator+this.getTitle().replaceAll(" ","_")+".txt");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file.getAbsolutePath());
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.content);
			oos.close();



			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}