package game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
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
	public Context context;
	private int score;
	public float centX;
	public float centY;
	float halfWidth;
	float halfHeight; 
	int index = 0;
	
	private char[] playerName = {'A','A','A'};
	
	Leaderboards l = new Leaderboards();
	
	EndGame(int score)  {
		l.getScores();
		
		hi = false;
		
		if(l.beatScore(score) != -1)  {
			hi = true;
		}
		this.score = (int)score;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		context = new Context(gc, gc.getGraphics());
		buttons = new ArrayList<Button>();
		
		Button backBtn = new Button(context,
				new Vector2f(gc.getWidth() / 2 - 75, 450f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		
		backBtn.onClick(() -> {
			if(!Context.mute)
			Audio.playSound("testSample.wav", MainMenu.mute);
			
			if(hi)  {
				submit();
			}
			
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

	
	
	public void showPlayerScore(GameContainer gc, Graphics g, String playerScore)  {
		
		g.drawString(playerScore, centX, centY);
		
	}
	
	public void askName(GameContainer gc, Graphics g)  {
		
		for(int i=0; i<playerName.length; i++)  {
			String c = Character.toString(playerName[i]);
			g.setColor(Color.black);
			if(index == i)  {
				g.setColor(Color.red);
			}
			g.drawString(c, centX-10 +10*i, centY+20);
		}
		
		
	}
	
	public void keyPressed(int key, char c)  {
		
		char temp = checkWhiteSpace(c, playerName[index]);
	
		playerName[index] = temp;
		index = (index+1) % playerName.length;
		
		//203 LEFT
		//205 RIGHT
		//200 UP
		//208 DOWN
	}
	
	public void submit()  {
		Leaderboards s = new Leaderboards();
		
		s.getScores();
		String m = "";
		for(int i=0; i<playerName.length; i++)  {
			m = m + playerName[i];
		}
		
		s.updateScore(score, m);
	}
	
	public char checkWhiteSpace(char c, char temp)  {
	
		if(c >= 'a' && c <= 'Z')  {
			return temp;
		}
		return c;
	}
	
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
		context.background(gc, g, "testBackground.png");
		
		for (Button button : buttons) {
			button.display();
		}
		
		context.setImage(gc, g, "score.png",  halfHeight, halfWidth - 200, 200f,85f);
		//context.setImage(gc, g, "image.png",  halfHeight - 100, halfWidth - 100, 200f,85f);
	    g.setColor(Color.black);
		String playerScore = new String(Integer.toString((int)score));
		g.drawString(playerScore, halfWidth + 40, halfHeight - 68);
		
		//showPlayerScore(gc, g, playerScore);
		
		if(hi)  {
			g.drawString("You have achieve a highscore \n  please enter your name!", halfWidth - 110, halfHeight + 50);
			askName(gc, g);
		}
		else
		{
			g.drawString("Failed to achieve highscore \n  better luck next time!", halfWidth - 110, halfHeight + 50);
		}
	
		
		//Leaderboards.showScores(gc, g);
		
	    //context.setImage(gc, g, "back.png", 200f,200f,50f,50f);
	}
	
}
