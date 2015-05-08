
package game.elements;
import game.Physics.AABoundingRect;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Element {

	/**
	 * This function takes a set of coordinates to determine where the player is spawned, sets the default image for
	 * standing still, runs the moving-animation loop, sets up the player's boundingShape (collision box), and sets 
	 * the values for the player's: acceleration speed, maximum speed, maximum fall speed and deceleration speed.
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
    public Player(float x, float y) throws SlickException{
    	//Constructor
    	super(x,y);
    	
    	//This image (aka. sprite) is set as the default for the player when no movement (keyboard-input) is taking place.
    	setSprite(new Image("images/marioStand1_2.0.png"));
    	
    	//Whenever movement occurs (keyboard-input is registered) this animation cycle/loop will run. Each image will be
    	//displayed in the chronological order they are added to the array, and each image will be shown for the duration
    	//of 125 milliseconds.
    	setMovingAnimation(new Image[]{new Image("images/marioWalk1_2.0.png"),new Image("images/marioWalk2_2.0.png"),
    	new Image("images/marioWalk3_2.0.png")}
    	,125);
    	
    	// --- 
    	boundingShape = new AABoundingRect(x+3, y, 26, 32);
    	
    	//These variables are set up with values specifically for this game element (the player). The purpose of the
    	//individual values are fairly self-explanatory.
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
    }
    
    // --- 
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
    
}
