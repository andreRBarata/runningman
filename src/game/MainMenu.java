package game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
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

		halfWidth = gc.getWidth() / divideBy;
		halfHeight = gc.getHeight() / divideBy;
		height = gc.getHeight();
		width = gc.getWidth();
		context = new Context(
			gc,
			gc.getGraphics()
		);
		
		buttons = new ArrayList<Button>();
		buttons.add(new Button(context, new Vector2f(halfWidth - width / 10, halfHeight
				- height / 5), "teste", new CallBack() {
			public void run() {
				options = true;
				mainMenu = false;
				System.out.println("teste");
			}
		}));

	//	buttons = new ArrayList<Button>();
		buttons.add(new Button(
				context,
				new Vector2f(halfWidth - width / 10, halfHeight), "tasty", new CallBack() {
					public void run() {
						options = true;
						mainMenu = false;
						System.out.println("teste");
					}
				}));

	//	buttons = new ArrayList<Button>();
		buttons.add(new Button(
				context,
				new Vector2f(halfWidth - width / 10, halfHeight
						+ height / 5), "tastier", new CallBack() {
					public void run() {
						options = true;
						mainMenu = false;
						System.out.println("teste");
					}
				}));
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
			int y = gc.getHeight() - Mouse.getY();

			for (Button button : buttons) {
				Shape spriteInSpace = new MorphShape(button.getSprite());
				spriteInSpace.setLocation(button.getPosition());

				if (spriteInSpace.contains(x, y)) {
					button.setClicked(true);
					button.getCallback().run();
				}
			}
		}
	}

	// System.out.println("Width: " + getWidth() / 2);
	// System.out.println("Height: " + getHeight() / 2);

	@Override
	public void render(GameContainer gc, org.newdawn.slick.Graphics g)
			throws SlickException {

		g.setBackground(Color.black);

		if (mainMenu) {
			//g.setFont(new TrueTypeFont(new Font("TimesRoman", Font.PLAIN,
			//		height / 10), false));

			g.setColor(Color.yellow);
			g.drawString("Start", halfWidth - width / 10, halfHeight - height
					/ 5);

			g.setColor(Color.white);
			g.drawString("Options", halfWidth - width / 10, halfHeight);

			g.setColor(Color.red);
			g.drawString("Instructions", halfWidth - width / 10, halfHeight
					+ height / 5);
		}

		if (options) {
			g.setColor(Color.yellow);
			g.drawString("Sound", halfWidth - width / 10, halfHeight - height
					/ 5);

			g.setColor(Color.white);
			g.drawString("Controls", halfWidth - width / 10, halfHeight);

			g.setColor(Color.red);
			g.drawString("Back", halfWidth - width / 10, halfHeight + height
					/ 5);
		}

		for (Button button : buttons) {
			button.display();
		}

	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainMenu("RunnerMan"));
			appgc.setDisplayMode(800, 500, false);
			// appgc.setShowFPS(false);

			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

}
