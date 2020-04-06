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
    List<Article> lesArticles = new ArrayList<Article>();
    int nombreArticle;
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

    public List<Article> getLesArticles() {
        return lesArticles;
    }

    public void setLesArticles(List<Article> lesArticles) {
        this.lesArticles = lesArticles;
    }

    public int getNombreArticle() {
        return nombreArticle;
    }

    public void setNombreArticle(int nombreArticle) {
        this.nombreArticle = nombreArticle;
    }
    //</editor-fold>
    
    //<editor-fold desc="Méthodes">
    public void initArticles(){
        lesArticles.add(new Article("Short Homme", 30.0, 5));
        lesArticles.add(new Article("Short Femme", 30.0, 5));
        lesArticles.add(new Article("Chronometre", 10.0, 5));
    }
    
    public Double calculCA(){
        Double valeur = 0.0;
        return valeur;
    }
    //</editor-fold>
    
}
