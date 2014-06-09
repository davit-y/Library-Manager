/**
 * The purpose of this is to check the data inputted.
 * <p>
 * @author David Yeghshatan 
 * @version Version 4 April 21
 */

public class DataCheck
{
  /**
   * This method checks the Author and Title and Location to make sure all letters are alpha numeric.
   * @param inputted String The string to be checked.
   */ 
  public static boolean checkName (String inputted)
  {
    if (inputted == null || inputted.equals(""))
      return true;
    
    for (int x = 0 ; x < inputted.length() ; x++)
    {
      if (!((inputted.charAt (x) >= 48 && inputted.charAt (x) <= 57) || (inputted.charAt (x) >= 64 && inputted.charAt (x) <= 90) || (inputted.charAt (x) >= 97 && inputted.charAt(x) <= 122) || inputted.charAt(x) == 95 || inputted.charAt(x) == 46 || inputted.charAt(x) == 45))
        return false;
    }
    return true;
  }
  
  /**
   * This method checks the email.
   * @param emailToCheck String The email to be checked.
   * @param atAmount int The amount of at signs.
   * @param atPlacement int The location of the at sign.
   */ 
  public static boolean checkDate (String inputted)
  {
    if (inputted == null || inputted.equals(""))
      return true;
    if (inputted.length()!=10 || inputted.charAt (2) != 47 || inputted.charAt (5) != 47)
      return false;
    
    for (int x = 0 ; x <= 1 ; x++)
    {
      if (!(inputted.charAt (x) >= 48 && inputted.charAt (x) <= 57))
        return false;
      if (!(inputted.charAt (x+3) >= 48 && inputted.charAt (x+3) <= 57))
        return false;
      if (!(inputted.charAt (x+6) >= 48 && inputted.charAt (x+6) <= 57))
        return false;
      if (!(inputted.charAt (x+7) >= 48 && inputted.charAt (x+8) <= 57))
        return false;
    }
    
    int day = (Character.getNumericValue(inputted.charAt (0))*10 + Character.getNumericValue(inputted.charAt (1)));
    int month = (Character.getNumericValue(inputted.charAt (3))*10 + Character.getNumericValue(inputted.charAt (4)));
    int year = (Character.getNumericValue(inputted.charAt (6))*1000 + Character.getNumericValue(inputted.charAt (7))*100 + Character.getNumericValue(inputted.charAt (8))*10 + Character.getNumericValue(inputted.charAt(9)));
    
    if (day > 31 || month > 12 || year < 2014)
      return false;
    
    return true;
  }
}
