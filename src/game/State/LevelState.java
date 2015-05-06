package game.State;
import java.awt.Color;

import Level.Tile.*;
import Level.object.Objective;
import game.Physics.Physics;
import game.SimpleSlickGame;
import Level.Level;
import game.elements.Element;
import game.elements.Enemy;
import controls.PlayerControl;
import controls.KeyBoard;
import game.elements.Player;
import Level.*;

import org.newdawn.slick.tiled.TiledMap;
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
	private Enemy enemy2;
	private Enemy enemy3;
	private Enemy enemy4;
	private Enemy enemy5;
	private Level level;
	private String firstLevel;
	public Image background;
	private Player player;
	private PlayerControl playerControl;
	private Physics physics ;
	private Objective objective;
	//public int health = 0;
	
	public LevelState ( String firstLevel){
		this.firstLevel = firstLevel;
			}
	
	public void init(GameContainer container, StateBasedGame sbg)throws SlickException {		
		player = new Player(170,275);
		//level.addElement(player);
		
		//enemy = new Enemy (200,200);
		
		//make boxes
		objective = new Objective (2850,150);
		//level.addLevelObject(new Objective(128,315));
       
		enemy = new Enemy (600,175);
		enemy2 = new Enemy (1400,300);
		enemy3 = new Enemy (900,120);
		enemy4 = new Enemy (2200,100);
		enemy5 =new Enemy (2850,100);
		
		level = new Level (firstLevel,player,enemy,enemy2,enemy3,enemy4,enemy5, objective);
		
		//link to PlayerControl class
		playerControl = new KeyBoard(player);
		physics = new Physics();
	}

	public void update (GameContainer container,  StateBasedGame  sbg, int delta) throws SlickException{
		//player movement is registered every frame
		playerControl.handleInput(container.getInput(), delta);
		physics.handlePhysics(level, delta);
	    }
	
	public void render (GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException{
		g.scale(SimpleSlickGame.SCALE, SimpleSlickGame.SCALE);

	    //for the sky
		Image background = new Image ("data/levels/Oose_Mario_game_sky.png");
		  g.drawImage (background, 0, 0, null);
		  
		  
		level.render();	
		//the life string in upper left corner
		//g.drawString("Life: "+ health ,12,35);
		//enemy.moveRight();
		
		//Upon colliding with enemy, game over image is spawned.
		if(physics.hasEnemyCollision()) {
			  Image gameOver = new   Image ("images/gameOverScreen.png");
			  g.drawImage (gameOver, SimpleSlickGame.WINDOW_WIDTH/4, SimpleSlickGame.WINDOW_HEIGTH/3, null);
			  
			 
		}
		if(physics.hasObjectiveCollision()) {
			  Image win = new Image ("images/winScreen.png");
			  g.drawImage (win, SimpleSlickGame.WINDOW_WIDTH/4, SimpleSlickGame.WINDOW_HEIGTH/3, null);
			  
			 
		}
	}
	//public void KeyPressed ( int key, char code){
	//	if (key == '1'){
	//		System.exit(1);
	//		System.out.println("hey");
	//	}
	//}
	public int getID(){
        //this is the id for changing states
        return 0;
	
	}

}
