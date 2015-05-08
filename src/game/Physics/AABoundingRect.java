
package game.Physics;
import java.util.ArrayList;

import Level.Tile.Tile;

public  class AABoundingRect extends BoundingShape{
	  
	public float x; 
	public float y; 
	public float width; 
	public float height;
	    
	public AABoundingRect(float x, float y, float width, float height) { 
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	 
	public void updatePosition(float newX, float newY) {
		this.x = newX;
		this.y = newY;
	}
	 
	public void movePosition(float x, float y) { 
		this.x += x; 
		this.y += y;
	}
	    
	public boolean checkCollision(AABoundingRect rect) {
		return !(rect.x > this.x+width || rect.x+rect.width < this.x || rect.y > this.y+height || rect.y+rect.height < this.y);
	}
	
	public ArrayList<Tile> getTilesOccupying(Tile[][] tiles) {
		ArrayList<Tile> occupiedTiles = new ArrayList<Tile>();
		
		for(int i = (int) x; i <= x+width+(32-width%32); i+=32){
			for(int j = (int) y; j <= y+height+(32-height%32); j+=32){
				occupiedTiles.add(tiles[i/32][j/32]);
			}
		}
		return occupiedTiles;
	    
	}
	    
	public ArrayList<Tile> getGroundTiles(Tile[][] tiles) {
		ArrayList<Tile> tilesUnderneath = new ArrayList<Tile>();
		int j = (int) (y+height+1);
		
		for(int i = (int) x; i <= x+width+(32-width%32); i+=32){
			tilesUnderneath.add(tiles[i/32][j/32]);
		}
		
		return tilesUnderneath;
	}
}

