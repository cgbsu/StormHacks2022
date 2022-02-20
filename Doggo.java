import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.lang.Math;

public class Doggo
{
    public Transform obj;
    public DoggoTextBox textBox;
    public HappyQuotes quoteGen;

    public VirtualPetGraphics vpg;

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
        int     timeSinceClick, 
                textScale, 
                messageDisplayTimeInMilliseconds, 
                messageClickAreaRadius;
        HappyQuotes quoteGen = new HappyQuotes();
        
        public static final int DefaultTextScaleFinal = 2;
        public static final int DefaultMessageDisplayTimeInMilliseciondsFinal = 3000;

        DoggoTextBox( 
                int messageClickAreaRadius, 
                Vector2 position, 
                VirtualPetGraphics window 
            )
        {
            this( 
                    DefaultTextScaleFinal, 
                    DefaultMessageDisplayTimeInMilliseciondsFinal, 
                    messageClickAreaRadius, 
                    position, 
                    window 
                );
        }

        DoggoTextBox( 
                int textScale, 
                int messageDisplayTimeInMilliseconds, 
                int messageClickAreaRadius, 
                Vector2 position, 
                VirtualPetGraphics window 
            ) 
        {
            super();
            this.messageDisplayTimeInMilliseconds = messageDisplayTimeInMilliseconds;
            this.textScale = textScale;
            this.messageClickAreaRadius = messageClickAreaRadius;
            text = "If your seeing this... its probably a bug.";
            this.window = window;
            this.position = position;
        }
        public String name() { return "Doggo_Textbox"; }

        public void draw( Graphics2D g )
        {
            if( window.clicked == true )
            {
                Vector2 globalCoordinates = TransformStack.getPreviousGraphicsGlobalTranslation();
                System.out.println( "Testing Happy Message Click At: " + globalCoordinates );

                final double DistanceFromCursor = Vector2.add( 
                            window.mouseClickCoordinates, 
                            globalCoordinates.negate() 
                        ).magnitude();
                if( DistanceFromCursor < messageClickAreaRadius )
                {
                    timeSinceClick = messageDisplayTimeInMilliseconds;
                    text = quoteGen.quoteGrab();
                    System.out.println( "Grabbed quote: " + text );
                }
            }
            if( timeSinceClick-- > 0 )
            {
                g.scale( textScale, textScale );
                g.drawString( text, 0, 0 );
                g.scale( 1.0f / textScale, 1.0f / textScale );
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
            position.x = ( horizontalScalar * this.bodyRadius ) 
            - ( horizontalScalar * legRadius ) 
            + ( leftRightScalar( this.horizontal ) * legRadius / 2 );
            position.y = this.bodyRadius - legRadius;        
        }    
        
        public void draw( Graphics2D g )
        {
            g.setColor( Color.GRAY );
            g.fillOval( 0, 0, legRadius, legRadius );
        }    
    }

    public class Ear extends Bone
    {
        int bodyRadius, earLength, earRadius, headRadius, horizontalScalar, isRight;
        Ear( double bodyRadius, Bone.BodyHorizontal horizontal )
        {
            super( null );
            this.bodyRadius = ( int ) bodyRadius;
            this.horizontal = horizontal;
            headRadius = this.bodyRadius * 9 / 10;
            earLength = this.bodyRadius * 5 / 10;
            earRadius = this.bodyRadius * 2 / 10;
            horizontalScalar = leftRightScalar( this.horizontal );
            isRight = ( horizontalScalar + 1 ) / 2;
            position.x = ( horizontalScalar * headRadius / 3 ) + ( earRadius );
        }
        public void draw( Graphics2D g )
        {
            g.setColor( Color.GREEN );
            g.fillOval( 0, 0, earLength, earRadius );
        }
        public String name() {
            return "Ear";
        }
        public Vector2 getAlignedCenter() {
            return new Vector2( earLength / 2, earRadius / 2 );
        }
    }
    
    Vector2 defaultPos;
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
            defaultPos = position;
        }

        float headBob = 0f;
        public void draw( Graphics2D g )
        {
            float bobSpeed = 6f;

            headBob += vpg.deltaTime;
            int bobAmmount = (int)(Math.sin(headBob * bobSpeed) * 20);
            int nodAmmount = (int)(Math.cos(headBob * bobSpeed) * 20);
            position = new Vector2(defaultPos.x + nodAmmount, defaultPos.y + bobAmmount);
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
        this.textBox = new DoggoTextBox( 90, new Vector2( 10, 10 ), window );
        this.obj.addChild(this.textBox);
        TransformStack.pushTransform( this.obj );

        vpg = window;
    }
    
    public void update() {}
}
