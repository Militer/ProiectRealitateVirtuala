package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import utils.Rotation;
import utils.Translation;

public class IronMan {
    private Spatial character;
    private Vector3f position;
    private Vector3f direction;
    
    public IronMan(AssetManager assetManager, Translation t, Rotation r){
        character = assetManager.loadModel("Models/IronMan/IronMan.j3o");
        character.rotate(r.getxAngle(), r.getyAngle(), r.getzAngle());
        character.setLocalTranslation(t.getX(), t.getY(), t.getZ());
        position = new Vector3f(t.getX(), t.getY() + 6.5f, t.getZ()  - 0.5f);
        direction = new Vector3f(0, 0, 0);
    }

    public Spatial getCharacter() {
        return character;
    }    

    public Vector3f getPosition() {
        return new Vector3f(position.x * direction.x, position.y, position.z * direction.z);
    }
    
    public void move(float x, float y, float z){
        character.move(x, y, z);
        position = position.add(x, y, z);
    }
    
    public void rotate(float x, float y, float z){
        character.rotate(x, y, z);
        direction = direction.add(x, y, z);
    }

    public Vector3f getDirection() {
        return direction;
    }
}