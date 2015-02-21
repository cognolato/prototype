package it.prototype.beans;

import it.prototype.dao.*;
import it.prototype.entity.*;
import it.prototype.utils.hashpass;
import it.prototype.utils.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean(name = "cambioBean")
@SessionScoped

public class cambioBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String message, uname;
    private String password;
    private String password1; 
    private String password2;
	hashpass hspass;

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

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
    
    public String modiPass() {
        
        boolean trovato = utenteDao.login(uname, password);
        if (trovato) {
        	Utente result = utenteDao.estrlogin(uname, password);
            String hashed = "";
            try {hashed = hspass.createHash(password2);}
            catch(Exception ex) {System.out.println("Anomalia conversione password");}
        	result.setPassword(hashed);
            utenteDao.aggUte(result);
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Credenziali di accesso non valide!",
                    "Riprova di nuovo!"));

            return "changepass";
        }
    } 
    
    public String changeLogin() {
        return "login";
     }    
 
    public String logout() {
      HttpSession session = util.getSession();
      session.invalidate();
      return "login";
   }
}
