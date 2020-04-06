package modele;

import java.io.Serializable;
import java.util.LinkedList;

public class Article implements Serializable {
    private int idArticle;
    private String reference;
    private double prix_unitaire;
    private int nombre_exemplaire;
    private int id_famille;
    private LinkedList<RelationArticleFacture> articleFacture= new LinkedList<RelationArticleFacture>();
    private LinkedList<RelationArticleMagasin> articleMagasin= new LinkedList<RelationArticleMagasin>();
    
    public int getId_famille() {
        return id_famille;
    }

    public void setId_famille(int id_famille) {
        this.id_famille = id_famille;
    }

    public Article(String reference, double prix_unitaire, int nombre_exemplaire) {
        this.reference=reference;
        this.prix_unitaire=prix_unitaire;
        this.nombre_exemplaire=nombre_exemplaire;
                
    }
    
    public int getIdArticle() {
        return idArticle;
    }
    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public double getPrix_unitaire() {
        return prix_unitaire;
    }
    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
    public int getNombre_exemplaire() {
        return nombre_exemplaire;
    }
    public void setNombre_exemplaire(int nombre_exemplaire) {
        this.nombre_exemplaire = nombre_exemplaire;
    }

    /*public void addFacture(int quantite, int id_facture) {
        RelationArticleFacture articleFacture= new RelationArticleFacture(quantite);
        articleFacture.setId_fature(id_facture);
    }*/
    
    public String toString() {
        StringBuilder string= new StringBuilder();
        string.append("idArticle: ");
        string.append(this.idArticle);
        string.append(" ");
        string.append("reference: ");
        string.append(this.reference);
        string.append(" ");
        string.append("id_famille: ");
        string.append(this.id_famille);
        string.append(" ");
        string.append("nombre_exemplaire: ");
        string.append(this.nombre_exemplaire);
        string.append(" ");
        string.append("prix_unitaire");
        string.append(this.prix_unitaire);
        string.append(" ");
        
        return string.toString();
    }
}
