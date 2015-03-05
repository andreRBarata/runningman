package game;

import java.util.ArrayList;
import java.util.Formatter;

public class WriteFile {
	Formatter y;
	//ArrayList<String> data
	String fileName;
	
	WriteFile(String fileName)  {
		this.fileName=fileName;
	}
	public void open()  {
		try  {
			y = new Formatter(fileName);
		}
		catch(Exception e)  {
			System.out.println("Unable to open leaders file for Writing");
		}
	}
	
	public void write(ArrayList<String> data)  {
		
		for(int i=0; i<data.size(); i++)  {
			y.format("%s", data.get(i));
		}
	}
	
	public void close()  {
		y.close();
	}
}
