/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author itcde
 */
public class DrawBackground {
    private JPanel _panel;
    
    public DrawBackground(JPanel panel, String pathImage) {
        this._panel = panel;
        PicPanel mainPanel = new PicPanel(pathImage);
        mainPanel.setBounds(0, 0, this._panel.getWidth(), this._panel.getHeight());
        this._panel.add(mainPanel);
    }

    class PicPanel extends JPanel {

        private BufferedImage image;
        private int imageWidth, imageHeight;

        public PicPanel(String fname) {

            //reads the image
            try {
                image = ImageIO.read(new File(fname));
                imageWidth = image.getWidth();
                imageHeight = image.getHeight();

            } catch (IOException ioe) {
                System.out.println("Could not read in the pic");
            }
        }
        
        //Warning. Unknown function
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(imageWidth, imageHeight);
        }
        
        //this will draw the image
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, _panel);
        }
    }

}
