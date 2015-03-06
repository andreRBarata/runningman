package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	
	final File x;
	private Scanner r;
	private String fileName;
	ArrayList<String> data = new ArrayList<String>();
	
	ReadFile(String fileName)  {
		this.fileName = new String(fileName);
		x = new File(fileName);
		
	}
	
	
	public void open()  {
		try  {
			if(x.canRead())  {
				System.out.println("File exists, no need to create");
				
			}
			else  {
				System.out.println("Creating the file because it does not exist");
				x.createNewFile();
			}
			r = new Scanner(x);
		}
		catch(Exception e)  {
			System.out.println("Some error existed and the file could not be created |" + fileName);
		}
		
	}
	
	public void read()  {
		while(r.hasNext())  {
			data.add(r.next());
		}
	}
	
	
	
	public void close()  {
		r.close();
	}
	
	
	public String getData(int i)  {
		return data.get(i);
	}
	
	public int getDataSize()  {
		return data.size();
	}
}
