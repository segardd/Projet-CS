package facade;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;

import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.Client;
import modele.Facture;
import modele.Magasin;
import modele.ModePaiement;
<<<<<<< HEAD
//import modele.RelationArticleFacture;
=======
import modele.RelationArticleFacture;
import serveur.magasin.PosteCaisseFonctionnalite;
>>>>>>> donatien


public class CaisseFacade implements PosteCaisseFonctionnalite{
    
<<<<<<< HEAD
    private DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
    private dao<Article> articleManager=factory.getArticleDAO();
    private dao<Client> clienManager= factory.getClientDAO();
    private dao<Facture> factureManager= factory.getFactureDAO();
    private dao<Magasin> magasinManager= factory.getMagasinDAO();
    //private dao<RelationArticleFacture> relationManager= factory.getRelationArticleFactureDAO();
=======
>>>>>>> donatien
    
private static CaisseFacade instance;

    
    private CaisseFacade() {
       
   }
   
   public static synchronized CaisseFacade getInstance() {
       
       if (instance == null) {
           instance = new CaisseFacade();
       }
       return instance;     
   }
    
    public String editerFacture(LinkedList<RelationArticleFacture> articles) {   //
        
        //TODO 
        System.out.println("");
        Facture facture= new Facture(0, new Date(System.currentTimeMillis()));
        facture.setArticles(articles);
        double somme=0;
        for(RelationArticleFacture rel : articles) {
            somme=rel.getLarticle().getPrix_unitaire()*rel.getQuantite();
        }
        facture.setTotale_facture(somme);
        
        
        
        return factureManager.create(facture).toString();
    }
    
    /**
     * 
     * @param facture , facture à payer
     * @param mode , mode de paiement choisis
     * @return la facture payée
     */
    /*public Facture PayerFacture(Facture facture, ModePaiement mode ) {
        facture.setId_mode_paiement(mode.getIdMode_paiement());
        facture=factureManager.update(facture);
        
<<<<<<< HEAD
        
    }*/
=======
        return null;
        
    }
>>>>>>> donatien
    
    /**
     * 
     * @param id , id du magasin dont on veut les factures
     * @return liste des factures de cce magasin
     */
    public LinkedList<Facture> ListeFacture(long id){
       return magasinManager.find(id).getFactures(); 
    }
    
    public static void main(String[] args) {
        
        
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
          
            CaisseFacade obj = CaisseFacade.getInstance();
          PosteCaisseFonctionnalite stub = (PosteCaisseFonctionnalite) UnicastRemoteObject.exportObject(obj, 0);

          // Bind the remote object's stub in the registry
          Registry registry = LocateRegistry.getRegistry();
          registry.bind("rmi://localhost/Hello", stub);

          System.err.println("Server ready");
      } catch (Exception e) {
          System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
      }
    }

}
