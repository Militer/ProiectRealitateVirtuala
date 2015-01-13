package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import com.jme3.scene.Spatial;
import java.util.List;
import utils.Dimensions;
import utils.Rotation;
import utils.Translation;

public class Room {
    private Node room = new Node();
    private static final float height = 5;
    //private List<Vector3f> boundingBoxes = new ArrayList<Vector3f>(); 
    Wall wallRight, wallFront, wallBack, wallLeftUpper, wallLeftLower1, wallLeftLower2;
    Floor floor, ceiling;
    Spatial aq;
    
    public Room(AssetManager assetManager, Vector3f center, float length, float width){
        floor = new Floor(assetManager, new Dimensions(length, 1, width), true);
        ceiling = new Floor(assetManager, new Dimensions(length, 1, width), false);
        
        aq = assetManager.loadModel("Models/Aquarium/Aquarium.j3o");
        aq.setLocalTranslation(0, 0, 3);
        
        Spider tarantula = new Spider(assetManager, new Translation(0,2,3), new Rotation(0,0,5));
        tarantula.getSpider().scale(0.1f);
        
        wallRight = new Wall(assetManager, new Dimensions(1, height, width));
        wallFront =  new Wall(assetManager, new Dimensions(width, height, 1));
        wallBack =  new Wall(assetManager, new Dimensions(width, height, 1));
        
        wallLeftUpper = new Wall(assetManager, new Dimensions(1, height/4, width));
        wallLeftLower1 = new Wall(assetManager, new Dimensions(1, height - 1.25f, width/3));
        wallLeftLower2 = new Wall(assetManager, new Dimensions(1, height - 1.25f, width/3));
        
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
            
    public Node getRoom() {
        return room;
    }

    public void addPhysicsSpace(BulletAppState bulletAppState) {
       bulletAppState.getPhysicsSpace().add(wallRight.addControl());
       bulletAppState.getPhysicsSpace().add(wallLeftUpper.addControl());
       bulletAppState.getPhysicsSpace().add(wallLeftLower1.addControl());
       bulletAppState.getPhysicsSpace().add(wallLeftLower2.addControl());
       bulletAppState.getPhysicsSpace().add(wallFront.addControl());
       bulletAppState.getPhysicsSpace().add(wallBack.addControl());
       bulletAppState.getPhysicsSpace().add(floor.addControl());
       bulletAppState.getPhysicsSpace().add(ceiling.addControl());
       RigidBodyControl rbc = new RigidBodyControl(0);
       aq.addControl(rbc);
       bulletAppState.getPhysicsSpace().add(aq);
       
    }   
}
