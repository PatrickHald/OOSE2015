package game.elements;
import game.Physics.*;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

<<<<<<< HEAD
=======
import Level.LevelObject;
>>>>>>> branch 'master' of https://github.com/PatrickHald/OOSE2015.git

public class Player extends Element {

    public Player(float x, float y) throws SlickException{
    	super(x,y);
    	
    	setSprite(new Image("data/img/characters/player/player.png"));
    	 
    	setMovingAnimation(new Image[]{new Image("data/img/characters/player/player_1.png"),new Image("data/img/characters/player/player_2.png"),
    	new Image("data/img/characters/player/player_3.png"),new Image("data/img/characters/player/player_4.png")}
    	,125);
    	
    	boundingShape = new AABoundingRect(x+3, y, 26, 32);
    	 
        accelerationSpeed = 0.001f;
        maximumSpeed = 0.15f;
        maximumFallSpeed = 0.3f;
        decelerationSpeed = 0.001f;
    }
    
    public void updateBoundingShape(){
        boundingShape.updatePosition(x+3,y);
    }
    
}
