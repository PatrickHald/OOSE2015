package game.State;
import game.SimpleSlickGame;
import Level.Level;
import game.elements.Player;
import game.elements.Enemy;
import controls.PlayerControl;

import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelState extends BasicGameState {
	private Player player;
	private PlayerControl playerControl;
	private Enemy enemy;
	private Level level;
	private String firstLevel;
	
	public LevelState ( String firstLevel){
		this.firstLevel = firstLevel;
	}
	
	public void init(GameContainer container, StateBasedGame sbg)throws SlickException{
		
		level = new Level (firstLevel);
		
		player = new Player(170,250);
		level.addElement(player);
		
		//link to PlayerControl class
		playerControl = new PlayerControl(player);
		//declaring gravity value
		player.setYVelocity(0.4f);
		
		
		enemy = new Enemy(280,274);
		level.addElement(enemy);
	}

	public void update (GameContainer container,  StateBasedGame  sbg, int delta) throws SlickException{
		//player movement is registered every frame
		playerControl.handleInput(container.getInput(), delta);
		//player gravity
		player.applyGravity(0.2f);
		player.moveDown(delta);
	}
	public void render (GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
		g.scale(SimpleSlickGame.SCALE, SimpleSlickGame.SCALE);
		level.render();	
		//enemy.moveRight();
	}
	public void KeyPressed ( int key, char code){
		if (key == Input.KEY_ESCAPE){
			System.exit(0);
		}
	}
	public int getID(){
        //this is the id for changing states
        return 0;
	
	}

}
