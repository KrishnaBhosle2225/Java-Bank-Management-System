package Project;

//importing all required packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CustomerDetails extends Project.MyGUI implements ActionListener{

    //declaring all required variables
    JFrame cust;
    JButton btn,back,clear;
    JTextField id,name = null,address=null,email,phone,pan,adhar,acc_no,acc_type,acc_bal,br_id,occupation,qualification,dob,gender;
    ResultSet rs=null;
    PreparedStatement ps;

    public CustomerDetails()
    {

        cust=myFrame("Customer Details",500, 100,1000,900);     //creating a frame

        myLabel("Enter ID :",100,100,130,40, Font.PLAIN,20);    //label for id
        myLabel("Name is :",100,250,150,30,Font.PLAIN,20);      //label for name
        myLabel("Address is :",100,300,150,30,Font.PLAIN,20);   //label for address
        myLabel("Qualification is :",100,350,150,30,Font.PLAIN,20);     //label for qualification
        myLabel("Occupation is :",100,400,150,30,Font.PLAIN,20);        //label for occupation
        myLabel("DOB is :",100,450,150,30,Font.PLAIN,20);               //label for DOB
        myLabel("Gender is :",100,500,150,30,Font.PLAIN,20);            //label for gender
        myLabel("PAN NO is :",100,550,150,30,Font.PLAIN,20);            //label for pan
        myLabel("Phone NO is :",550,250,150,30,Font.PLAIN,20);          //label for phone
        myLabel("Email is :",550,300,150,30,Font.PLAIN,20);             //label for email
        myLabel("Adharcard is :",550,350,150,30,Font.PLAIN,20);         //label for aadharcard
        myLabel("Acc No is :",550,400,150,30,Font.PLAIN,20);            //label for account no
        myLabel("Branch ID is :",550,450,150,30,Font.PLAIN,20);         //label for branch
        myLabel("Acc Type is :",550,500,150,30,Font.PLAIN,20);          //label for account type
        myLabel("Acc Bal is :",550,550,150,30,Font.PLAIN,20);           //label for account balance

        myLabels();         //calling method
        id=myTextField(250,105,150,30,"Enter ID which Data you Fetch");     //id for textfield

        btn = myButton("Fetch Data", 470, 105, 100, 30, "Click To Fetch Data");     //button
        btn.addActionListener(this);        //adding actionListener to the button

        back = myButton("Back", 20, 20, 70, 40, "Click to Back");   //button for back
        back.addActionListener(this);       //adding actionListener to the back

        clear = myButton("CLear Data",800,600,100,40,  "Click to Clear All Data");  //creating button
        clear.addActionListener(this);      //adding actionListener to the clear button
    }


    public void fetchData()
    {
        Function f=new Function();       //creating object of function

        rs=f.find(id.getText());        //passing id to the find method

        try{
            if(rs.next())       //fetching next
            {
                //fetching particular values by
                id.setEditable(false);
                id.setText(rs.getString(1));
                name.setText(rs.getString(2));
                address.setText(rs.getString(3));
                phone.setText(rs.getString(4));
                email.setText(rs.getString(5));
                dob.setText(rs.getString(6));
                gender.setText(rs.getString(7));
                qualification.setText(rs.getString(8));
                adhar.setText(rs.getString(9));
                pan.setText(rs.getString(10));
                occupation.setText(rs.getString(11));
                acc_no.setText(String.valueOf(rs.getDouble(12)));
                br_id.setText(String.valueOf(rs.getInt(13)));
                acc_type.setText(rs.getString(14));
                acc_bal.setText(String.valueOf(rs.getDouble(15)));

            }
            else{
                JOptionPane.showMessageDialog(null,"NO DATA Available FOR THIS ID");
                clearData();        //calling function to clear values
            }

        }catch (SQLException ex)
        {

            JOptionPane.showMessageDialog(null,ex.getMessage());
        }

    }

    public void myLabels()
    {

        //taken textfields for inputing values by the user
        name=myTextField(250,250,200,30,"Name");
        name.setEditable(false);
        address=myTextField(250,300,200,30,"Address");
        address.setEditable(false);
        phone=myTextField(250,350,200,30,"Phone");
        phone.setEditable(false);
        email=myTextField(250,400,200,30,"");
        email.setEditable(false);
        dob=myTextField(250,450,200,30,"");
        dob.setEditable(false);
        gender=myTextField(250,500,200,30,"");
        gender.setEditable(false);
        qualification=myTextField(250,550,200,30,"");
        qualification.setEditable(false);
        adhar=myTextField(700,250,200,30,"");
        adhar.setEditable(false);
        pan=myTextField(700,300,200,30,"");
        pan.setEditable(false);
        occupation=myTextField(700,350,200,30,"");
        occupation.setEditable(false);
        acc_no=myTextField(700,400,200,30,"");
        acc_no.setEditable(false);
        br_id=myTextField(700,450,200,30,"");
        br_id.setEditable(false);
        acc_type=myTextField(700,500,200,30,"");
        acc_type.setEditable(false);
        acc_bal=myTextField(700,550,200,30,"");
        acc_bal.setEditable(false);
    }


    public static void main(String[] args)
    {
        new CustomerDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==btn)         //if user clicks on button
        {
            fetchData();        //calling to the fetchData method
        }
        if (e.getSource()==back)        //if user clicks on back button
        {
            new Home_Page();                //redirecting to the Home page
            cust.setVisible(false);         //disables the current frame
        }
        if (e.getSource()==clear)       //if user clicks on clear button
        {
            clearData();                //calling to the clearData method
        }

    }


    public class Function{

        public ResultSet find(String s)
        {
            try
            {
                DbConnection db = new DbConnection();       //creating object of DbConnection class
                ps=db.con.prepareStatement("select * from customer where cust_id=?");   //passing sql query
                ps.setString(1,s);
                rs=ps.executeQuery();       //executting querry
            }

            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            return rs;      //it returns object of resultset
        }
    }

    public void clearData()
    {

        //clears all inputed values
        id.setEditable(true);
        id.setText(null);
        name.setText(null);
        address.setText(null);
        phone.setText(null);
        email.setText(null);
        occupation.setText(null);
        qualification.setText(null);
        dob.setText(null);
        gender.setText(null);
        adhar.setText(null);
        pan.setText(null);
        acc_bal.setText(null);
        acc_no.setText(null);
        br_id.setText(null);
        acc_type.setText(null);

    }

}
