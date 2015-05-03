package game.State;
import game.Physics.Physics;
import game.SimpleSlickGame;
import Level.Level;
import game.elements.Enemy;
import game.elements.*;
import controls.PlayerControl;
import game.elements.Player;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelState extends BasicGameState {
	private Enemy enemy;
	Level level;
	String firstLevel;
	public Image background;
	private Player player;
	private PlayerControl playerControl;
	private Physics physics;
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
		
		

	//	enemy = new Enemy(280,274);
		//level.addElement(enemy);
	}

	public void update (GameContainer container,  StateBasedGame  sbg, int delta) throws SlickException{

		//player movement is registered every frame
		playerControl.handleInput(container.getInput(), delta);
		//player gravity
		player.applyGravity(0.2f);
		//player.moveDown(delta);
	    //physics.handlePhysics(level, delta);
	    }
	
	public void render (GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
		g.scale(SimpleSlickGame.SCALE, SimpleSlickGame.SCALE);

	    //for the sky
		Image background = new Image ("data/levels/Oose_Mario_game_sky.png");
		  g.drawImage (background, 0, 0, null);
		  
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
