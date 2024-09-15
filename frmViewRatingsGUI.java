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

public class frmViewRatingsGUI extends frmValidityGUI
{
  JLabel lblTitle;
  JButton btnBack;
  JButton btnViewRatings;
  JButton btnSearch;
  JTextField txtBookName;
  JTextField txtAuthor;
  JTextField txtViewSearch;
  JTextArea txtViewRatedBooks;
  JTextArea txtResults;
  JLabel lblSearchGuide; 
  JLabel lblBookName;
  JLabel lblAuthor;

  // string variable stores the account name of hte active user
  String strActiveUser;

  // make a copy of arrBooks (using parent class)
  // used for searching
  ArrayList<Book> arrBookCopy = makeArrBookCopy();

  public frmViewRatingsGUI(String strActiveUser)
  {
    this.strActiveUser = strActiveUser;
    setLayout(null); 
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);

    lblTitle = new JLabel("<html><font size='7'>All your ratings!");
    lblTitle.setSize(400, 100);
    lblTitle.setLocation(100, 25 - 40);
    lblTitle.setForeground(Color.blue);
    add(lblTitle);

    btnBack = new JButton("Back");
    btnBack.setLocation(10, 430);
    btnBack.setSize(70, 30);
    btnBack.setActionCommand("Back");
    btnBack.addActionListener(this);
    add(btnBack);


    btnViewRatings = new JButton("View");
    btnViewRatings.setLocation(220, 250 - 40);
    btnViewRatings.setSize(70, 30);
    btnViewRatings.setActionCommand("view");
    btnViewRatings.addActionListener(this);
    add(btnViewRatings);

    txtViewRatedBooks = new JTextArea();
    txtViewRatedBooks.setSize(250, 30);
    txtViewRatedBooks.setLocation(125, 300 - 40);
    add(txtViewRatedBooks);

    txtResults = new JTextArea();
    txtResults.setSize(250, 75);
    txtResults.setLocation(125, 346);
    add(txtResults);

    txtViewSearch = new JTextField();
    txtViewSearch.setSize(250, 15);
    txtViewSearch.setLocation(125, 330);
    add(txtViewSearch);

    btnSearch = new JButton("<html><font size='2'>Search");
    btnSearch.setLocation(125 + 250, 330);
    btnSearch.setSize(80, 15);
    btnSearch.setActionCommand("search");
    btnSearch.addActionListener(this);
    add(btnSearch);

    txtBookName = new JTextField();
    txtBookName.setSize(120, 25);
    txtBookName.setLocation(190, 150 - 40);
    add(txtBookName);

    txtAuthor = new JTextField();
    txtAuthor.setSize(120, 25);
    txtAuthor.setLocation(190, 200 - 40);
    add(txtAuthor);


    lblBookName = new JLabel("<html><font size='4'>Book Name:");
    lblBookName.setSize(125, 25);
    lblBookName.setLocation(100, 150 - 40);
    add(lblBookName);

    lblAuthor = new JLabel("<html><font size='4'>Author Name:");
    lblAuthor.setSize(125, 25);
    lblAuthor.setLocation(85, 200 - 40);
    add(lblAuthor);

