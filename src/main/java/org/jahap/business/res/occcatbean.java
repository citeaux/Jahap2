package org.jahap.business.res;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jahap.business.base.languagebean;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Cat;
import org.jahap.entities.res.Occ;
import org.jahap.entities.res.Occcat;
import org.jahap.entities.res.Res;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


/**
 *
 * @author Sebastian
 */


public class occcatbean extends DatabaseOperations implements occcat_i {

 JahapDatabaseConnector dbhook;
	



    private static List<Occcat> allrecordlist;
Logger log = LoggerFactory.getLogger(occcatbean.class);
    /**
     *
     */
    public occcatbean(){
       
         log.debug("Function entry countrybean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occcat t ORDER BY t.id");
            List<occcatbean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Occcat t ORDER BY t.id");
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
    
    public List<Occcat>SearchForocccat(String searchstring){
        
         log.debug("Function entry SearchForocccat");
      
         
        
        log.debug("Function exit SearchForocccat ");
        return allrecordlist;
    }  
    
   
    
       public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Occcat>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Occcat emptyacc = new Occcat();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    }
     
     
     
     
    

    @Override
    public void nextRecordBackward() {
         log.debug("Function entry nextRecordBackward");
        
       if (currentRecordNumber>0) {
            currentRecordNumber--;
        }  
        log.debug("Function exit nextRecordBackward");
    }

    @Override
    public void nextRecordForeward() {
        log.debug("Function entry nextRecordForeward");
        
       if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }    
       
        log.debug("Function exit nextRecordForeward ");
    }

    public void removeLatestRecord(){
        allrecordlist.remove(currentRecordNumber);
    }
    
    
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occcat t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
    
     public Occcat getDataRecord(long id){
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
    
    public Occcat getLastPosition(){
          log.debug("Function entry getLastPosition(");
             if( tabelIsEmpty!=true){ 
                 log.debug("Function exit getLastPosition");   
              return allrecordlist.get(currentRecordNumber);
             }
             log.debug("Function exit getLastPosition with Null");
        return null;
    }
     
    public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }    
     public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }    


     private void saveNewRecord(){
          log.debug("Function entry saveNewRecord");
          
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from occcat t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     }
    
     
     
    //@Override
    public void quitDBaccess() {
       log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
       
           log.debug("Function exit quitDBaccess");
    }

     private void saveOldRecord(){
           log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().refresh(dbhook.getEntity().find(Occcat.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

    @Override
    public Date getArrivaldate() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getArrivaldate();
        return null;
    }


    @Override
    public Date getDeparturedate() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getDeparturedate();
        return null;
    }

    @Override
    public Long getId() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

  

    @Override
    public void setArrivaldate(Date arrivaldate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void setDeparturedate(Date departuredate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void setCat(Cat cat) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setCat(cat);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setCat(cat);
        }
    }

    @Override
    public void setOcc(Occ occ) {
       if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setOcc(occ);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setOcc(occ);
        }
    }

    @Override
    public void setRes(Res res) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setRes(res);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setRes(res);
        }
    }

    @Override
    public Cat getCat() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getCat();
        return null;
    }

    @Override
    public Occ getOcc() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getOcc();
        return null;
    }

    @Override
    public Res getRes() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getRes();
        return null;
    }

   

  
    
    
    
    
    
    
    




}
