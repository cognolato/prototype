package it.prototype.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
 
@ManagedBean(name = "utenti")
@SessionScoped
public class utentiBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    public String nome;
    public String ruolo;
    private Date data;
    public String via;
    public String citta;
    public String telefono;
    utenteBean utente;
 

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

    public utenteBean getUtente() {
        return utente;
    }
 
    public void setUtente(utenteBean utente) {
        this.utente = utente;
    }
    private static final ArrayList<utenteBean> utentiList = new ArrayList<utenteBean>();
 
    public ArrayList<utenteBean> getUtentiList() {
        return utentiList;
    }
 
    public String addAction() {
        utenteBean utentetmp = new utenteBean(this.nome, this.ruolo, this.data, this.via, this.citta, this.telefono);
        utentiList.add(utentetmp);
 
        nome = "";
        ruolo = "";
        String dateStr = "1970-01-01T00:00:00.000+01:00";
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 
        try  {
        	data = sdf.parse(dateStr.replaceAll(":(?=..$)", "")); 
        } catch (ParseException e) { 
                System.out.println("Unparseable using " + sdf); 
        }
        via = "";
        citta = "";
        telefono = "";
        return null;
    }
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Record modificato",((utenteBean) event.getObject()).getNome());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Modifiche annullate");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }  
	public void delete(utenteBean std){
		utentiList.remove(std);
		FacesMessage msg = new FacesMessage("Record cancellato");   
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}