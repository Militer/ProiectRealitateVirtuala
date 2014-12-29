package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import utils.Dimensions;

public class Room {
    private Node room = new Node();
    
    public Room(AssetManager assetManager){
        Floor floor = new Floor(assetManager, new Dimensions(10, 1f, 10f));
        Wall wallRight = new Wall(assetManager, new Dimensions(1,10,10));
        Wall wallLeft = new Wall(assetManager, new Dimensions(1,10,10));
        Wall wallFront = new Wall(assetManager, new Dimensions(10,10,1));
        Wall wallBack = new Wall(assetManager, new Dimensions(10,10,1));
        
        floor.getFloor().setLocalTranslation(0, -1, 0);
        wallRight.getWall().setLocalTranslation(10, 10, 0);
        wallLeft.getWall().setLocalTranslation(-10, 10, 0);
        wallFront.getWall().setLocalTranslation(0, 10, -10);
        wallBack.getWall().setLocalTranslation(0, 10, 10);
        
        room.attachChild(floor.getFloor());
        room.attachChild(wallRight.getWall());
        room.attachChild(wallLeft.getWall());
        room.attachChild(wallFront.getWall());
        room.attachChild(wallBack.getWall());
    }

    public Node getRoom() {
        return room;
    }
   
}
