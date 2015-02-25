package it.prototype.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
 
@ManagedBean(name = "utenti")
@RequestScoped
@SessionScoped
public class utentiBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    public int userid;
	public String nome;
    public String cognome;
    public String ruolo;
    public String password;
    private String seluff; 
    private Date data;
    public String via;
    public String citta;
    public String telefono;
    public List<Utente> users;
    public List<Ufficio> uffix;
    public String nomeUff;
    public utenteBean utente;
    public hashpass hspass;

	public Map<String,String> ufficio1List = new HashMap<String, String>();
	
	@ManagedProperty(value="#{utenteDao}")
    UtenteDao utenteDao;
	
	@ManagedProperty(value="#{ufficioDao}")
    UfficioDao ufficioDao;

	public hashpass getHspass() {
		return hspass;
	}

	public void setHspass(hashpass hspass) {
		this.hspass = hspass;
	}

	public List<Utente> getUsers() {
		return users;
	}

	public void setUsers(List<Utente> users) {
		this.users = users;
	}
	
	public UtenteDao getUtenteDao() {
		return utenteDao;
	}

	public void setUtenteDao(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}
	

	public UfficioDao getUfficioDao() {
		return ufficioDao;
	}

	public void setUfficioDao(UfficioDao ufficioDao) {
		this.ufficioDao = ufficioDao;
	}


    public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public String getSeluff() {
		return seluff;
	}

	public void setSeluff(String seluff) {
		this.seluff = seluff;
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
    
    public List<Ufficio> getUffix() {
		return uffix;
	}

	public void setUffix(List<Ufficio> uffix) {
		this.uffix = uffix;
	}

	public static ArrayList<utenteBean> getUtentilist() {
		return utentiList;
	}

	public Map<String, String> getUfficio1List() {
		return ufficio1List;
	}

	public void setUfficio1List(Map<String, String> ufficio1List) {
		this.ufficio1List = ufficio1List;
	}

	public String getNomeUff() {
		return nomeUff;
	}

	public void setNomeUff(String nomeUff) {
		this.nomeUff = nomeUff;
	}

	@PostConstruct
    public void init() {
	    users = utenteDao.getAll();
	    uffix = ufficioDao.getAll();
	    inizializza();
	    initdata();
	}
 
    public String addAction() {
    	
  	 // Istanzio e salvo gli oggetti utente e dettaglioutente
     Dettaglioutente dettUte = new Dettaglioutente(this.data, this.via, this.citta, this.telefono);
     // Crittografia della password
     String hashed = "";
     try {hashed = hspass.createHash(this.password);}
     catch(Exception ex) {System.out.println("Anomalia conversione password");}
     Utente ute = new Utente(this.nome, this.cognome, this.ruolo, hashed);
     utenteDao.saveDetUte(ute, dettUte);
     
     Ufficio uff = ufficioDao.getUfficio(Integer.parseInt(seluff));
     ufficioDao.aggUffUte(ute, uff);
     
     inizializza();
     initcampi();
     initdata();
     return null;
    }
    
    public void onEdit(RowEditEvent event) { 
        Dettaglioutente dettUte = new Dettaglioutente(((utenteBean) event.getObject()).getUserid(), ((utenteBean) event.getObject()).getData(), ((utenteBean) event.getObject()).getVia(), ((utenteBean) event.getObject()).getCitta(), ((utenteBean) event.getObject()).getTelefono());
        Utente ute = new Utente(((utenteBean) event.getObject()).getUserid(), ((utenteBean) event.getObject()).getNome(), ((utenteBean) event.getObject()).getCognome(), ((utenteBean) event.getObject()).getRuolo(), ((utenteBean) event.getObject()).getPassword());  	
        // Aggiorno l'utente
        utenteDao.aggUte(ute, dettUte);

        Ufficio uff = ufficioDao.getUfficio(Integer.parseInt(seluff));
        ufficioDao.aggUffUte(ute, uff);

        inizializza();
        FacesMessage msg = new FacesMessage("Utente modificato", ((utenteBean) event.getObject()).getCognome() + " " + ((utenteBean) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Modifiche annullate");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    } 
    
	public void delete(utenteBean std){
		if (std.getRuolo().equals("Amministratore")){
			FacesMessage msg = new FacesMessage("Impossibile cancellare utente Admin");   
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			// Elimina l'utente
			utenteDao.deleteUtente(std.getUserid());
			inizializza();
			FacesMessage msg = new FacesMessage("Record cancellato");   
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	} 
	
    public String changePass() {
        return "changepass";
     }
    
    public String gestUff() {
        return "ufficio";
     }
	
	private void inizializza(){
		
        utentiList.clear();
	    List<Utente> users = utenteDao.getAll();
		for (Utente user : users) {
			Dettaglioutente dettuser = utenteDao.getDettaglioutente(user.getuserId());
			Ufficio tmpuff = user.getUfficio();
			nomeUff = tmpuff.getNomeUfficio();
		//	seluff =  Integer.toString(tmpuff.getUfficioId());
			utenteBean utentetmp = new utenteBean(user.getuserId(), user.getNome(), user.getCognome(), user.getRuolo(), nomeUff, user.getPassword(), dettuser.getDataNascita(), dettuser.getVia(), dettuser.getCitta(), dettuser.getTelefono());
			utentiList.add(utentetmp);
		}
	}

	
	private void initcampi(){
	     nome = "";
	     cognome = "";
	     ruolo = "";
	     password = "";
	     via = "";
	     citta = "";
	     telefono = "";	
	}
	
	private void initdata(){		
	     String dateStr = "1970-01-01T00:00:00.000+01:00";
	     SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 
	     try  {
	     	data = sdf.parse(dateStr.replaceAll(":(?=..$)", "")); 
	     } catch (ParseException e) { 
	         System.out.println("Anomalia parser data " + sdf); 
	     }
	}

	
	public Map<String,String> getUfficio1Value() {

		int indi=0;
	   	for (Ufficio uff : uffix) {
	   		ufficio1List.put(uff.getNomeUfficio(), Integer.toString(uff.getUfficioId()));
			indi = indi + 1;
		}
		return ufficio1List;
	
	}

}
