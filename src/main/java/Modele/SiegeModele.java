/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author utilisateur
 */
public class SiegeModele  implements Serializable {
    
    //<editor-fold desc="Attributs">
    Date date;
    Double chiffreAffaire;
    //</editor-fold>
    
    //<editor-fold desc="Getters et Setters">
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getChiffreAffaire(Date date) {
        this.date = date;
        return chiffreAffaire = calculCA();
    }

    public void setChiffreAffaire(Double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }
    //</editor-fold>
    
    //<editor-fold desc="Méthodes">
    public Double calculCA(){
        Double valeur = 0.0;
        return valeur;
    }
    //</editor-fold>
    
}
