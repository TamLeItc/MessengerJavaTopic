/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Client;
import client.GUI.small.Online;
import DAO.UserDAO;
import client.GUI.small.Help;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author itcde
 */
public class Main extends javax.swing.JFrame {
    public int x = 0;
    public static int Y = 0;
    
    public static JList USER_ONLINE_LIST;
    public static Main MAIN = new Main();

    /**
     * Creates new form Main
     */
    public Main() {
        
        initComponents();
        String[] item = {"1", "2"};
        Main.USER_ONLINE_LIST = new JList();
        
        jPnlOnline.add(USER_ONLINE_LIST);
        USER_ONLINE_LIST.setBounds(0, 0, 221, 400);
        Color color = new Color(229,249,249);
        USER_ONLINE_LIST.setBackground(color);
        
        FrmHome home = new FrmHome();
        home.setBounds(5, 5, 581, 532);
        JPNL_CONTENT.add(home);
        
        //Bỏ border của textfield search
        this.jTxtSearch.setBorder(null);
     
        //Xét icon cho Frame
        ImageIcon icon = new ImageIcon("images/icons/logo.png");
        this.setIconImage(icon.getImage());
    }
    
    public Main(String userName) {
        initComponents();
        String[] item = {"1", "2"};
        Main.USER_ONLINE_LIST = new JList();
        
        jPnlOnline.add(USER_ONLINE_LIST);
        USER_ONLINE_LIST.setBounds(0, 10, 211, 340);
        Color color = new Color(229,249,249);
        USER_ONLINE_LIST.setBackground(color);
        
        
        FrmHome home = new FrmHome();
        home.setBounds(5, 5, 581, 532);
        JPNL_CONTENT.add(home);
        
        //Tải ảnh đại điện
        String pathAvartar = new UserDAO().getPathAvartarByUserName(userName);
        //Main.LOAD_AVARTAR(Main.JLBL_AVARTAR, pathAvartar);
        //Tải userName
        this.jLblUserName.setText(userName);
        
        //Xét icon cho Frame
        ImageIcon icon = new ImageIcon("images/icon/logo.png");
        this.setIconImage(icon.getImage());
        
    }
    
    
    
