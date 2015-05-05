package Level.object;

import game.elements.Element;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.LevelObject;


public  class Objective extends Element {

protected Animation animation; 

	public Objective(float x, float y) throws SlickException {
		super(x, y);
		 setSprite(new Image("images/mario_qb2.png"));
		
		
	}
 //public void render (float offset_x,float offset_y){
//			animation.draw(x-2-offset_x,y-2-offset_y);
//		}
	
	
	@Override
	
	public void applyGravity(float gravity){
	      //if we aren't already moving at maximum speed
	        
	    }
}
