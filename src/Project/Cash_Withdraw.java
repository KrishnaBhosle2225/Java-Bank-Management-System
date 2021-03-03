package Project;
//importing all required packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cash_Withdraw extends MyGUI implements ActionListener {

    //declaring required variables
    JFrame f1;
    JTextField acc_no,cust_name,id,debt_amount;
    JLabel acc_bal;
    JButton show_bal,debt_bal,back;
    PreparedStatement ps;
    ResultSet rs;
    int total;


    //custructor
    public Cash_Withdraw()
    {
        f1 = myFrame("Withdraw Cash",500,100,620,700);          //creating frame

        myLabel("Withdraw Cash",150,60,300,70, Font.BOLD,40);       //label for heading

        myLabel("Enter Account Number :",100,160,200,40, Font.PLAIN,20);    //label for acount number
        myLabel("Enter Customer Name :",100,220,200,40, Font.PLAIN,20);     //label for name
        myLabel("Enter Customer Id :",100,280,200,40, Font.PLAIN,20);       //label for id
        myLabel("Account Balance is:",100,340,200,40, Font.BOLD,23);        //label for balance
        myLabel("Debit Ammount :",100,400,200,40, Font.PLAIN,20);           //label for debit amount

        acc_no = myTextField(300,160,150,30,"Account Number");          //textfield for account number
        cust_name = myTextField(300,220,150,30,"Account Name");         //textfield for customer name
        id = myTextField(300,280,150,30,"Customer Id");                 //textfield for id
        acc_bal = myLabel(null,300,340,150,40,Font.BOLD,25);     //label for account balance
        show_bal = myButton("Show Balance ",450,340,150,40,"Click to Show Balance");    //button for show balance
        show_bal.addActionListener(this);       //adding actionlistener to the show balance button

        debt_amount = myTextField(300,400,150,30,"Account Balance");        //textfield for debit amount

        debt_bal = myButton("DEDIT",300,460,150,40,"Click to Debit Balance");    //button for account balance
        debt_bal.addActionListener(this);        //adding actionlistener to the button

        back = myButton("Back",150,460,100,40,"Click to Back");     //button for exit
        back.addActionListener(this);       //adding actionlistener to the back button
    }

    //main method
    public static void main(String[] args)
    {
        new Cash_Withdraw();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==show_bal)     //if user clicks on show balance button it will fetch data
        {
            fetchData();    //calling to the fetchdata method
        }

        if(e.getSource()==back)         //if user clicks on back button
        {
            new Home_Page();        //redirecting to the home page
            f1.setVisible(false);   //disappearing the current frame
        }

        if (e.getSource()==debt_bal)     //if user clicks on add balance button
        {
            fetchData();        //calling to the fetchdata method
            debtBalance();       //calling to the debtBalance method
            JOptionPane.showMessageDialog(f1,"Now Your Available balance is "+total);

            //clearing the values
            debt_amount.setText(null);
            id.setText(null);
            acc_no.setText(null);
            cust_name.setText(null);
            acc_bal.setText(null);
        }
    }

    //functionn for fetchdata
    public void fetchData()
    {
        Cash_Withdraw.Function f=new Cash_Withdraw.Function();
        rs=f.find(id.getText(),cust_name.getText(),acc_no.getText());
        try{
            if(rs.next())
            {
                acc_bal.setText(String.valueOf(rs.getString(15)));
            }
            else{
                JOptionPane.showMessageDialog(null,"Please Check your Account Number, Id and Username ");

                //cleares the values
                id.setText(null);
                acc_no.setText(null);
                cust_name.setText(null);
            }

        }catch (SQLException ex)
        {

            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }


    public class Function{

        public ResultSet find(String get_id,String get_name,String get_accno)
        {
            try
            {

                DbConnection db = new DbConnection();       //creating object of the DbConnection class

                //passing query to the database using prepareStatement
                ps=db.con.prepareStatement("select * from customer where cust_id=? and cust_name =? and account_number = ?");
                ps.setString(1,get_id);
                ps.setString(2,get_name);
                ps.setString(3,get_accno);

                rs=ps.executeQuery();       //finally executes operation
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            return rs;      //returns resultset
        }
    }


    public void debtBalance()
    {
        int acc_balance = Integer.parseInt(acc_bal.getText());

        int cred_bal = Integer.parseInt(debt_amount.getText());

        total = acc_balance-cred_bal;               //subtracting the account balance and debited balance

        try
        {
            int my_id = Integer.parseInt(id.getText());

            DbConnection db = new DbConnection();       //creating object of DbConnection class
            Statement st1 = db.con.createStatement();       //crating statement
            String sql = String.format("update customer set acc_bal='%d' where cust_id = %d",total,my_id);      //paasing sql query

            st1.executeUpdate(sql);     //executing statement
            System.out.println("data updated successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
