package game.elements;

//import java.io.IOException;
//import java.util.HashMap;

import game.Physics.AABoundingRect;
import game.elements.Element;
//import game.elements.Element.Facing;

//import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

//import Level.LevelObject;

public class Enemy extends Element {
	
	/**
	 * This function takes a set of coordinates to determine where a given enemy is spawned, runs the moving-animation 
	 * loop, sets up its boundingShape (collision box), and sets the values for any enemy's: acceleration speed, 
	 * maximum speed, maximum fall speed, deceleration speed and initial rightward movement speed.
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Enemy(float x, float y) throws SlickException{
    	//Constructor
		super(x,y);
    	
		//As the enemies never stand still, no "goombaStand" sprite is needed, and so this game element only needs
		//the movement-animation. This animation is therefore running all the time. Each image, in this loop of 3 images,
		//is shown for 175 milliseconds before moving on to the next image in line.
    	setMovingAnimation(new Image[]{new Image("images/goombaWalk1.png"),new Image("images/goombaStand1.png"),
    	new Image("images/goombaWalk2.png")}
    	,175);
    	
    	// --- 
    	boundingShape = new AABoundingRect(x+3, y, 26, 32);
    	
    	//These variables are set up with values specifically for this game element (the player). The purpose of the
    	//individual values are fairly self-explanatory.
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
        
        //As the enemies are not controlled by any keyboard-input (like the player) they are given a rightward movement
        //value at the game start-up. A higher value means faster movement speed for the enemies.
        moveRight(25);
    }
    
	// --- 
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
    
    
}
