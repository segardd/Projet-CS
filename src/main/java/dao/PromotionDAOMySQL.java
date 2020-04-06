package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import datasourceManagement.MySQLManager;
import modele.Promotion;


public class PromotionDAOMySQL extends dao<Promotion>  implements Serializable{
    public static PromotionDAOMySQL instance;

    private PromotionDAOMySQL() {
        
    }

    public static synchronized PromotionDAOMySQL getInstance() {

        if (instance == null) {
            instance = new PromotionDAOMySQL();
        }
        return instance;
    }

    @Override
    public Promotion find(long id) {
        StringBuilder req= new StringBuilder("SELECT * FROM promotion WHERE idPromotion=");
        req.append(id);
        ResultSet result=MySQLManager.getInstance().getData(req.toString());
        Promotion promotion=null;
        try {
            if (result.next()) {
                promotion=new Promotion(
                        result.getInt("valeur_pourcentage"),
                        result.getDouble("valeur_brute"),
                        result.getDate("debut_application"),
                        result.getDate("fin_application")
                        );
                promotion.setIdPromotion(result.getInt("idPromotion"));
                
                
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return promotion;
        
    }

    @Override
    public Promotion create(Promotion obj) {
        String req= 
                "INSERT INTO promotion (valeur_pourcentage, valeur_brute, debut_application, fin_application)"
                + " VALUES("+obj.getValeur_pourcentage()+","+obj.getValeur_brute()+","+obj.getDebut_application()+","+obj.getFin_application()+")";
        
                    
        obj.setIdPromotion(MySQLManager.getInstance().setData(req));
        return obj;
    }

    @Override
    public Promotion update(Promotion obj) {
        String req="UPDATE promotion SET valeur_pourcentage='"+obj.getValeur_pourcentage()+"',"
                + "valeur_brute='" + obj.getValeur_brute()+"',"
                + "debut_application='"+obj.getDebut_application()+"',"
                 + "fin_application='"+obj.getFin_application()+"'"
                         + " WHERE idPromotion="+obj.getIdPromotion();
        MySQLManager.getInstance().setData(req);
        return obj;
    }

    @Override
    public void delete(Promotion obj) {
        String req="DELETE FROM promotion WHERE idPromotion="+obj.getIdPromotion();
        MySQLManager.getInstance().setData(req);

    }

    @Override
    public void saveall(LinkedList<Promotion> obj) {
        for (Promotion promotion : obj) {
            if (promotion.getIdPromotion()==0) {
                promotion = create(promotion);    
            }else {
                promotion = update(promotion);
            }
        }

    }

    @Override
    public LinkedList<Promotion> findall() {
        // TODO Auto-generated method stub
        String req = "SELECT * From promotion";
        LinkedList<Promotion> promotions= new LinkedList<Promotion>();
        ResultSet result = MySQLManager.getInstance().getData(req);
        Promotion promotion = null;
        try {
            while(result.next()) {
                promotion=new Promotion(
                        result.getInt("valeur_pourcentage"),
                        result.getDouble("valeur_brute"),
                        result.getDate("debut_application"),
                        result.getDate("fin_application")
                        );
                promotion.setIdPromotion(result.getInt("idPromotion"));
             // on verra aussi pour les liens
                promotions.add(promotion);
            }       
        }
        catch(Exception e){
            System.out.println(e.toString());
            System.out.println("pas compte");
        }
        return promotions;
        
    }

}
