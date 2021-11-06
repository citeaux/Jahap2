package org.jahap.jobs;

import java.util.ArrayList;
import java.util.List;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.jobs.JobJobscheduler;
import org.jahap.entities.jobs.Jobs;
import org.jahap.entities.jobs.Jobscheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * The MIT License
 *
 * Copyright 2015 Open Jahap Community.
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


public class JobJobschedulerbean extends DatabaseOperations implements JobJobscheduler_i {

 JahapDatabaseConnector dbhook;
	



    private static List<JobJobscheduler> allrecordlist;
Logger log = LoggerFactory.getLogger(JobJobschedulerbean.class);
    /**
     *
     */
    protected JobJobschedulerbean(){
       
         log.debug("Function entry countrybean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from JobJobscheduler t ORDER BY t.id");
            List<JobJobschedulerbean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  JobJobscheduler t ORDER BY t.id");
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
   
    protected void jumpToFirstRecord(){
        currentRecordNumber=0;
    }    
     protected void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }

    protected List<JobJobscheduler>SearchForJobJobscheduler(String searchstring){
        
         log.debug("Function entry SearchForJobJobscheduler");
      
         
        
        log.debug("Function exit SearchForJobJobscheduler ");
        return allrecordlist;
    }  
    
   
    
       public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<JobJobscheduler>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               JobJobscheduler emptyacc = new JobJobscheduler();
        
       
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
    
    protected void deleteRecord( int id){
	    dbhook.getEntity().getTransaction().begin();
	    for(int ik=0;ik<=allrecordlist.size()-1;ik++){
		    if(allrecordlist.get(ik).getId()==id){
			    id=ik;
		    }
	    }
	    
            dbhook.getEntity().remove(allrecordlist.get(id));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
          
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from JobJobscheduler t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
    }
    

    private void RefreshAllRecords(){
         
         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from JobJobscheduler t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
    
     public JobJobscheduler getDataRecord(long id){
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
    
    public JobJobscheduler getLastPosition(){
          log.debug("Function entry getLastPosition(");
             if( tabelIsEmpty!=true){ 
                 log.debug("Function exit getLastPosition");   
              return allrecordlist.get(currentRecordNumber);
             }
             log.debug("Function exit getLastPosition with Null");
        return null;
    }
     
    protected void changePosition(int jobjobid, int newPosition){
	     dbhook.getEntity().getTransaction().begin();
	    for(int ik=0;ik<=allrecordlist.size()-1;ik++){
		    if(allrecordlist.get(ik).getId()==jobjobid){
			    jobjobid=ik;
		    }
	    }
	    allrecordlist.get(jobjobid).setPosition(newPosition);
	    
            dbhook.getEntity().merge(allrecordlist.get(jobjobid));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
          
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from JobJobscheduler t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
	    
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from JobJobscheduler t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     }
    
     
     
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
            dbhook.getEntity().refresh(dbhook.getEntity().find(JobJobscheduler.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

	@Override
	public Long getId() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Jobs getIdJob() {
		if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getIdJob();
        }
        return null;
	}

	@Override
	public Jobscheduler getIdJobscheduler() {
		if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getIdJobscheduler();
        }
        return null;
	}

	@Override
	public Integer getPosition() {
		if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getPosition();
        }
        return null;
	}

	@Override
	public void setIdJob(Jobs idJob) {
		if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setIdJob(idJob);
	}

	@Override
	public void setIdJobscheduler(Jobscheduler idJobscheduler) {
		if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setIdJobscheduler(idJobscheduler);
	
	
	}

	@Override
	public void setPosition(Integer position) {
		if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setPosition(position);
	}
    
    
    
    
    
    




}
