package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Magasin;


public class MagasinDAOMySQL extends dao<Magasin> {
    public static MagasinDAOMySQL instance;

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
        return obj;
    }

    @Override
    public Magasin update(Magasin obj) {
        String req="UPDATE magasin SET ville='"+obj.getVille()+"',"
                + "departement='" + obj.getDepartement()+"',"
                + "adresse='"+obj.getAdresse()+"'"
                        + " WHERE idMagasin="+obj.getIdMagasin();
        MySQLManager.getInstance().setData(req);
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
             // on verra aussi pour les liens
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
