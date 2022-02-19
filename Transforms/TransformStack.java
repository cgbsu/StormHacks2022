import java.awt.Graphics2Dl;
+
public static class TransformStack
{
    ArrayList< Transform > transforms;

    void initalize() {
        transforms = new ArrayList< Transform >();
    }

    void pushTransform( Transform newTransform ) {
        transforms.add( newTransform );
    }

    void render( Graphics2D g ) {
        for( Transform current : transforms )
            renderTransform( g, current );
    }

    void renderTransform( Graphics2D g, Transform current )
    {

        g.rotate( current.orientation, current.position.x, current.position.y )
        g.draw( g );
        for( Transform child : current.children )
            renderTransform( g, child );
        g.rotate( -current.orientation, current.position.x, current.position.y );
    }
}
