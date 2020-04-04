package main;

import java.lang.reflect.InvocationTargetException;

import dao.ClientDAOMySQL;
import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import dao.ArticleDAOMySQL;

public class testALaMano {
    public static void main(String[] args) {
        Article a1 = new Article("short femme", 15, 3);
        Article a2 = new Article("t-shirt uni",12,2);
        
        DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
        a1= factory.getArticleDAO().find(7);
       
        
        a1.setId_famille(2);
        a1=factory.getArticleDAO().update(a1);
        
        System.out.println(a1.toString());
        
        System.out.println(factory.getClientDAO().findall().toString());
        
    }
     

   
    
  
}
