package Project;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection
{
    Connection con;
    
    public DbConnection()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_mgmt_system_project","root","");

            System.out.println("Connection got succsessfully");

        }catch(Exception e)
        {

            e.printStackTrace();
        }
    }

}
