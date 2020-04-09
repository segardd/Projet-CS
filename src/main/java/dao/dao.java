package dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

import datasourceManagement.*;
import modele.Article;

public abstract class dao<T>  implements Serializable{

    private static dao instance;


    /**
     * Permet de r�cup�rer un objet via son ID
     * 
     * @param id
     * @return
     */
    public abstract T find(long id);

    /**
     * Permet de cr�er une entr�e dans la base de donn�es par rapport � un objet
     * 
     * @param obj
     */
    public abstract T create(T obj);

    /**
     * Permet de mettre � jour les donn�es d'une entr�e dans la base
     * 
     * @param obj
     */
    public abstract T update(T obj);

    /**
     * Permet la suppression d'une entr�e de la base
     * 
     * @param obj
     */
    public abstract void delete(T obj);

    public abstract void saveall(LinkedList<T> obj);

    public abstract LinkedList<T> findall();
}
