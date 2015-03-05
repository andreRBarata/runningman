package game;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Catchable extends Droppable {
	private String type;
	
	public String getType() {
		return type;
	}

	public Catchable(Context context, Vector2f position, Shape sprite, String type) {
		super(context, position, sprite);
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public void update() {
		super.update();
		
		this.setPosition(
			this.getPosition().sub(
				new Vector2f(context.playerSpeed, 0)
			)
		);
	}
}
