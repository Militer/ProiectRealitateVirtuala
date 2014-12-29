package mygame.Models;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingVolume;
import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.jme3.collision.UnsupportedCollisionException;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import java.util.Queue;
import utils.Rotation;
import utils.Translation;

public class Spider{
    private Spatial spider;
    public Spider(AssetManager assetManager, Translation t, Rotation r){
        spider = assetManager.loadModel("Models/spooder/blender2ogre-export.j3o");
        spider.rotate(r.getxAngle(), r.getyAngle(), r.getzAngle());
        spider.setLocalTranslation(t.getX(), t.getY(), t.getZ());
    }

    public Spatial getSpider() {
        return spider;
    }
}
