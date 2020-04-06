package facade;

import java.rmi.RemoteException;
import java.util.LinkedList;

import dao.ArticleDAOMySQL;
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
    
    /**
     * 
     * @return la liste d'Article du magasin courant
     */
    public LinkedList<Article> stock(){
    	/*LinkedList<Article> LesArticles = new LinkedList<Article>();
		
		LesArticles = ArticleDAOMySQL.getInstance().findall();*/
		return ArticleDAOMySQL.getInstance().findall();
    }
    
    /**
     * 
     * @param id_article l'id de l'article
     * @param id_magasin l'id du magasin
     * @return l'article du magasin
     */
    public RelationArticleMagasin StockArticleDansMagasin(int id_article, int id_magasin) {
    	RelationArticleMagasinDAOMySQL.getInstance().find(id_article, id_magasin);
    	//return 
    }
    
    
    /**
     * 
     * @param ref ref de la famille
     * @return liste d'articles de la famille
     */
    public LinkedList<Article> articleDeLaFamilleParRef(String ref){
        
    }
    
    /**
     * 
     * @param famille
     * @return la liste d'article
     */
    public LinkedList<Article> articleDeLaFamille(Famille famille){
        
    }
    
    /**
     * 
     * @param ref de l'article
     * @return l'intitulé de la famille de l'article
     */
    public String intituleDeLaFamille(String ref){
        return "Famille";
    }
}