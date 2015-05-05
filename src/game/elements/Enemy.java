package game.elements;

import java.io.IOException;
import java.util.HashMap;

import game.elements.Element;
import game.elements.Element.Facing;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Level.LevelObject;

public class Enemy extends Element {
	
    public Enemy(float x, float y) throws SlickException{
    	super(x,y);
		setSprite (new Image("images/Goomba3.png"));    	
    }
    
    
}
