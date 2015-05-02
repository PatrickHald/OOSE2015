package Level.Tile;
import game.Physics.AABoundingRect;
import game.Physics.BoundingShape;

public class SolidTile extends Tile{
		
		public SolidTile(int x, int y) {
        super(x, y);
        boundingShape = new AABoundingRect(x*32,y*32,32,32);  
    }
	
}
