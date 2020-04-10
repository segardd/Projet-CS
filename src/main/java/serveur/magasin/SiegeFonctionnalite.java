package serveur.magasin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;

import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.Client;

public interface SiegeFonctionnalite extends Remote{
	static DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
	static dao<Article> articleManager = factory.getArticleDAO();

    public Client createClient(Client client) throws RemoteException;
    
    /**
     * 
     * @return la liste des articles en stocks.
     */
	public LinkedList<Article> stock() throws RemoteException;
	

    double calculCA(Date date) throws RemoteException;

}
