package game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Instructions extends BasicGameState {

	@Override
	public void init(GameContainer gc, StateBasedGame g)  throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		 
		ArrayList<String> data = new ArrayList<String>();
		ReadFile r = new ReadFile("Data/Instructions.txt");
		r.open(); 
		r.read();
		r.close();
		
		for(int i=0; i<r.getDataSize(); i++)  {
			data.set(i, r.getData(i));
		}
		
		for(int i=0; i<data.size(); i++)  {
			g.drawString(data.get(i), 10+10*i, 20);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)  throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}
	
}
