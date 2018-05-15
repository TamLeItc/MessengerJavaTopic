/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI.small;

import client.Client;
import client.GUI.FrmMessage;
import client.GUI.Main;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author itcde
 */
public class Online extends javax.swing.JPanel {

    public static String ALL_MESSAGE = " ";
    /**
     * Creates new form FrmOnline
     */
    public Online() {
        initComponents();
    }
    
    public Online(String userName, String name, boolean isOnline) {
        initComponents();
        this.jLblUserName.setText(userName);
        this.jLblName.setText(name);
        
        if(isOnline == false){
            this.jLblOnline.setIcon(null);
        }
        
        this.jLblUserName.setVisible(false);
        
        
        this.jLblName.setLocation(6, 50);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLblUserName = new javax.swing.JLabel();
        jLblOnline = new javax.swing.JLabel();
        jLblName = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setBackground(new java.awt.Color(229, 249, 249));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
        });

        jLblOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/client/icons/online.png"))); // NOI18N

        jLblName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLblName.setForeground(new java.awt.Color(51, 51, 51));
        jLblName.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLblUserName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblName, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jLblOnline)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLblOnline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
        Main.JPNL_CONTENT.removeAll();
        Main.JPNL_CONTENT.repaint();
        Main.JPNL_CONTENT.validate();
        Main.JPNL_CONTENT.revalidate();
        Main.JPNL_CONTENT.add(Client.FORM_MESSAGE);
        Client.FORM_MESSAGE.setBounds(0, 0, 585, 565);
        
        
        //Tải tên và tên đăng nhập của người nhận lên
        FrmMessage.JLBL_RECEIVED.setText(this.jLblUserName.getText());
        FrmMessage.JLBL_NAME.setText(this.jLblName.getText());
        //Tải ảnh của người nhận lên
        Main.LOAD_AVARTAR(FrmMessage.JLBL_AVARTAR, "images/avartars/" + this.jLblUserName.getText() + ".jpg", 65, 64);
        System.out.println("images/avartar/" + this.jLblUserName.getText() + ".jpg");
        
        Client.USERNAME_RECEIVED = FrmMessage.JLBL_RECEIVED.getText();
        
        Client.CLIENT.getALLMessageOfTwoUser();
        
        FrmMessage.JEDITOR_PANE_MESSAGE.setText(ALL_MESSAGE);  
    }//GEN-LAST:event_formMouseClicked

    
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        Color color = new Color(204,204,204);
        this.jLblName.setBackground(color);
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        Color color = new Color(229,249,249);
        this.jLblName.setBackground(color);
    }//GEN-LAST:event_formMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLblName;
    private javax.swing.JLabel jLblOnline;
    private javax.swing.JLabel jLblUserName;
    // End of variables declaration//GEN-END:variables
}