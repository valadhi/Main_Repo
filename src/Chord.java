import java.io.Serializable;

import jm.music.data.*;


/**
 * Class containing chord types
 * @author Vlad
 *
 */
public class Chord implements Serializable {
	
	private String name;
	private boolean type;
	private int root;
	
	public Chord(String _name, int _root, boolean _type){
		root = _root;
		type = _type;
		name = _name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean getType(){
		return type;
	}
	
	public int getRoot(){
		return root;
	}
	
	public void setRoot(int _root){
		root = _root;
	}
	
	public void setType(boolean _type){
		type = _type;
	}
	
}
