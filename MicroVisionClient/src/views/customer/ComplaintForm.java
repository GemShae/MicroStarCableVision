package views.customer;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;

public class ComplaintForm extends JDialog {

    /** -------------------------MEMBERS------------------------------- */

    // container
    JPanel contentPanel;

    JScrollPane complaintTxtAreaScrollPane;
    // labels
    JLabel selectTypeOfServiceLabel;
    JLabel lodgeComplaintLabel;
    JLabel selectMethodOfContactLabel;
    JLabel MicroStarLabel;
    JLabel ComplaintFormLabel;

    JPanel topPanel;
    Border blueLine;
    Border raisedBevel;
    Color blueBackground;

    // comboboxes
    JComboBox<String> selectTypeOfServiceCombobox;
    JComboBox<String> selectMethodOfContactCombobox;

    // combobox options
    String typesOfService[] = { "Internet", "Phone", "Television" };
    String methodsOfContact[] = { "Phone", "Email" };

    // inputs
    JTextArea lodgeComplaintTxtArea;
    JButton submitBtn;
    JButton cancelBtn;

    /** -------------------------CONSTRUCTORS------------------------------- */

    public ComplaintForm(Frame parent) {

        super(parent, "Complaint Form", true);

        blueBackground = new Color(41, 193, 239);

        // JPanel objects
        topPanel = new JPanel();

        // set background colour to panel
        topPanel.setBackground(blueBackground);

        // setting the size of the panel
        topPanel.setBounds(0, 0, 1000, 68);

        this.add(topPanel);

        blueLine = BorderFactory.createLineBorder(blueBackground);
        raisedBevel = BorderFactory.createRaisedBevelBorder();

        topPanel.setLayout(null);

        // button objects
        submitBtn = new JButton("Submit");
        cancelBtn = new JButton("Cancel");

        submitBtn.setBackground(blueBackground);
        cancelBtn.setBackground(blueBackground);

        submitBtn.setForeground(Color.WHITE);
        cancelBtn.setForeground(Color.WHITE);

        contentPanel = new JPanel();

        // JLabel objects
        MicroStarLabel = new JLabel("Micro-Star Cable-Vision");
        ComplaintFormLabel = new JLabel("Complaint Form");

        // text fields objects
        lodgeComplaintTxtArea = new JTextArea();
        complaintTxtAreaScrollPane = new JScrollPane(lodgeComplaintTxtArea);

        // JLabel objects
        selectTypeOfServiceLabel = new JLabel("Select Type of Service");
        lodgeComplaintLabel = new JLabel("Lodge Complaint");
        selectMethodOfContactLabel = new JLabel("Select Contact Method");

        // a string array of service list items
        selectTypeOfServiceCombobox = new JComboBox<>(typesOfService);
        selectMethodOfContactCombobox = new JComboBox<>(methodsOfContact);

        // set one of the options as selected
        selectTypeOfServiceCombobox.setSelectedItem(0);
        selectMethodOfContactCombobox.setSelectedItem(0);

        // setting the size of the labels
        selectTypeOfServiceLabel.setBounds(10, 100, 150, 20);
        lodgeComplaintLabel.setBounds(10, 160, 150, 20);
        selectMethodOfContactLabel.setBounds(10, 250, 150, 20);

        // setting the size of text field sizes
        MicroStarLabel.setBounds(00, 0, 350, 50);
        ComplaintFormLabel.setBounds(50, 30, 250, 50);

        selectTypeOfServiceCombobox.setBounds(150, 90, 200, 40);
        complaintTxtAreaScrollPane.setBounds(150, 150, 200, 90);
        selectMethodOfContactCombobox.setBounds(150, 250, 200, 40);
        submitBtn.setBounds(150, 300, 95, 40);
        cancelBtn.setBounds(255, 300, 95, 40);

        // adding action listener to Submit button because it requires an action if it
        // is selected
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked");
            }
        });

        // adding action listener to Cancel button because it requires an action if it
        // is selected
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked");
            }
        });

        // centering Micro-Star Cable-Vision and Complaint Form
        MicroStarLabel.setHorizontalAlignment(JLabel.CENTER);
        ComplaintFormLabel.setHorizontalAlignment(JLabel.CENTER);

        // adding attributes
        // lodgeComplaintScroller.add();

        topPanel.add(MicroStarLabel);
        topPanel.add(ComplaintFormLabel);
        contentPanel.add(selectTypeOfServiceCombobox);
        contentPanel.add(complaintTxtAreaScrollPane); // TODO:: fix scroll pane
        contentPanel.add(selectMethodOfContactCombobox);
        contentPanel.add(submitBtn);
        contentPanel.add(cancelBtn);

        // adding labels to panel
        contentPanel.add(selectTypeOfServiceLabel);
        contentPanel.add(lodgeComplaintLabel);
        contentPanel.add(selectMethodOfContactLabel);

        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 0, 600, 680);

        this.add(contentPanel);

        this.setLayout(null);
        this.setSize(400, 400);

        // making GUI visable
        this.setVisible(true);

    }

}
