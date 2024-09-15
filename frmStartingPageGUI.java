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

public class frmStartingPageGUI extends JFrame implements ActionListener
{
  JLabel lblTitle; 
  JLabel lblGuide; 
  JLabel lblGuide2; 
  JButton btnLogin;
  JButton btnSignUp;
  JButton btnLoadData;
  public frmStartingPageGUI()
  {
    setLayout(null); 
    // color back ground
    Color backGround = new Color(100, 234, 216);
    getContentPane().setBackground(backGround);

    lblTitle = new JLabel("<html><font size='7'>Welcome!");
    lblTitle.setSize(200, 50);
    lblTitle.setLocation(150, 0);
    lblTitle.setForeground(Color.blue);
    add(lblTitle);

    lblGuide = new JLabel("<html><font size='3'>Before starting, load data files");
    lblGuide.setSize(225, 100);
    lblGuide.setLocation(150, 275);
    lblGuide.setForeground(Color.red);
    add(lblGuide);

    lblGuide2 = new JLabel("<html><font size='5'><font color =maroon>FIRST!");
    lblGuide2.setSize(225, 100);
    lblGuide2.setLocation(225, 295);
    add(lblGuide2);

    btnLogin = new JButton("Login");
    btnLogin.setLocation(65, 150);
    btnLogin.setSize(100, 30);
    btnLogin.setActionCommand("Login");
    btnLogin.addActionListener(this);
    add(btnLogin);

    btnSignUp = new JButton("Sign up");
    btnSignUp.setLocation(335, 150);
    btnSignUp.setSize(100, 30);
    btnSignUp.setActionCommand("Sign up");
    btnSignUp.addActionListener(this);
    add(btnSignUp);

    btnLoadData = new JButton("Load Data");
    btnLoadData.setLocation(175, 250);
    btnLoadData.setSize(150, 30);
    btnLoadData.setActionCommand("Load Data");
    btnLoadData.addActionListener(this);
    add(btnLoadData);


  }


  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("Load Data"))
    {
      frmLoadDataGUI frmLoadDataGUI = new frmLoadDataGUI();
      frmLoadDataGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmLoadDataGUI.setSize(500,500);
      frmLoadDataGUI.setVisible((true));
      this.dispose();
    }
    else if(e.getActionCommand().equals("Sign up"))
    {
      // check if the user loaded the data already
      if (frmLoadDataGUI.strUsedRatingFile.equals("") || frmLoadDataGUI.strUsedBookFile.equals(""))
      {
        JOptionPane.showMessageDialog(null, "you must load the data first! ");
      }

      else
      {
        frmSignUpGUI frmSignUpGUI = new frmSignUpGUI();
        frmSignUpGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSignUpGUI.setSize(500,500);
        frmSignUpGUI.setVisible((true));
        this.dispose();
      }

    }
    else if(e.getActionCommand().equals("Login"))
    {
      // check if the user loaded the data already
      if (frmLoadDataGUI.strUsedRatingFile.equals("") || frmLoadDataGUI.strUsedBookFile.equals(""))
      {
        JOptionPane.showMessageDialog(null, "you must load the data first! ");

      }
      else
      {
        // swtich the GUI to the login page
        frmLoginPageGUI frmLoginPageGUI = new frmLoginPageGUI();
        frmLoginPageGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLoginPageGUI.setSize(500, 500);
        frmLoginPageGUI.setVisible((true));
        this.dispose();
      }
    }
  }
}
