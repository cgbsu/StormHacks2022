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
    public float deltaTime = 0f;

    int frameDelay = 0;


    public static Color COMMUNISM = Color.RED;

    Color backroundColor = COMMUNISM;

    int x = 0;

    int mouseX, mouseY;
    boolean clicked = false;

    public VirtualPetGraphics() {
        super();
        this.doggo = new Doggo( null, this );
        setBackground( backroundColor );
        addMouseListener( this );
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
        int deFloatDeltaTime = (int)(deltaTime * 1000);

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

    void calculateDeltaTime()
    {
        long currentMillis = cluck.millis();

        long deltaTimeLong = currentMillis - lastFrameMillis; 

        deltaTime = (float)deltaTimeLong * 0.001f;

        lastFrameMillis = currentMillis;
    }

    @Override
    public void mouseClicked( MouseEvent e )
    {
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.print( mouseX );System.out.print( ", " );System.out.println( mouseY );
        clicked = true;
        repaint();
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
        // clicked = false;
    }
 }
