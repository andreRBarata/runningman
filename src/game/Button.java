package game;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.FontUtils;

/*
 Button button = new Button(
 new PVector(width/2, height/2),
 "button",
 50,
 50,
 new CallBack() {
 public void run() {
 println("teste");
 }
 }
 );

 buttons.add(button);
 */

public class Button extends Drawable {
	private CallBack callback;
	private String text;
	private boolean clicked;
	private boolean hover;
	private String img;
	private String hoverImg;

	public Button(Context context, Vector2f position, String text,
			CallBack callback) {
		super(context, position, new Polygon(new float[] { 0, 0,
				context.getG().getFont().getWidth(text), 0,
				context.getG().getFont().getWidth(text),
				context.getG().getFont().getHeight(text), 0,
				context.getG().getFont().getHeight(text) }));
		this.clicked = false;
		this.callback = callback;
		this.text = text;
	}

	public Button(Context context, Vector2f position, String text,
			CallBack callback, String img, String hoverImg) {
		super(context, position, new Polygon(new float[] { 0, 0,
				context.getG().getFont().getWidth(text), 0,
				context.getG().getFont().getWidth(text),
				context.getG().getFont().getHeight(text), 0,
				context.getG().getFont().getHeight(text) }));
		this.img = img;
		this.clicked = false;
		this.callback = callback;
		this.text = text;
		this.hoverImg = hoverImg;

	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public boolean isMouseOver(){
		return hover;
	}
	
	public void SetMouseOver(boolean isMouseOver){
		this.hover = isMouseOver;
	}
	
	public CallBack getCallback() {
		this.clicked = false;
		return callback;
	}

	public String getText() {
		return text;
	}

	public void drawImage(GameContainer gc, org.newdawn.slick.Graphics g,
			float x, final String url) {

		try {
			Image img = new Image("/src/Images/" + url);

			// MainMenu.class.getResourceAsStream("/Samples/" + url);
			float imgWidth = img.getWidth();
			float imgHeight = img.getHeight();
			float scaleWidth = imgWidth * context.scale + 20;
			float scaleHeight = imgHeight * context.scale;

			img.draw((context.getGc().getWidth() / 2) - (scaleWidth / 2),
					(scaleHeight / 2) * x, scaleWidth, scaleHeight);
			// get width and height of the image and multiply it by the scale,
			// scale
			// should be a global variable and should effect everything
			// Image = new Image("image.png", false, Image.FILTER_NEAREST);

		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.WARNING, null,
					ex);
		}
	}

	public void display() {

		if (img != null && !img.equals("") && !isMouseOver())
			drawImage(context.getGc(), context.getG(), 4, img);
		else if(hoverImg != null && !hoverImg.equals("") && isMouseOver())
			drawImage(context.getGc(), context.getG(), 4, hoverImg);

		if (!clicked) {
			context.getG().setColor(new Color(255));
		} else {
			context.getG().setColor(new Color(20, 20, 200));
		}

		context.getG()
				.getFont()
				.drawString((int) super.getPosition().x,
						(int) super.getPosition().y - 10, text);
	}
}