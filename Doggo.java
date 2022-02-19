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
            g.fillOval( position.x, position.y, 6, 6 );
        }
    }

    class TopLeg extends Bone
    {

        public String name() { return "Doggo_TopLeg"; }

        TopLeg( Bone.BodyHorizontal side )
        {
            super( new Bone[] { new Paw( Bone.BodyVertical.TOP, side ) } );
            horizontal = side;
        }

        public void draw( Graphics2D g ) {
            g.setColor( Color.GREEN );
            g.fillOval( position.x, position.y, 10, 20 );
        }
    }

    class DoggoTextBox extends Transform
    {
        public String name() { return "Doggo_Textbox"; }
        public String text;

        DoggoTextBox ( Vector2 pos ) {
            super();
            this.position = pos;
        }

        public void draw( Graphics2D g )
        {
            Vector2 finalPos = this.position;

            if (this.parrent != null)
                finalPos = Vector2.add(finalPos, this.parrent.position);

            g.drawString(text, finalPos.x, finalPos.y);
        }
    }

    public Doggo( Vector2 pos )
    {
        /*this.obj = new Bone(
            new Bone[] {
                TopLeg( left ), 
                TopLeg( right), 
                Head, 
                BottomLeg( left ), 
                BottomLeg( right ) 
            }
        );*/
        this.obj.position = pos;
        this.textBox = new DoggoTextBox( new Vector2(10, 10) );
        this.obj.addChild(this.textBox);
    }
    
    public void update() {}
}
