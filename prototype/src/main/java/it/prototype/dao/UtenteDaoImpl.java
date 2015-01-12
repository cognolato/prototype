package it.prototype.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.Utente;
import it.prototype.entity.Dettaglioutente;


/**
 * Implementazione DAO
 *  
 * @author Mauro Cognolato
 */
@Transactional(value = "transactionManager")
public class UtenteDaoImpl implements UtenteDao {

	@PersistenceContext(unitName = "jpaData")
	private EntityManager em;
	
	@Override	
	public int save(Utente utente) {
		em.persist(utente);
		return utente.getuserId();
	}
	
	@Override	
	public void deleteUtente(int userid) {
		em.remove(em.contains(getUtente(userid)) ? getUtente(userid) : em.merge(getUtente(userid)));
	}
	
	@Override	
	public Utente getUtente(int id) {
		return em.createQuery("SELECT p FROM Utente p where userid = " + id, Utente.class).getSingleResult();
	}	
	
	@Override	
	public Dettaglioutente getDettaglioutente(Integer id) {
		return em.createQuery("SELECT p FROM Dettaglioutente p where userid = " + id, Dettaglioutente.class).getSingleResult();
	}		
	
	@Override	
	public void aggUte(Utente ute, Dettaglioutente dettute) {
		em.merge(dettute);
        em.merge(ute);
	}
	
	@Override	
	public List<Utente>getAll() {
		return em.createQuery("SELECT p FROM Utente p", Utente.class).getResultList();
	}

	@Override	
	public void saveDetUte(Utente ute, Dettaglioutente dettUte) {	
    ute.setDettaglioutente(dettUte);
    dettUte.setUtente(ute);
    em.persist(ute);
	em.flush();
	// riallinea l'entità ai valori del DB
	em.refresh(ute);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
