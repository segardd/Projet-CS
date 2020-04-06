package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Famille;


public class FamilleDAOMySQL extends dao<Famille>  implements Serializable{
    public static FamilleDAOMySQL instance;

    private FamilleDAOMySQL() {
        
    }

    public static synchronized FamilleDAOMySQL getInstance() {

        if (instance == null) {
            instance = new FamilleDAOMySQL();
        }
        return instance;
    }

    @Override
    public Famille find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM famille WHERE id_famille=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        Famille famille=null;
        try {
            if (result.next()) {
                famille=new Famille(
                        result.getString("intitule")

                        );
                famille.setIdFamille(result.getInt("idFamille"));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return famille;
        
    }

    @Override
    public Famille create(Famille obj) {
        String req= 
                "INSERT INTO famille (intitule)"
                + " VALUES('"+obj.getIntitule()+"')";
        
                    
        obj.setIdFamille(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public Famille update(Famille obj) {
        String req="UPDATE famille SET intitule='"+obj.getIntitule()+"'"
                + " WHERE idFamille="+obj.getIdFamille();
        MySQLManager.getInstance().setData(req);
        return obj;
    }

    @Override
    public void delete(Famille obj) {
        String req="DELETE FROM famille WHERE idFamille="+obj.getIdFamille();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<Famille> obj) {
        for (Famille famille : obj) {
            if (famille.getIdFamille()==0) {
                famille = create(famille);    
            }else {
                famille = update(famille);
            }
        }

    }

    @Override
    public LinkedList<Famille> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From famille";
        LinkedList<Famille> familles= new LinkedList<Famille>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Famille famille = null;
        try {
            while(result.next()) {
                famille=new Famille(
                        result.getString("intitule")
                        );
                famille.setIdFamille(result.getInt("idFamille"));
             // on verra aussi pour les liens
                familles.add(famille);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return familles;
        
    }

}
