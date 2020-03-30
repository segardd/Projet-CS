/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGraphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author utilisateur
 */
public class CaisseView extends JFrame {
    //<editor-fold desc="Attributs">
    private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur,hauteur);
    private JTextField txf_objet = new JTextField(30);
    private JLabel lbl_objet;
    //</editor-fold>
    
    //<editor-fold desc="Constructeur">
    public CaisseView(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(dimension);
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        
        zoneDessin.setVisible(true);
        this.getContentPane().add(zoneDessin);
        this.setLocation(new Point(200, 80));
        this.pack();
        this.setVisible(true);
    }
    //</editor-fold>
    
    public class Dessin extends JPanel {
        //private Integer menu;
        
        public Dessin() {
            //image = Toolkit.getDefaultToolkit().getImage("src/ResourcesTicTacToe/image_fond_quadrillage.png");
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
}
