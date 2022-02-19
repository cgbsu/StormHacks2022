import java.util.ArrayList;
import java.awt.Graphics2D;

public class TransformStack
{
    static ArrayList< Transform > transforms;

    static void initalize() {
        transforms = new ArrayList< Transform >();
    }

    static void pushTransform( Transform newTransform ) {
        transforms.add( newTransform );
    }

    static void render( Graphics2D g ) {
        for( Transform current : transforms )
            renderTransform( g, current );
    }

    static void renderTransform( Graphics2D g, Transform current )
    {
        g.translate( current.position.x, current.position.x );
        g.rotate( current.orientation, current.position.x, current.position.y );
        current.draw( g );
        if( current.children != null ) {
            for( Transform child : current.children )
                renderTransform( g, child );
        }
        g.translate( -current.position.x, -current.position.x );
        g.rotate( -current.orientation, current.position.x, current.position.y );
    }
}
