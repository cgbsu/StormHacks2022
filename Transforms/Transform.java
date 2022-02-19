import java.util.ArrayList;
import java.awt.Graphics2D;

public abstract class Transform
{
    public String name;
    public ArrayList<Transform> children;
    public Vector2 position;
    public float orientation;
    

    public Transform( Transform[] children, Vector2 position, float orientation )
    {
        this.position = position;
        this.orientation = orientation;
        TransformStack.pushTransform( this );
        this.children = new ArrayList< Transform >();
        if( children != null ) {
            for( Transform child : children )
                this.children.add( child );
        }
    }
    public Transform( Transform[] children, Vector2 position ) {
        this( children, position, 0.0f );
    }
    public Transform( Transform[] children ) {
        this( children, new Vector2( 0, 0 ) );
    }
    public Transform() {
        this( null );
    }

    public abstract String name();

    public abstract void draw( Graphics2D g );

    public void addChild( Transform obj )
    {
        this.children.add(obj);
    }

    public Transform getChildByName(String name)
    {
        for ( Transform child : children )
        {
            if (child.name().compareTo( name ) == 0)
                return child;

            Transform littleBoy = child.getChildByName(name);
            
            if (littleBoy != null)
                return littleBoy;
        }

        return null;
    }

}
