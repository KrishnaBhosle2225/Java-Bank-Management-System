package Project;
//importing all necessary packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cash_Deposite extends MyGUI implements ActionListener {

    //declaring all required objects
    JFrame f1;
    JTextField acc_no,cust_name,id,cred_amount;
    JLabel acc_bal;
    JButton show_bal,add_bal,back,ok;
    PreparedStatement ps;
    ResultSet rs;
    int total;

    public Cash_Deposite()
    {
        f1 = myFrame(" Deposite Cash",500,100,620,700);     //creating frame

        myLabel("Deposite Cash",150,60,300,70,Font.BOLD,40);        //label for heading
        myLabel("Enter Account Number :",100,160,200,40, Font.PLAIN,20);    //label for account number
        myLabel("Enter Customer Name :",100,220,200,40, Font.PLAIN,20);     //label for name
        myLabel("Enter Customer Id :",100,280,200,40, Font.PLAIN,20);       //label for id
        myLabel("Account Balance is:",100,340,200,40, Font.BOLD,23);        //label for account balance
        myLabel("Credit Ammount :",100,400,200,40, Font.PLAIN,20);          //label for creadit amount

        acc_no = myTextField(300,160,150,30,"Account Number");              //textfield for account number
        cust_name = myTextField(300,220,150,30,"Account Name");             //textfield for customer name
        id = myTextField(300,280,150,30,"Customer Id");                     //textfield for id
        acc_bal = myLabel(null,300,340,150,40,Font.BOLD,25);          //label for account balance
        show_bal = myButton("Show Balance ",450,340,150,40,"Click to Show Balance");    //creating show_bal button
        show_bal.addActionListener(this);       //adding actionlistener to the show_bal button

        cred_amount = myTextField(300,400,150,30,"Account Balance");    //textfield for credit amount

        add_bal = myButton("Add Balance ",300,460,150,40,"Click to Add Balance");    //creating add_bal button
        add_bal.addActionListener(this);        //adding actionlistener to the add_bal button

        back = myButton("Back",150,460,100,40,"Click toBack");      //creating back button
        back.addActionListener(this);           //adding actionlistener to the back button

    }

    public static void main(String[] args)
    {
         new Cash_Deposite();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource()==show_bal)     //if user clicks on show bal button
        {
            fetchData();        //calling to fetchData method
        }

        if(e.getSource()==back)         //if user clicks on back button
        {
            new Home_Page();      //redirecting to home page
            f1.setVisible(false);   //disappears current frame
        }

        if (e.getSource()==add_bal)     //if user clicks on add_bal button
        {
            fetchData();        //calling to the fetchdata method
            addBalance();       //calling to the addBalance method
            JOptionPane.showMessageDialog(f1,"Now Your Available balance is "+total);

            //clears all the inputed values
            cred_amount.setText(null);
            id.setText(null);
            acc_no.setText(null);
            cust_name.setText(null);
            acc_bal.setText(null);
        }
    }

    //function is written for fetcing data
    public void fetchData()
    {
        Cash_Deposite.Function f=new Cash_Deposite.Function();
        rs=f.find(id.getText(),cust_name.getText(),acc_no.getText());
        try{
            if(rs.next())
            {
                //fetching account balance
                acc_bal.setText(String.valueOf(rs.getString(15)));

            }
            else{
                JOptionPane.showMessageDialog(null,"Please Check your Account Number, Id and Username ");

                //cleares all fields
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
                DbConnection db = new DbConnection();
                ps=db.con.prepareStatement("select * from customer where cust_id=? and cust_name =? and account_number = ?");
                ps.setString(1,get_id);
                ps.setString(2,get_name);
                ps.setString(3,get_accno);
                rs=ps.executeQuery();
            }
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            return rs;
        }
    }

    //function for addbalance
    public void addBalance()
    {
        int acc_balance = Integer.parseInt(acc_bal.getText());      //converting string to integer

        int cred_bal = Integer.parseInt(cred_amount.getText());     //converting string to integer

        total = acc_balance+cred_bal;       //adding those two integer values

        try
        {
            int my_id = Integer.parseInt(id.getText());
            DbConnection db = new DbConnection();           //creating object of DbConnection class
            Statement st1 = db.con.createStatement();
            String sql = String.format("update customer set acc_bal='%d' where cust_id = %d",total,my_id);      //sql Query
            st1.executeUpdate(sql);
            System.out.println("data updated successfully");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
