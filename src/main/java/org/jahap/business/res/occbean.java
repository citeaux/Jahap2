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



package org.jahap.business.res;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import jakarta.persistence.Query;
import org.jahap.business.base.Catbean;
import org.jahap.business.base.Locationbean;
import org.jahap.business.base.roomsbean;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Rooms;
import org.jahap.entities.res.Housekeepingblock;
import org.jahap.entities.res.Maintenanceblock;
import org.jahap.entities.res.Occ;
import org.jahap.entities.res.Res;
import org.jahap.entities.views.Housekeeping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russ
 */
public class occbean extends  DatabaseOperations implements occ_i{

    JahapDatabaseConnector dbhook;
    private static List<Occ> allrecordlist;
    private boolean createt=newEmptyRecordCreated;
    private roomsbean roomsb;
    private occcatbean occcat;
    Logger log = LoggerFactory.getLogger(occbean.class);
    /**
     *
     */
    public occbean(){
        log.debug("Function entry Contructor");
        long testg;
        roomsb=new roomsbean();
        occcat= new  occcatbean();
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t ORDER BY t.id");
            List<Occ>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t ORDER BY t.id");
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
        log.debug("Function exit Contructor");
    }
    
    private void RefreshAllRecords(){
        log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        log.debug("Function exit RefreshAllRecords");
    }

    /**
     * removes the record form the database
     *
     */
    private void removeCurrentRecordformDB(){
        log.debug("Function entry removeCurrentRecordformDB");

        currentRecordNumber=allrecordlist.size()-1;
        dbhook.getEntity().getTransaction().begin();
        dbhook.getEntity().remove(allrecordlist.get(currentRecordNumber));
        dbhook.getEntity().getTransaction().commit();
        RefreshAllRecords();
        log.debug("Function entry removeCurrentRecordformDB");


    }

    public Occ getOcc(){
        return allrecordlist.get(currentRecordNumber);
    }

    /**
     * removes current record from the working stack
     */
    public void dropOccSetup(){
        allrecordlist.remove(currentRecordNumber);
        RefreshAllRecords();
    }

    
    //----------------------- Search Function  ------------
        /**
     *
     * @param searchstring
     * @return
     */
    public List<Occ>SearchForOcc(String searchstring){
        log.debug("Function entry SearchForOcc");
                 
        return allrecordlist;
    }
    
    public List<Occ>SearchForOcc(Date fromDate, Date toDate){
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
        
	log.debug("Function entry" + fromDate.toString() + "   // " + toDate.toString());
       List<Occ> date;
       List<Occ> zwdate;
        query_AllDbRecords=dbhook.getEntity().createQuery("select t from Occ t where t.arrivaldate>='" + dt.format(fromDate) + "' AND t.departuredate<='"+ dt.format(toDate) + "' ORDER BY t.id");
	
        date=query_AllDbRecords.getResultList();
	
	query_AllDbRecords=dbhook.getEntity().createQuery("select t from Occ t where t.arrivaldate<='" + dt.format(fromDate) + "' AND t.departuredate>='"+ dt.format(fromDate) + "' AND t.departuredate<='"+ dt.format(toDate) + "' ORDER BY t.id");
	date.addAll(query_AllDbRecords.getResultList());
	
	query_AllDbRecords=dbhook.getEntity().createQuery("select t from Occ t where t.arrivaldate>='" + dt.format(fromDate) + "' AND t.arrivaldate<='"+ dt.format(toDate) + "' AND t.departuredate>='"+ dt.format(toDate) + "' ORDER BY t.id");
	date.addAll(query_AllDbRecords.getResultList());
	
	
	query_AllDbRecords=dbhook.getEntity().createQuery("select t from Occ t where t.arrivaldate<='" + dt.format(fromDate) + "' AND t.departuredate>='"+ dt.format(toDate) +  "' ORDER BY t.id");
	date.addAll(query_AllDbRecords.getResultList());
	
	
        date.forEach(i -> System.out.println(i.getArrivaldate().toString()+ " // " + i.getDeparturedate().toString() ));
                
                
        return date;
    }


