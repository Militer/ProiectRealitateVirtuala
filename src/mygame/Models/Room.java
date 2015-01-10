package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import utils.Dimensions;

public class Room {
    private Node room = new Node();
    private static final float height = 5;
    
    public Room(AssetManager assetManager, Vector3f center, float length, float width){
        Floor floor = new Floor(assetManager, new Dimensions(length, 1, width));
        
        Wall wallRight = new Wall(assetManager, new Dimensions(1, height, width));
        Wall wallLeft = new Wall(assetManager, new Dimensions(1, height, width));
        Wall wallFront = new Wall(assetManager, new Dimensions(length, height, 1));
        Wall wallBack = new Wall(assetManager, new Dimensions(length, height, 1));
        
        floor.getFloor().setLocalTranslation(0, -1, 0);
        wallRight.getWall().setLocalTranslation(center.x + length, center.y + height, center.z);
        wallLeft.getWall().setLocalTranslation(center.x - length, center.y + height, center.z);
        wallFront.getWall().setLocalTranslation(center.x, center.y + height, center.z -length);
        wallBack.getWall().setLocalTranslation(center.x, center.y + height, center.z + length);
        
        room.attachChild(floor.getFloor());
        room.attachChild(wallRight.getWall());
        room.attachChild(wallLeft.getWall());
        room.attachChild(wallFront.getWall());
        room.attachChild(wallBack.getWall());
    }

    public static enum DoorOnWall{
        FRONT, BACK, LEFT, RIGHT;
    }
            
            
    public Node getRoom() {
        return room;
    }
   
}
