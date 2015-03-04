package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndGame extends BasicGameState {
	
	private boolean hi = false;
	private float score;
	public float centX;
	public float centY;
	int location = 0;
	private String playerName = new String("AAA");
	
	Leaderboards l = new Leaderboards();
	
	EndGame(float score)  {
		
		l.getScores();
		if(l.beatScore(score) != -1)  {
			hi = true;
		}
		this.score = score;
	}
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		centX = gc.getWidth()/2;
		centY = gc.getHeight()/2;
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {

		//how can i run specific functions, here not just always the one
		
		String playerScore = new String(Float.toString(score));
		
		
		showPlayerScore(gc, g, playerScore);
		if(!hi)  {
			askName(gc, g);
			
		}
		
		//showScores();
	}
	
	
	public void showScores()  {
		
	}
	
	public void showPlayerScore(GameContainer gc, Graphics g, String playerScore)  {
		g.drawString(playerScore, centX, centY);
	}
	
	public void askName(GameContainer gc, Graphics g)  {
		
		
		g.drawString(playerName, centX, centY-20);
		
		
	}
	
	public void keyPressed(int key, char c)  {
		
		
		Keyboard.KEY_LEFT
		
		//203 LEFT
		//205 RIGHT
		//200 UP
		//208 DOWN
	}
	
	
	/*
	    String output;
			if(hi)  {
				output = new String("You got a Highscore of: " + score);
			}
			else  {
				output = new String("You Scored: " + score);
			}
		
			g.drawString(output, centX, centY/2);
		
			for(int i=3; i<=l.getDataSize(); ++i)  {
				g.drawString(l.getData(i-1), centX, centY/2+i);
			}
	 */
	
	
	public boolean getHi()  {
		return hi;
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
}
