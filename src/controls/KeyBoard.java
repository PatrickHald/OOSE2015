package controls;

import game.Physics.Physics;
import game.elements.Player;

import org.newdawn.slick.Input;

public class KeyBoard extends PlayerControl {
	private Physics physics ;
	 
	public KeyBoard(Player player) {
		
	    //Constructor
		super(player);
		physics = new Physics();
	}
	
	public void handleInput(Input i, int delta) {
	    handleKeyboardInput(i,delta);
	}
	
	/**
	 * This function handles any input from the keyboard.
	 * @param i
	 * @param delta
	 */
	private void handleKeyboardInput(Input i, int delta){
	    //Both WASD or arrow keys can be used to move around. This is made as an if-statement so the player can 
		//not move both left and right simultaneously.
	    if(i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)){
	        player.moveLeft(delta);
	    }else if(i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)){
	        player.moveRight(delta);
	    }else{
	        //we dont move if we don't press left or right, this will have the effect that our player decelerates
	        player.setMoving(false);
	    }
	   
	    //The space bar can be used to jump by checking for the isKeyDown-input of the mentioned button, and then 
	    //calling the "jump"-function for the game element "player".
	    if(i.isKeyDown(Input.KEY_SPACE)){
	        player.jump();
	        
	    }
	    //The ESC-key can be used to shut down the game at any given point in time.
	    if(i.isKeyDown(Input.KEY_ESCAPE)) {
	        System.exit(0);
	    }
	}
}


