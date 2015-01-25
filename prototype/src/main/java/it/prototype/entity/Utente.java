package it.prototype.entity;

import java.sql.Date;
import java.util.HashSet; 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class Utente {

	@Id
	@GeneratedValue
	@Column(name="userid")
	private int userId;

	@Column(name="nome")
	private String nome;

	@Column(name="cognome")
	private String cognome;

	@Column(name="ruolo")
	private String ruolo;
	
	@Column(name="password")
	private String password;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
	@JoinColumn(name="userid")
	private Dettaglioutente dettaglioutente;
	    
	 @ManyToOne
	 @JoinColumn(name="ufficioId", 
	                insertable=false, updatable=false, 
	                nullable=false)
	    private Ufficio ufficio;
	    
	  public Utente() {  
	  }

	  @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	  @JoinTable(name="utente_applicazione", 
	                joinColumns={@JoinColumn(name="userid")}, 
	                inverseJoinColumns={@JoinColumn(name="appid")})
	  private Set<Applicazione> applicazioni = new HashSet<Applicazione>();  



	public Utente(String nome, String cognome, String ruolo, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
		this.password = password;

	}
	
	public Utente(int userid, String nome, String cognome, String ruolo, String password) {
		this.userId = userid;
		this.nome = nome;
		this.cognome = cognome;
		this.ruolo = ruolo;
		this.password = password;

	}

	// 	Metodi Getter e Setter
	
	public Set<Applicazione> getApplicazioni() {
	      return applicazioni;
	}

	public void setApplicazioni(Set<Applicazione> applicazioni) {
	      this.applicazioni = applicazioni;
	}   

	public int getuserId() {
		return userId;
	}

	public void setuserId(int userId) {
		this.userId = userId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Dettaglioutente getDettaglioutente() {
		return dettaglioutente;
	}

	public void setDettaglioutente(Dettaglioutente dettaglioutente) {
		this.dettaglioutente = dettaglioutente;
	}

}
