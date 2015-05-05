package game.elements;

import java.io.IOException;
import java.util.HashMap;

import game.Physics.AABoundingRect;
import game.elements.Element;
import game.elements.Element.Facing;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.LevelObject;

public class Enemy extends Element {
	
	public Enemy(float x, float y) throws SlickException{
    	//Constructor
		super(x,y);
    	
		//
    	setMovingAnimation(new Image[]{new Image("images/goombaWalk1.png"),new Image("images/goombaStand1.png"),
    	new Image("images/goombaWalk2.png")}
    	,175);
    	
    	boundingShape = new AABoundingRect(x+3, y, 26, 32);
    	 
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
        
        moveRight(25);
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
    
    
}
