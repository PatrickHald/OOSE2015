package game.elements;
import Level.*;
import game.Physics.BoundingShape;

import java.util.HashMap;
import org.newdawn.slick.Animation;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Element extends LevelObject {
	
	protected HashMap<Facing,Image>		sprites;
	
	protected HashMap<Facing,Animation>	movingAnimations;
	protected long 						lastTimeMoved;
	protected boolean                   moving = false;
	protected float                     accelerationSpeed = 1;
	protected float                     decelerationSpeed = 1;
	protected float                     maximumSpeed = 1;
	protected float x;
	protected float y;
	//protected Image sprite;
	protected Facing facing;
	
	public Element(float x, float y) throws SlickException{
		super(x,y);
		
		setSprite(new Image("images/mario_small.png"));
		
		//all elements face right by default
		facing = Facing.RIGHT;
		// ----- Placeholder for image -----
		//sprite = new Image(".png");
	}
	
	public enum Facing{
		LEFT, RIGHT
	}
	
	protected void setMovingAnimation(Image[] images, int frameDuration){
		movingAnimations = new HashMap<Facing,Animation>();
		
		movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
		
		Animation facingLeftAnimation = new Animation();
		for(Image i : images){
			facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
		}
		movingAnimations.put(Facing.LEFT, facingLeftAnimation);
		
	}
		  
	protected void setSprite(Image i){
		 sprites = new HashMap<Facing,Image>();
		 sprites.put(Facing.RIGHT, i);
		 sprites.put(Facing.LEFT , i.getFlippedCopy(true, false));
	}
	
	public boolean isMoving(){
	    return moving;
	}
	
	public void setMoving(boolean b){
	    moving = b;
	}
	
	//move towards x_velocity = 0
	public void decelerate(int delta) {
	    if(x_velocity > 0){
	        x_velocity -= decelerationSpeed * delta;
	        if(x_velocity < 0)
	            x_velocity = 0;
	    }else if(x_velocity < 0){
	        x_velocity += decelerationSpeed * delta;
	        if(x_velocity > 0)
	            x_velocity = 0;
	    }
	}
	
	public void jump(){
	    if(onGround)
	        y_velocity = -0.4f;
	    }
	
	public void moveLeft(int delta){
	    //if we aren't already moving at maximum speed
	    if(x_velocity > -maximumSpeed){
	        //accelerate
	        x_velocity -= accelerationSpeed*delta;
	        if(x_velocity < -maximumSpeed){
	            //and if we exceed maximum speed, set it to maximum speed
	            x_velocity = -maximumSpeed;
	        }
	    }
	    moving = true;
	    facing = Facing.LEFT;
	}
	
	public void moveRight(int delta){
	    if(x_velocity < maximumSpeed){
	        x_velocity += accelerationSpeed*delta;
	        if(x_velocity > maximumSpeed){
	            x_velocity = maximumSpeed;
	        }
	    }
	    moving = true;
	    facing = Facing.RIGHT;
	}
	
	
	
    public void render(float offset_x, float offset_y){
    	 
        //draw a moving animation if we have one and we moved within the last 150 miliseconds
        if(movingAnimations != null && moving){
            movingAnimations.get(facing).draw(x-2-offset_x,y-2-offset_y);                
        }else{            
            sprites.get(facing).draw(x-2-offset_x, y-2-offset_y);          
        }
    	//sprite.draw(x+500,y+250);
    }
	
	
//	public void moveRight(){
//		x++;
//	}
//	public void moveLeft(){
//		x--;
//	}

}



	 
