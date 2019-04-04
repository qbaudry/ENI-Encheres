package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Categorie;

public interface CategorieDAO {
	public void save(Categorie c) throws BusinessException;
	public void delete(Categorie c) throws BusinessException;
	public Categorie select(int id) throws BusinessException;
	public Categorie selectbylibelle(String lib) throws BusinessException;
	public List<Categorie> lister() throws BusinessException;
}
