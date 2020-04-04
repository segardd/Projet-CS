package daoFactory;

import dao.dao;
import dao.ArticleDAOMySQL;
import dao.ClientDAOMySQL;
import dao.FactureDAOMySQL;
import dao.FamilleDAOMySQL;
import dao.MagasinDAOMySQL;
import dao.ModePaiementDAOMySQL;
import dao.PromotionDAOMySQL;
import dao.RelationArticleFactureDAOMySQL;
import dao.RelationArticleMagasinDAOMySQL;

public class DAOMySQLFactory extends DAOFactory{
	private static DAOMySQLFactory instance;
	
	private DAOMySQLFactory() {	
	}
	
	public static synchronized DAOFactory getInstance() {
		
		if (instance == null) {
			instance = new DAOMySQLFactory();
		}
		return instance;		
	}
	
	public dao getArticleDAO() {
        return ArticleDAOMySQL.getInstance();
    };

	public dao getClientDAO() {
		return ClientDAOMySQL.getInstance();
	};
	
	public dao getFactureDAO() {
		return FactureDAOMySQL.getInstance();
	}
	
	   public dao getFamilleDAO() {
	        return FamilleDAOMySQL.getInstance();
	    }
	   
	    public dao getMagasinDAO() {
	        return MagasinDAOMySQL.getInstance();
	    }
	    
	    public dao getModePaiementDAO() {
	        return ModePaiementDAOMySQL.getInstance();
	    }
	    
	    
	    public dao getPromotionDAO() {
	        return PromotionDAOMySQL.getInstance();
	    }
	    
	    public dao getRelationArticleFactureDAO() {
	        return RelationArticleFactureDAOMySQL.getInstance();
	    }
	    
	    public dao getRelationArticleMagasinDAO() {
	        return RelationArticleMagasinDAOMySQL.getInstance();
	    }
}

