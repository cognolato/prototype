package it.prototype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.Applicazione;



/**
 * Implementazione DAO
 *  
 * @author Mauro Cognolato
 */
@Transactional
public class ApplicazioneDaoImpl implements ApplicazioneDao {

	@PersistenceContext
	private EntityManager em;
	
	
	public int save(Applicazione applicazione) {
		em.persist(applicazione);
		em.flush();
		em.refresh(applicazione);
		return applicazione.getAppid();
	}
	
	public void deleteApplicazione(Integer id) {
		em.remove(getApplicazione(id));
	}
	
	public void deleteApplicazioni() {
		List<Applicazione> appes = getAll();
		for (Applicazione appe : appes) {
			em.remove(appe);
		}
	}

	public Applicazione getApplicazione(Integer id) {
		return em.find(Applicazione.class, id);
	}
	
	/*public void aggUffUte(Utente ute, Applicazione uff) {
        uff.getUtenti().add(ute);
        em.merge(uff);
	} */

	public List<Applicazione>getAll() {
		return em.createQuery("SELECT p FROM Applicazione p", Applicazione.class).getResultList();
	}
	
}
