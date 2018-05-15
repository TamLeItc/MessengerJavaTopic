/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.DAO;

import client.DTO.Message;

/**
 *
 * @author itcde
 */
public class MessageDAO {
    
    public String getSqlInsert(Message message){
        String sql = "INSERT INTO personal_message(sender_id, received_id, content) VALUES(" + message.getSenderId() 
                + ", " + message.getReceivedId() + ", '" + message.getContent() + "')";
        return sql;
    }
}
