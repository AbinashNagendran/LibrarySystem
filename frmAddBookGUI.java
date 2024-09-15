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

public class frmAddBookGUI extends frmValidityGUI
{
  JLabel lblTitle;
  JLabel lblBookName;
  JLabel lblAuthor;
  JLabel lblPublishYear;

  JButton btnBack;
  JButton btnSubmit;

  JTextField txtBookName;
  JTextField txtAuthor;
  JTextField txtPublishYear;

  // string variable stores the account name of hte active user
  String strActiveUser;
  public frmAddBookGUI (String strActiveUser)
  {
    this.strActiveUser = strActiveUser;
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);
    setLayout(null); 

    lblTitle = new JLabel("<html><font size='7'><font color = red>Add a Book!");
    lblTitle.setSize(300, 100);
    lblTitle.setLocation(125, 25);
    add(lblTitle);

    lblBookName = new JLabel("<html><font size='4'>Book Name:");
    lblBookName.setSize(125, 25);
    lblBookName.setLocation(140, 150);
    add(lblBookName);

    lblAuthor = new JLabel("<html><font size='4'>Author Name:");
    lblAuthor.setSize(125, 25);
    lblAuthor.setLocation(140, 250);
    add(lblAuthor);


    lblPublishYear = new JLabel("<html><font size='4'>Publish Year:");
    lblPublishYear.setSize(125, 25);
    lblPublishYear.setLocation(140, 350);
    add(lblPublishYear);


    txtBookName = new JTextField();
    txtBookName.setSize(120, 25);
    txtBookName.setLocation(140 + 120, 150);
    add(txtBookName);

    txtAuthor = new JTextField();
    txtAuthor.setSize(120, 25);
    txtAuthor.setLocation(140 + 120, 250);
    add(txtAuthor);

    txtPublishYear = new JTextField();
    txtPublishYear.setSize(120, 25);
    txtPublishYear.setLocation(140 + 120, 350);
    add(txtPublishYear);

    btnSubmit = new JButton("Submit");
    btnSubmit.setLocation(175, 400);
    btnSubmit.setSize(150, 30);
    btnSubmit.setActionCommand("Submit");
    btnSubmit.addActionListener(this);
    add(btnSubmit);

    btnBack = new JButton("Back");
    btnBack.setLocation(10, 430);
    btnBack.setSize(70, 30);
    btnBack.setActionCommand("Back");
    btnBack.addActionListener(this);
    add(btnBack);
  }

  /**
  @ param strBookName is the book name as a string
  @ param strAuthor is the book author as a string
  @ param strYear is the publish year as a string
  adds the book into arrBooks and writes it in the file
  **/
  public void addABook(String strBookName, String strAuthor, String strYear)
  {
    // add the book to arrBook array list
    // since the book is being being added to the end, the ISBN will be last index of arrBooks + 1
    frmLoadDataGUI.arrBooks.add(new Book(strAuthor, strBookName, strYear, frmLoadDataGUI.arrBooks.size()));

    // writing into the file
    try
    {
      FileWriter fr = new FileWriter(frmLoadDataGUI.strUsedBookFile, true);
      PrintWriter pr = new PrintWriter(fr);
      pr.println(strAuthor + "," + strBookName + "," + strYear + "," + frmLoadDataGUI.arrBooks.get(frmLoadDataGUI.arrBooks.size() - 1).getISBN());
      fr.close();
      pr.close();
    }
    catch(IOException e){}

    // now add the 0 ratings for all members
    for (String singleMember: frmLoadDataGUI.memberMap.keySet())
    {
      frmLoadDataGUI.memberMap.get(singleMember).addNewBookRating();
    }

    // change and save the ratings in the file
    frmLoadDataGUI.editRatingFile(frmLoadDataGUI.strUsedRatingFile);
  }

  public void actionPerformed(ActionEvent e)
  {
    // when the user clicks the back button
    if (e.getActionCommand().equals("Back"))
    {
      // swtich GUI to main page
      frmMainPageGUI frmMainPageGUI = new frmMainPageGUI(strActiveUser);
      frmMainPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmMainPageGUI.setSize(500, 500);
      frmMainPageGUI.setVisible((true));
      this.dispose();
    }
    // when the user clicks the submit button 
    else if (e.getActionCommand().equals("Submit"))
    {
      // check if the book is vaild to be entered
      if (vaildBook(txtBookName.getText(), txtAuthor.getText(), txtPublishYear.getText()))
      {
        // add the book and its information 
        addABook(txtBookName.getText(), txtAuthor.getText(), txtPublishYear.getText());
      }
    }
  }
}
