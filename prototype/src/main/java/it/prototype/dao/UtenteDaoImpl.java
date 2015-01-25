package it.prototype.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.beans.utenteBean;
import it.prototype.entity.Applicazione;
import it.prototype.entity.Utente;
import it.prototype.entity.Dettaglioutente;
import it.prototype.utils.*;


/**
 * Implementazione DAO
 *  
 * @author Mauro Cognolato
 */
@Transactional(value = "transactionManager")
public class UtenteDaoImpl implements UtenteDao {

	hashpass hspass;

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
	
	@Override
    public boolean login(String user, String password) {

		boolean bootemp = false;
		List<Utente> users = em.createQuery("SELECT p FROM Utente p", Utente.class).getResultList(); 
		if (users == null || users.isEmpty()) {
			bootemp = false;
	    } else {
			for (Utente userx : users) {
				String login = userx.getCognome() + userx.getNome();
				try  {
					if (user.equals(login) && hspass.validatePassword(password, userx.getPassword())) {
						bootemp = true;	} 
				}
		        catch(Exception ex)  {
		            System.out.println("ERROR: " + ex); }
            }
	    }
		return bootemp;

    }
	
	 
    public hashpass getHspass() {
		return hspass;
	}

	public void setHspass(hashpass hspass) {
		this.hspass = hspass;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
