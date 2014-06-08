// Daniel Chen
// May 15 2014 (15/05/2014)
// NOTE: Try with printer before handing in, my printer prints with extra large margins. Might add image loading (logo) 
// function later if I'm bored enough.
//public class Print implements Printable 
//{
//  private ArrayList <String> list;
//  private boolean newLine;
//  private int y;
//  
//  //Use like System.out.print (String)
//  public void print (String text)
//  {
//    if (newLine)
//      list.add (text);
//    else
//      list.add (list.remove (list.size () - 1) + text);
//    newLine = false;
//  }
//  
//  //Use like System.out.println ()
//  public void println ()
//  {
//    list.add ("");
//    newLine = true;
//  }
//  
//  //Use like System.out.println (String)
//  public void println (String text)
//  {
//    print (text);
//    newLine = true;
//  }
//   
//  //Use to start the actual printer printing
//  public boolean startPrinting ()
//  {
//    PrinterJob p = PrinterJob.getPrinterJob ();
//    p.setPrintable (this);
//    try
//    {
//      p.print ();    
//    }
//    catch (PrinterException e)
//    {
//      return false;
//    }
//    return true;
//  }
//  
//  //No toucherino, needed for Printable interface
//  public int print(Graphics g, PageFormat pf, int page) throws PrinterException 
//  {
//    if (page > 0)
//      return NO_SUCH_PAGE;
//    
//    Graphics2D g2d = (Graphics2D)g;
//    g2d.translate(pf.getImageableX(), pf.getImageableY());
//    y = (int) pf.getImageableY();
//    
//    for (String s : list)
//    {
//      y+= g.getFontMetrics ().getHeight ();
//      g2d.drawString(s, (int) pf.getImageableX(), y);
//    }
//    
//    return PAGE_EXISTS;
//  }
//  
//  public Print ()
//  {
//    list = new ArrayList <String> ();
//    newLine = true;
//  }
//}
