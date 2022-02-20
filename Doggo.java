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
        int bodyRadius, pawRadius;
        public String name() { return "Doggo_Paw"; }
        Paw( double bodyRadius, Bone.BodyVertical vertical, Bone.BodyHorizontal horizontal )
        {
            super( null );
            this.vertical = vertical;
            this.horizontal = horizontal;
            this.bodyRadius = ( int ) bodyRadius;
            pawRadius = ( int ) this.bodyRadius / 5;
            position.x = 0;
            if( vertical == Bone.BodyVertical.TOP )
                position.y = this.bodyRadius - pawRadius;
            else
            {
                position.y = ( this.bodyRadius / 2 ) - pawRadius / 2;
                position.x += ( this.bodyRadius / 4 ) - pawRadius / 2;
            }
        }
        public void draw( Graphics2D g )
        {
            g.setColor( Color.BLUE );
            int bodyRadius_ = ( int ) bodyRadius;
            g.fillOval( 0, 0, pawRadius, pawRadius );
        }
    }

    class TopLeg extends Bone
    {

        public String name() { return "Doggo_TopLeg"; }

        int bodyRadius, horizontalScalar, legRadius;

        TopLeg( double bodyRadius, Bone.BodyHorizontal horizontal )
        {
            super( new Bone[] { new Paw( bodyRadius, Bone.BodyVertical.TOP, horizontal ) } );
            this.horizontal = horizontal;
            this.bodyRadius = ( int ) bodyRadius;
            horizontalScalar = ( 1 + leftRightScalar( this.horizontal ) ) / 2;
            legRadius = ( int ) this.bodyRadius / 5;
            position.x = ( horizontalScalar * this.bodyRadius ) 
                    - ( horizontalScalar * legRadius ) 
                    - ( leftRightScalar( this.horizontal ) * legRadius / 2 );
            position.y = ( legRadius / 2 );
        }

        public void draw( Graphics2D g )
        {
            g.setColor( Color.GREEN );
            g.fillOval( 0, 0, legRadius, bodyRadius );
        }
    }
    
    public class DoggoTextBox extends Transform
    {
        public String text;
        VirtualPetGraphics window;
        int timeSinceClick = 0;
        HappyQuotes quoteGen = new HappyQuotes();
        
        DoggoTextBox( Vector2 pos, VirtualPetGraphics window ) {
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
                if( window.mouseX < x + 100 && window.mouseY < y + 100 && window.mouseX > x && window.mouseX > y )
                {
                    timeSinceClick = 1000;
                    text = quoteGen.quoteGrab();
                    System.out.println( "Grabbed quote: " + text );
                }
            }
            float tempScale = 3;
            if( timeSinceClick-- > 0 )
            {
                // System.out.println( text );
                g.scale( tempScale, tempScale );
                g.drawString( text, 0, 0 );
                g.scale( 1.0f / tempScale, 1.0f / tempScale );
            }
        }
    }

    final static Color COMMUNISM = Color.RED;

    public class Hip extends Bone
    {
        public String name() { return "Doggo_Hip"; }

        int bodyRadius, horizontalScalar, legRadius;

        Hip( double bodyRadius, Bone.BodyHorizontal horizontal )
        {
            super( new Bone[] { new Paw( bodyRadius, Bone.BodyVertical.BOTTOM, horizontal ) } );
            this.horizontal = horizontal;
            this.bodyRadius = ( int ) bodyRadius;
            horizontalScalar = ( 1 + leftRightScalar( this.horizontal ) ) / 2;
            legRadius = ( int ) this.bodyRadius / 2;
            position.x = ( horizontalScalar * this.bodyRadius )//; 
            - ( horizontalScalar * legRadius )//; 
            + ( leftRightScalar( this.horizontal ) * legRadius / 2 );
            position.y = this.bodyRadius - legRadius;//0;//( legRadius / 2 );        
        }    
        
        public void draw( Graphics2D g )
        {
            g.setColor( Color.GRAY );
            g.fillOval( 0, 0, legRadius, legRadius );
        }    
    }    

    public class Ear extends Bone
    {
        int bodyRadius, earLength, earRadius, headRadius, horizontalScalar;
        Ear( double bodyRadius, Bone.BodyHorizontal horizontal )
        {
            super( null );
            this.bodyRadius = ( int ) bodyRadius;
            this.horizontal = horizontal;
            headRadius = this.bodyRadius * 9 / 10;
            earLength = this.bodyRadius * 5 / 10;
            earRadius = this.bodyRadius * 2 / 10;
            horizontalScalar = leftRightScalar( this.horizontal );
            int isRight = ( horizontalScalar + 1 ) / 2;
            position.x = ( isRight * headRadius * 9 / 10 ) - ( headRadius / 4 );// + ( isRight * earRadius / 4 );
            // orientation = 30 * horizontalScalar;
            System.out.println( "EAR: " + position.x );
        }
        public void draw( Graphics2D g )
        {
            g.setColor( Color.GREEN );
            g.fillOval( 0, 0, earLength, earRadius );
        }
        public String name() {
            return "Ear";
        }
    }
    
    public class BigBrain extends Bone
    {
    
        int bodyRadius, headRadius;
        BigBrain( double bodyRadius )
        {
            super( new Bone[] { 
                    new Ear( bodyRadius, Bone.BodyHorizontal.LEFT ), 
                    new Ear( bodyRadius, Bone.BodyHorizontal.RIGHT )
                } );
            this.bodyRadius = ( int ) bodyRadius;
            headRadius = this.bodyRadius * 9 / 10;
            position.x =  this.bodyRadius / 20;
            position.y = -headRadius * 3 / 4;
        }
        public void draw( Graphics2D g )
        {
            g.setColor( Color.BLUE );
            g.fillOval( 0, 0, headRadius, headRadius );
        }
        public String name() {
            return "BigBrain";
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
                new Hip( bodyRadius, Bone.BodyHorizontal.LEFT ), 
                new Hip( bodyRadius, Bone.BodyHorizontal.RIGHT ), 
                new TopLeg( bodyRadius, Bone.BodyHorizontal.LEFT ), 
                new TopLeg( bodyRadius, Bone.BodyHorizontal.RIGHT ), 
                new BigBrain( bodyRadius )
            }
        );
        this.obj.position.x = 200;
        this.obj.position.y = 100;
        this.textBox = new DoggoTextBox( new Vector2( 10, 10 ), window );
        this.obj.addChild(this.textBox);
        TransformStack.pushTransform( this.obj );

    }
    
    public void update() {}
}
