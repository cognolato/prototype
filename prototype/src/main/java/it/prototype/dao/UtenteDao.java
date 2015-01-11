package it.prototype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.Utente;
import it.prototype.entity.Dettaglioutente;


public interface UtenteDao {

	public int save(Utente utente);
	
	public void deleteUtente(int userid);
	
	public Utente getUtente(int id);
	
	public Dettaglioutente getDettaglioutente(Integer id);
	
	public void aggUte(Utente ute, Dettaglioutente dettute);
	
	public List<Utente>getAll();

	public void saveDetUte(Utente ute, Dettaglioutente dettUte);
	
}
