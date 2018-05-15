package server;

import client.EmailSender;
import server.DAO.FriendDAO;
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.DAO.MessageDAO;
import server.DAO.UserDAO;

/**
 * 
 */
public class Server {

    /**
     * The port that the server listens on.
     */
    private static final int PORT = 9001;

    /**
     * The set of all user of clients in the chat room. Maintained so that we
     * can check that new clients are not registering name already in use.
     */
    private static HashSet<String> _userNameList = new HashSet<String>();

    /**
     * The set of all the print writers for all the clients. This set is kept so
     * we can easily broadcast messages.
     */
    private static HashMap<String, PrintWriter> writers = new HashMap<String, PrintWriter>();

    /**
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.serverRun();
    }

    public void serverRun() throws IOException {
        System.out.println("The chat server is running.");
        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                new Handler(listener.accept()).start();
            }
        }
    }

    /**
     * A handler thread class. Handlers are spawned from the listening loop and
     * are responsible for a dealing with a single client and broadcasting its
     * messages.
     */
    private class Handler extends Thread {

        private String _userName = "";
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private OutputStream dataOut;
        private InputStream dataIn;

      
        
        /**
         * Constructs a handler thread, squirreling away the socket. All the
         * interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;

        }

        /**
         * 
         */
        @Override
        public void run() {
            try {
                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                //Tạo stream để nhận và gửi file cho client
                dataOut = socket.getOutputStream();
                dataIn = socket.getInputStream();

                // Accept messages from this client and broadcast them.
                while (true) {

                    String input = in.readLine();
                    System.out.println(input);
                    if (input == null) {
                        return;
                    }
                    if(input.startsWith("LOGIN") || input.startsWith("AVARTAR_GET") || input.startsWith("RECEIVED_AVARTAR")){
                        handleLogin(input);
                    }
                    hadleInput(input);
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            } finally {

                //Nếu client tới đây. Nghĩa là kết nối client đã được tắt
                //Bỏ userName trong danh sách
                //Bỏ địa chỉ trong "writers"
                //Đóng socket
                System.out.println(_userName);
                
                if(!_userName.equals("")){
                    if (_userName != null) {
                        _userNameList.remove(_userName);
                    }

                    if (out != null) {
                        writers.remove(this._userName, out);
                    }
                    
                    //Gửi tin nhắn về tất cả các client. Có user đã out. Load lại các user đang online
                    String userNameList = "OUT_USER";
                    userNameList += allUserOnline();
                    //sendMessageAllClient(userNameList);
                    sendMessageAllClient(userNameList);
                }
                
                try {
                    socket.close();
                } catch (IOException e) {
                }

                
                try {
                    in.close();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        private void handleLogin(String input){
            //Nếu nhận được yêu cần đăng nhập từ login
            if (input.startsWith("LOGIN")) {
                if(input.length() == 6){
                    return;
                }
                
                String[] user = input.substring(6).split(" ");
                String userName = user[0];
                String passWord = user[1];

                //Nếu userName, passWord của client gửi lên chính xác thì chấp nhận việc đăng nhập
                if (new UserDAO().checkLogin(userName, passWord)) {
                    out.println("ACCEPT");
                    //Nếu chấp nhận việc đăng nhập của client thì:
                    //Nếu userName chưa có trong danh sách thì thêm vào danh sách
                    //Thêm một địa chỉ gửi tin nhắn của client mới vào danh sách "writers"
                    String[] temp = input.split(" ");
                    _userName = temp[1];
                    
                    if(!_userName.equals("")){
                        synchronized (_userNameList) {
                            if (!_userNameList.contains(_userName)) {
                                _userNameList.add(this._userName);
                            }
                        }
                    }

                    //Thêm địa chỉ gửi tin nhắn của client
                    writers.put(this._userName, out);

                } else {
                    out.println("ERROR");
                }
            } 
            
            //Nếu nhận được lệnh yêu cần AVARTAR từ user
            //Từ userName lấy ra avartar và gửi về client
            else if (input.startsWith("AVARTAR_GET")) {
                
                String path = new UserDAO().getPathAvartarByUserName(input.substring(12));
                sendFile(path);
            } 
            //Nếu client báo về đã nhận được avartar
            else if (input.startsWith("RECEIVED_AVARTAR")) {
                //Gửi về client toàn bộ bạn bè của user đang chạy trên client
                out.println("ALL_FRIEND " + new FriendDAO().getAllFriendByUserName(_userName));
                
                //Lấy ra danh sách user đang có trong danh sách(Những user đang online)
                //Và gửi danh sách về cho client
                String userNameList = "NEW_USER";
                userNameList += allUserOnline();
                sendMessageAllClient(userNameList);
            }
        }

        //Xử lí các tin nhắn nhận được từ client
        private void hadleInput(String input) {
            
            //Nếu client yêu cầu lấy toàn bột hông tin của tài khoản đang chạy trên client
            //Lấy dữ liệu từ server và trả về client dưới dạng một chuỗi chưa toàn bộ thông tin
            //của tài khoản mà client yêu cầu
            if(input.startsWith("ALL_INFO")){
                String info = "ALL_INFO " + new UserDAO().getAllInfoOfUser(_userName);
                out.println(info);
            }
            //Nếu nhận được tin nhắn tới từ client
            else if (input.startsWith("MESSAGE")) {
                //Đưa tin nhắn vào database
                addMessageToDatabase(input.substring(8));

                //Gửi tin nhắn tới tất cả client
                //sendMessageAllClient(input);
                sendMessageToClientReceive(input.substring(8));
            } 
            //Nếu Client yêu cầu lấy tất cả tin nhắn giữa 2 user
            else if (input.startsWith("ALL_MESSAGE")) {
                String allMessage = "ALL_MESSAGE " + getAllMessageOfTwoUser(input);
                out.println(allMessage);
            } 
            //Nếu client gửi yêu cần thay đổi avartar cho user
            else if (input.startsWith("CHANGE_AVARTAR")) {
                receivedAvartar("images/avartar/" + this._userName + ".jpg");
            }
            //Nếu nhận được yêu cầu update server từ client
            else if(input.startsWith("UPDATE_USER")){
                //Nếu update user thành công
                if(updateUser(input.substring(12))){
                    out.println("UPDATE_SUCCESS");
                }
                else{
                    out.println("UPDATE_FAILED");
                }
            }
            //Nếu nhận được yêu cầu insert user từ client
            else if(input.startsWith("INSERT_USER")){
                //Nếu insert user thành công
                if(insertUser(input.substring(12))){
                    out.println("INSERT_SUCCESS");
                }
                else{
                    out.println("INSERT_FAILED");
                }
            }
            //Nếu nhận được yêu cầu lấy tên của user thông qua userName
            else if(input.startsWith("NAME_USER")){
                String userName = input.substring(10);
                out.println("NAME_USER " + new UserDAO().getNameOfUserByUserName(userName));
            }
            //Nếu nhận được kiểm tra username có tồn tại hay ko
            else if(input.startsWith("CHECK_USER")){
                if(new UserDAO().checkExistByUserName(input.substring(11))){
                    //Vì khi gửi về client. Tin nhắn sẽ bị đọc một lần ở vòng while trong hàm run rồi mới được
                    //đọc ở phương thức "checkExistUser" ở client. Nên cần phải gửi về 2 dòng để đảm bảo rằng
                    //một dòng sẽ được đọc ở phương thức "checkExistUser". Đảm bảo chương trình ko bị lỗi
                    out.println("CHECK_USER YES");
                    out.println("CHECK_USER YES");
                 
                }
                else{
                    out.println("CHECK_USER NO");
                    out.println("CHECK_USER NO");
                }
            }
            //Nếu nhận được tin nhắn "STOP" từ client thì gửi tin nhắn về client đó yêu cầu dừng
            else if(input.startsWith("STOP_CLIENT")){
                out.println("STOP_CLIENT");
            }
            //Nếu nhận được yêu cầu thêm bạn bè vào dữ liệu
            else if(input.startsWith("NEW_FRIEND")){
                if(!insertFriend(input.substring(11))){
                    out.println("ADD_NEW_FRIEND_FAILED");
                }
            }
            //Nếu nhận được yêu cầu từ client kiểm tra 2 user có phải bạn bè với nhau hay ko
            else if(input.startsWith("IS_FRIEND")){
                System.out.println(input);
                String[] temp = input.substring(10).split(" ");
                String userName1 = temp[0];
                String userName2 = temp[1];
                if(new FriendDAO().checkIsFriend(userName1, userName2)){
                    //Vì khi gửi về client. Tin nhắn sẽ bị đọc một lần ở vòng while trong hàm run rồi mới được
                    //đọc ở phương thức "checkIsFriend" ở client. Nên cần phải gửi về 2 dòng để đảm bảo rằng
                    //một dòng sẽ được đọc ở phương thức "checkIsFriend". Đảm bảo chương trình ko bị lỗi
                    
                    out.println("IS_FRIEND YES");
                    out.println("IS_FRIEND YES");
                }
                else{
                    out.println("IS_FRIEND NO");
                    out.println("IS_FRIEND NO");
                }
            }
            else if(input.startsWith("ALL_FRIEND")){
                out.println("ALL_FRIEND " + new FriendDAO().getAllFriendByUserName(_userName));
            }
            else if(input.startsWith("AVARTAR_FRIEND")){
                out.println("START_SEND");
            }
            else if(input.startsWith("FORGOT_PASSWORD")){
                String userName = input.substring(16);
                EmailSender.sendMail(new UserDAO().getEmailByUserName(userName), new UserDAO().getEmailByUserName(userName));
            }
        }
        
        //Từ câu sql truyền từ client
        //Thực hiện việc update
        private boolean updateUser(String sql){
            return new UserDAO().update(sql);
        }
        
        //Từ câu sql được truyền vào
        //Thực hiện việc insert
        private boolean insertUser(String sql){
            return new UserDAO().addNew(sql);
        }
        
        //Từ tin nhắn chứa userName 2 tài khoản người từ client lên
        //Phân tích tin nhắn lấy ra userName của 2 tài khoản
        //Gọi hàm thực hiện việc thêm một bạn bè vào danh sách
        private boolean insertFriend(String message){
            String[] temp = message.split(" ");
            String userName1 = temp[0];
            String userName2 = temp[1];
            return new FriendDAO().addNew(userName1, userName2);
        }

        //Gửi một file tới client
        private void sendFile(String path) {
            //Lấy đường dẫn Avartar
            //String pathAvartar = new UserDAO().getPathAvartarByUserName(_userName);
            FileInputStream fileInSend = null;
            try {
                //Đọc ra file để lấy tên của file
                File file = new File(path);
                //Lấy file ra để gửi đi
                fileInSend = new FileInputStream(path);
                //Tạo một mảng kiểu byte với độ dài 1024. nghĩa là 1kb
                byte[] buffer = new byte[1024];
                //Tạo biến đếm để chứa độ dài buffer
                int count;
                while ((count = fileInSend.read(buffer)) >= 0) {
                    //Gửi file buffer đi bắt đầu từ 0, độ dài là count
                    dataOut.write(buffer, 0, count);
                    if (count < 1024) {
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileInSend.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Nhận file avartar mới từ client
        //Ghi file avartar mới cho user bằng được dẫn được truyền vào
        private void receivedAvartar(String nameFile) {
            receivedFile(nameFile);
        }

        //Nhận một file từ client
        private void receivedFile(String nameFile) {
            FileOutputStream fos = null;
            BufferedOutputStream bufferOut = null;
            try {
                fos = new FileOutputStream(nameFile);
                bufferOut = new BufferedOutputStream(fos);

                byte[] buffer = new byte[1024];
                int count;
                while ((count = dataIn.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                    if (count < 1024) {
                        break;
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                try {
                    fos.close();
                    bufferOut.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.println("RECEIVED_FILE");
        }

        //Lấy tất cả tin nhắn giữa hai user trong cơ sở dữ liệu
        private String getAllMessageOfTwoUser(String twoUserName) {
            String allMessage = "";

            //Cắt chuỗi. bỏ phần đầu đi. lấy ra userName của 2 user
            String[] temp = twoUserName.split(" ");
            String userName1 = temp[1];
            String userName2 = temp[2];
            allMessage = new MessageDAO().getAllMessageByTwoUserName(userName1, userName2);
            return allMessage;
        }

        //Lấy tất cả userName và tên đang online tạo thành một chuỗi
        //Các user được ngăn cách với nhau bởi dấu ";"
        //Tên và tên đăng nhập của mỗi user sẽ được năng cách bởi kí tự "@"
        private String allUserOnline() {
            String userNameList = "";
            for (String item : _userNameList) {
                userNameList += ";" + item + "@" + new UserDAO().getNameOfUserByUserName(item);
            }
            return userNameList;
        }

        //Phân tích tin nhắn thành người nhận, người gửi, nội dung và đưa vào cơ sở dữ liệu
        private void addMessageToDatabase(String message) {
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
                        
                        content = message.substring(start);
                        break;
                    }
                }
            }

            new MessageDAO().addNew(sender, receiver, content);
        }

        //Nhận được tin nhắn từ client
        //Từ tin nhắn phân tích ra username nhận và gửi tin nhắn tới client của username đó
        private void sendMessageToClientReceive(String message) {
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
                        
                        content = message.substring(start);
                        break;
                    }
                }
            }
            try {
                PrintWriter writer = writers.get(receiver);
                writer.println("MESSAGE " + message);
            } catch (java.lang.NullPointerException e) {
            }
        }

        //Gửi tin nhắn tới tất cả các client đang hoạt động.
        private void sendMessageAllClient(String message) {
            PrintWriter writer;
            for (String item : _userNameList) {
                writer = writers.get(item);
                writer.println(message);
            }
        }

    }
}
