package InterfaceGraphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Sound.Sound;
import dao.MagasinDAOMySQL;
import facade.CaisseFacade;
import facade.PosteClientFacade;
import modele.Magasin;

public class GeneralView extends JFrame {
	private Sound Sound;
	private Dessin zoneDessin;
    private int largeur = 240;
    private int hauteur = 312;
    private Dimension dimension = new Dimension(largeur,hauteur);
    private JComboBox cmb_magasin;
    private JButton btn_siege, btn_ordinateur, btn_caisse, btn_quitter;
    
  //<editor-fold desc="Constructeur">
    public GeneralView(){
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        zoneDessin = new Dessin();
        zoneDessin.setOpaque(true);
        zoneDessin.setPreferredSize(dimension);
        zoneDessin.setBorder(BorderFactory.createEtchedBorder());
        
      //<editor-fold desc="JComboBox">
        cmb_magasin = new JComboBox();
        cmb_magasin.setFont(new Font("Calibri", Font.PLAIN, 18));
        
        cmb_magasin.addItem("----------------------------");
        LinkedList<Magasin> mags = MagasinDAOMySQL.getInstance().findall();
        for (int i = 0; i < mags.size(); i++) {
			cmb_magasin.addItem(mags.get(i).getVille());
		}
        cmb_magasin.setVisible(true);
        zoneDessin.add(cmb_magasin);
        //</editor-fold>
        
      //<editor-fold desc="JButton">
        btn_siege = new JButton();
        btn_siege.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_siege.setForeground(Color.black);
        btn_siege.setText("Siège");
        btn_siege.setVisible(true);
        zoneDessin.add(btn_siege);
        btn_siege.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	new LanceurSiege();
            }
        });
        
        btn_ordinateur = new JButton();
        btn_ordinateur.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_ordinateur.setForeground(Color.black);
        btn_ordinateur.setText("Ordinateur de travail");
        btn_ordinateur.setVisible(true);
        zoneDessin.add(btn_ordinateur);
        btn_ordinateur.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	int ID = MagasinDAOMySQL.getInstance().findByVille(cmb_magasin.getSelectedItem().toString());
            	PosteClientFacade.main(null);
            	new LanceurOrdiTravail(ID);
            }
        });
        
        btn_caisse = new JButton();
        btn_caisse.setFont(new Font("Calibri", Font.PLAIN, 18));
        btn_caisse.setForeground(Color.black);
        btn_caisse.setText("Caisse");
        btn_caisse.setVisible(true);
        zoneDessin.add(btn_caisse);
        btn_caisse.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
            	int ID = MagasinDAOMySQL.getInstance().findByVille(cmb_magasin.getSelectedItem().toString());
            	CaisseFacade.main(null);
            	new LanceurCaisse(ID);
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
    
    public class Dessin extends JPanel {
        //private Integer menu;
        
        public Dessin() {
        	Sound = new Sound("/Ressources/musique1.wav");
            //Sound.play();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            cmb_magasin.setBounds(20, 80, 200, 32);
            btn_siege.setBounds(20, 20, 200, 40);
            btn_ordinateur.setBounds(20, 132, 200, 40);
            btn_caisse.setBounds(20, 192, 200, 40);
            btn_quitter.setBounds(20, 252, 200, 40);
        }
    }
    
}
