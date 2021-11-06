/*
 * The MIT License
 *
 * Copyright 2016 Open Jahap Community.
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
package org.jahap.config;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.vfs2.FileSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Sebastian
 */
public class ClientConfig {
    Logger log = LoggerFactory.getLogger(ClientConfig.class);
      
    private static final ClientConfig ourInstance = new ClientConfig();
    private List<ClientConfigDatabase> resultdb;
    private List<ClientConfigUser> resultuser;
    private ClientConfigDatabase usedbconfig;
    private ClientConfigUser usedclientuserconfig;
    private StandardRelativFilePaths paths;
    private FileSystem fs;      
    public static ClientConfig getInstance() {
        return ourInstance;
    }
    
    
    public ClientConfig()  {
        log.debug("Function entry ClientConfig");
        
        try {
            readconfig();
        } catch (ConfigurationException ex) {
            java.util.logging.Logger.getLogger(ClientConfig.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileSystemException ff){
            java.util.logging.Logger.getLogger(ClientConfig.class.getName()).log(Level.SEVERE, null, ff);
        }catch(MalformedURLException jj){
            java.util.logging.Logger.getLogger(ClientConfig.class.getName()).log(Level.SEVERE, null, jj);
        }

        log.debug("Function exit ClientConfig");
    }
    
    //********** DB config
    
      public List<String>getConfigListDB(){
          log.debug("Function getConfigListDB");
          return resultdb.stream().map(ClientConfigDatabase -> ClientConfigDatabase.getDatabase_title()).collect(Collectors.toList());
      }
    
      @Deprecated
      public List<String>getConfigList(){
          log.debug("Function getConfigList");
          return resultdb.stream().map(ClientConfigDatabase -> ClientConfigDatabase.getDatabase_title()).collect(Collectors.toList());
      }
      
      public ClientConfigDatabase getUsedbconfig() {
          log.debug("Function getUsedbconfig");
        return usedbconfig;
    }
      
      public ClientConfig getClientConfig(){
          return ourInstance;
      }
      
      public boolean setDatabaseConfig(String database_title){
          log.debug("Function entry setDatabaseConfig");
          
         database_title.toUpperCase();
          List<ClientConfigDatabase> zw = new ArrayList<ClientConfigDatabase>();
          zw= resultdb.stream().filter(u->u.getDatabase_title().toUpperCase().equals(database_title.toUpperCase())).collect(Collectors.toList());
         
          
           try {
               setUsedbconfig(zw.get(0));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
             log.debug("Function exit setDatabaseConfig");
        return false;
          
        
      }
      
      public ClientConfigDatabase getConfigitemAndSet(String database_title){
          log.debug("Function entry setDatabaseConfig");
          
         database_title.toUpperCase();
          List<ClientConfigDatabase> zw = new ArrayList<ClientConfigDatabase>();
          zw= resultdb.stream().filter(u->u.getDatabase_title().toUpperCase().equals(database_title.toUpperCase())).collect(Collectors.toList());
         
          
           try {
               setUsedbconfig(zw.get(0));
            return zw.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
                log.debug("Function exit setDatabaseConfig");
        return null;
          
      
      }
      
      
      public void setUsedbconfig(ClientConfigDatabase usedbconfig) {
          log.debug("Function setUsedbconfig");
        this.usedbconfig = usedbconfig;
    }
      
      
    //******* Userconfig  
      
      public List<String>getConfigListUser(){
          log.debug("Function getConfigListUser"  );
          return resultuser.stream().map(ClientConfigUser-> ClientConfigUser.getUserloginsetup_login()).collect(Collectors.toList());
      }
    
      public ClientConfigUser getConfigLastUser(){
           log.debug("Function getConfigListUser"  );
          
                   return resultuser.stream().filter(ClientConfigUser -> ClientConfigUser.isUserloginsetup_lastused().equals("true")).findFirst().get() ;
      
         
    
           
           
      }
      
      public boolean setUserConfig(String userlogin){
          log.debug("Function enter setUserConfig"  );
          
          
          userlogin.toUpperCase();
          List<ClientConfigUser> zw = new ArrayList<ClientConfigUser>();
          zw= resultuser.stream().filter(u->u.getUserloginsetup_login().toUpperCase().equals(userlogin.toUpperCase())).collect(Collectors.toList());
         
          
           try {
               setUsedclientUserconfig(zw.get(0));
               
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit setUserConfig"  );
         
        return false;
          
      }
      
      public ClientConfigUser getUsedclientUserconfig() {
        return usedclientuserconfig;
    }

    
  
     
    public void setUsedclientUserconfig(ClientConfigUser usedclientuserconfig) {
        this.usedclientuserconfig = usedclientuserconfig;
    }
      

    //******* Read cofig ****

    
    
    void readconfig() throws ConfigurationException, FileSystemException, MalformedURLException{
        log.debug("Function entry readconfig");
        
          fs=fs.getFileSystem();
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<XMLConfiguration> builder =
        new FileBasedConfigurationBuilder<XMLConfiguration>(XMLConfiguration.class)
            .configure(params.xml()
                .setURL(fs.getConfigFileUrl()));
            
                XMLConfiguration config = builder.getConfiguration();

           
            
           
            int dbconfigs=0;
            int userconfigs=0;
            
            Object prop = config.getProperty("database.database_title");
            if(prop instanceof Collection)
                {
                    
                    log.debug("Number of tables database: " + ((Collection<?>) prop).size());
                     dbconfigs= ((Collection<?>) prop).size();
                }
            
             prop = config.getProperty("userloginsetup.login");
            if(prop instanceof Collection)
                {
                    
                    log.debug("Number of tables user: " + ((Collection<?>) prop).size());
                     userconfigs= ((Collection<?>) prop).size();
                }
            
            
            // Read the database configuration
            resultdb= new ArrayList<ClientConfigDatabase>();
            ClientConfigDatabase dbm;
            for (int o=0;o<=dbconfigs-1;o++){
                dbm= new ClientConfigDatabase();
                dbm.setDatabase_driver(config.getProperty("database("+ String.valueOf(o) + ").database_driver").toString());
                dbm.setDatabase_path(config.getProperty("database("+ String.valueOf(o) + ").database_path").toString());
                dbm.setDatabase_title(config.getProperty("database("+ String.valueOf(o) + ").database_title").toString());
                dbm.setDatabase_type(config.getProperty("database("+ String.valueOf(o) + ").database_type").toString());
                dbm.setDatabase_url(config.getProperty("database("+ String.valueOf(o) + ").database_url").toString());
                dbm.setPersitence_unit(config.getProperty("database("+ String.valueOf(o) + ").persitence_unit").toString());
                if(config.getProperty("database("+ String.valueOf(o) + ").reportsetup.relativreportfolder").toString()!=""){
                    dbm.setReports_relativpath(config.getProperty("database("+ String.valueOf(o) + ").reportsetup.relativreportfolder").toString());
                }else{
                    dbm.setReports_relativpath(paths.REPORTS.toString());
                }
                resultdb.add(dbm);
            }
            
            resultuser= new ArrayList<ClientConfigUser>();
            ClientConfigUser udm;
            
            for (int m=0;m<=userconfigs-1;m++){
                udm = new ClientConfigUser();
                udm.setUserloginsetup_language(config.getProperty("userloginsetup("+ String.valueOf(m) + ").languuage").toString());
                udm.setUserloginsetup_login(config.getProperty("userloginsetup(" + String.valueOf(m) + ").login").toString());
                udm.setUserloginsetup_prefereddatabase(config.getProperty("userloginsetup(" + String.valueOf(m) + ").prefereddatabase").toString());
                udm.setUserloginsetup_lastused(config.getProperty("userloginsetup(" + String.valueOf(m) + ").lastused").toString());
                resultuser.add(udm);
            }
            
            
    }
    
    
}
