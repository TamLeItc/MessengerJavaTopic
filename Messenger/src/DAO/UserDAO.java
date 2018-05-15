/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import client.Encode;
import server.DBConnection.DBConnection;
import client.DTO.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itcde
 */
public class UserDAO {

    public UserDAO() {
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user_tb";

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                User user = new User();
                user.setId(Integer.parseInt(result.getString(1)));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setBirthDate(result.getString(4));
                user.setGender(result.getString(5));
                user.setUserName(result.getString(6));
                user.setPassword(result.getString(7));
                user.setEmail(result.getString(8));
                user.setPathAvartar(result.getString(9));
                list.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return list;
    }
    
    public ArrayList<User> getUserByColumnCondition(String columnName, String condition) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM user_tb WHERE " + columnName + " LIKE'" + condition + "'";

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                User user = new User();
                user.setId(Integer.parseInt(result.getString(1)));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setBirthDate(result.getString(4));
                user.setGender(result.getString(5));
                user.setUserName(result.getString(6));
                user.setPassword(result.getString(7));
                user.setEmail(result.getString(8));
                user.setPathAvartar(result.getString(9));
                list.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return list;
    }
    
    public User getUserByUserId(String userId){
        User user = new User();
        String sql = "SELECT * FROM user_tb WHERE user_id = " + userId;

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                user.setId(Integer.parseInt(result.getString(1)));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setBirthDate(result.getString(4));
                user.setGender(result.getString(5));
                user.setUserName(result.getString(6));
                user.setPassword(result.getString(7));
                user.setEmail(result.getString(8));
                user.setPathAvartar(result.getString(9));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return user;
    }
    
    public User getUserByUserName(String userName){
        User user = new User();
        String sql = "SELECT * FROM user_tb WHERE user_name = '" + userName + "'";

        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if(result == null){
                return user;
            }
            while (result.next()) {
                user.setId(Integer.parseInt(result.getString(1)));
                user.setFirstName(result.getString(2));
                user.setLastName(result.getString(3));
                user.setBirthDate(result.getString(4));
                user.setGender(result.getString(5));
                user.setUserName(result.getString(6));
                user.setPassword(result.getString(7));
                user.setEmail(result.getString(8));
                user.setPathAvartar(result.getString(9));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return user;
    }
    
    public String getPathAvartarByUserName(String userName){
        String sql = "SELECT path_avartar FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if(result == null){
                return "";
            }
            while (result.next()) {
                return result.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return "";
    }
    
    public int getIdByUserName(String userName){
        String sql = "SELECT id FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if(result == null){
                return -1;
            }
            while (result.next()) {
                return result.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return -1;
    }
    
    public String getUserNameById(int id){
        String sql = "SELECT user_name FROM user_tb WHERE id =" + id + "";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if(result == null){
                return "";
            }
            while (result.next()) {
                return result.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return "";    }
    
    public boolean checkExistByUserName(String userName){
        String sql = "SELECT user_name FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if(result == null){
                return false;
            }
            while (result.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return false;
    }
    
    public boolean checkPassword(String userName, String password){
        String pass = Encode.encryptMD5(password);
        
        String sql = "SELECT password FROM user_tb WHERE user_name = '" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                if(result.getString(1).equals(pass)){
                    dbc.close();
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
    
    public boolean addNew(User user){
        
        String sql = "INSERT INTO user_tb(first_name, last_name, birth_date, gender, username, password, email, "
                + "path_avartar) " + "VALUES('" + user.getFirstName() + "', '" + user.getLastName() 
                + "', '" + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getPathAvartar() 
                + "'";
        
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.insert(sql)){
            dbc.close();
            return false;
        }
        dbc.close();
        return true;
    }
    
    //Có thể cập nhật tên, mật khẩu, đường dẫn ảnh đại diện
    //Ko thể cập nhật userName, ngày tạo tài khoản
    public boolean updateUserById(User user){
        
        String sql = "UPDATE user_tb SET first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName()
                + "', birth_date = '" + user.getBirthDate() + "', gender = '" + user.getGender() + "', email = '" 
                + user.getEmail() + "' WHERE user_name = '" + user.getUserName() + "'";
        
        DBConnection dbc = new DBConnection();
        dbc.open();
        if(dbc.update(sql)){
            dbc.close();
            return false;
        }
        dbc.close();
        
        return true;
    }
    
    public boolean deleteUserByUserName(String userName){
        String sql = "DELETE FROM user_tb WHERE user_name = " + userName;
        
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
