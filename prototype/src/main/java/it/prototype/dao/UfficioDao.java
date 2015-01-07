package it.prototype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.*;

public interface UfficioDao {


	public int save(Ufficio ufficio);
	
	public void deleteUfficio(Integer id);

	public Ufficio getUfficio(Integer id);
	
	public void aggUffUte(Utente ute, Ufficio uff);

	public List<Ufficio>getAll();
	
}
