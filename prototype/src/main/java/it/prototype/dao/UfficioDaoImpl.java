package it.prototype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.*;


/**
 * Implementazione DAO
 *  
 * @author Mauro Cognolato
 */
@Transactional
public class UfficioDaoImpl implements UfficioDao {

	@PersistenceContext
	private EntityManager em;
	
	
	public int save(Ufficio ufficio) {
		em.persist(ufficio);
		return ufficio.getUfficioId();
	}
	
	public void deleteUfficio(Integer id) {
		em.remove(getUfficio(id));
	}

	public Ufficio getUfficio(Integer id) {
		return em.find(Ufficio.class, id);
	}
	
	public void aggUffUte(Utente ute, Ufficio uff) {
        uff.getUtenti().add(ute);
        em.merge(uff);
        em.flush();
    	// riallinea l'entità ai valori del DB
    	em.refresh(uff);
	}
	
	public void remUffUte(Utente ute, Ufficio uff) {
		uff.getUtenti().remove(ute);
        em.merge(uff);
	}	

	public void aggUff(Ufficio uff) {
        em.merge(uff);
	}

	public Ufficio minUff() {	
		Integer idTmp = (Integer)em.createQuery("select min(u.ufficioId) from Ufficio u").getSingleResult();
		return getUfficio(idTmp);
	}
	
	public List<Ufficio>getAll() {
		return em.createQuery("SELECT p FROM Ufficio p", Ufficio.class).getResultList();
	}
	
}
