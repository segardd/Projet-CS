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
import modele.ModePaiement;


public class ModePaiementDAOMySQL extends dao<ModePaiement>  implements Serializable{
    public static ModePaiementDAOMySQL instance;

    private ModePaiementDAOMySQL() {
        
    }

    public static synchronized ModePaiementDAOMySQL getInstance() {

        if (instance == null) {
            instance = new ModePaiementDAOMySQL();
        }
        return instance;
    }

    @Override
    public ModePaiement find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM mode_paiement WHERE idMode_paiement=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        ModePaiement modePaiement=null;
        try {
            if (result.next()) {
                modePaiement=new ModePaiement(
                        result.getString("intitule_paiement")
                        );
                modePaiement.setIdMode_paiement(result.getInt("idMode_paiement"));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return modePaiement;
        
    }

    @Override
    public ModePaiement create(ModePaiement obj) {
        String req= 
                "INSERT INTO modePaiement (intitule_paiement)"
                + " VALUES('"+obj.getIntitule_paiement()+"')";
        
                    
        obj.setIdMode_paiement(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public ModePaiement update(ModePaiement obj) {
        String req="UPDATE mode_paiement SET nom='"+obj.getIntitule_paiement()+"'"
                + " WHERE idMode_paiement="+obj.getIdMode_paiement();
        MySQLManager.getInstance().setData(req);
        return obj;
    }

    @Override
    public void delete(ModePaiement obj) {
        String req="DELETE FROM mode_paiement WHERE idModePaiement="+obj.getIdMode_paiement();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<ModePaiement> obj) {
        for (ModePaiement modePaiement : obj) {
            if (modePaiement.getIdMode_paiement()==0) {
                modePaiement = create(modePaiement);    
            }else {
                modePaiement = update(modePaiement);
            }
        }

    }

    @Override
    public LinkedList<ModePaiement> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From mode_paiement";
        LinkedList<ModePaiement> modePaiements= new LinkedList<ModePaiement>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        ModePaiement modePaiement = null;
        try {
            while(result.next()) {
                modePaiement=new ModePaiement(
                        result.getString("intitule_paiement")
                        );
                modePaiement.setIdMode_paiement(result.getInt("idMode_paiement"));
             // on verra aussi pour les liens
                modePaiements.add(modePaiement);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return modePaiements;
        
    }

	public ModePaiement findModePaiementByRef(String ref) {
		String req = "SELECT * From mode_paiement WHERE intitule_paiement = '" + ref + "'";
    	ResultSet result = MySQLManager.getInstance().getData(req);
    	ModePaiement mode = null;
        try {
            while(result.next()) {
            	mode.setIdMode_paiement(result.getInt("mode_paiement"));
            	mode.setIntitule_paiement(result.getString("intitule_paiement"));
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return mode;
	}

}
