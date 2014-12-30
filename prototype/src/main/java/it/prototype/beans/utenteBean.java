package it.prototype.beans;
 
import java.io.Serializable;
import java.util.Date;
 
public class utenteBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String nome;
    private String ruolo;
    private Date data;
    private String via;
    private String citta;
    private String telefono;
 
    public utenteBean(String nome, String ruolo, Date data, String via, String citta, String telefono) {
        this.nome = nome;
        this.ruolo = ruolo;
        this.data = data;
        this.via = via;
        this.citta = citta;
        this.telefono = telefono;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
    
}