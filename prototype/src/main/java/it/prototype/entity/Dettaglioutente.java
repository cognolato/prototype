package it.prototype.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.FetchType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="dettaglioutente")
public class Dettaglioutente {

    @Id
    @Column(name="userid", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="utente"))
    private int userId;
    
    @Column(name="datanascita")
    private Date dataNascita;
    
    @Column(name="via")
    private String via;
    
    @Column(name="citta")
    private String citta;
    
    @Column(name="telefono")
    private String telefono;
    
    @OneToOne(fetch = FetchType.LAZY, optional=true)
    @PrimaryKeyJoinColumn
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Utente utente;
    
    public Dettaglioutente() {
    
    }
    
    public Dettaglioutente(Date dataNascita, String via, String citta, String telefono) {
      this.dataNascita = dataNascita;
      this.via = via;
      this.citta = citta;
      this.telefono = telefono;
    }
    
    
    // Metodi Getter e Setter 
    
    public int getUserId() {
      return userId;
    }
    
    public void setUserId(int userId) {
      this.userId = userId;
    }
    
    public Date getDataNascita() {
      return dataNascita;
    }
    
    public void setDataNascita(Date dataNascita) {
      this.dataNascita = dataNascita;
    }
    
    public String getVia() {
      return via;
    }
    
    public void setVia(String via) {
      this.via = via;
    }
    
    public String getCitta() {
      return citta;
    }
    
    public void setCitta(String citta) {
      this.citta = citta;
    }
    
    public String getTelefono() {
      return telefono;
    }
    
    public void setTelefono(String telefono) {
      this.telefono = telefono;
    }
    
    public Utente getUtente() {
      return utente;
    }
    
    public void setUtente(Utente utente) {
      this.utente = utente;
    }

}
