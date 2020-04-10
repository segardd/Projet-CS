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
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Sound.Sound;
import dao.ArticleDAOMySQL;
import modele.Article;
import modele.RelationArticleMagasin;
import serveur.magasin.PosteClientFonctionnalite;

/**
 *
 * @author utilisateur
 */
public class OrdinateurTravailView extends JFrame {
    // <editor-fold desc="Attributs">
    private Sound Sound;
    private int idMagasin;
    private Dessin zoneDessin;
    private int largeur = 1500;
    private int hauteur = 900;
    private Dimension dimension = new Dimension(largeur, hauteur);
    private JTextField txf_objet = new JTextField(30);
    private JComboBox cmb_ref_article;
    private JTextField txf_quantite_article = new JTextField(30);
    private JLabel lbl_objet, lbl_tab_reference, lbl_tab_prix, lbl_tab_quantite, lbl_tab_famille, lbl_ajouter_produit,
            lbl_ref_article, lbl_quantite_article;
    private JButton btn_consulter_stock, btn_consulter_famille, btn_ajouter, btn_quitter;
    private JTable tab_stock;
    private Object[][] donnees = { { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " " } };
    private JTableHeader header;
    private String[] colonnes = { "Référence", "prix", "en stock", "famille" };

    // Normalement ce sera pas disponible à partir du client mais par
    // l'intermédiaire de la façade
    //private DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
    //private dao<Article> ArticlesManager=factory.getArticleDAO();

    // classe façade qu'on récupère à partir de RMI
    PosteClientFonctionnalite facadePosteClient = null;

    // </editor-fold>

    // <editor-fold desc="Constructeur">
    public OrdinateurTravailView(int ID) {
        super();
        idMagasin = ID;

        // On récupère notre connexion à notre façade
        try {
            Registry registry = LocateRegistry.getRegistry();
            facadePosteClient = (PosteClientFonctionnalite) registry.lookup("rmi://localhost/Client");

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

        txf_quantite_article.setVisible(true);
        txf_quantite_article.setBackground(Color.white);
        txf_quantite_article.setBorder(BorderFactory.createLineBorder(Color.black));
        txf_quantite_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        txf_quantite_article.setHorizontalAlignment(JTextField.CENTER);
        txf_quantite_article.setSelectedTextColor(Color.blue);
        txf_quantite_article.setForeground(Color.black);
        zoneDessin.add(txf_quantite_article);

        lbl_ajouter_produit = new JLabel();
        lbl_ref_article = new JLabel();
        lbl_quantite_article = new JLabel();
        lbl_tab_reference = new JLabel();
        lbl_tab_prix = new JLabel();
        lbl_tab_quantite = new JLabel();
        lbl_tab_famille = new JLabel();

        lbl_ajouter_produit.setFont(new Font("Calibri", Font.PLAIN, 24));
        lbl_ref_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_quantite_article.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_tab_reference.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_tab_prix.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_tab_quantite.setFont(new Font("Calibri", Font.PLAIN, 18));
        lbl_tab_famille.setFont(new Font("Calibri", Font.PLAIN, 18));

        lbl_ajouter_produit.setForeground(Color.black);
        lbl_ref_article.setForeground(Color.black);
        lbl_quantite_article.setForeground(Color.black);
        lbl_tab_reference.setForeground(Color.black);
        lbl_tab_prix.setForeground(Color.black);
        lbl_tab_quantite.setForeground(Color.black);
        lbl_tab_famille.setForeground(Color.black);

        lbl_ajouter_produit.setText("Ajouter un produit");
        lbl_ref_article.setText("Veuillez selectionner la référence de l'article : ");
        lbl_quantite_article.setText("Veuillez indiquer la quantité de l'article à ajouter : ");
        lbl_tab_reference.setText("Référence");
        lbl_tab_prix.setText("Prix");
        lbl_tab_quantite.setText("En stock");
        lbl_tab_famille.setText("Famille");

        lbl_ajouter_produit.setVisible(true);
        lbl_ref_article.setVisible(true);
        lbl_quantite_article.setVisible(true);
        lbl_tab_reference.setVisible(true);
        lbl_tab_prix.setVisible(true);
        lbl_tab_quantite.setVisible(true);
        lbl_tab_famille.setVisible(true);

        zoneDessin.add(lbl_ajouter_produit);
        zoneDessin.add(lbl_ref_article);
        zoneDessin.add(lbl_quantite_article);
        zoneDessin.add(lbl_tab_reference);
        zoneDessin.add(lbl_tab_prix);
        zoneDessin.add(lbl_tab_quantite);
        zoneDessin.add(lbl_tab_famille);

        // <editor-fold desc="JComboBox">
        cmb_ref_article = new JComboBox();
        cmb_ref_article.setFont(new Font("Calibri", Font.PLAIN, 18));

        cmb_ref_article.addItem("----------------------------");
        try {
			for (int i = 0; i < facadePosteClient.stock().size(); i++) {
			    cmb_ref_article.addItem(facadePosteClient.stock().get(i).getReference());
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        cmb_ref_article.setVisible(true);
        zoneDessin.add(cmb_ref_article);
        // </editor-fold>

        // <editor-fold desc="JButton">
        btn_consulter_stock = new JButton();
        btn_consulter_stock.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_consulter_stock.setForeground(Color.black);
        btn_consulter_stock.setText("Consulter le stock");
        btn_consulter_stock.setVisible(true);
        zoneDessin.add(btn_consulter_stock);

        btn_consulter_stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cmb_ref_article.getSelectedIndex() == 0) {
                    consulterStock();

                } else {
                    consulterStock(cmb_ref_article.getSelectedIndex(), idMagasin);
                }
            }
        });

