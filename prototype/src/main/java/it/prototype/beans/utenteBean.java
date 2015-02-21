package it.prototype.beans;
 
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
 
public class utenteBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private int userid;
 	private String nome;
    private String cognome;
	private String ruolo;
	private String nomeuff;
	private String password;
	private String city;  
    private Map<String,String> ufficio = new HashMap<String, String>();
    private Date data;
    private String via;
    private String citta;
    private String telefono;
 
    public utenteBean(int userid, String nome, String cognome, String ruolo, String nomeuff, String password, Date data, String via, String citta, String telefono) {
        this.userid = userid;
    	this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.nomeuff = nomeuff;
        this.password = password;
        this.ufficio = ufficio;
        this.data = data;
        this.via = via;
        this.citta = citta;
        this.telefono = telefono;
    }
    
    public int getUserid() {
 		return userid;
 	}

 	public void setUserid(int userid) {
 		this.userid = userid;
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

	public String getNomeuff() {
		return nomeuff;
	}

	public void setNomeuff(String nomeuff) {
		this.nomeuff = nomeuff;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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