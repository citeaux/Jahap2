package org.jahap.business.base;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Location;


/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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


/**
 *
 * @author russ
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Locationbean extends DatabaseOperations implements location_i {

 JahapDatabaseConnector dbhook;
	



    private static List<Location> allrecordlist;
Logger log = LoggerFactory.getLogger(Locationbean.class);
    /**
     *
     */
    public Locationbean(){
       
         log.debug("Function entry countrybean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Location t ORDER BY t.id");
            List<Locationbean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Location t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          
        log.debug("Function entry billbean");    
        
    }
    
    /**
     *
     * @param searchstring
     * @return
     */
    public List<Location>SearchForLocation (String searchstring){
        
         log.debug("Function entry SearchForLocation");
      
         
        
        log.debug("Function exit SearchForLocation ");
        return allrecordlist;
    }  
    
    
    /**
     *
     * 
     * @return
     */
    public List<String>SearchForLocation (){
        
         log.debug("Function entry SearchForLocation");
      
         
        
        log.debug("Function exit SearchForLocation ");
        return allrecordlist.stream().map(Location->Location.getFloor()).collect(Collectors.toList());
    }  
    
    /**
     *
     */
    public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }
    
    /**
     *
     */
    public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }
    
    /**
     *
     */
    public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Location>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Location emptyacc = new Location();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    }
     
    /**
     *
     */
    @Override
    public void nextRecordBackward() {
         log.debug("Function entry nextRecordBackward");
        
       if (currentRecordNumber>0) {
            currentRecordNumber--;
        }  
        log.debug("Function exit nextRecordBackward");
    }

    /**
     *
     */
    @Override
    public void nextRecordForeward() {
        log.debug("Function entry nextRecordForeward");
        
       if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }    
       
        log.debug("Function exit nextRecordForeward ");
    }

    /**
     *
     */
    @Override
    public void saveRecord() {
       log.debug("Function entry saveRecord");
         
         if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          RefreshAllRecords();
        
         }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
        log.debug("Function exit saveRecord ");
    }

    private void RefreshAllRecords(){
         
         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Location t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Location getDataRecord(long id){
        if(id==0)return null;
        log.debug("Function entry getDataRecord");
       int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
            return null;
        }
        
        log.debug("Function exit getDataRecord " + String.valueOf(currentRecordNumber) );
        return allrecordlist.get(currentRecordNumber);
        
   }
    
    /**
     *
     * @return
     */
    public Location getLastPosition(){
          log.debug("Function entry getLastPosition(");
             if( tabelIsEmpty!=true){ 
                 log.debug("Function exit getLastPosition");   
              return allrecordlist.get(currentRecordNumber);
             }
             log.debug("Function exit getLastPosition with Null");
        return null;
    }
     
    
     private void saveNewRecord(){
          log.debug("Function entry saveNewRecord");
          
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Location t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     }
    
    /**
     *
     */
    @Override
    public void quitDBaccess() {
       log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
       
           log.debug("Function exit quitDBaccess");
    }

     private void saveOldRecord(){
           log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Location.class,allrecordlist.get(currentRecordNumber).getId() );
             dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

    /**
     *
     * @return
     */
    @Override
    public Long getAddressId() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getAddressId();
       return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getBuilding() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getBuilding();
       return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFloor() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getFloor();
       return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Short getId() {
       if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getId();
       return null;
    }

    /**
     *
     * @param addressId
     */
    @Override
    public void setAddressId(Long addressId) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setAddressId(addressId);
    }

    /**
     *
     * @param building
     */
    @Override
    public void setBuilding(String building) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setBuilding(building);
    }

    /**
     *
     * @param floor
     */
    @Override
    public void setFloor(String floor) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
        
        allrecordlist.get(currentRecordNumber).setFloor(floor);
    }
    
    
    
    
    
    




}
