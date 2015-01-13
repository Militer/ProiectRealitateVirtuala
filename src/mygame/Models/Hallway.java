/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import utils.Dimensions;

/**
 *
 * @author Bogdan
 */
public class Hallway {
    private Node hallway;
    
    private Floor above;
    private Floor floor;
    private Wall right;
    private Wall back;
    private Wall front;
    
    public Hallway(AssetManager assetManager) {
        hallway = new Node();

        front = new Wall(assetManager, new Dimensions(5,10,1));
        front.getWall().setLocalTranslation(0,0,60);
        
        above = new Floor(assetManager, new Dimensions(5,1,30), false);
        above.getFloor().setLocalTranslation(0,10,30);
        
        back = new Wall(assetManager, new Dimensions(5, 10, 1));
        back.getWall().setLocalTranslation(0,0,-1);
        
        right = new Wall(assetManager, new Dimensions(1,10,30));
        right.getWall().setLocalTranslation(-6, 0, 30);
        
        floor = new Floor(assetManager, new Dimensions(5,1,30), true);
        floor.getFloor().setLocalTranslation(0,-1,30);
        
        hallway.attachChild(right.getWall());
        hallway.attachChild(floor.getFloor());
        hallway.attachChild(back.getWall());
        hallway.attachChild(above.getFloor());
        hallway.attachChild(front.getWall());
    }
    public Node getHallway() {
        return hallway;
    }
}