    public List<Occ>SearchForHskBocksonThisRoom(long roomid){



        query_AllDbRecords=dbhook.getEntity().createQuery("select t  from Occ t, Rooms o where o.id=t.room AND o.id=" + String.valueOf(roomid) + " AND t.housekeeping>=1  ORDER BY t.id");
        List<Occ> rooms= query_AllDbRecords.getResultList();
        return rooms;
    }

    public List<Occ>SearchForMaintenanceBocksonThisRoom(long roomid){



        query_AllDbRecords=dbhook.getEntity().createQuery("select t  from Occ t join t.room o where t.maintenance<>0 AND o.id=" + String.valueOf(roomid) + " ORDER BY t.id");
        List<Occ> rooms= query_AllDbRecords.getResultList();
        return rooms;
    }


    public List<Occ>SearchForOccforHskBlock(Housekeepingblock hsk){
        log.debug("Function entry SearchForOccforHSkblock" + String.valueOf(hsk));
        List<Occ>hl=new ArrayList<Occ>();
        int ind=0;
        if(allrecordlist.size()>0) {
            for(ind=0;allrecordlist.size()>ind;ind++){

                try {
                    log.debug("Function entry xx" + String.valueOf(ind));
                    if (Objects.equals(allrecordlist.get(ind).getHousekeepingblock().getId(), hsk.getId())) {
                        hl.add(allrecordlist.get(ind));
                    }
                } catch (Exception e) {
                    log.error("No HSKblock for Occ " + String.valueOf(allrecordlist.get(ind).getId()) + " Record found");
                }

            }




            log.debug("Function exit SearchForOccforRes");
            return hl;
        }
        return null;
    }

    
    public void checkin(){
           allrecordlist.get(currentRecordNumber).setCheckin(true);
           allrecordlist.get(currentRecordNumber).setCheckinTimestamp();
    }
    
    
    public void checkout(){
           allrecordlist.get(currentRecordNumber).setCheckout(true);
           allrecordlist.get(currentRecordNumber).setCheckoutTimestamp();
    }
    
    public void checkin(List<Integer> checkins){
        for (Integer tt:checkins ){
                         
            allrecordlist.stream().filter(u -> u.getId().equals(tt)).findFirst().get().setCheckin(true);
            allrecordlist.stream().filter(u -> u.getId().equals(tt)).findFirst().get().setCheckinTimestamp();
             
        }
    }


    public List<Occ>SearchForOccforMaintenanceBlock(Maintenanceblock hsk){
        log.debug("Function entry SearchForOccforMaintananceblock" + String.valueOf(hsk));
        List<Occ>hl=new ArrayList<Occ>();
        int ind=0;
        if(allrecordlist.size()>0) {
            for(ind=0;allrecordlist.size()>ind;ind++){

                try {
                    log.debug("Function entry xx" + String.valueOf(ind));
                    if (Objects.equals(allrecordlist.get(ind).getMaintenanceblock().getId(), hsk.getId())) {
                        hl.add(allrecordlist.get(ind));
                    }
                } catch (Exception e) {
                    log.error("No HSKblock for Occ " + String.valueOf(allrecordlist.get(ind).getId()) + " Record found");
                }

            }




            log.debug("Function exit SearchForOccforRes");
            return hl;
        }
        return null;
    }





    public List<Occ>SearchForOccforRes(Res res){
         log.debug("Function entry SearchForOccforRes" + String.valueOf(res));
        List<Occ>hl=new ArrayList<Occ>();
    int ind=0;
      if(allrecordlist.size()>0) {
          for(ind=0;allrecordlist.size()>ind;ind++){
              
              try {
		      log.debug("Function entry xx" + String.valueOf(ind));
			  if (Objects.equals(allrecordlist.get(ind).getRes().getId(), res.getId())) {
				  hl.add(allrecordlist.get(ind));
			  }
		  } catch (Exception e) {
			  log.error("No Reservation for Occ " + String.valueOf(allrecordlist.get(ind).getId()) + " Record found");
		  }
              
          }
           
        
        
      
         log.debug("Function exit SearchForOccforRes");
        return hl;
    }
      return null;
     }
        //-----------------------------------------------
    
