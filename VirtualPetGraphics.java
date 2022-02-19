import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;   
import javax.swing.JPanel;
import java.awt.Graphics2D;

class VirtualPetGraphics extends JPanel
{
    Doggo doggo;

    int x = 0;

    public static Color COMMUNISM = Color.RED;

    Color backroundColor = COMMUNISM;

    public VirtualPetGraphics( Doggo doggo ) {
        super();
        this.doggo = doggo;
        setBackground( backroundColor );
    }
    
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        g.setColor( backroundColor );
        g.fillRect( 0, 0, getWidth(), getHeight() );
        doggo.draw( g );
        g.setColor( Color.WHITE );
        ( (Graphics2D) g ).rotate( ++x % 360, 100, 100 );
        g.fillRect( 0, 0, 100, 100 );
        repaint();
    }
    
}
