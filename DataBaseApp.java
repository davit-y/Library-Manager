import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 * The purpose of this class to run the program and create JFrame.
 * <p>
 * @param title The string that holds the title name.
 * @param author The string that holds the author name.
 * @param location The string that holds the location.
 * @param genre The string that holds the genre number.
 * @param a An instance of the AdressBook class.
 * @param sortGenre, sortLocation,sortEmail, sortPhone The radio buttons that choose which field to sort.
 * @param sortDialogue The dialog box that allowes the user to choose whih field to sort by.
 * @param currentView Keeps track of the current view of the program (text or chart).
 * <p>
 * @author David Yeghshatan 
 * @version Version 4 April 21
 */

public class DataBaseApp extends JFrame implements ActionListener
{ 
  /**
   * d (JDialog) points to the JDialog class
   */
  JDialog d;
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
   *  newItem creates the JMenuItem "New".
   */
  JMenuItem newItem;
  /**
   *  openItem creates the JMenuItem "Open".
   */
  JMenuItem openItem;
  /**   
   *  saveItem creates the JMenuItem "Save".
   */ 
  JMenuItem saveItem;
  /**   
   *  saveAsItem creates the JMenuItem "Save As".
   */ 
  JMenuItem saveAsItem;
  /**   
   *  signOutItem creates the JMenuItem "Sign Out".
   */ 
  JMenuItem signOutItem;
  /**  
   *  passItem creates the JMenuItem "Change Password".
   */ 
  JMenuItem passItem;
  /** 
   *  chartItem creates the JMenuItem "Chart".
   */ 
  JMenuItem chartItem;
  /**
   *  browseItem creates the JMenuItem "Browse".
   */ 
  JMenuItem browseItem;
  /**
   *  graphItem creates the JMenuItem "Graph".
   */ 
  JMenuItem graphItem;
  /**   
   *  sortItem creates the JMenuItem "Sort".
   */ 
  JMenuItem sortItem;
  /**   
   *  searchItem creates the JMenuItem "Search".
   */
  JMenuItem searchItem;
  /**
   * This is the radio buttons to choose which field to sort.
   */ 
  JRadioButton sortTitle, sortAuthor, sortGenre, sortLocation, sortBorrow, sortReturn;
  /**
   * This is the dialogue box to choose which field to sort.
   */ 
  JDialog sortDialogue;
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
   * a Instance of the RecordManager class.
   */ 
  RecordManager r = new RecordManager ();
  /**
   * currentView Keeps track of the current view of the program (text or chart).
   */ 
  String currentView = "Text";
  /**
   * a Instance of the BarGraph class.
   */ 
  BarGraph b;
  
