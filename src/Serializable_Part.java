import java.io.*;

import jm.music.data.Part;


public class Serializable_Part extends Part implements Serializable{
	
	public Serializable_Part (String name, int instrument, int channel){
		super(name, instrument, channel);
	}

	private void writeObject(ObjectOutputStream OutputStream) throws IOException 
	{
		OutputStream.defaultWriteObject();
		}
	
	private void readObject(ObjectInputStream InputStream) throws ClassNotFoundException, IOException 
	{
		InputStream.defaultReadObject();
		}
	
}
