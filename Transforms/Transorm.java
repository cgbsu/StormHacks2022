public class Transform
{
    Transform[] children;
    Vector2 position;
    float orientation;
    
    public Transform () {
        TransformStack.pushTransform( this );
    }

    abstract void draw ( Graphics2D g );
}
