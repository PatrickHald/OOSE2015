package game.elements;
import Level.*;
import game.Physics.BoundingShape;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public abstract class Element extends LevelObject {
	
		protected boolean                   moving = false;
		protected float                     accelerationSpeed = 1;
		protected float                     decelerationSpeed = 1;
		protected float                     maximumSpeed = 1;
	    protected float x;
	    protected float y;
	    protected Image sprite;
 
	public Element(float x, float y) throws SlickException{
		super(x,y);
		// ----- Placeholder for image -----
		//sprite = new Image("data/img/placeholder_sprite.png");
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
	    }
	 
	    public void moveRight(int delta){
	        if(x_velocity < maximumSpeed){
	            x_velocity += accelerationSpeed*delta;
	            if(x_velocity > maximumSpeed){
	                x_velocity = maximumSpeed;
	            }
	        }
	        moving = true;
	    }
	 
	   
	
   

 
    public void render(){
    	
      	sprite.draw(x+500,y+250);
 
}
	
	
//	public void moveRight(){
//		x++;
//	}
//	public void moveLeft(){
//		x--;
//	}

}



	 
