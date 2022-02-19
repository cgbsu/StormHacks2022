import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Doggo
{
    public Transform obj;
    public DoggoTextBox textBox;

    class Paw extends Bone
    {
        public String name() { return "Doggo_Paw"; }
        Paw( Bone.BodyVertical vertical, Bone.BodyHorizontal horizontal ) {
            super( null );
            this.vertical = vertical;
            this.horizontal = horizontal;
        }
        public void draw( Graphics2D g ) {
            g.setColor( Color.BLUE );
            g.fillOval( 50 + ( 25 * leftRightScalar( horizontal ) ), 0, 20, 20 );
        }
    }

    class TopLeg extends Bone
    {

        public String name() { return "Doggo_TopLeg"; }

        TopLeg( Bone.BodyHorizontal horizontal )
        {
            super( new Bone[] { new Paw( Bone.BodyVertical.TOP, horizontal ) } );
            this.horizontal = horizontal;
        }

        public void draw( Graphics2D g ) {
            g.setColor( Color.GREEN );
            g.fillOval( 50 + ( 25 * leftRightScalar( horizontal ) ), 0, 20, 50 );
        }
    }

    class DoggoTextBox extends Transform
    {
        public String name() { return "Doggo_Textbox"; }
        public String text;
        VirtualPetGraphics window;
        int timeSinceClick = 0;

        DoggoTextBox ( Vector2 pos, VirtualPetGraphics window ) {
            super();
            text = "Test text";
            this.window = window;
            this.position = pos;
        }

        public void draw( Graphics2D g )
        {
            if( window.clicked == true )
            {
                double x = g.getTransform().getTranslateX();
                double y = g.getTransform().getTranslateY();
                System.out.print("X:");System.out.print(x);System.out.print("Y:");System.out.println(y);
                if( window.mouseX < x + 100 && window.mouseY < y + 100 && window.mouseX > x && window.mouseX > y )
                    timeSinceClick = 1000;
            }
            float tempScale = 10;
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
        ThicccBod( Bone[] children ) {
            super( children );
        }
        public void draw( Graphics2D g ) {
            g.setColor( Color.ORANGE );
            g.fillOval( 0, 0, 100, 100 );
        }
        public String name() {
            return "Thiccccc";
        }
    }

    public Doggo( Vector2 pos, VirtualPetGraphics window )
    {
        TransformStack.initalize();
        this.obj = new ThicccBod(
            new Bone[] {
                new TopLeg( Bone.BodyHorizontal.LEFT ), 
                new TopLeg( Bone.BodyHorizontal.RIGHT ) 
            }
        );
        this.obj.position.x = 100;
        this.obj.position.y = 100;
        this.textBox = new DoggoTextBox( new Vector2( 10, 10 ), window );
        this.obj.addChild(this.textBox);
    }
    
    public void update() {}
}
