package controls;

import game.elements.Player;
import org.newdawn.slick.Input;


public abstract class PlayerControl{
	
    protected Player player;
    
    public PlayerControl(Player player){
        this.player = player;
    }
    
    public abstract void handleInput(Input i, int delta);

}



