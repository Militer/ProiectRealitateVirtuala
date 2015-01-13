/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Bogdan
 */
import com.jme3.material.Material;
import com.jme3.texture.Texture;
import utils.Dimensions;
public class Wall {
    private Box _wall;
    private Geometry _wallG;
    
    public Wall(AssetManager assetManager, Dimensions d) {
        _wall = new Box(d.getX(), d.getY(), d.getZ());
        _wallG = new Geometry("WalG",_wall);
        Material wallMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture wallTex = assetManager.loadTexture("Textures/wall.jpg");
        wallMat.setTexture("ColorMap", wallTex);
        _wallG.setMaterial(wallMat);        
    }
    public Geometry getWall() {
        return _wallG;
    }
    
    public RigidBodyControl addControl(){
        RigidBodyControl rbc = new RigidBodyControl(0);
        _wallG.addControl(rbc);
        return rbc;
    }
}
