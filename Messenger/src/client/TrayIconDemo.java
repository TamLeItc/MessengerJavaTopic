/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author itcde
 */
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class TrayIconDemo {

    
    public void handle(){
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            try { 
                td.displayTray();
                System.exit(1);
            } catch (AWTException ex) {
                Logger.getLogger(TrayIconDemo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(TrayIconDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray() throws AWTException, java.net.MalformedURLException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();
        
        //If the icon is a file
        ImageIcon icon = new ImageIcon("images/icons/email.png");
        //Alternative (if the icon is on the classpath):
        
        TrayIcon trayIcon = new TrayIcon(icon.getImage(), "Tray Demo");
        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo:");
        tray.add(trayIcon);
        trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
        
                
    }
}
