package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class EndGame extends BasicGame {
	
	private boolean hi = false;
	private float score;
	public float centX;
	public float centY;
	int location = 0;
	
	
	Leaderboards l = new Leaderboards();
	
	EndGame(float score)  {
		super("Running Man");
		
		l.getScores();
		if(l.beatScore(score) != -1)  {
			hi = true;
		}
		this.score = score;
	}
	@Override
	public void init(GameContainer gc) throws SlickException {
		centX = gc.getWidth()/2;
		centY = gc.getHeight()/2;
		
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {

		//how can i run specific functions, here not just always the one
		
		if(hi)  {
			askName(gc, g);
		}
		
		//showScores();
	}
	
	public void showScores()  {
		
	}
	
	public void askName(GameContainer gc, Graphics g)  {
		
		
		
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
	
	
	
	
	public static void start() {
		try	{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new EndGame(1));
			appgc.setDisplayMode(800, 500, false);
			//appgc.setShowFPS(false);
			appgc.start();
		}
		catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void main(String[] args)  {
		start();
	}
	
	
	public boolean getHi()  {
		return hi;
	}
	
}
