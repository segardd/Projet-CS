package facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import dao.ClientDAOMySQL;
import modele.Article;
import modele.Client;

import serveur.magasin.SiegeFonctionnalite;

public class SiegeFacade implements SiegeFonctionnalite {
    
    public ClientDAOMySQL clientManager= ClientDAOMySQL.getInstance();
    
private static SiegeFacade instance;

    
    private SiegeFacade() {
       
    }
   
    public static synchronized SiegeFacade getInstance() {
       
       if (instance == null) {
           instance = new SiegeFacade();
       }
       return instance;     
    }

    @Override
    public Client createClient(Client client)
            throws RemoteException {
        // TODO Auto-generated method stub
        client=clientManager.create(client);
        
        return client;
    }

    
 public static void main(String[] args) {
        
        
        System.setProperty("java.rmi.server.hostname", "localhost");
        try {
          
            SiegeFacade obj = SiegeFacade.getInstance();
          SiegeFonctionnalite stub = (SiegeFonctionnalite) UnicastRemoteObject.exportObject(obj, 0);

          // Bind the remote object's stub in the registry
          Registry registry = LocateRegistry.getRegistry();
          registry.bind("rmi://localhost/Siege", stub);

          System.err.println("Server Siege ready");
      } catch (Exception e) {
          System.err.println("Server exception: " + e.toString());
          e.printStackTrace();
      }
    }
}

