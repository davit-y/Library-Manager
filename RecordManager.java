import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.*;
import java.security.*;


/**
 * Creates a library manager in which users can create a number of book records. Information will
 * be stored in an ArrayList. There is a tool bar that has six buttons: previous, next, 
 * save, new, delete, and borrow. The user can cycle through the previous profiles created as well as
 * create new ones and delete old ones. The user can also save and open files.
 * 
 * @author JDL Development - Lily&David
 * @version 1.0, May 12/14
 */
public class RecordManager extends JPanel implements ActionListener
{
  /**
   * d (JDialog) points to the JDialog class
   */
  JDialog d;
  /**
   * book (ArrayList) creates a reference for the BookRecord array
   */
  ArrayList <BookRecord> book = new ArrayList <BookRecord> ();
  /**
   * The panel in which all components are placed.
   */
  JPanel thePanel = new JPanel ();
  /**
   * currentRec (int) signals the current record being viewed.
   */
  private static int currentRec;
  /**
   * toolBarTop This is the toolbar at the top of the window.
   */ 
  JToolBar toolBarTop = new JToolBar ("Top Bar");
  /**
   * PREVIOUS (String) stores a string used to reference the button.
   */
  static final private String PREVIOUS = "previous";
  /**
   * NEXT (String) stores a string used to reference the button.
   */
  static final private String NEXT = "next";
  /**
   * SAVE (String) stores a string used to reference the button.
   */
  static final private String SAVE = "save";
  /**
   * NEW (String) stores a string used to reference the button.
   */
  static final private String NEW = "new";
  /**
   * DELETE (String) stores a string used to reference the button.
   */
  static final private String DELETE = "delete";
  /**
   * BORROW (String) stores a string used to reference the button.
   */
  static final private String BORROW = "borrow";
  /**
   * RETURN (String) stores a string used to reference the button.
   */
  static final private String RETURN = "return";
  /**
   * This variable holds the username of the current user.
   */ 
  String username;
  /**
   * entryLabel (JLabel) points to the JLabel class.
   */
  JLabel entryLabel;
  /**
   * titleField (JTextField) creates a new textfield.
   */
  JTextField titleField;
  /**
   * authorField (JTextField) creates a new textfield.
   */
  JTextField authorField;
  /**
   * genreBox (JComboBox) creates a new JComboBox.
   */
  JComboBox genreBox;
  /**
   * locationField (JTextField) creates a new textfield.
   */
  JTextField locationField;
  /**
   * borrowField (JTextField) creates a new textfield.
   */
  JTextField borrowField;
  /**
   * returnField (JTextField) creates a new textfield.
   */
  JTextField returnField;
  /**
   * genreBoxItems (String []) stores an array of the items to be stored in genreBox.
   */
  String [] genreBoxItems = {"---", "Action", "Historical", "Horror", "Humour", "Kids", "Mystery", "Romance", "Sci-fi/Fantasy", "Supernatural", "Young Adult", "Other"};
  /**
   * This is a boolean statement that holds whether or not the current record has been saved.
   */ 
  boolean recSaved;
  /**
   * This is a boolean statement that holds whether or not the current file has been saved.
   */
  boolean fileSaved = true;
  /**
   * fileName The name of the file.
   */ 
  String fileName;
  /**
   * theFile The file that will be saved and opened.
   */ 
  File theFile;
  /**
   * optionPaneResult Stores value from the error dialogue box.
   */ 
  int optionPaneResult;
  /**
   * admin is true if the admin is accessing the program, false if it is a guest
   */
  boolean admin = true;
  /**
   * columnNames stores the names of columns
   */
  Object columnNames[];
  /**
   * dataValues stores the data for the table
   */
  Object dataValues[] [];
  /**
   * myTable creates a new JTable
   */
  JTable myTable = new JTable ();
  /**
   * This is the order of the records to be displayed
   */
  int [] order;
  /**
   * This stores which field will be sorted.
   */ 
  int sortWhichField = 0;
  /**
   *The constructor creates a new entry in the array list and starts the display method.
   */
  SearchAndSort s = new SearchAndSort ();
  /**
   *The constructor creates a new entry in the array list and starts the display method.
   */
  DataBaseApp dba;
  /**
   *Boolean variable to identify whether the JTable is sorted.
   */ 
  boolean sorted = false;
  /**
   *Boolean variable to identify whether the JTable should display search results.
   */ 
  boolean searchMode = false;
  /**
   * This is the index of records found by the search
   */
  int [] searchIndex;
  /**
   * This stores which field will be searched.
   */ 
  int searchWhichField = 0;
  /**
   * This stores which type of search to use.
   */ 
  int whichSearch = 0;
  /**
   * Variable which holds whether a partial search or whole search is requested.
   */
  int partialOrWhole = 0;
  /**
   * The text to be searched.
   */ 
  String searchText = "";
  /**
   * This variable keeps track of amount of searches found.
   */ 
  int amountFound = 0;
  /**
   * This is a boolean statement that holds whether or not the correct password has been entered.
   */
  boolean successPass = true;
  /**
   * Points to the MessageDigest class.
   */
  private static MessageDigest md;
  
  
  /**
   * Constructor for the RecordManager class.
   */
  public RecordManager ()
  {
    recSaved = true;
    fileSaved = true;
    currentRec = 0;
  }
  
  
  /**
   * This method lets the user change the password.
   * 
   * @param changePassword1 creates a new JLabel.
   * @param changePassword2 creates a new JLabel.
   * @param changePassword3 creates a new JLabel.
   * @param changePassword4 creates a new JLabel.
   * @param OLDPASSFIELD creates a new JTextField.
   * @param NEWPASSFIELD creates a new JTextField.
   * @param NEWPASSFIELD2 creates a new JTextField.
   * @param ok creates a new JButton.
   * @param cancel creates a new JButton.
   * @param newPassFile writes the new password into the file.
   * @param e points to the ActionEvent class.
   * @param ie is for the IOException.
   * @e IOException catches IO errors.
   */
  public void changePassword ()
  {
    d = new JDialog ();
    d.setSize (300, 270);
    d.setResizable (true);
    JLabel changePassword1 = new JLabel ("Change the admin password");
    JLabel changePassword2 = new JLabel ("Old password");
    JLabel changePassword3 = new JLabel ("New password");
    JLabel changePassword4 = new JLabel ("Confirm password");
    final JTextField OLDPASSFIELD = new JTextField (20);
    final JTextField NEWPASSFIELD = new JTextField (20);
    final JTextField NEWPASSFIELD2 = new JTextField (20);
    JButton ok = new JButton ("Change Password");
    JButton cancel = new JButton ("Cancel");
    
    d.setLayout (new BoxLayout(d.getContentPane(),BoxLayout.Y_AXIS));
    d.add (changePassword1);
    d.add(Box.createRigidArea(new Dimension(0,10)));
    d.add (changePassword2);
    d.add (OLDPASSFIELD);
    d.add(Box.createRigidArea(new Dimension(0,10)));
    d.add (changePassword3);
    d.add (NEWPASSFIELD);
    d.add(Box.createRigidArea(new Dimension(0,10)));
    d.add (changePassword4);
    d.add (NEWPASSFIELD2);
    d.add(Box.createRigidArea(new Dimension(0,10)));
    d.add (ok);
    d.add (cancel);
    
    
    ok.addActionListener(new ActionListener() 
                           {
      public void actionPerformed(ActionEvent e) {
        try
        {
        BufferedReader passFile = new BufferedReader (new FileReader ("Misc/pass.txt"));
          if (passFile.readLine ().equals (encryptPassword(OLDPASSFIELD.getText ())))
          {
            if (NEWPASSFIELD.getText ().equals (NEWPASSFIELD2.getText ()))
            {
              PrintWriter newPassFile = new PrintWriter (new FileWriter ("Misc/pass.txt"));
              newPassFile.print (encryptPassword(NEWPASSFIELD.getText ()));
              newPassFile.close ();
              JOptionPane.showMessageDialog (thePanel, "You have successfully changed your password.", "Successful", JOptionPane.INFORMATION_MESSAGE);
              d.dispose ();
            }
            else
            {
              JOptionPane.showMessageDialog (thePanel, "The new passwords do not match. Please try again.", "New Passwords Don't Match", JOptionPane.ERROR_MESSAGE);
              NEWPASSFIELD2.requestFocus ();
              NEWPASSFIELD2.setText ("");
            }
          }
          else
          {
            JOptionPane.showMessageDialog (thePanel, "Incorrect old password. Please try again.", "Old Password Incorrect", JOptionPane.ERROR_MESSAGE);
            OLDPASSFIELD.requestFocus ();
            OLDPASSFIELD.setText ("");
          }
        }
        catch (IOException ie)
        {
        }
      }
    });
    
    cancel.addActionListener(new ActionListener() 
                               {
      public void actionPerformed(ActionEvent e) {
        d.dispose();
      }
    });
    d.setVisible (true);
  }
  
