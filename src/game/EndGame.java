package game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
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
	float halfWidth;
	float halfHeight; 
	int location = 0;
	private String playerName = new String("AAA");
	TrueTypeFont font;
	
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
		buttons = new ArrayList<Button>();
		
		Button backBtn = new Button(context,
				new Vector2f(gc.getWidth() / 2 - 75, 350f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		
		backBtn.onClick(() -> {
			Audio.playSound("testSample.wav");
			
			game.enterState(0, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		});
		
		buttons.add(backBtn);
		
		centX = gc.getWidth()/2;
		centY = gc.getHeight()/2;
		
		halfHeight = gc.getHeight() /2 ;
		halfWidth = gc.getWidth() /2 ;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		for (Button button : buttons) {

			if (button.containsPoint(Mouse.getX(), gc.getHeight() - Mouse.getY())) {
				button.SetMouseOver(true);

				if (Mouse.isButtonDown(0)) {
					button.setClicked(true);
				}
				else {
					button.setClicked(false);
				}

			}
			else {
				button.SetMouseOver(false);
				button.setClicked(false);
			}
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		context.background(gc, g, "testBackground.png");
		context.background(gc, g, "testTitle.png");
		
		for (Button button : buttons) {
			button.display();
		}
		
		//how can i run specific functions, here not just always the one
			
	    context.setImage(gc, g, "score.png",  halfHeight, halfWidth - 200, 200f,85f);
	    
	    g.setColor(Color.black);
		String playerScore = new String(Float.toString(score));
		g.drawString(playerScore, halfWidth + 35, halfHeight - 60);
		
	//	showPlayerScore(gc, g, playerScore);
		//if(hi)  {
			askName(gc, g);

	}
	
	public void showScores()  {
		
	}
	
	public void showPlayerScore(GameContainer gc, Graphics g, String playerScore)  {
		g.drawString(playerScore, halfWidth + 50, halfHeight - 100);
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
