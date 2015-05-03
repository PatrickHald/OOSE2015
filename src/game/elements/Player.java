package game.elements;
import game.Physics.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.LevelObject;

public class Player extends Element {

    public Player(float x, float y) throws SlickException{
    	super(x,y);
    	sprite = new Image("images/mario_small.png");
    	
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