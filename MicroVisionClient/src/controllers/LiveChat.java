package controllers;

import models.accounts.Account;
import models.chat.Message;
import models.chat._Message;
import models.complaints.Complaint;
import models.users.Customer;
import models.users.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sound.Mp3;
import driver.Driver;
import javazoom.jl.decoder.JavaLayerException;
import models.users._User;
import utilities.ServerRequest;
import utilities.ServerResponse;
import views.livechat.ChatHome;

import java.util.ArrayList;

public class LiveChat {
    static Logger employee = LogManager.getLogger("EmployeeAccess");
    static Logger customer = LogManager.getLogger("CustomerAccess");

    public static void logOnToLiveChat (_User user) {
        //Login to live chat
        ServerRequest<_User> request = new ServerRequest<_User>(ServerRequest.USER_LIVE_CHAT_COMMAND, user);
        Driver.messageConnection.sendRequest(request);

        ArrayList<_User> onlineUsersFromServer = null;

        ServerResponse<ArrayList<_User>> response = Driver.messageConnection.receiveResponse();

        if (response.getMessage().equals("Login Successful")) {
            onlineUsersFromServer = (ArrayList<_User>) response.getData();
            if (user.getClass().getSimpleName().equals("Customer")) {
                //Send a message to all Customers the technicians that are online
                //play an mp3 sound - maybe a ping

                for (_User onlineUser:onlineUsersFromServer) {
                    if (onlineUser.getClass().getSimpleName().equals("Employee")) {
                        Employee employee = (Employee) onlineUser;
                        if (employee.getRole().equals("Technician")) {
                            ChatHome.personsOnlineTextArea.append("Technician " + onlineUser.getUsername() + " is online \n");
                        }else {
                            ChatHome.personsOnlineTextArea.append("Cust. Rep. " + onlineUser.getUsername() + " is online \n");
                        }
                    }
                }
                try {
                    Mp3.playMp3("1");
                }catch (JavaLayerException ex) {
                    System.out.println("Error message to be logged");
                    customer.error(ex.getMessage());
                }
            }else if (user.getClass().getSimpleName().equals("Employee")) {
                //Send a message to all Technicians the customers that are online
                //play an mp3 sound - maybe a ping
                for (_User onlineUser:onlineUsersFromServer) {
                    if (onlineUser.getClass().getSimpleName().equals("Customer")) {
                        ChatHome.personsOnlineTextArea.append("Customer " + onlineUser.getUsername() + " is online \n");
                    }
                }
                try {
                    Mp3.playMp3("1");
                }catch (JavaLayerException ex) {
                    System.out.println("Error message to be logged");
                    employee.error(ex.getMessage());
                }
            }
        }else if (response.getMessage().equals("Login Failed")) {
            ChatHome.personsOnlineTextArea.setText("Failure to Log on to Live Chat");
        }
    }

    public static void logOffLiveChat(_User user) {
        //Log off live chat
        ServerRequest<_User> request = new ServerRequest<_User>(ServerRequest.USER_END_CHAT_COMMAND, user);
        Driver.messageConnection.sendRequest(request);

        ServerResponse response;
        response = Driver.messageConnection.receiveResponse();

        //Check if it was successful

        //Close the message connection socket for the user who wishes to log off
        Driver.messageConnection.closeConnection();
    }

    public static _User findUserFromUsername(String username) {
        _User user = null;
        //Send a request to the server asking who's username is this and respond with the user
        if (Driver.SESSION_TYPE.equals("Customer")) {
            Employee employee = new Employee();
            employee.setUsername(username);
            ServerRequest request = new ServerRequest(ServerRequest.USER_LOAD_COMMAND,employee);
            Driver.messageConnection.sendRequest(request);
            
            ServerResponse response = Driver.messageConnection.receiveResponse();
            user = (_User) response.getData();
        }else if (Driver.SESSION_TYPE.equals("Employee")) {
            Customer customer = new Customer();
            customer.setUsername(username);
            ServerRequest request = new ServerRequest(ServerRequest.USER_LOAD_COMMAND, customer);
            Driver.messageConnection.sendRequest(request);

            ServerResponse response = Driver.messageConnection.receiveResponse();
            user = (_User) response.getData();
        }

        return user;
    }

    public static void loadUserComplaints(_User user) {
        Account userAccount = new Account();
        userAccount.setCustomerId(user.getUserID());
        ServerRequest<Account> request = new ServerRequest<Account>(ServerRequest.ACCOUNT_LOAD_COMMAND, userAccount);
        Driver.clientConnection.sendRequest(request);
        ServerResponse<Account> response = Driver.clientConnection.receiveResponse();

        if (response.getCode() == ServerResponse.REQUEST_SUCCEEDED) {
            userAccount = (Account) response.getData();

            ArrayList<Complaint> listofComplaints = Complaint.loadComplaints();

            for (Complaint complaint:listofComplaints) {
                ChatHome.personsOnlineTextArea.setText("");
                ChatHome.personsOnlineTextArea.append("List of Complaints \n");
                //Match the user to the complaint
                if ( userAccount.getAccountID() == complaint.getAccountId()) {
                    ChatHome.personsOnlineTextArea.append(complaint.toString() + "\n");
                }
            }
        }
    }

    public static Complaint findComplaintFromId(int compaintId) {
        Complaint complaint = new Complaint();
        complaint.setComplaintId(compaintId);
        ServerRequest<Complaint> request = new ServerRequest<Complaint>(ServerRequest.COMPLAINT_lOAD_COMMAND, complaint);
        Driver.messageConnection.sendRequest(request);

        ServerResponse response = Driver.messageConnection.receiveResponse();
        complaint = (Complaint) response.getData();

        return complaint;
    }

    public static void sendMessage(Message message) {
        //Send messages through live chat
        //Check if recipient is online before sending
        ServerRequest<Message> request = new ServerRequest<Message>(ServerRequest.USER_SEND_MESSAGE_LIVE_CHAT_COMMAND,
                message);
        Driver.messageConnection.sendRequest(request);

        //Save Message in Database
        request = new ServerRequest<Message>(ServerRequest.MESSAGE_UPDATE_COMMAND,message);
        Driver.messageConnection.sendRequest(request);
    }

    public static Message receiveMessage() {
        ServerResponse response;
        response = Driver.messageConnection.receiveResponse();

        //Maybe update database with the message

        return (Message) response.getData();
    }
}
