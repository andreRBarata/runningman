package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;

public class WriteFile {
	
	final File x;
	private Formatter r;
	
	public WriteFile(String fileName)  {
		x = new File(fileName);
	}
	

	public void open()  {
		try  {
			//if readable, if not create the file.
			if(x.canRead())  {
				System.out.println("File exists, Reading from it now... ...");
			}
			else  {
				System.out.println("Creating the file because it does not exist");
				//put something into the scoreboards after creating it
				x.createNewFile();
				Leaderboards.resetScores();
			}
			//open the file with r because it is able to be read now, as it exists/created now
			r = new Formatter(x);
		}
		catch(Exception e)  {
			System.out.println("Some error existed and the file could not be created |" + x.getName());
		}
	}
	
	public void write(ArrayList<String> data)  {
		
		try  {
			for(int i=0; i<data.size()-1; i+=2)  {
				r.format("%s %s\n", data.get(i), data.get(i+1));
			}
		}
		catch(Exception e)  {
			System.out.println("Writing error to " + x.getAbsolutePath());
		}
		
	}
	
	public void close()  {
		r.close();
	}
}
