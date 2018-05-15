/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.DTO;

import java.util.Date;

/**
 *
 * @author itcde
 */
public class Message {
    private int _id;
    private int _senderId;
    private int _receivedId;
    private String _content;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getSenderId() {
        return _senderId;
    }

    public void setSenderId(int _senderId) {
        this._senderId = _senderId;
    }

    public int getReceivedId() {
        return _receivedId;
    }

    public void setReceivedId(int _receivedId) {
        this._receivedId = _receivedId;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String _content) {
        this._content = _content;
    }
    
    public Message(){}
    
    public Message(int id, int senderId, int receivedId, String content){
        this._id = id;
        this._senderId = senderId;
        this._receivedId = receivedId;
        this._content = content;
    }
}