    public static void LOAD_AVARTAR(JLabel lbl, String path, int widht, int height){
        lbl.setSize(widht, height);
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int labelWidth = lbl.getWidth();
            int labelHeight = lbl.getHeight();
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int x = 0, y = 0;
            
            //Xét tỉ lệ giữa ảnh và label để cho ra ảnh đúng tỉ lệ thật
            if(labelWidth / labelHeight > imageWidth / imageHeight){
                y = labelHeight;
                x = y * imageWidth / imageHeight;
            }
            else{
                x = labelWidth;
                y = x * imageHeight / imageWidth;
            }
            
            if(x > widht){
                int temp = x - widht;
                y = y * (x - temp) / x;
                x = x - temp;
            }
            else if(y > height){
                int temp = y - height;
                x = x * (y - temp) / y;
                y = y - temp;
            }
            
            ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, 50));
            lbl.setIcon(icon);
        } catch (IOException | java.lang.NullPointerException e) {
            lbl.setText("Upload failed");
        }
    }
    
    //Load bạn bè lên Frame Main thể hiện thông qua tên. Nếu bạn bè đang online thì hiển thị chấm xanh, ngược lại thì ko
    //Load userName của mỗi user lên jLblUserName
    //listUser: chứa danh sách user đang online
    //friendList: chứa danh sách bạn bè
    //currentUserName: user hiện tại của client
   
    //Các thành phần trong danh sách "listUser" được ngăn cách nhau bởi kí tự ";"
    //Mỗi thàn phần trong danh sách sẽ chứa tên đăng nhập và tên của một user được ngăn cách với bởi kí tự "@"
    //friendList cũng có dạng như vậy
    
    //Condition
    //Kiểm tra những user nào có phần đầu của tên giống với codition thì mới load lên
    
    public void loadAllUserONline(String listUser, ArrayList<String> friendList, String currentUserName, String condition) {
        //Cắt chuỗi lấy ra các thành phần riêng(mỗi thành phần chứa tên đăng nhập, tên của một user)
        String[] allUser = listUser.split(";");
        
        for (String friend : friendList) {
            
            //Cắt chuỗi lấy ra tên đăng nhập và tên của user đang bạn bè
            String[] array = friend.split("@");
            String userNameOfFriend = array[0];
            String nameOfFriend = array[1];
            
            boolean isOnline = false;
            for (String item : allUser) {
                //Cắt chuỗi lấy ra tên đăng nhập và tên của user đang online
                String[] temp = item.split("@");
                String userName = temp[0];
                
                if(userName.equals(userNameOfFriend)){
                    isOnline = true;
                }
            }
            
            //Nếu friend khác với user hiện tại. Và condition phù hợp
            if (!userNameOfFriend.equals(currentUserName)) {
                
                //Kiểm tra điều kiện(condition)
                int length = condition.length();
                if(length != 0){
                    String temp = nameOfFriend.substring(0, length);
                    
                    //Nếu điều kiện ko phù hợp
                    if(!temp.equals(condition)){
                        continue;
                    }
                }
                
                if(isOnline == true){
                    loadOnline(userNameOfFriend, nameOfFriend, true);
                }
                else{
                    loadOnline(userNameOfFriend, nameOfFriend, false);
                }
            }
        }
        
    }
    
    //Tải tất cả bạn bè đang online online lên list
    public void loadOnline(String userName, String name, boolean isOnline){
        Online online = new Online(userName, name, isOnline);
        Main.USER_ONLINE_LIST.add(online);
        online.setBounds(x, Y, 221, 35);
        Y += 35;
        
        //Yêu cầu lấy tên từ user
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        JLBL_AVARTAR = new javax.swing.JLabel();
        jLblUserName = new javax.swing.JLabel();
        JLBL_NAME = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPnlOnline = new javax.swing.JPanel();
        jTxtSearch = new javax.swing.JTextField();
        JPNL_CONTENT = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(229, 249, 249));

        jPanel7.setBackground(new java.awt.Color(229, 249, 249));

        jLblUserName.setForeground(new java.awt.Color(51, 51, 51));
        jLblUserName.setText("userName");

        JLBL_NAME.setForeground(new java.awt.Color(102, 102, 102));
        JLBL_NAME.setText("Name");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(JLBL_AVARTAR, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(JLBL_NAME)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JLBL_NAME)
                .addContainerGap(19, Short.MAX_VALUE))
            .addComponent(JLBL_AVARTAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPnlOnline.setBackground(new java.awt.Color(229, 249, 249));

        javax.swing.GroupLayout jPnlOnlineLayout = new javax.swing.GroupLayout(jPnlOnline);
        jPnlOnline.setLayout(jPnlOnlineLayout);
        jPnlOnlineLayout.setHorizontalGroup(
            jPnlOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );
        jPnlOnlineLayout.setVerticalGroup(
            jPnlOnlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Online", jPnlOnline);

        jTxtSearch.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTxtSearch.setForeground(new java.awt.Color(153, 153, 153));
        jTxtSearch.setText("  Search");
        jTxtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTxtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTxtSearch)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTxtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        JPNL_CONTENT.setBackground(new java.awt.Color(255, 255, 255));
        JPNL_CONTENT.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout JPNL_CONTENTLayout = new javax.swing.GroupLayout(JPNL_CONTENT);
        JPNL_CONTENT.setLayout(JPNL_CONTENTLayout);
        JPNL_CONTENTLayout.setHorizontalGroup(
            JPNL_CONTENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        JPNL_CONTENTLayout.setVerticalGroup(
            JPNL_CONTENTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("Talk");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Home");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Profile");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setText("Change password");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setText("Log out");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setText("Exit");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Contact");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setText("Add contact");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JPNL_CONTENT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPNL_CONTENT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FrmProfile profile = new FrmProfile(this.jLblUserName.getText());
        Main.JPNL_CONTENT.removeAll();
        Main.JPNL_CONTENT.repaint();
        Main.JPNL_CONTENT.revalidate();
        Main.JPNL_CONTENT.add(profile);
        profile.setBounds(0, 0, 588, 565);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FrmChangePassword frm = new FrmChangePassword();
        Main.JPNL_CONTENT.removeAll();
        Main.JPNL_CONTENT.repaint();
        Main.JPNL_CONTENT.revalidate();
        Main.JPNL_CONTENT.add(frm);
        frm.setBounds(0, 0, 588, 565);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrmHome frmHome = new FrmHome();
        Main.JPNL_CONTENT.removeAll();
        Main.JPNL_CONTENT.repaint();
        Main.JPNL_CONTENT.revalidate();
        Main.JPNL_CONTENT.add(frmHome);
        frmHome.setBounds(0, 0, 588, 565);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

        this.setVisible(false);
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setVisible(true);
        
        //User name của client bây giờ xét ko có
        Client.USERNAME = "";
        
        Client.CLIENT.stopClient();
        //Gửi tin nhắn lên server yêu cầu client dừng
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        FrmAddContact frmAddContact = new FrmAddContact();
        Main.JPNL_CONTENT.removeAll();
        Main.JPNL_CONTENT.repaint();
        Main.JPNL_CONTENT.revalidate();
        Main.JPNL_CONTENT.add(frmAddContact);
        frmAddContact.setBounds(0, 0, 588, 565);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            Help help = new Help();
            JDialog dialog = new JDialog(this, "Help", true);
            dialog.add(help);
            dialog.pack();
            dialog.setResizable(false);
            dialog.setLocation(500, 200);
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jTxtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtSearchKeyPressed
        if(this.jTxtSearch.getText().equals("  Search")){
            this.jTxtSearch.setText("");
            Color color = new Color(51, 51, 51);
            this.jTxtSearch.setForeground(color);
        }
    }//GEN-LAST:event_jTxtSearchKeyPressed

    private void jTxtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtSearchKeyReleased
        Main.USER_ONLINE_LIST.removeAll();
        Main.USER_ONLINE_LIST.repaint();
        Main.USER_ONLINE_LIST.revalidate();
        Main.Y = 0;
        
        if(this.jTxtSearch.getText().equals("")){
            this.jTxtSearch.setForeground(Color.LIGHT_GRAY);
            this.jTxtSearch.setText("  Search");
            
            loadAllUserONline(Client.CLIENT.userOnlineList, Client.CLIENT.friendList, Client.USERNAME, "");
            return;
        }
        loadAllUserONline(Client.CLIENT.userOnlineList, Client.CLIENT.friendList, Client.USERNAME, this.jTxtSearch.getText());
    }//GEN-LAST:event_jTxtSearchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
        
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel JLBL_AVARTAR;
    public static javax.swing.JLabel JLBL_NAME;
    public static javax.swing.JPanel JPNL_CONTENT;
    private javax.swing.JLabel jLblUserName;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPnlOnline;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTxtSearch;
    // End of variables declaration//GEN-END:variables
}
