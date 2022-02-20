import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StormHacks_Main_Thingy
{
   public static void main( String[] args )
   {
       SwingUtilities.invokeLater(() -> {
           var frame = new JFrame("A true work of beauty that not even the gods could replicate.");
           Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
           frame.setSize( (int)screenSize.getWidth() / 2 , (int)screenSize.getHeight() / 2 );
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.getContentPane().add(new VirtualPetGraphics(), BorderLayout.CENTER);
           frame.setVisible(true);
       });
   }
   
   
}