package game;

import java.util.ArrayList;

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
	//this is setting up variables
	private boolean hi = false; //to dictate if the user has  ahi score
	private Context context; //something
	private float score; //the score the user got
	public float centX;  //centre Width
	public float centY;  //centre Height
	private String playerName = new String("AAA");  //The players name
	
	
	ArrayList<Button> buttons = new ArrayList<Button>();//button array lists
	
	Leaderboards l = new Leaderboards();
	
	EndGame(float score)  {
		l.getScores();
		
		if(l.beatScore(score) != -1)  {
			hi = true;
			System.out.println("Hi Score was beat");
		}
		this.score = score;
		
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		context = new Context(gc, gc.getGraphics());
		//buttons2 = new ArrayList<Button>();
		
		Button startBtn = new Button(context,
				new Vector2f(gc.getWidth() / 2 - 75, 150f), Context.getImage("start.png"),
				Context.getImage("highStart.png"));
		
		startBtn.onClick(() -> {
			Audio.playSound("testSample.wav");
			game.enterState(0, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		});
		
		//buttons.add(startBtn);
		
		centX = gc.getWidth()/2;
		centY = gc.getHeight()/2;
		
		
		
		
		
		
		try  {
			Vector2f position = new Vector2f();
			//CallBack c = null;
			
			String imgUp = "triUp.png";
			String imgUp2 = "triUp.png";
			String imgDown = "triDown.png";
			String imgDown2 = "triDown2.png";
			
			position.set(centX+10, centY-50);
			buttons.add(new Button(context, position, Context.getImage(imgUp), Context.getImage(imgUp2)));
			position.set(centX+15, centY-50);
			buttons.add(new Button(context, position, Context.getImage(imgUp), Context.getImage(imgUp2)));
			position.set(centX+20, centY-50);
			buttons.add(new Button(context, position, Context.getImage(imgUp), Context.getImage(imgUp2)));
			
			position.set(centX+10, centY+50);
			buttons.add(new Button(context, position, Context.getImage(imgDown), Context.getImage(imgDown2)));
			position.set(centX+15, centY+50);
			buttons.add(new Button(context, position, Context.getImage(imgDown), Context.getImage(imgDown2)));
			position.set(centX+20, centY+50);
			buttons.add(new Button(context, position, Context.getImage(imgDown), Context.getImage(imgDown2)));
			
		}
		catch(Exception e)  {
			System.out.println("Unable to load image");
		}
	}
		
		/*for(int j=1; j<=3; j++)  {
			position.x = centX/2 + 10*j;
			position.y = centY/2-30;
			
			
			buttons.add(new Button(context, position, "", c));
		}
		for(int k=4; k<=6;k++)  {
			position.x = centX/2;
			position.y = centY/2+30;
			
			buttons.add(new Button(context, position, "", c));
		}
		
		
		
	}
	*/
	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		/*for (Button button : buttons) {

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
		}*/
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {	
		g.setColor(Color.orange);
		g.setBackground(Color.white);
		String playerScore = new String(Float.toString(score));
		
		showPlayerScore(gc, g, playerScore);
		if(hi)  {
			askName(gc,g);
		}
		
		
		showHiScores(gc, g);
	}
	
	public void showHiScores(GameContainer gc, Graphics g)  {
		Leaderboards read = new Leaderboards();
		read.getScores();
		
		for(int i=2; i<read.getDataSize(); i++)  {
		//System.out.println(read.getData(i));	
			
			if(i%2 == 0)  {
				g.drawString(read.getData(i), 10, 10+30*i-2);
			}
			else  {
				g.drawString(read.getData(i), 50, 10+30*(i-2));
			}
		}
	}
	
	public void showPlayerScore(GameContainer gc, Graphics g, String playerScore)  {
		g.drawString(playerScore, centX, centY / 3);
	}
	
	public void askName(GameContainer gc, Graphics g)  {
		
		//g.setFont(font);
		
		
		
		for(int i=0; i<buttons.size(); i++)  {
			buttons.get(i).display();
			//System.out.println(buttons.get(i).getPosition());
		}
		buttons.get(1).display();
		buttons.get(2).display();
		//buttons.get(3).display();
				//buttons.get(4).display();
				//buttons.get(5).display();
		
		
		
		g.setColor(Color.orange);
		g.drawString(playerName, centX, centY-20);
		
		
	}
	
	/*
	 * triU = new Arrow(centX, centY-40);
		triD = new Arrow(centX, centY);
		float[] fU = {triU.getX(), triU.getY(), triU.getW()/2+triU.getX(), triU.getH()/2-triU.getY(),triU.getW()/2-triU.getX(), triU.getH()/2-triU.getY()};
		float[] fD = {triD.getX(), triD.getY(), triD.getW()/2+triD.getX(), triD.getH()/2+triD.getY(),triU.getW()/2-triU.getX(), triU.getH()/2-triU.getY()};
		
		
	 * 
	 * 
	 */
	
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


