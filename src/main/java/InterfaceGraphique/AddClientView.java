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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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

import InterfaceGraphique.CaisseView.Dessin;
import Sound.Sound;
import modele.Client;
import modele.Facture;
import modele.ModePaiement;
import modele.RelationArticleFacture;
import serveur.magasin.PosteCaisseFonctionnalite;
import serveur.magasin.PosteClientFonctionnalite;

public class AddClientView extends JFrame {
    private Sound Sound;
    private int idMagasin;
    private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur, hauteur);
    private Long millis = System.currentTimeMillis();
    private Date date = new Date(millis);
    private JTextField txf_nom = new JTextField(30);
    private JTextField txf_prenom = new JTextField(50);
    private JTextField txf_mail = new JTextField(50);
    private JTextField txf_codePostal = new JTextField(10);
    private JTextField txf_ville = new JTextField(50);
    private JLabel lbl_nom;
    private JLabel lbl_prenom;
    private JLabel lbl_mail;
    private JLabel lbl_codePostal;
    private JLabel lbl_ville;
    private JLabel titre;
    private JButton btn_ajouter_client;
    private JButton btn_quitter;
    private LinkedList<JLabel> mesLabels = new LinkedList<JLabel>();
    private LinkedList<JTextField> mesFields = new LinkedList<JTextField>();
    PosteCaisseFonctionnalite facade;
    /**
     * 
     */
    private static final long serialVersionUID = -2481480541092085292L;

    public AddClientView(int ID,PosteCaisseFonctionnalite facade) {
        super();
        idMagasin = ID;

        
        this.facade=facade;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(dimension);
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        
        titre= new JLabel();
        titre.setText("Ajout de client");
        titre.setFont(new Font("Calibri", Font.PLAIN, 32));
        titre.setForeground(Color.black);
        titre.setVisible(true);
        zoneDessin.add(titre);
        
        lbl_nom = new JLabel();
        lbl_prenom = new JLabel();
        lbl_mail = new JLabel();
        lbl_codePostal = new JLabel();
        lbl_ville = new JLabel();

        
        lbl_nom.setText("Nom");
        lbl_prenom.setText("Prenom");
        lbl_mail.setText("Mail");
        lbl_codePostal.setText("CodePostal");
        lbl_ville.setText("Ville");
        
        
        mesLabels.add(lbl_nom);
        mesLabels.add(lbl_prenom);
        mesLabels.add(lbl_mail);
        mesLabels.add(lbl_codePostal);
        mesLabels.add(lbl_ville);
        
        
        
        
        
        
        mesFields.add(txf_nom);
        mesFields.add(txf_prenom);
        mesFields.add(txf_mail);
        mesFields.add(txf_codePostal);
        mesFields.add(txf_ville);

        
        
        for (JLabel label : mesLabels) {
            label.setFont(new Font("Calibri", Font.PLAIN, 24));
            label.setForeground(Color.black);
            label.setVisible(true);
           
        
        }
        
        for (JTextField field: mesFields) {
            field.setFont(new Font("Calibri", Font.PLAIN, 22));
            field.setForeground(Color.black);
            field.setText("");
            field.setVisible(true);
   
            

         
            

        }
        
        for (int i=0; i<mesLabels.size(); i++) {
            zoneDessin.add(mesLabels.get(i));
            zoneDessin.add(mesFields.get(i));
            
        }
        
        
        
        
        btn_ajouter_client = new JButton();
        btn_ajouter_client.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_ajouter_client.setForeground(Color.black);
        btn_ajouter_client.setText("Ajouter");
        btn_ajouter_client.setVisible(true);
        zoneDessin.add(btn_ajouter_client);
        btn_ajouter_client.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //TODO fonction de l'event
                
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
                    AddClientView.this.dispose();
                }
            }
        });
        
        zoneDessin.setVisible(true);
        this.getContentPane().add(zoneDessin);
        this.setLocation(new Point(200, 80));
        this.pack();
        this.setVisible(true);

    }
    
    private boolean ajoutClient() {
        String nom=txf_nom.getText();
        String prenom=txf_prenom.getText();
        String mail=txf_mail.getText();
        String codePostal=txf_codePostal.getText();
        String ville=txf_ville.getText();
        Client client;
        try {
            client = facade.createClient(nom, prenom, mail, codePostal, ville);
            return client.getIdClient()!=0;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        
    }

    public class Dessin extends JPanel {
        // private Integer menu;

        public Dessin() {
            Sound = new Sound("/Ressources/musique3.wav");
            // Sound.play();
        }
        @Override public void paintComponent(Graphics g) { 
            
            final int height= 40;
            int x=largeur/2-250;
            int y= 100;
            final int width= 500;
            super.paintComponent(g);
           
          /* txf_quantite.setBounds(450, 100, 80, 40); 
           txf_id.setBounds(largeur/2+400,150, 50, 40);
           
           cmb_ref_article.setBounds(450, 50, 200, 32); lbl_ref_article.setBounds(100,
           50, 400, 40); lbl_quantite.setBounds(100, 100, 300, 40);
           lbl_saisie_id.setBounds(largeur/2+100, 150, 300, 40);
           lbl_sous_total_indiq.setBounds(100, 150, 200, 40);
           lbl_sous_total.setBounds(450, 150, 350, 40); lbl_total_indiq.setBounds(105,
           610, 100, 40); lbl_total.setBounds(650, 610, 350, 40);
           txa_liste_article_gauche.setBounds(100, 250, 600, 400);
           txa_liste_article_droite.setBounds(largeur/2+100, 250, 600, 400);
           btn_ajouter_client.setBounds(450, 200, 200, 40);
           cmb_mode_paiement.setBounds(largeur/2-500, hauteur-235, 140, 32);
           btn_payer_facture.setBounds(largeur/2-500, hauteur-175, 200, 40);
           btn_consulter_facture.setBounds(largeur/2+300, hauteur-175, 200, 40);*/
           
           titre.setBounds(largeur/2-125,y,width, height);
           y+=100;
           
           for (int i=0; i< mesLabels.size(); i++) {
               mesLabels.get(i).setBounds(x, y, width, height);
               y+=50;
               mesFields.get(i).setBounds(x, y, width, height);
               y+=50;
               
               
           }
           btn_ajouter_client.setBounds(largeur/2-100, hauteur-100, 200, 40);
           btn_quitter.setBounds(largeur/2-100, hauteur-50, 200, 40); }
    }

    
      
     
    
  public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          public void run() {
              new AddClientView(1,null);
          }
      });
  }
}
