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

	Leaderboards(){}
	
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
	
	public int beatScore(float curScore)  {
		try  {
			int i=3;//2 because this is where the HiScores start
			while(i<data.size())  {
				
				float hiScore = Float.parseFloat(data.get(i));
				
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
	public void updateScore(float curScore, String name)  {
		int i = beatScore(curScore)+1-2;
		
		if(i>1)  {
			String scurScore = Float.toString(curScore);
			for(int j=i; j<getDataSize()-2; j++)  {
				data.set(j+2, data.get(j));
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
			catch(Exception e)  {
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
	
}
