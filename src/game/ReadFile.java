package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	//not much comments needed here, it's clear what this class is doing and the methods
	final File x;
	private Scanner r;
	ArrayList<String> data = new ArrayList<String>();
	
	ReadFile(String fileName)  {
		x = new File(fileName);
		
	}
	
	public void open()  {
		try  {
			if(!x.canRead())  {
				System.out.println("Creating the file because it does not exist");
				x.createNewFile();
				Leaderboards.resetScores();
			}
			r = new Scanner(x);
		}
		catch(Exception e)  {
			System.out.println("Some error existed and the file could not be created |" + x.getName());
		}
		
	}
	//read the file and add the information to data ArrayList
	public void read()  {
		while(r.hasNext())  {
			data.add(r.next());
		}
	}
	//close file
	public void close()  {
		r.close();
	}
	
	//getters for the data arraylist
	public String getData(int i)  {
		return data.get(i);
	}
	
	public int getDataSize()  {
		return data.size();
	}
}
