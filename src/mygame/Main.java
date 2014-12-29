package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;
import mygame.Models.Spider;
import utils.Rotation;
import utils.Translation;
import utils.Dimensions;
import mygame.Models.Floor;
import mygame.Models.IronMan;
import mygame.Models.Room;
import mygame.Models.Wall;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication implements  AnalogListener{

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    Vector3f walkDirection = new Vector3f();
    Vector3f camDir = new Vector3f();
    Vector3f camLeft = new Vector3f();
    CameraNode camNode;
    
    IronMan character;
    Spider spider1;
    Room room;
            
    @Override
    public void simpleInitApp() {       
        character = new IronMan(assetManager, new Translation(0f, -1.5f, 0f), new Rotation(0, 3.2f, 0));
        spider1 = new Spider(assetManager, new Translation(0f, 0f, -0.5f), new Rotation(0, 0, 0));
        room = new Room(assetManager);
        
        flyCam.setEnabled(false);
        //flyCam.setMoveSpeed(5);
        camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(ControlDirection.CameraToSpatial);
        camNode.setControlDir(ControlDirection.SpatialToCamera);
        //Attach the camNode to the target:
        camNode.attachChild(character.getCharacter());
        camNode.setLocalTranslation(character.getPosition());
        //chaseCam = new ChaseCamera(cam, character.getCharacter(), inputManager);
        //chaseCam.setSmoothMotion(true);
        
        rootNode.attachChild(character.getCharacter());
        rootNode.attachChild(spider1.getSpider());
        rootNode.attachChild(room.getRoom());
        
        //rootNode.setLocalTranslation(0, -1, 2.5f);
        registerInput();

  }

  public void registerInput() {
    inputManager.addMapping("rotateX", new MouseAxisTrigger(MouseInput.AXIS_X, true));
    inputManager.addMapping("rotateXReverse", new MouseAxisTrigger(MouseInput.AXIS_X, false));
    inputManager.addMapping("moveForward", new KeyTrigger(keyInput.KEY_UP), new KeyTrigger(keyInput.KEY_W));
    inputManager.addMapping("moveBackward", new KeyTrigger(keyInput.KEY_DOWN), new KeyTrigger(keyInput.KEY_S));
    inputManager.addMapping("moveRight", new KeyTrigger(keyInput.KEY_RIGHT), new KeyTrigger(keyInput.KEY_D));
    inputManager.addMapping("moveLeft", new KeyTrigger(keyInput.KEY_LEFT), new KeyTrigger(keyInput.KEY_A));
    inputManager.addMapping("displayPosition", new KeyTrigger(keyInput.KEY_P));
    inputManager.addListener(this, "moveForward", "moveBackward", "moveRight", "moveLeft", "rotateX", "rotateXReverse");
    inputManager.addListener(this, "displayPosition");
  }

    Quaternion q = new Quaternion();

    public void onAnalog(String name, float value, float tpf) {
        if (name.equals("moveForward")) {
            character.move(cam.getDirection().x * 3 * value, 0, cam.getDirection().z * 3 *  value);
        }        
        if (name.equals("moveBackward")) {
            character.move(cam.getDirection().x * -3 * value, 0, cam.getDirection().z * -3 *  value);
        }
        if (name.equals("moveRight")) {
            character.move(cam.getDirection().z * -3 * value, 0, cam.getDirection().x * 3 *  value);
        }
        if (name.equals("moveLeft")) {
            character.move(cam.getDirection().z * 3 * value, 0, cam.getDirection().x * -3 *  value);
         }
         cam.setLocation(character.getPosition());
        if(name.equals("rotateX")){
            character.rotate(0, value * 1, 0);
            camNode.rotate(0f, value * 1, 0f);
        }
        if(name.equals("rotateXReverse")){
            character.rotate(0, -value * 1, 0);
            camNode.rotate(0f, value * 1, 0f);
        }
    }
    

    @Override
    public void simpleUpdate(float tpf) {
    /*camDir.set(cam.getDirection()).multLocal(0.6f);
    camLeft.set(cam.getLeft()).multLocal(0.4f);
        walkDirection.set(0, 0, 0);
        if (left) {
            walkDirection.addLocal(camLeft);
        }
        if (right) {
            walkDirection.addLocal(camLeft.negate());
        }
        if (forward) {
            walkDirection.addLocal(camDir);
        }
        if (backward) {
            walkDirection.addLocal(camDir.negate());
        }
        //character.getCharacter().setWalkDirection(walkDirection);
        cam.setLocation(character.getPosition());
    */}

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(ColorRGBA.Blue);
    }
}
