package utils;

public class Rotation {
    private float xAngle, yAngle, zAngle;
    
    public Rotation(float x, float y, float z){
        xAngle = x;
        yAngle = y;
        zAngle = z;
    }

    public float getxAngle() {
        return xAngle;
    }

    public float getyAngle() {
        return yAngle;
    }

    public float getzAngle() {
        return zAngle;
    }   
}
