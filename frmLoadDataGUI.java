/* 
Author: Abinash Nagendran
Date: 15/08/2024
ICS4U Culminating Task 
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class frmLoadDataGUI extends frmValidityGUI
{
  JLabel lblTitle; 
  JLabel lblRatings; 
  JLabel lblBooks; 

  JButton btnSubmit;
  JButton btnBack;
  JTextField txtBookFileName;
  JTextField txtRatingsFileName;

  // array list that stores objects of books
  public static ArrayList<Book> arrBooks = new ArrayList<Book>();

  // stores the name of the ratings file used
  public static String strUsedRatingFile = "";
  // stores the name of the book file used
  public static String strUsedBookFile = "";

  // HashMap stores all account names and their member object
  public static Map<String, Member> memberMap = new HashMap<String, Member>();

  /**
  @ param strBookFileName is a string
  writes into book file
  **/
  public static void editBookFile(String strBookFileName)
  {
    // string array list stores every line of strBookFileName
    ArrayList<String> arrBookFileLines = new ArrayList<String>();
    try
    {
      FileReader fr = new FileReader(strBookFileName);
      BufferedReader br = new BufferedReader(fr);
      // string variable reads each line of strBookFileName
      String strLine = br.readLine();
      // go through every line in the text file
      while (strLine != null)
      {
        arrBookFileLines.add(strLine);
        strLine = br.readLine();
      }
      fr.close();
      // integer variable stores the index of each line in arrBookFileLines
      int intIndex = 0;
      // go through every book in arrBook and change each line with update information
      for (Book BookInfo: arrBooks)
      {
        // stores book information (book name, publish year, and ISBN)
        String strBookInfo = BookInfo.getAuthor() + "," + BookInfo.getBookName() + "," + BookInfo.getPublishYear() + "," + BookInfo.getISBN();
        // change the line to the updated version
        arrBookFileLines.set(intIndex, strBookInfo);
        intIndex++;
      }
        FileWriter fr2 = new FileWriter(strBookFileName);
        PrintWriter pr = new PrintWriter(fr2);
        // print every index of arrBookFileLines into the text file
        for (int i = 0; i < arrBookFileLines.size(); i++)
        {
          pr.println(arrBookFileLines.get(i));
        }
        fr2.close();
        pr.close();
    }
      catch(IOException e){}

  }

  /**
  @ param strBookFileName is a string
  reads the file and adds to an arraylist of Books
  **/
  public void loadBookFiles(String strBookFileName)
  {
    // this try and catch block is for file name
    try
    {
      FileReader fr2 = new FileReader(strBookFileName);
      BufferedReader br = new BufferedReader(fr2);

      // string variable that reads in line in the text file
      String strLine = br.readLine();
      // integer variable that makes the ISBN number for each book
      int intIsbnNum = 0;
      // goes through files lines and add book to arrayList 
      while(strLine != null)
      {
        // string array that splits words in each line 
        String [] arrBookInfo = strLine.split(",");

        // checks how many words/numbers are given (sometimes year is not given)
        // add the book into arrBooks with its respective parameters
        if (arrBookInfo.length == 3)
        {
          arrBooks.add(new Book(arrBookInfo[0], arrBookInfo[1], arrBookInfo[2], intIsbnNum));
        }
        // when year is not given
        else if (arrBookInfo.length == 2)
        {
          arrBooks.add(new Book(arrBookInfo[0], arrBookInfo[1], intIsbnNum));
        }
        // when everything (including ISBN) is given
        else if (arrBookInfo.length == 4)
        {
          arrBooks.add(new Book(arrBookInfo[0], arrBookInfo[1], arrBookInfo[2], Integer.parseInt(arrBookInfo[3])));
        }
        strLine = br.readLine();
        // increase the ISBN number for next book
        intIsbnNum++;
        strUsedBookFile = strBookFileName;
        }
    }
    catch(IOException e){}
  }

  /**
  @ param strBookFileName is a string
  reads the file and adds to an arraylist of Books
  **/
  public void loadRatingFiles(String strRatingFileName)
  {
    try
    {
      FileReader fr = new FileReader(strRatingFileName);
      BufferedReader br = new BufferedReader(fr);

      // string variable that reads in line in the text file
      String strLine = br.readLine();

      // goes through every line in the rating file
      while(strLine != null)
      {
        // this string array stores account, name and password
        String [] arrMemberInfo = strLine.split(",");

        // string variable stores the account name
        // if the file is already edited, there may be commas
        String strAccountName = arrMemberInfo[0];

        // getting the ratings
        strLine = br.readLine();
        // string array that stores the ratings as string 
        String [] arrMemberRatings = strLine.split(" ");
        // array list of integers that stores the ratings as a integer
        ArrayList<Integer> arrMemberRatingsNum = new ArrayList<Integer>();

        // go through every rating in arrMemberRatings and converts it into a integer
        for (int i = 0; i < arrMemberRatings.length; i++)
        {
          arrMemberRatingsNum.add(Integer.parseInt(arrMemberRatings[i]));
        }
        // already modified file with possible members
        if (arrMemberInfo.length == 3)
        {
          // add the account name and member object to the hashmap
          memberMap.put(strAccountName, new Member(strAccountName, arrMemberInfo[1], arrMemberInfo[2], arrMemberRatingsNum));
        }
        // when the member only has a account name
        else
        {
          // add the account name and member object to the hashmap
          memberMap.put(strAccountName, new Member(strAccountName, arrMemberRatingsNum));
        }

        strLine = br.readLine();
      }

      strUsedRatingFile = strRatingFileName;
      br.close();
    }
    catch(IOException e){}
  }

  public frmLoadDataGUI()
  {
    setLayout(null); 
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);

    lblTitle = new JLabel("<html><font size='6'>Load External Data!");
    lblTitle.setSize(400, 50);
    lblTitle.setLocation(120, 0);
    lblTitle.setForeground(Color.blue);
    add(lblTitle);

    lblBooks = new JLabel("Load Books");
    lblBooks.setSize(100, 50);
    lblBooks.setLocation(65 + 250, 180);
    add(lblBooks);

    lblRatings = new JLabel("Load Ratings");
    lblRatings.setSize(100, 50);
    lblRatings.setLocation(65, 180);
    add(lblRatings);


    btnSubmit = new JButton("Enter");
    btnSubmit.setLocation(190, 150 + 120);
    btnSubmit.setSize(120, 30);
    btnSubmit.setActionCommand("submit");
    btnSubmit.addActionListener(this);
    add(btnSubmit);

    btnBack = new JButton("back");
    btnBack.setLocation(175, 250 + 150);
    btnBack.setSize(150, 30);
    btnBack.setActionCommand("back");
    btnBack.addActionListener(this);
    add(btnBack);

    txtBookFileName = new JTextField();
    txtBookFileName.setLocation(65 + 250, 215);
    txtBookFileName.setSize(120, 25);
    add(txtBookFileName);

    txtRatingsFileName = new JTextField();
    txtRatingsFileName.setLocation(65, 215);
    txtRatingsFileName.setSize(120, 25);
    add(txtRatingsFileName);
  }

  public void actionPerformed(ActionEvent e)
  {
    // when the user clicks the back button
    if (e.getActionCommand().equals("back"))
    {
      // create the starting page frame
      frmStartingPageGUI frmStartingPageGUI = new frmStartingPageGUI();
      frmStartingPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmStartingPageGUI.setSize(500, 500);
      frmStartingPageGUI.setVisible((true));
      this.dispose();
    }
    // when the user clicks the Submit button to load books
    else if (e.getActionCommand().equals("submit"))
    {
      // checking if the file names in the field is vaild
      if (isFileVaild(txtBookFileName.getText(), txtRatingsFileName.getText()))
      {
        // formatting of the file could be wrong which will make a run time error or the user could press the button twice
        try
        {
          // load and read the book file
          loadBookFiles(txtBookFileName.getText());
          editBookFile(txtBookFileName.getText());
          // load the read ratings file
          loadRatingFiles(txtRatingsFileName.getText());
          editRatingFile(txtRatingsFileName.getText());
        }
        catch(Exception e1)
        {
          JOptionPane.showMessageDialog(null, "The format for the books file is incorrect or the data was loaded twice!");
        }
      }
    }
  }
}
