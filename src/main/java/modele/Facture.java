package modele;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Facture  implements Serializable {
    private int idFacture;
    private int id_magasin;
    private int id_mode_paiement;
    private int idClient;
    private LinkedList<RelationArticleFacture> articlesFac= new LinkedList<RelationArticleFacture>();
    
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
    
    
    public void setArticles(LinkedList<RelationArticleFacture> articles) {
        this.articlesFac=articles;
    }
    
    public LinkedList<RelationArticleFacture> getArticles() {
        return this.articlesFac;
    }
    
    public void addArticle(Article article, int quantite) {
        RelationArticleFacture rel= new RelationArticleFacture(quantite);
        rel.setId_facture(this.idFacture);
        rel.setId_article(article.getIdArticle());
        this.articlesFac.add(rel);
    }
    
    public String toString() {
        StringBuilder mafacture= new StringBuilder();
        mafacture.append("Numéro facture: ");
        mafacture.append(this.idFacture);
        mafacture.append("/r");
        
        mafacture.append("Numéro magasin: ");
        mafacture.append(this.id_magasin);
        mafacture.append("/r");
        
        mafacture.append("Numéro paiement: ");
        mafacture.append(this.id_mode_paiement);
        mafacture.append("/r");
        
        mafacture.append("Détails: /r");
        for(RelationArticleFacture rel: this.articlesFac) {
            mafacture.append(rel.getId_article()+" "+rel.getLarticle().getReference()+" x"+rel.getQuantite()+" "+
        rel.getLarticle().getPrix_unitaire()*rel.getQuantite()+"/r");
        }
        mafacture.append("TOTAL: ");
        mafacture.append(this.totale_facture);
        
        return mafacture.toString();
        
        
        
        
    }
    
    
    
}
