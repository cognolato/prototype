package it.prototype.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import it.prototype.entity.Applicazione;


public interface ApplicazioneDao {

	public int save(Applicazione applicazione);
	
	public void deleteApplicazione(Integer id);
	
	public void deleteApplicazioni();

	public Applicazione getApplicazione(Integer id);

	public List<Applicazione>getAll();
	
}
