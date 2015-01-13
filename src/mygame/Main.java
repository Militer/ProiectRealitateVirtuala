package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.light.DirectionalLight;
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
import mygame.Models.Hallway;
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
    Node characterNode = new Node();
    Node neckNode = new Node();
        
    IronMan character;
    Spider spider1;
    Room room;
    Room room2;
    Room room3;
            
    @Override
    public void simpleInitApp() {       
        character = new IronMan(assetManager, new Translation(0, -1.5f, 0f), new Rotation(0, 0, 0));
        //spider1 = new Spider(assetManager, new Translation(0f, 2f, 0), new Rotation(0, 0, 5));
        room = new Room(assetManager, new Vector3f(0, 0 ,0), 10, 10);
        room.getRoom().setLocalTranslation(15, 0, 10);
        
        room2 = new Room(assetManager, new Vector3f(0, 0 ,0), 10, 10);
        room2.getRoom().setLocalTranslation(15, 0, 30);
        
        room3 = new Room(assetManager, new Vector3f(0, 0 ,0), 10, 10);
        room3.getRoom().setLocalTranslation(15, 0, 50);
        
        Hallway hw = new Hallway(assetManager);
        
        //characterNode.attachChild(character.getCharacter());
        camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(ControlDirection.SpatialToCamera);
        //Attach the camNode to the target:
        characterNode.attachChild(character.getCharacter());
        characterNode.attachChild(neckNode);
        neckNode.attachChild(camNode);
        neckNode.setLocalTranslation(character.getPosition());
        
        flyCam.setEnabled(true);
        inputManager.setCursorVisible(true);
        
        rootNode.attachChild(character.getCharacter());
        rootNode.attachChild(characterNode);
        //rootNode.attachChild(spider1.getSpider());
        rootNode.attachChild(room.getRoom());
        rootNode.attachChild(room2.getRoom());
        rootNode.attachChild(room3.getRoom());
        //rootNode.attachChild(spider1.getSpider());
        neckNode.move(0, 6.5f, 0);
        camNode.move(0, 0.8f, 0);
        rootNode.attachChild(hw.getHallway());
        
        registerInput();

  }

  public void registerInput() {
    inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, true));
    inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, false));
    inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
    inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
    inputManager.addMapping("moveForward", new KeyTrigger(keyInput.KEY_UP), new KeyTrigger(keyInput.KEY_W));
    inputManager.addMapping("moveBackward", new KeyTrigger(keyInput.KEY_DOWN), new KeyTrigger(keyInput.KEY_S));
    inputManager.addMapping("moveRight", new KeyTrigger(keyInput.KEY_RIGHT), new KeyTrigger(keyInput.KEY_D));
    inputManager.addMapping("moveLeft", new KeyTrigger(keyInput.KEY_LEFT), new KeyTrigger(keyInput.KEY_A));
    inputManager.addMapping("displayPosition", new KeyTrigger(keyInput.KEY_P));
    inputManager.addListener(this, "moveForward", "moveBackward", "moveRight", "moveLeft", "rotateLeft", "rotateRight", "rotateUp", "rotateDown");
    inputManager.addListener(this, "displayPosition");
  }

    Quaternion q = new Quaternion();
    float rotUp = 0;
    static final float movementSpeed = 2, rotationSpeed = 5;

    public void onAnalog(String name, float value, float tpf) {
        Vector3f direction = new Vector3f();
        float xCam = direction.set(cam.getDirection()).x, zCam = direction.set(cam.getDirection()).z;
        float r = (float) (movementSpeed / Math.sqrt((xCam * xCam) + (zCam * zCam)));
        xCam *= r;
        zCam *= r;
        cam.getDirection().normalize();
        if (name.equals("moveForward")) {
          direction.set(xCam * 3 * value, 0, zCam * 3 *  value);
          characterNode.move(direction);
          character.getCharacter().move(direction);
        }
        if (name.equals("moveBackward")) {
          direction.set(-xCam * 3 * value, 0, -zCam * 3 *  value);
          characterNode.move(direction);
          character.getCharacter().move(direction);
        }
        if (name.equals("moveRight")) {
          direction.set(-zCam * 3 *  value, 0, xCam * 3 * value);
          characterNode.move(direction);
          character.getCharacter().move(direction);
        }
        if (name.equals("moveLeft")) {
          direction.set(zCam * 3 *  value, 0, -xCam * 3 * value);
          characterNode.move(direction);
          character.getCharacter().move(direction);
        }
        
        if (name.equals("rotateLeft")) {
          characterNode.rotate(0, rotationSpeed * tpf, 0);
          character.getCharacter().rotate(0, rotationSpeed * tpf, 0);
        }
        if (name.equals("rotateRight")) {
          characterNode.rotate(0, -rotationSpeed * tpf, 0);
          character.getCharacter().rotate(0, -rotationSpeed * tpf, 0);
        }
        if (name.equals("rotateUp") && rotUp < 1.3f) {
          neckNode.rotate(rotationSpeed * tpf, 0, 0);
          rotUp += rotationSpeed * tpf;
        }
        if (name.equals("rotateDown") && rotUp > -1.5f) {
            neckNode.rotate(-rotationSpeed * tpf, 0, 0);
            rotUp -= rotationSpeed * tpf;
        }
    }
    

    @Override
    public void simpleUpdate(float tpf) {
    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(ColorRGBA.Blue);
    }
}
