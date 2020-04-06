package modele;

import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedList;

import modele.modeleinterface.MesFactures;

public class ModePaiement implements MesFactures, Serializable{
    private int idMode_paiement;
    private String intitule_paiement;
    private LinkedList<Facture> factures= new LinkedList<Facture>();
    
    public ModePaiement(String intitule_paiement) {
        this.intitule_paiement=intitule_paiement;
    }
    
    public int getIdMode_paiement() {
        return idMode_paiement;
    }
    public void setIdMode_paiement(int idMode_paiement) {
        this.idMode_paiement = idMode_paiement;
    }
    public String getIntitule_paiement() {
        return intitule_paiement;
    }
    public void setIntitule_paiement(String intitule_paiement) {
        this.intitule_paiement = intitule_paiement;
    }
    public void AddFacture(int id_client, double total_facture, Date date_facturation) {
        Facture facture= new Facture(total_facture,date_facturation);
        facture.setIdClient(id_client);
        this.factures.add(facture);
    }
    
    public void AddFacture(Facture facture) {
        this.factures.add(facture);
    }
    
}