  /**
   *The constructor creates a new instance of AdressBook and sets up the window.
   * @param quitItem creates a new JMenuItem with the title Quit.
   * @param helpItem creates a new JMenuItem with the title Help.
   * @param aboutItem creates a new JMenuItem with the title About.
   * @param fileMenu creates a JMenu called File.
   * @param helpMenu creates a JMenu called Help.
   * @param viewMenu creates a JMenu called View.
   * @param toolsMenu creates a JMenu called Tools.
   * @param myMenus creates a new JMenuBar for the window. 
   * @param newItem creates the JMenuItem "New".
   * @param openItem creates the JMenuItem "Open".
   * @param saveItem creates the JMenuItem "Save".
   * @param saveAsItem creates the JMenuItem "Save As".
   * @param signOutItem creates the JMenuItem "Sign Out".
   * @param passItem creates the JMenuItem "Change Password".
   * @param chartItem creates the JMenuItem "Chart".
   * @param browseItem creates the JMenuItem "Browse".
   * @param graphItem creates the JMenuItem "Graph".
   * @param sortItem creates the JMenuItem "Sort".
   * @param searchItem creates the JMenuItem "Search".
   * @param ActionListener listens to user input
   * @param DISPOSE_ON_CLOSE clears the RAM and closes the program when it is complete.
   */
  public DataBaseApp ()
  { 
    //SplashScreen ss = new SplashScreen ();
    
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    JMenu viewMenu = new JMenu ("View");
    JMenu toolsMenu = new JMenu ("Tools");
    
    JMenuItem quitItem = new JMenuItem ("Quit");
    JMenuItem helpItem = new JMenuItem ("Help");
    JMenuItem aboutItem = new JMenuItem ("About");
    newItem = new JMenuItem ("New");
    saveItem = new JMenuItem ("Save");
    saveAsItem = new JMenuItem ("Save As");
    openItem = new JMenuItem ("Open");
    signOutItem = new JMenuItem ("Sign Out");
    passItem = new JMenuItem ("Change Password");
    chartItem = new JMenuItem("Chart");
    browseItem = new JMenuItem("Browse");
    graphItem = new JMenuItem ("Graph");
    sortItem = new JMenuItem("Sort");
    searchItem = new JMenuItem("Search");
    
    fileMenu.add (newItem);
    fileMenu.add (openItem);
    fileMenu.add (saveItem);
    fileMenu.add (saveAsItem);
    fileMenu.add (signOutItem);
    fileMenu.add (quitItem);
    toolsMenu.add (searchItem);
    toolsMenu.add (sortItem);
    toolsMenu.add (passItem);
    viewMenu.add (browseItem);
    viewMenu.add (chartItem);
    viewMenu.add (graphItem);
    helpMenu.add (helpItem);
    helpMenu.add (aboutItem);
    
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
    passItem.addActionListener (this);
    browseItem.addActionListener (this);
    chartItem.addActionListener (this);
    graphItem.addActionListener (this);
    sortItem.addActionListener (this);
    searchItem.addActionListener (this);
    
    buttonEnable ("disable all");
  
    setSize (400,550);
    setResizable (false);
    setVisible (true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    
    add(r);
    usernameOpener();
  }
  
  /**
   * This method opens the username screen.
   * 
   * @param help this button displays useful tips to the user.
   * @param ok this button let's the user continue into the program.
   * @param icon creates the background.
   * @param label creates a new JLabel.
   */ 
  public void usernameOpener ()
  {  
    r.add (r.thePanel);
    r.thePanel.setPreferredSize (new Dimension (400,500));
    r.thePanel.setLayout (null);
    
    JButton ok = new JButton ("OK");
    ok.setBounds (270,200,51,30);
    ok.setActionCommand ("Log In OK");
    ok.setToolTipText ("Log In");
    ok.addActionListener (this);
    r.thePanel.add(ok);
        
    JButton help = new JButton ("?");
    help.setBounds (321,200,41,30);
    help.setActionCommand ("Log In Help");
    help.setToolTipText ("Help");
    help.addActionListener (this);
    r.thePanel.add(help);
    
    r.usernameField = new JTextField ();
    r.usernameField.setBounds(20, 200, 250, 30);
    r.thePanel.add (r.usernameField);
    
    Icon icon = new ImageIcon(".//Graphics/Username.gif");
    JLabel label = new JLabel(icon);
    label.setBounds (0,0,400,500);
    r.thePanel.add (label);
  }  
  
    /**
   * This method triggers the JDialog in which the admin enters their password.
   * 
   * @param enterPass creates a new JLabel.
   * @param ok creates a new JButton.
   * @param cancel creates a new JButton.
   * @param PASSFIELD creates a new JTextField.
   * @param e points to the ActionEvent class.
   * @param passFile opens the password file.
   * @param no is for the IOException.
   */
  public void adminLogin ()
  {
    r.successPass = false;
    d = new JDialog ();
    d.setLocationRelativeTo (null);
    d.setSize (450, 100);
    d.setResizable (false);
    d.setLayout (new FlowLayout());
    
    JLabel enterPass = new JLabel ("You are attempting to log in as admin. Please enter the password below:");
    final JTextField passField = new JTextField (20);
    passField.setPreferredSize (new Dimension (200,29));
    JButton ok = new JButton ("OK");
    JButton cancel = new JButton ("Cancel");
    d.add (enterPass);
    d.add (passField);
    d.add (ok);
    d.add (cancel);
    d.setVisible (true);
    ok.addActionListener (new ActionListener ()
                            {
      public void actionPerformed (ActionEvent e)
      {
        try
        {
          BufferedReader passFile = new BufferedReader (new FileReader ("pass.txt"));
          if (passFile.readLine ().equals (passField.getText ()))
          {
            r.successPass = true;
            r.admin = true;
            buttonEnable ("enable all");
          }
          else
            JOptionPane.showMessageDialog (d, "Please enter a previously used username. If this is your first time using this application ","Help", JOptionPane.WARNING_MESSAGE);  
          d.dispose ();
          continueLogIn ();
        }
        catch (IOException no)
        {
        }
      }
    });
    cancel.addActionListener (new ActionListener ()
                                {
      public void actionPerformed (ActionEvent e)
      {
        d.dispose ();
      }});
  }
  
  /**
   * This method either lets the user use the program or repeats the admin login process whether or not the password was correct.
   */
  public void continueLogIn ()
  {
    if (r.successPass)
    {
      r.newDatabase ();
      r.fieldView ();
      r.invalidate();
      r.validate();
      r.repaint();
    }
    else
    {
      adminLogin ();
    }
  }
  
  /**
   * This method opens a dialogue box to choose which field to sort.
   * 
   * @param group This holds the group of buttons.
   * @param okButton This button allows the suer to submit his choice.
   * @param closeButton This button allows the user to close the dialogue box.
   * @param e points to the ActionEvent class.
   * @param title,pickLabel Label for titles.
   */ 
  public void specifySort ()
  {
    sortDialogue = new JDialog (this,"Choose Field");
    sortDialogue.setSize (30,300);
    sortDialogue.setLocationRelativeTo (null);
    sortDialogue.setVisible(true);
    sortDialogue.setLayout (new FlowLayout(FlowLayout.LEADING));
    
    ButtonGroup group = new ButtonGroup ();
    JButton okButton = new JButton ("          OK          ");
    okButton.setSize (new Dimension (35,40));
    JLabel pickLabel = new JLabel ("Pick a field to sort:");
    JLabel title = new JLabel ("Specify Search");
    title.setFont (new Font ("Serif", Font.BOLD, 17)); 
    
    sortTitle = new JRadioButton ("Title Field");
    sortAuthor = new JRadioButton ("Author Field");
    sortGenre = new JRadioButton ("Genre");
    sortLocation = new JRadioButton ("Location");
    sortBorrow = new JRadioButton ("Borrow Date");
    sortReturn = new JRadioButton ("Return Date");
    
    group.add(sortTitle);
    group.add(sortAuthor);
    group.add(sortGenre);
    group.add(sortLocation);
    group.add(sortBorrow);
    group.add(sortReturn);
    
    sortDialogue.add(title);
    sortDialogue.add(pickLabel);
    sortDialogue.add(sortTitle);
    sortDialogue.add(sortAuthor);
    sortDialogue.add(sortGenre);
    sortDialogue.add(sortLocation);
    sortDialogue.add(sortBorrow);
    sortDialogue.add(sortReturn);
    sortDialogue.add (okButton);
    
    okButton.addActionListener (new ActionListener()
                                  {
      public void actionPerformed(ActionEvent e){
        if (sortTitle.isSelected ())
          r.sortWhichField = 1;
        
        if (sortAuthor.isSelected ())
          r.sortWhichField = 2;
        
        if (sortGenre.isSelected ())
          r.sortWhichField = 3;
        
        if (sortLocation.isSelected ())
          r.sortWhichField = 4;
        
        if (sortBorrow.isSelected ())
          r.sortWhichField = 5;
        
        if (sortReturn.isSelected ())
          r.sortWhichField = 6;
        
        sortDialogue.dispose();
        r.sorter();
        setSize (600,(80+(BookRecord.recNum*20)));
        r.tableView ();
        currentView = "Chart";
        invalidate();
        validate();
        repaint();
      }
    });
  }
  
  /**
   * This method disables all menus items.
   */ 
  public void buttonEnable (String choice)
  {
    if (choice.equals ("disable all"))
    {
      newItem.setEnabled(false);
      saveItem.setEnabled(false);
      saveAsItem.setEnabled(false);
      openItem.setEnabled(false);
      signOutItem.setEnabled(false);
      passItem.setEnabled(false);
      chartItem.setEnabled(false);
      browseItem.setEnabled(false);
      graphItem.setEnabled(false);
      sortItem.setEnabled(false);
      searchItem.setEnabled(false);
    }
    if (choice.equals ("enable guest"))
    {
      openItem.setEnabled(true);
      signOutItem.setEnabled(true);
      chartItem.setEnabled(true);
      browseItem.setEnabled(true);
      graphItem.setEnabled(true);
      sortItem.setEnabled(true);
      searchItem.setEnabled(true);
    }
    if (choice.equals ("enable all"))
    {
      newItem.setEnabled(true);
      saveItem.setEnabled(true);
      saveAsItem.setEnabled(true);
      openItem.setEnabled(true);
      signOutItem.setEnabled(true);
      passItem.setEnabled(true);
      chartItem.setEnabled(true);
      browseItem.setEnabled(true);
      graphItem.setEnabled(true);
      sortItem.setEnabled(true);
      searchItem.setEnabled(true);
    }
  }
  
  /**
   * This method opens a dialogue box to choose what to search.
   * 
   * @param group This holds the group of buttons.
   * @param okButton This button allows the suer to submit his choice.
   * @param closeButton This button allows the user to close the dialogue box.
   * @param group2 This holds the second group of buttons.
   * @param searchLabel creates a new label.
   * @param e points to the ActionEvent class.
   */ 
  public void specifySearch()
  {
    searchBox = new JDialog (this,"Choose Criteria");
    searchBox.setSize (155,410);
    searchBox.setLocationRelativeTo (null);
    searchBox.setVisible(true);
    searchBox.setLayout (new BoxLayout(searchBox.getContentPane(),BoxLayout.Y_AXIS));
    
    ButtonGroup group = new ButtonGroup ();
    ButtonGroup group2 = new ButtonGroup ();
    JButton okButton = new JButton ("              Ok              ");
    okButton.setPreferredSize (new Dimension (155,30));
    JLabel title = new JLabel (" Specify Search");
    JLabel fieldLabel = new JLabel ("Pick a field to search:");
    JLabel typeLabel = new JLabel ("Pick a type of search:");
    JLabel searchLabel = new JLabel ("Input text below:");
    title.setFont (new Font ("Serif", Font.BOLD, 19)); 
    
    searchTitle = new JRadioButton ("Title");
    searchAuthor = new JRadioButton ("Author");
    searchLocation = new JRadioButton ("Location");
    searchGenre = new JRadioButton ("Genre");
    searchBorrow = new JRadioButton ("Borrow Date");
    searchReturn = new JRadioButton ("Return Date");
    partialSearch = new JRadioButton ("Partial Search");
    wholeSearch = new JRadioButton ("Entire Field");
    
    group.add(searchTitle);
    group.add(searchAuthor);
    group.add(searchLocation);
    group.add(searchGenre);
    group.add(searchBorrow);
    group.add(searchReturn);
    group2.add(partialSearch);
    group2.add(wholeSearch);
    
    searchBox.add(title);
    searchBox.add(Box.createRigidArea(new Dimension(0,10)));
    searchBox.add (fieldLabel);
    searchBox.add(Box.createRigidArea(new Dimension(0,5)));
    searchBox.add(searchTitle);
    searchBox.add(searchAuthor);
    searchBox.add(searchLocation);
    searchBox.add(searchGenre);
    searchBox.add(searchBorrow);
    searchBox.add(searchReturn);
    searchBox.add(Box.createRigidArea(new Dimension(0,10)));
    searchBox.add (typeLabel);
    searchBox.add(partialSearch);
    searchBox.add(wholeSearch);
    searchBox.add(Box.createRigidArea(new Dimension(0,10))); 
    searchBox.add(searchLabel);
    searchBox.add(Box.createRigidArea(new Dimension(0,5)));
    searchBox.add(searchField);
    searchBox.add(Box.createRigidArea(new Dimension(0,10))); 
    searchBox.add(okButton);
    
    okButton.addActionListener (new ActionListener()
                                  {
      public void actionPerformed(ActionEvent e){
        if (searchTitle.isSelected ())
          r.searchWhichField = 1;
        
        if (searchAuthor.isSelected ())
          r.searchWhichField = 2;
        
        if (searchGenre.isSelected ())
          r.searchWhichField = 3;
        
        if (searchLocation.isSelected ())
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
        r.searcher();
        setSize (600,(80+(BookRecord.recNum*20)));
        r.tableView ();
        currentView = "Chart";
        invalidate();
        validate();
        repaint();
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
      if (!currentView.equals ("Browse"))
      {
        if (currentView.equals ("Chart") && r.admin == true)
        {
          r.getChartData();
        }
        currentView = "Browse";
        setSize (400,550);
        r.fieldView();
      }
      r.updateDisplay();
    }
    
    else if (ae.getActionCommand ().equals("About"))
      JOptionPane.showMessageDialog(this,"Copyright JDL Development 2014   -   Version 1.0.0");
    
    else if (ae.getActionCommand ().equals("Open"))
    {
      r.saveChecker();
      r.openFile ();
      if (!currentView.equals ("Browse"))
      {
        if (currentView.equals ("Chart") && r.admin == true)
        {
          r.getChartData();
        }
        currentView = "Browse";
        setSize (400,550);
        r.fieldView();
      }
      r.updateDisplay();
    }    
    else if (ae.getActionCommand ().equals("Save"))
      r.save (r.fileName);
    
    else if (ae.getActionCommand ().equals("Save As"))
      r.saveAs ();
    
    else if (ae.getActionCommand().equals("Browse")) 
    {
      if (!currentView.equals ("Browse"))
      {
        if (currentView.equals ("Chart") && r.admin == true)
        {
          r.getChartData();
        }
        currentView = "Browse";
        setSize (400,550);
        r.fieldView();
        r.updateDisplay();
      }
    }
    
    else if (ae.getActionCommand().equals("Chart")) 
    {
      if (!currentView.equals ("Chart"))
      {
        currentView = "Chart";
        r.sortWhichField = 0;
        setSize (600,(80+(BookRecord.recNum*20)));
        r.tableView();
      }
    }
    
    else if (ae.getActionCommand().equals("Graph")) 
    {
      if (!currentView.equals ("Graph"))
        currentView = "Graph";
      b = new BarGraph ();
      r.graphView (b);
      b.frame.setVisible (true);
    }
    
    else if (ae.getActionCommand().equals("Sort")) 
    {
      if (currentView.equals("Chart"))
        r.getChartData();
      specifySort ();
    }
    
    else if (ae.getActionCommand().equals("Search")) 
    {
      if (currentView.equals("Chart"))
        r.getChartData();
      specifySearch ();
    }
    
    else if (ae.getActionCommand().equals("Help"))
    {
      String progpath = new String ("hh.exe youtube.chm");
      try
      {
        Runtime.getRuntime ().exec (progpath);
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(this,"Couldn't find the Help File");
      }
    }
    
    else if (ae.getActionCommand ().equals ("Quit"))
    {
      r.saveChecker();
      System.exit (0);
    }
    
    else if (ae.getActionCommand ().equals ("Change Password"))
    {
      if (r.admin)
      {
        r.changePassword ();
      }
    }
    else if (ae.getActionCommand ().equals ("Log In OK"))
    {
      r.username = r.usernameField.getText ();
      if (r.username.equals (""))
        JOptionPane.showMessageDialog (this, "Please enter a username to continue,", "No Username", JOptionPane.ERROR_MESSAGE);
      else if (r.username.equals ("admin"))
        adminLogin();
      else
      {
        r.successPass = true;
        r.admin = false;
        buttonEnable("enable guest");
        continueLogIn ();
      }
      r.toolbarMaker();
      r.invalidate();
      r.validate();
      r.repaint();
    }
    else if (ae.getActionCommand ().equals ("Log In Help"))
    {
      JOptionPane.showMessageDialog (this, "Please enter a previously used username. If this is your first time using this application " +
                                       "type in a desired username into the field. If you would like to edit the data, type 'admin'.",
                                     "Help", JOptionPane.INFORMATION_MESSAGE);  
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
