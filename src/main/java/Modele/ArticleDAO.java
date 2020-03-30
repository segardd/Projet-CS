/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author utilisateur
 */
public class ArticleDAO {
    //Attributs
    private String reference;
    private int ID_famille;
    private Double prix_unitaire;
    private int nombre_exemplaire;
    
    public ArticleDAO(String reference, int ID_famille, Double prix_unitaire, int nombre_exemplaire) {
        this.reference = reference;
        this.ID_famille = ID_famille;
        this.prix_unitaire = prix_unitaire;
        this.nombre_exemplaire = nombre_exemplaire;
    }
    
    //Getters && Setters
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getID_famille() {
        return ID_famille;
    }

    public void setID_famille(int ID_famille) {
        this.ID_famille = ID_famille;
    }

    public Double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(Double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public int getNombre_exemplaire() {
        return nombre_exemplaire;
    }

    public void setNombre_exemplaire(int nombre_exemplaire) {
        this.nombre_exemplaire = nombre_exemplaire;
    }
    
    @Override
    public String toString() {
        return "ArticleDAO{" + "reference=" + reference + ", ID_famille=" + ID_famille + ", prix_unitaire=" + prix_unitaire + ", nombre_exemplaire=" + nombre_exemplaire + '}';
    }
}
