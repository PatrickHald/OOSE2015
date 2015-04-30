package game.elements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Element  {
	
	   // protected BoundingShape boundingShape;
	 
	    protected float    x_velocity = 0;
	    protected float    y_velocity = 0;
	    protected float    maximumFallSpeed = 5;
	 
	    protected boolean  onGround = true;
	    protected float x;
	    protected float y;
	    protected Image sprite;
 
	public Element(float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		// ----- Placeholder for image -----
		//sprite = new Image("data/img/placeholder_sprite.png");
	}
	 
        
  //      boundingShape = new AABoundingRect(x,y,32,32);
    
 
    public void applyGravity(float gravity){
      //if we aren't already moving at maximum speed
    	if(y_velocity < maximumFallSpeed){
            //accelerate
            y_velocity += gravity;
            if(y_velocity > maximumFallSpeed){
                //and if we exceed maximum speed, set it to maximum speed
                y_velocity = maximumFallSpeed;
            }
        }
        
    }
 
    public float getYVelocity() {
        return y_velocity;
    }
    public void setYVelocity(float f){
        y_velocity = f;
    }
    public float getXVelocity(){
        return x_velocity;
    }
    public void setXVelocity(float f){
        x_velocity = f;
    }
	
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void render(){
		sprite.draw(x-2,y-2);
		
		
	}
    public void moveLeft(int delta){
    	x = x - (0.15f*delta);
    }
     
    public void moveRight(int delta){
    	x = x + (0.15f*delta);
    }
    public void moveUp(int delta){
        y = y - (0.60f*delta);
    }
    public void moveDown(int delta){
        y = y + (0.15f*delta);
    }
//	public void moveRight(){
//		x++;
//	}
//	public void moveLeft(){
//		x--;
//	}
}

	 
