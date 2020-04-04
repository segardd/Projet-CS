package modele;

import java.sql.Date;

public class Facture {
    private int idFacture;
    private int id_magasin;
    private int id_mode_paiement;
    private int idClient;
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    private double totale_facture;
    private Date date_facture;
    
    public Facture(double totale_facture, Date date_facture) {
        this.totale_facture=totale_facture;
        this.date_facture=date_facture;
    }
    
    public int getIdFacture() {
        return idFacture;
    }
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
    public int getId_magasin() {
        return id_magasin;
    }
    public void setId_magasin(int id_magasin) {
        this.id_magasin = id_magasin;
    }
    public int getId_mode_paiement() {
        return id_mode_paiement;
    }
    public void setId_mode_paiement(int id_mode_paiement) {
        this.id_mode_paiement = id_mode_paiement;
    }
    public double getTotale_facture() {
        return totale_facture;
    }
    public void setTotale_facture(double totale_facture) {
        this.totale_facture = totale_facture;
    }
    public Date getDate_facture() {
        return date_facture;
    }
    public void setDate_facture(Date date_facture) {
        this.date_facture = date_facture;
    }
    
    
}
