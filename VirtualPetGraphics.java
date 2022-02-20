import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;   
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.time.*;
// import java.awt.MouseListener;

class VirtualPetGraphics extends JPanel implements MouseListener
{
    Doggo doggo;

    Clock cluck = Clock.systemDefaultZone();
    long lastFrameMillis = cluck.millis();
<<<<<<< HEAD
    long lastClickMillis = cluck.millis();
    public float deltaTime = 0f;
=======
>>>>>>> e0883bbfc41705e186aceb9a344b5da6b3e720ee

    int frameDelay = 0;


    public static Color COMMUNISM = Color.WHITE;

    Color backroundColor = COMMUNISM;

    int x = 0;

    Vector2 mouseClickCoordinates;
    boolean clicked = false;

    public VirtualPetGraphics() {
        super();
        this.doggo = new Doggo( null, this );
        setBackground( backroundColor );
        addMouseListener( this );
        mouseClickCoordinates = new Vector2( 0, 0 );
    }

    public void paintComponent( Graphics g )
    {
        calculateDeltaTime();

        super.paintComponent( g );
        g.setColor( backroundColor );
        g.fillRect( 0, 0, getWidth(), getHeight() );
        //doggo.update();
        TransformStack.render( ( Graphics2D ) g );
        repaint();
        clicked = false;
        
        /*For refrence
        g.setColor( Color.WHITE );
        ( ( Graphics2D ) g ).translate( 50, 50 );
        ( ( Graphics2D ) g ).rotate( ++x % 360, 50, 50 );
        g.fillRect( 0, 0, 100, 100 );
        ( ( Graphics2D ) g ).rotate( -x % 360, 50, 50 );
        ( ( Graphics2D ) g ).translate( -50, -50 );
        g.fillRect( 200, 200, 100, 100 );*/

        int targetDeltaMilli = 1000 / 60;
        int deFloatDeltaTime = (int)(ToolSet.deltaTime * 1000);

        if (deFloatDeltaTime > targetDeltaMilli && frameDelay > 0)
            frameDelay--;
        else if (deFloatDeltaTime < targetDeltaMilli)
            frameDelay++;

        try{
            Thread.sleep(frameDelay);
        }
        catch (InterruptedException ex)
        {
            System.out.println("damm");
        }
    }

    double calculateDeltaTime()
    {
        long currentMillis = cluck.millis();

        long deltaTimeLong = currentMillis - lastFrameMillis; 

        ToolSet.deltaTime = (float) deltaTimeLong * 0.001f;

        lastFrameMillis = currentMillis;

        return ToolSet.deltaTime;
    }

    protected void collectMouseInfo( MouseEvent event, String source )
    {
        long currentTime = cluck.millis();
        if( ( currentTime - lastClickMillis ) >= 1000 )
        {
            lastClickMillis = currentTime;
            mouseClickCoordinates.x = event.getX();
            mouseClickCoordinates.y = event.getY();
            System.out.println( "Mouse " + source + " Detected!: " + mouseClickCoordinates.toString() );
            clicked = true;
            repaint();
        }
    }

    @Override
    public void mouseClicked( MouseEvent e ) {
        collectMouseInfo( e, "Click" );
    }
    @Override
    public void mouseExited( MouseEvent e ) {
        clicked = false;
    }
    @Override
    public void mouseEntered( MouseEvent e ) {
        clicked = false;
    }
    @Override
    public void mouseReleased( MouseEvent e ) {
        clicked = false;
    }
    @Override
    public void mousePressed( MouseEvent e ) {
        collectMouseInfo( e, "Pressed" );
    }
 }
