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

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class frmSignUpGUI extends frmValidityGUI
{
  JLabel lblTitle; 
  JButton btnBack;
  JButton btnSubmit;
  JLabel lblAccountName; 
  JLabel lblName; 
  JLabel lblPassword; 
  JTextField txtAccountName;
  JTextField txtName;
  JTextField txtPassword;


  /**
  adds the new user to the map and writes into the file
  **/
  public void signUpMember(String strAccount, String strName, String strPassword)
  {
    // need to create a ratings for this user 
    // integer array list stores new member ratings (set to 0)
    ArrayList<Integer> arrNewMemberRatings = new ArrayList<Integer>();

    for (int i = 0; i < frmLoadDataGUI.arrBooks.size(); i++)
    {
      arrNewMemberRatings.add(0);
    }
    frmLoadDataGUI.memberMap.put(strAccount, new Member(strAccount, strName, strPassword, arrNewMemberRatings));
    JOptionPane.showMessageDialog(null, "Sign up sucessful! You can login now!");

    // writing into the new member into the file
    try
    {
      FileWriter fr = new FileWriter(frmLoadDataGUI.strUsedRatingFile, true);
      PrintWriter pr = new PrintWriter(fr);
      // string variable stores the account, name and password
      String strNewMemberInfo = strAccount + "," + strName + "," + strPassword;
      // stores the new member ratings
      String strNewMemberRatings = frmLoadDataGUI.memberMap.get(strAccount).getRatingsAsString();
      // printing to the file ratings
      pr.println(strNewMemberInfo);
      pr.println(strNewMemberRatings);

      fr.close();
      pr.close();
    }
    catch(IOException e){}
  }

  public frmSignUpGUI()
  {
    setLayout(null); 
    // color back ground
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);

    lblTitle = new JLabel("<html><font size='7'>Sign up!");
    lblTitle.setSize(200, 50);
    lblTitle.setLocation(170, 0);
    lblTitle.setForeground(Color.blue);
    add(lblTitle);

    btnBack = new JButton("Back");
    btnBack.setLocation(200, 400);
    btnBack.setSize(100, 30);
    btnBack.setActionCommand("back");
    btnBack.addActionListener(this);
    add(btnBack);

    btnSubmit = new JButton("Submit");
    btnSubmit.setLocation(175, 330);
    btnSubmit.setSize(150, 30);
    btnSubmit.setActionCommand("Submit");
    btnSubmit.addActionListener(this);
    add(btnSubmit);

    lblName = new JLabel("Name");
    lblName.setSize(100, 50);
    lblName.setLocation(190, 170);
    add(lblName);

    txtName = new JTextField();
    txtName.setLocation(190, 210);
    txtName.setSize(120, 25);
    add(txtName);

    lblAccountName = new JLabel("Account Name");
    lblAccountName.setLocation(190, 100);
    lblAccountName.setSize(100, 50);
    add(lblAccountName);

    txtAccountName = new JTextField();
    txtAccountName.setLocation(190, 140);
    txtAccountName.setSize(120, 25);
    add(txtAccountName);

    txtPassword = new JTextField();
    txtPassword.setLocation(190, 280);
    txtPassword.setSize(120, 25);
    add(txtPassword);

    lblPassword = new JLabel("Password");
    lblPassword.setLocation(190, 240);
    lblPassword.setSize(100, 50);
    add(lblPassword);

  }
  public void actionPerformed(ActionEvent e)
  {
    // if the user presses the back button
    if (e.getActionCommand().equals("back"))
    {
      // swtich the GUI to the starting page
      frmStartingPageGUI frmStartingPageGUI = new frmStartingPageGUI();
      frmStartingPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmStartingPageGUI.setSize(500, 500);
      frmStartingPageGUI.setVisible((true));
      this.dispose();
    }
    // when the user clicks the submit button
    else if (e.getActionCommand().equals("Submit"))
    {
      // check if the account name is vaild
      if(accountNameVaild(txtAccountName.getText(), txtName.getText(), txtPassword.getText()))
      {
        // add member to array list and write into file
        signUpMember(txtAccountName.getText(), txtName.getText(), txtPassword.getText());
      }
    }
  }
}
