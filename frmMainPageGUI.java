/* 
Author: Abinash Nagendran
Date: 15/08/2024
ICS4U Culminating Task 
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class frmMainPageGUI extends frmValidityGUI
{
  JLabel lblTitle;
  JLabel lblChangePassword;
  JLabel lblAddABook; 
  JLabel lblRateABook; 
  JLabel lblBookName; 
  JLabel lblUserRating; 
  JLabel lblAuthor; 
  JButton btnAddingBook;
  JButton btnLogOut;
  JButton btnRateABook;
  JButton btnRecommendBooks;
  JButton btnViewRaitngs;
  JButton btnChangePassword;
  JTextField txtBook;
  JTextField txtBookRating;
  JTextField txtAuthor;
  JTextField txtPassword;
  JTextArea txtRecommendedBooks;

  // string variable stores the account name of the active user
  String strActiveUser;
  public frmMainPageGUI(String strActiveUser)
  {
    this.strActiveUser = strActiveUser;
    // string variable stores the name of the active user
    String strActiveName = frmLoadDataGUI.memberMap.get(strActiveUser).getName();
    setLayout(null); 
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);

    lblAddABook = new JLabel("<html><font size='3'>Make sure you know the book name, author, and publish year!");
    lblAddABook.setSize(245, 70);
    lblAddABook.setLocation(25, 390);
    lblAddABook.setForeground(Color.red);
    add(lblAddABook);

    lblChangePassword = new JLabel("<html><font size='3'>Change Password");
    lblChangePassword.setSize(180, 70);
    lblChangePassword.setLocation(55, 235);
    add(lblChangePassword);

    txtPassword = new JTextField();
    txtPassword.setSize(150, 20);
    txtPassword.setLocation(55, 280);
    add(txtPassword);

    btnChangePassword = new JButton("Change");
    btnChangePassword.setSize(150, 20);
    btnChangePassword.setLocation(55, 300);
    btnChangePassword.setActionCommand("change");
    btnChangePassword.addActionListener(this);
    add(btnChangePassword);


    lblTitle = new JLabel("<html><font size='7'>Hello " + strActiveName + "!");
    lblTitle.setSize(300, 100);
    lblTitle.setLocation(150, 25);
    lblTitle.setForeground(Color.blue);
    add(lblTitle);


    btnAddingBook = new JButton("Add a book!");
    btnAddingBook.setLocation(50, 365);;
    btnAddingBook.setSize(150, 30);
    btnAddingBook.setActionCommand("Add Book");
    btnAddingBook.addActionListener(this);
    add(btnAddingBook);

    btnLogOut = new JButton("Log Out");
    btnLogOut.setLocation(360, 10);;
    btnLogOut.setSize(120, 30);
    btnLogOut.setActionCommand("Log Out");
    btnLogOut.addActionListener(this);
    add(btnLogOut);

    btnRateABook = new JButton("Rate!");
    btnRateABook.setLocation(340, 410);;
    btnRateABook.setSize(100, 30);
    btnRateABook.setActionCommand("rate");
    btnRateABook.addActionListener(this);
    add(btnRateABook);

    lblRateABook = new JLabel("<html><font size='4'><font color = purple>Rate a Book!");
    lblRateABook.setSize(300, 100);
    lblRateABook.setLocation(340, 250 - 30);
    add(lblRateABook);

    txtBook = new JTextField();
    txtBook.setLocation(330, 280 + 50);
    txtBook.setSize(120, 25);
    add(txtBook);

    txtBookRating = new JTextField();
    txtBookRating.setLocation(330, 310 + 50);
    txtBookRating.setSize(120, 25);
    add(txtBookRating);

    txtAuthor = new JTextField();
    txtAuthor.setLocation(330, 250 + 50);
    txtAuthor.setSize(120, 25);
    add(txtAuthor);

      lblAuthor = new JLabel("<html><font size='2'><font color =purple>Author:");
    lblAuthor.setSize(100, 30);
    lblAuthor.setLocation(285, 300);
  add(lblAuthor);

    lblBookName = new JLabel("<html><font size='2'><font color =purple>Book Name:");
    lblBookName.setSize(100, 30);
    lblBookName.setLocation(260, 330);
    add(lblBookName);

    lblUserRating = new JLabel("<html><font size='2'><font color =purple>Give a Rating:");
    lblUserRating.setSize(100, 30);
    lblUserRating.setLocation(250, 360);
    add(lblUserRating);

    txtRecommendedBooks = new JTextArea();
    txtRecommendedBooks.setSize(300, 75);
    txtRecommendedBooks.setLocation(50 - 20, 150 + 20);
    add(txtRecommendedBooks);

    btnRecommendBooks = new JButton("Recommend me books!");
    btnRecommendBooks.setLocation(75 - 20, 105 + 20);;
    btnRecommendBooks.setSize(200, 30);
    btnRecommendBooks.setActionCommand("Recommend");
    btnRecommendBooks.addActionListener(this);
    add(btnRecommendBooks);

    btnViewRaitngs = new JButton("View all my ratings!");
    btnViewRaitngs.setLocation(295, 125);;
    btnViewRaitngs.setSize(180, 30);
    btnViewRaitngs.setActionCommand("view");
    btnViewRaitngs.addActionListener(this);
    add(btnViewRaitngs);

  }

  /**
  @ param strBookName is the book name as a string
  @ param intBookRating is a book rating as a integer
  @ param intRatingBookIndex is the book index as a integer
  changes the user rating on the book and writes it into the file
  **/
  public void addBookRating(String strBookName, int intBookRating, int intRatingBookIndex)
  {
    // set the book rating in the object
    frmLoadDataGUI.memberMap.get(strActiveUser).setBookRating(intBookRating, intRatingBookIndex);

    // change the rating in the file
    editRatingFile(frmLoadDataGUI.strUsedRatingFile);
    JOptionPane.showMessageDialog(null, "Rating added!");
  }

  /**
  returns the account name who is most similar to strActiveUser ratings
  **/
  public String userBestMatch()
  {
    // stores all the dot product of all members
    ArrayList<Integer> arrUsersDotProduct = new ArrayList<Integer>();

    // stores all users account names
    ArrayList<String> arrUsers = new ArrayList<String>();

    // go through every user and calculate the dot product
    for (String singleMember : frmLoadDataGUI.memberMap.keySet())
    {
      // skip over the active user
      if(!singleMember.equals(strActiveUser))
      {
        arrUsers.add(singleMember);
        arrUsersDotProduct.add(frmLoadDataGUI.memberMap.get(strActiveUser).calculateDot(frmLoadDataGUI.memberMap.get(singleMember)));
      }
    }
    // sort the arrays using selection sort

    // stores the length the of integer array 
    int arrLength = arrUsersDotProduct.size();
    for (int i = 0; i < arrLength - 1; i++)
    {
      for (int j = i + 1; j <  arrLength; j++)
      {
        // swap if ith dot product is bigger than jth dot product
        if (arrUsersDotProduct.get(i) > arrUsersDotProduct.get(j))
        {
          // swapping the integers in arrUsersDotProduct
          // stores the the ith value of the arrUsersDotProduct
          int intTemp = arrUsersDotProduct.get(i);
          arrUsersDotProduct.set(i, arrUsersDotProduct.get(j));
          arrUsersDotProduct.set(j, intTemp);

          // swapping the string in arrUsers
          // stores the the ith value of the arrUsers
          String strTemp = arrUsers.get(i);
          arrUsers.set(i, arrUsers.get(j));
          arrUsers.set(j, strTemp);
        }
      }
    }
    return arrUsers.get(arrUsers.size() - 1); 
  }
  /**
  @param arrActiveUserRatings is the ratings of the active user
  @param arrMemberRatings is the ratings of the member being compared 
  @param arrBookCopy is copy of arrBooks
  returns the number of books that could be recommended to arrActiveUserRatings
  **/
  public ArrayList<String> getRecommendBooks (ArrayList <Integer> arrActiveUserRatings, ArrayList <Integer> arrMemberRatings, ArrayList<Book> arrBookCopy) 
  {
    // stores the recommended books 
    ArrayList<String> arrRecommendedBooks = new ArrayList<String>();

    // sort arrMemberRatings, at the same time, sort arrActiveUserRatings and arrBookCopy in the same order 
    // this will insure we get the right book and right ratings to comapre
    // stores the length of arrMemberRatings
    int arrLength = arrMemberRatings.size();
    for (int i = 0; i < arrLength - 1; i++)
    {
      for (int j = i + 1; j <  arrLength; j++)
      {
        // if the ith integer rating is bigger than jth swap 
        if (arrMemberRatings.get(i) > arrMemberRatings.get(j))
        {
          // swapping the integers in arrMemberRatings

          // stores the the ith value of the arrMemberRatings
          int intTemp = arrMemberRatings.get(i);
          arrMemberRatings.set(i, arrMemberRatings.get(j));
          arrMemberRatings.set(j, intTemp);


          // swapping the integers in arrActiveUserRatings

          // stores the the ith value of the arrActiveUserRatings
          int intTemp2 = arrActiveUserRatings.get(i);
          arrActiveUserRatings.set(i, arrActiveUserRatings.get(j));
          arrActiveUserRatings.set(j, intTemp2);


          // swapping the books in arrBookCopy

          // stores the the ith value of the arrBookCopy
          Book tempBook = arrBookCopy.get(i);
          arrBookCopy.set(i, arrBookCopy.get(j));
          arrBookCopy.set(j, tempBook);
        }
      }
    }
    // go thourgh arrMemberRatings backwards and compare if active user did not read the same books as arrMemberRatings
    // if not then add that book to the recommended
    for (int i = arrLength - 1; i >= 0; i--)
    {
      // check if the active user hasn't read a book that the member has
      if (arrActiveUserRatings.get(i) == 0 && arrMemberRatings.get(i) != 0)
      {
        // checks if the user already has the max amount of recommendations (3)
        if (arrRecommendedBooks.size() != 3)
        {
          // add author and book name to arrRecommendedBooks
          arrRecommendedBooks.add(arrBookCopy.get(i).getAuthor() + ", " + arrBookCopy.get(i).getBookName());
        }
      }
    }
    return arrRecommendedBooks;
  }

  /**
  @ param strBestUser is the account name that best matched string
  returns the display text of the recommended books
  **/
  public String displayRecommendations(String strBestUser)
  {
    // stores the recommended books 
    String strRecommendedBooks = "";

    // stores a copy of arrBooks to use and sort
    ArrayList<Book> arrBooksCopy = makeArrBookCopy();


    // stores the ratings of the active user AS A COPY
    ArrayList<Integer> arrActiveUserRatingsCopy = new ArrayList<Integer>();

    // go through memberMap and copy the active user ratings
    for (int singleRating : frmLoadDataGUI.memberMap.get(strActiveUser).getRatings())
    {
      arrActiveUserRatingsCopy.add(singleRating);
    }

    //stores the ratings of strBestUser AS A COPY
    ArrayList<Integer> arrBestUserRatingsCopy = new ArrayList<Integer>();

    // go through memberMap and copy the active user ratings
    for (int singleRating : frmLoadDataGUI.memberMap.get(strBestUser).getRatings())
    {
      arrBestUserRatingsCopy.add(singleRating);
    }
    //stores recommeneded books 
    ArrayList<String> arrRecommendedBooks = getRecommendBooks(arrActiveUserRatingsCopy, arrBestUserRatingsCopy, arrBooksCopy);

    // get three books to display that active user hasn't read yet

    // stores the number of books that are recommended 
    int intNumOfRecomended = 0;
    // stores the index in which the books are displayed in order
    int intIndex = 0; 

    // go through arrRecommendedBooks and add each element to the string if possible
    if (arrRecommendedBooks.size() != 0)
    {
      // while the number of recommended books does not equal the number of books in the array, keep adding recommended books
      while(intNumOfRecomended != arrRecommendedBooks.size())
      {
        //add the recommended book to the array
        strRecommendedBooks += arrRecommendedBooks.get(intIndex) + "\n";
        // move index to the right and increase the number of recommended books
        intIndex++;
        intNumOfRecomended++;
      }
    }
    // when no books could be added
    else if (strRecommendedBooks.equals(""))
    {
      return "This is a very rare occurance \n that no books can be recommended!";
    }
    return strRecommendedBooks;
  }

  public void actionPerformed(ActionEvent e)
  {
    // when the user click the rate button
    if (e.getActionCommand().equals("rate"))
    {
      // stores the index of the book being rated in arrBooks
      // -1 means there invaild user format or book was not found
      int intRatingBookIndex = vaildUserRating(txtBook.getText(), txtBookRating.getText(), txtAuthor.getText());
      // checcks theres an actual index of the book -1 means not there
      if(intRatingBookIndex != -1)
      {
        addBookRating(txtBook.getText(), Integer.parseInt(txtBookRating.getText()), intRatingBookIndex);
      }
    }
    // when the user clicks add a book switch GUI to frmAddBook
    else if (e.getActionCommand().equals("Add Book"))
    {
      frmAddBookGUI frmAddBookGUI = new frmAddBookGUI(strActiveUser);
      frmAddBookGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmAddBookGUI.setSize(500, 500);
      frmAddBookGUI.setVisible((true));
      this.dispose();
    }

    // when the user clicks view ratings switch GUI frmViewRatings
    else if (e.getActionCommand().equals("view"))
    {
      frmViewRatingsGUI frmViewRatingsGUI = new frmViewRatingsGUI(strActiveUser);
      frmViewRatingsGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmViewRatingsGUI.setSize(500, 500);
      frmViewRatingsGUI.setVisible((true));
      this.dispose();
    }

    // when the user clicks log out switch GUI frmStartingPageGUI
    else if ((e.getActionCommand().equals("Log Out")))
    {
      frmStartingPageGUI frmStartingPageGUI = new frmStartingPageGUI();
      frmStartingPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmStartingPageGUI.setSize(500, 500);
      frmStartingPageGUI.setVisible((true));
      this.dispose();
    }

    // when the user clicks recommend books
    else if ((e.getActionCommand().equals("Recommend")))
    {
      // stores the account name that matches the best with activeUser
      String strBestMemberMatch = userBestMatch(); 
      // displays the recommended books
      txtRecommendedBooks.setText(displayRecommendations(strBestMemberMatch));
    }

    // when the user clicks password change
    else if ((e.getActionCommand().equals("change")))
    {
      // if nothing was entered prompt error
      if(txtPassword.getText().equals(""))
      {
        JOptionPane.showMessageDialog(null, "No Password was entered");
      }

      // if the password is less than 3 characters long, prompt user
      else if(txtPassword.getText().length() < 3)
      {
        JOptionPane.showMessageDialog(null, "The password must be 3 characters! ");
      }

      // change password in memberMap and update ratings file
      else
      {
        // change password
        frmLoadDataGUI.memberMap.get(strActiveUser).setPassword(txtPassword.getText());
        // update the file
        editRatingFile(frmLoadDataGUI.strUsedRatingFile);
        JOptionPane.showMessageDialog(null, "Password change is successful!");
      }
    }
  }
}
