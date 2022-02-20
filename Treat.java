import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.MouseInfo;

public class Treat extends Transform
{
    Vector2 dimentions, center;
    public boolean active;
    VirtualPetGraphics window;

    public Treat( Vector2 dimentions, VirtualPetGraphics window )
    {
        super( null );
        this.position = new Vector2( 0, 0 );
        this.dimentions = dimentions;
        this.center = new Vector2( this.dimentions.x / 2, this.dimentions.y / 2 );
        this.window = window;
        active = false;
    }

    public void draw( Graphics2D g )
    {
        if( active == true )
        {
            g.setColor( new Color( 121, 68, 59 ) );
            g.fillRect( 0, 0, dimentions.x, dimentions.y );
            //Round off the edges.//
            g.fillOval( dimentions.y / 10, 0, dimentions.y / 10, dimentions.y );
            g.fillOval( dimentions.x - dimentions.y / 10, 0, dimentions.y / 10, dimentions.y );
            final int radialOffset = ( dimentions.y / 4 );
            for( int x : new int[] { 0 - radialOffset, dimentions.x - radialOffset } ) {
                for( int y : new int[] { 0 - radialOffset, dimentions.y - radialOffset} )
                    g.fillOval( x, y, dimentions.y / 2, dimentions.y / 2 );
            }
        }
            var point = window.getMousePosition();
            if( point != null ) {
                this.position.x = point.x;
                this.position.y = point.y;
            }
    }
    
    public Vector2 getAlignedCenter() {
        return center;
    }

    public String name() {
        return "Treat";
    }
}
