/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;
<<<<<<< HEAD

import java.util.ArrayList;
import java.util.List;
=======
>>>>>>> donatien

/**
 *
 * @author utilisateur
 */
public class CaisseModele {
	//Attributs
	List<Article> lesArticles = new ArrayList<Article>();
	int nombreArticle;
	    
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
    
    public void initArticles(){
        lesArticles.add(new Article("Short Homme", 30.0, 5));
        lesArticles.add(new Article("Short Femme", 30.0, 5));
        lesArticles.add(new Article("Chronometre", 10.0, 5));
    }
}
