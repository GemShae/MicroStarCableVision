package models.complaints;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.*;

// Annotations
@MappedSuperclass
public abstract class _Complaint implements Serializable {

    public static final String COMPLAINT_STATUS_UNRESOLVED = "Unresolved";
    public static final String COMPLAINT_STATUS_RESOLVED = "resolved";

    public static final String COMPLAINT_ISSUE_CONNECTIVITY = "Connectivity";
    public static final String COMPLAINT_ISSUE_DEVICE = "Device";
    public static final String COMPLAINT_ISSUE_SERVICE = "Missing service";
    public static final String COMPLAINT_ISSUE_OUTAGE = "Outage";

    @Id
    @Column(name = "complaint_id")
    protected int complaintId;

    @Column(name = "status")
    protected String status;

    @Column(name = "details_of_issue")
    protected String details;

    @Column(name = "type_of_issue")
    protected String typeOfIssue;

    @Column(name = "date_raised")
    protected LocalDateTime dateRaised;

    @Column(name = "account_id")
    protected int accountId;

    @Transient
    protected ArrayList<String> validation_errors;

    public _Complaint() {
    }

    /**
     * validate members to ensure all dat falls within acceptable ranges
     * @return
     */
    public boolean validate(){
        boolean valid = true;

        // check if each fields data is valid
        if( !( this.status.equals(_Complaint.COMPLAINT_STATUS_RESOLVED) ) && !( this.status.equals(_Complaint.COMPLAINT_STATUS_UNRESOLVED) ) ){
            
            this.validation_errors.add("Invalid issue status entered.");
            valid = false;
        }

        if( !( this.status.equals(_Complaint.COMPLAINT_ISSUE_CONNECTIVITY) ) && !( this.status.equals(_Complaint.COMPLAINT_ISSUE_DEVICE) ) && !( this.status.equals(_Complaint.COMPLAINT_ISSUE_OUTAGE) ) && !( this.status.equals(_Complaint.COMPLAINT_ISSUE_SERVICE) )){
            
            this.validation_errors.add("Invalid issue type entered.");
            
            valid = false;
        }
        
        if(this.details.isBlank()){
            
            this.validation_errors.add("Detaiks cannot be blank.");
            
            valid = false;
        }
        return valid;

    }

    public _Complaint(int complaintId, String status, String details, String typeOfIssue, LocalDateTime dateRaised, int accountId) {
        this.complaintId = complaintId;
        this.status = status;
        this.details = details;
        this.typeOfIssue = typeOfIssue;
        this.dateRaised = dateRaised;
        this.accountId = accountId;
    }

    public int getComplaintId() {
        return this.complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTypeOfIssue() {
        return this.typeOfIssue;
    }

    public void setTypeOfIssue(String typeOfIssue) {
        this.typeOfIssue = typeOfIssue;
    }

    public LocalDateTime getDateRaised() {
        return this.dateRaised;
    }

    public void setDateRaised(LocalDateTime dateRaised) {
        this.dateRaised = dateRaised;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public _Complaint complaintId(int complaintId) {
        setComplaintId(complaintId);
        return this;
    }

    public _Complaint status(String status) {
        setStatus(status);
        return this;
    }

    public _Complaint details(String details) {
        setDetails(details);
        return this;
    }

    public _Complaint typeOfIssue(String typeOfIssue) {
        setTypeOfIssue(typeOfIssue);
        return this;
    }

    public _Complaint dateRaised(LocalDateTime dateRaised) {
        setDateRaised(dateRaised);
        return this;
    }

    public _Complaint accountId(int accountId) {
        setAccountId(accountId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof _Complaint)) {
            return false;
        }
        _Complaint _Complaint = (_Complaint) o;
        return complaintId == _Complaint.complaintId && Objects.equals(status, _Complaint.status) && Objects.equals(details,
                _Complaint.details) && Objects.equals(typeOfIssue, _Complaint.typeOfIssue) && Objects.equals(dateRaised,
                _Complaint.dateRaised) && accountId == _Complaint.accountId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(complaintId, status, details, typeOfIssue, dateRaised, accountId);
    }

    @Override
    public String toString() {
        return "{" +
                " complaintId='" + getComplaintId() + "'" +
                ", status='" + getStatus() + "'" +
                ", details='" + getDetails() + "'" +
                ", typeOfIssue='" + getTypeOfIssue() + "'" +
                ", dateRaised='" + getDateRaised() + "'" +
                ", accountId='" + getAccountId() + "'" +
                "}";
    }
}
