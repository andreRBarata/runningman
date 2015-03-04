package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EndGame extends BasicGameState {
	private ArrayList<Button> buttons;
	private boolean hi = false;
	private Context context;
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
		context = new Context(gc, gc.getGraphics());
		Button startBtn = new Button(context,
				new Vector2f(gc.getWidth() / 2 - 75, 150f), Context.getImage("start.png"),
				Context.getImage("highStart.png"));
		startBtn.onClick(() -> {
			Audio.playSound("testSample.wav");
			game.enterState(0, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		});
		
		centX = gc.getWidth()/2;
		centY = gc.getHeight()/2;
		
		buttons = new ArrayList<Button>();
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		for (Button button : buttons) {

			if (button.containsPoint(Mouse.getX(), Mouse.getY())) {
				button.SetMouseOver(true);

				if (Mouse.isButtonDown(0))
					button.setClicked(true);

				else
					button.setClicked(false);

			} else {
				button.SetMouseOver(false);
				button.setClicked(false);
			}

		}
		
		//how can i run specific functions, here not just always the one
		g.setColor(Color.orange);
		String playerScore = new String(Float.toString(score));
		g.drawString(playerScore, 120, 120);
		
		showPlayerScore(gc, g, playerScore);
		//if(hi)  {
			askName(gc, g);
			
		//}
		
		//showScores();
	}
	
	
	public void showScores()  {
		
	}
	
	public void showPlayerScore(GameContainer gc, Graphics g, String playerScore)  {
		g.drawString(playerScore, 120, 120);
	}
	
	public void askName(GameContainer gc, Graphics g)  {
		
		
		g.drawString(playerName, centX, centY-20);
		
		
	}
	
	public void keyPressed(int key, char c)  {
		
		//203 LEFT
		//205 RIGHT
		//200 UP
		//208 DOWN
	}
	
	
	/*
	    String output;
			if(hi)  {
				output = new String("You got a Highscore of: " + score);
			}2
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
