/* 
Author: Abinash Nagendran
Date: 14/08/2024
ICS4U Culminating Task 
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.ImageIcon;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;


public class frmLoginPageGUI extends frmValidityGUI
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

  public frmLoginPageGUI()
  {
    setLayout(null); 
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);

    lblTitle = new JLabel("<html><font size='7'>Login!");
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
    btnSubmit.setLocation(175, 250);
    btnSubmit.setSize(150, 30);
    btnSubmit.setActionCommand("Submit");
    btnSubmit.addActionListener(this);
    add(btnSubmit);

    lblPassword = new JLabel("Password");
    lblPassword.setSize(100, 50);
    lblPassword.setLocation(190, 170);
    add(lblPassword);

    txtPassword = new JTextField();
    txtPassword.setLocation(190, 210);
    txtPassword.setSize(120, 25);
    add(txtPassword);

    lblAccountName = new JLabel("Account Name");
    lblAccountName.setLocation(190, 100);
    lblAccountName.setSize(100, 50);
    add(lblAccountName);

    txtAccountName = new JTextField();
    txtAccountName.setLocation(190, 140);
    txtAccountName.setSize(120, 25);
    add(txtAccountName);
  }
  /**
  @ param strAccountName is the account name string
  @ param strPassword is the password of the account string
  returns (true/false) if the the account name and password match correctly
  **/
  public boolean loginVaild(String strAccountName, String strPassword)
  {
    // first if all fields are not empty
    if (strAccountName.equals(""))
    {
      JOptionPane.showMessageDialog(null, "No account name was entered!");
      return false;
    }
    else if (strPassword.equals(""))
    {
      JOptionPane.showMessageDialog(null, "No password was given was entered!");
      return false;
    }
    // check if account name is in map
    else if(!frmLoadDataGUI.memberMap.containsKey(strAccountName))
    {
      JOptionPane.showMessageDialog(null, "Account name does not exist!");
      return false;
    }

    // check if the password does not match with the account name password in the map
    else if (!strPassword.equals(frmLoadDataGUI.memberMap.get(strAccountName).getPassword()))
    {
      JOptionPane.showMessageDialog(null, "Incorrect Password");
      return false;
    }
    return true;
  }

  public void actionPerformed(ActionEvent e)
  {
    // when the user clicks tghe back button
    if (e.getActionCommand().equals("back"))
    {
      // swtich the GUI to the starting page
      frmStartingPageGUI frmStartingPageGUI = new frmStartingPageGUI();
      frmStartingPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmStartingPageGUI.setSize(500, 500);
      frmStartingPageGUI.setVisible((true));
      this.dispose();
    }
    // if the user clicks the submit button
    else if (e.getActionCommand().equals("Submit"))
    {
      // checks if the login details are vaild
      if(loginVaild(txtAccountName.getText(), txtPassword.getText()))
      {
        // swtich GUI the main page and pass in the account name
        frmMainPageGUI frmMainPageGUI = new frmMainPageGUI(txtAccountName.getText());
        frmMainPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMainPageGUI.setSize(500, 500);
        frmMainPageGUI.setVisible((true));
        this.dispose();
      }
    }
  }
}
