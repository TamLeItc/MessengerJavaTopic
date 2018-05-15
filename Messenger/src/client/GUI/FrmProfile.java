/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.GUI;

import client.DTO.User;
import client.Client;
import client.DateFormatGuide;
import client.GUI.register.ConfirmationEmail;
import client.GUI.small.ChoiceBirthDate;
import client.GUI.small.ChoiceGender;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author itcde
 */
public class FrmProfile extends javax.swing.JPanel {

    public static JDialog DIALOG;
    //Lưu đường dẫn của ảnh cho việc thay đổi ảnh đại diện
    private String _pathImage = "";
   

    /**
     * Creates new form Profile
     */
    public FrmProfile() {
        this.setLayout(null);
        initComponents();
        
        //Bỏ border các textfield
        FrmProfile.JTXT_BIRTH_DATE.setBorder(null);
        FrmProfile.JTXT_EMAIL.setBorder(null);
        this.jTxtFirstName.setBorder(null);
        this.jTxtLastName.setBorder(null);
        FrmProfile.JTXT_GENDER.setBorder(null);

        
    }

    public FrmProfile(String userName) {
        initComponents();

        loadData(Client.USER);
        loadAvartar(this.jLblAvartar, "images/avartars/" + Client.USERNAME + ".jpg");

        //Bỏ border các textfield
        FrmProfile.JTXT_BIRTH_DATE.setBorder(null);
        FrmProfile.JTXT_EMAIL.setBorder(null);
        this.jTxtFirstName.setBorder(null);
        this.jTxtLastName.setBorder(null);
        FrmProfile.JTXT_GENDER.setBorder(null);

        //Không hiển thị nút cancel chỗ ảnh đại diện
        //Chỉ hiện thị lên khi người dùng thay đổi ảnh đại diện
        this.jLblCancel.setVisible(false);

        //Thiết lập text của tất cả nút Save là một khoảng trắng
        //Để khi người dùng chưa có ý định thay đổi tên thì Label này sẽ như ko nhìn thấy
        //Nhưng vẫn giữ được kích thước của mình để khi thiết lập text thành "Save" không ảnh
        //hưởng đến vị trí của các thành phần khác trên panel
        this.jLblSaveFirstName.setText("         ");
        this.jLblSaveLastName.setText("         ");
        FrmProfile.JLBL_SAVE_BIRTH_DATE.setText("         ");
        FrmProfile.JLBL_SAVE_GENDER.setText("         ");
        FrmProfile.JLBL_SAVE_EMAIL.setText("         ");
    }

    //Thay đổi màu cho label
    //Nếu text truyền vào là "BLUISH" thì chuyển thành màu xanh nhạc
    //Ngược lại thì màu xám
    public static void CHANGE_FOREGROUD_LABEL(JLabel lbl, String clr) {
        Color color = null;
        if(clr.equals("BLUISH")){
            color = new Color(0, 102, 255);
        }
        else{
            color = new Color(102,102,102);
        }
        lbl.setForeground(color);
    }

    private void loadData(User user) {
        this.jLblUserName.setText(user.getUserName());
        this.jTxtFirstName.setText(user.getFirstName());
        this.jTxtLastName.setText(user.getLastName());
        FrmProfile.JTXT_BIRTH_DATE.setText(user.getBirthDate());
        FrmProfile.JTXT_GENDER.setText(user.getGender());
        FrmProfile.JTXT_EMAIL.setText(user.getEmail());
    }

