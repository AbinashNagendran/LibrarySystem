/* 
Author: Abinash Nagendran
Date: 15/08/2024
ICS4U Culminating Task 
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class frmValidityGUI extends JFrame implements ActionListener
{
  public frmValidityGUI()
  {

  }

  /**
  @ param strUsedBookFile is a string
  @ param strUsedRatingFile is a string
  returns (true/false) if the file name is vaild or not 
  **/
  public boolean isFileVaild(String strUsedBookFile, String strUsedRatingFile)
  {
    // if the fields is empty
    if(strUsedBookFile.equals("") || strUsedRatingFile.equals(""))
    {
      JOptionPane.showMessageDialog(null, "No file(s) was entered");
      return false; 
    }
    // checking if file name was actually a given file
    try
    {
      // this will cause an error if file not in directory
      FileReader fr = new FileReader(strUsedBookFile);
      FileReader fr2 = new FileReader(strUsedRatingFile);
    }
    catch(IOException e)
    {
      JOptionPane.showMessageDialog(null, "File Name was not found, check spellling!");
      return false;
    }

    return true;
  }

  /**
  @ param strBookName is the book name as a string
  @ param strBookRating is a book rating as a string
  @ param strAuthor is a author name as a string
  returns the index of which book is being rated and checks if the rating and author is vaild
  -1 is returned if user input was not vaild or strBookName was not found in arrBooks
  **/
  public int vaildUserRating(String strBookName, String strBookRating, String strAuthor)
  {
    // integer array stores all the vaild ratings
    int [] arrVaildRatings = {-5, -3, 0, 3, 5};
    // first, check of the book name, book rating, and book author fields are empty
    if (strBookName.equals(""))
    {
      JOptionPane.showMessageDialog(null, "A book name was not entered!");
      return -1;
    }
    else if (strBookRating.equals(""))
    {
      JOptionPane.showMessageDialog(null, "The rating was not entered");
      return -1;
    }
    else if (strAuthor.equals(""))
    {
      JOptionPane.showMessageDialog(null, "Author name was not entered!");
      return -1;
    }
    // check if the rating is one of the vaild ratings (-5, -3, 0...)
    try
    {
      // linear search to find if the user rating in arrVaildRatings
      for (int i = 0; i < 5; i++)
      {
        if (arrVaildRatings[i] == Integer.parseInt(strBookRating))
        {
          // check if the strBookName is in arrBooks

          // integer variable stores the length of arrBooks
          int intArrBooksLength = frmLoadDataGUI.arrBooks.size();
          // goes through every book name in arrBooks and checks if strBookName is equal
          for (int j = 0; j < intArrBooksLength; j++)
          {
            // check if book name equals in the arrBooks jth value
            if(strBookName.equals(frmLoadDataGUI.arrBooks.get(j).getBookName()))
            {
              // check if author name equals in the arrBooks jth value
              if(strAuthor.equals(frmLoadDataGUI.arrBooks.get(j).getAuthor()))
              {
                return j;
              }
            }
          }
          JOptionPane.showMessageDialog(null, "The book and author does not match! Check Spelling!");
          return -1;
        }
      }
      JOptionPane.showMessageDialog(null, "The rating is not in the vaild range! (-5 , -3, 0, 3, or 5)");
      return -1;
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null, "The rating is not a integer! Must be -5 , -3, 0, 3, or 5");
    }
    return -1;    
  }
  /**
  @ param strBookName is the book name as a string
  @ param strAuthor is the book author as a string
  @ param strYear is the publish year as a string
  returns (true/false) if the book name, author, publish year are vaild 
  **/
  public boolean vaildBook(String strBookName, String strAuthor, String strYear)
  {
    try
    {
      // checks if the book name, author publish year are empty first
      if(strBookName.equals(""))
      {
        JOptionPane.showMessageDialog(null, "The book name was not entered!");
        return false;
      }
      else if(strAuthor.equals(""))
      {
        JOptionPane.showMessageDialog(null, "The author name was not entered!");
        return false;
      }
      else if(strYear.equals(""))
      {
        JOptionPane.showMessageDialog(null, "The publish year was not entered!");
        return false;
      }
      // check if publish is a vaild year (positive integer)
      else if (!(Integer.parseInt(strYear) > -1))
      {
        JOptionPane.showMessageDialog(null, "The year is not positive!");
        return false;
      }

      // the book could already exist
      // check if strBookName is in arrBooks
      for (Book singleBook : frmLoadDataGUI.arrBooks)
      {
        // check if singleBook book name is same as strBookName
        if (strBookName.equals(singleBook.getBookName()))
        {
          // check if singleBook author name is same as strAuthor
          if(strAuthor.equals(singleBook.getAuthor()))
          {
            JOptionPane.showMessageDialog(null, "The book already exist");
            return false;
          }
        }
      }
    }
    catch(Exception e)
    {
      JOptionPane.showMessageDialog(null, "The year is not an integer!");
    }
    JOptionPane.showMessageDialog(null, "Book is sucessfully added!");
    return true;
  }

  /**
  @ param strBookName is the book name as a string
  @ param strAuthor is the book author as a string
  returns the index in which the book is stored in the arraylist
  -1 if book is not found or user related issues
  **/
  public int viewRatingsVaild(String strBookName, String strAuthor)
  {
    // checks if the book name and author name is empty
    // if so prompt error message
    if(strBookName.equals(""))
    {
      JOptionPane.showMessageDialog(null, "The book name was not entered!");
      return -1;
    }
    else if(strAuthor.equals(""))
    {
      JOptionPane.showMessageDialog(null, "The author name was not entered!");
      return -1;
    }
    // stores the index in which the book is stored
    int intIndex = 0;
    // check if the book name is in the arrBooks
    for (Book singleBook : frmLoadDataGUI.arrBooks)
    {
      if (strBookName.equals(singleBook.getBookName()))
      {
        // now check for if author matches
        if(strAuthor.equals(singleBook.getAuthor()))
        {
          return intIndex;
        }
      }
      // no book is found, increase the index
      intIndex++;
    }
    JOptionPane.showMessageDialog(null, "Information entered is incorrect!");
    return -1;
  }

  /**
  returns a copy of arrBooks 
  **/
  public ArrayList<Book> makeArrBookCopy()
  {
    // stores a copy of arrBooks
    ArrayList<Book> arrBookCopy = new  ArrayList<Book>();
    // add arrBooks elements into arrBookCopy
    for (Book singleBook : frmLoadDataGUI.arrBooks)
    {
      arrBookCopy.add(singleBook);
    }
    return arrBookCopy;
  }
  /**
  @ param strRatingFileName is a string
  writes into the rating file 
  **/
  public static void editRatingFile(String strRatingFileName)
  {
    // string array list stores every account, name, and password for each user in the file
    ArrayList<String> arrMemberFileLines = new ArrayList<String>();
    // string array list stores every account ratings 
    ArrayList<String> arrRatingFileLines = new ArrayList<String>();
    try
    {
      FileReader fr = new FileReader(strRatingFileName);
      BufferedReader br = new BufferedReader(fr);
      // string variable that reads in line in the text file
      String strLine = br.readLine();
      // goes through every line in the rating file
      while(strLine != null)
      {
        // this string variable only stores the account name
        String strMemberAccountName = strLine.split(",")[0];

        // this string varaibles stores the account, name, and password
        // if the file is already edited, there will be commas
        String strMemberInfo = strLine.split(",")[0];

        // we also need to keep a copy of all the ratings while printing
        arrRatingFileLines.add(frmLoadDataGUI.memberMap.get(strMemberAccountName).getRatingsAsString()); 

        // adding the name and password to the line
        strMemberInfo = strMemberInfo + "," + frmLoadDataGUI.memberMap.get(strMemberAccountName).getName();
        strMemberInfo = strMemberInfo + "," + frmLoadDataGUI.memberMap.get(strMemberAccountName).getPassword();
        // adding the line to the array to get printed into the file
        arrMemberFileLines.add(strMemberInfo);


        // reading the next user (skipping the ratings)
        strLine = br.readLine();
        strLine = br.readLine();
      }
      FileWriter fr2 = new FileWriter(strRatingFileName);
      PrintWriter pr = new PrintWriter(fr2);
      // print every index of arrRatingsFileLines into the text file
      for (int i = 0; i < arrMemberFileLines.size(); i++)
      {
        pr.println(arrMemberFileLines.get(i));
        pr.println(arrRatingFileLines.get(i));
      }
      fr2.close();
      pr.close();
    }
    catch(IOException e){}
  }

  /**
  @param strAccount is a string
  @param strName is a string
  @param strPassword is a string
  returns (true/false) if the file name is vaild or not 
  **/
  public boolean accountNameVaild(String strAccount, String strName, String strPassword)
  {
    // first check for if account, name and password fields have some text inside of them 
    // if not prompt error
    if (strAccount.equals(""))
    {
      JOptionPane.showMessageDialog(null, "No account name was entered!");
      return false;
    }
    else if (strName.equals(""))
    {
      JOptionPane.showMessageDialog(null, "No name was entered!");
      return false;
    }
    else if (strPassword.equals(""))
    {
      JOptionPane.showMessageDialog(null, "No password was given was entered!");
      return false;
    }
    // check if the username is available 
    else if (frmLoadDataGUI.memberMap.containsKey(strAccount))
    {
      JOptionPane.showMessageDialog(null, "Account name not available");
      return false;
    }
    return true;
  }

  public void actionPerformed(ActionEvent e)
  {


  }
} 
