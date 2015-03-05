package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class BackgroundObject {

	private float scrollSpeed;
	private Image image;
	private Vector2f position1;
	private Vector2f position2;
	private GameContainer gc;
	
	public BackgroundObject(GameContainer gc, Image image, Vector2f startPosition, float scrollSpeed) {
		this.image = image;
		this.position1 = new Vector2f(startPosition.x, startPosition.y);
		this.position2 = new Vector2f(startPosition.x, startPosition.y);
		this.position2.x += gc.getWidth();
		this.scrollSpeed = scrollSpeed;
		this.gc = gc;
		
	}
	
	
	public void update(){
		position1.x -= scrollSpeed;
		position2.x -= scrollSpeed;
				
		checkBounds();


	}
	
	private void checkBounds(){
		if(position1.x < -gc.getWidth())
			position1.x = position2.x + gc.getWidth();
		
		if(position2.x < -gc.getWidth())
			position2.x = position1.x + gc.getWidth();
	}
	
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
//		g.drawImage(image, position1.x, position1.y);
//		g.drawImage(image, position2.x, position2.y);
		image.draw(position1.x, position1.y, gc.getWidth(), gc.getHeight());
		image.draw(position2.x, position2.y, gc.getWidth(), gc.getHeight());

	}

}
