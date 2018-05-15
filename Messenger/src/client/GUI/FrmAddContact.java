/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.Client;
import client.Check;
import client.GUI.small.Loading;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author itcde
 */
public class FrmAddContact extends javax.swing.JPanel {

    /**
     * Creates new form FrmAddContact
     */
    public FrmAddContact() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        jTxtUserName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Add a new contact");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Enter user name of a account:");

        jTxtUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTxtUserNameKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(7, 128, 180));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jTxtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(34, 34, 34)
                .addComponent(jTxtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        handle();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTxtUserNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTxtUserNameKeyReleased
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){


            
            handle();
            
            
        }
        
        
    }//GEN-LAST:event_jTxtUserNameKeyReleased

    private void jButton1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            handle();
        }
    }//GEN-LAST:event_jButton1KeyReleased
    
 
    private boolean handle(){
        if(this.jTxtUserName.getText().equals("") || this.jTxtUserName.getText().equals("User name")){
        
            JOptionPane.showMessageDialog(null, "Please enter the name!!!", "Add contact failed", JOptionPane.ERROR_MESSAGE);
            this.jTxtUserName.requestFocus();
            return false;
        }
        if(!Check.checkName(this.jTxtUserName.getText())){
          
            JOptionPane.showMessageDialog(null, "User name incorrect format. User name only include non-accented characters!!!", "Add contact failed", JOptionPane.ERROR_MESSAGE);
            this.jTxtUserName.requestFocus();
            return false;
        }
        //Kiểm tra username đã tồn tại hay chưa bằng cách gửi userName lên server để kiểm tra
        if(!Client.CLIENT.checkExistUser(this.jTxtUserName.getText())){
           
            JOptionPane.showMessageDialog(null, "User name not exist", "Add contact failed", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(Client.CLIENT.checkIsFriend(Client.USERNAME, this.jTxtUserName.getText())){
            
            
            JOptionPane.showMessageDialog(null, "You and " + this.jTxtUserName.getText() + " was already friend now.");
            return false;
        }
        else{
            
            Client.CLIENT.sendMessageToServer("NEW_FRIEND " + Client.USERNAME + " " + this.jTxtUserName.getText());
            JOptionPane.showMessageDialog(null, "Success. Now you and " + this.jTxtUserName.getText() + " are friend.");
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTxtUserName;
    // End of variables declaration//GEN-END:variables
}
