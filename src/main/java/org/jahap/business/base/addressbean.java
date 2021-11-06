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



package org.jahap.business.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jahap.MainApp;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.acc.Bill;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Country;
import org.jahap.entities.base.Currency;
import org.jahap.entities.base.Language;
import org.jahap.entities.res.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */

public class addressbean extends DatabaseOperations implements address_i {

  
   Logger log = LoggerFactory.getLogger(MainApp.class);
    JahapDatabaseConnector dbhook;
    private static List<Address> allrecordlist;
    // Has there been an Tabel buffer (List) created?



    
    /**
     * Constructor
     * establishes a connection to the Database an picks all adresses
     */
    public addressbean(){
        log.debug("Function entry constructor");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("SELECT a FROM Address a ORDER BY a.id");
            List<Address>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("SELECT a FROM Address a ORDER BY a.id ");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
        
        log.debug("Function exit constructor");
        
       log.debug("Function Exit newrecordcreated:" + newEmptyRecordCreated);
        
    }
    //--------------------------------- Search Function ----------------------
    /**
     *
     * @param searchstring
     * @return List<Adress>
     * gets all Addresses of the Database
     */
    public List<Address>SearchForAddress(String searchstring){
        log.debug("Function entry SearchForAddress");
        return allrecordlist;
        
        
    }
    
    public Address getCurrentAddress(){
        log.debug("Function entry getCurrentAddress");

        return allrecordlist.get(currentRecordNumber);
    
    }
    
    
    //---------------------------------
    
     /**
     * Creates an empty Record - has to be filled in oder get written in to the db
     */
    @Override
    public void createNewEmptyRecord(){
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Address>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Address emptyacc = new Address();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    }
   
    private void saveNewRecord(){
        log.debug("Function entry createNewEmptyRecord");
       if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Address t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
        }
      
