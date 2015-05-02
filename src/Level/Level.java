package Level;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Image;






import Level.*;
import Level.Tile.AirTile;
import Level.Tile.SolidTile;
import Level.Tile.Tile;
import game.elements.*;

import java.util.ArrayList;

public class Level {
	   private TiledMap map;
	
	   
	   //a list of all characters present somewhere on this map
	   private ArrayList<Element> elements;
	   
	    private Tile[][] tiles;
	    public Level(String level) throws SlickException{
	        map = new TiledMap("data/levels/" + level + ".tmx",true);
	       // characters = new ArrayList<Character>();
	        elements = new ArrayList<Element>();
	        loadTileMap();

	       }
	    
	 
	   
	  
	    private void loadTileMap(){
	        //create an array to hold all the tiles in the map
	        tiles = new Tile[map.getWidth()][map.getHeight()];
	 
	        int layerIndex = map.getLayerIndex("CollisionLayer");
	 
	        if(layerIndex == -1){
	            //TODO we can clean this up later with an exception if we want, but because we make the maps ourselves this will suffice for now
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
	                switch(map.getTileProperty(tileID, "tileType", "Solid")){
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
   
	    public void render(){
	    	//chronological rendering of map, elements, etc.
	    	map.render(0, 0, 0, 0, 32, 18);
	    	
	        for(Element e: elements){
	        	e.render();
	        }
	    }
	    
	  
  


}