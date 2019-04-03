package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.BusinessException;
import fr.eni.enchere.bo.Enchere;

public interface EnchereDAO {
	public void save(Enchere ench) throws BusinessException;
	public void delete(Enchere ench) throws BusinessException;
	public Enchere select(int id) throws BusinessException;
	public List<Enchere> lister() throws BusinessException;
}