    private void saveOldRecord(){
        log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Address.class,allrecordlist.get(currentRecordNumber).getId() );
             dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
        log.debug("Function exit saveOldRecord");
    }   
    
    /**
     * Saves the record which is currently worked on
     */
    @Override      
  public void saveRecord(){
        log.debug("Function entry saveRecord");
      if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
           RefreshAllRecords();
      }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
      
        log.debug("Function exit saveRecord"); 
  }        

       
  private void RefreshAllRecords(){
         
         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Address t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
  
    /**
     * moves the selctor to the next address record
     */
    @Override
    public void nextRecordForeward(){
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
        
    }
    
    /**
     * moves the selctor one record back
     */
    @Override
    public void nextRecordBackward(){
        
         if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }
    
    
    /**
     * closes database connection
     */
    public void quitDBaccess(){
        log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
        log.debug("Function exit quitDBaccess");
   }


    public void jumptolastrecord(){
         currentRecordNumber=numberOfLastRecord;
    }
   

    /**
     * gets the record id of the Dbrecord in focus 
     * @return long 
     */
    public Long getId(){
        log.debug("Function entry getid");
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return (long) 0;
   }
   
    /**
     * 
     * @param id
     */
    public void  setDataRecordId(Long id){
        log.debug("Function entry setDataRecordId");
       int inl;
        
      
            
            for(inl=0; inl<=allrecordlist.size()-1;inl++)
           {
                
                if((long)allrecordlist.get(inl).getId() == id){
                    currentRecordNumber=inl;
                   
                }
                
              
            } 
          log.debug("currentRecoprd" + currentRecordNumber ); 
        log.debug("Function exit setDataRecordId");
       
   }
   
    /**
     *
     * @param id
     * @return address
     */
    public Address getDataRecord(long id){
        log.debug("Function entry getDataRecord");
       int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
        }
        log.debug("Function exit getDataRecord");
        return allrecordlist.get(currentRecordNumber);
   }
   
    
    
    /**
     *
     * @return
     */
    @Override
    public String getName() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return "";
    }
    
   

    /**
     *
     * @return
     */
    @Override
    public String getChristianname() {
       if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getChristianname();
       return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getStreet() {
       if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getStreet();
       return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getZipcode() {
      if( tabelIsEmpty!=true)   
       return allrecordlist.get(currentRecordNumber).getZipcode();
      return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getCity() {
     if( tabelIsEmpty!=true)      
       return allrecordlist.get(currentRecordNumber).getCity();
     return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getPhone() {
       if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getPhone();
       return "";
    }

    /**
     *
     * @return
     */
    @Override
    public String getEmail() {
       if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getEmail();
       return "";
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        if(tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setName(name);
    }

    /**
     *
     * @param christianname
     */
    @Override
    public void setChristianname(String christianname) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
       
        allrecordlist.get(currentRecordNumber).setChristianname(christianname);
    }

    /**
     *
     * @param street
     */
    @Override
    public void setStreet(String street) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setStreet(street);
    }

    /**
     *
     * @param zipcode
     */
    @Override
    public void setZipcode(String zipcode) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setZipcode(zipcode);
    }
    

    /**
     *
     * @param city
     */
    @Override
    public void setCity(String city) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setCity(city);
    }

    /**
     *
     * @param phone
     */
    @Override
    public void setPhone(String phone) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setPhone(phone);
        
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmail(String email) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setEmail(email);
        
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Collection<Bill> getBillCollection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Collection<Res> getResCollection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        
    }

    public void setResCollection(Collection<Res> resCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Language getLanguage() {
       if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getLanguage();
       return null;
    }

    @Override
    public Country getCountry() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getCountry();
       return null;    
    
    }

    @Override
    public Currency getCurrency() {
        
         if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getCurrency();
       return null;
        
         }

    @Override
    public void setCountry(Country country) {
        
     if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
     
        allrecordlist.get(currentRecordNumber).setCountry(country);    
    
    }

    
    public void setCountry(int country) {
        
     if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
     
        countrybean gg=new countrybean();
        
        allrecordlist.get(currentRecordNumber).setCountry(gg.getDataRecord(country));    
    
    }
    
    @Override
    public void setCurrency(Currency currency) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setCurrency(currency);
    
    }
    
    
    public void setCurrency(int currency) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        currencybean hh=new currencybean();
        
        
        allrecordlist.get(currentRecordNumber).setCurrency(hh.getDataRecord(currency));
    
    }

    @Override
    public void setLanguage(Language language) {
         if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setLanguage(language);
        
        
            }

   public void setLanguage(int language) {
         if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
         languagebean hh=new languagebean();
        allrecordlist.get(currentRecordNumber).setLanguage(hh.getDataRecord(language));
        
        
            }

    @Override
    public String getTitel() {
         if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getTitel();
       return null;
    }

    @Override
    public String getHomepage() {
         if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getHomepage();
       return null;
    }

    @Override
    public String getAddresstype() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getAddresstype();
       return null;
    }

    @Override
    public String getRemarks() {
         if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getRemarks();
       return null;
    }

    @Override
    public String getGreeting() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getGreeting();
       return null;
    }

    @Override
    public String getSalutation() {
         if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getSalutation();
       return null;
    }

    @Override
    public void setTitel(String title) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
         
        allrecordlist.get(currentRecordNumber).setTitel(title);
    }

    @Override
    public void setHomepage(String homepage) {
         if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        
        allrecordlist.get(currentRecordNumber).setHomepage(homepage);
    }

    @Override
    public void setAddresstype(String addresstype) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
            
       
        allrecordlist.get(currentRecordNumber).setAddresstype(addresstype);
    }

    @Override
    public void setRemarks(String remarks) {
         if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setRemarks(remarks);
    }

    @Override
    public void setGreeting(String greeting) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
        
        
        allrecordlist.get(currentRecordNumber).setGreeting(greeting);
    }

    @Override
    public void setSalutation(String salutation) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
        
        allrecordlist.get(currentRecordNumber).setSalutation(salutation);
    }
    

}
