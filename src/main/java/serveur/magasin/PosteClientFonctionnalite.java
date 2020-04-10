package serveur.magasin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

import dao.ArticleDAOMySQL;
import dao.RelationArticleMagasinDAOMySQL;
import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.Famille;
import modele.Magasin;
import modele.RelationArticleMagasin;

public interface PosteClientFonctionnalite extends Remote{
	static DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
	static dao<Article> articleManager = factory.getArticleDAO();
	static dao<Famille> familleManager = factory.getFamilleDAO();
	static dao<Magasin> magasinManager = factory.getMagasinDAO();
	static dao<RelationArticleMagasin> relationArticleMagasinManager = factory.getRelationArticleMagasinDAO();
	
	/**
     * 
     * @return la liste des articles en stocks.
     */
	public LinkedList<Article> stock() throws RemoteException;
	
	/**
     * 
     * @param id_article , id de l'article
     * @param id_magasin , id du magasin
     * @return l'article du magasin
     */
	public RelationArticleMagasin StockArticleDansMagasin(int id_article, int id_magasin) throws RemoteException;
	
	/**
     * 
     * @param ref , référence de l'article
     * @return les articles de la même famille.
     */
	public LinkedList<Article> articleDeLaFamilleParRef(String ref) throws RemoteException;
	
	/**
     * 
     * @param famille , objet famille
     * @return les articles cette famille.
     */
	public LinkedList<Article> articleDeLaFamille(Famille famille) throws RemoteException;
	
	/**
     * 
     * @param ref , référence de l'article
     * @return l'intitulé de la famille de l'article.
     */
	public String intituleDeLaFamille(String ref) throws RemoteException;
	
	/**
     * 
     * @param ref de l'article
     * @return l'article
     */
    public Article findArticleByRef(String ref) throws RemoteException;
    
    /**
     * 
     * @param artMag nouvelle valeur de l'article dans le magasin
     * @param article nouvelle valeur de l'article
     */
    public void remettreEnStock(RelationArticleMagasin artMag, Article article) throws RemoteException;
    
    /**
     * 
     * @param id_magasin identifiant du magasin
     * @return la liste d'Article du magasin courant
     */
    public LinkedList<RelationArticleMagasin> findByMagasin(int id_magasin) throws RemoteException;

}
