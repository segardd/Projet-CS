package facade;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;

import dao.ClientDAOMySQL;
import dao.FactureDAOMySQL;
import modele.Article;
import modele.Client;
import modele.Facture;
import serveur.magasin.SiegeFonctionnalite;

public class SiegeFacade implements SiegeFonctionnalite {
    
    public ClientDAOMySQL clientManager= ClientDAOMySQL.getInstance();
    public FactureDAOMySQL factureManager= FactureDAOMySQL.getInstance();
    
	private static SiegeFacade instance;
	
	private static SiegeFonctionnalite facadeSiege;

    
    private SiegeFacade() {
       
    }
   
    public static synchronized SiegeFacade getInstance() {
       
       if (instance == null) {
           instance = new SiegeFacade();
           /*try {
               Registry registry = LocateRegistry.getRegistry();
               facadeSiege = (SiegeFonctionnalite) registry.lookup("rmi://localhost/Siege");

           } catch (Exception e) {
               System.out.println("non connecté au server");
               e.printStackTrace();
           }*/
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
    
    public LinkedList<Article> stock() throws RemoteException {
    	
    	return null;
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
 
	@Override
	public double calculCA(Date date) {
	    // TODO Auto-generated method stub
	    if (date==null) {
	        date=new Date(System.currentTimeMillis());
	    }
	    double somme=0;
	    LinkedList<Facture> factures= factureManager.findall();
	    for (Facture facture: factures) {
	        // on regarde si la facture a un mode de paiement <=> facture payée
	        if (facture.getId_mode_paiement()!=0 && facture.getDate_facture().compareTo(date)>= 0) {
	            somme+=facture.getTotale_facture();
	        }
	    }
	    return somme;
	}
}