  /**
   * Encrypts the password.
   * 
   * @param ex for the NoSuchAlgorithmException.
   * @return the encrypted password.
   * @e NoSuchAlgorithmException catches algorithm errors.
   */
  public String encryptPassword(String pass)
  {
    try 
    {
      md = MessageDigest.getInstance("MD5");
      byte[] passBytes = pass.getBytes();
      md.reset();
      byte[] digested = md.digest(passBytes);
      StringBuffer sb = new StringBuffer();
      for(int i = 0; i < digested.length ; i++)
      {
        sb.append(Integer.toHexString(0xff & digested[i]));
      }
      return sb.toString();
    } 
    catch (NoSuchAlgorithmException ex) 
    {
    }
    return null;
  }
  
  /**
   * Prints the database.
   * 
   * @param p points to the Printer class.
   */
  public void printDatabase ()
  {
    Printer p = new Printer ();
    p.println ("Data in Library Manager");
    p.println ();
    for (int x = 0; x < BookRecord.recNum; x ++)
    {
      p.println ("Record Number: " + (x + 1));
      p.println ("Book Title: " + book.get(x).getTitle());
      p.println ("Author Name: " + book.get(x).getAuthor());
      p.println ("Book Genre: " + book.get(x).getGenre());
      p.println ("Location: " + book.get(x).getLocation());
      p.println ("Borrow Date: " + book.get(x).getBorrowDate());
      p.println ("Return Date: " + book.get(x).getReturnDate());
      p.println ();
    } 
    p.printUsingDialog ();
  }
  
  
  /**
   * Switches from an alternate view to the textfield view.
   * 
   * @param icon icon that holds the background image.
   * @param bg label that holds background image.
   */
  public void fieldView ()
  {
    thePanel.removeAll();
    thePanel.setLayout(null);
    thePanel.setPreferredSize(new Dimension (400,500));
    entryLabel = new JLabel ("Entry " + (currentRec + 1) + " of " + BookRecord.recNum);
    entryLabel.setFont (new Font ("Calibri", Font.PLAIN, 24)); 
    
    titleField = new JTextField (30);
    authorField = new JTextField (30);
    genreBox = new JComboBox (genreBoxItems);
    genreBox.setSelectedItem ("---");
    locationField = new JTextField (30);
    borrowField = new JTextField (30);
    returnField = new JTextField (30);
    
    toolBarTop.setBounds (0,0,400,50);
    entryLabel.setBounds (243,349,200,200);
    titleField.setBounds (30,120,170, 25);
    authorField.setBounds (30,200,170, 25);
    genreBox.setBounds (30,260,170, 25);
    locationField.setBounds (30,320,170, 25);
    borrowField.setBounds (30,380,170, 25);
    returnField.setBounds (30,440,170, 25);
    
    Icon icon = new ImageIcon(".//Graphics/TextFieldBG.jpg");
    JLabel bg = new JLabel(icon);
    bg.setBounds (0,50,400,450);
    
    
    thePanel.add (toolBarTop);
    thePanel.add (entryLabel);
    thePanel.add (titleField);
    thePanel.add (authorField);
    thePanel.add (genreBox);
    thePanel.add (locationField);
    thePanel.add (borrowField);
    thePanel.add (returnField);
    thePanel.add (bg);
  }
  
