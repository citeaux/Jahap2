package org.jahap.business.base;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Country;
import org.jahap.entities.base.Currency;
import org.jahap.entities.base.Hotel;
import org.jahap.entities.base.Language;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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


public class Hotelbean extends DatabaseOperations implements hotel_i {

 JahapDatabaseConnector dbhook;
	



    private static List<Hotel> allrecordlist;

    Logger log = LoggerFactory.getLogger(Hotelbean.class);


    /**
     *
     */
    public Hotelbean(){
       
         log.debug("Function entry hotelbean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Hotel t ORDER BY t.id");
            List<Hotelbean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Hotel t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          
        log.debug("Function exit hotelbean");    
        
    }
    
    /**
     *
     * @param searchstring
     * @return
     */
    public List<Hotel>SearchForHotel(String searchstring){
        
         log.debug("Function entry SearchForHotel");
      
         
        
        log.debug("Function exit SearchForHotel ");
        return allrecordlist;
    }  
    
    /**
     *
     */
    public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Hotel>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Hotel emptyacc = new Hotel();
        
       
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Hotel t ORDER BY t.id");
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
    public Hotel getDataRecord(long id){
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
    public Hotel getLastPosition(){
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Hotel t ORDER BY t.id"); // Refresh list
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
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Hotel.class,allrecordlist.get(currentRecordNumber).getId() );
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
    public Address getHotelAdress() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelAdress();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getHotelBankaccountdata1() {
         if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelBankaccountdata1();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getHotelBankaccountdata2() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelBankaccountdata2();
        }
        return null;
        
        
    }

    /**
     *
     * @return
     */
    @Override
    public String getHotelCode() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelCode();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Country getHotelCountry() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelCountry();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Currency getHotelCurrency() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelCurrency();
        }
        return null;
        
    }

    /**
     *
     * @return
     */
    @Override
    public String getHotelFootertext() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelFootertext();
        }
        return null;
                
    }

    /**
     *
     * @return
     */
    @Override
    public Language getHotelLanguage() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelLanguage();
        }
        return null;
        
    }

    /**
     *
     * @return
     */
    @Override
    public String getHotelName() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelName();
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getId() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getId();
        }
        return null;
    }

    /**
     *
     * @param hotelAdress
     */
    @Override
    public void setHotelAdress(Address hotelAdress) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setHotelAdress(hotelAdress);
    }

    /**
     *
     * @param hotelBankaccountdata1
     */
    @Override
    public void setHotelBankaccountdata1(String hotelBankaccountdata1) {
       if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        
        allrecordlist.get(currentRecordNumber).setHotelBankaccountdata1(hotelBankaccountdata1);
        
    }

    /**
     *
     * @param hotelBankaccountdata2
     */
    @Override
    public void setHotelBankaccountdata2(String hotelBankaccountdata2) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelBankaccountdata2(hotelBankaccountdata2);
        
    }

    /**
     *
     * @param hotelCode
     */
    @Override
    public void setHotelCode(String hotelCode) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
        allrecordlist.get(currentRecordNumber).setHotelCode(hotelCode);
        
    }

    /**
     *
     * @param hotelCountry
     */
    @Override
    public void setHotelCountry(Country hotelCountry) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelCountry(hotelCountry);
    }

    /**
     *
     * @param hotelCountry
     */
    public void setHotelCountry(int hotelCountry) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
         countrybean gg=new countrybean();
        allrecordlist.get(currentRecordNumber).setHotelCountry(gg.getDataRecord(hotelCountry));
    }
    
    /**
     *
     * @param hotelCurrency
     */
    @Override
    public void setHotelCurrency(Currency hotelCurrency) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelCurrency(hotelCurrency);
    }
    
    /**
     *
     * @param hotelCurrency
     */
    public void setHotelCurrency(int hotelCurrency) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        currencybean hh=new currencybean();
        allrecordlist.get(currentRecordNumber).setHotelCurrency(hh.getDataRecord(hotelCurrency));
    }

    /**
     *
     * @param hotelFootertext
     */
    @Override
    public void setHotelFootertext(String hotelFootertext) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelFootertext(hotelFootertext);
    }

    /**
     *
     * @param hotelLanguage
     */
    @Override
    public void setHotelLanguage(Language hotelLanguage) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelLanguage(hotelLanguage);
    }
    
    /**
     *
     * @param hotelLanguage
     */
    public void setHotelLanguage(int hotelLanguage) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        
        languagebean hh=new languagebean();
        allrecordlist.get(currentRecordNumber).setHotelLanguage(hh.getDataRecord(hotelLanguage));
    }

    /**
     *
     * @param hotelName
     */
    @Override
    public void setHotelName(String hotelName) {
        if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelName(hotelName);
    }

    /**
     *
     * @return
     */
    @Override
     public Date getOperationdate() {
		  if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getOperationdate();
        }
        return null;
		
	}

    /**
     * Do not use!
     * @param operationdate
     */

	@Override
    @Deprecated
	public void setOperationdate(Date operationdate) {
		throw new UnsupportedOperationException("Not supported yet."); 
		
		// can't be set by the gui!!!
	}

    /**
     * increments the operationdate by one day
     */


	public void incrementOperationdate(){
		LocalDate hotelday=LocalDate.fromDateFields(allrecordlist.get(currentRecordNumber).getOperationdate());
		allrecordlist.get(currentRecordNumber).setOperationdate(hotelday.plusDays(1).toDate());
		this.saveRecord();
		
		
	}

    /**
     *
     * @return
     */
    @Override
	public String getHotelNumberformat() {
		 if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHotelNumberformat();
        }
        return null;
	}

    /**
     *
     * @return
     */
    @Override
	public String getHoteldateformat() {
		if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getHoteldateformat();
        }
        return null;
	}

    /**
     *
     * @param hotelNumberformat
     */
    @Override
	public void setHotelNumberformat(String hotelNumberformat) {
		if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHotelNumberformat(hotelNumberformat);
	}

    /**
     *
     * @param hoteldateformat
     */
    @Override
	public void setHoteldateformat(String hoteldateformat) {
		if (tabelIsInit==false|| tabelIsEmpty==true)createNewEmptyRecord();
         
        allrecordlist.get(currentRecordNumber).setHoteldateformat(hoteldateformat);
	}
    
    
    
    
    




}
