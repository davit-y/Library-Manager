import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The purpose of this class to run the program and create JFrame.
 * <p>
 * @param title The string that holds the title name.
 * @param author The string that holds the author name.
 * @param location The string that holds the location.
 * @param genre The string that holds the genre number.
 * @param a An instance of the AdressBook class.
 * @param sortGenre, sortLocation,sortEmail, sortPhone The radio buttons that choose which field to sort.
 * @param sortBox The dialog box that allowes the user to choose whih field to sort by.
 * @param currentView Keeps track of the current view of the program (text or chart).
 * <p>
 * @author David Yeghshatan 
 * @version Version 4 April 21
 */

public class DataBaseApp extends JFrame implements ActionListener
{ 
  /**
   * The string that holds the title name.
   */ 
  private String title;
  /**
   * The string that holds the author name.
   */ 
  private String author;
    /**
   * The string that holds the genre number.
   */ 
  private String genre;
  /**
   * The string that holds the location.
   */ 
  private String location;
  /**
   * The string that holds the borrow date.
   */ 
  private String borrowdate;
  /**
   * The string that holds the return date.
   */ 
  private String returnDate;
  /**
   * This is the radio buttons to choose which field to sort.
   */ 
  JRadioButton sortTitle, sortAuthor, sortGenre, sortLocation, sortBorrow, sortReturn;
  /**
   * This is the dialogue box to choose which field to sort.
   */ 
  JDialog sortBox;
  /**
   * This is the radio buttons to choose which field to search.
   */ 
  JRadioButton searchLocation, searchGenre, searchTitle, searchAuthor, searchBorrow, searchReturn;
  /**
   * This is the dialogue box to choose which field to search.
   */ 
  JDialog searchBox;
  /**
   * This is the radio buttons to choose to partial match or search the whole field.
   */
  JRadioButton partialSearch, wholeSearch;
  /**
   * The text field that holds the desired search text.
   */ 
  JTextField searchField = new JTextField ();
  /**
   * a Instance of the AdressBook class.
   */ 
  RecordManager r = new RecordManager ();
  /**
   * currentView Keeps track of the current view of the program (text or chart).
   */ 
  String currentView = "Text";
  
  /**
   *The constructor creates a new instance of AdressBook and sets up the window.
   * @param quitItem creates a new JMenuItem with the title Quit.
   * @param helpItem creates a new JMenuItem with the title Help.
   * @param aboutItem creates a new JMenuItem with the title About.
   * @param fileMenu creates a JMenu called File.
   * @param helpMenu creates a JMenu called Help.
   * @param myMenus creates a new JMenuBar for the window. 
   * @param newItem creates the JMenuItem "New".
   * @param openItem creates the JMenuItem "Open".
   * @param saveItem creates the JMenuItem "Save".
   * @param saveAsItem creates the JMenuItem "Save As".
   * @param ActionListener listens to user input
   * @param DISPOSE_ON_CLOSE clears the RAM and closes the program when it is complete.
   */
  public DataBaseApp ()
  { 
    SplashScreen ss = new SplashScreen ();
    
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    JMenu viewMenu = new JMenu ("View");
    JMenu toolsMenu = new JMenu ("Tools");
    
    JMenuItem quitItem = new JMenuItem ("Quit");
    JMenuItem helpItem = new JMenuItem ("Help");
    JMenuItem aboutItem = new JMenuItem ("About");
    JMenuItem newItem = new JMenuItem ("New");
    JMenuItem saveItem = new JMenuItem ("Save");
    JMenuItem saveAsItem = new JMenuItem ("Save As");
    JMenuItem openItem = new JMenuItem ("Open");
    JMenuItem signOutItem = new JMenuItem ("Sign Out");
    JMenuItem chartItem = new JMenuItem("Chart");
    JMenuItem browseItem = new JMenuItem("Browse");
    JMenuItem sortItem = new JMenuItem("Sort");
    JMenuItem searchItem = new JMenuItem("Search");
    
    fileMenu.add (newItem);
    fileMenu.add (openItem);
    fileMenu.add (saveItem);
    fileMenu.add (saveAsItem);
    fileMenu.add (signOutItem);
    fileMenu.add (quitItem);
    helpMenu.add (helpItem);
    helpMenu.add (aboutItem);
    viewMenu.add (chartItem);
    viewMenu.add (sortItem);
    toolsMenu.add (sortItem);
    toolsMenu.add (searchItem);
    
    JMenuBar myMenus = new JMenuBar ();
    
    myMenus.add (fileMenu);
    myMenus.add (toolsMenu);
    myMenus.add (viewMenu);
    myMenus.add (helpMenu);
    
    setJMenuBar (myMenus);
    
    helpItem.addActionListener (this);
    quitItem.addActionListener (this);
    aboutItem.addActionListener (this);
    newItem.addActionListener (this);
    saveItem.addActionListener (this);
    saveAsItem.addActionListener (this);
    openItem.addActionListener (this);
    chartItem.addActionListener (this);
    sortItem.addActionListener (this);
    sortItem.addActionListener (this);
    searchItem.addActionListener (this);
    
    add(r);
    
    setSize (400,550);
    setResizable (false);
    setVisible (true);
    setLocationRelativeTo(null);
    
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
  }
  