  /**
   * Switches from an alternate view to the JTable view, and creates the JTable.
   * 
   * @param tableModel sets the table model for the JTable.
   * @param scroll creates a scroll bar.
   */
  public void tableView() 
  {
    thePanel.removeAll();
    thePanel.setLayout(new BorderLayout());
    thePanel.setPreferredSize(new Dimension (580,(80+(BookRecord.recNum*20))));
    createColumns();
    createData();
    DefaultTableModel tableModel = new DefaultTableModel (dataValues, columnNames);
    myTable.setModel (tableModel);
    myTable.setColumnSelectionAllowed(false);
    myTable.setCellSelectionEnabled(false);
    myTable.setRowSelectionAllowed(false);
    myTable.setRowHeight(20);
    myTable.setAutoResizeMode (JTable.AUTO_RESIZE_OFF);
    myTable.getColumnModel().getColumn(0).setPreferredWidth(25);
    myTable.getColumnModel().getColumn(1).setPreferredWidth(140);
    myTable.getColumnModel().getColumn(2).setPreferredWidth(100);
    myTable.getColumnModel().getColumn(3).setPreferredWidth(78);
    myTable.getColumnModel().getColumn(4).setPreferredWidth(87);
    myTable.getColumnModel().getColumn(6).setPreferredWidth(73);
    myTable.setShowVerticalLines(true);
    myTable.setShowHorizontalLines(true);
    myTable.setGridColor(Color.LIGHT_GRAY);
    
    JScrollPane scroll = new JScrollPane (myTable);
    thePanel.add(scroll, BorderLayout.CENTER);
  }
  
  /**
   * This method creates the toolbar.
   * 
   * @param button This is a new JButton.
   */ 
  public void toolbarMaker ()
  {
    toolBarTop.setLayout (new FlowLayout (FlowLayout.CENTER));
    toolBarTop.setFloatable (false);
    JButton button = null;
    button = makeNavigationButton ("previous", PREVIOUS, "Previous Record", "Previous");
    toolBarTop.add (button);
    button = makeNavigationButton ("next", NEXT, "Next Record", "Next");
    toolBarTop.add (button);   
    if (admin)
    {
      button = makeNavigationButton ("save", SAVE, "Save Record", "Save");
      toolBarTop.add (button);
      button = makeNavigationButton ("delete", DELETE, "Delete Record", "Del");
      toolBarTop.add (button);
      button = makeNavigationButton ("new", NEW, "New Record", "New");
      toolBarTop.add (button);
    }
    else
    {
      button = makeNavigationButton ("borrow", BORROW, "Borrow this Book", "Borrow");
      toolBarTop.add (button);
      button = makeNavigationButton ("return", RETURN, "Return this Book", "Return");
      toolBarTop.add (button);
    }
  }
  
  /**
   * This method creates the butons for the toolbar.
   * 
   * @param imageName is the name of the image to be added
   * @param actionCommand this is the string value that ActionPerformed will pick up
   * @param toolTipText this is the text that appears on mouse-over
   * @param altText this the alternate text for the button
   * @param imgLocation is the location of the image to be added
   * @param imageGIF is the image that will be added to the button
   * @param button is a new JButton
   * @return the new button on the toolbar.
   */ 
  protected JButton makeNavigationButton (String imageName, String actionCommand, String toolTipText, String altText)
  {
    String imgLocation = "Graphics/"+imageName + ".png";
    Image imageGIF = Toolkit.getDefaultToolkit ().getImage (imgLocation);
    
    JButton button = new JButton ();
    button.setActionCommand (actionCommand);
    button.setToolTipText (toolTipText);
    button.addActionListener (this);
    
    button.setIcon (new ImageIcon (imageGIF, altText));
    
    return button;
  }
  
