/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGraphique;

import Sound.Sound;
import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.SiegeModele;
import serveur.magasin.PosteCaisseFonctionnalite;
import serveur.magasin.SiegeFonctionnalite;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ComboBoxModel;


/**
 *
 * @author utilisateur
 */
public class SiegeView extends JFrame{
    //<editor-fold desc="Attributs">
    private Sound Sound;
    private SiegeModele modele = new SiegeModele();
    private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur,hauteur);
    private JTextField txf_date_JJ = new JTextField(2);
    private JTextField txf_date_MM = new JTextField(2);
    private JTextField txf_date_AAAA = new JTextField(4);
    private JComboBox cmb_ref_article;
    //private JTextField txf_quantite_article = new JTextField(30);
    private JLabel lbl_calculer_chiffre_affaire,lbl_chiffre_affaire_valeur,lbl_date_CA,lbl_chiffre_affaire/*,lbl_ajouter_produit,lbl_ref_article,lbl_quantite_article*/;
    private JButton btn_quitter,btn_calculer_CA/*,btn_ajouter*/;
    
    // classe façade qu'on récupère à partir de RMI
    SiegeFonctionnalite facadeSiege = null;
    //</editor-fold>
    
    //<editor-fold desc="Constructeur">
    public SiegeView(){
        super();
        
        // On récupère notre connexion à notre façade
        try {
            Registry registry = LocateRegistry.getRegistry();
            facadeSiege = (SiegeFonctionnalite) registry.lookup("rmi://localhost/Siege");
            System.out.println("connecté au server");
        } catch (Exception e) {
            System.out.println("non connecté au server");
            e.printStackTrace();
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(dimension);
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        
        //<editor-fold desc="JTextFields">
        txf_date_JJ.setVisible(true);
        txf_date_JJ.setBackground(Color.white);
        txf_date_JJ.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_date_JJ.setFont(new Font("Calibri", Font.PLAIN, 18));
        txf_date_JJ.setHorizontalAlignment(JTextField.CENTER);
        txf_date_JJ.setSelectedTextColor(Color.orange);
        txf_date_JJ.setForeground(Color.black);
        txf_date_JJ.setText("JJ");
        zoneDessin.add(txf_date_JJ);
        
        txf_date_MM.setVisible(true);
        txf_date_MM.setBackground(Color.white);
        txf_date_MM.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_date_MM.setFont(new Font("Calibri", Font.PLAIN, 18));
        txf_date_MM.setHorizontalAlignment(JTextField.CENTER);
        txf_date_MM.setSelectedTextColor(Color.orange);
        txf_date_MM.setForeground(Color.black);
        txf_date_MM.setText("MM");
        zoneDessin.add(txf_date_MM);
        
        txf_date_AAAA.setVisible(true);
        txf_date_AAAA.setBackground(Color.white);
        txf_date_AAAA.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_date_AAAA.setFont(new Font("Calibri", Font.PLAIN, 18));
        txf_date_AAAA.setHorizontalAlignment(JTextField.CENTER);
        txf_date_AAAA.setSelectedTextColor(Color.orange);
        txf_date_AAAA.setForeground(Color.black);
        txf_date_AAAA.setText("AAAA");
        zoneDessin.add(txf_date_AAAA);
        
        //<editor-fold desc="JLabel">
        lbl_calculer_chiffre_affaire = new JLabel();
        lbl_date_CA = new JLabel();
        lbl_chiffre_affaire = new JLabel();
        lbl_chiffre_affaire_valeur = new JLabel();
        
        
        lbl_calculer_chiffre_affaire.setFont(new Font("Calibri", Font.PLAIN, 24));
        lbl_date_CA.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_chiffre_affaire.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_chiffre_affaire_valeur.setFont(new Font("Calibri", Font.PLAIN, 18));
        
        lbl_calculer_chiffre_affaire.setForeground(Color.black);
        lbl_date_CA.setForeground(Color.black);
        lbl_chiffre_affaire.setForeground(Color.black);
        lbl_chiffre_affaire_valeur.setForeground(Color.black);
        
        lbl_calculer_chiffre_affaire.setText("Calculer le chiffre d'affaire");
        lbl_date_CA.setText("Indiquez une date pour le calcul du Chiffre d'affaire : ");
        lbl_chiffre_affaire.setText("Chiffre d'affaire : ");
        lbl_chiffre_affaire_valeur.setText("...");
        
        lbl_calculer_chiffre_affaire.setVisible(true);
        lbl_date_CA.setVisible(true);
        lbl_chiffre_affaire.setVisible(false);
        lbl_chiffre_affaire_valeur.setVisible(false);
        
        zoneDessin.add(lbl_calculer_chiffre_affaire);
        zoneDessin.add(lbl_date_CA);
        zoneDessin.add(lbl_chiffre_affaire);
        zoneDessin.add(lbl_chiffre_affaire_valeur);
        //</editor-fold>
        
        //<editor-fold desc="JComboBox">
        cmb_ref_article = new JComboBox();
        cmb_ref_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        
        cmb_ref_article.addItem("----------------------------");
        try {
			for(int i = 0;i < facadeSiege.stock().size(); i++){
			    cmb_ref_article.addItem(facadeSiege.stock().get(i).getReference());
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
        cmb_ref_article.setVisible(true);
        zoneDessin.add(cmb_ref_article);
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
        
        /*btn_ajouter = new JButton();
        btn_ajouter.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_ajouter.setForeground(Color.black);
        btn_ajouter.setText("Remettre en stock");
        btn_ajouter.setVisible(true);
        zoneDessin.add(btn_ajouter);
        
        btn_ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterArticle();
			}
		});*/
        
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
                	SiegeView.this.dispose();
                }
            }
        });
        //</editor-fold>
           
        //zoneDessin.add(dtp);
        
        zoneDessin.setVisible(true);
        this.getContentPane().add(zoneDessin);
        this.setLocation(new Point(200, 80));
        this.pack();
        this.setVisible(true);
    }
    //</editor-fold>
    
    /*private void ajouterArticle()
	{
		
	}*/
    
    public class Dessin extends JPanel {
        //private Integer menu;
        
        public Dessin() {
            //image = Toolkit.getDefaultToolkit().getImage("src/ResourcesTicTacToe/image_fond_quadrillage.png");
        	Sound = new Sound("/Ressources/musique2.wav");
            Sound.play();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            
            lbl_calculer_chiffre_affaire.setBounds(100, 100, 400, 40);
            lbl_date_CA.setBounds(200, 200, 400, 40);
            txf_date_JJ.setBounds(largeur/2-100, 200, 40, 40);
            txf_date_MM.setBounds(largeur/2-50, 200, 40, 40);
            txf_date_AAAA.setBounds(largeur/2, 200, 80, 40);
            lbl_chiffre_affaire.setBounds(200, 250, 400, 40);
            if(lbl_chiffre_affaire.isVisible()){
                btn_calculer_CA.setBounds(largeur/2-100, 300, 200, 40);
                lbl_chiffre_affaire_valeur.setBounds(largeur/2-100, 250, 200, 40);
                try {
                    Date date = new Date(Integer.parseInt(txf_date_JJ.getText()),
                        Integer.parseInt(txf_date_MM.getText()),
                        Integer.parseInt(txf_date_AAAA.getText()));
                    lbl_chiffre_affaire_valeur.setText("" + modele.getChiffreAffaire(date));
                } catch(Exception e){
                    //e.printStackTrace();
                    lbl_chiffre_affaire_valeur.setText("Erreur de date");
                }
                
                
                lbl_chiffre_affaire_valeur.setVisible(true);
            } else {
                btn_calculer_CA.setBounds(largeur/2-100, 250, 200, 40);
            }
            /*lbl_ajouter_produit.setBounds(100, 400, 200, 40);
            lbl_ref_article.setBounds(200, 450, 400, 40);
            lbl_quantite_article.setBounds(200, 500, 400, 40);
            cmb_ref_article.setBounds(largeur/2-100, 450, 200, 32);
            txf_quantite_article.setBounds(largeur/2-100, 500, 200, 40);
            btn_ajouter.setBounds(largeur/2-100, 550, 200, 40);*/
            btn_quitter.setBounds(largeur/2-100, hauteur-100, 200, 40);
            
        }
    }
}