  /**
   * This method opens a dialogue box to choose which field to sort.
   * 
   * @param group This holds the group of buttons.
   * @param okButton This button allows the suer to submit his choice.
   * @param closeButton This button allows the user to close the dialogue box.
   */ 
  public void chooseSort()
  {
    sortBox = new JDialog (this,"Field");
    ButtonGroup group = new ButtonGroup ();
    JButton okButton = new JButton ("Ok");
    JButton closeButton = new JButton("Close");  
    
    sortBox.setSize (80,280);
    sortTitle = new JRadioButton ("Title");
    sortAuthor = new JRadioButton ("Author");
    sortGenre = new JRadioButton ("Genre");
    sortLocation = new JRadioButton ("Location");
    
    group.add(sortTitle);
    group.add(sortAuthor);
    group.add(sortGenre);
    group.add(sortLocation);
    group.add(sortBorrow);
    group.add(sortReturn);

    sortBox.setLayout (new FlowLayout(FlowLayout.LEADING));
    sortBox.setVisible (true);
    sortBox.add(sortGenre);
    sortBox.add(sortLocation);
    sortBox.add(sortTitle);
    sortBox.add(sortAuthor);
    sortBox.add(closeButton);
    sortBox.add(okButton);
    
    okButton.addActionListener (new ActionListener()
                                  {
      public void actionPerformed(ActionEvent e){
        if (sortGenre.isSelected ())
          r.sortWhichField = 1;
        
        if (sortLocation.isSelected ())
          r.sortWhichField = 2;
        
        if (sortTitle.isSelected ())
          r.sortWhichField = 3;
        
        if (sortAuthor.isSelected ())
          r.sortWhichField = 4;
        
        if (sortBorrow.isSelected ())
          r.sortWhichField = 5;
        
        if (sortReturn.isSelected ())
          r.sortWhichField = 6;
        
        sortBox.dispose();    
        r.sorter();
        invalidate();
        validate();
        repaint();
      }
    });
    
    closeButton.addActionListener(new ActionListener() 
                                    {
      public void actionPerformed(ActionEvent e) {
        sortBox.dispose();
      }
    });
  }  
  
