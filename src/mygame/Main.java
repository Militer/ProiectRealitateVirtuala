package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingBox;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.BetterCharacterControl;
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
import java.util.ArrayList;
import java.util.List;
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
public class Main extends SimpleApplication implements ActionListener, AnalogListener {

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
    
    List<Vector3f> boundingBoxes = new ArrayList<Vector3f>();
            
    Vector3f walkDirection = new Vector3f();
    Vector3f camDir = new Vector3f();
    Vector3f camLeft = new Vector3f();
    CameraNode camNode;
    Node characterNode = new Node();
    Node bodyNode = new Node();
    Node neckNode = new Node();
        
    IronMan character;
    Spider spider1;
    Room room;
    Room room2;
    Room room3;
            
    private BetterCharacterControl player;
    private BulletAppState bulletAppState;
  
  
    @Override
    public void simpleInitApp() { 
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
    
    
        character = new IronMan(assetManager, new Translation(0, -1.5f, 0f), new Rotation(0, 0, 0));
        //spider1 = new Spider(assetManager, new Translation(0f, 2f, 0), new Rotation(0, 0, 5));
        room = new Room(assetManager, new Vector3f(0, 0 ,0), 10, 10);
        room.getRoom().setLocalTranslation(15, 0, 10);
        
        room2 = new Room(assetManager, new Vector3f(0, 0 ,0), 10, 10);
        room2.getRoom().setLocalTranslation(15, 0, 30);
        
        room3 = new Room(assetManager, new Vector3f(0, 0 ,0), 10, 10);
        room3.getRoom().setLocalTranslation(15, 0, 50);
        
        Hallway hw = new Hallway(assetManager);
        
        camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(ControlDirection.SpatialToCamera);
        
        player = new BetterCharacterControl(1.5f, 4f, 1f);
        //player.warp(character.getPosition());
        player.setGravity(new Vector3f(0, 1, 0));
        player.setJumpForce(new Vector3f(0, 2, 0));
        
        //Attach the camNode to the target:
        neckNode.attachChild(camNode);
        neckNode.setLocalTranslation(character.getPosition());
        
        bodyNode.attachChild(character.getCharacter());
        bodyNode.attachChild(neckNode);
        
        characterNode.attachChild(bodyNode);
        
        flyCam.setEnabled(true);
        inputManager.setCursorVisible(true);
        //flyCam.setEnabled(false);
        
        
        bulletAppState.getPhysicsSpace().add(player);
        bulletAppState.getPhysicsSpace().addAll(characterNode);        
        
        //rootNode.attachChild(character.getCharacter());
        rootNode.attachChild(characterNode);
        //rootNode.attachChild(spider1.getSpider());
        rootNode.attachChild(room.getRoom());
        rootNode.attachChild(room2.getRoom());
        rootNode.attachChild(room3.getRoom());
        //rootNode.attachChild(spider1.getSpider());
        neckNode.move(0, 6.5f, 0);
        camNode.move(0, 0.8f, 0);
        rootNode.attachChild(hw.getHallway());
        
        //camNode.addControl(player);
        characterNode.addControl(player);
        //character.getCharacter().addControl(player);
        
        room.addPhysicsSpace(bulletAppState);
        room2.addPhysicsSpace(bulletAppState);
        room3.addPhysicsSpace(bulletAppState);
        hw.addPhysicsSpace(bulletAppState);
        
        
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
    inputManager.addMapping("jump", new KeyTrigger(keyInput.KEY_SPACE));
    
    
    inputManager.addMapping("displayPosition", new KeyTrigger(keyInput.KEY_P));
    inputManager.addListener(this, "moveForward", "moveBackward", "moveRight", "moveLeft", "rotateLeft", "rotateRight", "rotateUp", "rotateDown", "jump");
    inputManager.addListener(this, "displayPosition");
  }

    float rotUp = 0;
    boolean forward = false, backward = false, right = false, left = false, jump = false;
    Vector3f direction = new Vector3f();
    
    static final float movementSpeed = 5f, rotationSpeed = 2;
    
    @Override
    public void onAnalog(String name, float value, float tpf){
         if (name.equals("rotateLeft")) {
          bodyNode.rotate(0, rotationSpeed * tpf, 0);
        }
        if (name.equals("rotateRight")) {
          bodyNode.rotate(0, -rotationSpeed * tpf, 0);
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
    public void onAction(String name, boolean keyIsPressed, float tpf) {       
        if (name.equals("moveForward")) {
          forward = keyIsPressed;
        }
        if (name.equals("moveBackward")) {
          backward = keyIsPressed;
        }
        if (name.equals("moveRight")) {
          right = keyIsPressed;
        }
        if (name.equals("moveLeft")) {
          left = keyIsPressed;
        }   
        if (name.equals("jump")) {
          jump = keyIsPressed;
        } 
    }
    

    @Override
    public void simpleUpdate(float tpf) {
        float xCam = direction.set(cam.getDirection()).x, zCam = direction.set(cam.getDirection()).z;
        float r = (float) (movementSpeed / Math.sqrt((xCam * xCam) + (zCam * zCam)));
        xCam *= r;
        zCam *= r;
        /*Vector3f camDir = cam.getDirection().clone();
        Vector3f camLeft = cam.getLeft().clone();
        camDir.y = 0;
        camLeft.y = 0;
        camDir.normalizeLocal();
        camLeft.normalizeLocal();*/
        walkDirection.set(0, 0, 0);
        
        if(forward == true){
           walkDirection.addLocal(xCam, 0, zCam);
        }
        if(backward == true){
           walkDirection.addLocal(-xCam, 0, -zCam);
        }
        if(right == true){
            walkDirection.addLocal(-zCam , 0, xCam);
        }
        if(left == true){
            walkDirection.addLocal(+zCam , 0, -xCam);
        }
        if(jump == true){
           player.jump();
        }
        
         if(forward || backward || left || right) {
            //Vector3f x= direction.mult(600 * tpf);
            //System.out.println(x.x + "- " + x.y + " - " + x.z);
            player.setWalkDirection(walkDirection.mult(200 * tpf));
            //bodyNode.move(direction);
            //character.getCharacter().move(direction);
       
        }
         else {
             player.setWalkDirection(Vector3f.ZERO);
         }      
    }

    @Override
    public void simpleRender(RenderManager rm) {
        viewPort.setBackgroundColor(ColorRGBA.Blue);
    }
}
