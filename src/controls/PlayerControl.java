package controls;

import game.elements.Player;
import org.newdawn.slick.Input;


public abstract class PlayerControl{
	
    protected Player player;
    
    /**
     * This function links the functions of the PlayerControl to the game element "player".
     * @param player
     */
    public PlayerControl(Player player){
        this.player = player;
    }
    
	/**
	 *This functions checks for, and registers, any input from the keyboard-input function.This function, and many
	 *others throughout the code, takes a delta value as input. "delta" is used to ensure that the game-play is not 
	 *affected by the frame-rate (the delta-variable is how much time, in milliseconds, has passed since the last 
	 *update call).
	 */
    public abstract void handleInput(Input i, int delta);

}



