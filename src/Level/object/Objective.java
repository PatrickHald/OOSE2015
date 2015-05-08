/**
 * set image in on objective and overrides gravity so objective can stay up in the air.
 */
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
		//chooses which image to set as our objective.
		 setSprite(new Image("images/marioStar.png"));	
	}
	@Override
	// here we remove its gravity so it will stay up in the air.
	public void applyGravity(float gravity){
	      //if we aren't already moving at maximum speed
	        
	    }
}
