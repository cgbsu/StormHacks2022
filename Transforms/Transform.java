import java.util.ArrayList;
import java.awt.Graphics2D;

public abstract class Transform
{
    public String name;
    public ArrayList<Transform> children;
    public Vector2 position;
    public float orientation, speed;

    public Transform( Transform[] children, Vector2 position, float orientation )
    {
        this.position = position;
        this.orientation = orientation;
        // TransformStack.pushTransform( this );
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

    public Vector2 getAlignedCenter() {
        return new Vector2( 0, 0 );
    }

    Vector2 Lerp( Vector2 end, double deltaTime ) {
        return Vector2.add( end, position.negate() ).multiply( deltaTime ).multiply( speed );
    }

    Vector2 ThisLerp( Vector2 end, double deltaTime )
    {
        final Vector2 DeltaFinal = Lerp( end, deltaTime );
        position = Vector2.add( position, DeltaFinal );
        return DeltaFinal;
    }

    double Lerp( double angle, double deltaTime, double angularVelocity ) {
        return ( angle - orientation ) * deltaTime * angularVelocity;
    }

    double ThisLerp( double angle, double deltaTime, double angularVelocity )
    {
        final double DeltaFinal = Lerp( angle, deltaTime, angularVelocity );
        orientation += DeltaFinal;
        return orientation;
    }

    Vector2 Translate( Vector2 direction, double deltaTime ) {
        return direction.toUnitVector().multiply( speed ).multiply( deltaTime );
    }

    Vector2 TranslateThis( Vector2 direction, double deltaTime ) {
        final Vector2 DeltaFinal = Translate( direction, deltaTime );
        position = Vector2.add( position, DeltaFinal );
        return DeltaFinal;
    }

    double Translate( double angularVelocity, double deltaTime ) {
        return angularVelocity * deltaTime;
    }

    double ThisTranslate( double angularVelocity, double deltaTime )
    {
        final double DeltaFinal = Translate( angularVelocity, deltaTime );
        orientation += DeltaFinal;
        return DeltaFinal;
    }


    public Transform getChildByName(String name)
    {
        for ( Transform child : children )
        {
            if (child.toString().compareTo( name ) == 0)
                return child;

            Transform littleBoy = child.getChildByName(name);
            
            if (littleBoy != null)
                return littleBoy;
        }

        return null;
    }

}
