package serveur.magasin;
/*

import java.sql.*;
import java.util.*;
import modele.*;
import dao.*;
import facade.*;
import daoFactory.*;
import datasourceManagement.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;




import facade.CaisseFacade;

import java.sql.*;
import java.util.*;


public class ServerMagasin extends CaisseFacade {
   /* private static ServerMagasin instance;*/

    

   /*
   public static synchronized ServerMagasin getInstance() {
       
       if (instance == null) {
           instance = new ServerMagasin();
       }
       return instance;     
   }
   */
   
  /* public static void main(String[] args) {
    
       
       System.setProperty("java.rmi.server.hostname", "localhost");
       try {
         
         ServerMagasin obj = new ServerMagasin();
         PosteCaisseFonctionnalite stub = (PosteCaisseFonctionnalite) UnicastRemoteObject.exportObject(obj, 0);

         // Bind the remote object's stub in the registry
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("rmi://localhost/Hello", stub);

         System.err.println("Server ready");
     } catch (Exception e) {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
     }
}
}*/