    private boolean loadAvartar(JLabel lbl, String path) {
        lbl.setSize(300, 180);
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int labelWidth = lbl.getWidth();
            int labelHeight = lbl.getHeight();
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int x = 0, y = 0;

            //Xét tỉ lệ giữa ảnh và label để cho ra ảnh đúng tỉ lệ thật
            if (labelWidth / labelHeight > imageWidth / imageHeight) {
                y = labelHeight;
                x = y * imageWidth / imageHeight;
            } else {
                x = labelWidth;
                y = x * imageHeight / imageWidth;
            }

            if (x > 180) {
                int temp = x - 180;
                y = y * (x - temp) / x;
                x = x - temp;
            } else if (y > 300) {
                int temp = y - 300;
                x = x * (y - temp) / y;
                y = y - temp;
            }

            ImageIcon icon = new ImageIcon(image.getScaledInstance(x, y, 50));
            lbl.setIcon(icon);
        } catch (IOException | java.lang.NullPointerException e) {
            lbl.setText("Upload failed");
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblChangePicture = new javax.swing.JLabel();
        jLblCancel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLblAvartar = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLblUserName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLbl = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLblCancelFirstName = new javax.swing.JLabel();
        jLblCancelLastName = new javax.swing.JLabel();
        JLBL_CANCEL_BIRTH_DATE = new javax.swing.JLabel();
        JLBL_CANCEL_GENDER = new javax.swing.JLabel();
        JLBL_CANCEL_EMAIL = new javax.swing.JLabel();
        JTXT_EMAIL = new javax.swing.JTextField();
        JTXT_GENDER = new javax.swing.JTextField();
        JTXT_BIRTH_DATE = new javax.swing.JTextField();
        jTxtLastName = new javax.swing.JTextField();
        jTxtFirstName = new javax.swing.JTextField();
        jLblSaveFirstName = new javax.swing.JLabel();
        jLblSaveLastName = new javax.swing.JLabel();
        JLBL_SAVE_BIRTH_DATE = new javax.swing.JLabel();
        JLBL_SAVE_GENDER = new javax.swing.JLabel();
        JLBL_SAVE_EMAIL = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLblChangePicture.setForeground(new java.awt.Color(0, 102, 255));
        jLblChangePicture.setText("Change picture");
        jLblChangePicture.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblChangePicture.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLblChangePictureMouseMoved(evt);
            }
        });
        jLblChangePicture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblChangePictureMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblChangePictureMouseExited(evt);
            }
        });

        jLblCancel.setForeground(new java.awt.Color(0, 102, 255));
        jLblCancel.setText("Cancel");
        jLblCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLblCancel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLblCancelMouseMoved(evt);
            }
        });
        jLblCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblCancelMouseExited(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLblAvartar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLblAvartar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLblChangePicture)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblCancel)
                .addGap(0, 84, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblChangePicture)
                    .addComponent(jLblCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLblUserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLblUserName.setText("userName");

        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("First name");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Last name");

        jLbl.setForeground(new java.awt.Color(102, 102, 102));
        jLbl.setText("Birth date");

        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Gender");

        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Email");

        jLblCancelFirstName.setForeground(new java.awt.Color(102, 102, 102));
        jLblCancelFirstName.setText("  Public");
        jLblCancelFirstName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLblCancelFirstNameMouseMoved(evt);
            }
        });
        jLblCancelFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelFirstNameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblCancelFirstNameMouseExited(evt);
            }
        });

        jLblCancelLastName.setForeground(new java.awt.Color(102, 102, 102));
        jLblCancelLastName.setText("   Public");
        jLblCancelLastName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLblCancelLastNameMouseMoved(evt);
            }
        });
        jLblCancelLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblCancelLastNameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblCancelLastNameMouseExited(evt);
            }
        });

        JLBL_CANCEL_BIRTH_DATE.setForeground(new java.awt.Color(102, 102, 102));
        JLBL_CANCEL_BIRTH_DATE.setText("   Public");
        JLBL_CANCEL_BIRTH_DATE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_BIRTH_DATEMouseMoved(evt);
            }
        });
        JLBL_CANCEL_BIRTH_DATE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_BIRTH_DATEMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_BIRTH_DATEMouseExited(evt);
            }
        });

        JLBL_CANCEL_GENDER.setForeground(new java.awt.Color(102, 102, 102));
        JLBL_CANCEL_GENDER.setText("Public");
        JLBL_CANCEL_GENDER.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_GENDERMouseMoved(evt);
            }
        });
        JLBL_CANCEL_GENDER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_GENDERMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_GENDERMouseExited(evt);
            }
        });

        JLBL_CANCEL_EMAIL.setForeground(new java.awt.Color(102, 102, 102));
        JLBL_CANCEL_EMAIL.setText("Private");
        JLBL_CANCEL_EMAIL.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_EMAILMouseMoved(evt);
            }
        });
        JLBL_CANCEL_EMAIL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_EMAILMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLBL_CANCEL_EMAILMouseExited(evt);
            }
        });

        JTXT_EMAIL.setEditable(false);
        JTXT_EMAIL.setBackground(new java.awt.Color(255, 255, 255));
        JTXT_EMAIL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTXT_EMAILMouseClicked(evt);
            }
        });

        JTXT_GENDER.setEditable(false);
        JTXT_GENDER.setBackground(new java.awt.Color(255, 255, 255));
        JTXT_GENDER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTXT_GENDERMouseClicked(evt);
            }
        });

        JTXT_BIRTH_DATE.setEditable(false);
        JTXT_BIRTH_DATE.setBackground(new java.awt.Color(255, 255, 255));
        JTXT_BIRTH_DATE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTXT_BIRTH_DATEMouseClicked(evt);
            }
        });

        jTxtLastName.setEditable(false);
        jTxtLastName.setBackground(new java.awt.Color(255, 255, 255));
        jTxtLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTxtLastNameMouseClicked(evt);
            }
        });

        jTxtFirstName.setEditable(false);
        jTxtFirstName.setBackground(new java.awt.Color(255, 255, 255));
        jTxtFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTxtFirstNameMouseClicked(evt);
            }
        });

        jLblSaveFirstName.setForeground(new java.awt.Color(0, 102, 255));
        jLblSaveFirstName.setText("Save");
        jLblSaveFirstName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLblSaveFirstNameMouseMoved(evt);
            }
        });
        jLblSaveFirstName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblSaveFirstNameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblSaveFirstNameMouseExited(evt);
            }
        });

        jLblSaveLastName.setForeground(new java.awt.Color(0, 102, 255));
        jLblSaveLastName.setText("Save");
        jLblSaveLastName.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLblSaveLastNameMouseMoved(evt);
            }
        });
        jLblSaveLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLblSaveLastNameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLblSaveLastNameMouseExited(evt);
            }
        });

        JLBL_SAVE_BIRTH_DATE.setForeground(new java.awt.Color(0, 102, 255));
        JLBL_SAVE_BIRTH_DATE.setText("Save");
        JLBL_SAVE_BIRTH_DATE.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_BIRTH_DATEMouseMoved(evt);
            }
        });
        JLBL_SAVE_BIRTH_DATE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_BIRTH_DATEMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_BIRTH_DATEMouseExited(evt);
            }
        });

        JLBL_SAVE_GENDER.setForeground(new java.awt.Color(0, 102, 255));
        JLBL_SAVE_GENDER.setText("Save");
        JLBL_SAVE_GENDER.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_GENDERMouseMoved(evt);
            }
        });
        JLBL_SAVE_GENDER.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_GENDERMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_GENDERMouseExited(evt);
            }
        });

        JLBL_SAVE_EMAIL.setForeground(new java.awt.Color(0, 102, 255));
        JLBL_SAVE_EMAIL.setText("Save");
        JLBL_SAVE_EMAIL.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_EMAILMouseMoved(evt);
            }
        });
        JLBL_SAVE_EMAIL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_EMAILMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JLBL_SAVE_EMAILMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLbl)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTXT_EMAIL, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(JTXT_GENDER)
                            .addComponent(JTXT_BIRTH_DATE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTxtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                    .addComponent(jTxtLastName))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLblSaveLastName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLblCancelLastName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(JLBL_SAVE_BIRTH_DATE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JLBL_CANCEL_BIRTH_DATE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLblSaveFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLblCancelFirstName))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(JLBL_SAVE_GENDER)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JLBL_CANCEL_GENDER)))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JLBL_SAVE_EMAIL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JLBL_CANCEL_EMAIL)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblCancelFirstName)
                    .addComponent(jTxtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblSaveFirstName)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblCancelLastName)
                    .addComponent(jTxtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblSaveLastName)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBL_CANCEL_BIRTH_DATE)
                    .addComponent(JTXT_BIRTH_DATE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBL_SAVE_BIRTH_DATE)
                    .addComponent(jLbl))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBL_CANCEL_GENDER)
                    .addComponent(JTXT_GENDER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBL_SAVE_GENDER)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLBL_CANCEL_EMAIL)
                    .addComponent(JTXT_EMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLBL_SAVE_EMAIL)
                    .addComponent(jLabel7))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {JTXT_BIRTH_DATE, JTXT_EMAIL, JTXT_GENDER, jTxtFirstName, jTxtLastName});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLblCancelFirstNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelFirstNameMouseMoved
        if (this.jLblCancelFirstName.getText().equals("Cancel")) {
            underLineTextInLabel(this.jLblCancelFirstName, 1);
            this.jLblCancelFirstName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_jLblCancelFirstNameMouseMoved

    private void jLblCancelFirstNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelFirstNameMouseExited
        underLineTextInLabel(this.jLblCancelFirstName, 0);
    }//GEN-LAST:event_jLblCancelFirstNameMouseExited

    private void jLblCancelLastNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelLastNameMouseMoved
        if (this.jLblCancelLastName.getText().equals("Cancel")) {
            underLineTextInLabel(this.jLblCancelLastName, 1);
            this.jLblCancelLastName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_jLblCancelLastNameMouseMoved

    private void jLblCancelLastNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelLastNameMouseExited
        underLineTextInLabel(this.jLblCancelLastName, 0);
    }//GEN-LAST:event_jLblCancelLastNameMouseExited

    private void JLBL_CANCEL_BIRTH_DATEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_BIRTH_DATEMouseMoved
        if (FrmProfile.JLBL_CANCEL_BIRTH_DATE.getText().equals("Cancel")) {
            underLineTextInLabel(FrmProfile.JLBL_CANCEL_BIRTH_DATE, 1);
            FrmProfile.JLBL_CANCEL_BIRTH_DATE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_JLBL_CANCEL_BIRTH_DATEMouseMoved

    private void JLBL_CANCEL_BIRTH_DATEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_BIRTH_DATEMouseExited
        underLineTextInLabel(FrmProfile.JLBL_CANCEL_BIRTH_DATE, 0);
    }//GEN-LAST:event_JLBL_CANCEL_BIRTH_DATEMouseExited

    private void JLBL_CANCEL_GENDERMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_GENDERMouseMoved
        if (FrmProfile.JLBL_CANCEL_GENDER.getText().equals("Cancel")) {
            underLineTextInLabel(FrmProfile.JLBL_CANCEL_GENDER, 1);
            FrmProfile.JLBL_CANCEL_GENDER.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_JLBL_CANCEL_GENDERMouseMoved

    private void JLBL_CANCEL_GENDERMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_GENDERMouseExited
        underLineTextInLabel(FrmProfile.JLBL_CANCEL_GENDER, 0);
    }//GEN-LAST:event_JLBL_CANCEL_GENDERMouseExited

    private void JLBL_CANCEL_EMAILMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_EMAILMouseMoved
        if (FrmProfile.JLBL_CANCEL_EMAIL.getText().equals("Cancel")) {
            underLineTextInLabel(FrmProfile.JLBL_CANCEL_EMAIL, 1);
            FrmProfile.JLBL_CANCEL_EMAIL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_JLBL_CANCEL_EMAILMouseMoved

    private void JLBL_CANCEL_EMAILMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_EMAILMouseExited
        underLineTextInLabel(FrmProfile.JLBL_CANCEL_EMAIL, 0);
    }//GEN-LAST:event_JLBL_CANCEL_EMAILMouseExited

    private void jLblChangePictureMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblChangePictureMouseMoved
        underLineTextInLabel(this.jLblChangePicture, 1);
    }//GEN-LAST:event_jLblChangePictureMouseMoved

    private void jLblChangePictureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblChangePictureMouseExited
        underLineTextInLabel(this.jLblChangePicture, 0);
    }//GEN-LAST:event_jLblChangePictureMouseExited

    private void jLblCancelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelMouseMoved
        underLineTextInLabel(this.jLblCancel, 1);
    }//GEN-LAST:event_jLblCancelMouseMoved

    private void jLblCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelMouseExited
        underLineTextInLabel(this.jLblCancel, 0);
    }//GEN-LAST:event_jLblCancelMouseExited

    private void jLblSaveFirstNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSaveFirstNameMouseMoved
        //Với điều kiện text của label phải là "Save"
        if (this.jLblSaveFirstName.getText().equals("Save")) {
            //Gạch chân text của label khi chuột di chuyển qua label
            underLineTextInLabel(this.jLblSaveFirstName, 1);

            //Icon của chuột khi di chuyển qua label có dạnh bàn tay
            this.jLblSaveFirstName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }


    }//GEN-LAST:event_jLblSaveFirstNameMouseMoved

    private void jLblSaveFirstNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSaveFirstNameMouseExited
        //Bỏ gạch chân text mỗi khi chuột di chuyền ra khỏi label
        underLineTextInLabel(this.jLblSaveFirstName, 0);
    }//GEN-LAST:event_jLblSaveFirstNameMouseExited

    private void jLblSaveLastNameMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSaveLastNameMouseMoved
        //Với điều kiện text của label phải là "Save"
        if (this.jLblSaveLastName.getText().equals("Save")) {
            //Gạch chân text của label khi chuột di chuyển qua label
            underLineTextInLabel(this.jLblSaveLastName, 1);

            //Icon của chuột khi di chuyển qua label có dạnh bàn tay
            this.jLblSaveLastName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_jLblSaveLastNameMouseMoved

    private void jLblSaveLastNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSaveLastNameMouseExited
        //Bỏ gạch chân text mỗi khi chuột di chuyền ra khỏi label
        underLineTextInLabel(this.jLblSaveLastName, 0);
    }//GEN-LAST:event_jLblSaveLastNameMouseExited

    private void JLBL_SAVE_BIRTH_DATEMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_BIRTH_DATEMouseMoved
        //Với điều kiện text của label phải là "Save"
        if (FrmProfile.JLBL_SAVE_BIRTH_DATE.getText().equals("Save")) {
            //Gạch chân text của label khi chuột di chuyển qua label
            underLineTextInLabel(FrmProfile.JLBL_SAVE_BIRTH_DATE, 1);

            //Icon của chuột khi di chuyển qua label có dạnh bàn tay
            FrmProfile.JLBL_SAVE_BIRTH_DATE.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_JLBL_SAVE_BIRTH_DATEMouseMoved

    private void JLBL_SAVE_BIRTH_DATEMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_BIRTH_DATEMouseExited
        underLineTextInLabel(FrmProfile.JLBL_SAVE_BIRTH_DATE, 0);
    }//GEN-LAST:event_JLBL_SAVE_BIRTH_DATEMouseExited

    private void JLBL_SAVE_GENDERMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_GENDERMouseMoved
        //Với điều kiện text của label phải là "Save"
        if (FrmProfile.JLBL_SAVE_GENDER.getText().equals("Save")) {
            //Gạch chân text của label khi chuột di chuyển qua label
            underLineTextInLabel(FrmProfile.JLBL_SAVE_GENDER, 1);

            //Icon của chuột khi di chuyển qua label có dạnh bàn tay
            FrmProfile.JLBL_SAVE_GENDER.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_JLBL_SAVE_GENDERMouseMoved

    private void JLBL_SAVE_GENDERMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_GENDERMouseExited
        underLineTextInLabel(FrmProfile.JLBL_SAVE_GENDER, 0);
    }//GEN-LAST:event_JLBL_SAVE_GENDERMouseExited

    private void JLBL_SAVE_EMAILMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_EMAILMouseMoved
        //Với điều kiện text của label phải là "Save"
        if (FrmProfile.JLBL_SAVE_EMAIL.getText().equals("Save")) {
            //Gạch chân text của label khi chuột di chuyển qua label
            underLineTextInLabel(FrmProfile.JLBL_SAVE_EMAIL, 1);

            //Icon của chuột khi di chuyển qua label có dạnh bàn tay
            FrmProfile.JLBL_SAVE_EMAIL.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_JLBL_SAVE_EMAILMouseMoved

    private void JLBL_SAVE_EMAILMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_EMAILMouseExited
        underLineTextInLabel(FrmProfile.JLBL_SAVE_EMAIL, 0);
    }//GEN-LAST:event_JLBL_SAVE_EMAILMouseExited

    private void jLblChangePictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblChangePictureMouseClicked

        //Nếu người dùng ấn Lbl change picture
        if (evt.getButton() == MouseEvent.BUTTON1) {
            //Nếu text hiện tại của label là "Change picture"
            //Hiển thị dialog để có thể chọn file
            if (!this.jLblChangePicture.getText().equals("Save")) {
                _pathImage = getPathEmageFromFileChooser();
                if (!_pathImage.equals("")) {
                    if (!loadAvartar(this.jLblAvartar, _pathImage)) {
                        this.jLblAvartar.setIcon(null);
                    }
                    this.jLblChangePicture.setText("Save");

                    //Hiển thị nút cancel
                    this.jLblCancel.setVisible(true);
                }
            } //Nếu text hiện tại của label là "Save"
            //Lưu lại thay đổi vào cơ sở dữ liệu
            //Xét ảnh đại diện ở Frame chính đúng với file mà người dùng vừa chọn
            else {
                //Gửi file avartar tới server
                Client.CLIENT.sendAvartar(_pathImage);
                
                //Ẩn nút cancel
                this.jLblCancel.setVisible(false);
                this.jLblChangePicture.setText("Change picture");
                
                Main.LOAD_AVARTAR(Main.JLBL_AVARTAR, _pathImage, 70, 70);
                if (!loadAvartar(this.jLblAvartar, _pathImage)) {
                    this.jLblAvartar.setIcon(null);
                }

            }
        }
    }//GEN-LAST:event_jLblChangePictureMouseClicked

    private void jLblCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelMouseClicked
        //Khi người dùng ấn hủy bỏ
        //Đưa hình đại diện ở Panel Profile về đúng với ảnh mà userName đó đang dùng
        //Ẩn Label Cancel. Đổi text của Lbl ChangePikture
        if (evt.getButton() == MouseEvent.BUTTON1) {
            this.jLblAvartar.setIcon(null);
            if(!loadAvartar(this.jLblAvartar, Client.USER.getPathAvartar())){
                        this.jLblAvartar.setIcon(null);
                    }
            this.jLblCancel.setVisible(false);
            this.jLblChangePicture.setText("Change picture");
        }
    }//GEN-LAST:event_jLblCancelMouseClicked

    private void jTxtFirstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTxtFirstNameMouseClicked

        if (evt.getButton() == MouseEvent.BUTTON1) {
            //Đổi text label Save thành "Save"
            this.jLblSaveFirstName.setText("Save");
            //Đổi màu chữ cho Label Cancel
            CHANGE_FOREGROUD_LABEL(this.jLblCancelFirstName, "BLUISH");
            //Đổi text label Cancel thành Cancel
            this.jLblCancelFirstName.setText("Cancel");
            //Cho phép sửa text của textfield
            this.jTxtFirstName.setEditable(true);
            this.jTxtFirstName.requestFocus();
            
            borderForTextField(this.jTxtFirstName);
        }
    }//GEN-LAST:event_jTxtFirstNameMouseClicked

    private void jTxtLastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTxtLastNameMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            //Đổi text label Save thành "Save"
            this.jLblSaveLastName.setText("Save");
            //Đổi màu chữ cho Label Cancel
            CHANGE_FOREGROUD_LABEL(this.jLblCancelLastName, "BLUISH");
            //Đổi text label Cancel thành Cancel
            this.jLblCancelLastName.setText("Cancel");
            //Cho phép sửa text của textfield
            this.jTxtLastName.setEditable(true);
            
            borderForTextField(this.jTxtLastName);
        }
    }//GEN-LAST:event_jTxtLastNameMouseClicked

    private void JTXT_BIRTH_DATEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTXT_BIRTH_DATEMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            //Khai báo panel ChoiceBirthDate
            ChoiceBirthDate choice = new ChoiceBirthDate();
            //Khai báo một dialog với Frame cha là Main
            DIALOG = new JDialog(Main.MAIN, "Choce birth date", true);
            //Thêm nội dung cho dialog
            DIALOG.add(choice);
            //Xác định kích thước của dialog bằng với kích thước của "choiceBirthDate"
            DIALOG.pack();
            //Không cho điều chỉnh kích thước dialog
            DIALOG.setResizable(false);
            //Vị trí hiển thị của dialog
            DIALOG.setLocation(480, 200);
            //Xét hiển thị
            DIALOG.setVisible(true);
            //Xét icon cho dialog
            DIALOG.setIconImage(new ImageIcon("images/icons/calendar.png").getImage());
            
        }
    }//GEN-LAST:event_JTXT_BIRTH_DATEMouseClicked

    private void JTXT_GENDERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTXT_GENDERMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            ChoiceGender choice = new ChoiceGender();
            DIALOG = new JDialog(Main.MAIN, "Choice gender", true);
            DIALOG.add(choice);
            DIALOG.pack();
            DIALOG.setResizable(false);
            DIALOG.setLocation(500, 220);
            DIALOG.setVisible(true);
            DIALOG.setIconImage(new ImageIcon("images/icons/gender.png").getImage());
        }
    }//GEN-LAST:event_JTXT_GENDERMouseClicked

    private void JTXT_EMAILMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTXT_EMAILMouseClicked
        if (evt.getButton() == MouseEvent.BUTTON1) {
            //Đổi text label Save thành "Save"
            this.JLBL_SAVE_EMAIL.setText("Save");
            //Đổi màu chữ cho Label Cancel
            CHANGE_FOREGROUD_LABEL(this.JLBL_CANCEL_EMAIL, "BLUISH");
            //Đổi text label Cancel thành Cancel
            this.JLBL_CANCEL_EMAIL.setText("Cancel");
            //Cho phép sửa text của textfield
            this.JTXT_EMAIL.setEditable(true);
            this.JTXT_EMAIL.requestFocus();
            
            borderForTextField(this.JTXT_EMAIL);
        }
    }//GEN-LAST:event_JTXT_EMAILMouseClicked

    private void jLblCancelFirstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelFirstNameMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(this.jLblCancelFirstName.getText().equals("Cancel")){
                this.jLblSaveFirstName.setText("         ");
                CHANGE_FOREGROUD_LABEL(this.jLblCancelFirstName, "GRAY");
                this.jLblCancelFirstName.setText("  Public");
                
                //Bỏ border của textfield và ko cho phép chỉnh sữa nữa
                removeBorderForTextField(jTxtFirstName);
                this.jTxtFirstName.setEditable(false);
                
                //Tải lại thông tin đúng cho textfeild
                this.jTxtFirstName.setText(Client.USER.getFirstName());
            }
        }
    }//GEN-LAST:event_jLblCancelFirstNameMouseClicked

    private void jLblCancelLastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCancelLastNameMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(this.jLblCancelLastName.getText().equals("Cancel")){
                this.jLblSaveLastName.setText("         ");
                CHANGE_FOREGROUD_LABEL(this.jLblCancelLastName, "GRAY");
                this.jLblCancelLastName.setText("   Public");
                
                //Bỏ border của textfield và ko cho phép chỉnh sữa nữa
                this.jTxtLastName.setEditable(false);
                removeBorderForTextField(jTxtLastName);
                
                //Tải lại thông tin hiển thị cho textfield vì người dùng ko chỉnh sửa
                //Nên phải tải thông tin đúng của user hiện tại lên
                this.jTxtLastName.setText(Client.USER.getLastName());
            }
        }
    }//GEN-LAST:event_jLblCancelLastNameMouseClicked

    private void JLBL_CANCEL_BIRTH_DATEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_BIRTH_DATEMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(FrmProfile.JLBL_CANCEL_BIRTH_DATE.getText().equals("Cancel")){
                JLBL_SAVE_BIRTH_DATE.setText("         ");
                CHANGE_FOREGROUD_LABEL(JLBL_CANCEL_BIRTH_DATE, "GRAY");
                JLBL_CANCEL_BIRTH_DATE.setText("   Public");
                
                //Bỏ border của textfield và ko cho phép chỉnh sữa nữa
                removeBorderForTextField(JTXT_BIRTH_DATE);
                JTXT_BIRTH_DATE.setEditable(false);
                
                JTXT_BIRTH_DATE.setText(Client.USER.getBirthDate());
            }
        }
    }//GEN-LAST:event_JLBL_CANCEL_BIRTH_DATEMouseClicked

    private void JLBL_CANCEL_GENDERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_GENDERMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(FrmProfile.JLBL_CANCEL_GENDER.getText().equals("Cancel")){
                JLBL_SAVE_GENDER.setText("         ");
                CHANGE_FOREGROUD_LABEL(JLBL_CANCEL_GENDER, "GRAY");
                JLBL_CANCEL_GENDER.setText("   Public");
                JTXT_GENDER.setEditable(false);
                removeBorderForTextField(JTXT_GENDER);
                
                JTXT_GENDER.setText(Client.USER.getGender());
            }
        }
    }//GEN-LAST:event_JLBL_CANCEL_GENDERMouseClicked

    private void JLBL_CANCEL_EMAILMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_CANCEL_EMAILMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(FrmProfile.JLBL_CANCEL_EMAIL.getText().equals("Cancel")){
                FrmProfile.JLBL_SAVE_EMAIL.setText("         ");
                CHANGE_FOREGROUD_LABEL(FrmProfile.JLBL_CANCEL_EMAIL, "GRAY");
                FrmProfile.JLBL_CANCEL_EMAIL.setText("Private");
                FrmProfile.JTXT_EMAIL.setEditable(false);
                
                removeBorderForTextField(JTXT_EMAIL);
                
                FrmProfile.JTXT_EMAIL.setText(Client.USER.getEmail());
            }
        }
    }//GEN-LAST:event_JLBL_CANCEL_EMAILMouseClicked

    private void jLblSaveLastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSaveLastNameMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(this.jLblSaveLastName.getText().equals("Save")){
                Client.USER.setFirstName(this.jTxtLastName.getText());
                Client.CLIENT.updateUser();

                if(this.jLblCancelLastName.getText().equals("Cancel")){
                    this.jLblSaveLastName.setText("         ");
                    CHANGE_FOREGROUD_LABEL(this.jLblCancelLastName, "GRAY");
                    this.jLblCancelLastName.setText("   Public");
                }
                this.jTxtLastName.setEditable(false);
                removeBorderForTextField(jTxtLastName);
                
                //Cật nhật lại tên trên phần thông tin
                Main.JLBL_NAME.setText(Client.USER.getFirstName() + " " + Client.USER.getLastName());
            }
        }
    }//GEN-LAST:event_jLblSaveLastNameMouseClicked

    private void jLblSaveFirstNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblSaveFirstNameMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(this.jLblSaveFirstName.getText().equals("Save")){
                Client.USER.setFirstName(this.jTxtFirstName.getText());
                Client.CLIENT.updateUser();

                if(this.jLblCancelFirstName.getText().equals("Cancel")){
                    this.jLblSaveFirstName.setText("         ");
                    CHANGE_FOREGROUD_LABEL(this.jLblCancelFirstName, "GRAY");
                    this.jLblCancelFirstName.setText("  Public");
                }
                this.jTxtFirstName.setEditable(false);
                removeBorderForTextField(jTxtFirstName);
                
                //Cật nhật lại tên trên phần thông tin
                Main.JLBL_NAME.setText(Client.USER.getFirstName() + " " + Client.USER.getLastName());
            }
        }
    }//GEN-LAST:event_jLblSaveFirstNameMouseClicked

    private void JLBL_SAVE_BIRTH_DATEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_BIRTH_DATEMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
           if(JLBL_SAVE_BIRTH_DATE.getText().equals("Save")){
               DateFormatGuide dateFormatGuide = new DateFormatGuide(JTXT_BIRTH_DATE.getText(), DateFormatGuide.FROM_DMY_TO_YMD);
               String date = DateFormatGuide.NEW_DATE;
               Client.USER.setBirthDate(date);
               Client.CLIENT.updateUser();
               
                JLBL_SAVE_BIRTH_DATE.setText("         ");
                CHANGE_FOREGROUD_LABEL(JLBL_CANCEL_BIRTH_DATE, "GRAY");
                JLBL_CANCEL_BIRTH_DATE.setText("   Public");
                
                JTXT_BIRTH_DATE.setEditable(false);
                removeBorderForTextField(JTXT_BIRTH_DATE);
           }
       }
    }//GEN-LAST:event_JLBL_SAVE_BIRTH_DATEMouseClicked

    private void JLBL_SAVE_GENDERMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_GENDERMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            if(JLBL_SAVE_GENDER.getText().equals("Save")){
                Client.USER.setGender(JTXT_GENDER.getText());
                Client.CLIENT.updateUser();
                
                JLBL_SAVE_GENDER.setText("         ");
                CHANGE_FOREGROUD_LABEL(JLBL_CANCEL_GENDER, "GRAY");
                JLBL_CANCEL_GENDER.setText("   Public");
                JTXT_GENDER.setEditable(false);
                removeBorderForTextField(JTXT_GENDER);
            }
        }
    }//GEN-LAST:event_JLBL_SAVE_GENDERMouseClicked

    private void JLBL_SAVE_EMAILMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JLBL_SAVE_EMAILMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1){
            
            //Xác nhận email chính chủ
            ConfirmationEmail confirEmail = new ConfirmationEmail(true, JTXT_EMAIL.getText());
            confirEmail.jBtnConfirm.setVisible(true);
            DIALOG = new JDialog();
            
            //Thêm icon cho dialog
            DIALOG.setIconImage(new ImageIcon("images/icons/email.png").getImage());
            
            DIALOG.add(confirEmail);
            DIALOG.setTitle("Confirm email");
            DIALOG.pack();
            DIALOG.setResizable(false);
            DIALOG.setVisible(true);
            DIALOG.setLocation(500, 130);
        }
    }//GEN-LAST:event_JLBL_SAVE_EMAILMouseClicked

    private void borderForTextField(JTextField jTxt){
        Color color = new Color(0,102,255);
        Border border = BorderFactory.createLineBorder(color, 1, true);
        jTxt.setBorder(border);
    }
    
    private void removeBorderForTextField(JTextField jTxt){
        jTxt.setBorder(null);
    }
    
    private String getPathEmageFromFileChooser() {

        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.getName().endsWith(".jpg") || f.getName().endsWith(".png")) {
                    return true;
                }
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "image";
            }
        };
        fileChooser.setFileFilter(filter);

        //Hiển thị dialog chọn file
        int confirm = fileChooser.showOpenDialog(null);
        String path = "";
        
        if (confirm == JFileChooser.OPEN_DIALOG) {
            File file = fileChooser.getSelectedFile();
            try {
                path = file.getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(FrmProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return path;
    }

    private void underLineTextInLabel(JLabel lbl, int isOn) {
        Font font = lbl.getFont();
        Map attributes = font.getAttributes();
        if (isOn == 1) {
            attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        } else {
            attributes.put(TextAttribute.UNDERLINE, -1);
        }
        lbl.setFont(font.deriveFont(attributes));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel JLBL_CANCEL_BIRTH_DATE;
    public static javax.swing.JLabel JLBL_CANCEL_EMAIL;
    public static javax.swing.JLabel JLBL_CANCEL_GENDER;
    public static javax.swing.JLabel JLBL_SAVE_BIRTH_DATE;
    public static javax.swing.JLabel JLBL_SAVE_EMAIL;
    public static javax.swing.JLabel JLBL_SAVE_GENDER;
    public static javax.swing.JTextField JTXT_BIRTH_DATE;
    public static javax.swing.JTextField JTXT_EMAIL;
    public static javax.swing.JTextField JTXT_GENDER;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLbl;
    private javax.swing.JLabel jLblAvartar;
    private javax.swing.JLabel jLblCancel;
    private javax.swing.JLabel jLblCancelFirstName;
    private javax.swing.JLabel jLblCancelLastName;
    private javax.swing.JLabel jLblChangePicture;
    private javax.swing.JLabel jLblSaveFirstName;
    private javax.swing.JLabel jLblSaveLastName;
    private javax.swing.JLabel jLblUserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTxtFirstName;
    private javax.swing.JTextField jTxtLastName;
    // End of variables declaration//GEN-END:variables
}