    lblSearchGuide = new JLabel("<html><font size='2'>Forgot the author? Get the author by searching there book name!");
    lblSearchGuide.setSize(300, 50);
    lblSearchGuide.setLocation(125, 410);
    add(lblSearchGuide);
  }

  /*
  @param intIndex is an integer
  return array list of all the indexes that match with the search result
  */
  public ArrayList<Integer> findExtraIdenticalBooks(int intIndex, String strString)
  {
    ArrayList<Integer> extraBooksFound = new ArrayList<Integer>();
    // if no index is found then just there are not extra indexes
    if (intIndex == -1)
    {
      return extraBooksFound;
    }
    // since the arrBooksCopied is already sorted the rest of the indentical results
    // should be from the left and right of intIndex

    // checking left side
    // stores the index for the left side of intIndex
    int intIndexLeft = intIndex - 1;

    // if the book is at the beginning of the array, getting the next index will cause an error
    try
    {
      // go through arrBooksCopy until the same book name is not found from the left side 
      while (strString.equals(arrBookCopy.get(intIndexLeft).getBookName()))
      {
        // add the book and decrease the index to look at
        extraBooksFound.add(intIndexLeft);
        intIndexLeft--;
      }
    }
    catch(Exception e1){}
    // checking left right
    // stores the index for the left side of intIndex
    int intIndexRight = intIndex + 1;

    // if the book is at the end of the array, getting the next index will cause an error
    try
    {
      // go through arrBooksCopy until the same book name is not found from the left side 
      while (strString.equals(arrBookCopy.get(intIndexRight).getBookName()))
      {
        // add the book and increase the index to look at
        extraBooksFound.add(intIndexRight);
        intIndexRight++;
      }
    }
    catch(Exception e1){}

    return extraBooksFound;
  }

  /*
  sorts the copied version of arrBooks by book name
  */
  public void selectionSortBookNames()
  {
    // stores the length of arrBooks
    int arrLength = arrBookCopy.size();
    // using selection sort to sort arrBookCopy
    for (int i = 0; i < arrLength - 1; i++)
    {
      for (int j = i + 1; j <  arrLength; j++)
      {
        // swaps if ith value of arrBookCopy is bigger alphabetically than jth
        if (arrBookCopy.get(i).getBookName().compareTo(arrBookCopy.get(j).getBookName()) > 0)
        {
          // stores temporary book in order to swap
          Book BookTemp = arrBookCopy.get(i);
          arrBookCopy.set(i, arrBookCopy.get(j));
          arrBookCopy.set(j, BookTemp);
        }
      }
    }
  }
  /*
  @param strSearch is a string
  @param intMin is a integer 
  @param intMax is a integer
  return the index in which strSearch is found in arrBooksCopy (recursively)
  */
 public int binarySearchBook(String strSearch, int intMin, int intMax)
  {
    // stores the middle value of the index we are search for
    int intMiddle = (intMin + intMax) / 2;
    // base case (the book was not found so return -1)
    if (intMin > intMax)
    {
      return -1;
    }
    // if the book is found at intMiddle
    else if (arrBookCopy.get(intMiddle).getBookName().compareTo(strSearch) == 0)
    {
      return intMiddle;
    }
    // the target is bigger (alphabetically) than the intMiddle value 
    else if (arrBookCopy.get(intMiddle).getBookName().compareTo(strSearch) < 0)
    {
      intMin = intMiddle + 1;
      return binarySearchBook(strSearch, intMin,intMax);
    }
    // the target is smaller (alphabetically) than the intMiddle value 
    else
    {
      intMax = intMiddle - 1; 
      return binarySearchBook(strSearch, intMin,intMax);
    }
  }


  /*
  @param strBookName is the book name string
  @param strAuthorName is the author name string
  @param intBookIndex is a integer
  displays the book and rating
  */
  public void displayRating(String strBookName, String strAuthorName, int intBookIndex)
  {

    // first, get the rating using the index (intBookIndex)
    // stores the rating of the book the user selected
    int intRating = frmLoadDataGUI.memberMap.get(strActiveUser).getRatings().get(intBookIndex);
    // check if the user actually rated the book or not
    if (intRating == 0)
    {
      txtViewRatedBooks.setText("You have not rated this book!");
    }
    else 
    {
      txtViewRatedBooks.setText("You gave " + strBookName + " a " + intRating + "!");
    }

  }

  /*
  @param strBookName arrResultsIndexes stores integers that store the index of recommended books
  returns a formmated string of the recommended books to display to the user
  */
  public String displayResults(ArrayList <Integer> arrResultsIndexes)
  {
    // stores the display text for the user
    String strDisplayText = "";
    // go through every integer in arrResultsIndexes 
    for (int intIndex : arrResultsIndexes)
    {
      // add the recommended book to the display text
      strDisplayText += arrBookCopy.get(intIndex).getAuthor() + ", " + arrBookCopy.get(intIndex).getBookName() + "\n" + "ISBN: " + arrBookCopy.get(intIndex).getISBN() + "\n";
    }
    return strDisplayText;
  }

  public void actionPerformed(ActionEvent e)
  {
    // when the user clicks the back button
    if (e.getActionCommand().equals("Back"))
    {
      // swtich to the frmMainPageGUI GUI
      frmMainPageGUI frmMainPageGUI = new frmMainPageGUI(strActiveUser);
      frmMainPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmMainPageGUI.setSize(500, 500);
      frmMainPageGUI.setVisible((true));
      this.dispose();
    }
    // when the user clicks on the view button
    else if (e.getActionCommand().equals("view"))
    {
      // check if book name and author name is vaild inputs
      // anything but -1 means its vaild
      if(viewRatingsVaild(txtBookName.getText(), txtAuthor.getText()) != -1)
      {
        // stores the book index of txtBookName in the array list arrBooks
        int intBookIndex = viewRatingsVaild(txtBookName.getText(), txtAuthor.getText());
        // displays rating to the screen
        displayRating(txtBookName.getText(), txtAuthor.getText(), intBookIndex); 
      }
    }
    // when the user clicks on the search button
    else if (e.getActionCommand().equals("search"))
    {
      // check if the search bar is empty, if so, prompt user error message
      if(txtViewSearch.getText().equals(""))
      {
        JOptionPane.showMessageDialog(null, "Nothing was entered!");
      }
      else
      {
        // sort the copy (compare the booknames and swap object book)
        selectionSortBookNames();
        // stores the index in which the search is find in arrBooks sorted
        int identicalBooksIndex = binarySearchBook(txtViewSearch.getText(), 0, arrBookCopy.size() - 1);

        // but, there are books that have the same name
        // so we also need to find those and display them

        // checks if a book is matched with user search input 
        if (identicalBooksIndex == -1)
        {
          txtResults.setText("No results, must put book name exactly");
        }
        else
        {
          // stores all the index of the book that are identical to the search
          ArrayList<Integer> allIdenticalBooksIndexes = findExtraIdenticalBooks(identicalBooksIndex, txtViewSearch.getText());
          // add the index found in binary recursion to the allIdenticalBooksIndexes
          allIdenticalBooksIndexes.add(identicalBooksIndex);
          // display the text using allIdenticalBooksIndexes
          txtResults.setText(displayResults(allIdenticalBooksIndexes));
        }        
      }
    }
  }
}
