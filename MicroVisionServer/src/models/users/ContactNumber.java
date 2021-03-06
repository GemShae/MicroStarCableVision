package models.users;

import javax.persistence.*;

@Entity
@Table(name = "contactnum")
public class ContactNumber extends _ContactNumber{
    /**
     *
     */
    private static final long serialVersionUID = 7526981870015571890L;


    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    Customer customer;
    
    
    public ContactNumber(String contactNum, int userId) {
        super(contactNum, userId);
    }

    public ContactNumber() {
        super();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.userId = customer.getUserID();
    }
    
}
