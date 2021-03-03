package Project;

//importing all required packages
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Apply_Loan_Page extends Project.MyGUI
{

    //declaring variables as globally
    JFrame l_frame;
    JTextField l_id,income,l_amount;
    JComboBox l_type;

    public  Apply_Loan_Page()
    {
        l_frame=myFrame("Loan Page",500,100,600,600);       //creating frame

        myLabel("Apply For Loan Here",100,20,400,50, Font.BOLD,40);     //label for heading
        myLabel("Loan ID :",150,150,150,30,Font.PLAIN,20);              //label for loan id
        myLabel("Loan Type :",150,200,150,30,Font.PLAIN,20);            //label for loan type
        myLabel("Annual Inacome :",150,250,150,30,Font.PLAIN,20);       //label for annual income
        myLabel("Loan Amount :",150,300,150,30,Font.PLAIN,20);          //label for loan amount

        l_id = myTextField(300,150,150,30,"Loan ID");       //textfield for id

        l_type= myChoice(new String[]{"select type","personal loan","home loan","education loan","vehicle loan","gold loan"},300,200,150,30);
        l_type= myChoice(new String[]{"select type","personal loan","home loan","education loan","vehicle loan","gold loan"},300,200,150,30);
        //ComboBox for account type

        income = myTextField(300,250,150,30,"Annual income in RS");     //textfield for income
        l_amount = myTextField(300,300,150,30,"Loan ammount");          //textfield for amount

        JButton loan=myButton("Apply",350,350,100,40,"Click to Apply for loan");        //creating button for loan

        loan.addActionListener(new ActionListener()             //adding actionlistener to the button
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==loan )                   //if user clicks on loan button
                {
                    if (l_id.getText().isEmpty() ||
                            income.getText().isEmpty()||
                            l_amount.getText().isEmpty())       //it checks empty fields
                    {
                        JOptionPane.showMessageDialog(null, "Plz Fill all details", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    else{

                        insertData();           //if all fields are non empty then insert data
                        l_frame.setVisible(false);      //disappear the current fframe

                    }

                }

            }
        });

        JButton back=myButton("Back",150,350,100,40,"Back page");       //creating back button
        back.addActionListener(new ActionListener()                                 //adding actionlistener to the button
        {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Home_Page();        //redirecting to the home page
                l_frame.setVisible(false);      //disappearing current frame
            }
        });
    }

    //function for inserting data
    public void insertData()
    {
        try {

            DbConnection db =new DbConnection();        //creating object of predifine DbConnection class

            PreparedStatement ps = db.con.prepareStatement("insert into loan values(?,?,?,?)");     //passing query to the preparedstatement

            //inserting the inputed values from the user into database using preparedstatement
            ps.setString(1,l_id.getText());
            ps.setString(2, (String) l_type.getSelectedItem());
            ps.setString(3,income.getText());
            ps.setString(4,l_amount.getText());

            int i=ps.executeUpdate();       //executes the operations
            System.out.println(i+" Data Inserted Succcessfully");

            //clears the values
            l_id.setText("");
            l_type.setSelectedIndex(0);
            l_amount.setText("");
            income.setText("");
            messageBox();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            new Apply_Loan_Page();
        }

    }

    //main method
    public static void main(String[] args)
    {
        new Apply_Loan_Page();
    }
}








