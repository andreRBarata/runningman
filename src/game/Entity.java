package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public interface Entity {
	
	public void display(Graphics g);
	public void update();
	
	
}
