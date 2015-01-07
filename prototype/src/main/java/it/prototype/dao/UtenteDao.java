package it.prototype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.*;


public interface UtenteDao {

	public int save(Utente utente);
	
	public void deleteUtente(Integer id);
	
	public Utente getUtente(Integer id);
	
	public void aggUte(Utente ute);
	
	public List<Utente>getAll();

	public void saveDetUte(Utente ute, Dettaglioutente dettUte);
	
}