     public List<Rooms>searchforfreerooms(LocalDate from, LocalDate to){
	     log.debug("Function entry searchforfreerooms From: " + from.format(DateTimeFormatter.ISO_DATE) + " to: " + to.format(DateTimeFormatter.ISO_DATE));
	   
	     Query rooms;
	     List<Object[]>jj=null;
	     List<Rooms>allfreerooms = new ArrayList<Rooms>();
	     try {

		     
		     
            rooms = dbhook.getEntity().createNativeQuery("select  distinct t.id,t.category,t.code,t.name,t.cat,t.location,t.clean,t.no_maintenance from Rooms t, Occ k where (t.id=k.room and (k.arrivaldate>'" + from.format(DateTimeFormatter.ISO_DATE) + "' or k.departuredate<'" + to.format(DateTimeFormatter.ISO_DATE)+ "'))");
	    jj=rooms.getResultList();
	    Catbean gg=new Catbean();
	    Locationbean jkl=new Locationbean();
	    
	    Object[] rol=null;
	    Rooms kk=null;
            Iterator itr = jj.iterator();
	    while(itr.hasNext()){
		  kk=new Rooms(); 
		   rol=(Object[])itr.next();
                   kk.setId((long)rol[0]);
		   kk.setCategory(gg.getDataRecord((Integer)rol[4]));
		   kk.setClean((Boolean)rol[6]);
		   kk.setCode((String)rol[2]);
	           kk.setName((String)rol[3]);
		   kk.setLocation(jkl.getDataRecord((Integer)rol[5]));
		   kk.setNo_maintenance((Boolean)rol[7]);
		   allfreerooms.add(kk);
	    }
	    
            numberOfLastRecord= allfreerooms.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
	    e.printStackTrace();
        }
	    return allfreerooms;
     } 
     
     
    /**
     *
     */
    public void createNewEmptyRecord() {
        
        log.debug("Function entry createNewEmptyRecord");
        if(tabelIsEmpty==true){
		log.trace(String.valueOf(tabelIsEmpty==true));
            allrecordlist = new ArrayList<Occ>();
            //numberOfLastRecord++;
        }else if(tabelIsEmpty==false){
            log.trace("tabelIsEmpty==false");
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        /*
         if(numberOfLastRecord==-1){
            
            numberOfLastRecord++;
            
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         } */
        Occ emptyocc = new Occ();
        
       
        allrecordlist.add(emptyocc);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
        log.debug("Function exit createNewEmptyRecord");
        
    }
    
   // ####################### Validation ######################     
        private boolean validateOverlappAtEnd(int depdate){
            return true;
        }
    
        private boolean validateOverlappAtBegin(int depdate){
            return true;
        }
        
        private boolean validateOverlappInMid(int depdate){
            return true;
        }
        
        private  List<Occ>validateRoom(List<Occ>vrooms){
            log.debug("Function entry validateRoom");
            boolean entryremoved=false;
            int cont=0;
            
            do{
              // Remove every record which is after resbooking
              if(!vrooms.isEmpty()) {
                  if (allrecordlist.get(currentRecordNumber).getArrivaldate().before(vrooms.get(cont).getArrivaldate())) {
                      if (allrecordlist.get(currentRecordNumber).getDeparturedate().before(vrooms.get(cont).getArrivaldate())) {
                          vrooms.remove(cont);
                          entryremoved = true;
                      }
                  } else if ((allrecordlist.get(currentRecordNumber).getRes() != null)&& (allrecordlist.get(currentRecordNumber).getRes().getState().getIsFree().equals("yes"))  ) {


                          vrooms.remove(cont);
                          entryremoved = true;


                  } else if (allrecordlist.get(currentRecordNumber).getArrivaldate().after(vrooms.get(cont).getDeparturedate())) {
                      if (allrecordlist.get(currentRecordNumber).getDeparturedate().after(vrooms.get(cont).getDeparturedate())) {
                          vrooms.remove(cont);
                          entryremoved = true;

                      }
                  } else if (allrecordlist.get(currentRecordNumber).getArrivaldate().equals(vrooms.get(cont).getDeparturedate())) {
                      if (allrecordlist.get(currentRecordNumber).getArrivaltime().after(vrooms.get(cont).getDeparturetime())) {
                          vrooms.remove(cont);
                          entryremoved = true;
                      }
                  } else if (allrecordlist.get(currentRecordNumber).getDeparturedate().equals(vrooms.get(cont).getArrivaldate())) {
                      if (allrecordlist.get(currentRecordNumber).getDeparturetime().after(vrooms.get(cont).getArrivaltime())) {
                          vrooms.remove(cont);
                          entryremoved = true;

                      }
                  }
              }

             if(entryremoved==false) {
                 cont++;

             }else if(entryremoved==true){
                 cont=0;
                 entryremoved=false;

                }
               
              }while(cont<vrooms.size());
             log.debug("Function exit validateRoom");
            return vrooms;
              
        }
            
       
    /**
     * checks if there are reservations which blocking the room of the current record at
     * the same time
     *
     * the check runs again the record in focus
     *
     * @return List<String>Resnumbers
     */
    public List<String>CheckForOverlappingReservations(){
           log.debug("Function entry checkForOverlappingReservations");
             List<Occ>vrooms=new ArrayList<Occ>();
            List<String>overlapping=new ArrayList<String>();
            int count=0;
            
            if(!tabelIsEmpty && allrecordlist.size()>2){ // at least 2 occ record should be in the db in order to check for overlappings
                    do{
                        if(allrecordlist.get(count).getId()!=null){
                                if(allrecordlist.get(count).getRoom().getCode()==allrecordlist.get(currentRecordNumber).getRoom().getCode()){
                                      vrooms.add(allrecordlist.get(count));
                                }
                        }
                        count++;
                    }while(count<allrecordlist.size()-1);
                 if(!vrooms.isEmpty())
                    validateRoom(vrooms);  // calls the Validation for the room
                 
            }


               
            
             if(!vrooms.isEmpty() ){
                 
               if((vrooms.size()==1) ||(tabelIsEmpty) ){
                  return null;   
                }
                 
             int col=0;
             do{
                
                overlapping.add(vrooms.get(col).getRes().getResno());
                col++;  
             }while(col<vrooms.size()-1);
             RefreshAllRecords();
            return overlapping;
        }
             log.debug("Function exit checkForOverlappingReservations");
             return null;
            
        }
        
 //  ###########################   Rec Ops ##############       
        
        private List<String>saveNewRecord(){
             // TODO: the record to validat might not be saved -> causes validation Problem
             // TODO: all overlappings are found -> where to log? Validation focus needed?
            log.debug("Function entry saveNewRecord");
            
            List<Occ>vrooms=new ArrayList<Occ>();
            List<String>overlapping=new ArrayList<String>();
            int count=-1;
            if(!tabelIsEmpty) {

                for (Occ anAllrecordlist : allrecordlist) {
                    Occ kl;
                    kl = anAllrecordlist;
                    if (kl.getId() != null) { // picks all occrecords with that current room
                        if (kl.getRoom().getId() == allrecordlist.get(currentRecordNumber).getRoom().getId()) {
                            vrooms.add(kl);
                        }
                    }
                }
//                    do{
//                        count++;
//                       if(allrecordlist.get(count).getId()!=null){
//                                if(allrecordlist.get(count).getRoom().getId()==allrecordlist.get(currentRecordNumber).getRoom().getId()){
//                                      vrooms.add(allrecordlist.get(count));
//                                }
//                       }
//
//                    }while(count<=allrecordlist.size()-1);

                     if(!vrooms.isEmpty())
                    validateRoom(vrooms);
            }
            
        if((vrooms.isEmpty()) ||(tabelIsEmpty) ){
           
               //------------------------------

                if ( newEmptyRecordCreated==true){
                    try{
                    dbhook.getEntity().getTransaction().begin();
                    dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
                    dbhook.getEntity().getTransaction().commit();
                    // *****  set occ in occcat table ***
                    occcat.createNewEmptyRecord();
                    occcat.setArrivaldate(allrecordlist.get(currentRecordNumber).getArrivaldate());
                    occcat.setDeparturedate(allrecordlist.get(currentRecordNumber).getDeparturedate());
                    occcat.setCat(allrecordlist.get(currentRecordNumber).getRoom().getCategory());
                    occcat.setOcc(allrecordlist.get(currentRecordNumber));
                    occcat.setRes(allrecordlist.get(currentRecordNumber).getRes());
                    
                    //*********
                    newEmptyRecordCreated=false;
                    tabelIsEmpty=false;
                    occcat.saveRecord();
                    }
                    catch (Exception e){
                          newEmptyRecordCreated=false;
                          occcat.removeLatestRecord();
                          allrecordlist.remove(currentRecordNumber);
                          RefreshAllRecords(); // Reloads List with all Records

                    }
                }
                 return null;
           }
        
        if(vrooms.size()!=0){
             int col=0;
             do{
                overlapping.add(vrooms.get(col).getRes().getResno());
                col++;
             }while(col<vrooms.size());
              RefreshAllRecords();
            return overlapping;
        }
           RefreshAllRecords();
            log.debug("Function exit saveNewRecord");
        return overlapping;
        }
    
      private void saveOldRecord(){
          log.debug("Function entry saveOldRecord ");
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Occ.class,allrecordlist.get(currentRecordNumber).getId() );
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            
            dbhook.getEntity().getTransaction().commit();
            // *****  set occ in occcat table ***
                    
        }
          log.debug("Function exit saveOldRecord");
        
    }     
    

    /**
     *
     */
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
    public void nextRecordForeward() {
        log.debug("Function entry nextRecordForeward");
        
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
        
        log.debug("Function exit nextRecordForeward");
    }
    /**
     *
     */
    
    /**
     *
     * @return
     */
   @Deprecated
   @Override 
   public void saveRecord(){}
    
    public List<String>saveRecord(boolean test){
       
        log.debug("Function entry saveRecord");
         if (newEmptyRecordCreated==false){
		 log.trace("newEmptyRecordCreated==false");
          saveOldRecord();    // Validation for old Record is needed
      }
        
         List<String>hh=new ArrayList<String>();
        System.out.println(" ->:" + String.valueOf(this.newEmptyRecordCreated));
        
         if (newEmptyRecordCreated==true){
		 log.trace("newEmptyRecordCreated==true");
            if(!tabelIsEmpty){    // Validate Date only if there are Records
                  hh=saveNewRecord();
                  
                 try {
                    if(hh!=null){
                         if (!hh.isEmpty()) {


				     log.trace(String.valueOf(hh.size()));
                                RefreshAllRecords(); // Reload Data from Database
                                return hh;


                        }
                        if (hh.isEmpty()) {
				 log.trace(String.valueOf(hh.size()));
                            setNewEmptyRecordSaved();
                            return null;
                        }
                    }
                } catch (Exception e) {
                       e.printStackTrace();  
                }
                  }
                  
                  
                  
            }
            
            if(tabelIsEmpty){    // Just create a Record if Table is empty
                 saveNewRecord();
                        setNewEmptyRecordSaved();
            }
          
     
        log.debug("Function exit saveRecord");
      return null;
    }
    
      /**
     *
     * @return
     */
    public Long getId(){
        
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return (long) 0;
   }
    
       
       /**
     *
     * @param id
     */
    public void  setDataRecordId(Long id){
        int inl=-1;
        
           try {
               do {
                   
                   currentRecordNumber = inl;
                   
                   inl++;
               } while (allrecordlist.get(currentRecordNumber).getId() != id && allrecordlist.size() - 1 > inl);
               currentRecordNumber = inl;
           } catch (Exception e) {
               e.printStackTrace();   
           }
   }
       
    /**
     *
     * @param id
     * @return
     */
    public Occ getDataRecord(long id){
       int inl=-1;
        
        try {
           do {
               
               
               
               inl++;
           } while (allrecordlist.get(currentRecordNumber).getId() != id && allrecordlist.size() - 1 > inl);
           currentRecordNumber = inl;
       } catch (Exception e) {
           e.printStackTrace();   
       }
      return  allrecordlist.get(currentRecordNumber);
   }    
       
   // ------------------------------------- Getters --------------------- 


    /**
     *
     * @return
     */
    public Date getArrivaltime() {
         // if( tabelIsEmpty!=true) 
              // return allrecordlist.get(currentRecordNumber).getArrivaltime();
        return null;
    }

 

    /**
     *
     * @return
     */
    public Date getDeparturetime() {
        //if( tabelIsEmpty!=true) 
              //return allrecordlist.get(currentRecordNumber).getDeparturetime();
        return null;
    }

   

    /**
     *
     * @return
     */
    public Res getRes() {
        Res test=null;
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRes();
        return test;
    }

    /**
     *
     * @return
     */
   

    /**
     *
     * @return
     */
    public Rooms getRoom() {
         Rooms test=null; 
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRoom();
        return test;
    }

    
     /**
     *
     * @return
     */
    public Date getArrivaldate() {
      if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getArrivaldate();
       return null;
    }

    /**
     *
     * @return
     */
    public Date getDeparturedate() {
        if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getDeparturedate();
       return null;
    }
    


    
   

 
   
  

    /**
     *
     * @param res
     */
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

    

    /**
     *
     * @param room
     */
    public void setRoom(Rooms room) {
	    log.debug("Function entry setRoom");
		    
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setRoom(room);
            }
        }
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setRoom(room);
    }
      log.debug("Function exit setRoom");
    }
   
    /**
     *
     * @param arrivaldate type date
     */

    @Override 
    public void setArrivaldate(Date arrivaldate){
	    log.debug("Function entry setArrivaldate");
         if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setArrivaldate(arrivaldate);
                     setArrivaltime("12:01");
            }
         }
         if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setArrivaldate(arrivaldate);
            setArrivaltime("12:01");
         }  
	    log.debug("Function entry setArrivaldate");
    }
    /**
     *
     * @param arrivaldate dd.MM.yyyy
     */
    public void setArrivaldate(String arrivaldate) {
	    log.debug("Function entry setArrivaldate");
            Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
        try {
            dt=df.parse(arrivaldate);
        } catch (ParseException ex) {
            log.error(String.valueOf(ex));
        }
           if(tabelIsInit==false || tabelIsEmpty==true){
                    if(newEmptyRecordCreated!=true){
                        createNewEmptyRecord();
                        allrecordlist.get(currentRecordNumber).setArrivaldate(dt);
                        setArrivaltime("12:01");
                    }
           }
             if(tabelIsInit==true || tabelIsEmpty==false){
                    allrecordlist.get(currentRecordNumber).setArrivaldate(dt);
                        setArrivaltime("12:01");
             }       
          log.debug("Function exit setArrivaldate");          
    }

    /**
     *
     * @param departuredate
     */
    
    @Override public void setDeparturedate(Date departuredate){
	    log.debug("Function entry setDeparturedate");
          if (tabelIsInit==false || tabelIsEmpty==true){
                if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setDeparturedate(departuredate);
            setDeparturetime("12:00");
                }
          }
            if(tabelIsInit==true || tabelIsEmpty==false){
                 allrecordlist.get(currentRecordNumber).setDeparturedate(departuredate);
            setDeparturetime("12:00");
            }
     log.debug("Function exit setDeparturedate");
    }
    /**
     *
     * @param departuredate dd.MM.yyyy
     */
    public void setDeparturedate(String departuredate) {
	    log.debug("Function entry setDeparturedate");
         Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
        try {
            dt=df.parse(departuredate);
        } catch (ParseException ex) {
            log.error(String.valueOf(ex));
        }
        
        
         if (tabelIsInit==false || tabelIsEmpty==true){
                if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setDeparturedate(dt);
                    setDeparturetime("12:00");
                }
            }
         if(tabelIsInit==true || tabelIsEmpty==false){
             allrecordlist.get(currentRecordNumber).setDeparturedate(dt);
                    setDeparturetime("12:00");
         log.debug("Function exit setDeparturedate");
    }
    }
    /**
     *
     * @param arrivaltime
     */
    @Override 
    public void setArrivaltime(Date arrivaltime){
	    
    }
    
    /**
     *
     * @param arrivaltime
     */
    public void setArrivaltime(String arrivaltime){
        Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "HH:mm" );
        try {
            dt=df.parse(arrivaltime);
        } catch (ParseException ex) {
            log.error(String.valueOf(ex));
        }
        
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setArrivaltime(dt);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setArrivaltime(dt);
        }
    }

    /**
     *
     * @param departuretime
     */
    @Override public void setDeparturetime(Date departuretime) {}
    /**
     *
     * @param departuretime
     */
    public void setDeparturetime(String departuretime) {
        Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "HH:mm" );
        try {
            dt=df.parse(departuretime);
        } catch (ParseException ex) {
            log.error(String.valueOf(ex));
        }
        
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                    createNewEmptyRecord();
                    allrecordlist.get(currentRecordNumber).setDeparturetime(dt);
            }
        }
         if(tabelIsInit==true || tabelIsEmpty==false){
              allrecordlist.get(currentRecordNumber).setDeparturetime(dt);
         }
    }

    public Address getGuest() {
        Address test=null;
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getGuest();
        return test;
    }

    public void setGuest(Address guest) {
         if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setGuest(guest);
            }
         }
           if(tabelIsInit==true || tabelIsEmpty==false){
                allrecordlist.get(currentRecordNumber).setGuest(guest);
           } 
            
    }

    public Accounts getAccount() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccount();
        return null;
    }

    public void setAccount(Accounts account) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setAccount(account);
            }
         }
           if(tabelIsInit==true || tabelIsEmpty==false){
                allrecordlist.get(currentRecordNumber).setAccount(account);
           } 
    }

	@Override
	public Housekeepingblock getHousekeepingblock() {
		if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getHousekeepingblock();
        return null;
	}

	@Override
	public Maintenanceblock getMaintenanceblock() {
		if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getMaintenanceblock();
        return null;
	}

    @Override
    public int getPax() {
        if( tabelIsEmpty!=true)
            return allrecordlist.get(currentRecordNumber).getPax();
        return 0;
    }

    @Override
    public void setPax(int pax) {
        if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setPax(pax);
            }

        }

        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setPax(pax);
        }
    }

    @Override
	public void setHousekeepingblock(Housekeepingblock housekeepingblock) {
		if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setHousekeepingblock(housekeepingblock);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setHousekeepingblock(housekeepingblock);
        }
		
		
	}

	@Override
	public void setMaintenanceblock(Maintenanceblock maintenanceblock) {
		if(tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true){
                createNewEmptyRecord();
                allrecordlist.get(currentRecordNumber).setMaintenanceblock(maintenanceblock);
            }
           
        }
        
        if(tabelIsInit==true || tabelIsEmpty==false){
            allrecordlist.get(currentRecordNumber).setMaintenanceblock(maintenanceblock);
        }
	}

    @Override
    public Boolean getCheckin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCheckinTimestamp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getCheckout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getCheckoutTimestamp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCheckin(Boolean checkin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCheckinTimestamp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCheckout(Boolean checkout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCheckoutTimestamp() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
