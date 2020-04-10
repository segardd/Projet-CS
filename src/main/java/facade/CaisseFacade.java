package facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import dao.ArticleDAOMySQL;
import dao.FactureDAOMySQL;
import dao.ModePaiementDAOMySQL;
import dao.RelationArticleFactureDAOMySQL;
import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.Client;
import modele.Facture;
import modele.Magasin;
import modele.ModePaiement;

import modele.RelationArticleFacture;
import serveur.magasin.PosteCaisseFonctionnalite;

import modele.RelationArticleFacture;



public class CaisseFacade implements PosteCaisseFonctionnalite{
    

    private DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
    private dao<Article> articleManager=factory.getArticleDAO();
    private dao<Client> clienManager= factory.getClientDAO();
    private dao<Facture> factureManager= factory.getFactureDAO();
    private dao<Magasin> magasinManager= factory.getMagasinDAO();

    //private dao<RelationArticleFacture> relationManager= factory.getRelationArticleFactureDAO();

    private dao<RelationArticleFacture> relationManager= factory.getRelationArticleFactureDAO();

    
	private static CaisseFacade instance;

    
    private CaisseFacade() {
       
    }
   
    public static synchronized CaisseFacade getInstance() {
       
       if (instance == null) {
           instance = new CaisseFacade();
       }
       return instance;     
    }
    
    /**
     * 
     * @return la liste d'Article du magasin courant
     */
    public LinkedList<Article> stock(){
		return ArticleDAOMySQL.getInstance().findall();
    }
    
    public LinkedList<ModePaiement> moyensPaiement(){
    	return ModePaiementDAOMySQL.getInstance().findall();
    }
    
	public ModePaiement findModePaiementByRef(String ref) {
		return ModePaiementDAOMySQL.getInstance().findModePaiementByRef(ref);
	}
    
    public Double prixArticle(String ref) {
    	return ArticleDAOMySQL.getInstance().prixArticle(ref);
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
    
	public Article findArticleByRef(String ref){
		return ArticleDAOMySQL.getInstance().findArticleByRef(ref);
	}
    
    /**
     * 
     * @param facture , facture à payer
     * @param mode , mode de paiement choisis
     * @return la facture payée
     */
    public Facture PayerFacture(Facture facture, List<RelationArticleFacture> panier) {
        facture = FactureDAOMySQL.getInstance().create(facture);
        for (int i = 0; i < panier.size(); i++) {
        	RelationArticleFactureDAOMySQL.getInstance().create(panier.get(i));
        }
        return facture;
    }

    /**
     * 
     * @param id , id du magasin dont on veut les factures
     * @return liste des factures de cce magasin
     */
    public LinkedList<Facture> ListeFacture(long id){
       return magasinManager.find(id).getFactures(); 
    }
    
    public LinkedList<RelationArticleFacture> consulterFacture(int idFacture) {
    	return RelationArticleFactureDAOMySQL.getInstance().findByFacture(idFacture);
    }
    
    public static void main(String[] args) {
        
        
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
          
            CaisseFacade obj = CaisseFacade.getInstance();
          PosteCaisseFonctionnalite stub = (PosteCaisseFonctionnalite) UnicastRemoteObject.exportObject(obj, 0);

          // Bind the remote object's stub in the registry
          Registry registry = LocateRegistry.getRegistry();
          registry.bind("rmi://localhost/Caisse", stub);

          System.err.println("Server ready");
      } catch (Exception e) {
          System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
      }
    }
}
