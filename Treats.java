import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.MouseInfo;

public class Treats extends Transform
{

    public class Treat extends Transform
    {
        Vector2 dimentions, center;
        public boolean active;

        public Treat( Vector2 dimentions )
        {
            super( null );
            this.position = new Vector2( 0, 0 );
            this.dimentions = dimentions;
            this.center = new Vector2( this.dimentions.x / 2, this.dimentions.y / 2 );
            active = false;
        }

        public void draw( Graphics2D g )
        {
            if( active == true )
            {
                var point = window.getMousePosition();
                if( point != null ) {
                    position.x = point.x;
                    position.y = point.y;
                }
                g.setColor( new Color( 121, 68, 59 ) );
                g.fillRect( 0, 0, dimentions.x, dimentions.y );
                //Round off the edges.//
                g.fillOval( dimentions.y / 10, 0, dimentions.y / 10, dimentions.y );
                g.fillOval( dimentions.x - dimentions.y / 10, 0, dimentions.y / 10, dimentions.y );
                for( int x : new int[] { 0 - ( dimentions.x / 3 ), dimentions.x - ( dimentions.x / 3 ) } ) {
                    for( int y : new int[] { 0 - ( dimentions.y / 3 ), dimentions.y - ( dimentions.y / 3 ) } )
                        g.fillOval( x, y, dimentions.y / 2, dimentions.y / 2 );
                }
            }
        }
        
        public Vector2 getAlignedCenter() {
            return center;
        }

        public String name() {
            return "Treat";
        }
    }

    Vector2 dimentions;
    VirtualPetGraphics window;
    int messageClickAreaRadius;
    Treat treat;

    public Treats( VirtualPetGraphics window, Vector2 position, Vector2 dimentions, int messageClickAreaRadius )
    {
        super( null );
        this.position = position;
        this.dimentions = dimentions;
        this.messageClickAreaRadius = messageClickAreaRadius;
        this.window = window;
        treat = new Treat( new Vector2( dimentions.x / 2, dimentions.y / 2 ) );
        TransformStack.pushTransform( treat );
    }
    
    public void select()
    {
        if( window.clicked == true )
        {
                Vector2 globalCoordinates = TransformStack.getPreviousGraphicsGlobalTranslation();
                System.out.println( "Testing Treat Click At: " + globalCoordinates );

                final double DistanceFromCursor = Vector2.add( 
                            window.mouseClickCoordinates, 
                            globalCoordinates.negate() 
                        ).magnitude();
                if( DistanceFromCursor < messageClickAreaRadius )
                {
                    System.out.println( "Clicked treat!!" );
                    //treat.position.x = window.mouseClickCoordinates.x;
                    //treat.position.y = window.mouseClickCoordinates.y;
                    treat.active = true;
                }

        }
    }

    public void draw( Graphics2D g )
    {
        select();
        g.setColor( Color.RED );
        //Round off the edges.//
        g.fillOval( -dimentions.x / 16, 0, dimentions.x / 8, dimentions.y );
        g.fillOval( dimentions.x - ( dimentions.x / 16 ), 0, dimentions.x / 8, dimentions.y );
        g.fillRect( 0, 0, dimentions.x, dimentions.y );
        g.setColor( Color.MAGENTA );
        double scale = 2.0;
        g.scale( scale, scale );
        g.drawString( "Treats", 0, dimentions.y / 4 );
        g.scale( 1.0 / scale, 1.0 / scale );
    }
    public String name() {
        return "TreatBag";
    }
}
