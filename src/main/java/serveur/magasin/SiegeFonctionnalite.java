package serveur.magasin;

import java.rmi.Remote;
import java.rmi.RemoteException;

import modele.Article;
import modele.Client;

public interface SiegeFonctionnalite extends Remote{
   

    public Client createClient(Client client) throws RemoteException;

}
