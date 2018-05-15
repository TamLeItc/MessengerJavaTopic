/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.DAO;

import client.DTO.Friend;
import java.util.ArrayList;

/**
 *
 * @author itcde
 */
public class FriendDAO {
    
    //Từ chuỗi String truyền vào bao gồm các thành phần(chứa userName và name của user) được ngăn nhau bởi kí tự ";"
    //Tạo thành một danh sách(ArrayList) bạn bè bao gồm các userName
    public ArrayList<String> getAllFriend(String allFriend){
        String[] temp = allFriend.split(";");
        
        ArrayList<String> friendList = new ArrayList<>();
        for (String item : temp) {
            friendList.add(item);
        }
        
        return friendList;
    }
    
    public String getSqlInsert(Friend friend){
        String sql = "INSERT INTO friend(user_id_1, user_id_2) VALUES(" +
                + friend.getUserId1() + ", " + friend.getUserId2();
        return sql;
    }
    
    public String getSqlDelete(int userName1, int userName2){
        return "";
    }
}
