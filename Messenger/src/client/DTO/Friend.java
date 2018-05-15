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
public class Friend {
    private int _id;
    private int _userId1;
    private int _userId2;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getUserId1() {
        return _userId1;
    }

    public void setUserId1(int _userId1) {
        this._userId1 = _userId1;
    }

    public int getUserId2() {
        return _userId2;
    }

    public void setUserId2(int _userId2) {
        this._userId2 = _userId2;
    }
    
    public Friend(){}
    
    public Friend(int userId1, int userId2){
        this._userId1 = userId1;
        this._userId2 = userId2;
    }
    
    
}