        btn_consulter_famille = new JButton();
        btn_consulter_famille.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_consulter_famille.setForeground(Color.black);
        btn_consulter_famille.setText("Consulter les articles de la même famille");
        btn_consulter_famille.setVisible(true);
        zoneDessin.add(btn_consulter_famille);
        
        btn_consulter_famille.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consulterFamille(cmb_ref_article.getSelectedIndex(), idMagasin);
            }
        });

        btn_ajouter = new JButton();
        btn_ajouter.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_ajouter.setForeground(Color.black);
        btn_ajouter.setText("Remettre en stock");
        btn_ajouter.setVisible(true);
        zoneDessin.add(btn_ajouter);

        btn_ajouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterArticle();
            }
        });

        btn_quitter = new JButton();
        btn_quitter.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_quitter.setForeground(Color.black);
        btn_quitter.setText("Quitter");
        btn_quitter.setVisible(true);
        zoneDessin.add(btn_quitter);

        btn_quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane jop = new JOptionPane();
                int option = jop.showConfirmDialog(null, "Voulez-vous quitter l'application ?", "Quitter le siège",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        // </editor-fold>

        tab_stock = new JTable(donnees, colonnes);
        tab_stock.setName("Stock");
        TableColumnModel tcm = tab_stock.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(400); // Référence
        tcm.getColumn(1).setPreferredWidth(200); // prix
        tcm.getColumn(2).setPreferredWidth(200); // en stock
        tcm.getColumn(3).setPreferredWidth(400); // famille

        tab_stock.setVisible(true);
        header = tab_stock.getTableHeader();
        // zoneDessin.add(header);
        zoneDessin.add(tab_stock);

        zoneDessin.setVisible(true);
        this.getContentPane().add(zoneDessin);
        this.setLocation(new Point(200, 80));
        this.pack();
        this.setVisible(true);

    }
    // </editor-fold>

    private void ajouterArticle() {
        System.out.println("ajout : " + cmb_ref_article.getSelectedItem().toString());
        try {
            // appel grace au serveur
            System.out.println("appel serveur rmi");
            Article article = facadePosteClient.findArticleByRef(cmb_ref_article.getSelectedItem().toString());
            article.setNombre_exemplaire(article.getNombre_exemplaire() + (Integer) Integer.parseInt(txf_quantite_article.getText()));
            facadePosteClient.remettreEnStock(article);
            System.out.println("succés");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        zoneDessin.repaint();
    }
    
    private void consulterFamille(int idArticle, int idMagasin) {
    	try {
            // appel grace au serveur
            System.out.println("appel serveur rmi");
            LinkedList<Article> lesArticles = facadePosteClient.articleDeLaFamilleParRef(cmb_ref_article.getSelectedItem().toString());
            for (int i = 0; i < lesArticles.size(); i++) {
                donnees[i][0] = lesArticles.get(i).getReference();
                donnees[i][1] = lesArticles.get(i).getPrix_unitaire();
                donnees[i][2] = lesArticles.get(i).getNombre_exemplaire();
                donnees[i][3] = facadePosteClient.intituleDeLaFamille(lesArticles.get(i).getReference());
            }
            tab_stock.repaint();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        zoneDessin.repaint();
    }

    private void consulterStock() {
        System.out.println("consulter tous");
        
        try {
            // appel grace au serveur
            System.out.println("appel serveur rmi");
            LinkedList<Article> lesArticles = facadePosteClient.stock();
            for (int i = 0; i < lesArticles.size(); i++) {
                donnees[i][0] = lesArticles.get(i).getReference();
                donnees[i][1] = lesArticles.get(i).getPrix_unitaire();
                donnees[i][2] = lesArticles.get(i).getNombre_exemplaire();
                donnees[i][3] = facadePosteClient.intituleDeLaFamille(lesArticles.get(i).getReference());
            }
            tab_stock.repaint();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        zoneDessin.repaint();
    }

    private void consulterStock(int idArticle, int idMagasin) {
        System.out.println("consulter article : idArticle= " + idArticle + " , idMagasin=" + idMagasin);

        RelationArticleMagasin ArtMag;

        try {
            // appel grace au serveur
            System.out.println("appel serveur rmi");
            ArtMag = facadePosteClient.StockArticleDansMagasin(idArticle, idMagasin);

            Article Article = ArticleDAOMySQL.getInstance().find(ArtMag.getId_article());
            System.out.println("reference : " + Article.getReference());
            // Object[][] donnees2 = {{"" + Article.getReference(),""+
            // Article.getPrix_unitaire(),""+ ArtMag.getEn_stock(),""
            // +PosteClientFacade.getInstance().intituleDeLaFamille(Article.getReference())}};
            donnees[0][0] = Article.getReference();
            donnees[0][1] = Article.getPrix_unitaire();
            donnees[0][2] = ArtMag.getEn_stock();
            donnees[0][3] = facadePosteClient.intituleDeLaFamille(Article.getReference());
            // tab_stock = new JTable(donnees2, colonnes);
            tab_stock.repaint();
            // zoneDessin.repaint();
            
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public class Dessin extends JPanel {
        // private Integer menu;

        public Dessin() {
            Sound = new Sound("/Ressources/musique1.wav");
            // Sound.play();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // header.setBounds(100, 100, 1200, 50);
            lbl_tab_reference.setBounds(250, 60, 100, 40);
            lbl_tab_prix.setBounds(575, 60, 100, 40);
            lbl_tab_quantite.setBounds(750, 60, 100, 40);
            lbl_tab_famille.setBounds(1075, 60, 100, 40);

            tab_stock.setRowHeight(50);
            tab_stock.setBounds(100, 100, 1200, 250);
            lbl_ajouter_produit.setBounds(100, 400, 200, 40);
            lbl_ref_article.setBounds(200, 450, 400, 40);
            lbl_quantite_article.setBounds(200, 500, 400, 40);
            cmb_ref_article.setBounds(largeur / 2 - 100, 450, 200, 32);
            txf_quantite_article.setBounds(largeur / 2 - 100, 500, 200, 40);
            btn_consulter_stock.setBounds(largeur / 2 - 350, 550, 200, 40);
            btn_consulter_famille.setBounds(largeur / 2 - 125, 550, 350, 40);
            btn_ajouter.setBounds(largeur / 2 + 250, 550, 200, 40);
            btn_quitter.setBounds(largeur / 2 - 100, hauteur - 100, 200, 40);
        }
    }
}
