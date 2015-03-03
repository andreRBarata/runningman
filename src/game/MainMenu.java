package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class MainMenu extends BasicGame {
	Context context;
	int height;
	int width;
	int divideBy = 2;
	int halfWidth;
	int halfHeight;
	float scale = 0.3f;
	ArrayList<Button> buttons;

	// void resize(500,500);
	boolean mainMenu = true;
	boolean options = false;
	boolean instructions = false;

	public MainMenu(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		buttons(gc);
	}

	public void buttons(GameContainer gc) throws SlickException {
		halfWidth = gc.getWidth() / divideBy;
		halfHeight = gc.getHeight() / divideBy;
		height = gc.getHeight();
		width = gc.getWidth();
		context = new Context(gc, gc.getGraphics());

		buttons = new ArrayList<Button>();
		buttons.add(new Button(context, new Vector2f(width / 2, height / 2 + 50), "testing", new CallBack() {
			public void run() {
				options = true;
				mainMenu = false;
				MainGame.start();
				Audio.playSound("test.wav");
				System.out.println("test");
			}
		}, "start.png", "highStart.png"));

		// buttons = new ArrayList<Button>();
		buttons.add(new Button(context, new Vector2f(width / 2, height / 2 - 10), "test", new CallBack() {
			public void run() {
				options = true;
				mainMenu = false;
				Audio.playSound("test.wav");
				System.out.println("test");
			}
		},"highScores.png", "highHighScores.png"));

		// buttons = new ArrayList<Button>();
		buttons.add(new Button(context, new Vector2f(halfWidth - width / 10), "test", new CallBack() {
			public void run() {
				options = true;
				mainMenu = false;
				Audio.playSound("test.wav");
				System.out.println("test");
			}
		},"Options.png", "highOptions.png"));
		
		// buttons = new ArrayList<Button>();
				buttons.add(new Button(context, new Vector2f(width / 2, height / 2 - 10), "test", new CallBack() {
					public void run() {
						options = true;
						mainMenu = false;
						Audio.playSound("test.wav");
						System.out.println("test");
					}
				},"instructions.png", "highInstructions.png"));
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {

		int x = Mouse.getX();
		int y = gc.getHeight() - Mouse.getY();

		for (Button button : buttons) {
			Shape spriteInSpace = new MorphShape(button.getSprite());
			spriteInSpace.setLocation(button.getPosition());

			if(spriteInSpace.contains(x, y)){
				button.SetMouseOver(true);
			}
			else
				button.SetMouseOver(false);
			
			if (Mouse.isButtonDown(0)) {
				if (spriteInSpace.contains(x, y)) {
					button.setClicked(true);
					button.getCallback().run();
				}
			}
		}

	}

	public void drawImage(GameContainer gc, org.newdawn.slick.Graphics g,
			float x, final String url) throws SlickException {

		Image img = new Image("/src/Images/" + url);
		// MainMenu.class.getResourceAsStream("/Samples/" + url);
		float imgWidth = img.getWidth();
		float imgHeight = img.getHeight();
		float scaleWidth = imgWidth * scale + 20;
		float scaleHeight = imgHeight * scale;

		img.draw((width / 2) - (scaleWidth / 2), (scaleHeight / 2) * x,
				scaleWidth, scaleHeight);
		// get width and height of the image and multiply it by the scale, scale
		// should be a global variable and should effect everything
		// Image = new Image("image.png", false, Image.FILTER_NEAREST);
	}

	@Override
	public void render(GameContainer gc, org.newdawn.slick.Graphics g)
			throws SlickException {

		/*
		 * if (mainMenu) { drawImage(gc, g, 1, "start.png"); drawImage(gc, g, 4,
		 * "options.png"); drawImage(gc, g, 7, "instructions.png"); }
		 * 
		 * if (options) { drawImage(gc, g, 1, "highStart.png"); drawImage(gc, g,
		 * 4, "options.png"); drawImage(gc, g, 7, "instructions.png"); }
		 */

		g.scale(1, 1);
		g.setBackground(Color.black);

		// if (mainMenu) {
		// g.setFont(new TrueTypeFont(new Font("TimesRoman", Font.PLAIN,
		// height / 10), false));

		/*
		 * g.setColor(Color.yellow); g.drawString("Start", halfWidth - width /
		 * 10, halfHeight - height / 5);
		 * 
		 * g.setColor(Color.white); g.drawString("Options", halfWidth - width /
		 * 10, halfHeight);
		 * 
		 * g.setColor(Color.red); g.drawString("Instructions", halfWidth - width
		 * / 10, halfHeight + height / 5);
		 */
		// }
		/*
		 * if (options) { g.setColor(Color.yellow); g.drawString("Sound",
		 * halfWidth - width / 10, halfHeight - height / 5);
		 * 
		 * g.setColor(Color.white); g.drawString("Controls", halfWidth - width /
		 * 10, halfHeight);
		 * 
		 * g.setColor(Color.red); g.drawString("Back", halfWidth - width / 10,
		 * halfHeight + height / 5); }
		 */

		for (Button button : buttons) {
			button.display();
		}

	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainMenu("RunnerMan"));
			appgc.setDisplayMode(800, 600, false);
			// appgc.setShowFPS(false);

			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

}