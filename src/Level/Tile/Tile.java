package Level.Tile;


import game.Physics.BoundingShape;


public class Tile {
		protected BoundingShape boundingShape = null;
		protected int x;
	    protected int y;
	   
	   
	    public BoundingShape getBoundingShape(){
	    	return boundingShape;
	    }
	 
	    public Tile(int x,int y) {
	        this.x = x;
	        this.y = y;
	    }
	 
	    public int getX(){
	        return x;
	    }
	 
	    public int getY(){
	        return y;
	    }
	
}
