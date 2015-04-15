package game;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Leaderboards {
	
	private ArrayList<String> data = new ArrayList<String>();
	//Setters/Getters
	public String getData(int i) {
		return data.get(i);
	}
	
	public int getDataSize()  {
		return data.size();
	}

	public void setData(int i, String data) {
		this.data.set(i, data);
	}

	
	
	//order:
	//Get Scores,
	//if beatScores, update Scores (Update scores will do this however) its just for voiding exceptions
	
	public void getScores()  {
		
		ReadFile r = new ReadFile("Data/leaders.txt");
		
		r.open();
		r.read();
		r.close();
		
		for(int i=0; i<r.getDataSize(); i++)  {
			data.add(r.getData(i));
		}
	}
	
	public int beatScore(int curScore)  {
		//if score beat, returns int over boolean because wants multiple results and to use the integer returned
		try  {
			int i=3;//3 because this is where the HiScores start
			while(i<data.size())  {
				
				int hiScore = Integer.parseInt(data.get(i));
				
				if(curScore > hiScore)  {
					return i;
				}
				i+=2;
			}
			
			return -1;
		}
		catch(Exception e)  {
			System.out.println("Could not find leaders data");
			return -1;
		}
	}
	public void updateScore(int curScore, String name)  {
		int i = beatScore(curScore)+1-2;
		
		if(i>1)  {
			String scurScore = "" + curScore;
			for(int j=data.size()-1; j>i; j--)  {
				data.set(j, data.get(j-2));
			}
			
			setData(i, name);
			i++;
			setData(i, scurScore);
			
			try {
				WriteFile w = new WriteFile("Data/leaders.txt");
				w.open();
				w.write(data);
				w.close();
			}
			catch(Exception e)  {//Exceptions are done like this throughout because its not a big error and the extra work on here is not required, 
								 //as the methods themselves solve the errors, its just more a console thing for the user to read and then 
								 //fix themselves if they want to.
				System.out.println("Could not write to leaders.txt");
			}
		}
	}
	
	public static void showScores(GameContainer gc, Graphics g)  {
		
		float halfWidth = gc.getWidth() / 2;
		float halfHeight = gc.getHeight() / 2 - 100;
		
		g.setColor(Color.black);
		Leaderboards s = new Leaderboards();
		s.getScores();
		
		for(int i=0; i<s.getDataSize(); i+=2)  {
			g.drawString(s.getData(i), halfWidth - 80, halfHeight+20*i);
		}
		
		for(int i=1; i<s.getDataSize(); i+=2)  {
			g.drawString(s.getData(i), halfWidth + 50, halfHeight+20*(i-1));
		}
	}
	
	public static void resetScores()  {
		//use different variable to not conflict with the data in main Leaderboards class
		ArrayList<String> data2 = new ArrayList<String>();
		ReadFile r = new ReadFile("Data/defLeaders.txt");
		WriteFile w = new WriteFile("Data/leaders.txt");
		
		r.open();
		r.read();
		r.close();
		
		for(int i=0; i<r.getDataSize(); i++)  {
			data2.add(r.getData(i));
		}
		
		w.open();
		w.write(data2);
		w.close();
	}
}
