package facade;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;

import dao.ArticleDAOMySQL;
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



public class CaisseFacade implements PosteCaisseFonctionnalite,Serializable{
    

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
           Runtime rt = Runtime.getRuntime();
           try {
               ProcessBuilder builder= new ProcessBuilder();
               builder.command("cmd.exe","%JAVA_HOME%\\bin\\rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false");
               builder.directory(new File("D:\\Users\\donat\\eclipse-workspace\\ProjetCS\\target\\classes"));
               Process pr= builder.start();
               
            /*Process pr= rt.exec("cmd.exe /c D:");
            pr =rt.exec("cmd.exe /c cd D:\\Users\\donat\\eclipse-workspace\\ProjetCS\\target\\classes");
            pr = rt.exec("cmd.exe /c %JAVA_HOME%\\bin\\rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false");*/
               
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
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
    
    /**
     * 
     * @param facture , facture à payer
     * @param mode , mode de paiement choisis
     * @return la facture payée
     */
    public Facture PayerFacture(Facture facture, ModePaiement mode) {
        facture.setId_mode_paiement(mode.getIdMode_paiement());
        facture=factureManager.update(facture);
        return null;
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
    
    public static void main(String[] args) throws IOException {
        
        
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
          
            CaisseFacade obj = CaisseFacade.getInstance();
          PosteCaisseFonctionnalite stub = (PosteCaisseFonctionnalite) UnicastRemoteObject.exportObject(obj, 0);

          // Bind the remote object's stub in the registry
          String host = (args.length < 1) ? null : args[0];

          Registry registry = LocateRegistry.getRegistry(host);
          registry.bind("rmi://localhost/Caisse", stub);

          System.err.println("Server ready");
      } catch (Exception e) {
          System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
          Runtime rt = Runtime.getRuntime();
          Process pr = rt.exec("taskkill /IM \"rmiregistry.exe\" /F");
      }
    }

}
