import java.util.ArrayList;
import java.awt.Graphics2D;

public class TransformStack
{
    static ArrayList< Transform > transforms;

    protected static Graphics2D previouslyUsedGraphics2D;

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
        // System.out.println( "<renderTransform " + current.name() + ">" );
        g.translate( current.position.x, current.position.y );
        g.rotate( current.orientation, current.getAlignedCenter().x, current.getAlignedCenter().y );
        current.draw( g );
        if( current.children != null ) {
            for( Transform child : current.children )
                renderTransform( g, child );
        }
        g.rotate( -current.orientation, current.getAlignedCenter().x, current.getAlignedCenter().y );
        g.translate( -current.position.x, -current.position.y );
        previouslyUsedGraphics2D = g;
        // System.out.println( "</renderTransform " + current.name() + ">" );
    }

    public static Vector2 getPreviousGraphicsGlobalTranslation() {
        return new Vector2( 
                ( int ) previouslyUsedGraphics2D.getTransform().getTranslateX(), 
                ( int ) previouslyUsedGraphics2D.getTransform().getTranslateY() 
            );
    }
}
