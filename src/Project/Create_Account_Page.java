package Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class Create_Account_Page extends Project.MyGUI {

    //declaring all required objects/variables
    JFrame acc_page;
    JButton create,back;
    PreparedStatement ps;
    JTextField id,name,address,phone,email,dob,qualification,adhar,pan,occupation;
    Checkbox male,female;
    JComboBox acc_type;
    JTextField acc_no,branch_id,acc_bal;
    String insert_cust_data,gender;

    public Create_Account_Page()        //constructor
    {
        acc_page= myFrame("Create New Account",500,10,700,1000);    //creating frame

        myLabel("Create New Account",150,10,400,70, Font.CENTER_BASELINE,40);   //label for heading
        myLabel("Customer ID :",200,100,150,30,Font.PLAIN,20);      //label for customer id
        myLabel("Customer Name :",200,150,150,30,Font.PLAIN,20);    //label for customer name
        myLabel("Cust Address :",200,200,150,30,Font.PLAIN,20);     //label for customer address
        myLabel("Phone No :",200,250,150,30,Font.PLAIN,20);         //label for phone number
        myLabel("Email ID :",200,300,150,30,Font.PLAIN,20);         //label for Email ID
        myLabel("DOB :",200,350,150,30,Font.PLAIN,20);              //label for DOB
        myLabel("Gender :",200,400,100,30,Font.PLAIN,20);           //label for Gender
        myLabel("Qualification :",200,450,150,30,Font.PLAIN,20);    //label for Qualification
        myLabel("Aadhar No :",200,500,150,30,Font.PLAIN,20);        //label for Aadhar No
        myLabel("PAN No :",200,550,150,30,Font.PLAIN,20);           //label for PAN No
        myLabel("Occupation :",200,600,150,30,Font.PLAIN,20);       //label for Occupation
        myLabel("Account No :",200,650,150,30,Font.PLAIN,20);       //label for Account No
        myLabel("Branch ID :",200,700,150,30,Font.PLAIN,20);        //label for Branch ID
        myLabel("Account Type :",200,750,150,30,Font.PLAIN,20);     //label for Account Type
        myLabel("Account Balance :",200,800,150,30,Font.PLAIN,20);  //label for Account Balance

        id=myTextField(350,100,150,30,"Enter Customer ID");             //textfield for id
        name=myTextField(350,150,150,30,"Enter Customer Name");         //textfield for name
        address=myTextField(350,200,150,30,"Enter Customer Address");   //textfield for address
        phone=myTextField(350,250,150,30,"Enter Customer Phone No");    //textfield for phone
        email=myTextField(350,300,150,30,"Enter Customer Email ID");    //textfield for email
        dob=myTextField(350,350,150,30,"Enter Customer DOB");           //textfield for dob
        myradiobutton();                                                                        //radiobutton function is called here
        qualification=myTextField(350,450,150,30,"Enter Customer Qualification");   //textfield for qualification
        adhar=myTextField(350,500,150,30,"Enter Customer Aadhar No");       //textfield for aadhar
        pan=myTextField(350,550,150,30,"Enter Customer PAN No");            //textfield for pan no
        occupation=myTextField(350,600,150,30,"Enter Customer Occupation"); //textfield for occupation
        acc_no=myTextField(350,650,150,30,"Enter Customer Account No");     //textfield for account number
        branch_id = myTextField(350, 700, 150, 30, "Enter Customer Branch ID"); //textfield for branch id
        acc_type = myChoice(new String[]{"Select type", "Saving account", "Current account", "Joint account", "Bussiness account"}, 350, 750, 150, 30);
        //comboBox for account type

        acc_bal=myTextField(350,800,150,30,"Enter Customer Account Balance");   //textfield for account balance

        create=myButton("Create Account",350,870,150,40,"Click to create new account"); //button for creating account
        create.addActionListener(new ActionListener()       //actionlistener added to button
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==create)          //if user clicks on the create button
                {

                    if(email.getText().endsWith("@gmail.com")==true)
                    {
                        JOptionPane.showMessageDialog(acc_page,"PLZ Fill valid email","EMAIL Error",JOptionPane.ERROR_MESSAGE);
                    }

                    if(id.getText().isEmpty() ||
                            name.getText().isEmpty() ||
                            address.getText().isEmpty()||
                            phone.getText().isEmpty()||
                            occupation.getText().isEmpty()||
                            qualification.getText().isEmpty()||
                            dob.getText().isEmpty()||
                            adhar.getText().isEmpty()||
                            pan.getText().isEmpty()||
                            acc_no.getText().isEmpty()||
                            acc_bal.getText().isEmpty()||
                            branch_id.getText().isEmpty())       //for checking empty fields
                    {
                        JOptionPane.showMessageDialog(acc_page,"PLZ Fill All Details","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        insertData();   //all fields are non empty then data will be inserted
                    }
                }
            }
        });

        back=myButton("Back",150,870,100,40,"Back");        //creating a back button
        back.addActionListener(new ActionListener()                                     //adding actionlistener to the button
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Home_Page();                        //redirecting to home page
                acc_page.setVisible(false);             //disables to the current frame

            }
        });
    }

    public static void main(String[] args) {

        new Create_Account_Page();

    }


    public String insertCustomerDetails()          //query for inserting customer data
    {
        insert_cust_data="insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return insert_cust_data;
    }



    public void insertData()
    {
        try {

            DbConnection db = new DbConnection();

            String customer_data=insertCustomerDetails();
            ps=db.con.prepareStatement(customer_data);

            if(male.getState()==true)
            {
                gender="Male";
            }
            else
            {
                gender="Female";
            }

            //inserting user inputed values into database using preparedstatement
            ps.setString(1,id.getText());
            ps.setString(2,name.getText());
            ps.setString(3,address.getText());
            ps.setString(4,qualification.getText());
            ps.setString(5,occupation.getText());
            ps.setString(6,dob.getText());
            ps.setString(7,gender);
            ps.setString(8,pan.getText());
            ps.setString(9,phone.getText());
            ps.setString(10,email.getText());
            ps.setString(11,adhar.getText());
            ps.setString(12, acc_no.getText());
            ps.setString(13,branch_id.getText());
            ps.setString(14, (String) acc_type.getSelectedItem());
            ps.setString(15,acc_bal.getText());
            int i1=ps.executeUpdate();
            JOptionPane.showMessageDialog(acc_page,"Account Created Successfully");
            clearData();            //cleares all data
            db.con.close();            //closing database connection
        }
        catch (Exception throwable) {

            throwable.printStackTrace();
        }
    }

    //function is written for clearing all filled data
    public void clearData()
    {
        id.setText(null);
        name.setText(null);
        address.setText(null);
        phone.setText(null);
        email.setText(null);
        occupation.setText(null);
        qualification.setText(null);
        dob.setText(null);
        adhar.setText(null);
        pan.setText(null);
        acc_bal.setText(null);
        acc_no.setText(null);
        branch_id.setText(null);
        acc_type.setSelectedIndex(0);

    }

    public void myradiobutton()
    {

        CheckboxGroup cbg=new CheckboxGroup();

        male=new Checkbox("Male",cbg,false);
        male.setBounds(350,400,70,40);

        female=new Checkbox("Female",cbg,false);
        female.setBounds(420,400,80,40);

        acc_page.add(male);
        acc_page.add(female);

    }



}
