package it.prototype.beans;

import it.prototype.dao.*;
import it.prototype.entity.*;
import it.prototype.utils.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean(name = "loginBean")
@SessionScoped

public class loginBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String password;
    private String message, uname;
    
    @ManagedProperty(value="#{utenteDao}")
    UtenteDao utenteDao;
    

	public UtenteDao getUtenteDao() {
		return utenteDao;
	}

	public void setUtenteDao(UtenteDao utenteDao) {
		this.utenteDao = utenteDao;
	}

	public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getUname() {
        return uname;
    }
 
    public void setUname(String uname) {
        this.uname = uname;
    }
 
    public String loginProject() {
        boolean result = utenteDao.login(uname, password);
        if (result) {
            // Richiama la sessione Http e salva username
            HttpSession session = util.getSession();
            session.setAttribute("username", uname);
 
            return "index";
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Credenziali di accesso non valide!",
                    "Riprova di nuovo!"));
 
            // manda un messaggio di sessione non valida

            return "login";
        }
    }
 
    public String logout() {
      HttpSession session = util.getSession();
      session.invalidate();
      return "login";
   }
}
