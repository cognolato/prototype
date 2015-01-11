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
    public int userid;
	public String nome;
    public String cognome;
    public String ruolo;
    private Date data;
    public String via;
    public String citta;
    public String telefono;
    public List<Utente> users;
	utenteBean utente;
    
    @ManagedProperty(value="#{utenteDao}")
    UtenteDao utenteDao;
    @ManagedProperty(value="#{applicazioneDao}")
    ApplicazioneDao applicazioneDao;
    @ManagedProperty(value="#{ufficioDao}")
    UfficioDao ufficioDao;

    
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
    
    @PostConstruct
    public void init() {
	    users = utenteDao.getAll();
	    utentiList.clear();
		for (Utente user : users) {
			Dettaglioutente dettuser = utenteDao.getDettaglioutente(user.getuserId());
			utenteBean utentetmp = new utenteBean(user.getuserId(), user.getNome(), user.getCognome(), user.getRuolo(), dettuser.getDataNascita(), dettuser.getVia(), dettuser.getCitta(), dettuser.getTelefono());
	        utentiList.add(utentetmp);
		}
        String dateStr = "1970-01-01T00:00:00.000+01:00";
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 
        try  {
        	data = sdf.parse(dateStr.replaceAll(":(?=..$)", "")); 
        } catch (ParseException e) { 
                System.out.println("Unparseable using " + sdf); 
        }
	}
 
    public String addAction() {
    	


  	// Definisco un oggetto Ufficio e gli assegno il valore 
 // 	Ufficio uff = new Ufficio();
 // 	uff.setNomeUfficio("Vendite");
 // 	ufficioDao.save(uff);
  //	int idUff = uff.getUfficioId();
  	
  	// Istanzio e salvo gli oggetti utente e dettaglioutente
     Dettaglioutente dettUte = new Dettaglioutente(this.data, this.via, this.citta, this.telefono);
     Utente ute = new Utente(this.nome, this.cognome, this.ruolo);
     utenteDao.saveDetUte(ute, dettUte);
      
      // Creo gli oggetti Applicazione
  //    Applicazione app1 = new Applicazione("SAP", "Sistema gestionale");
   //   applicazioneDao.save(app1);
   //   Applicazione app2 = new Applicazione("GIS", "Sistema territoriale");
   //   applicazioneDao.save(app2);

      // Creo un collegamento tra il nuovo utente e l'ufficio
   //   ufficioDao.aggUffUte(ute, uff);
      
      // Creo un collegamento tra il nuovo utente e la applicazioni create
   //   ute.getApplicazioni().add(app1);
  //    ute.getApplicazioni().add(app2);
      
      // Aggiorno l'utente
      // utenteDao.aggUte(ute, dettUte);

      // Recupero e stampo a video gli oggetti
    //  List<Utente> users = utenteDao.getAll();
   //   String nomeUff = uff.getNomeUfficio();
   //   for (Utente user : users) {
	//		System.out.println("L utente " + user.getCognome() + " " + user.getNome() + " vive a " + user.getDettaglioutente().getCitta() + " e lavora nell'ufficio " + nomeUff);
	 //       utenteDao.deleteUtente(user.getuserId());
	//		for (Applicazione appx : user.getApplicazioni()) {
	//        	System.out.println("Abilitato all'applicazione: " + appx.getNomeapp() + " - " + appx.getDescrizioneapp());
	 //       	applicazioneDao.deleteApplicazione(appx.getAppid());
	//        }
	//	}
	//	for (Ufficio uffx : ufficioDao.getAll()) {
	//	    System.out.println("Abilitato all'applicazione: " + uffx.getNomeUfficio());
	//		ufficioDao.deleteUfficio(uffx.getUfficioId());
	//	 }
    		
        utentiList.clear();
	    List<Utente> users = utenteDao.getAll();
		for (Utente user : users) {
			Dettaglioutente dettuser = utenteDao.getDettaglioutente(user.getuserId());
			utenteBean utentetmp = new utenteBean(user.getuserId(), user.getNome(), user.getCognome(), user.getRuolo(), dettuser.getDataNascita(), dettuser.getVia(), dettuser.getCitta(), dettuser.getTelefono());
	        utentiList.add(utentetmp);
		}
    //    utenteBean utentetmp = new utenteBean(ute.getuserId(), this.nome, this.cognome, this.ruolo, this.data, this.via, this.citta, this.telefono);
    //    utentiList.add(utentetmp);
 
        nome = "";
        cognome = "";
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
        Dettaglioutente dettUte = new Dettaglioutente(((utenteBean) event.getObject()).getUserid(), ((utenteBean) event.getObject()).getData(), ((utenteBean) event.getObject()).getVia(), ((utenteBean) event.getObject()).getCitta(), ((utenteBean) event.getObject()).getTelefono());
        Utente ute = new Utente(((utenteBean) event.getObject()).getUserid(), ((utenteBean) event.getObject()).getNome(), ((utenteBean) event.getObject()).getCognome(), ((utenteBean) event.getObject()).getRuolo());  	
        // Aggiorno l'utente
        utenteDao.aggUte(ute, dettUte);
        utentiList.clear();
	    List<Utente> users = utenteDao.getAll();
		for (Utente user : users) {
			Dettaglioutente dettuser = utenteDao.getDettaglioutente(user.getuserId());
			utenteBean utentetmp = new utenteBean(user.getuserId(), user.getNome(), user.getCognome(), user.getRuolo(), dettuser.getDataNascita(), dettuser.getVia(), dettuser.getCitta(), dettuser.getTelefono());
	        utentiList.add(utentetmp);
		}
        FacesMessage msg = new FacesMessage("Record modificato",((utenteBean) event.getObject()).getNome());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Modifiche annullate");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }  
	public void delete(utenteBean std){
		System.out.println(">>>" + std.getUserid() + "--" + std.getNome());
       // Dettaglioutente dettute = new Dettaglioutente(std.getUserid(), std.getData(), std.getVia(), std.getCitta(), std.getTelefono());
       // Utente ute = new Utente(std.getUserid(), std.getNome(), std.getCognome(), std.getRuolo());  
        utenteDao.deleteUtente(std.getUserid());
        utentiList.clear();
	    List<Utente> users = utenteDao.getAll();
		for (Utente user : users) {
			Dettaglioutente dettuser = utenteDao.getDettaglioutente(user.getuserId());
			utenteBean utentetmp = new utenteBean(user.getuserId(), user.getNome(), user.getCognome(), user.getRuolo(), dettuser.getDataNascita(), dettuser.getVia(), dettuser.getCitta(), dettuser.getTelefono());
	        utentiList.add(utentetmp);
		}
		FacesMessage msg = new FacesMessage("Record cancellato");   
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}