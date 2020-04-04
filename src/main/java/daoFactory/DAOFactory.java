package daoFactory;

import dao.dao;
import modele.Article;
import modele.Client;
import modele.Facture;
import modele.Famille;
import modele.Magasin;
import modele.ModePaiement;
import modele.Promotion;
import modele.RelationArticleFacture;
import modele.RelationArticleMagasin;
import dao.PromotionDAOMySQL;
import dao.RelationArticleFactureDAOMySQL;
import dao.RelationArticleMagasinDAOMySQL;
import dao.ClientDAOMySQL;
import dao.FactureDAOMySQL;
import dao.FamilleDAOMySQL;
import dao.MagasinDAOMySQL;
import dao.ModePaiementDAOMySQL;

public abstract class DAOFactory{
	
	public enum SourcesDonnees{
		mySQL,
		JSON
	}
	
	public abstract dao<Article> getArticleDAO();

	public abstract dao<Client> getClientDAO();
    
    public abstract dao<Facture> getFactureDAO();
    
       public abstract dao<Famille> getFamilleDAO();
       
        public abstract dao<Magasin> getMagasinDAO();
        
        public abstract dao<ModePaiement> getModePaiementDAO();
        
        
        public abstract dao<Promotion> getPromotionDAO();
        
        public abstract dao<RelationArticleFacture> getRelationArticleFactureDAO();
        
        public abstract dao<RelationArticleMagasin> getRelationArticleMagasinDAO();

	
	public static DAOFactory getFactory(SourcesDonnees sd) 
	{
			switch(sd) {
			case mySQL:
				return DAOMySQLFactory.getInstance();
			}
			return null;
		
	};
	
}
