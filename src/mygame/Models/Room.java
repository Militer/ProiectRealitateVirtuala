package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;
import utils.Dimensions;

public class Room {
    private Node room = new Node();
    private static final float height = 5;
    //private List<Vector3f> boundingBoxes = new ArrayList<Vector3f>(); 
    Wall wallRight, wallLeft, wallFront, wallBack;
    Floor floor;
    
    public Room(AssetManager assetManager, Vector3f center, float length, float width){
        floor = new Floor(assetManager, new Dimensions(length, 1, width));
        
        wallRight = new Wall(assetManager, new Dimensions(1, height, width));
        wallLeft = new Wall(assetManager, new Dimensions(1, height, width));
        wallFront = new Wall(assetManager, new Dimensions(length, height, 1));
        wallBack = new Wall(assetManager, new Dimensions(length, height, 1));
        
        floor.getFloor().setLocalTranslation(0, -1, 0);
        wallRight.getWall().setLocalTranslation(center.x + length, center.y + height, center.z);
        wallLeft.getWall().setLocalTranslation(center.x - length, center.y + height, center.z);
        wallFront.getWall().setLocalTranslation(center.x, center.y + height, center.z -length);
        wallBack.getWall().setLocalTranslation(center.x, center.y + height, center.z + length);
        
        /*BoundingBox rightWallCollison = new BoundingBox(new Vector3f(center.x - length, center.y + height, center.z);
        BoundingBox leftWallCollison = new BoundingBox(new Vector3f(center.x - length, center.y + height, center.z), 1, height, width);
        BoundingBox frontWallCollison = new BoundingBox(new Vector3f(center.x, center.y + height, center.z -length), length, height, 1);
        BoundingBox backWallCollison = new BoundingBox(new Vector3f(center.x, center.y + height, center.z + length), length, height, 1);
        
        boundingBoxes.add(new Vector3f());
        boundingBoxes.add(new Vector3f());
        boundingBoxes.add(new Vector3f());
        boundingBoxes.add(new Vector3f());
        */
                
        room.attachChild(floor.getFloor());
        room.attachChild(wallRight.getWall());
        room.attachChild(wallLeft.getWall());
        room.attachChild(wallFront.getWall());
        room.attachChild(wallBack.getWall());
    }
            
    public Node getRoom() {
        return room;
    }

    public void addPhysicsSpace(BulletAppState bulletAppState) {
       bulletAppState.getPhysicsSpace().add(wallRight.addControl());
       bulletAppState.getPhysicsSpace().add(wallLeft.addControl());
       bulletAppState.getPhysicsSpace().add(wallFront.addControl());
       bulletAppState.getPhysicsSpace().add(wallBack.addControl());
       
       RigidBodyControl rbc = new RigidBodyControl(0);
       floor.getFloor().addControl(rbc);
       bulletAppState.getPhysicsSpace().add(rbc);

    }   
}
