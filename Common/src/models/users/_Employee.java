package models.users;

import javax.persistence.*;

import utilities.Validator;


// Annotations
// @Entity
// @Table(name = "employee")
// @PrimaryKeyJoinColumn(name = "emp_id")
@MappedSuperclass
public class _Employee extends _User {
    // @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
    /**
     *
     */
    private static final long serialVersionUID = 3820461434905932966L; 
    
    public static final String ROLE_CUSTOMER_REP = "Customer Service Rep";
    public static final String ROLE_TECH = "Technician";
    public static final String ROLE_ADMIN = "Admin";

    // @GeneratedValue(strategy=GenerationType.AUTO)
    // @Id
    // @Column(name="emp_id")
    // int emp_id;

    @Column(name="role")
    protected String role;

    @Column(name="status")
    protected String status;


    // public abstract boolean registerUser(_User user);


    @Override
    public boolean validate() {
        //TODO implement individual validators
        boolean valid = super.validate();

        // check if each fields data is valid
        if( !( this.role.equals(_Employee.ROLE_ADMIN) ) && !( this.role.equals(_Employee.ROLE_CUSTOMER_REP) ) && !( this.role.equals(_Employee.ROLE_TECH) )){
            this.validation_errors.add("Invalid email entered.");
            valid = false;
        }
        return valid;
    }
    
    
    /**---------------------------------------------------------------------------------- */

    // default constructor
    public _Employee() {
        super();
        this.role = "";
        this.status = "";
    }

    // primary constructor
    public _Employee(int userID, String username, String firstName, String lastName, String middleName, String password, int age,
            String gender, String role, String status) {
        super(userID, username, firstName, lastName, middleName, password, age, gender);
        this.role = role;
        this.status = status;
    }

    // copy constructor
    public _Employee(_Employee employee) {
        super(
            employee.getUserID(),
            employee.getUsername(),
            employee.getfirstName(),
            employee.getlastName(),
            employee.getmiddleName(),
            employee.getPassword(),
            employee.getAge(),
            employee.getGender()
        );
        this.role = employee.role;
        this.status = employee.status;
    }

    // getters & setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee [role=" + role + ", status=" + status + "]";
    }

}
