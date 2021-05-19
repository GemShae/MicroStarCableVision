package views.customerrepresentative;

import driver.Driver;
import models.users.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.technician.TechDashboard;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewCustomerDetails extends JDialog {

    /** -------------------------MEMBERS------------------------------- */

    // container
    JPanel contentPanel;

    // labels
    JLabel customerIDLabel;
    JLabel nameLabel;
    JLabel emailAddressLabel;
    JLabel contactNumberLabel;
    JLabel typeOfIssueLabel;
    JLabel detailsOfIssueLabel;
    JLabel microStarLabel;
    JLabel customerDetailsLabel;

    // inputs
    JButton DashboardBtn;

    Logger employee = LogManager.getLogger("EmployeeAccess");

    /** -------------------------CONSTRUCTORS------------------------------- */

    public ViewCustomerDetails(Frame parent) {

        super(parent, "Customer Details", true);

        contentPanel = new JPanel();

        // JLabel objects
        microStarLabel = new JLabel("Micro-Star Cable-Vision");
        customerDetailsLabel = new JLabel("Customer Details");

        // text field objects
        DashboardBtn = new JButton("Dashboard");

        // JLabel objects
        customerIDLabel = new JLabel("Customer ID");
        nameLabel = new JLabel("Name");
        emailAddressLabel = new JLabel("Email Address");
        contactNumberLabel = new JLabel("Contact Number");
        typeOfIssueLabel = new JLabel("Type of Issue");
        detailsOfIssueLabel = new JLabel("Details of Issue");

        JLabel customerIDLabelField = new JLabel("12345");
        JLabel nameLabelField = new JLabel("Jane Doe");
        JLabel emailAddressLabelField = new JLabel("janedoe@gmail.com");
        JLabel contactNumberLabelField = new JLabel("(876) 123-4567");
        JLabel typeOfIssueLabelField = new JLabel("Fire");
        JLabel detailsOfIssueLabelField = new JLabel("Cord caught on fire");

        // setting the size of the labels
        microStarLabel.setBounds(00, 0, 350, 50);
        customerDetailsLabel.setBounds(50, 30, 250, 50);
        customerIDLabel.setBounds(10, 100, 150, 20);
        nameLabel.setBounds(10, 140, 150, 20);
        emailAddressLabel.setBounds(10, 180, 150, 20);
        contactNumberLabel.setBounds(10, 220, 150, 20);
        typeOfIssueLabel.setBounds(10, 260, 150, 20);
        detailsOfIssueLabel.setBounds(10, 300, 150, 20);

        // setting the size of text field sizes
        customerIDLabelField.setBounds(160, 100, 150, 20);
        nameLabelField.setBounds(160, 140, 150, 20);
        emailAddressLabelField.setBounds(160, 180, 150, 20);
        contactNumberLabelField.setBounds(160, 220, 150, 20);
        typeOfIssueLabelField.setBounds(160, 260, 150, 20);
        detailsOfIssueLabelField.setBounds(160, 300, 150, 20);

        DashboardBtn.setBounds(160, 340, 100, 40);

        DashboardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dashboard Button clicked");
                employee.info("Dashboard Button Clicked.");
                dispose();

                if (((Employee) Driver.CURRENT_USER).getRole().equals(Employee.ROLE_CUSTOMER_REP)) {
                    employee.info("Returning to Rep Dashboard");
                    RepDashboard repDashboard = new RepDashboard();
                    repDashboard.setBounds(0, 0, 700, 700);
                    Driver.FRAME.add(repDashboard);
                }else if (((Employee)Driver.CURRENT_USER).getRole().equals(Employee.ROLE_ADMIN)) {
                    employee.info("Returning to Admin Dashboard");
                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setBounds(0, 0, 700, 700);
                    Driver.FRAME.add(adminDashboard);
                }else {
                    employee.info("Returning to Tech Dashboard");
                    TechDashboard techDashboard = new TechDashboard();
                    techDashboard.setBounds(0, 0, 700, 700);
                    Driver.FRAME.add(techDashboard);
                }
            }
        });

        // centering Micro-Star Cable-Vision and Complaint Form
        microStarLabel.setHorizontalAlignment(JLabel.CENTER);
        customerDetailsLabel.setHorizontalAlignment(JLabel.CENTER);

        // adding attributes
        contentPanel.add(microStarLabel);
        contentPanel.add(customerDetailsLabel);
        contentPanel.add(customerIDLabel);
        contentPanel.add(customerIDLabelField);
        contentPanel.add(nameLabel);
        contentPanel.add(nameLabelField);
        contentPanel.add(emailAddressLabel);
        contentPanel.add(emailAddressLabelField);
        contentPanel.add(contactNumberLabel);
        contentPanel.add(contactNumberLabelField);
        contentPanel.add(typeOfIssueLabel);
        contentPanel.add(typeOfIssueLabelField);
        contentPanel.add(detailsOfIssueLabel);
        contentPanel.add(detailsOfIssueLabelField);
        contentPanel.add(DashboardBtn);

        contentPanel.setLayout(null);
        contentPanel.setBounds(0, 0, 600, 680);

        this.add(contentPanel);
        this.setSize(400, 400);

        this.setLayout(null);

        // making GUI visable
        this.setVisible(true);

        
    }


    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("test");
    //     frame.setVisible(true);
    //     ViewCustomerDetails viewCustomerDetails = new ViewCustomerDetails(frame);
    // }
}
