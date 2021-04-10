package driver; 
import utilities.*;

import controllers.Authentication;
import java.util.ArrayList;

import javax.swing.JFrame;

import models.users.*;
import utilities.communication.*;
import views.customer.CustomerDashboard;
import views.customer.Dashboard;
import views.customer.SignIn;
import views.customerrepresentative.AdminDashboard;
import views.customerrepresentative.RepDashboard;
import views.technician.TechDashboard;

public class Driver extends JFrame{
    public static Client clientConnection; //socket connection for general purpose use in the app
    public static Client messageConnection; //socket connection for use with chat
    public static _User CURRENT_USER; // the currently logged in user
    public static String SESSION_ID; //identifies a session based on user-id and a random string 
    public static String SESSION_TYPE; //Whether a use is an employee or a customer
    public static String APP_NAME = "MicroStar Cable Vision";
    public static JFrame FRAME = null;

 
    
    public Driver() {
        super(Driver.APP_NAME);
        clientConnection = new Client();
    }
    
    public Driver(String title) {
        super(title);
        clientConnection = new Client();
    }


    
    public static void main(String[] args) throws Exception {
        
        Driver driver = new Driver();

        Driver.FRAME = driver;

        SignIn signInDialog = new SignIn(driver);

        // System.out.println(Driver.SESSION_TYPE);
        // signInDialog.setVisible(true);

        // checking if its customer
        if (SESSION_TYPE.equals("Customer"))  {

            CustomerDashboard dashboard = new CustomerDashboard();
            dashboard.setBounds(0, 0, 700, 700);
            
            driver.add(dashboard);

        // checking if the employee is "Rep", "Customer Rep" or "Tech" and supply the with their respective dashboard
        } else if(SESSION_TYPE.equals("Employee")) {
            
            if (((Employee)CURRENT_USER).getRole().equals(Employee.ROLE_CUSTOMER_REP)) {
                
                RepDashboard repDashboard = new RepDashboard();
                repDashboard.setBounds(0, 0, 700, 700);
                
                driver.add(repDashboard);
                
            } else if(((Employee)CURRENT_USER).getRole().equals(Employee.ROLE_ADMIN)) {

                AdminDashboard adminDashboard = new AdminDashboard();
                adminDashboard.setBounds(0, 0, 700, 700);
                
                driver.add(adminDashboard);
            

            } else {
                
                TechDashboard techDashboard = new TechDashboard();
                techDashboard.setBounds(0, 0, 700, 700);
                
                driver.add(techDashboard);
                
            }
            
        }

        // set window properties
        driver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close app when window is closed
        driver.setSize(500, 500); // set panel window size
        driver.setLayout(null);
        driver.setVisible(true);


/*
        System.out.println("Hello, World!");
        Authentication auth =  new Authentication();
        auth.login("Abi", "Gordon"); 
        ArrayList<Customer> customers = Customer.loadCustomers();

        Customer customer = new Customer();
        customer.save();
        */
    }
}
