package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login_Page extends Project.MyGUI
{

    JTextField username;
    JPasswordField passwordText;
    JButton login;

    public Login_Page()
    {
        JFrame login_frame=myFrame("Login Page",500,100,600,600);       //creating frame

        login=myButton("Login",250,360,100,50,"Click to Login");    //creating button
        login.addActionListener(new ActionListener()        //adding actionlistener to the button
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user = username.getText();                           //getting username from the textfield
                String pass = new String(passwordText.getPassword());       //getting username from the passwordfield

                if (user.equals("admin") && pass.equals("admin123") || user.equals("krishna") && pass.equals("bhosle") )    //checking condition
                {
                    JOptionPane.showMessageDialog(login_frame, "User Login Succsessfully....");
                    JOptionPane.showMessageDialog(login_frame, "Welcome to the Bank management system");
                    new Home_Page();        //redirecting to the home page after checking condition
                    login_frame.setVisible(false);      //disappearing the current frame
                }
                else    //if condition fails
                {
                    JOptionPane.showMessageDialog(login_frame,"Invalid username or password","Authentication",JOptionPane.ERROR_MESSAGE);
                    username.setText(null);
                    passwordText.setText(null);
                }
            }
        });

        myLabel("Bank Management System",100,50,500,100, Font.BOLD,40);     //heading of the login page
        myLabel("Login here",230,150,200,50,Font.BOLD,25);                  //displaying a simple text
        myLabel("Username :",130,230,120,40,Font.ITALIC,22);                //creating a simple label
        myLabel("Password :",130,290,120,40,Font.ITALIC,22);                //creating a simple label

        username=myTextField(250,235,200,30,"Enter Username");      //textfield for username
        passwordText=myPassField(250,295,200,30,"Enter Password");  ////textfield for password
    }

    public static void main(String arg[])
    {
        Login_Page l1=new Login_Page();
    }
}
