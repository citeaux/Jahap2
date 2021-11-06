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
import org.jahap.business.acc.revaccountsbean;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.acc.AccountPosition;
import org.jahap.entities.acc.Csc;
import org.jahap.entities.acc.Revaccounts;
import org.jahap.entities.base.Rates;
import org.jahap.entities.base.Vattype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */
public class ratesbean  extends DatabaseOperations  implements rates_i{
   
    Logger log = LoggerFactory.getLogger(ratesbean.class);
    
  
     JahapDatabaseConnector dbhook;
    private static List<Rates> allrecordlist; 
    private revaccountsbean revAccBean;
    private Long id;

    public ratesbean(){
       long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         revAccBean= new revaccountsbean();
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rates t ORDER BY t.id");
            List<Rates>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rates t ORDER BY t.id");
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

    public void jumptolastrecord(){
        currentRecordNumber=numberOfLastRecord;
    }
    
    public List<Rates>getCurrentRate(){
        List<Rates>hh=new ArrayList<Rates>();
        hh.add(allrecordlist.get(currentRecordNumber));
        return hh;
    
    }
    
    
        public void createNewEmptyRecord() {
        log.debug("Function entry createNewEmptyRecord");
       
         
                 
        if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        Rates emptyroom = new Rates();
        
       
        allrecordlist.add(emptyroom);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected     
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

    public void saveRecord() {
        log.debug("Function entry save Record");
         
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
      
       if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
    }
    
    
     private void saveOldRecord(){
         log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Rates.class,allrecordlist.get(currentRecordNumber).getId() );
             dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
    

    
    private void saveNewRecord(){
        log.debug("Function entry saveNewRecord");
        
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(currentRecordNumber));
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
        }
    
    
     public void  movetoDataRecordId(Long id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber=inl;
        } catch (Exception e) {
            e.printStackTrace();  
            
        }
       
   }
     
   public List<Rates>SearchForRate(String searchstring){
    
        return allrecordlist;
    }  
     
/* Depricated - not used
     public Rates SearchForRatewithId(long id){
             
         for(Rates kj:allrecordlist){
                 if(kj.getId()==id){
                     return kj;
                 }
         }
         
         
         return null;
                 
     }
     
  */   
     
    public Rates  getDataRecord(Long id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
            System.out.println(currentRecordNumber);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return allrecordlist.get(currentRecordNumber);
   } 
     
     
     
    public void quitDBaccess() {
        dbhook.getEntity().close();
    }

    
    // ---------------------- Getters & Setters   ------------------
    
    
    public Collection<AccountPosition> getAccountPositionCollection() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getAccountPositionCollection();
        }
        return null;
    }

    public String getCode() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCode();
        return "";
    }

    public Long getId() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    public String getName() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return null;
    }

    public double getPrice() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getPrice();
        return 0;
    }

    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        
    }

    public void setCode(String code) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setCode(code);
    }

  

    public void setName(String name) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setName(name);
    }

    public void setPrice(double price) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord(); 
        
        
        allrecordlist.get(currentRecordNumber).setPrice(price);
    }

    /**
     *
     * @param id
     * @deprecated
     */
    public void setId(Long id) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    
//        public long getRevaccount() {
//            if( tabelIsEmpty!=true) 
//                  return allrecordlist.get(currentRecordNumber).getRevaccount().getId();
//            return 0;
//        }

    

    public Collection<Csc> getCscCollection() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getCscCollection();
        }
        return null;   
    }

    public boolean getOvernight() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getOvernight();
        }
        return false;
    }

    public void setCscCollection(Collection<Csc> cscCollection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setOvernight(boolean overnight) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        
        allrecordlist.get(currentRecordNumber).setOvernight(overnight);
    }

    public void setRevaccount(long revaccount) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setRevaccount(revAccBean.SearchForRevAccount(revaccount));
    }

    @Override
    public Vattype getVattype() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getVattype();
        }
        return null;
    
    
    }

    
    public void setVattype(long vattype) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         vattypesbean vatb= new vattypesbean(); 
         allrecordlist.get(currentRecordNumber).setVattype(vatb.getDataRecord(vattype));
         
    
    
    }
    
    
    @Override
    public void setVattype(Vattype vattype) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         allrecordlist.get(currentRecordNumber).setVattype(vattype);
         
    
    
    
    }

    @Override
    public Revaccounts getRevaccount() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevaccount();
        return null;
    }

    @Override
    public void setRevaccount(Revaccounts revaccount) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         allrecordlist.get(currentRecordNumber).setRevaccount(revaccount);
    }
    
}
