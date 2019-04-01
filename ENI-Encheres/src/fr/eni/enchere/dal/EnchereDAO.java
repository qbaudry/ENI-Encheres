package fr.eni.enchere.dal;

import java.util.List;

public interface EnchereDAO<T> {
	public Object selectObjectByID(String table,int id);
	public List<T> ListerTable(String table,T tyeObjet);
	
}
