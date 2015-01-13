package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import utils.Dimensions;

public class Floor {

    private Geometry floor;
    
    public Floor(AssetManager assetManager, Dimensions dimension, boolean floorB){
        Box floorBox = new Box(dimension.getX(), dimension.getY(), dimension.getZ());
        Geometry geom = new Geometry("floor", floorBox);        
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        if(floorB == true)
            mat.setTexture("ColorMap", assetManager.loadTexture("Textures/floor.jpg"));
        else {
            mat.setTexture("ColorMap", assetManager.loadTexture("Textures/wall.jpg"));
        }
        geom.setMaterial(mat);
        floor = geom;
    }

    public Geometry getFloor() {
        return floor;
    }
    
    public RigidBodyControl addControl(){
       RigidBodyControl rbc = new RigidBodyControl(0);
       floor.addControl(rbc);
       return rbc;
    }
}
