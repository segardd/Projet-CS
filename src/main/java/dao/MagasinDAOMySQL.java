package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Facture;
import modele.Magasin;
import modele.RelationArticleFacture;


public class MagasinDAOMySQL extends dao<Magasin>  implements Serializable{
    public static MagasinDAOMySQL instance;
    private static FactureDAOMySQL factureManager= FactureDAOMySQL.getInstance();

    private MagasinDAOMySQL() {
        
    }

    public static synchronized MagasinDAOMySQL getInstance() {

        if (instance == null) {
            instance = new MagasinDAOMySQL();
        }
        return instance;
    }

    @Override
    public Magasin find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM magasin WHERE id_magasin=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        Magasin magasin=null;
        try {
            if (result.next()) {
                magasin=new Magasin(
                        result.getString("ville"),
                        result.getString("departement"),
                        result.getString("adresse")
                          );
                  magasin.setIdMagasin(result.getInt("id"));
                  
                  magasin.setFactures(factureManager.findByMagasin(magasin.getIdMagasin()));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return magasin;
        
    }

    @Override
    public Magasin create(Magasin obj) {
        String req= 
                "INSERT INTO magasin (ville, departement, adresse)"
                + " VALUES('"+obj.getVille()+"','"+obj.getDepartement()+"','"+obj.getAdresse()+"')";
        
                    
        obj.setIdMagasin(MySQLManager.getInstance().setData(req));
        
        for(Facture rel: obj.getFactures() ) {
            rel.setId_magasin(obj.getIdMagasin());
        }
        factureManager.saveall(obj.getFactures());
        return obj;
    }

    @Override
    public Magasin update(Magasin obj) {
        LinkedList<Facture> updateFacture= new LinkedList<Facture>();
        String req="UPDATE magasin SET ville='"+obj.getVille()+"',"
                + "departement='" + obj.getDepartement()+"',"
                + "adresse='"+obj.getAdresse()+"'"
                        + " WHERE idMagasin="+obj.getIdMagasin();
        MySQLManager.getInstance().setData(req);
        
        //lien, factures du magasin
        for (Facture rel: obj.getFactures() ) {
            if (rel.getIdFacture()==0) {
                rel.setIdFacture(factureManager.create(rel).getIdFacture());
                
            }
            else {
                
                rel=factureManager.update(rel);
            }
            updateFacture.add(rel);
        }
        obj.setFactures(updateFacture);
        return obj;
    }

    @Override
    public void delete(Magasin obj) {
        String req="DELETE FROM magasin WHERE idMagasin="+obj.getIdMagasin();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<Magasin> obj) {
        for (Magasin magasin : obj) {
            if (magasin.getIdMagasin()==0) {
                magasin = create(magasin);    
            }else {
                magasin = update(magasin);
            }
        }

    }

    @Override
    public LinkedList<Magasin> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From magasin";
        LinkedList<Magasin> magasins= new LinkedList<Magasin>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Magasin magasin = null;
        try {
            while(result.next()) {
                magasin=new Magasin(
                      result.getString("ville"),
                      result.getString("departement"),
                      result.getString("adresse")
                        );
                magasin.setIdMagasin(result.getInt("id"));
                
             // lien
                magasin.setFactures(factureManager.findByMagasin(magasin.getIdMagasin()));
                
                magasins.add(magasin);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return magasins;
        
    }

}
