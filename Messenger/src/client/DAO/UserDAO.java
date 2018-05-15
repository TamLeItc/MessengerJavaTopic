/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.DAO;

import client.DTO.User;

/**
 *
 * @author itcde
 */
public class UserDAO {
    
    //Đưa vào một chuỗi chứa các thông tin của một user. Các thông tin được ngăn cách nhau bởi kí tự "@"
    //Từ thông tin lấy từ chuỗi đó tạo ra một user chứa các thông tin đó
    public User getUserToString(String userString){
        String[] arrayString = userString.split("@");
        User user = new User();
       
        user.setFirstName(arrayString[0]);
        user.setLastName(arrayString[1]);
        user.setBirthDate(arrayString[2]);
        user.setGender(arrayString[3]);
        user.setUserName(arrayString[4]);
        user.setPassword(arrayString[5]);
        user.setEmail(arrayString[6]);
        user.setPathAvartar(arrayString[7]);
        
        return user;
    }
    
    //Truyền vào một user. Từ user đó tạo thành một câu sql
    //Câu sql này được truyền tới server để thực hiện việc update
    public String getStringUpdateUser(User user){
        String sql = "UPDATE user_tb SET first_name = '" + user.getFirstName() + "', last_name = '" + user.getLastName()
                + "', birth_date = '" + user.getBirthDate() + "', gender = '" + user.getGender() +  "', password = '" 
                + user.getPassword() + "', email = '" + user.getEmail() + "', path_avartar = '" + user.getPathAvartar() 
                 + "' WHERE user_name = '" + user.getUserName() + "'";
        return sql;
    }
    
    //Truyền vào một user. Từ user đó tạo thành một câu sql
    //Câu sql này được truyền tới servre để thực hiện việc thêm mới một tài khoản
    public String getStringInsertUser(User user){
        String sql = "INSERT INTO user_tb(first_name, last_name, birth_date, gender, user_name, password, email, "
                + "path_avartar) " + "VALUES('" + user.getFirstName() + "', '" + user.getLastName() + "', '" 
                + user.getBirthDate() + "', '" + user.getGender() + "', '" + user.getUserName() + "', '" + user.getPassword() 
                + "', '" + user.getEmail() + "', '" + user.getPathAvartar() + "')";
        
        return sql;
    }
}
