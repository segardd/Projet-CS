package facade;

import java.util.LinkedList;

import dao.dao;
import daoFactory.DAOFactory;
import daoFactory.DAOFactory.SourcesDonnees;
import modele.Article;
import modele.Client;
import modele.Facture;
import modele.Magasin;
import modele.ModePaiement;
//import modele.RelationArticleFacture;

public class CaisseFacade {
    
    private DAOFactory factory=DAOFactory.getFactory(SourcesDonnees.mySQL);
    private dao<Article> articleManager=factory.getArticleDAO();
    private dao<Client> clienManager= factory.getClientDAO();
    private dao<Facture> factureManager= factory.getFactureDAO();
    private dao<Magasin> magasinManager= factory.getMagasinDAO();
    //private dao<RelationArticleFacture> relationManager= factory.getRelationArticleFactureDAO();
    
    
    
    public String editerFacture() {
        //TODO 
        return "";
    }
    
    /**
     * 
     * @param facture , facture à payer
     * @param mode , mode de paiement choisis
     * @return la facture payée
     */
    /*public Facture PayerFacture(Facture facture, ModePaiement mode ) {
        facture.setId_mode_paiement(mode.getIdMode_paiement());
        facture=factureManager.update(facture);
        
        
    }*/
    
    /**
     * 
     * @param id , id du magasin dont on veut les factures
     * @return liste des factures de cce magasin
     */
    public LinkedList<Facture> ListeFacture(long id){
       return magasinManager.find(id).getFactures(); 
    }
    
    

}
