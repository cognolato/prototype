package it.prototype.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="applicazione")
public class Applicazione {

    @Id
    @Column(name="appid")
    @GeneratedValue
    private int appid;

    @Column(name="nomeapp")
    private String nomeapp;

    @Column(name="desrizioneapp")
    private String descrizioneapp;    
     
    @ManyToMany(fetch = FetchType.LAZY, mappedBy="applicazioni", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Utente> utenti = new HashSet<Utente>();
    
   public Applicazione() {
      
   }

    public Applicazione(String nomeapp, String descrizioneapp) {
        this.nomeapp = nomeapp;
        this.descrizioneapp = descrizioneapp;
    }

   public int getAppid() {
      return appid;
   }

   public void setAppid(int appid) {
      this.appid = appid;
   }

   public String getNomeapp() {
      return nomeapp;
   }

   public void setNomeapp(String nomeapp) {
      this.nomeapp = nomeapp;
   }

   public String getDescrizioneapp() {
      return descrizioneapp;
   }

   public void setDescrizioneapp(String descrizioneapp) {
      this.descrizioneapp = descrizioneapp;
   }

   public Set<Utente> getUtenti() {
      return utenti;
   }

   public void setUtenti(Set<Utente> utenti) {
      this.utenti = utenti;
   }
   

}
