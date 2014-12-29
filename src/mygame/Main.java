package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import mygame.Models.Spider;
import utils.Rotation;
import utils.Translation;
import utils.Dimensions;
import mygame.Models.Floor;
import mygame.Models.Wall;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    Spatial spider1;
    @Override
    public void simpleInitApp() {
        spider1 = new Spider(assetManager, new Translation(0f, 0f, 10f), new Rotation(0, 0, 0.0f)).spider;
        
        Geometry floor = new Floor(assetManager, new Dimensions(30.0f, 1.0f, 30.0f)).getFloor();
        Wall wallRight = new Wall(assetManager, new Dimensions(1,20,30));
        Wall wallLeft = new Wall(assetManager, new Dimensions(1,20,30));
        Wall wallFront = new Wall(assetManager, new Dimensions(30,20,1));
        Wall wallBack = new Wall(assetManager, new Dimensions(30,20,1));
        Spatial batman = assetManager.loadModel("Models/IronMan/IronMan.j3o");
        
        floor.setLocalTranslation(0, -20, 0);
        wallRight.getWall().setLocalTranslation(30, 0, 0);
        wallLeft.getWall().setLocalTranslation(-30, 0, 0);
        wallFront.getWall().setLocalTranslation(0, 0, -30);
        wallBack.getWall().setLocalTranslation(0, 0, 30);
        rootNode.attachChild(spider1);
        rootNode.attachChild(floor);
        rootNode.attachChild(wallRight.getWall());
        rootNode.attachChild(wallLeft.getWall());
        rootNode.attachChild(wallFront.getWall());
        rootNode.attachChild(wallBack.getWall());
        rootNode.attachChild(batman);
        //batman.scale(0.5f);
        batman.setLocalTranslation(0,0,-5);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(ColorRGBA.Blue);
        
        //TODO: add render code
    }
}
