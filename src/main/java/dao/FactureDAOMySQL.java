package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Facture;
import modele.RelationArticleFacture;


public class FactureDAOMySQL extends dao<Facture> {
    public static FactureDAOMySQL instance;
    private static  RelationArticleFactureDAOMySQL relArtFacManager= RelationArticleFactureDAOMySQL.getInstance();

    private FactureDAOMySQL() {
        
    }

    public static synchronized FactureDAOMySQL getInstance() {

        if (instance == null) {
            instance = new FactureDAOMySQL();
        }
        return instance;
    }

    @Override
    public Facture find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM facture WHERE idFacture=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        Facture facture=null;
        try {
            if (result.next()) {
                facture=new Facture(
                        result.getDouble("totale_facture"),
                        result.getDate("date_facturation")
                        );
                facture.setIdFacture(result.getInt("idFacture"));
                facture.setId_magasin(result.getInt("ID_magasin"));
                facture.setId_mode_paiement(result.getInt("ID_mode_paiement"));
                
                facture.setArticles(RelationArticleFactureDAOMySQL.getInstance().findByFacture(id));
                
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return facture;
        
    }

    @Override
    public Facture create(Facture obj) {
        String req= 
                "INSERT INTO facture (ID_magasin, ID_client, ID_mode_paiement, totale_facture, date_facturation)"
                + " VALUES("+obj.getId_magasin()+","+obj.getIdClient()+","+obj.getTotale_facture()+","+obj.getDate_facture()+")";
        
                    
        obj.setIdFacture(MySQLManager.getInstance().setData(req));
        
        for(RelationArticleFacture rel: obj.getArticles() ) {
            rel.setId_facture(obj.getIdFacture());
        }
        relArtFacManager.saveall(obj.getArticles());
        return obj;
    }

    @Override
    public Facture update(Facture obj) {
        String req="UPDATE facture SET ID_magasin="+obj.getId_magasin()+","
                + "ID_client=" + obj.getIdClient()+","
                + "ID_mode_paiement="+obj.getId_mode_paiement()+","
                 + "totale_facture="+obj.getTotale_facture()+","
                 + "date_facturation="+obj.getDate_facture()+""
                         + "WHERE idFacture="+obj.getIdFacture();
        MySQLManager.getInstance().setData(req);
       
        for (RelationArticleFacture rel: obj.getArticles() ) {
            if (rel.getId_art_fac()==0) {
                rel.setId_art_fac(relArtFacManager.create(rel).getId_art_fac());
            }
            else {
                
                rel=relArtFacManager.update(rel);
            }
        }
        
        return obj;
    }

    @Override
    public void delete(Facture obj) {
        String req="DELETE FROM facture WHERE idFacture="+obj.getIdFacture();
        MySQLManager.getInstance().setData(req);
        
        for(RelationArticleFacture rel : obj.getArticles()) {
            relArtFacManager.delete(rel);                       //DELETE CASCADE
        }

    }

    @Override
    public void saveall(LinkedList<Facture> obj) {
        for (Facture facture : obj) {
            if (facture.getIdFacture()==0) {
                facture = create(facture);    
            }else {
                facture = update(facture);
            }
        }

    }

    @Override
    public LinkedList<Facture> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From facture";
        LinkedList<Facture> factures= new LinkedList<Facture>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Facture facture = null;
        try {
            while(result.next()) {
                facture=new Facture(
                        result.getDouble("totale_facture"),
                        result.getDate("date_facturation")
                        );
                facture.setIdFacture(result.getInt("idFacture"));
                facture.setId_magasin(result.getInt("ID_magasin"));
                facture.setId_mode_paiement(result.getInt("ID_mode_paiement"));
             // on verra aussi pour les liens
                facture.setArticles(RelationArticleFactureDAOMySQL.getInstance().findByFacture(facture.getIdFacture()));
                
                factures.add(facture);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas facture");
        }
        return factures;
        
    }
    
    public LinkedList<Facture> findByMagasin(long id) {
        // TODO Auto-generated method stub
        String req = "SELECT * From facture WHERE ID_magasin= "+id;
        LinkedList<Facture> factures= new LinkedList<Facture>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Facture facture = null;
        try {
            while(result.next()) {
                facture=new Facture(
                        result.getDouble("totale_facture"),
                        result.getDate("date_facturation")
                        );
                facture.setIdFacture(result.getInt("idFacture"));
                facture.setId_magasin(result.getInt("ID_magasin"));
                facture.setId_mode_paiement(result.getInt("ID_mode_paiement"));
             // on verra aussi pour les liens
                facture.setArticles(RelationArticleFactureDAOMySQL.getInstance().findByFacture(facture.getIdFacture()));
                
                factures.add(facture);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas facture");
        }
        return factures;
        
    }
    
    
    

}
