/*
 * The MIT License
 *
 * Copyright 2017 Open Jahap Community.
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

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.res.Res;
import org.jahap.entities.views.Checkin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author demokrite
 */
public class checkinbean {
 Logger log = LoggerFactory.getLogger(checkinbean.class);    JahapDatabaseConnector dbhook;
    private static List<Checkin> allrecordlist;
    private occbean occb;
    private accountsbean accb;
    private Hotelbean hotelb;
    private int numberOfLastRecord;
    private Query query;
    private int currentRecordNumber=0;
    private boolean tabelIsEmpty=true;
    private boolean tabelIsInit=false; 

    public checkinbean() {
        log.debug("Function entry constructor");
        Long testg;
         
        hotelb= new Hotelbean();
        occb= new occbean();
        
        dbhook = JahapDatabaseConnector.getConnector();
        
         
        try {
            query= dbhook.getEntity().createNamedQuery("Checkin.findByOccArrivaldate");
           query.setParameter("occArrivaldate", hotelb.getOperationdate()); // get Hoteldate
                   
            
            List<Checkin>allreslist= query.getResultList();
            numberOfLastRecord= allreslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query = dbhook.getEntity().createNamedQuery("Checkin.findByOccArrivaldate");
            query.setParameter("occArrivaldate", hotelb.getOperationdate()); // get Hoteldate
            allrecordlist= query.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getOccId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
        
        
        
        log.debug("Function exit constructor");
    }
    
    public List<Checkin> getCheckinList(){
        
         return allrecordlist;
        
        // -> error message if hoteldate is not equal to currentdate
    }
    
     public List<Checkin> getCheckinList(Date CheckinDate){
         
          try {
            query= dbhook.getEntity().createNamedQuery("Checkin.findByOccArrivaldate");
           query.setParameter("occArrivaldate", CheckinDate); // get Hoteldate
                   
            
            List<Checkin>allreslist= query.getResultList();
            numberOfLastRecord= allreslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query = dbhook.getEntity().createNamedQuery("Checkin.findByOccArrivaldate");
            query.setParameter("occArrivaldate", CheckinDate); // get Hoteldate
            allrecordlist= query.getResultList();
         
         
        
         return allrecordlist;
        
        // -> error message if hoteldate is not equal to currentdate
    }
    
    public void checkin(long occid){
          log.debug("Set Checkin");
          occb.getDataRecord(occid).setCheckin(true);
          occb.saveRecord(true);
          
    } 
     
     
     
   /* 
    public getOccCheckinList(){
        
    }
    
    public getAccCheckinList(){
        
    }
    
    public checkin(){
        
    }
    
    public checkinOCC(){
        
    }
    
    public checkinACC(){
        
    }
    
    */
   
}
