package game.elements;
import game.Physics.AABoundingRect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Element {

    public Player(float x, float y) throws SlickException{
    	super(x,y);
    	
    	setSprite(new Image("images/mario_small.png"));
    	 
    	setMovingAnimation(new Image[]{new Image("images/mario_small.png"),new Image("images/mario_small.png"),
    	new Image("images/mario_small.png"),new Image("images/mario_small.png")}
    	,125);
    	
    	boundingShape = new AABoundingRect(x+3, y, 26, 32);
    	 
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
    
}
