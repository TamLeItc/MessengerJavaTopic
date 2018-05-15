/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import server.DBConnection.DBConnection;
import client.DTO.Message;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itcde
 */
public class PersonalMessageDAO {
    public PersonalMessageDAO(){}
    
    public ArrayList<Message> getAllMessageBySenderId(int senderId){
        ArrayList<Message> list = new ArrayList<>();
        
        String sql = "SELECT * FROM personal_message WHERE sender_id = " + senderId;

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                Message message = new Message();
                message.setId(Integer.parseInt(result.getString(1)));
                message.setSenderId(Integer.parseInt(result.getString(2)));
                message.setReceivedId(Integer.parseInt(result.getString(3)));
                message.setContent(result.getString(4));
                
                list.add(message);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return list;
    }
    
    public ArrayList<Message> getAllMessageByTwoUserNameSender(String userNameSender1, String userNameSender2){
        
        ArrayList<Message> list = new ArrayList<>();
        
        int senderId = new UserDAO().getIdByUserName(userNameSender1);
        int receiverId = new UserDAO().getIdByUserName(userNameSender2);
        
        
        String sql = "SELECT * FROM personal_message WHERE sender_id = " + senderId + " OR sender_id = " + receiverId;
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                Message message = new Message();
                message.setId(Integer.parseInt(result.getString(1)));
                message.setSenderId(Integer.parseInt(result.getString(2)));
                message.setReceivedId(Integer.parseInt(result.getString(3)));
                message.setContent(result.getString(4));
                
                list.add(message);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return list;
    }
    
    public boolean addNewByUserNameSenderAndReveiverSender(String userNameSender, String receiverSender, String content){
        int senderId = new UserDAO().getIdByUserName(userNameSender);
        int receiverId = new UserDAO().getIdByUserName(receiverSender);
        
        
        String sql = "INSERT INTO personal_message(sender_id, received_id, content) VALUES(" + senderId
                + ", " + receiverId + ", '" + content + "')";
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.insert(sql)){
            dbc.close();
            return true;
        }
        dbc.close();
        return false;
    }
    
    public boolean addNew(Message message){
        String sql = "INSERT INTO personal_message(sender_id, received_id, content) VALUES(" + message.getSenderId() 
                + ", " + message.getReceivedId() + ", '" + message.getContent() + "')";
        
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.insert(sql)){
            dbc.close();
            return false;
        }
        dbc.close();
        return true;
    }
    
}
