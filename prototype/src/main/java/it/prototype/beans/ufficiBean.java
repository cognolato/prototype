package it.prototype.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import it.prototype.dao.ApplicazioneDao;
import it.prototype.dao.UfficioDao;
import it.prototype.dao.UtenteDao;
import it.prototype.utils.*;
import it.prototype.entity.Applicazione;
import it.prototype.entity.Dettaglioutente;
import it.prototype.entity.Ufficio;
import it.prototype.entity.Utente;
 
@ManagedBean(name = "uffici")
@RequestScoped
public class ufficiBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    public int ufficioId;
	public String nomeUfficio;
    public List<Ufficio> offices;
	Ufficio ufficio;
	hashpass hspass;
    private Date data;
	
	@ManagedProperty(value="#{ufficioDao}")
    UfficioDao ufficioDao;

	public hashpass getHspass() {
		return hspass;
	}

	public void setHspass(hashpass hspass) {
		this.hspass = hspass;
	}
	
	public int getUfficioId() {
		return ufficioId;
	}

	public void setUfficioId(int ufficioId) {
		this.ufficioId = ufficioId;
	}

	public static ArrayList<Ufficio> getUfficiolist() {
		return ufficioList;
	}	
	
    public String getNomeUfficio() {
		return nomeUfficio;
	}

	public void setNomeUfficio(String nomeUfficio) {
		this.nomeUfficio = nomeUfficio;
	}

	public Ufficio getUfficio() {
		return ufficio;
	}

	public void setUfficio(Ufficio ufficio) {
		this.ufficio = ufficio;
	}

	public List<Ufficio> getOffices() {
		return offices;
	}

	public void setOffices(List<Ufficio> offices) {
		this.offices = offices;
	}

	public UfficioDao getUfficioDao() {
		return ufficioDao;
	}

	public void setUfficioDao(UfficioDao ufficioDao) {
		this.ufficioDao = ufficioDao;
	}

	private static final ArrayList<Ufficio> ufficioList = new ArrayList<Ufficio>();
 
    public ArrayList<Ufficio> getUfficioList() {
        return ufficioList;
    }
    
    public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@PostConstruct
    public void init() {
	    offices = ufficioDao.getAll();
	    inizializza();
	    initdata();
	}
 
    public String addAction() {
    	
  	// Istanzio e salvo gli oggetti ufficio
     Ufficio uff = new Ufficio(this.nomeUfficio);
     ufficioDao.save(uff);
    		
     inizializza();

     nomeUfficio = "";

     initdata();
     return null;
    }
    
    public void onEdit(RowEditEvent event) { 
        Ufficio uff = new Ufficio(((Ufficio) event.getObject()).getUfficioId(), ((Ufficio) event.getObject()).getNomeUfficio());  	
        // Aggiorno l'ufficio
        ufficioDao.aggUff(uff);
        inizializza();
        FacesMessage msg = new FacesMessage("Record modificato",((Ufficio) event.getObject()).getNomeUfficio());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Modifiche annullate");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    } 
    
	public void delete(Ufficio std){ 
		// Elimina l'ufficio
        ufficioDao.deleteUfficio(std.getUfficioId());
        inizializza();
		FacesMessage msg = new FacesMessage("Record cancellato");   
        FacesContext.getCurrentInstance().addMessage(null, msg);
	} 
	
    public String changePass() {
        return "changepass";
     }
    
    public String indietro() {
        return "index";
     }
	
	public void inizializza(){
        ufficioList.clear();
	    List<Ufficio> offices = ufficioDao.getAll();
		for (Ufficio uff : offices) {
			Ufficio ufficiotmp = new Ufficio(uff.getUfficioId(), uff.getNomeUfficio());
	        ufficioList.add(ufficiotmp);
		}
	}
	
	public void initdata(){
	     String dateStr = "1970-01-01T00:00:00.000+01:00";
	     SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 
	     try  {
	     	data = sdf.parse(dateStr.replaceAll(":(?=..$)", "")); 
	     } catch (ParseException e) { 
	         System.out.println("Anomalia parser data " + sdf); 
	     }
	}
	
}
