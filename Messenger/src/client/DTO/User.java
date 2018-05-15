/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.DTO;

import client.Encode;


/**
 *
 * @author itcde
 */
public class User {
    private int _id;
    private String _firstName;
    private String _lastName;
    private String _birthDate;
    private String _gender;
    private String _userName;
    private String _password;
    private String _email;
    private String _pathAvartar;

    public void setId(int id){
        this._id = id;
    }
    
    public int getId() {
        return _id;
    }
    
    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String getBirthDate() {
        return _birthDate;
    }

    public void setBirthDate(String _birthDate) {
        this._birthDate = _birthDate;
    }

    public String getGender() {
        return _gender;
    }

    public void setGender(String _gender) {
        this._gender = _gender;
    }
    
    

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }
    
    

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String _userName) {
        this._userName = _userName;
    }

    public String getPassword() {
        return this._password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getPathAvartar() {
        return _pathAvartar;
    }

    public void setPathAvartar(String _avartar) {
        this._pathAvartar = _avartar;
    }
    
    public User(){}
    
    @SuppressWarnings("empty-statement")
    public User(String firstName, String lastName, String birthDate, String gender, String userName,
            String password, String email, String pathAvartar){
        this._firstName = firstName;
        this._lastName = lastName;
        this._birthDate = birthDate;
        this._gender = gender;
        this._userName = userName;
        this._password = Encode.encryptMD5(password);
        this._email = email;
        this._pathAvartar = pathAvartar;
    }
}
