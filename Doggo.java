import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Doggo
{
    public Transform obj;
    public DoggoTextBox textBox;
    public HappyQuotes quoteGen;

    class Paw extends Bone
    {
        double bodyRadius;
        public String name() { return "Doggo_Paw"; }
        Paw( double bodyRadius, Bone.BodyVertical vertical, Bone.BodyHorizontal horizontal ) {
            super( null );
            this.vertical = vertical;
            this.horizontal = horizontal;
            this.bodyRadius = bodyRadius;
        }
        public void draw( Graphics2D g )
        {
            g.setColor( Color.BLUE );
            int bodyRadius_ = ( int ) bodyRadius;
            g.fillOval( 0, 
                    ( bodyRadius_ ), 
                    ( bodyRadius_ / 5 ), 
                    ( bodyRadius_ / 5 ) );
        }
    }

    class TopLeg extends Bone
    {

        public String name() { return "Doggo_TopLeg"; }

        double bodyRadius;

        TopLeg( double bodyRadius, Bone.BodyHorizontal horizontal )
        {
            super( new Bone[] { new Paw( bodyRadius, Bone.BodyVertical.TOP, horizontal ) } );
            this.horizontal = horizontal;
            this.bodyRadius = bodyRadius;
            int bodyRadius_ = ( int ) bodyRadius;
            position.x =-1 + leftRightScalar( this.horizontal ) * ( bodyRadius_ / 3 );
            
            /*( ( bodyRadius_ / 6 ) * ( 
                        ( horizontal == BodyHorizontal.LEFT ) ? 
                                -1 : 5 
                    ) );*/
            position.y = ( bodyRadius_ / 10 );
        }

        public void draw( Graphics2D g )
        {
            g.setColor( Color.GREEN );
            int bodyRadius_ = ( int ) bodyRadius;
            g.fillOval( position.x, position.y, ( bodyRadius_ / 5 ), ( bodyRadius_ ) );
        }
    }
    
    public class DoggoTextBox extends Transform
    {
        public String text;
        VirtualPetGraphics window;
        int timeSinceClick = 0;
        HappyQuotes quoteGen = new HappyQuotes();
        
        DoggoTextBox ( Vector2 pos, VirtualPetGraphics window ) {
            super();
            text = "Test text";
            this.window = window;
            this.position = pos;
        }
        public String name() { return "Doggo_Textbox"; }

        public void draw( Graphics2D g )
        {
            if( window.clicked == true )
            {
                double x = g.getTransform().getTranslateX();
                double y = g.getTransform().getTranslateY();
                System.out.print("X:");System.out.print(x);System.out.print("Y:");System.out.println(y);
                if( window.mouseX < x + 100 && window.mouseY < y + 100 && window.mouseX > x && window.mouseX > y ) {
                    timeSinceClick = 1000;
                    text = quoteGen.quoteGrab();
                }
            }
            float tempScale = 3;
            if( timeSinceClick-- > 0 )
            {
                g.scale( tempScale, tempScale );
                g.drawString( text, 0, 0 );
                g.scale( 1.0f / tempScale, 1.0f / tempScale );
            }
        }
    }

    class ThicccBod extends Bone
    {
        double radius;
        ThicccBod( double radius, Bone[] children ) {
            super( children );
            this.radius = radius;
        }
        public void draw( Graphics2D g ) {
            g.setColor( Color.ORANGE );
            g.fillOval( 0, 0, ( int ) radius, ( int ) radius );
        }
        public String name() {
            return "Thiccccc";
        }
    }

    public Doggo( Vector2 pos, VirtualPetGraphics window )
    {
        TransformStack.initalize();
        double bodyRadius = 100;
        this.obj = new ThicccBod(
            bodyRadius, 
            new Bone[] {
                new TopLeg( bodyRadius, Bone.BodyHorizontal.LEFT ), 
                new TopLeg( bodyRadius, Bone.BodyHorizontal.RIGHT ) 
            }
        );
        this.obj.position.x = 300;
        this.obj.position.y = 100;
        this.textBox = new DoggoTextBox( new Vector2( 10, 10 ), window );
        this.obj.addChild(this.textBox);

    }
    
    public void update() {}
}
