package dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import datasourceManagement.MySQLManager;
import modele.Article;
import modele.Famille;



public class ArticleDAOMySQL extends dao<Article>  implements Serializable{
	private static dao<Article> instance;
	
	private ArticleDAOMySQL() {
		
	}
	
	public static synchronized dao<Article> getInstance() {
		
		if (instance == null) {
			instance = new ArticleDAOMySQL();
		}
		return instance;		
	}

    @Override
    public Article find(long id) {
       
        StringBuilder req= new StringBuilder("SELECT * FROM article WHERE idArticle=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        Article article=null;
        try {
            if (result.next()) {
                article=new Article(result.getString("reference"),result.getDouble("prix_unitaire"),result.getInt("nombre_exemplaire"));
                article.setId_famille((result.getInt("ID_famille")));
                article.setIdArticle(result.getInt("idArticle"));
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article create(Article obj) {
        // TODO Auto-generated method stub
        String req= 
                "INSERT INTO article (reference,ID_famille,prix_unitaire,nombre_exemplaire)"
                + " VALUES('"+obj.getReference()+"',"+obj.getId_famille()+","+obj.getPrix_unitaire()+","+obj.getNombre_exemplaire()+")";
        
                    
        obj.setIdArticle(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public Article update(Article obj) {
        // TODO Auto-generated method stub
        String req="UPDATE article SET reference='"+obj.getReference()+"',"
                + "ID_famille= '" + obj.getId_famille()+"',"
                + "prix_unitaire="+obj.getPrix_unitaire()+","
                 + "nombre_exemplaire="+obj.getNombre_exemplaire()+""
                         + " WHERE idArticle= "+obj.getIdArticle();
        MySQLManager.getInstance().setData(req);
        return obj;
                
    }

    @Override
    public void delete(Article obj) {
        // TODO Auto-generated method stub
        String req="DELETE FROM article WHERE idArticle="+obj.getIdArticle();
        MySQLManager.getInstance().setData(req);
        
    }

    @Override
    public void saveall(LinkedList<Article> obj) {
        // TODO Auto-generated method stub
        for (Article article : obj) {
            if (article.getIdArticle()==0) {
                article = create(article);    
            }else {
                article = update(article);
            }
        }
        
    }

    @Override
    public LinkedList<Article> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From article";
        LinkedList<Article> articles= new LinkedList<Article>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Article article = null;
        try {
            while(result.next()) {
                article = new Article(result.getString("reference"),result.getDouble("prix_unitaire"),result.getInt("nombre_exemplaire"));
                article.setId_famille((result.getInt("ID_famille")));
             // on verra aussi pour les liens
                articles.add(article);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return articles;
    }
    
    public LinkedList<Article> findByRef(String intitule){
    	String req = "SELECT * From article WHERE reference= "+intitule;
    	LinkedList<Article> arts= new LinkedList<Article>();
    	ResultSet result = MySQLManager.getInstance().getData(req);
        Article art = null;
        try {
            while(result.next()) {
            	art.setIdArticle(result.getInt("idArticle"));
                art.setReference(result.getString("reference"));
                art.setId_famille(result.getInt("ID_famille"));
                art.setPrix_unitaire(result.getDouble("prix_unitaire"));
                art.setNombre_exemplaire(result.getInt("nombre_exemplaire"));
             // on verra aussi pour les liens
                arts.add(art);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return arts;
    }
    
    public LinkedList<Article> findByFamile(Famille famille){
    	String req = "SELECT * From article WHERE ID_famille= "+famille.getIdFamille();
    	LinkedList<Article> arts= new LinkedList<Article>();
    	ResultSet result = MySQLManager.getInstance().getData(req);
        Article art = null;
        try {
            while(result.next()) {
            	art.setIdArticle(result.getInt("idArticle"));
                art.setReference(result.getString("reference"));
                art.setId_famille(result.getInt("ID_famille"));
                art.setPrix_unitaire(result.getDouble("prix_unitaire"));
                art.setNombre_exemplaire(result.getInt("nombre_exemplaire"));
             // on verra aussi pour les liens
                arts.add(art);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return arts;
    }
    
    public String findIntituleByRef(String ref){
    	String req = "SELECT intitule From article,famille WHERE reference= " + ref + "AND ID_famille= idFamille";
    	ResultSet result = MySQLManager.getInstance().getData(req);
        String intitule = null;
        try {
            while(result.next()) {
            	intitule = result.getString("intitule");
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return intitule;
    }
}
