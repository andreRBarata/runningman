package game;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ReadFile {
	private Scanner x;
	Formatter y;
	
	String fileName;
	
	ReadFile(String fileName)  {
		this.fileName=fileName;
	}
	
	public void open()  {
		try  {
			x = new Scanner(fileName);
		}
		catch(Exception e)  {
			System.out.println("Could not find '"+fileName+"' file");
		}
		
	}
	public ArrayList<String> read()  {
		ArrayList<String> stringData = new ArrayList<String>();
		
		while(x.hasNext())  {
			stringData.add(x.next());
		}
		
		return stringData;
	}
	
	
	
	public void close()  {
		x.close();
	}
}
