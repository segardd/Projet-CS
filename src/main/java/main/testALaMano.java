package main;

import java.lang.reflect.InvocationTargetException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

import dao.ClientDAOMySQL;
import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.RelationArticleFacture;
import serveur.magasin.PosteCaisseFonctionnalite;
import dao.ArticleDAOMySQL;
import facade.CaisseFacade;

public class testALaMano {
    
    public static dao<Article> articleManager=DAOFactory.getFactory(SourcesDonnees.mySQL).getArticleDAO();
    
    public static void main(String[] args) {
      /*  Article a1 = new Article("short femme", 15, 3);
        Article a2 = new Article("t-shirt uni",12,2);
        
        DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
        a1= factory.getArticleDAO().find(7);
       
        
        a1.setId_famille(2);
        a1=factory.getArticleDAO().update(a1);
        
        System.out.println(a1.toString());
        
        System.out.println(factory.getClientDAO().findall().toString());*/
        
        
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            PosteCaisseFonctionnalite stub = (PosteCaisseFonctionnalite) registry.lookup("rmi://localhost/Caisse");
            //System.out.println("response: " + response);
       
        
        LinkedList<Article> articles=new LinkedList<Article>();
        articles.add(articleManager.find(1));
        articles.add(articleManager.find(2));
        
        LinkedList<RelationArticleFacture> rel=new LinkedList<RelationArticleFacture>();
        for (Article art: articles) {
            RelationArticleFacture relation=new RelationArticleFacture(2);
            relation.setLarticle(art);
            rel.add(relation);
            
        }
        
        System.out.println(stub.editerFacture(rel));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
        
        
        
    }
     

   
    
  
}
