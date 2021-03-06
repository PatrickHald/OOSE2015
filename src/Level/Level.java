/**
 * in this class we put our object into the level parameters, and put the tiles -which is what we use as ground, into the map.
 * in the offsets we calculate where the player is on the screen and makes him the center of the screen. this i calculated for both the X and Y axis
 */
package Level;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import game.elements.Element;
import game.elements.Enemy;
import java.util.ArrayList;
import Level.Tile.AirTile;
import Level.Tile.SolidTile;
import Level.Tile.Tile;
import Level.object.Objective;
import game.SimpleSlickGame;
import game.elements.Player;

public class Level {
	private TiledMap map;
	   
	//a list of all characters present somewhere on this map
	private ArrayList<Element> elements;
	private Player player;
	private Enemy enemy;
	private Objective objective;
	private Tile[][] tiles;
	
	public Level(String level, Player player, Enemy enemy,Enemy enemy2,Enemy enemy3,Enemy enemy4,Enemy enemy5, Objective objective) throws SlickException{
		map = new TiledMap("data/levels/" + level + ".tmx","data/levels");
	    
	    elements = new ArrayList<Element>();
	    
	    this.player=player;
	    addElement(player);
	    this.enemy = enemy;
	    addElement(enemy);
	    this.enemy = enemy2;
	    addElement(enemy2);
	    this.enemy = enemy3;
	    addElement(enemy3);
	    this.enemy = enemy4;
	    addElement(enemy4);
	    this.enemy = enemy5;
	    addElement(enemy5);
	    this.objective=objective;
	    addElement(objective);
	    
	    loadTileMap();
	}    
	
	private void loadTileMap(){
		//create an array to hold all the tiles in the map
	    tiles = new Tile[map.getWidth()][map.getHeight()];
	    
	    int layerIndex = map.getLayerIndex("CollisionLayer");
	    
	    if(layerIndex == -1){
	    	System.err.println("Map does not have the layer \"CollisionLayer\"");
	        System.exit(0);
	    }
	    
	    //loop through the whole map
	    for(int x = 0; x < map.getWidth(); x++){
	    	for(int y = 0; y < map.getHeight(); y++){
	            //get the tile
	            int tileID = map.getTileId(x, y, layerIndex);
	            Tile tile = null;
	            //check the type of tile    
	            switch(map.getTileProperty(tileID, "TileType", "Solid")){
	                case "air":
	                    tile = new AirTile(x,y);              
	                    break;
	                default:
	                    tile = new SolidTile(x,y);
	                    break;
	            }
	            tiles[x][y] = tile;
	        }
	    }
	}
	
	public void addElement(Element e){
		elements.add(e);
	}
	
	public ArrayList<Element> getElements(){
	    return elements;
	}
   
	public Tile[][] getTiles(){
	    return tiles;
	}
	
	public int getXOffset(){
	    int offset_x = 1;
	    
	    //the first thing we are going to need is the half-width of the screen, to calculate if the player is in the middle of our screen
	    int half_width = (int) (SimpleSlickGame.WINDOW_WIDTH/SimpleSlickGame.SCALE/2);
	    
	    //next up is the maximum offset, this is the most right side of the map, minus half of the screen off course
	    int maxX = (int) (map.getWidth()*32)-half_width;
	    
	    //now we have 3 cases here
	    if(player.getX() < half_width){
	    	//the player is between the most left side of the map, which is zero and half a screen size which is 0+half_screen
	        offset_x = 1;
	    }else if(player.getX() > maxX){
	        //the player is between the maximum point of scrolling and the maximum width of the map
	        //the reason why we subtract half the screen again is because we need to set our offset to the topleft position of our screen
	        offset_x = maxX-half_width;
	    }else{
	        //the player is in between the 2 spots, so we set the offset to the player, minus the half-width of the screen
	        offset_x = (int) (player.getX()-half_width);
	    }
	    
	    return offset_x;
	}
	// the same as above just for the Y axis 
	public int getYOffset(){
		int offset_y = 0;
		
		int half_heigth = (int) (SimpleSlickGame.WINDOW_HEIGTH/SimpleSlickGame.SCALE/2);
		int maxY = (int) (map.getHeight()*32)-half_heigth;
		
		if(player.getY() < half_heigth){
			offset_y = 0;
		}else if(player.getY() > maxY){
		    offset_y = maxY-half_heigth;
		}else{
		    offset_y = (int) (player.getY()-half_heigth);
		}
		
		return offset_y;
	}
	
	public void render(){
		int offset_x = getXOffset();
		int offset_y = getYOffset();
		
		//render the map first
		map.render(-(offset_x%32), -(offset_y%32), offset_x/32, offset_y/32, 33, 19);
		
		//and then render the characters on top of the map
		for(Element e : elements){
			e.render(offset_x,offset_y);
		}
	}
	}
