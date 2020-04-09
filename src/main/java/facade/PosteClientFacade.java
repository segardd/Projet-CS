package facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

import dao.ArticleDAOMySQL;
import dao.FamilleDAOMySQL;
import dao.RelationArticleMagasinDAOMySQL;
import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import daoFactory.DAOMySQLFactory;

import modele.Article;
import modele.Famille;
import modele.Magasin;
import modele.RelationArticleFacture;
import modele.RelationArticleMagasin;
import serveur.magasin.PosteCaisseFonctionnalite;
import serveur.magasin.PosteClientFonctionnalite;

public class PosteClientFacade implements PosteClientFonctionnalite {
	
	DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
    dao<Article> articleManager = factory.getArticleDAO();
    dao<Famille> familleManager = factory.getFamilleDAO();
    dao<Magasin> magasinManager = factory.getMagasinDAO();
    dao<RelationArticleMagasin> relationArticleMagasinManager = factory.getRelationArticleMagasinDAO();
    
    private static PosteClientFacade instance;
    
    private PosteClientFacade() {
    	
    }
    
    public static synchronized PosteClientFacade getInstance() {
        
        if (instance == null) {
            instance = new PosteClientFacade();
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
    
    /**
     * 
     * @param id_article l'id de l'article
     * @param id_magasin l'id du magasin
     * @return l'article du magasin
     */
    public RelationArticleMagasin StockArticleDansMagasin(int id_article, int id_magasin) {
    	return (RelationArticleMagasinDAOMySQL.getInstance().findByArticleMagasin(id_article, id_magasin));
    }
    
    
    /**
     * 
     * @param ref ref de la famille
     * @return liste d'articles de la famille
     */
    public LinkedList<Article> articleDeLaFamilleParRef(String ref) {
        return ArticleDAOMySQL.getInstance().findByRef(ref);
    }
    
    /**
     * 
     * @param famille
     * @return la liste d'article
     */
    public LinkedList<Article> articleDeLaFamille(Famille famille) {
    	return ArticleDAOMySQL.getInstance().findByFamile(famille);
    }
    
    /**
     * 
     * @param ref de l'article
     * @return l'intitulé de la famille de l'article
     */
    public String intituleDeLaFamille(String ref) {
        return FamilleDAOMySQL.getInstance().findIntituleByRef(ref);
    }
    
    /**
     * 
     * @param ref de l'article
     * @return l'intitulé de la famille de l'article
     */
    public Article findArticleByRef(String ref) {
        return ArticleDAOMySQL.getInstance().findArticleByRef(ref);
    }
    
    public static void main(String[] args) {
        
        
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
          
            PosteClientFacade obj = PosteClientFacade.getInstance();
          PosteClientFonctionnalite stub = (PosteClientFonctionnalite) UnicastRemoteObject.exportObject(obj, 0);

          // Bind the remote object's stub in the registry
          Registry registry = LocateRegistry.getRegistry();
          // il faudrait indiquer le  n° du magasin dans le lien
          // ou faire en sorte qu'on puisse instancier un registry pour chacun des
          // magasins
          registry.bind("rmi://localhost/Client", stub);

          System.err.println("Server ready");
      } catch (Exception e) {
          System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
      }
    }
}