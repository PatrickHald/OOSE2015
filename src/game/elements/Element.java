package game.elements;

import java.util.HashMap;

import Level.LevelObject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Element extends LevelObject {
    
	 protected HashMap<Facing,Image>     sprites;
    
    protected HashMap<Facing,Animation> movingAnimations;
    protected Facing                    facing;
    protected boolean                   moving = false;
    protected float                     accelerationSpeed = 1;
    protected float                     decelerationSpeed = 1;
    protected float                     maximumSpeed = 1;
    
    public Element(float x, float y) throws SlickException{
        super(x,y);
        //in case we forget to set the image, we don't want the game to crash, but it still has to be obvious that something was forgotten
        setSprite(new Image("images/marioStand1_2.0.png"));
     
        //The default direction for any object will be right
        facing = Facing.RIGHT;
    }
    //This enum is set up to "Facing", and its two directions easily distinguishable in the code.
	public enum Facing{
		LEFT, RIGHT
	}
	
	//Function that takes an array of images and the duration which each image is displayed and cycles
	//through the given images.
    protected void setMovingAnimation(Image[] images, int frameDuration){
        //The HashMap takes a facing-direction and an animation image and becomes the movingAnimation
    	movingAnimations = new HashMap<Facing,Animation>();
        
        //The images of an object, facing right, is set as the default animation
        movingAnimations.put(Facing.RIGHT, new Animation(images,frameDuration));
        
        //A new animation is declared for the leftward-flipped images.
        Animation facingLeftAnimation = new Animation();
        //The loop goes through each image and flips all of them to face left
        //The "getFlippedCopy" provides a horizontally (true) and vertically (false) flipped image of the original
        for(Image i : images){
            facingLeftAnimation.addFrame(i.getFlippedCopy(true, false), frameDuration);
        }
        movingAnimations.put(Facing.LEFT, facingLeftAnimation);
        
    }
    
    //
    //The "getFlippedCopy" provides a horizontally (true) and vertically (false) flipped image of the original
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
    
    //The function is set to allow the player an added y-velocity value if the player is colliding with the ground tiles.
    private boolean jumping;
    
    public boolean getJumping() {
    	return jumping;
    }
    
    //Sets the velocity which the player will be given when jumping - a higher NEGATIVE value will grant a higher jump
    public void jump(){
        if(onGround)
            y_velocity = -0.45f;
    }
    
    //These booleans are only used for the get functions below, and set to private so they cannot be 
    //changed anywhere else in the code.
    private boolean movingRight;
    private boolean movingLeft;
    
    //The get-functions are used to check the booleans when an enemy-to-tile collision occurs in
    //the "handleGameObject"-function within the Physics-class.
    public boolean getMovingRight() {
    	return movingRight;
    }
    public boolean getMovingLeft() {
    	return movingLeft;
    }
    
    //The following two functions, "moveLeft" and "moveRight", takes a delta value as input. "delta" is used
    //to ensure that the game-play is not affected by the frame-rate (the delta-variable is how much time, 
    //in milliseconds, has passed since the last update call).
    
    //By triggering one of these two functions the boolean "moving" is set to true.
    //Depending on which way the object is currently moving the "facing"-variable will be set to left or right, 
    //thereby turning the sprite-animation of the setMovingAnimation accordingly.
    
    //The "movingLeft" and "movingRight" booleans are only used for enemy-to-tiles collision-detection in 
    //the Physics-class. These are used to determine whether the enemy should turn left because he is moving right,
    //or vice versa.
    public void moveLeft(int delta){
        //if the object is not already moving at maximum speed
        if(x_velocity > -maximumSpeed){
            //then the object will accelerate towards maximum speed
            x_velocity -= accelerationSpeed*delta;
            if(x_velocity < -maximumSpeed){
                //and if the object exceeds maximum speed, set it to maximum speed
                x_velocity = -maximumSpeed;
            }
        }
        moving = true;
        facing = Facing.LEFT;
        movingLeft = true;
        movingRight = false;
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
        movingRight = true;
        movingLeft = false;
    }
    
    public void render(float offset_x, float offset_y ){
        
        //draw a moving animation if we have one and we moved within the last 150 milliseconds
        if(movingAnimations != null && moving){

            movingAnimations.get(facing).draw(x-2-offset_x,y-2-offset_y);
        }else{
            sprites.get(facing).draw(x-2-offset_x, y-2-offset_y);

        }
    }

}
