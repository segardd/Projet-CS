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


public class RelationArticleFactureDAOMySQL extends dao<RelationArticleFacture>  implements Serializable{
    public static RelationArticleFactureDAOMySQL instance;
    private dao<Article> articleManager= ArticleDAOMySQL.getInstance();
    

    private RelationArticleFactureDAOMySQL() {
        
    }

    public static synchronized RelationArticleFactureDAOMySQL getInstance() {

        if (instance == null) {
            instance = new RelationArticleFactureDAOMySQL();
        }
        return instance;
    }

    @Override
    public RelationArticleFacture find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM rt_art_fac WHERE idRT_Art_Fac=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        RelationArticleFacture artfac=null;
        try {
            if (result.next()) {
                artfac=new RelationArticleFacture(
                        result.getInt("quantite")
                        );
                artfac.setId_art_fac(result.getInt("idRT_Art_Fac"));
                artfac.setId_article(result.getInt("ID_Article"));
                artfac.setId_facture(result.getInt("ID_Facture"));
                
                //get Larticle de la relation
                artfac.setLarticle(articleManager.find(artfac.getId_article()));
                
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return artfac;
        
    }
    
    public RelationArticleFacture find(long id_art, long id_fac) {
        StringBuilder req= new StringBuilder("SELECT * FROM rt_art_fac WHERE ID_Facture= "+ id_fac+
                " AND ID_Article= " + id_art);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        RelationArticleFacture artfac=null;
        try {
            if (result.next()) {
                artfac=new RelationArticleFacture(
                        result.getInt("quantite")
                        );
                artfac.setId_art_fac(result.getInt("idRT_Art_Fac"));
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
    public RelationArticleFacture create(RelationArticleFacture obj) {
        String req= 
                "INSERT INTO rt_art_fac (ID_Article,ID_Facture,quantite)"
                + " VALUES("+obj.getId_article()+","+obj.getId_facture()+","+obj.getQuantite()+")";
        
                    
        obj.setId_art_fac(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public RelationArticleFacture update(RelationArticleFacture obj) {
        String req="UPDATE rt_art_fac SET quantite='"+obj.getQuantite()+"',"
                + "ID_Article= "+obj.getId_article()+","
                +"ID_Facture= "+obj.getId_facture()
                         + " WHERE idRT_Art_Fac="+obj.getId_art_fac();
        MySQLManager.getInstance().setData(req);
        return obj;
    }

    @Override
    public void delete(RelationArticleFacture obj) {
        String req="DELETE FROM rt_art_fac WHERE idRT_Art_Fac="+obj.getId_art_fac();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<RelationArticleFacture> obj) {
        for (RelationArticleFacture artfac : obj) {
            if (artfac.getId_art_fac()==0) {
                artfac = create(artfac);    
            }else {
                artfac = update(artfac);
            }
        }

    }

    @Override
    public LinkedList<RelationArticleFacture> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From rt_art_fac";
        LinkedList<RelationArticleFacture> artfacs= new LinkedList<RelationArticleFacture>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleFacture artfac = null;
        try {
            while(result.next()) {
                artfac=new RelationArticleFacture(
                        result.getInt("quantite")
                        );
                artfac.setId_art_fac(result.getInt("idRT_Art_Fac"));
                artfac.setId_article(result.getInt("ID_Article"));
                artfac.setId_facture(result.getInt("ID_Facture"));
             // on verra aussi pour les liens
                artfacs.add(artfac);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return artfacs;
        
    }
    
    public LinkedList<RelationArticleFacture> findByArticle(long id){
        String req = "SELECT * From rt_art_fac WHERE ID_Article= "+id;
        LinkedList<RelationArticleFacture> artfacs= new LinkedList<RelationArticleFacture>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleFacture artfac = null;
        try {
            while(result.next()) {
                artfac=new RelationArticleFacture(
                        result.getInt("quantite")
                        );
                artfac.setId_art_fac(result.getInt("idRT_Art_Fac"));
                artfac.setId_article(result.getInt("ID_Article"));
                artfac.setId_facture(result.getInt("ID_Facture"));
             // on verra aussi pour les liens
                artfacs.add(artfac);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return artfacs;
    }
    
    public LinkedList<RelationArticleFacture> findByFacture(long id){
        String req = "SELECT * From rt_art_fac WHERE ID_Facture= "+id;
        LinkedList<RelationArticleFacture> artfacs= new LinkedList<RelationArticleFacture>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        RelationArticleFacture artfac = null;
        try {
            while(result.next()) {
                artfac=new RelationArticleFacture(
                        result.getInt("quantite")
                        );
                artfac.setId_art_fac(result.getInt("idRT_Art_Fac"));
                artfac.setId_article(result.getInt("ID_Article"));
                artfac.setId_facture(result.getInt("ID_Facture"));
             // on verra aussi pour les liens
                artfacs.add(artfac);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas factures");
        }
        return artfacs;
    }

}