  /**
   * This method opens a dialogue box to choose what to search.
   * 
   * @param group This holds the group of buttons.
   * @param okButton This button allows the suer to submit his choice.
   * @param closeButton This button allows the user to close the dialogue box.
   * @param group2 This holds the second group of buttons.
   */ 
  public void chooseSearch()
  {
    searchBox = new JDialog (this,"Field");
    ButtonGroup group = new ButtonGroup ();
    ButtonGroup group2 = new ButtonGroup ();
    JButton okButton = new JButton ("Ok");
    JButton closeButton = new JButton("Close");  
    JLabel searchLabel = new JLabel ("Input text below:");
    searchLabel.setFont (new Font ("Serif", Font.PLAIN, 16)); 
    
    searchBox.setSize (220,350);
    searchTitle = new JRadioButton ("Title");
    searchAuthor = new JRadioButton ("Author");
    searchLocation = new JRadioButton ("Location");
    searchGenre = new JRadioButton ("Genre");
    partialSearch = new JRadioButton ("Partial Search");
    wholeSearch = new JRadioButton ("Match Whole Field");
    
    group.add(searchTitle);
    group.add(searchAuthor);
    group.add(searchLocation);
    group.add(searchGenre);
    group.add(searchBorrow);
    group.add(searchReturn);
    group2.add(partialSearch);
    group2.add(wholeSearch);
    
    searchBox.setLayout (new BoxLayout(searchBox.getContentPane(),BoxLayout.Y_AXIS));
    searchBox.setVisible (true);
    searchBox.add(searchTitle);
    searchBox.add(searchAuthor);
    searchBox.add(searchLocation);
    searchBox.add(searchGenre);
    searchBox.add(searchBorrow);
    searchBox.add(searchReturn);
    searchBox.add(Box.createRigidArea(new Dimension(0,10)));
    searchBox.add(partialSearch);
    searchBox.add(wholeSearch);
    searchBox.add(Box.createRigidArea(new Dimension(0,10))); 
    searchBox.add(searchLabel);
    searchBox.add(searchField);

    searchBox.add(closeButton);
    searchBox.add(okButton);

    okButton.addActionListener (new ActionListener()
                                  {
      public void actionPerformed(ActionEvent e){
        if (searchTitle.isSelected ())
          r.searchWhichField = 1;
        
        if (searchAuthor.isSelected ())
          r.searchWhichField = 2;
        
        if (searchLocation.isSelected ())
          r.searchWhichField = 3;
        
        if (searchGenre.isSelected ())
          r.searchWhichField = 4;
        
        if (searchBorrow.isSelected ())
          r.searchWhichField = 5;
        
        if (searchReturn.isSelected ())
          r.searchWhichField = 6;
        
        if (partialSearch.isSelected ())
          r.partialOrWhole = 1;
        
        if (wholeSearch.isSelected ())
          r.partialOrWhole = 2;
        
        r.searchText = searchField.getText ();
        searchBox.dispose();    
        r.tableView();
        invalidate();
        validate();
        repaint();
      }
    });
    
    closeButton.addActionListener(new ActionListener() 
                                    {
      public void actionPerformed(ActionEvent e) {
        searchBox.dispose();
      }
    });
  }
  
  /**
   * This method assigns actions to buttons based on user input.
   * 
   * @param actionPerformed is the action performed by the user
   */ 
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals("New"))
    {
      r.saveChecker();
      r.newDatabase();
      r.fileName = null;
      r.fieldView();
      r.updateDisplay();
    }
    
    else if (ae.getActionCommand ().equals("About"))
      JOptionPane.showMessageDialog(this,"Program by JDL");
    
    else if (ae.getActionCommand ().equals("Help"))
      JOptionPane.showMessageDialog(this,"This is Library Manager. It will keep track of all of your books. Make sure that you don't leave all the records blank.");
    
    else if (ae.getActionCommand ().equals("Open"))
    {
      r.saveChecker();
      r.openFile ();
      r.fieldView();
      r.updateDisplay();
    }    
    else if (ae.getActionCommand ().equals("Save"))
      r.save (r.fileName);
    
    else if (ae.getActionCommand ().equals("Save As"))
      r.saveAs ();
    
    else if (ae.getActionCommand().equals("Chart")) 
    {
      currentView = "Chart";
      r.sortWhichField = 0;
      if (r.recSaved)
        r.tableView();
    }
    else if (ae.getActionCommand().equals("Browse")) 
    {
      currentView = "Text";
      r.getChartData();
      r.fieldView();
      r.updateDisplay();
    }
    else if (ae.getActionCommand().equals("Sort")) 
    {
      if (r.recSaved)
      {
        if (currentView.equals("Chart"))
          r.getChartData();
        chooseSort ();
      }
    }
    else if (ae.getActionCommand().equals("Search")) 
    {
      if (r.recSaved)
      {
        if (currentView.equals("Chart"))
          r.getChartData();
        chooseSearch ();
      }
    }
    else if (ae.getActionCommand ().equals ("Quit"))
    {
      r.saveChecker();
      System.exit (0);
    }
    this.invalidate();
    this.validate();
    this.repaint();
  }  
  /**
   * This is the main method of the program.
   * 
   * @param args - Args is a parameter pass for the main method
   */
  public static void main (String[] args)
  {
    new DataBaseApp ();
  }
}
