/* 
Author: Abinash Nagendran
Date: 15/08/2024
ICS4U Culminating Task 
*/

public class Book
{
  // stores the author number
  private String strAuthor;
  // stores the book name 
  private String strBookName;
  // sotores the publish year of the book
  private int intPublishYear;
  // stores the ISBN of te book
  private int intISBN;


  public Book(String strAuthor, String strBookName, String strPublishYear, int intISBN)
  {
    this.strAuthor = strAuthor;
    this.strBookName = strBookName;
    this.intPublishYear = Integer.parseInt(strPublishYear);
    this.intISBN = intISBN;
  }
  public Book(String strAuthor, String strBookName, int intISBN)
  {
    this.strAuthor = strAuthor;
    this.strBookName = strBookName;
    this.intPublishYear = 0;
    this.intISBN = intISBN;
  }

  /**
  returns the author name
  **/
  public String getAuthor()
  {
    return strAuthor;
  }
  /**
  returns the book name
  **/
  public String getBookName()
  {
    return strBookName; 
  }
  /**
  returns the publish year
  **/
  public int getPublishYear()
  {
    if (intPublishYear == 0)
    {
      return 0;
    }
    return intPublishYear;
  }
  /**
  returns the 10 digit ISBN as string
  **/
  public String getISBN()
  {
    // this string variable converts the integer ISBN number into a string 
    String strISBN = "" + intISBN;
    // adds 0 before the string until it reach 10 digits
    while (strISBN.length() != 10)
    {
      strISBN = "0" + strISBN;
    }
    return strISBN;
  }
}
