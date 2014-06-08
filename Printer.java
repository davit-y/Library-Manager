import java.awt.print.PrinterJob;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PageFormat;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;
import javax.imageio.ImageIO;

// Daniel Chen
// May 15 2014 (15/05/2014)
// NOTE: Try with printer before handing in, my printer prints with extra large margins. Might add image loading (logo) 
// function later if I'm bored enough.
/**
 *
 *
 */
// June 2 - Removed newLine and print () and RENAMED startPrinting (). All data are expected to be final (cannot modify a line after it has been queued. 
// Proccess Strings BEFORE passing in. Added new format which allows printing to different alliment and image printing.
// WARNING: This class will not check for overlaps, make sure you predict the location of everything if you dont want overlapping.

public class Printer implements Printable 
{
  private ArrayList <String> left, center, right;
  private ArrayList <BufferedImage> images;
  private ArrayList <Point> imageLocations;
  private ArrayList <Boolean> needsResizing;
  private ArrayList <int []> imageSizePercent;
  private int y;
  private final int MARGINS = 72; // 72 is the number for 1 inch margins, change as you see fit.
  private final Font FONT = new Font ("Serif", Font.PLAIN, 12);
  
  //Use like System.out.println ()
  public void println ()
  {
    left.add ("");
    center.add ("");
    right.add ("");
  }
  
  //Use like System.out.println (String)
  public void println (String text)
  {
    println (text, "", "");
  }
  
  //Use like System.out.println (String)
  public void println (String leftText, String centerText, String rightText)
  {
    left.add (leftText);
    center.add (centerText);
    right.add (rightText);
  }
  
  public void printImage (BufferedImage image, Point location)
  {
    images.add (image);
    imageLocations.add (location);
    imageSizePercent.add (null);
    needsResizing.add (new Boolean (false));
  }
  
  public void printImage (BufferedImage image, Point location, int percentX)
  {
    int [] temp = {percentX, -1};
    images.add (image);
    imageLocations.add (location);
    imageSizePercent.add (temp);
    needsResizing.add (new Boolean (true));
  }
  
  public void printImage (BufferedImage image, Point location, int percentX, int percentY)
  {
    int [] temp = {percentX, percentY};
    images.add (image);
    imageLocations.add (location);
    imageSizePercent.add (temp);
    needsResizing.add (new Boolean (true));
  }
  
  //Use to start the actual printer printing
  public boolean printUsingDialog ()
  {
    PrinterJob p = PrinterJob.getPrinterJob ();
    if (p.printDialog ())
    {
      p.setPrintable (this);
      try
      {
        p.print ();    
      }
      catch (PrinterException e)
      {
        return false;
      }
      return true;
    }
    else
      return false;
  }
  
  //No toucherino, needed for Printable interface
  public int print(Graphics g, PageFormat pf, int page) throws PrinterException 
  {
    if (page > 0)
      return NO_SUCH_PAGE;
    
    g.setFont (FONT);
    Graphics2D g2d = (Graphics2D)g;
    FontMetrics fm = g.getFontMetrics ();
    y = MARGINS + fm.getHeight ();
    double pageWidth = 2 * pf.getImageableX () + pf.getImageableWidth ();
    double pageHeight = 2 * pf.getImageableY () + pf.getImageableHeight ();
    
    for (int i = 0; i < images.size (); i ++)
    {
      if (!((boolean) needsResizing.get (i)))
        g2d.drawImage (images.get (i), (int) imageLocations.get (i).getX (), (int) imageLocations.get (i).getY (), null);
      else
      {
        int [] temp = imageSizePercent.get (i);
        int width = (int) (temp [0] / 100.0 * pageWidth);
        int height;
        if (temp [1] == -1)
          height = (int) (temp [0] / 100.0 * pageWidth);
        else
          height = (int) (temp [1] / 100.0 * pageHeight);
        g2d.drawImage (images.get (i), (int) imageLocations.get (i).getX (), (int) imageLocations.get (i).getY (), width, height, null);
      }
    }
    
    for (int i = 0; i < center.size (); i ++)
    {
      g2d.drawString(left.get (i), MARGINS , y);
      g2d.drawString(center.get (i), (int) (pageWidth/2 - fm.stringWidth (center.get (i))/2.0), y); //Roughly center, will lose precision due to double -> int
      g2d.drawString(right.get (i), (int) (pageWidth - fm.stringWidth (right.get (i))) - MARGINS, y);
      y+= g.getFontMetrics ().getHeight ();
      if (y >= pageHeight - MARGINS)
        break;
    }
    return PAGE_EXISTS;
  }
  
  public Printer ()
  {
    left = new ArrayList <String> ();
    center = new ArrayList <String> ();
    right = new ArrayList <String> ();
    images = new ArrayList <BufferedImage> ();
    imageLocations = new ArrayList <Point> ();
    needsResizing = new ArrayList <Boolean> ();
    imageSizePercent = new ArrayList <int []> ();
  }
  
  public static void main (String [] args)
  {
    Printer p = new Printer ();
    p.println ("hi");
    p.println ("", "", "right");
    p.println ("", "center", "");
    p.println ("left", "", "");
    p.println ("all", "all", "all");
    try
    {
      p.printImage (ImageIO.read (p.getClass ().getResource ("/resources/End.png")), new Point (0, 0));
      p.printImage (ImageIO.read (p.getClass ().getResource ("/resources/All.png")), new Point (0, 100), 20);
      p.printImage (ImageIO.read (p.getClass ().getResource ("/resources/T.png")), new Point (250, 0), 10, 10);
    }
    catch (IOException e)
    {
      e.printStackTrace ();
    }
    p.printUsingDialog ();
  }
}