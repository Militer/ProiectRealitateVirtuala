package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import utils.Dimensions;
import utils.Rotation;
import utils.Translation;

public class Room {
    private Node room = new Node();
    private static final float height = 5;
    
    public Room(AssetManager assetManager, Vector3f center, float length, float width){
        Floor floor = new Floor(assetManager, new Dimensions(length, 1, width), true);
        Floor ceiling = new Floor(assetManager, new Dimensions(length, 1, width), false);
        
        Spatial aq = assetManager.loadModel("Models/Aquarium/Aquarium.j3o");
        aq.setLocalTranslation(0, 0, 3);
        
        Spider tarantula = new Spider(assetManager, new Translation(0,2,3), new Rotation(0,0,5));
        tarantula.getSpider().scale(0.1f);
        
        Wall wallRight = new Wall(assetManager, new Dimensions(1, height, width));
        Wall wallLeftUpper = new Wall(assetManager, new Dimensions(1, height/4, width));
        Wall wallLeftLower1 = new Wall(assetManager, new Dimensions(1, height - 1.25f, width/3));
        Wall wallLeftLower2 = new Wall(assetManager, new Dimensions(1, height - 1.25f, width/3));
        Wall wallFront = new Wall(assetManager, new Dimensions(length, height, 1));
        Wall wallBack = new Wall(assetManager, new Dimensions(length, height, 1));
        
        floor.getFloor().setLocalTranslation(0, -1, 0);
        ceiling.getFloor().setLocalTranslation(0, height + 5, 0);
        wallRight.getWall().setLocalTranslation(center.x + length, center.y + height, center.z);
        wallLeftUpper.getWall().setLocalTranslation(center.x - length, center.y + height + 3.8f, center.z);
        wallLeftLower1.getWall().setLocalTranslation(center.x - length, center.y + 3.8f, center.z - 6f);
        wallLeftLower2.getWall().setLocalTranslation(center.x - length, center.y + 3.8f, center.z + 6f);
        wallFront.getWall().setLocalTranslation(center.x, center.y + height, center.z -length);
        wallBack.getWall().setLocalTranslation(center.x, center.y + height, center.z + length);
        
        room.attachChild(floor.getFloor());
        room.attachChild(wallRight.getWall());
        room.attachChild(wallLeftUpper.getWall());
        room.attachChild(wallLeftLower1.getWall());
        room.attachChild(wallLeftLower2.getWall());
        room.attachChild(wallFront.getWall());
        room.attachChild(wallBack.getWall());
        room.attachChild(ceiling.getFloor());
        room.attachChild(aq);
        room.attachChild(tarantula.getSpider());
    }

    public static enum DoorOnWall{
        FRONT, BACK, LEFT, RIGHT;
    }
            
            
    public Node getRoom() {
        return room;
    }
   
}
