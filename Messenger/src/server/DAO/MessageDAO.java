/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.DAO;

import server.DBConnection.DBConnection;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itcde
 */
public class MessageDAO {
    
    //Từ 2 userName. Trả về mỗi chuỗi trong đó chứa các tin nhắn được định dạng thẻ <font> màu để hiển thị lên JEditorText
    //userNameSender1 được xem là user hiện tại, userNameSender2 là một user nào đó
    //Tin nhắn của userNameSender1 sẽ có màu xanh. userNameSender2 sẽ có màu den
    //Mỗi tin nhắn chưa thông tin: Người nhận, Người gửi, Nội dung
    //Ko chứa id của mỗi tin nhắn
    public String getAllMessageByTwoUserName(String userName1, String userName2){
        
        String allMessage = "";
        
        int senderId1 = new UserDAO().getIdByUserName(userName1);
        int senderId2 = new UserDAO().getIdByUserName(userName2);
        
        String sql = "SELECT * FROM personal_message WHERE (sender_id = " + senderId1 + " AND received_id = " + senderId2
                + ") OR (sender_id = " + senderId2 + " AND received_id = " + senderId1 + ")";
        DBConnection dbc = new DBConnection();
        try {
            dbc.open();
            ResultSet result = dbc.select(sql);
            
            while (result.next()) {
                String message = "";
                //Tin nhắn gồm 3 thành phần
                int senderId = Integer.parseInt(result.getString(2));
                int receiverId = Integer.parseInt(result.getString(3));
                String content = result.getString(4);
                
                //Thêm thẻ font vào tin nhắn
                if(senderId == senderId2){
                    message = "<right><font color=\"rgb(51, 51, 51)\">" + content + "</font></right></left> <br/>";
                }
                else{
                    message = "<font color=\"rgb(0, 102, 255)\">" + content + "</font> <br/>";
                }
                
                allMessage += message;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            dbc.close();
        }

        return allMessage;
    }
    
    public boolean addNew(String userNameSender, String userNameReceiver, String content){
        int senderId = new UserDAO().getIdByUserName(userNameSender);
        int receiverId = new UserDAO().getIdByUserName(userNameReceiver);
        
        String sql = "INSERT INTO personal_message(sender_id, received_id, content) VALUES(" + senderId + ", " + receiverId + ", '" 
                + content + "')";
        System.out.println(sql);
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
