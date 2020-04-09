package InterfaceGraphique;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Sound.Sound;

public class GeneralView {
	private Sound Sound;
	private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur,hauteur);
    private JComboBox cmb_ref_article;
    private JButton btn_siege, btn_ordinateur,btn_caisse,btn_quitter;
    
  //<editor-fold desc="Constructeur">
    public GeneralView(){
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
        	Sound = new Sound("/Ressources/musique1.wav");
            //Sound.play();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
    
}
