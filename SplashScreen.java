import javax.swing.*;
import java.awt.*;
import java.awt.AlphaComposite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This program creates a splashscreen to be shown before the program starts.. 
 * 
 * @author Wendy Fu
 * @version 2.0, May 23, 2014      
 */
public class SplashScreen extends JWindow
{
  /**
   * @param label creates a new JLabel.
   */
  static JLabel label;
/**
   * The SplashScreen constructor that creates a borderless screen and displays the Plat Engine loading screen.
   * <p>
   * @param icon creates a new Icon.
   * @param label creates a JLabel that is the img icon.
   * @param f creates a reference to the JFrame class.
   */
  public SplashScreen ()
  {
    
    Icon icon = new ImageIcon("Splash-Screen.gif");
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
      Thread.sleep(7000);
      f.dispose();      
    }
    catch (Exception e)
    {
    }
  }
}