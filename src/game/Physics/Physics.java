package game.Physics;
import game.elements.Element;
import Level.Level;
import Level.LevelObject;
import Level.Tile.Tile;
import java.util.ArrayList;

public class Physics {
	private final float gravity = 0.0015f;
	
    public void handlePhysics(Level level, int delta){
        handleElements(level,delta);
    }
	
	
	   private void handleElements(Level level, int delta){
	        for(Element e : level.getElements()){
	 
	            //and now decelerate the character if he is not moving anymore
	            if(!e.isMoving()){
	                e.decelerate(delta);
	            }
	 
	            handleGameObject(e,level,delta);
	        }
	    }
	    
	    private void handleGameObject(LevelObject obj, Level level, int delta){
	    	 
	        //first update the onGround of the object
	        obj.setOnGround(isOnGround(obj,level.getTiles()));
	 
	        //now apply gravitational force if we are not on the ground or when we are about to jump
	        if(!obj.isOnGround() || obj.getYVelocity() < 0)
	            obj.applyGravity(gravity*delta);
	        else
	            obj.setYVelocity(0);
	 
	        //calculate how much we actually have to move
	        float x_movement = obj.getXVelocity()*delta;
	        float y_movement   = obj.getYVelocity()*delta;
	 
	        //we have to calculate the step we have to take
	        float step_y = 0;
	        float step_x = 0;
	 
	        if(x_movement != 0){
	            step_y = Math.abs(y_movement)/Math.abs(x_movement);
	            if(y_movement < 0)
	                step_y = -step_y;
	 
	            if(x_movement > 0)
	                step_x = 1;
	            else
	                step_x = -1;
	 
	            if((step_y > 1 || step_y < -1) && step_y != 0){
	                step_x = Math.abs(step_x)/Math.abs(step_y);
	                if(x_movement < 0)
	                    step_x = -step_x;
	                if(y_movement < 0)
	                    step_y = -1;
	                else
	                    step_y = 1;
	            }
	        }else if(y_movement != 0){
	            //if we only have vertical movement, we can just use a step of 1
	            if(y_movement > 0)
	                step_y = 1;
	            else
	                step_y = -1;
	        }
	 
	        //and then do little steps until we are done moving
	        while(x_movement != 0 || y_movement != 0){
	 
	            //we first move in the x direction
	            if(x_movement != 0){
	                //when we do a step, we have to update the amount we have to move after this
	                if((x_movement > 0 && x_movement < step_x) || (x_movement > step_x  && x_movement < 0)){
	                    step_x = x_movement;
	                    x_movement = 0;
	                }else
	                    x_movement -= step_x;
	 
	                //then we move the object one step
	                obj.setX(obj.getX()+step_x);
	 
	                //if we collide with any of the bounding shapes of the tiles we have to revert to our original position
	                if(checkCollision(obj,level.getTiles())){
	 
	                   //undo our step, and set the velocity and amount we still have to move to 0, because we can't move in that direction
	                    obj.setX(obj.getX()-step_x);
	                    obj.setXVelocity(0);
	                    x_movement = 0;
	                }
	 
	            }
	            //same thing for the vertical, or y movement
	            if(y_movement != 0){
	                if((y_movement > 0 && y_movement < step_y) || (y_movement > step_y  && y_movement < 0)){
	                    step_y = y_movement;
	                    y_movement = 0;
	                }else
	                    y_movement -= step_y;
	 
	                obj.setY(obj.getY()+step_y);
	 
	                if(checkCollision(obj,level.getTiles())){
	                    obj.setY(obj.getY()-step_y);
	                    obj.setYVelocity(0);
	                    y_movement = 0;
	                    break;
	                }
	            }
	        }
	    }

	   public boolean checkCollision(LevelObject obj, Tile[][] mapTiles){
	        //get only the tiles that matter
	        ArrayList<Tile> tiles = obj.getBoundingShape().getTilesOccupying(mapTiles);
	        for(Tile t : tiles){
	            //if this tile has a bounding shape
	            if(t.getBoundingShape() != null){
	                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

	   public boolean isOnGround(LevelObject obj, Tile[][] mapTiles){
	        //we get the tiles that are directly "underneath" the characters, also known as the ground tiles
	        ArrayList<Tile> tiles = obj.getBoundingShape().getGroundTiles(mapTiles);
	 
	        //we lower the the bounding object a bit so we can check if we are actually a bit above the ground
	        obj.getBoundingShape().movePosition(0, 1);
	 
	        for(Tile t : tiles){
	            //not every tile has a bounding shape (air tiles for example)
	            if(t.getBoundingShape() != null){
	                //if the ground and the lowered object collide, then we are on the ground
	                if(t.getBoundingShape().checkCollision(obj.getBoundingShape())){
	                    //don't forget to move the object back up even if we are on the ground!
	                    obj.getBoundingShape().movePosition(0, -1);
	                    return true;
	                }
	            }
	        }
	        
	   
	 
	        //and obviously we have to move the object back up if we don't hit the ground
	        obj.getBoundingShape().movePosition(0, -1);
	 
	        return false;
	    }
    
	
	
}

