package modele;

import java.io.Serializable;
import java.util.LinkedList;

public class Famille  implements Serializable {
    private int idFamille;
    private String intitule;
    private LinkedList<Article> articles= new LinkedList<Article>();
    
    public Famille(String intitule) {
        this.intitule=intitule;
    }

    public int getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(int idFamille) {
        this.idFamille = idFamille;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    public void AddArticle(Article article) {
        articles.add(article);
    }

}
