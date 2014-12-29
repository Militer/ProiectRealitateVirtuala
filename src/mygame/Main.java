package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import mygame.Models.Spider;
import utils.Rotation;
import utils.Translation;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    Spatial p;
    @Override
    public void simpleInitApp() {
        p = assetManager.loadModel("Models/spooder/blender2ogre-export.j3o");
        //p.setLocalTranslation(0,0,8);
        rootNode.attachChild(p);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        //p.rotate(0,0,1*tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(ColorRGBA.Blue);
        //TODO: add render code
    }
}
