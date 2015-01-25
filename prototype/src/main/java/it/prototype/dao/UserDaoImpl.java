	package it.prototype.dao;
	
	import java.sql.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
	  
	public class UserDaoImpl implements UserDao {
		
		@PersistenceContext(unitName = "jpaData")
		private EntityManager em;
		
		@Override
	    public boolean login(String user, String password) {

	    	 //Questa classe va implementata con la chiamata ad un database 
	    	 // i dati di utenze e password (crittografata) vanno memorizzati in una tabella
	    	 if (user.equals("admin") && password.equals("test")) 
	            {
	    		    return true;
	            }
	            else {
	                return false;
	            }

	    }
	}