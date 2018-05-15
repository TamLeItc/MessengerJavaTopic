/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author itcde
 */
public class DBConnection {
    private Connection _con;
    private String _driver = "com.mysql.jdbc.Driver";
    private String _connectionUrl = "jdbc:mysql://localhost:3306/messenger";
    private Statement _statement;
    private ResultSet _result;
    
    
    public DBConnection(){ 
    }
    
    public void open(){
        try {
            Class.forName(_driver);
            this._con = DriverManager.getConnection(_connectionUrl, "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            connectFailed("Connect data failed");
        }
        
    }
    
    public ResultSet select(String sql){
        try {
            _statement = this._con.createStatement();
            _result = _statement.executeQuery(sql); 
            
        } catch (SQLException ex) {
            connectFailed("Loading data failed");
        }catch(java.lang.NullPointerException e){
            return null;
        }
        
        return _result;
    }
    
    public boolean insert(String sql){
        try {
            _statement = this._con.createStatement();
            this._statement.executeUpdate(sql);
            
        } catch (SQLException ex) {
            connectFailed("Add new failed");
            return false;
        }
        return true;
    }
    
    public boolean update(String sql){
        try {
            _statement = this._con.createStatement();
            this._statement.executeUpdate(sql);
        } catch (SQLException ex) {
            connectFailed("Update data failed");
            return false;
        }
        return true;
    }
    
    public boolean delete(String sql){
        try {
            _statement = this._con.createStatement();
            this._statement.executeUpdate(sql);
        } catch (SQLException ex) {
            connectFailed("Delete data failed");
            return false;
        }
        return true;
    }
    
    public void close(){
        try {
            if(this._con != null){
                this._con.close();
            }
            if(this._statement != null){
                this._statement.close();
            }
            if(this._result != null){
                this._result.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void connectFailed(String str){
        JOptionPane.showMessageDialog(null, str, "Error database connect", JOptionPane.ERROR_MESSAGE);
    }
}
