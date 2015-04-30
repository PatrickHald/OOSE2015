package game.elements;
     
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Element {
     
    public Player(float x, float y) throws SlickException{
    	super(x,y);
    	sprite = new Image("images/mario_small.png");
    }
}