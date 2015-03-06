package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class WriteFile {
	
	//ArrayList<String> data
	
	final File x;
	private Formatter r;
	private String fileName;
	ArrayList<String> data = new ArrayList<String>();
	
	public WriteFile(String fileName)  {
		this.fileName = fileName;
		x = new File(this.fileName);
	}
	

	public void open()  {
		try  {
			if(x.canRead())  {
				System.out.println("File exists, Reading from it now... ...");
			}
			else  {
				System.out.println("Creating the file because it does not exist");
				x.createNewFile();
			}
			r = new Formatter(x);
		}
		catch(Exception e)  {
			System.out.println("Some error existed and the file could not be created |" + fileName);
		}
	}
	
	public void write(ArrayList<String> data)  {
		this.data = data;
		try  {
			for(int i=0; i<data.size()-1; i+=2)  {
				r.format("%s %s\n", data.get(i), data.get(i+1));
			}
		}
		catch(Exception e)  {
			System.out.println("Writing error");
		}
		
	}
	
	public void close()  {
		r.close();
	}
}
