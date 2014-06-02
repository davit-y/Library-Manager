import javax.swing.*;
import java.awt.*;
//import java.awt.AlphaComposite;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

/**
 * This class creates the splash screen.
 * 
 * @author David Yeghshatyan
 * @version 1.0, May 27, 2014      
 */
public class SplashScreen extends JWindow
{
/**
   * The SplashScreen constructor that creates a borderless screen and displays the Plat Engine loading screen.
   * <p>
   * @param icon creates a new Icon.
   * @param label creates a JLabel that is the img icon.
   * @param f creates a reference to the JFrame class.
   */
  public SplashScreen ()
  {
    
    Icon icon = new ImageIcon(".//Graphics/Splash-Screen.gif");
    JLabel label = new JLabel(icon);
    JFrame f = new JFrame("Animation");
    f.getContentPane().add(label);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setUndecorated(true);
    f.pack();
    f.setLocationRelativeTo(null);
    
    try
    {
      f.setVisible(true);
      f.setLocationRelativeTo (null);
      
      f.show();
      Thread.sleep(6100);
      f.dispose();      
    }
    catch (Exception e)
    {
    }
  }
}