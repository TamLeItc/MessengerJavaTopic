/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.DAO;

import server.DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itcde
 */
public class FriendDAO {
    
    public String getAllFriendByUserName(String userName){
        int userId = new UserDAO().getIdByUserName(userName);
        
        String sql = "SELECT user_id_1, user_id_2 FROM friend WHERE user_id_1 = " + userId + " OR user_id_2 = " + userId;
        String friendList = "";
        
        UserDAO userDao = new UserDAO();
        
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while(result.next()){
                if(result.getInt(1) == userId){
                    String userNameOfUser = userDao.getUserNameById(result.getInt(2));
                    friendList += userNameOfUser + "@" + userDao.getNameOfUserByUserName(userNameOfUser) +";";
                }
                else {
                    String userNameOfUser = userDao.getUserNameById(result.getInt(1));
                    friendList += userNameOfUser + "@" + userDao.getNameOfUserByUserName(userNameOfUser) +";";
                }
            }
        } catch (SQLException e) {
        }
        finally{
            dbc.close();
        }
        
        return friendList;
    }
    
    //Truyền vào userName của 2 người dùng. Kiểm tra họ có phải là bạn của nhau hay không
    public boolean checkIsFriend(String userName1, String userName2){
        int userId1 = new UserDAO().getIdByUserName(userName1);
        int userId2 = new UserDAO().getIdByUserName(userName2);
        
        String sql = "SELECT * FROM friend WHERE (user_id_1 = " + userId1 + " AND user_id_2 = " + userId2 + ")"
            + "OR (user_id_1 = " + userId2 + " AND user_id_2 = " + userId1 + ")";
        boolean check = false;
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return check;
    }
    
    public boolean addNew(String userName1, String userName2){
        
        int userId1 = new UserDAO().getIdByUserName(userName1);
        int userId2 = new UserDAO().getIdByUserName(userName2);
        
        String sql = "INSERT INTO friend(user_id_1, user_id_2) VALUES(" +
                + userId1 + ", " + userId2 + ")";
        System.out.println(sql);
        
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.insert(sql)){
            dbc.close();
            return true;
        }
        dbc.close();
        return false;
    }
    
    public int delete(String sql){
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.delete(sql)){
            dbc.close();
            return 0;
        }
        dbc.close();
        return 1;
    }
}
