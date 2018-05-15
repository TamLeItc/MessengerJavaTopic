/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.DAO.FriendDAO;
import client.DAO.UserDAO;
import client.DTO.User;
import client.GUI.FrmLogin;
import client.GUI.Main;
import client.GUI.FrmMessage;
import client.GUI.small.Online;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javax.swing.JOptionPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

/**
 *
 */
public class Client extends Thread {

    BufferedReader in;
    PrintWriter out;
    private OutputStream _dataOut;
    private InputStream _dataIn;

    private String _serverAddress = "localhost";

    private Main _main;
    private FrmLogin _frmLogin;
    public static Client CLIENT;

    public static FrmMessage FORM_MESSAGE;          //Form message
    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static String USERNAME_RECEIVED;

    public static User USER = new User();
    
    public ArrayList<String> friendList = new ArrayList<>();    //Chứa danh sách bạn bè
    public String userOnlineList = "";  //Chứa danh sách user đanh online
    
    public Client(){
        this.start();
    }
    
    /**
     * @param userName
     * @param password
     * @param login
     */
    
    public Client(String userName, String password, FrmLogin login) {

        //UserName của Client
        Client.USERNAME = userName;
        Client.PASSWORD = password;

        FORM_MESSAGE = new FrmMessage();
        //Bắt sự kiện ấn phím "Enter" để gửi tin nhắn
        //Lấy Text của "TXT_MESSAGE" gửi lên Server
        FrmMessage.TXT_MESSAGE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = FrmMessage.TXT_MESSAGE.getText();
                sendMessageToServer(messageToSend(message));
                Online.ALL_MESSAGE += "<font color=\"rgb(0, 102, 255)\">" + message + "</font> <br/>";
                FrmMessage.JEDITOR_PANE_MESSAGE.setText(Online.ALL_MESSAGE);
                FrmMessage.TXT_MESSAGE.setText("");
            }
        });

        _frmLogin = login;
        this.start();
    }

    /**
     * Connects to the server then enters the processing loop.
     *
     */
    @Override
    public void run() {
        // Make connection and initialize streams
        Socket socket;
        try {
            socket = new Socket(_serverAddress, 9001);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            //Tạo stream data cho client
            _dataOut = socket.getOutputStream();
            _dataIn = socket.getInputStream();
            
            //Nếu userName là một chuỗi khác trống thì mới có thể kiểm tra việc đăng nhập
            //lấy ảnh đại diện, thông tin của user đó
            if(!USERNAME.equals("")){
                if(checkLogin() == false){
                    return;
                }
                //Yêu cầu avartar từ Server
                receiveAvartarFromServer();
                
                //Gửi yêu cầu lấy toàn bộ thông tin của user
                sendMessageToServer("ALL_INFO");
                
                //Gửi yêu cầu lấy toàn bộ bạn bè của user hiện tại
                out.println("ALL_FRIEND"); 
            }

            //Nếu nhận được tin nhắn từ server trong khi client ko gửi yêu cầu gì
            while (!Thread.currentThread().isInterrupted()) {
               
                //Nếu userName khác một chuỗi trống thì mới đọc tin nhắn từ server gửi về ở đây
                //Để khi userName trống. có thể đọc tin nhắn từ một phương thức khác trong khi client vẫn chạy
                if(!USERNAME.equals("")){
                    String line = in.readLine();
                    System.out.println(line);
                    handleInput(line);
                }
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sendMessageToServer("STOP_CLIENT");
                out.close();
                in.close();
                _dataIn.close();
                _dataOut.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    //Xử lí việc đăng nhập của một user
    public boolean checkLogin(){
        //Gửi tin về server yêu cầu đăng nhập
        //Tin nhắn chứa userName, password;
        String newLogin = "LOGIN " + Client.USERNAME + " " + Client.PASSWORD;
        sendMessageToServer(newLogin);

        //Sau khi gửi đi. Nhập lại tin nhắn xem có chấp nhận cho user đăng nhập không
        String accept;
        try {
            accept = in.readLine();
            if (accept.equals("ACCEPT")) {
                _frmLogin.setVisible(false);
                _main = new Main(USERNAME);
                _main.setVisible(true);
                
            } else {
                FrmLogin.JPNL_LOGIN.setVisible(true);
                FrmLogin.LOADING.setVisible(false);
                JOptionPane.showMessageDialog(null, "Incorrect user name or password", "Login failed", JOptionPane.ERROR_MESSAGE);
                this.stop();
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;    
    }
    
    //Xử lí việc nhận tin nhắn từ server
    public void handleInput(String line) {
        if (line.startsWith("NEW_USER") || line.startsWith("OUT_USER")) {
            Main.USER_ONLINE_LIST.removeAll();
            Main.USER_ONLINE_LIST.repaint();
            Main.USER_ONLINE_LIST.revalidate();
            Main.Y = 0;
            
            //Hiển thị toàn bộ danh sách bạn bè đang online
            userOnlineList = line.substring(9);
            _main.loadAllUserONline(userOnlineList, friendList, Client.USERNAME, "");
        }
        //Chuỗi nhận từ Server có dạng: MESSAGE(để nhận biết đây là một tin nhắn) + ...
        //Load tin nhắn này lên Frame thì ta bỏ chuỗi này đi
        else if (line.startsWith("MESSAGE")) {
            String message = line.substring(8);
            handleMessage(message);
            getSoundWhenReceivedMessage("sounds/message.mp3");
            
            
            
        } //Nếu client gửi về tất cả tin nhắn của 2 user như yêu cầu
        else if (line.startsWith("ALL_MESSAGE")) {
            Online.ALL_MESSAGE = line.substring(12);
            FrmMessage.JEDITOR_PANE_MESSAGE.setText(Online.ALL_MESSAGE);
        }
        else if(line.startsWith("ALL_INFO")){
            //Nhận thông tin của tài khoản từ Server
            //Lấy thông tin từ chuỗi nhận về đưa vào USER
            Client.USER = new UserDAO().getUserToString(line.substring(9));
            Main.JLBL_NAME.setText(Client.USER.getFirstName() + " " + Client.USER.getLastName()); 
        }
        //Nếu server gửi thông tin về việc update
        else if(line.startsWith("UPDATE_FAILED")){
            JOptionPane.showMessageDialog(null, "Save information failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Nếu server gửi thông tin về việc insert
        else if(line.startsWith("INSERT_FAILED")){
            JOptionPane.showMessageDialog(null, "Register failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        //Nếu server gửi về danh sách bạn bè
        else if(line.startsWith("ALL_FRIEND")){
            friendList = new FriendDAO().getAllFriend(line.substring(11));
            
            sendMessageToServer("AVARTAR_FRIEND");
        }
        else if(line.startsWith("START_SEND")){
            try {
                String temp = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            receiveAllAvartarOfFriend();
        }
    }
    
    //Gửi tin nhắn tới server. Kiểm tra một user có tồn tại hay chưa thông qua userName
    public boolean checkExistUser(String userName){
        sendMessageToServer("CHECK_USER " + userName);
        try {
            String line = in.readLine();
            System.out.println(line);
            if(line.substring(11).equals("YES")){
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Gửi tin nhắn tới server. Kiểm tra 2 user có phải là bạn bè của nhau hay ko
    public boolean checkIsFriend(String userName1, String userName2){
        Client.CLIENT.sendMessageToServer("IS_FRIEND " + userName1 + " " + userName2);
        try {
            String line = in.readLine();
            System.out.println(line);
            if(line.substring(10).equals("YES")){
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Gửi yêu cầu thực hiện sql update user tới server
    public void updateUser(){
        out.println("UPDATE_USER " + new UserDAO().getStringUpdateUser(USER));
    }
    
    //Gửi yêu cầu thực hiện sql insert user tới server
    public void insertUser(String sql){
        out.println("INSERT_USER " + sql);
    }

    //Lấy toàn bộ thông tin của một user
    //Gửi file avartar mới tới server
    public void sendAvartar(String path) {
        sendMessageToServer("CHANGE_AVARTAR");
        sendFile(path);
    }

    //Nhận ảnh đại diện từ server
    public void receiveAvartarFromServer() {
        //Yêu cầu cung cấp avartar của user hiện tại đến server
        sendMessageToServer("AVARTAR_GET " + Client.USERNAME);
        //Chờ và nhập lại file được gửi từ server
        receivedFile("images/avartars/" + Client.USERNAME + ".jpg");
        //Báo về Server đã nhận được file
        sendMessageToServer("RECEIVED_AVARTAR");
        
        Main.LOAD_AVARTAR(Main.JLBL_AVARTAR, "images/avartars/" + Client.USERNAME + ".jpg", 65, 64);
    }
    
    public void receiveAllAvartarOfFriend(){
        for (String item : friendList) {
            
            //Cắt chuỗi item để lấy userName
            String temp[] = item.split("@");
            String userNameOfFriend = temp[0];
            
            //Yêu cầu cung cấp avartar bạn bè có username = item đến server
            sendMessageToServer("AVARTAR_GET " + userNameOfFriend);
            //Chờ và nhập lại file được gửi từ server
            receivedFile("images/avartars/" + userNameOfFriend + ".jpg");
        }
    }

    //Gửi một file tới server
    public void sendFile(String path) {
        //Lấy đường dẫn Avartar
        //String pathAvartar = new UserDAO().getPathAvartarByUserName(_userName);
        FileInputStream fileInSend = null;
        File file = null;
        try {
            //Đọc ra file để lấy tên của file
            file = new File(path);
            //Lấy file ra để gửi đi
            fileInSend = new FileInputStream(path);
            //Tạo một mảng kiểu byte với độ dài 1024. nghĩa là 1kb
            byte[] buffer = new byte[1024];
            //Tạo biến đếm để chứa độ dài buffer
            int count;
            while ((count = fileInSend.read(buffer)) >= 0) {
                //Gửi file buffer đi bắt đầu từ 0, độ dài là count
                _dataOut.write(buffer, 0, count);
                if (count < 1024) {
                    break;
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Has occurred failed", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Has occurred failed", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                fileInSend.close();
            } catch (IOException ex) {
            }
        }

    }

    //Nhận một file từ server
    private void receivedFile(String nameFile) {
        FileOutputStream fos = null;
        BufferedOutputStream bufferOut = null;
        try {
            fos = new FileOutputStream(nameFile);
            bufferOut = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int count;
            while ((count = _dataIn.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
                if (count < 1024) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Has occurred failed", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Has occurred failed", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                fos.close();
                bufferOut.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Has occurred failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //Báo về Server đã nhận được file
        sendMessageToServer("RECEIVED_FILE");
    }

    //Hàm này xử lí việc nhận được tin nhắn từ client khác thông qua server
    public void handleMessage(String message) {
        try {
            String sender = "";
            String receiver = "";
            String content = "";
            int start = 0;
            for (int i = 0; i < message.length(); i++) {
                if (message.charAt(i) == ' ') {
                    if (sender.equals("")) {
                        sender = message.substring(start, i);
                        start = i + 1;
                    } else {
                        receiver = message.substring(start, i);
                        start = i + 1;
                        content = message.substring(start, message.length());
                        break;
                    }
                }
            }
            Online.ALL_MESSAGE += "<font color=\"rgb(51, 51, 51)\">" + content + "</font> <br/>";
            FrmMessage.JEDITOR_PANE_MESSAGE.setText(Online.ALL_MESSAGE);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        
    }

    //Gửi yêu cầu lấy tất cả tin nhắn của 2 user đến Server
    public void getALLMessageOfTwoUser() {
        String message = "ALL_MESSAGE " + Client.USERNAME + " " + Client.USERNAME_RECEIVED;
        sendMessageToServer(message);
    }

    //Kiểm tra xem tin nhắn này có phải được gửi tới user của client này
    //hay group mà user này là một thành viên hay không
    private boolean checkReciver(String receiver) {
        if (receiver.equals(Client.USERNAME)) {
            return true;
        } else {

        }
        return false;
    }

    //Gửi một tin nhắn tới server
    public void sendMessageToServer(String info) {
        out.println(info);
    }

    //Tin nhắn để gửi lên server với người gửi, người nhận
    private String messageToSend(String content) {
        return "MESSAGE " + USERNAME + " " + Client.USERNAME_RECEIVED + " " + content;
    }
    
    public void stopClient(){
        this.interrupt();
    }
    
    //Chạy một file âm thanh
   public void getSoundWhenReceivedMessage(String soundName) {

        try {
            JFXPanel j = new JFXPanel();
            String url = new File("sounds/messager.mp3").toURI().toString();
            new MediaPlayer(new Media(url)).play();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
}
