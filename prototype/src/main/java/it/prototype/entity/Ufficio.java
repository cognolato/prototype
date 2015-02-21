package it.prototype.entity;

import java.util.HashSet;
import java.util.List;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="UFFICIO")
public class Ufficio {

    @Id
    @GeneratedValue
    @Column(name="ufficioid")
    private int ufficioId;
     
    @Column(name="nomeufficio")
    private String nomeUfficio;
  
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="ufficioid")
    @IndexColumn(name="iduf")
    private Set<Utente> utenti = new HashSet<Utente>();
    
    
  public Ufficio() {
  }
    
    public Ufficio(String nome) {
    this.nomeUfficio = nome;
  }
    
	public Ufficio(int ufficioid, String nome) {
		this.ufficioId = ufficioid;
	    this.nomeUfficio = nome;

	}
    
  // Metodi Getter e Setter

  public int getUfficioId() {
    return ufficioId;
  }

  public void setUfficioId(int ufficioId) {
    this.ufficioId = ufficioId;
  }

  public String getNomeUfficio() {
    return nomeUfficio;
  }

  public void setNomeUfficio(String nomeUfficio) {
    this.nomeUfficio = nomeUfficio;
  }

  public Set<Utente> getUtenti() {
    return utenti;
  }

  public void setUtenti(Set<Utente> utenti) {
    this.utenti = utenti;
  }
    
}
