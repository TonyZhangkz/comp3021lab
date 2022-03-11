package base;

import java.lang.String;
public class TextNote extends Note{
	String content;
	public TextNote(String title) {
		super(title);
		this.content = new String();
	}
	public TextNote(String title,String content) {
		super(title);
		this.content=content;
	}
	

}