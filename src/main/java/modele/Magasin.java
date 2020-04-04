package modele;

import java.util.LinkedList;

import modele.modeleinterface.MesFactures;




public class Magasin implements MesFactures{
    private int idMagasin;
    private String ville;
    private String  departement;
    private String adresse;
    private LinkedList<Facture> factures= new LinkedList<Facture>();
    
    public Magasin(String ville, String departement, String adresse) {
        this.ville=ville;
        this.departement=departement;
        this.adresse=adresse;
    }
    
    public int getIdMagasin() {
        return idMagasin;
    }
    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getDepartement() {
        return departement;
    }
    public void setDepartement(String departement) {
        this.departement = departement;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void AddFacture(Facture factures) {
        this.factures.add(factures);
        
    }
    
    
    
}
