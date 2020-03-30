/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGraphique;

import Sound.Sound;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author utilisateur
 */
public class SiegeView extends JFrame{
    //<editor-fold desc="Attributs">
    private Sound Sound;
    private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur,hauteur);
    private JTextField txf_date_CA = new JTextField(30);
    private JTextField txf_ref_article = new JTextField(30);
    private JTextField txf_quantite_article = new JTextField(30);
    private JLabel lbl_calculer_chiffre_affaire,lbl_date_CA,lbl_chiffre_affaire,lbl_ajouter_produit,lbl_ref_article,lbl_quantite_article;
    private JButton btn_quitter,btn_calculer_CA,btn_ajouter;
    //</editor-fold>
    
    //<editor-fold desc="Constructeur">
    public SiegeView(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(dimension);
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        
        //<editor-fold desc="JTextFields">
        txf_date_CA.setVisible(true);
        txf_date_CA.setBackground(Color.white);
        txf_date_CA.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_date_CA.setFont(new Font("Calibri", Font.PLAIN, 18));
        txf_date_CA.setHorizontalAlignment(JTextField.CENTER);
        txf_date_CA.setSelectedTextColor(Color.orange);
        txf_date_CA.setForeground(Color.gray);
        zoneDessin.add(txf_date_CA);
        
        txf_ref_article.setVisible(true);
        txf_ref_article.setBackground(Color.white);
        txf_ref_article.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_ref_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        txf_ref_article.setHorizontalAlignment(JTextField.CENTER);
        txf_ref_article.setSelectedTextColor(Color.red);
        txf_ref_article.setForeground(Color.gray);
        zoneDessin.add(txf_ref_article);
        
        txf_quantite_article.setVisible(true);
        txf_quantite_article.setBackground(Color.white);
        txf_quantite_article.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_quantite_article.setFont(new Font("Roboto", Font.PLAIN, 18));
        txf_quantite_article.setHorizontalAlignment(JTextField.CENTER);
        txf_quantite_article.setSelectedTextColor(Color.blue);
        txf_quantite_article.setForeground(Color.gray);
        zoneDessin.add(txf_quantite_article);
        //</editor-fold>
        
        //<editor-fold desc="JLabel">
        lbl_calculer_chiffre_affaire = new JLabel();
        lbl_date_CA = new JLabel();
        lbl_chiffre_affaire = new JLabel();
        lbl_ajouter_produit = new JLabel();
        lbl_ref_article = new JLabel();
        lbl_quantite_article = new JLabel();
        
        
        lbl_calculer_chiffre_affaire.setFont(new Font("Roboto", Font.PLAIN, 24));
        lbl_date_CA.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_chiffre_affaire.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_ajouter_produit.setFont(new Font("Roboto", Font.PLAIN, 24));
        lbl_ref_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_quantite_article.setFont(new Font("Roboto", Font.PLAIN, 18));
        
        lbl_calculer_chiffre_affaire.setForeground(Color.black);
        lbl_date_CA.setForeground(Color.black);
        lbl_chiffre_affaire.setForeground(Color.black);
        lbl_ajouter_produit.setForeground(Color.black);
        lbl_ref_article.setForeground(Color.black);
        lbl_quantite_article.setForeground(Color.black);
        
        lbl_calculer_chiffre_affaire.setText("Calculer le chiffre d'affaire");
        lbl_date_CA.setText("Indiquez une date pour le calcul du Chiffre d'affaire : ");
        lbl_chiffre_affaire.setText("Chiffre d'affaire : ");
        lbl_ajouter_produit.setText("Ajouter un produit");
        lbl_ref_article.setText("Veuillez selectionner la référence de l'article : ");
        lbl_quantite_article.setText("Veuillez indiquer la quantité de l'article à ajouter : ");
        
        lbl_calculer_chiffre_affaire.setVisible(true);
        lbl_date_CA.setVisible(true);
        lbl_chiffre_affaire.setVisible(false);
        lbl_ajouter_produit.setVisible(true);
        lbl_ref_article.setVisible(true);
        lbl_quantite_article.setVisible(true);
        
        zoneDessin.add(lbl_calculer_chiffre_affaire);
        zoneDessin.add(lbl_date_CA);
        zoneDessin.add(lbl_chiffre_affaire);
        zoneDessin.add(lbl_ajouter_produit);
        zoneDessin.add(lbl_ref_article);
        zoneDessin.add(lbl_quantite_article);
        //</editor-fold>
        
        //<editor-fold desc="JButton">
        btn_calculer_CA = new JButton();
        btn_calculer_CA.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_calculer_CA.setForeground(Color.black);
        btn_calculer_CA.setText("Calculer CA");
        btn_calculer_CA.setVisible(true);
        zoneDessin.add(btn_calculer_CA);
        
        btn_calculer_CA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                lbl_chiffre_affaire.setVisible(true);
                zoneDessin.repaint();
            }
        });
        
        btn_ajouter = new JButton();
        btn_ajouter.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_ajouter.setForeground(Color.black);
        btn_ajouter.setText("Remettre en stock");
        btn_ajouter.setVisible(true);
        zoneDessin.add(btn_ajouter);
        
        btn_quitter = new JButton();
        btn_quitter.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_quitter.setForeground(Color.black);
        btn_quitter.setText("Quitter");
        btn_quitter.setVisible(true);
        zoneDessin.add(btn_quitter);
        
        btn_quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                JOptionPane jop = new JOptionPane();    	
                int option = jop.showConfirmDialog(null, 
                  "Voulez-vous quitter l'application ?", 
                  "Quitter le siège", 
                  JOptionPane.YES_NO_OPTION, 
                  JOptionPane.QUESTION_MESSAGE);

                if(option == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
        //</editor-fold>
        
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
            
            Sound = new Sound("/Ressources/musique1.wav");
            Sound.play();
            lbl_calculer_chiffre_affaire.setBounds(100, 100, 400, 40);
            lbl_date_CA.setBounds(200, 200, 400, 40);
            txf_date_CA.setBounds(largeur/2-100, 200, 200, 40);
            lbl_chiffre_affaire.setBounds(200, 250, 400, 40);
            if(lbl_chiffre_affaire.isVisible()){
                btn_calculer_CA.setBounds(largeur/2-100, 300, 200, 40);
            } else {
                btn_calculer_CA.setBounds(largeur/2-100, 250, 200, 40);
            }
            lbl_ajouter_produit.setBounds(100, 400, 200, 40);
            lbl_ref_article.setBounds(200, 450, 400, 40);
            lbl_quantite_article.setBounds(200, 500, 400, 40);
            txf_ref_article.setBounds(largeur/2-100, 450, 200, 40);
            txf_quantite_article.setBounds(largeur/2-100, 500, 200, 40);
            btn_ajouter.setBounds(largeur/2-100, 550, 200, 40);
            btn_quitter.setBounds(largeur/2-100, hauteur-100, 200, 40);
            
        }
    }
}
