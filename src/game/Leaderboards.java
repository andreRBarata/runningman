package game;

import java.util.ArrayList;

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
		
		ReadFile r = new ReadFile("/Data/leaders.txt");
		
		r.open();
		data = r.read();
		r.close();
	}
	
	public int beatScore(float curScore)  {
		try  {
			int i=2;//2 because this is where the HiScores start
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
		int i = beatScore(curScore);
		
		if(i>1)  {
			String scurScore = Float.toString(curScore);
			setData(i, name);
			i++;
			setData(i, scurScore);
			
			try {
				WriteFile w = new WriteFile("/Data/leaders.txt");
				
				w.open();
				w.write(data);
				w.close();
			}
			catch(Exception e)  {
				System.out.println("Could not write to leaders.txt");
			}
		}
	}
	
}
