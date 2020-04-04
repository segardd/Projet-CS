package daoFactory;

import dao.dao;
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
	

	public abstract dao getClientDAO();
    
    public abstract dao getFactureDAO();
    
       public abstract dao getFamilleDAO();
       
        public abstract dao getMagasinDAO();
        
        public abstract dao getModePaiementDAO();
        
        
        public abstract dao getPromotionDAO();
        
        public abstract dao getRelationArticleFactureDAO();
        
        public abstract dao getRelationArticleMagasinDAO();

	
	public static DAOFactory getFactory(SourcesDonnees sd) 
	{
			switch(sd) {
			case mySQL:
				return DAOMySQLFactory.getInstance();
			}
			return null;
		
	};
	
}
