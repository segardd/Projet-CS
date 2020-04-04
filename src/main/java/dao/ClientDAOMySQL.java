package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Client;


public class ClientDAOMySQL extends dao<Client> {
    public static ClientDAOMySQL instance;

    private ClientDAOMySQL() {
        
    }

    public static synchronized ClientDAOMySQL getInstance() {

        if (instance == null) {
            instance = new ClientDAOMySQL();
        }
        return instance;
    }

    @Override
    public Client find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM client WHERE idClient=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        Client client=null;
        try {
            if (result.next()) {
                client=new Client(
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse_mail"),
                        result.getString("code_postal"),
                        result.getString("ville")
                        );
                client.setIdClient(result.getInt("idClient"));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return client;
        
    }

    @Override
    public Client create(Client obj) {
        String req= 
                "INSERT INTO client (nom,prenom,adresse_mail,code_postal,ville)"
                + " VALUES("+obj.getNom()+","+obj.getPrenom()+","+obj.getAdresse_mail()+","+obj.getCode_postal()+")";
        
                    
        obj.setIdClient(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public Client update(Client obj) {
        String req="UPDATE client SET nom='"+obj.getNom()+"',"
                + "prenom='" + obj.getPrenom()+"',"
                + "code_postal='"+obj.getCode_postal()+"',"
                 + "ville='"+obj.getVille()+"'"
                         + " WHERE idClient="+obj.getIdClient();
        MySQLManager.getInstance().setData(req);
        return obj;
    }

    @Override
    public void delete(Client obj) {
        String req="DELETE FROM client WHERE idClient="+obj.getIdClient();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<Client> obj) {
        for (Client client : obj) {
            if (client.getIdClient()==0) {
                client = create(client);    
            }else {
                client = update(client);
            }
        }

    }

    @Override
    public LinkedList<Client> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From client";
        LinkedList<Client> clients= new LinkedList<Client>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Client client = null;
        try {
            while(result.next()) {
                client=new Client(
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("adresse_mail"),
                        result.getString("code_postal"),
                        result.getString("ville")
                        );
                client.setIdClient(result.getInt("idClient"));
             // on verra aussi pour les liens
                clients.add(client);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return clients;
        
    }

}
