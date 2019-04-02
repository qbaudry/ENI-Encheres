package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.BusinessException;

public interface RetraitDAO {
	public void save(Retrait r) throws BusinessException;
	public void delete(Retrait r) throws BusinessException;
	public Retrait select(int id) throws BusinessException;
	public List<Retrait> lister() throws BusinessException;
}
