package ca.uwo.csd.cs2212.team11;
import org.jdatepicker.graphics.*; 
import org.jdatepicker.impl.*; 
import org.jdatepicker.util.*;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Component;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.io.Serializable;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.*;
import ca.uwo.csd.cs2212.team11.Team11_FitBitViewer.*;
import ca.uwo.csd.cs2212.team11.SharedData.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePickerImpl;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
/**
*@Author Dara Amin -- Used for JDate Picker
*/

public class SelectDate extends JFrame implements ActionListener  
{

JButton checkBtn;
public JDatePickerImpl dtePick;
String reportDate;
Date selectedDate;


/**
*@Param -- Takes button and datpicker and inits events
*
*/
public SelectDate(JButton check, JDatePickerImpl datePicker) 
    {


    checkBtn = check;
    dtePick = datePicker;
    checkBtn.addActionListener(this);

    }

    /**
    *@Param action event is used to attach an event to a button to do something with selected dates
    *
    */
public void actionPerformed(ActionEvent e) throws NullPointerException 
    {
        if(checkBtn==e.getSource())
        {
        selectedDate = (Date) dtePick.getModel().getValue();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        reportDate = df.format(selectedDate);
        }
    }

    /**
    *@return selected date
    */

    public String getDate() throws NullPointerException{


        return this.reportDate;
    }

    /**
    *
    *@return selected date
    */

    public Date returnDate() throws NullPointerException{

             return this.selectedDate;
    }

}