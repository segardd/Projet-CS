package modele;

import java.sql.Date;
import java.util.LinkedList;

import modele.modeleinterface.MesFactures;

public class Client implements MesFactures{
    private int idClient;
    private String nom;
    private String prenom;
    private String adresse_mail;
    private String code_postal;
    private String ville;
    private LinkedList<Facture> factures=new LinkedList<Facture>();
    
    public  Client(String nom, String prenom,String adresse_mail,String code_postal,String ville) {
        this.nom=nom;
        this.prenom=prenom;
        this.adresse_mail=adresse_mail;
        this.code_postal=code_postal;
        this.ville=ville;
    }
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public void addFacture(double totale_facture, Date date_facture) {
        Facture facture=new Facture(totale_facture, date_facture);
        facture.setIdClient(this.idClient);
        this.factures.add(facture);
    }
    public void AddFacture(Facture facture) {
        this.factures.add(facture);
        
    }
}
    
    
    
    

