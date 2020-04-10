/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfaceGraphique;

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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Sound.Sound;
import modele.RelationArticleFacture;
import serveur.magasin.PosteCaisseFonctionnalite;

/**
 *
 * @author utilisateur
 */
public class CaisseView extends JFrame {
    //<editor-fold desc="Attributs">
	private Sound Sound;
	private int idMagasin;
    private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur,hauteur);
    private JTextField txf_quantite = new JTextField(10);
    private JTextField txf_id = new JTextField(5);
    private JComboBox cmb_ref_article;
    private JLabel lbl_ref_article, lbl_quantite, lbl_saisie_id, lbl_sous_total_indiq, lbl_sous_total;
    private JTextArea txa_liste_article_gauche, txa_liste_article_droite;
    private JButton btn_ajouter_article, btn_payer_facture, btn_consulter_facture, btn_quitter;
    
    // classe façade qu'on récupère à partir de RMI
    PosteCaisseFonctionnalite facadePosteCaisse = null;
    //</editor-fold>
    
    //<editor-fold desc="Constructeur">
    public CaisseView(int ID){
        super();
        idMagasin = ID;
        
     // On récupère notre connexion à notre façade
        try {
            Registry registry = LocateRegistry.getRegistry();
            facadePosteCaisse = (PosteCaisseFonctionnalite) registry.lookup("rmi://localhost/Caisse");
            System.out.println("connecté au server");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("non connecté au server");
            e.printStackTrace();
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(dimension);
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        
        //<editor-fold desc="JButton">
        lbl_ref_article = new JLabel();
        lbl_quantite = new JLabel();
        lbl_saisie_id = new JLabel();
        lbl_sous_total_indiq = new JLabel();
        lbl_sous_total = new JLabel();
        
        lbl_ref_article.setFont(new Font("Calibri", Font.PLAIN, 24));
        lbl_quantite.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_saisie_id.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_sous_total_indiq.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_sous_total.setFont(new Font("Calibri", Font.PLAIN, 18));
        
        lbl_ref_article.setForeground(Color.black);
        lbl_quantite.setForeground(Color.black);
        lbl_saisie_id.setForeground(Color.black);
        lbl_sous_total_indiq.setForeground(Color.black);
        lbl_sous_total.setForeground(Color.black);
        
        lbl_ref_article.setText("Veuillez selectionner l'article : ");
        lbl_quantite.setText("Veuillez indiquer la quantité : ");
        lbl_saisie_id.setText("Veuillez saisir l'ID de la facture : ");
        lbl_sous_total_indiq.setText("Sous-Total : ");
        lbl_sous_total.setText("0.0");
        
        lbl_ref_article.setVisible(true);
        lbl_quantite.setVisible(true);
        lbl_saisie_id.setVisible(true);
        lbl_sous_total_indiq.setVisible(true);
        lbl_sous_total.setVisible(true);
        
        zoneDessin.add(lbl_ref_article);
        zoneDessin.add(lbl_quantite);
        zoneDessin.add(lbl_saisie_id);
        zoneDessin.add(lbl_sous_total_indiq);
        zoneDessin.add(lbl_sous_total);
        //</editor-fold>
        
      //<editor-fold desc="JComboBox">
        cmb_ref_article = new JComboBox();
        cmb_ref_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        
        cmb_ref_article.addItem("----------------------------");
        try {
			for (int i = 0; i < facadePosteCaisse.stock().size(); i++) {
			    cmb_ref_article.addItem(facadePosteCaisse.stock().get(i).getReference());
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
        cmb_ref_article.setVisible(true);
        zoneDessin.add(cmb_ref_article);
        //</editor-fold>
        
        //<editor-fold desc="JTextArea">
        txa_liste_article_gauche = new JTextArea();
        txa_liste_article_droite = new JTextArea();
        
        txa_liste_article_gauche.setFont(new Font("Calibri", Font.PLAIN, 22));
        txa_liste_article_droite.setFont(new Font("Calibri", Font.PLAIN, 22));
        
        txa_liste_article_gauche.setForeground(Color.black);
        txa_liste_article_droite.setForeground(Color.black);
        
        txa_liste_article_gauche.setText("Date : ");
        txa_liste_article_droite.setText("ID : ");
        
        txa_liste_article_gauche.setVisible(true);
        txa_liste_article_droite.setVisible(true);
        
        zoneDessin.add(txa_liste_article_gauche);
        zoneDessin.add(txa_liste_article_droite);
        //</editor-fold>
        
        //<editor-fold desc="JTextFields">
        txf_quantite.setFont(new Font("Calibri", Font.PLAIN, 22));
        txf_id.setFont(new Font("Calibri", Font.PLAIN, 22));
        
        txf_quantite.setForeground(Color.black);
        txf_id.setForeground(Color.black);
        
        txf_quantite.setText("");
        txf_id.setText("");
        
        txf_quantite.setVisible(true);
        txf_id.setVisible(true);
        
        zoneDessin.add(txf_quantite);
        zoneDessin.add(txf_id);
      //</editor-fold>
        
        //<editor-fold desc="JButton">
        btn_ajouter_article = new JButton();
        btn_ajouter_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_ajouter_article.setForeground(Color.black);
        btn_ajouter_article.setText("Ajouter");
        btn_ajouter_article.setVisible(true);
        zoneDessin.add(btn_ajouter_article);
        btn_ajouter_article.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cmb_ref_article.getSelectedIndex() == 0) {
                	JOptionPane jop = new JOptionPane();    	
                	jop.showMessageDialog(null, 
                      "Veuillez sélectionner un article", 
                      "Aucune sélection",
                      JOptionPane.ERROR_MESSAGE);

                } else {
                	ajouterArticle(cmb_ref_article.getSelectedIndex());
                }
            }
        });
        
        btn_payer_facture = new JButton();
        btn_payer_facture.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_payer_facture.setForeground(Color.black);
        btn_payer_facture.setText("Payer");
        btn_payer_facture.setVisible(true);
        zoneDessin.add(btn_payer_facture);
        btn_payer_facture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                payerFacture();
            }
        });
        
        btn_consulter_facture = new JButton();
        btn_consulter_facture.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_consulter_facture.setForeground(Color.black);
        btn_consulter_facture.setText("Consulter");
        btn_consulter_facture.setVisible(true);
        zoneDessin.add(btn_consulter_facture);
        btn_consulter_facture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consulterFacture();
            }
        });
        
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
    
    public void ajouterArticle(int idArticle){
    	if (txf_quantite.getText().trim() == "") {
    		JOptionPane jop = new JOptionPane();    	
        	jop.showMessageDialog(null, 
              "Veuillez indiquer une quantité", 
              "Aucune quantité",
              JOptionPane.WARNING_MESSAGE);
    	} else {
    		Double prix_unitaire;
			try {
				prix_unitaire = facadePosteCaisse.prixArticle(cmb_ref_article.getSelectedItem().toString());
			} catch (RemoteException e) {
				prix_unitaire = 0.0;
				JOptionPane jop = new JOptionPane();    	
	        	jop.showMessageDialog(null, 
	              "Erreur lors du chargement du prix", 
	              "Prix Inconnu",
	              JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
    		txa_liste_article_gauche.setText(txa_liste_article_gauche + "</br>" + cmb_ref_article.getSelectedItem().toString() 
    				+ " x" + (Integer) Integer.parseInt(txf_quantite.getText()) + " " + (Double) Double.parseDouble(txf_quantite.getText())*prix_unitaire);
    	}
    	
    }
    
    public void payerFacture(){
    	
    }
    
    public void consulterFacture(){
    	if (txf_id.getText().trim() == "") {
    		JOptionPane jop = new JOptionPane();    	
        	jop.showMessageDialog(null, 
              "Veuillez indiquer un id valide", 
              "Aucun ID",
              JOptionPane.WARNING_MESSAGE);
    	} else {
    		try {
    			int ID = (Integer) Integer.parseInt(txf_id.getText().trim());
    			try {
    				List<RelationArticleFacture> lesArticles = facadePosteCaisse.consulterFacture(ID);
    			} catch (RemoteException e) {
    				System.out.println("chargement facture échoué");
    			}
    		} catch(Exception e) {
    			JOptionPane jop = new JOptionPane();    	
            	jop.showMessageDialog(null, 
                  "Veuillez indiquer un entier pour l'ID", 
                  "ID étrange",
                  JOptionPane.WARNING_MESSAGE);
    		}
    	}
    	txa_liste_article_droite.setText("");
    }
    
    public class Dessin extends JPanel {
        //private Integer menu;
        
        public Dessin() {
        	Sound = new Sound("/Ressources/musique3.wav");
            //Sound.play();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            txf_quantite.setBounds(450, 100, 80, 40);
            txf_id.setBounds(largeur/2+400, 150, 50, 40);
            
            cmb_ref_article.setBounds(450, 50, 200, 32);
            lbl_ref_article.setBounds(100, 50, 400, 40);
            lbl_quantite.setBounds(100, 100, 300, 40);
            lbl_saisie_id.setBounds(largeur/2+100, 150, 300, 40);
            lbl_sous_total_indiq.setBounds(100, 150, 200, 40);
            lbl_sous_total.setBounds(450, 150, 350, 40);
            txa_liste_article_gauche.setBounds(100, 250, 600, 400);
            txa_liste_article_droite.setBounds(largeur/2+100, 250, 600, 400);
            btn_ajouter_article.setBounds(450, 200, 200, 40);
            btn_payer_facture.setBounds(largeur/2-500, hauteur-175, 200, 40);
            btn_consulter_facture.setBounds(largeur/2+300, hauteur-175, 200, 40);
            btn_quitter.setBounds(largeur/2-100, hauteur-100, 200, 40);
        }
    }
}