  /**
   * Empties the old database and creates a new one.
   */
  public void newDatabase ()
  {
    book.clear ();
    BookRecord.recNum = 0;
    book.add (new BookRecord());
    currentRec = 0;
    recSaved = true;
    fileSaved = true;
  }
  
  
  /**
   * Updates the info being displayed.
   */
  public void updateDisplay ()
  {
    titleField.setText (book.get(currentRec).getTitle());
    authorField.setText (book.get(currentRec).getAuthor());
    genreBox.setSelectedIndex (findIndex (book.get(currentRec).getGenre ()));
    locationField.setText (book.get(currentRec).getLocation());                       
    borrowField.setText (book.get(currentRec).getBorrowDate());
    returnField.setText (book.get(currentRec).getReturnDate());
    
    entryLabel.setText ("Entry " + (currentRec + 1) + " of " + BookRecord.recNum);
    fileSaved = false;
  }
  
  /**
   * Finds the index of the item in the genreBoxItem array.
   * 
   * @param item (String) the item that needs to be found in the array.
   * @return the index of the item
   */
  public int findIndex (String item)
  {
    for (int x = 0; x < 12; x++)
    {
      if (genreBoxItems [x].equals (item))
      {
        return x;
      }
    }
    return -1;
  }
  
  /**
   * Shows the previous profile from the current one. Allows for wrapping (if the current
   * profile is the first one, it shows the last one).
   */
  private void prevRec ()
  {
    if (recSaved)
    {
      if (currentRec == 0)
      {
        currentRec = BookRecord.recNum-1;
      }
      else
      {
        currentRec --;
      }
      updateDisplay ();
    }
    else
    {
      JOptionPane.showMessageDialog (this, "Please save/delete this record first!", "Not Saved", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Shows the next profile from the current one. Allows for wrapping. (if the current
   * profile is the last one, it shows the first one).
   */
  private void nextRec ()
  {
    if (recSaved)
    {
      if (currentRec == (BookRecord.recNum - 1))
      {
        currentRec = 0;
      }
      else
      {
        currentRec ++;
      }
      updateDisplay (); 
    }
    else
    {
      JOptionPane.showMessageDialog (this, "Please save/delete this record first!", "Not Saved", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Creates a new profile IF all the fields are not empty.
   */
  private void newRec ()
  {
    if (!(titleField.getText ().equals ("") && recSaved == false))
    {
      book.add (new BookRecord());
      currentRec = BookRecord.recNum - 1;
      updateDisplay ();
      genreBox.setSelectedItem ("---");
      recSaved = false;
      fileSaved = false;
    }
  }
  
  /**
   * Deletes the current profile being viewed and displays the previous file.
   */
  public void deleteRec ()
  {
    book.remove(currentRec);
    BookRecord.recNum --;
    if (BookRecord.recNum == 0)
    {
      book.add (new BookRecord());
    }
    recSaved = true;
    prevRec ();
    fileSaved = false;
  }
  
  /**
   * Takes the current date and sets the borrow date and also adds 10 days for the return date.
   * 
   * @param tempBDate stores the borrow date
   * @param tempRDate stores the return date
   * @param dateFormat stores the format the date is displayed
   * @param date gets the current date
   * @param isLeapYear true if the year is a leap year, false if not
   */
  public void borrowBook ()
  {
    String tempBDate = "";
    String tempRDate = "";
    if (!(book.get(currentRec).getBorrowDate() == null || book.get(currentRec).getBorrowDate().equals ("") || book.get(currentRec).getLocation().substring (12).equals (username)))
    {
      JOptionPane.showMessageDialog (this, "This book has already been borrowed by someone else!", "Unavailable", JOptionPane.ERROR_MESSAGE);
    }
    else
    {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Date date = new Date();
      
      tempBDate = (String)(dateFormat.format(date));
      
      tempRDate = determineReturnDate (tempBDate);
      book.get(currentRec).setBorrowDate(tempBDate);
      book.get(currentRec).setReturnDate(tempRDate);
      book.get(currentRec).setLocation("Borrowed by " + username);
      save (fileName);
      updateDisplay ();
    }
    
  }
  
  /**
   * Determines the return date based off of the borrow date.
   * 
   * @param returnDay stores the day the book is returned from 1-28/29/30/31.
   * @param returnMonth stores the month the book is returned from 1-12.
   * @param returnYear stores the year the book is returned.
   * @param tempBDate gets the borrow date that the return date is based off of.
   * @param tempRDate stores the new return date.
   * @return the determined return date.
   */
  public String determineReturnDate (String tempBDate)
  {
    int returnDay = 0;
    int returnMonth = 0;
    int returnYear = 0;
    String tempRDate = "";
    
    returnDay = Integer.parseInt (tempBDate.substring (0, 2)) + 10;
    returnMonth = Integer.parseInt (tempBDate.substring (3, 5));
    returnYear = Integer.parseInt (tempBDate.substring (6, 10));
    if (returnMonth == 1 || returnMonth == 3 || returnMonth == 5 || returnMonth == 7 || returnMonth == 8 || returnMonth == 10 || returnMonth == 12)
    {
      if (returnDay > 31)
      {
        returnDay = returnDay - 31;
        returnMonth ++;
      }
    }
    else if (returnMonth == 4 || returnMonth == 4 || returnMonth == 4 || returnMonth == 4)
    {
      if (returnDay > 30)
      {
        returnDay = returnDay - 30;
        returnMonth ++;
      }
    }
    else
    {
      boolean isLeapYear = ((returnYear % 4 == 0) && (returnYear % 100 != 0) || (returnYear % 400 == 0));
      if (isLeapYear)
      {
        if (returnDay > 29)
        {
          returnDay = returnDay - 29;
          returnMonth ++;
        }
      }
      else
      {
        if (returnDay > 28)
        {
          returnDay = returnDay - 28;
          returnMonth ++;
        }
      }
    }
    if (returnMonth > 12)
    {
      returnMonth = returnMonth - 12;
      returnYear ++;
    }
    if (returnDay < 10)
    {
      tempRDate = "0" + returnDay + "/";
    }
    else
    {
      tempRDate = returnDay + "/";
    }
    if (returnMonth < 10)
    {
      tempRDate = tempRDate + "0" + returnMonth + "/";
    }
    else
    {
      tempRDate = tempRDate + returnMonth + "/";
    }
    return (tempRDate + returnYear);
  }
  
  /**
   * Returns the book to the library and clears the borrow date data and the location data.
   */
  public void returnBook ()
  {
    if (!(book.get(currentRec).getBorrowDate() == null || book.get(currentRec).getBorrowDate().equals ("")))
    {
      try
      {
        if (book.get(currentRec).getLocation().substring (12).equals (username))
        {
          book.get(currentRec).setBorrowDate("");
          book.get(currentRec).setReturnDate("");
          book.get(currentRec).setLocation("");
          save (fileName);
          updateDisplay ();
        }
        else
        {
          JOptionPane.showMessageDialog (this, "This book has been borrowed by someone else, so you cannot return it!", "Unavailable", JOptionPane.ERROR_MESSAGE);
        }
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog (this, "This book has been borrowed by someone else, so you cannot return it!", "Unavailable", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog (this, "This book is already in the library!", "Unavailable", JOptionPane.ERROR_MESSAGE);
    }
  }
  
  /**
   * Saves the information to the ArrayList if the fields are not empty.
   * 
   * @param title (String) the book title to be stored
   * @param author (String) the author name to be stored
   * @param genre (String) the genre to be stored
   * @param location (String) the location to be stored
   * @param borrowD (String) the borrow date to be stored
   * @param returnD (String) the return date to be stored
   */
  private void saveRec (String title, String author, String genre, String location, String borrowD, String returnD)
  {
    if (!title.equals (""))
    {
      if (DataCheck.checkDate (borrowD) == false)
      {
        borrowD = "";
        JOptionPane.showMessageDialog (this, "The borrow date is improperly formatted.", "INVALID DATE", JOptionPane.WARNING_MESSAGE);
      }
      else if (DataCheck.checkDate (returnD) == false)
      {
        returnD = "";
        JOptionPane.showMessageDialog (this, "The return date is improperly formatted.", "INVALID DATE", JOptionPane.WARNING_MESSAGE);
      }
      else
      {
        if (!(borrowD == null || borrowD.equals ("")))
        {
          location = "Borrowed";
          returnD = determineReturnDate (borrowD);
        }
        else
        {
          if (!(location == null || location.equals ("")))
          {
            if (location.charAt (0) == 'B')
            {
              if (location.substring (0, 8).equals ("Borrowed"))
              {
                location = "";
              }
            }
            returnD = "";
          }
        }
        book.get(currentRec).setTitle (title);
        book.get(currentRec).setAuthor (author);
        book.get(currentRec).setGenre (genre);
        book.get(currentRec).setLocation (location);
        book.get(currentRec).setBorrowDate (borrowD);
        book.get(currentRec).setReturnDate(returnD);
        recSaved = true;
        fileSaved = false;
      }
    }
    else
      JOptionPane.showMessageDialog (this, "You must fill in at least the book title for a valid entry!", "BOOK FIELD EMPTY", JOptionPane.INFORMATION_MESSAGE);
    
    updateDisplay ();
  }
  
  /**
   * This method saves data onto a previously existing file or creates a new file.
   * 
   * @param pw References the PrintWriter class and makes its methods and features available.
   * @param fileNameString passes a string value through.
   * @param e IOException catches FileIO errors
   * @e IOException catches FileIO errors..
   */
  public void save (String fileName)
  {
    if (fileName != null)
    {
      PrintWriter output;
      try
      {PrintWriter ouput = new PrintWriter (new FileWriter ("Documents/"+fileName));
        ouput.println ("Storing the library data like a boss.");
        ouput.println (BookRecord.recNum);
        for (int z = 0; z < BookRecord.recNum; z++ ) 
        {
          ouput.println (book.get(z).getTitle());
          ouput.println (book.get(z).getAuthor());
          ouput.println (book.get(z).getGenre());
          ouput.println (book.get(z).getLocation());
          ouput.println (book.get(z).getBorrowDate());
          ouput.println (book.get(z).getReturnDate());
        }
        ouput.close(); 
        fileSaved = true;
      }
      catch (IOException e) 
      {
        JOptionPane.showMessageDialog (this, "Error occured while saving file.", "Save Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    else
      saveAs ();
  }
  
  /**
   * This method creates a new file to store the current data and allows the user to name the file.
   * 
   * @param fileChooser references the JFileChooser class.
   * @param filter creates a new ExampleFileFilter class.
   * @param result stores the action commands of the user in the JFileChooser dialog.
   * @param br references the BufferedReader class.
   * @param e catches FileIO errors.
   * @e IOException catches FileIO errors.
   */
  public void saveAs ()
  {
    JFileChooser fileChooser = new JFileChooser (".\\Documents");
    
    ExampleFileFilter filter = new ExampleFileFilter ( );
    filter.addExtension ("lmf");
    filter.setDescription ("Library Manager Files");
    fileChooser.setFileFilter (filter);
    int result = fileChooser.showSaveDialog (this);
    if (result == JFileChooser.APPROVE_OPTION) 
    {
      fileName = addExtension(fileChooser.getSelectedFile().getName());
      if (!(fileName == null || fileName.equals ("")))
      {
        try
        {
          BufferedReader input = new BufferedReader (new FileReader ("Documents/" + fileName));
          optionPaneResult = JOptionPane.showConfirmDialog (this, "Would you like to overwite this file?", "Overwrite?", JOptionPane.YES_NO_OPTION);
          if (optionPaneResult == 0)
            save (fileName);
          
          else if (optionPaneResult == 1)
            JOptionPane.showMessageDialog(this,"No changes will be made.","No Changes",JOptionPane.WARNING_MESSAGE);
          
        }
        catch (IOException e)
        {
          save (fileName);
        }
      }
    }
  }
  
  /**
   * This method adds the ".lmf" extension
   * 
   * @param fileName the original filename to be added onto.
   * @return the filename with the extension added.
   */ 
  public String addExtension (String fileName)
  {
    for (int x = 0; x < fileName.length ()-1; x++)
    {
      if (fileName.charAt (x) == '.')
        fileName = fileName.substring (0, x);
    }
    return (fileName + ".lmf");
  }
  
  /**
   * This method opens a user chosen file.
   * 
   * @param headerLine this is the first line of all lmf files.
   * @param titleLine this stores the book title taken from the file.
   * @param authorLine this stores the author name taken from the file.
   * @param genreLine this stores the genre taken from the file.
   * @param locationLine this stores the location taken from the file.
   * @param borrowDLine this stores the borrow date taken from the file.
   * @param returnDLine this stores the return date taken from the file.
   * @param fileChooser instance of the FileChooser class.
   * @param recAmount this stores the amount of records to be created.
   * @param filter instance of the ExampleFileFilter class.
   * @param openDialog opens the dialogue to allow the user to pick a file.
   * @param input stores the input from reading the file.
   * @param e catches IOException errors.
   * @e IOException catches IO errors.
   */ 
  public void openFile ()
  {
    String headerLine = "";
    String titleLine;
    String authorLine;
    String genreLine;
    String locationLine;
    String borrowDLine;
    String returnDLine;
    JFileChooser fileChooser = new JFileChooser (".\\Documents");
    int recAmount;
    ExampleFileFilter filter = new ExampleFileFilter ( );
    filter.addExtension ("lmf");
    filter.setDescription ("Library Manager Files");
    fileChooser.setFileFilter (filter);
    
    int openDialog = fileChooser.showOpenDialog (this);
    
    if (openDialog != JFileChooser.APPROVE_OPTION) 
      return;
    
    theFile = fileChooser.getSelectedFile();
    BufferedReader input;
    
    try
    {
      input = new BufferedReader (new FileReader (theFile));
      headerLine = input.readLine ();
    }
    catch (Exception e){}
    
    if (theFile == null || theFile.getName ().equals ("") || theFile.getName ().length () > 255)
    {
      JOptionPane.showMessageDialog (this, "Please pick a file. Go to File > Open.", "No File Chosen", JOptionPane.ERROR_MESSAGE);
      theFile = null;
    }
    
    else if (!(filter.getExtension (theFile)).equals ("lmf"))
    {
      JOptionPane.showMessageDialog (this, "Wrong Extension", "Invalid Extension", JOptionPane.ERROR_MESSAGE);
      theFile = null;
    }
    else if (!headerLine.equals ("Storing the library data like a boss."))
    {
      JOptionPane.showMessageDialog (this, "Incorrect Header.", "Invalid Header", JOptionPane.ERROR_MESSAGE);
      theFile = null;
    }
    else
    {
      try 
      {
        input = new BufferedReader (new FileReader (theFile));
        input.readLine ();
        recAmount = Integer.parseInt(input.readLine());
        currentRec = 0;
        book.clear ();
        BookRecord.recNum = 0;
        
        for (int i = 0 ; i < recAmount ; i++)
        { 
          titleLine = input.readLine();
          authorLine = input.readLine();
          genreLine = input.readLine();
          locationLine = input.readLine();
          borrowDLine = input.readLine ();
          returnDLine = input.readLine ();
          if (titleLine.equals ("null"))
            titleLine = "";
          if (authorLine.equals ("null"))
            authorLine = "";
          if (genreLine.equals ("null"))
            genreLine = "";
          if (locationLine.equals ("null"))
            locationLine = "";
          if (borrowDLine.equals ("null"))
            borrowDLine = "";
          if (returnDLine.equals ("null"))
            returnDLine = "";
          book.add(new BookRecord());
          book.get(i).setTitle(titleLine);
          book.get(i).setAuthor(authorLine);
          book.get(i).setGenre(genreLine);
          book.get(i).setLocation(locationLine);
          book.get(i).setBorrowDate(borrowDLine);
          book.get(i).setReturnDate(returnDLine);
        }
        BookRecord.recNum = recAmount;
        input.close();
        currentRec=0;
        recSaved=true;
        fileSaved = true;
        fileName = theFile.getName ();
        updateDisplay();        
      }
      catch (Exception e) 
      {
        JOptionPane.showMessageDialog (this, "Error occured while opening file.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  } 
  
  
  /**
   * This method asks to save changes if the database is not saved.
   */ 
  public void saveChecker ()
  {
    if (!fileSaved)
    {
      optionPaneResult = JOptionPane.showConfirmDialog (this, "Would you like to save changes?", "Save Changes?", JOptionPane.YES_NO_OPTION);
      if (optionPaneResult == 0)
      {
        save (fileName);
      }
    }
  }
  
  /**
   * Sets the name of the columns.
   */
  private void createColumns ()
  {
    columnNames = new Object [7];
    
    columnNames [0] = "#";
    columnNames [1] = "Book Title";
    columnNames [2] = "Author Name";
    columnNames [3] = "Genre";
    columnNames [4] = "Location";
    columnNames [5] = "Borrow Date";
    columnNames [6] = "Return Date";
  }
  
  /**
   * This metohd inputs the data onto the table.
   */
  public void createData() 
  {
    dataValues = new String[BookRecord.recNum][7];
    if (searchMode)
    {
      for (int i = 0; i < BookRecord.recNum; i++) 
      {
        if (order[i] != -1)
        {
          dataValues[i][0] = "" + (order[i] + 1);
          dataValues[i][1] = "" + (book.get(order[i]).getTitle());
          dataValues[i][2] = "" + (book.get(order[i]).getAuthor());
          dataValues[i][3] = "" + (book.get(order[i]).getGenre());
          dataValues[i][4] = "" + (book.get(order[i]).getLocation());
          dataValues[i][5] = "" + (book.get(order[i]).getBorrowDate());
          dataValues[i][6] = "" + (book.get(order[i]).getReturnDate());
          amountFound ++;
        }
      }
    }
    else
    {
      sorter();
      for (int i = 0; i < BookRecord.recNum; i++) 
      {
        dataValues[i][0] = "" + (order[i] + 1);
        dataValues[i][1] = "" + (book.get(order[i]).getTitle());
        dataValues[i][2] = "" + (book.get(order[i]).getAuthor());
        dataValues[i][3] = "" + (book.get(order[i]).getGenre());
        dataValues[i][4] = "" + (book.get(order[i]).getLocation());
        dataValues[i][5] = "" + (book.get(order[i]).getBorrowDate());
        dataValues[i][6] = "" + (book.get(order[i]).getReturnDate());
      }
    } 
    currentRec = 0;
  }
  
  /**
   * Gets the data from the chart and stores it back in the arraylist.
   * 
   * @param tempBook the arraylist to be returned
   * @param tableModel points to the TableModel class
   * @param order the order of indices
   */
  public void getChartData ()
  {
      ArrayList <BookRecord> tempBook = new ArrayList<BookRecord>();
      TableModel tableModel = myTable.getModel ();
      int [] order = new int [tableModel.getRowCount ()];
      
      for (int x = 0; x < tableModel.getRowCount () ; x++)
      {
        tempBook.add (new BookRecord ());
        order [x] = Integer.parseInt ((String)(tableModel.getValueAt (x, 0))) - 1;
      }
      
      for (int x = 0; x < tableModel.getRowCount () ; x++)
      {
        if (!(myTable.getValueAt (x,1).toString().equals ("")))
        {
          tempBook.get(order [x]).setTitle (tableModel.getValueAt (x , 1).toString ());
          
          tempBook.get(order [x]).setAuthor (tableModel.getValueAt (x , 2).toString ());
          tempBook.get(order [x]).setGenre (book.get(order [x]).getGenre ());
          tempBook.get(order [x]).setLocation (tableModel.getValueAt (x , 4).toString ());
          if (DataCheck.checkDate ((String)(tableModel.getValueAt (x , 5))) == true && (String)(tableModel.getValueAt (x , 5)) != null)
          {
            tempBook.get(order [x]).setBorrowDate (tableModel.getValueAt (x , 5).toString ());
          }
          if (DataCheck.checkDate ((String)(tableModel.getValueAt (x , 6))) == true && (String)(tableModel.getValueAt (x , 6)) != null)
          {
            tempBook.get(order [x]).setReturnDate (tableModel.getValueAt (x , 6).toString ());
          }
        }
        else
        {
          JOptionPane.showMessageDialog(this,"The title for book #"+ (order[x]+1) +" is empty, no changes will be made to that book.","Empty Title",JOptionPane.WARNING_MESSAGE);
          tempBook.get(order [x]).setTitle (book.get(order [x]).getTitle ());        
          tempBook.get(order [x]).setAuthor (book.get(order [x]).getAuthor ());
          tempBook.get(order [x]).setGenre (book.get(order [x]).getGenre ());
          tempBook.get(order [x]).setLocation (book.get(order [x]).getLocation ());
          tempBook.get(order [x]).setBorrowDate (book.get(order [x]).getBorrowDate ());
          tempBook.get(order [x]).setReturnDate (book.get(order [x]).getReturnDate ());
        }
      }
      
      BookRecord.recNum = tableModel.getRowCount ();
      book = tempBook;
  }
  
  /**
   * This method sorts the fields.
   * 
   * @param original Holds the unsorted array.
   */ 
  public void sorter ()
  {
    order = new int [BookRecord.recNum];
    for (int i = 0; i < BookRecord.recNum; i++)
      order [i]=i;
    if (sortWhichField != 0)
    {
      String [] original = new String [BookRecord.recNum];
      
      for (int i = 0; i < BookRecord.recNum; i++)
      {
        if (sortWhichField == 1)
        {
          if ((book.get(i).getTitle()).equals (""))
            original [i] = (" ");
          else
            original [i] = (book.get(i).getTitle()).toUpperCase();
          
        }
        else if (sortWhichField == 2)
        {
          if ((book.get(i).getAuthor()).equals (""))
            original [i] = " ";
          else
            original [i] = (book.get(i).getAuthor());
        }
        else if (sortWhichField == 3)
        {
          if ((book.get(i).getGenre()).equals (""))
            original [i] = (" ");
          else
            original [i] = (book.get(i).getGenre()).toUpperCase();
        }
        else if (sortWhichField == 4)
        {
          if ((book.get(i).getLocation()).equals (""))
            original [i] = (" ");
          else
            original [i] = (book.get(i).getLocation()).toUpperCase();
        }
        else if (sortWhichField == 5)
        {
          if ((book.get(i).getBorrowDate()).equals (""))
            original [i] = (" ");
          else
            original [i] = (book.get(i).getBorrowDate()).toUpperCase();
        }
        else if (sortWhichField == 4)
        {
          if ((book.get(i).getReturnDate()).equals (""))
            original [i] = (" ");
          else
            original [i] = (book.get(i).getReturnDate()).toUpperCase();
        }
      } 
      order = s.bubbleSort (original);
    }
  }
  /**
   * This method searches for a piece of text
   * 
   * @param original Holds the unsorted array.
   */ 
  public void searcher ()
  {
    String [] original = new String [BookRecord.recNum];
    
    for (int i = 0; i < BookRecord.recNum; i++)
    {
      if (searchWhichField == 1)
        original [i] = (book.get(i).getTitle()).toUpperCase();
      else if (searchWhichField == 2)
        original [i] = (book.get(i).getAuthor()).toUpperCase();
      else if (searchWhichField == 3)
        original [i] = (book.get(i).getGenre()).toUpperCase();
      else if (searchWhichField == 4)
        original [i] = (book.get(i).getLocation()).toUpperCase();
      else if (searchWhichField == 5)
        original [i] = (book.get(i).getBorrowDate()).toUpperCase();
      else if (searchWhichField == 6)
        original [i] = (book.get(i).getReturnDate()).toUpperCase();
      
      if (partialOrWhole != 2)
      {
        original [i] = original [i] + "                                            ";
        original [i] = original[i].substring (0,searchText.length());
      }
    }
    
    order = s.sequentialSearch (original,searchText);
    
    sorted = false;
    searchMode = true;
    tableView();
    
    if (amountFound > 1)
      JOptionPane.showMessageDialog(this,amountFound + " matches were found.");
    else if (amountFound == 1)
      JOptionPane.showMessageDialog(this,"1 match was found.");
    else
      JOptionPane.showMessageDialog(this,"No matches were found.");
    amountFound = 0;
  }  
  
  /**
   * Sends the data to the BarGraph class.
   * 
   * @param b points to the BarGraph class.
   */
  public void graphView (BarGraph b)
  {
    b.countValues (book);
  }
  
  /**
   * Runs the appropriate method according to the action of the user input.
   * 
   * @param ae points to the ActionEvent class.
   * @param cmd (String) gets the action command.
   */
  public void actionPerformed (ActionEvent e)
  {
    String cmd = e.getActionCommand ();
    
    if (PREVIOUS.equals (cmd))
    { 
      prevRec ();
    }
    else if (NEXT.equals (cmd))
    { 
      nextRec ();
    }
    else if (NEW.equals (cmd))
    { 
      newRec ();
    }
    else if (SAVE.equals (cmd))
    { 
      saveRec (titleField.getText (), authorField.getText (), (String)(genreBox.getSelectedItem ()), locationField.getText (), 
               borrowField.getText (), returnField.getText ());
    }
    else if (DELETE.equals (cmd))
    {
      deleteRec ();
    }
    else if (BORROW.equals (cmd))
    {
      borrowBook ();
    }
    else if (RETURN.equals (cmd))
    {
      returnBook ();
    }
    this.invalidate();
    this.validate();
    this.repaint();
  }
}
