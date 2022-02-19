import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StormHacks_Main_Thingy
{
   public static void main( String[] args )
   {
       SwingUtilities.invokeLater(() -> {
           var frame = new JFrame("A simple graphics program");
           frame.setSize(400, 300);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.getContentPane().add(new VirtualPetGraphics(new Doggo(null)), BorderLayout.CENTER);
           frame.setVisible(true);
       });
   }
   
   
}