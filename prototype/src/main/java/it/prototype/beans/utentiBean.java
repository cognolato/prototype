package it.prototype.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.prototype.dao.ApplicazioneDao;
import it.prototype.dao.UfficioDao;
import it.prototype.dao.UtenteDao;
import it.prototype.entity.Applicazione;
import it.prototype.entity.Dettaglioutente;
import it.prototype.entity.Ufficio;
import it.prototype.entity.Utente;
 
@ManagedBean(name = "utenti")
@RequestScoped
public class utentiBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    public String nome;
    public String ruolo;
    private Date data;
    public String via;
    public String citta;
    public String telefono;
    utenteBean utente;
    
    @ManagedProperty(value="#{utenteDao}")
    UtenteDao utenteDao;
    @ManagedProperty(value="#{applicazioneDao}")
    ApplicazioneDao applicazioneDao;
    @ManagedProperty(value="#{ufficioDao}")
    UfficioDao ufficioDao;

	public UtenteDao getUtenteDao() {
		return utenteDao;
	}

	public void setUtenteDao(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}

	public ApplicazioneDao getApplicazioneDao() {
		return applicazioneDao;
	}

	public void setApplicazioneDao(ApplicazioneDao applicazioneDao) {
		this.applicazioneDao = applicazioneDao;
	}

	public UfficioDao getUfficioDao() {
		return ufficioDao;
	}

	public void setUfficioDao(UfficioDao ufficioDao) {
		this.ufficioDao = ufficioDao;
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
    	


  	// Definisco un oggetto Ufficio e gli assegno il valore 
  	Ufficio uff = new Ufficio();
  	uff.setNomeUfficio("Vendite");
  	ufficioDao.save(uff);
  	int idUff = uff.getUfficioId();
  	
  	// Istanzio e salvo gli oggetti utente e dettaglioutente
     Dettaglioutente dettUte = new Dettaglioutente(new Date(821212), "via Roma 11", "Milano", "0612345678");
     Utente ute = new Utente("Mario", "Rossi", "Amministratore");
     utenteDao.saveDetUte(ute, dettUte);
      
      // Creo gli oggetti Applicazione
      Applicazione app1 = new Applicazione("SAP", "Sistema gestionale");
      applicazioneDao.save(app1);
      Applicazione app2 = new Applicazione("GIS", "Sistema territoriale");
      applicazioneDao.save(app2);

      // Creo un collegamento tra il nuovo utente e l'ufficio
      ufficioDao.aggUffUte(ute, uff);
      
      // Creo un collegamento tra il nuovo utente e la applicazioni create
      ute.getApplicazioni().add(app1);
      ute.getApplicazioni().add(app2);
      
      // Aggiorno l'utente
      utenteDao.aggUte(ute);

      // Recupero e stampo a video gli oggetti
      List<Utente> users = utenteDao.getAll();
      String nomeUff = uff.getNomeUfficio();
      for (Utente user : users) {
			System.out.println("L utente " + user.getCognome() + " " + user.getNome() + " vive a " + user.getDettaglioutente().getCitta() + " e lavora nell'ufficio " + nomeUff);
	    //    utenteDao.deleteUtente(user.getuserId());
			for (Applicazione appx : user.getApplicazioni()) {
	        	System.out.println("Abilitato all'applicazione: " + appx.getNomeapp() + " - " + appx.getDescrizioneapp());
	        //	applicazioneDao.deleteApplicazione(appx.getAppid());
	        }
		}    
    		
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