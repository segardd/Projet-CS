package modele;

import java.io.Serializable;

public class RelationArticleFacture  implements Serializable {
    
    private int quantite;
    private int id_art_fac;
    private int id_article;
    private int id_facture;
    private Article Larticle;
    private Facture LaFacture;
    
    
    public Article getLarticle() {
        return Larticle;
    }



    public void setLarticle(Article larticle) {
        Larticle = larticle;
        this.id_article=larticle.getIdArticle();
    }



    public Facture getLaFacture() {
        return LaFacture;
    }



    public void setLaFacture(Facture laFacture) {
        LaFacture = laFacture;
        this.id_facture=laFacture.getIdFacture();
    }



    public RelationArticleFacture(int quantite) {
        super();
        this.quantite = quantite;
    }


    
    public int getId_art_fac() {
        return id_art_fac;
    }

    public void setId_art_fac(int id_art_fac) {
        this.id_art_fac = id_art_fac;
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

}
