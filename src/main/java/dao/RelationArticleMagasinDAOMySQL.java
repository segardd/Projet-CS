package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Article;
import modele.RelationArticleFacture;
import modele.RelationArticleMagasin;


public class RelationArticleMagasinDAOMySQL extends dao<RelationArticleMagasin>  implements Serializable{
    public static RelationArticleMagasinDAOMySQL instance;
    private dao<Article> articleManager= ArticleDAOMySQL.getInstance();

    private RelationArticleMagasinDAOMySQL() {
        
    }

    public static synchronized RelationArticleMagasinDAOMySQL getInstance() {

        if (instance == null) {
            instance = new RelationArticleMagasinDAOMySQL();
        }
        return instance;
    }

    @Override
    public RelationArticleMagasin find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM rt_art_mag WHERE idRT_Art_Mag=" + id);
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        RelationArticleMagasin artmag=null;
        try {
            if (result.next()) {
                artmag=new RelationArticleMagasin(
                        result.getInt("en_stock")
                        );
                artmag.setId_art_mag(result.getInt("idRT_Art_Mag"));
                artmag.setId_article(result.getInt("ID_article"));
                artmag.setId_magasin(result.getInt("ID_magasin"));
                
              //get Article de la relation
                artmag.setArticle(articleManager.find(artmag.getId_article()));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return artmag;
        
    }
    
    public RelationArticleFacture find(long id_art, long id_mag) {
        StringBuilder req= new StringBuilder("SELECT * FROM rt_art_fac WHERE ID_magasin= '"+ id_mag+
                "' AND ID_article= " + id_art);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        RelationArticleFacture artfac=null;
        try {
            if (result.next()) {
                artfac=new RelationArticleFacture(
                        result.getInt("quantite")
                        );
                artfac.setId_art_fac(result.getInt("idRT_Art_Mag"));
                artfac.setId_article(result.getInt("ID_Article"));
                artfac.setId_facture(result.getInt("ID_Facture"));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return artfac;
        
    }

    @Override
    public RelationArticleMagasin create(RelationArticleMagasin obj) {
        String req= 
                "INSERT INTO artmag (en_stock)"
                + " VALUES("+obj.getEn_stock()+")";
        
                    
        obj.setId_art_mag(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public RelationArticleMagasin update(RelationArticleMagasin obj) {
        String req="UPDATE artmag SET en_stock='"+obj.getEn_stock()+"'"
                         + " WHERE idRT_Art_Mag="+obj.getId_art_mag();
        MySQLManager.getInstance().setData(req);
        return obj;
    }

    @Override
    public void delete(RelationArticleMagasin obj) {
        String req="DELETE FROM artmag WHERE idRT_Art_Mag="+obj.getId_art_mag();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<RelationArticleMagasin> obj) {
        for (RelationArticleMagasin artmag : obj) {
            if (artmag.getId_art_mag()==0) {
                artmag = create(artmag);    
            }else {
                artmag = update(artmag);
            }
        }

    }

    @Override
    public LinkedList<RelationArticleMagasin> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From artmag";
        LinkedList<RelationArticleMagasin> artmags= new LinkedList<RelationArticleMagasin>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleMagasin artmag = null;
        try {
            while(result.next()) {
                artmag=new RelationArticleMagasin(
                        result.getInt("en_stock")
                        );
                artmag.setId_art_mag(result.getInt("idRT_Art_Mag"));
                artmag.setId_article(result.getInt("ID_article"));
                artmag.setId_magasin(result.getInt("ID_magasin"));
             // on verra aussi pour les liens
                artmags.add(artmag);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return artmags;
        
    }
    
    public RelationArticleMagasin findByArticleMagasin(long id_art, long id_mag) {
    	String req = "SELECT * From rt_art_mag WHERE ID_article= '"+ id_art + "' AND ID_magasin= '" + id_mag + "'";
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleMagasin artmag = null;
        try {
            while(result.next()) {
                artmag=new RelationArticleMagasin(result.getInt("en_stock"));
                artmag.setId_art_mag(result.getInt("idRT_Art_Mag"));
                artmag.setId_article(result.getInt("ID_article"));
                artmag.setId_magasin(result.getInt("ID_magasin"));
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return artmag;
    }
    
    public LinkedList<RelationArticleMagasin> findByArticle(long id){
        String req = "SELECT * From artmag WHERE ID_article= '"+id +"'";
        LinkedList<RelationArticleMagasin> artmags= new LinkedList<RelationArticleMagasin>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleMagasin artmag = null;
        try {
            while(result.next()) {
                artmag=new RelationArticleMagasin(result.getInt("en_stock"));
                artmag.setId_art_mag(result.getInt("idRT_Art_Mag"));
                artmag.setId_article(result.getInt("ID_article"));
                artmag.setId_magasin(result.getInt("ID_magasin"));
             // on verra aussi pour les liens
                artmags.add(artmag);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return artmags;
    }
    
    public LinkedList<RelationArticleMagasin> findByMagasin(long id){
        String req = "SELECT * From artmag WHERE ID_magasin= '"+id+"'";
        LinkedList<RelationArticleMagasin> artmags= new LinkedList<RelationArticleMagasin>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleMagasin artmag = null;
        try {
            while(result.next()) {
                artmag=new RelationArticleMagasin(result.getInt("en_stock"));
                artmag.setId_art_mag(result.getInt("idRT_Art_Mag"));
                artmag.setId_article(result.getInt("ID_article"));
                artmag.setId_magasin(result.getInt("ID_magasin"));
             // on verra aussi pour les liens
                artmags.add(artmag);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return artmags;
    }
}
