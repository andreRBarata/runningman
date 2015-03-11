package game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {
	Context context;
	int height;
	int width;
	int divideBy = 2;
	int halfWidth;
	int halfHeight;
	float scale = 0.3f;
	ArrayList<Button> mainButtons = new ArrayList<Button>();
	ArrayList<Button> optionsButtons = new ArrayList<Button>();
	ArrayList<Button> currentButtons = new ArrayList<Button>();
	ArrayList<Button> audioButtons = new ArrayList<Button>();
	ArrayList<Button> scoreButtons = new ArrayList<Button>();
	ArrayList<Button> instructionsButtons = new ArrayList<Button>();
	//TrueTypeFont font;

	// void resize(500,500);
	boolean mainMenu = true;
	boolean options = false;
	boolean audioMenu = false;
	boolean instructions = false;
	boolean scores = false;
	StateBasedGame game;
	//Font font;

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		buttons(gc);
		this.game = game;
	}

	public void buttons(GameContainer gc) throws SlickException {
		halfWidth = gc.getWidth() / divideBy;
		halfHeight = gc.getHeight() / divideBy;
		height = gc.getHeight();
		width = gc.getWidth();

		float heightOffset = 0;
		float heightIncr = 100f;
		context = new Context(gc, gc.getGraphics());

		currentButtons = mainButtons;
		// buttons =

		Button audioBtn = new Button(context,
				new Vector2f(width / 2 - 75, 150f), Context.getImage("audio.png"),
				Context.getImage("highAudio.png"));
		audioBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			audioMenu = true;
			Context.mute = Context.mute = true;
			if(Context.mute)
			Audio.playSound("testSample.wav");

				currentButtons = audioButtons;
		});

		Button screenBtn = new Button(context, new Vector2f(width / 2 - 75,
				250f), Context.getImage("screenSize.png"),
				Context.getImage("highScreenSize.png"));
		screenBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			Audio.playSound("testSample.wav");
			if(context.mute)
			MainGameApplication.toggleFullScreen();
		});

		Button optionsBackBtn = new Button(context,
				new Vector2f(width / 2 - 75, 450f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		optionsBackBtn.onClick(() -> {
			options = false;
			mainMenu = true;
			if(context.mute)
			Audio.playSound("testSample.wav");
			
				currentButtons = mainButtons;
			
			// buttons.add(startBtn);

		});
		
		Button instructionsBackBtn = new Button(context,
				new Vector2f(width / 2 - 75, 450f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		instructionsBackBtn.onClick(() -> {
			options = false;
			mainMenu = false;
			instructions = false;
			if(context.mute)
			Audio.playSound("testSample.wav");
			
				currentButtons = mainButtons;
			
			// buttons.add(startBtn);

		});
		
		
		Button scoreBackBtn = new Button(context,
				new Vector2f(width / 2 - 75, 450f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		scoreBackBtn.onClick(() -> {
			options = false;
			mainMenu = true;
			scores = false;
			if(context.mute)
			Audio.playSound("testSample.wav");
			
				currentButtons = mainButtons;
			
			// buttons.add(startBtn);

		});
		
		Button audioBackBtn = new Button(context,
				new Vector2f(width / 2 - 75, 350f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		audioBackBtn.onClick(() -> {
			options = false;
			mainMenu = true;
			if(context.mute)
			Audio.playSound("testSample.wav");
			
			currentButtons = optionsButtons;
			
			// buttons.add(startBtn);

		});
		
		Button startBtn = new Button(context,
				new Vector2f(width / 2 - 75, 150f), Context.getImage("start.png"),
				Context.getImage("highStart.png"));
		startBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			if(context.mute)
			Audio.playSound("testSample.wav");
			game.enterState(1, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		});

		Button scoresBtn = new Button(context, new Vector2f(width / 2 - 75,
				250f), Context.getImage("highScores.png"),
				Context.getImage("highHighScores.png"));
		scoresBtn.onClick(() -> {
			options = false;
			mainMenu = false;
			scores = true;
			
			currentButtons = scoreButtons;
			Leaderboards.showScores(gc, context.getG());
			if(context.mute)
			Audio.playSound("testSample.wav");
		});

		Button optionsBtn = new Button(context, new Vector2f(width / 2 - 75,
				350f), Context.getImage("options.png"), Context.getImage("highOptions.png"));
		optionsBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			if (options) {
				/*
				 * buttons = new ArrayList<Button>(); buttons.add(audioBtn);
				 * buttons.add(screenBtn); buttons.add(backBtn);
				 */
				currentButtons = optionsButtons;
				// currentButtons = new ArrayList<Button>();
			}
			if(context.mute)
			Audio.playSound("testSample.wav");
			System.out.println("test");
		});

		Button instructionsBtn = new Button(context, new Vector2f(
				width / 2 - 385, 20f), Context.getImage("instructions.png"),
				Context.getImage("highInstructions.png"));
		instructionsBtn.onClick(() -> {
		
			System.out.println("testggg");
			options = false;
			mainMenu = false;
			instructions = true;
			currentButtons = instructionsButtons;
			//Audio.playSound("test.wav");
			
		});
		
		Button muteBtn = new Button(context, new Vector2f(
				width / 2 - 75, 150f), Context.getImage("audioMute.png"),
				Context.getImage("highAudioMute.png"));
		muteBtn.onClick(() -> {
		
			System.out.println("testggg");
			options = false;
			mainMenu = false;
			//context.getGc().isMusicOn();
			context.getGc().setMusicOn(
				!context.getGc().isMusicOn()
			);
			//currentButtons = instructionsButtons;
			Audio.playSound("test.wav");
			
		});
		
		
		/*Button audioBarBtn = new Button(context,
				new Vector2f(width / 2 - 75, 550f), Context.getImage("audioBar.png"),
				Context.getImage("audioBar.png"));
		audioBarBtn.onClick(() -> {
		
			Audio.playSound("testSample.wav");

		});
		
		Button audioPlusBtn = new Button(context, new Vector2f(width / 2 - 150,
				200f), Context.getImage("plus.png"),
				Context.getImage("plus.png"));
		audioPlusBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			Audio.playSound("testSample.wav");
			//Context.fullScreen = true;
			System.out.println("true");
		});
		
		Button audioMinusBtn = new Button(context, new Vector2f(width / 2 + 150,
				200f), Context.getImage("minus.png"),
				Context.getImage("minus.png"));
		audioMinusBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			Audio.playSound("testSample.wav");
			//Context.fullScreen = true;
			System.out.println("true");
		});
		*/
		
		Button closeBtn = new Button(context, new Vector2f(width / 2 + 340,
				20f), Context.getImage("Exit.png"),
				Context.getImage("highExit.png"));
		closeBtn.onClick(() -> {
			context.getGc().exit();
			Audio.playSound("testSample.wav");
			//Context.fullScreen = true;
			System.out.println("true");
		});
		
		// populate arraylist

		mainButtons.add(startBtn);
		mainButtons.add(scoresBtn);
		mainButtons.add(optionsBtn);
		mainButtons.add(instructionsBtn);
		mainButtons.add(closeBtn);

	//	optionsButtons.add(audioBtn);
		optionsButtons.add(screenBtn);
		optionsButtons.add(optionsBackBtn);
		optionsButtons.add(closeBtn);
		optionsButtons.add(muteBtn);

		
		scoreButtons.add(scoreBackBtn);
		
		instructionsButtons.add(instructionsBackBtn);

	/*	audioButtons.add(audioBackBtn);
		audioButtons.add(audioPlusBtn);
		audioButtons.add(audioMinusBtn);*/
	//	audioButtons.add(audioBarBtn);
		
		
	}

	public void makeButtons(GameContainer gc) throws SlickException {

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)
			throws SlickException {
		// }

		int x = Mouse.getX();
		int y = gc.getHeight() - Mouse.getY();

		for (Button button : currentButtons) {

			if (button.containsPoint(x, y)) {
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

	}

	@Override
	public void render(GameContainer gc, StateBasedGame game,
			org.newdawn.slick.Graphics g) throws SlickException {

		g.scale(1, 1);
		g.setBackground(Color.black);

		context.background(gc, g, "testBackground.png");
		context.title(gc, g, "testTitle.png");

		for (Button button : currentButtons) {
			button.display();
		}
		
		if(scores)
		{
			context.setImage(gc, g, "image.png",  halfHeight - 50 , halfWidth - 250, 300f,250f);
			Leaderboards.showScores(gc, context.getG());
		}
		
		if(instructions)
		{
			context.setImage(gc, g, "image.png",  halfHeight - 225 , halfWidth - 250, 650f,300f);
			g.setColor(Color.black);
			g.drawString("Run, and dont let the screen outrun you.", halfWidth - 250, halfHeight - 100);
			g.drawString("Use the arrows keys to move left and right but never", halfWidth - 250, halfHeight - 80);
			g.drawString("outside the screen and jump using space. " , halfWidth - 250, halfHeight - 60);
			g.drawString("The jump over the hills. Good luck.", halfWidth - 250, halfHeight - 40);
			g.setColor(Color.red);
			g.drawString("Keyboard:", halfWidth - 250, halfHeight - 0);
			g.setColor(Color.black);
			g.drawString("Left key - move left", halfWidth - 250, halfHeight + 20);
			g.drawString("Right key - move right", halfWidth - 250, halfHeight + 40);
			g.drawString("Space key - jump", halfWidth - 250, halfHeight + 60);
			g.drawString("'P' key - pause", halfWidth - 250, halfHeight + 80);
			g.setColor(Color.red);
			g.drawString("PS3 Controller:", halfWidth - 0, halfHeight - 0);
			g.setColor(Color.black);
			g.drawString("Left D-Pad- move left", halfWidth - 0, halfHeight + 20);
			g.drawString("Right D-Pad - move right", halfWidth - 0, halfHeight + 40);
			g.drawString("X Button - jump", halfWidth - 0, halfHeight + 60);
			g.drawString("Start Button - pause", halfWidth - 0, halfHeight + 80);
			
			/*Run, and dont let the screen outrun you. Use the arrows keys to move left and right but never outside the screen and jump using space. The jump over the hills.
			Good luck.

			Keyboard:
			Left key - move left
			Right key - move right
			Space key - jump
			'P' key - pause

			PS3 Controller:
			Left D-Pad- move left
			Right D-Pad - move right
			X Button - jump
			Start Button - pause*/
		}

	}

	@Override
	public int getID() {
		return 0;
	}

}