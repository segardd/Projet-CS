/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author utilisateur
 */
public class SiegeModele {
    
    //<editor-fold desc="Attributs">
    Date date;
    Double chiffreAffaire;
    List<ArticleDAO> lesArticles = new ArrayList<ArticleDAO>();
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

    public List<ArticleDAO> getLesArticles() {
        return lesArticles;
    }

    public void setLesArticles(List<ArticleDAO> lesArticles) {
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
        lesArticles.add(new ArticleDAO("Short Homme", 1, 30.0, 5));
        lesArticles.add(new ArticleDAO("Short Femme", 1, 30.0, 5));
        lesArticles.add(new ArticleDAO("Chronometre", 1, 10.0, 5));
    }
    
    public Double calculCA(){
        Double valeur = 0.0;
        return valeur;
    }
    //</editor-fold>
    
}
