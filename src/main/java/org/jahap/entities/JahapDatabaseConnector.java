/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */




package org.jahap.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.jahap.config.ClientConfigDatabase;
import org.jahap.entities.databasetypes.DatabaseCommandFactory;
import org.jahap.entities.databasetypes.databaseCommands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author russ
 * 
 * - Hier muss ein Singelton einfügt werden, so dass nur eine Instanz des DB objekt gebildet werden kann.
 */
public  final class JahapDatabaseConnector {

     private static  String PERSISTENCE_UNIT_NAME;
     private static String DB_URL;
    // private static final String PERSISTENCE_UNIT_NAME = "JAHAP";
  private static EntityManagerFactory factory;
    private static EntityManager EntManager;
    private static JahapDatabaseConnector databaseConnector=null;
    private static  Connection classicConnection; 
     private String DBUSER;
     private String DBPASS;
     private static databaseCommands DBCommands;
     private static String DBPath;
    
//    private JahapDatabaseConnector() {
//        ReadConfig config = new ReadConfig();
//        List<ConfigItem> result = config.readConfig("config.xml");
//        PERSISTENCE_UNIT_NAME = result.get(0).getPersitence_unit();
//        DB_URL= result.get(0).getDatabase_url();
//        Map properties = new HashMap();
//        properties.put("jakarta.persistence.jdbc.url", DB_URL);
//        properties.put("jakarta.persistence.jdbc.user", DBUSER);
//        properties.put("jakarta.persistence.jdbc.password", DBPASS);
//        factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//        
//        EntManager=factory.createEntityManager(properties);
//        
//    }
    
    private JahapDatabaseConnector(String user, String password, ClientConfigDatabase result) {

      PERSISTENCE_UNIT_NAME = result.getPersitence_unit();
        DB_URL= result.getDatabase_url();
	DBPath=result.getDatabase_path();
	DatabaseCommandFactory k=new DatabaseCommandFactory();
	DBCommands=k.getDatabaseCommands(result.getDatabase_type());
	
        Map properties = new HashMap();
        DBPASS=password;
        DBUSER=user;
        properties.put("jakarta.persistence.jdbc.url", DB_URL);
        properties.put("jakarta.persistence.jdbc.user", user);
        properties.put("jakarta.persistence.jdbc.password", password);
        properties.put("eclipselink.ddl-generation", "create-tables");
        properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
        
        factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME,properties);
        getclassicConnection();
        EntManager=factory.createEntityManager();
        
    }
    
    private void getclassicConnection(){
	    try {
		    classicConnection = DriverManager.getConnection(DB_URL + "?user=" + DBUSER + "&password=" + DBPASS);
	    } catch (SQLException sQLException) {
	    }
    }
    
    
    public Connection getclassicConnection2(){
	    try {
		    return  DriverManager.getConnection(DB_URL + "?user=" + DBUSER + "&password=" + DBPASS);
	    } catch (SQLException sQLException) {
	    }
            return null;
    }
    
    public static EntityManagerFactory getFactory() {
        return factory;
    }

    public static void setFactory(EntityManagerFactory factory) {
        JahapDatabaseConnector.factory = factory;
    }
    
    
     public static JahapDatabaseConnector getConnector(String user, String password, ClientConfigDatabase databaseconfig){
          if(databaseConnector==null){
               databaseConnector=new JahapDatabaseConnector(user, password, databaseconfig);
               
        }
          return databaseConnector;
     }
     public static JahapDatabaseConnector getConnector(){
          
          return databaseConnector;
     }
    
    public static databaseCommands getDBCommmands(){
	    return DBCommands;
    }
    
     public static String getDBPath(){
	    return DBPath;
    }
     
   public static Connection GetClassicDbConnection() {
	   
	   
	   
	  return  classicConnection;
   }
     
   
     
    public EntityManager getEntity(){
       
        return EntManager;
       
    }
    
}
