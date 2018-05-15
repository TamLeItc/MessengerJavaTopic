/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.DAO;

import client.Encode;
import server.DBConnection.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itcde
 */
public class UserDAO {

    //Trả về một chuỗi chứa thông tin của user
    //Chuỗi này ko có id. Nên chỉ lấy từ cột thứ 2
    public String getAllInfoOfUser(String userName) {
        
        String sql = "SELECT * FROM user_tb WHERE user_name = '" + userName + "'";
        String userInfo = "";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if (result == null) {
                return "";
            }

            result.next();
            userInfo += result.getString(2) + "@";
            userInfo += result.getString(3) + "@";
            userInfo += result.getString(4) + "@";
            userInfo += result.getString(5) + "@";
            userInfo += result.getString(6) + "@";
            userInfo += result.getString(7) + "@";
            userInfo += result.getString(8) + "@";
            userInfo += result.getString(9) + "@";

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            userInfo = "ERROR";
        } finally {
            dbc.close();
        }
        return userInfo;
    }
    
    public String getNameOfUserByUserName(String userName){
        String sql = "SELECT first_name, last_name FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if(result == null){
                return "";
            }
            while (result.next()) {
                return (result.getString(1) + " " + result.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }
        return "";
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
    
    public int getIdByUserName(String userName) {
        String sql = "SELECT id FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        int id = 0;
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if (result != null) {
                while (result.next()) {
                    id = result.getInt(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbc.close();
        }
        return id;
    }
    
    public String getPasswordByUserName(String userName){
        String sql = "SELECT password FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if (result != null) {
                while (result.next()) {
                    return result.getString(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbc.close();
        }
        return "";
    }
    
    public String getEmailByUserName(String userName){
        String sql = "SELECT email FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if (result != null) {
                while (result.next()) {
                    return result.getString(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            dbc.close();
        }
        return "";
    }
    
    public String getUserNameById(int userId){
        String sql = "SELECT user_name FROM user_tb WHERE id = " + userId + "";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if (result != null) {
                while (result.next()) {
                    return result.getString(1);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbc.close();
        }
        return "";
    }

    public boolean checkExistByUserName(String userName) {
        String sql = "SELECT user_name FROM user_tb WHERE user_name ='" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            if (result == null) {
                return false;
            } else {
                while (result.next()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbc.close();
        }
        return false;
    }

    public boolean checkLogin(String userName, String password) {
        String pass = Encode.encryptMD5(password);
        boolean check = false;

        String sql = "SELECT password FROM user_tb WHERE user_name = '" + userName + "'";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            while (result.next()) {
                if (result.getString(1).equals(pass)) {
                    check = true;
                }
            }

        } catch (SQLException ex) {
            //Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbc.close();
        }
        return check;
    }

    public boolean addNew(String sql) {
        DBConnection dbc = new DBConnection();
        dbc.open();
        if (dbc.insert(sql)) {
            dbc.close();
            return true;
        }
        dbc.close();
        return false;
    }

    
    public boolean update(String sql) {

        DBConnection dbc = new DBConnection();
        dbc.open();
        System.out.println(sql);
        if (dbc.update(sql)) {
            dbc.close();
            return true;
        }
        dbc.close();
        return false;
    }
}
