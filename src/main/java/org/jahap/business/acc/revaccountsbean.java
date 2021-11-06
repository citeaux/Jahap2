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


package org.jahap.business.acc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.acc.Revaccounts;
import org.jahap.entities.acc.Revenue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */
public class revaccountsbean extends DatabaseOperations implements revaccounts_i{

    Logger log = LoggerFactory.getLogger(revaccountsbean.class);
     private static List<Revaccounts> allrecordlist;
    JahapDatabaseConnector dbhook;
        public revaccountsbean(){
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revaccounts t ORDER BY t.id ");
            List<Revaccounts>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Revaccounts t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          log.trace("DB connected"); 
           // If the table is yet empty, init List 
        
    }
    
    
     private void RefreshAllRecords(){
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revaccounts t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }  
     public Revaccounts SearchForRevAccount(long id){
    
             for(Revaccounts reva:allrecordlist){
                  if(reva.getId()==id){
                          
                          return reva;
                  }
             }
         return null;
        
       
    }
        
      public List<String> SearchForRevAccountString(){
           
        
             
         return allrecordlist.stream().map(Revaccounts->Revaccounts.getName()).collect(Collectors.toList());
        
       
    }
     
     public List<Revaccounts> SearchForRevAccount(String g){
    
            
         return allrecordlist;
        
       
    }   
    
    
    public void createNewEmptyRecord() {
        if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Revaccounts>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                Revaccounts emptyacc = new Revaccounts();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; //
    }
    

    public void nextRecordBackward() {
        if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }

    public void nextRecordForeward() {
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
    }
    
    public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }
    
    public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }

    public void saveRecord() {
         if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
    }

   

     private void saveNewRecord(){
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
           
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revaccounts t GROUP BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
     } 
    
     private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Revaccounts.class,allrecordlist.get(currentRecordNumber).getId() );
             dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
     
       public void quitDBaccess(){
       dbhook.getEntity().close();
   } 
    
       
   // ####################### Getter and Setters ############ 
       
    public Revaccounts getRevAccount(long id){
           
        if( tabelIsEmpty!=true) 
             for(Revaccounts nh:allrecordlist){
                  if (nh.getId()==id){
                      return nh;
                  }
             }
               
              
        return null;
    }   
       
    
    public String getName() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return null;
    }

    public long getRevaccnumber() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevaccnumber();
        return 0;
    }

    public Collection<Revenue> getRevenueCollection() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevenueCollection();
        return null;
    }

    public void setName(String name) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setName(name);
    }

    public void setRevaccnumber(long revaccnumber) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevaccnumber(revaccnumber);
    }

    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getId() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    @Override
    public String getRev_group() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRev_group();
        return null;
    }

    @Override
    public void setRev_group(String rev_group) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRev_group(rev_group);
    }
    
}
