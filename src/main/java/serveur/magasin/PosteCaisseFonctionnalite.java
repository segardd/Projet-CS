package serveur.magasin;

import java.rmi.Remote;
import java.rmi.RemoteException;

import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import facade.CaisseFacade;
import java.util.*;
import java.sql.*;


import modele.Article;
import modele.Client;
import modele.Facture;
import modele.Magasin;
import modele.ModePaiement;
import modele.RelationArticleFacture;

public interface PosteCaisseFonctionnalite extends Remote{
    static DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
    static dao<Article> articleManager=factory.getArticleDAO();
    static dao<Client> clienManager= factory.getClientDAO();
    static dao<Facture> factureManager= factory.getFactureDAO();
    static dao<Magasin> magasinManager= factory.getMagasinDAO();
    

    /**
     * 
     * @return la liste des articles en stocks.
     */
	public LinkedList<Article> stock() throws RemoteException;
	
	/**
    * 
    * @return la liste des moyens de paiements.
    */
	public LinkedList<ModePaiement> moyensPaiement() throws RemoteException;
	
	/**
     * 
     * @param ref , intitule du mode de paiement
     * @return le mode de paiement
     */
	public ModePaiement findModePaiementByRef(String ref) throws RemoteException;
	
	/**
     * 
     * @param idFacture , l'identifiant de la facture
     * @return articles de la facture sous forme de liste
     */
	public LinkedList<RelationArticleFacture> consulterFacture(int idFacture) throws RemoteException;
	
	/**
     * 
     * @param idFacture , l'identifiant de la facture
     * @return facture la facture correspondante
     */
	public Facture findFacture(int idFacture) throws RemoteException;
	
	/**
	 * 
     * @param ref , la reference de l'article
     * @return le prix unitaire de l'article.
     */
	public Double prixArticle(String ref) throws RemoteException;
    
	/**
     * 
     * @param articles , liste des articles de la facture
     * @return facture sous forme de chaine de caractère
     */
    public String editerFacture(LinkedList<RelationArticleFacture> articles) throws RemoteException;
    
    /**
     * 
     * @param facture , facture à payer
     * @param panier , liste des articles dans le panier
     * @return la facture payée
     */
    public Facture PayerFacture(Facture facture, List<RelationArticleFacture> panier ) throws RemoteException;
    
    /**
     * 
     * @param id , id du magasin dont on veut les factures
     * @return liste des factures de cce magasin
     */
    public LinkedList<Facture> ListeFacture(long id) throws RemoteException;
    
    /**
     * 
     * @param ref de l'article
     * @return l'article
     */
    public Article findArticleByRef(String ref) throws RemoteException;
    

    public Client createClient(String nom, String prenom,String mail,String codePostal, String ville ) throws RemoteException;

    /**
     * 
     * @param id de l'article
     * @return l'article
     */
    public Article findArticleByID(int id) throws RemoteException;

}
