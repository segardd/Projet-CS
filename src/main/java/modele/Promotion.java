package modele;

import java.io.Serializable;
import java.sql.Date;

public class Promotion  implements Serializable  {
    private int idPromotion;
    private int valeur_pourcentage;
    private double valeur_brute;
    private Date debut_application;
    private Date fin_application;
    
    public Promotion(int valeur_pourcentage, double valeur_brute, Date debut_application, Date fin_application) {
        this.valeur_pourcentage=valeur_pourcentage;
        this.valeur_brute=valeur_brute;
        this.debut_application=debut_application;
        this.fin_application=fin_application;
    }
    
    public int getIdPromotion() {
        return idPromotion;
    }
    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }
    public int getValeur_pourcentage() {
        return valeur_pourcentage;
    }
    public void setValeur_pourcentage(int valeur_pourcentage) {
        this.valeur_pourcentage = valeur_pourcentage;
    }
    public double getValeur_brute() {
        return valeur_brute;
    }
    public void setValeur_brute(double valeur_brute) {
        this.valeur_brute = valeur_brute;
    }
    public Date getDebut_application() {
        return debut_application;
    }
    public void setDebut_application(Date debut_application) {
        this.debut_application = debut_application;
    }
    public Date getFin_application() {
        return fin_application;
    }
    public void setFin_application(Date fin_application) {
        this.fin_application = fin_application;
    }
    
    
}
