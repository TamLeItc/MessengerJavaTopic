/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import server.DBConnection.DBConnection;
import client.DTO.Friend;
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
public class FriendDAO {
    public FriendDAO(){}
    
    //Truyền vào id của một người dùng. Lấy ra toàn bộ bạn bè của người đó
    public ArrayList<Friend> getAllFriendByUserId(int userId){
        ArrayList<Friend> list = new ArrayList<>();
        
        String sql = "SELECT * FROM friend WHERE user_id_1 = " + userId;

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                Friend friend = new Friend();
                friend.setId(Integer.parseInt(result.getString(1)));
                friend.setUserId1(Integer.parseInt(result.getString(2)));
                friend.setUserId2(Integer.parseInt(result.getString(3)));
                
                list.add(friend);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return list;
    }
    
    //Truyền vào id của 2 người dùng. Kiểm tra họ có phải là bạn của nhau hay không
    public boolean checkIsFriend(int userId1, int userId2){
        String sql = "SELECT * FROM message WHERE user_id_1 = " + userId1;

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                if(Integer.parseInt(result.getString(3)) == userId2){
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return false;
    }
    
    public boolean addNew(Friend friend){
        String sql = "INSERT INTO friend(user_id_1, user_id_2, date_of_making_friend) VALUES(" +
                + friend.getUserId1() + ", " + friend.getUserId2();
        
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.insert(sql)){
            dbc.close();
            return false;
        }
        dbc.close();
        return true;
    }
    
    public boolean deleteById(int id){
        String sql = "DELETE FROM friend WHERE id = " + id;
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.delete(sql)){
            dbc.close();
            return false;
        }
        dbc.close();
        return true;
    }
    
  
}
