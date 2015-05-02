package controls;

import game.elements.Player;
import game.elements.*;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PlayerControl{
	
	protected Player player;
	
	public PlayerControl(Player player) throws SlickException{
		super();
		this.player = player;
	}
		  
	public void handleInput(Input i, int delta) {
		//handle input from keyboard
		handleKeyboardInput(i,delta);
	}
	
	private void handleKeyboardInput(Input i, int delta){
		//use the WASD or arrow keys to move around
		if(i.isKeyDown(Input.KEY_A) || i.isKeyDown(Input.KEY_LEFT)){
			player.moveLeft(delta);
		}else if(i.isKeyDown(Input.KEY_D) || i.isKeyDown(Input.KEY_RIGHT)){
			player.moveRight(delta);
		}
		//use SPACE to jump
		if(i.isKeyDown(Input.KEY_SPACE)){
			player.moveUp(delta);
		}
		
	}





}