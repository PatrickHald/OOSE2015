package game.elements;

import game.Physics.AABoundingRect;
import game.Physics.BoundingShape;
import game.Physics.Physics;

import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy extends Element {
    
    public Enemy(float x, float y) throws SlickException{
    	super(x,y);
		//sprite = new Image("images/Goomba3.png");
    	setSprite(new Image("images/marioStand1_2.0.png"));
    	
    	setMovingAnimation(new Image[]{new Image("images/marioWalk1_2.0.png"),new Image("images/marioWalk2_2.0.png"),
    		    new Image("images/marioWalk3_2.0.png")}
    		    ,125);
    	
    	boundingShape = new AABoundingRect(x+3, y, 26, 32);
    	
    	accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
        
        //if(checkCollision() != true)
        this.moveRight(3);
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
        
    }
  
}
