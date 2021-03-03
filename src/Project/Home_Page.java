package Project;

//importing all required packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home_Page extends Project.MyGUI implements ActionListener{

    JButton CustDetails,cash_deposite,loan,create_acc,cash_withdraw ,exit;        //creating buttons
    Frame f1;
    Home_Page()
    {
        f1=myFrame("Home Page",500,100,600,600);    //creating frame
        Choice ch1=new Choice();                                          //creating choice object
        ch1.setBounds(300,600,100,100);                 //setting bounds to the choice
        f1.add(ch1);                                                      //adding choice object to frame

        myLabel("Welcome To Zeal Bank",70,50,500,100, Font.BOLD,40);                   //creating heading using label
        create_acc=myButton("Create Account",50,200,200,40,"Click to create a new Account");    // button for crete account
        create_acc.addActionListener(this);         // adding actionlistener to the button

        cash_withdraw = myButton("Cash Withdrawal",350,200,200,40,"Click to withdraw cash");    //button for cash withdraw
        cash_withdraw.addActionListener(this);      // adding actionlistener to the button

        cash_deposite = myButton("Cash Deposite",50,300,200,40,"Click to Deposit Cash");    //button for cash deposite
        cash_deposite.addActionListener(this);      // adding actionlistener to the button

        loan=myButton("Apply For Loan",350,300,200,40,"Click Apply For Loan");      //button for loan
        loan.addActionListener(this);               // adding actionlistener to the button

        CustDetails=myButton("Customer Details",50,400,200,40,"Click to know customer details");    //button for customerdetails
        CustDetails.addActionListener(this);        // adding actionlistener to the button

        exit=myButton("Log Out",350,400,200,40,"Click to Exit");    //button for exit
        exit.addActionListener(this);               // adding actionlistener to the button
    }


    public static void main(String[] args) {

        Home_Page h1=new Home_Page();       //creating object of our class
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==cash_deposite)    //if user clicks on cash deposit button
        {
            new Cash_Deposite();    //redirecting to the cash deposite page
            f1.setVisible(false);   //disappearing the current frame
        }
        if (e.getSource()==exit)
        {
            JOptionPane.showMessageDialog(f1,"Thank you For Visiting Us");
            f1.setVisible(false);           //disappearing the current frame
        }
        if(e.getSource()==CustDetails)
        {
            new CustomerDetails();      //redirecting to the customer details page
            f1.setVisible(false);       //disappearing the current frame
        }
        if(e.getSource()==loan)
        {
            new Apply_Loan_Page();      //redirecting to the loan page
            f1.setVisible(false);       //disappearing the current frame
        }
        if (e.getSource()==create_acc)
        {
            new Create_Account_Page();       //redirecting to the create account page
            f1.setVisible(false);        //disappearing the current frame
        }
        if (e.getSource()==cash_withdraw)
        {
            new Cash_Withdraw();        //redirecting to the cash withdraw page
            f1.setVisible(false);       //disappearing the current frame
        }

    }
}


