package modele;

import java.io.Serializable;

public class RelationArticleMagasin implements Serializable  {
    private int en_stock;
    private int id_art_mag;
    public int getId_art_mag() {
        return id_art_mag;
    }

    public void setId_art_mag(int id_art_mag) {
        this.id_art_mag = id_art_mag;
    }

    private int id_magasin;
    private int id_article;

    public RelationArticleMagasin(int en_stock) {
        super();
        this.en_stock = en_stock;
    }

    public int getEn_stock() {
        return en_stock;
    }

    public void setEn_stock(int en_stock) {
        this.en_stock = en_stock;
    }

    public int getId_magasin() {
        return id_magasin;
    }

    public void setId_magasin(int id_magasin) {
        this.id_magasin = id_magasin;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

}
