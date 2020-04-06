package serveur.magasin;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.LinkedList;

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
    

    
    public String editerFacture(LinkedList<RelationArticleFacture> articles) throws RemoteException;
    
    /**
     * 
     * @param facture , facture à payer
     * @param mode , mode de paiement choisis
     * @return la facture payée
     */
    public Facture PayerFacture(Facture facture, ModePaiement mode ) throws RemoteException;
    
    /**
     * 
     * @param id , id du magasin dont on veut les factures
     * @return liste des factures de cce magasin
     */
    public LinkedList<Facture> ListeFacture(long id) throws RemoteException;
}
