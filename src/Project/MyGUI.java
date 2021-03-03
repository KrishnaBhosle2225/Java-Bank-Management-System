package Project;

import javax.swing.*;
import java.awt.*;

public class MyGUI extends Component {
    JFrame f1;

//    function is defined for creating a frame
    public JFrame myFrame(String title, int x, int y, int width, int height)
    {
        f1=new JFrame(title);       //creating object of frame
        f1.setVisible(true);       //frame is now visible
        f1.setBounds(x,y,width,height);     //setting positions and dimensions
        f1.setLayout(null);           //setting layout as null
        f1.setResizable(false);
        return f1;
    }

    //    function is defined for creating a label
    public JLabel myLabel(String name, int x, int y, int width, int height, int fontstyle, int fontsize)
    {
        JLabel l1=new JLabel(name);     //creating object of label
        l1.setBounds(x,y,width,height);  //setting positions and dimensions
        Font font=new Font("times new roman",fontstyle,fontsize);   //setting fontstyle,fontsize to the label
        l1.setFont(font);   //setting font object to the label
        f1.add(l1);         //adding label obj into frame
        return l1;          //function returns label
    }


    //    function is defined for creating a textfield
    public JTextField myTextField(int x, int y, int width, int height, String tooltip)
    {
        JTextField t1=new JTextField();     //creating object of JTextField
        t1.setBounds(x,y,width,height);     //setting positions and dimensions
        t1.setToolTipText(tooltip);     //setting tooltip to the textfield
        f1.add(t1);             //adding textfield object to frame
        return t1;              //function returns textfield
    }

    //    function is defined for creating a passwordfield
    public JPasswordField myPassField(int x, int y, int width, int height, String tooltip)
    {
        JPasswordField pf=new JPasswordField();     //creating object of passwordfield
        pf.setBounds(x,y,width,height);         //setting positions and dimensions
        pf.setToolTipText(tooltip);         //setting tooltip to the passwordfield
        f1.add(pf);         //adding passwordfield object to the frame
        return pf;          //function returning passwordfield
    }


    //    function is defined for creating a button
    public JButton myButton(String name, int x, int y, int width, int height, String tooltip)
    {
        JButton b1=new JButton(name);       //creating object of button
        b1.setBounds(x,y,width,height);     //setting positions and dimensions
        b1.setToolTipText(tooltip);         //setting tooltip to the passwordfield
        f1.add(b1);                         //adding button to the frame
        return b1;                          //function returns button
    }

    //    function is defined for creating a combobox
    public JComboBox myChoice(String[] list, int x, int y, int width, int height)
    {
        JComboBox cb1=new JComboBox(list);   //creating object of comboBox
        cb1.setBounds(x,y,width,height);     //setting positions and dimensions
        f1.add(cb1);                        //adding combobox to the frame
        return cb1;                         //function returns combobox
    }

    //    function is defined for creating a messagebox
    public void messageBox()
    {
        JOptionPane.showMessageDialog(this,"You Aplied for loan Successfully","Loan",JOptionPane.INFORMATION_MESSAGE);
    }


    //predefine function for setting images
    public void addImage(String path,int x,int y,int width,int height)
    {
        ImageIcon icon = new ImageIcon(path);      //created object of imageicon and passing path of the image
        Image img = icon.getImage();
        JLabel l22 = new JLabel(icon, JLabel.CENTER);   //setting image on label
        l22.setBounds( x, y, width, height);            //setting positions and dimensions
        Image newimg = img.getScaledInstance(l22.getWidth(), l22.getHeight(), Image.SCALE_SMOOTH);  //proper positionig of image
        f1.add(l22);                //adding label to the frame

    }
}
