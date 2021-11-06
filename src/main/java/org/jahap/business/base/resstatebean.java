/*The MIT License (MIT)
*
*Copyright (c) <year> <copyright holders>
*
*Permission is hereby granted, free of charge, to any person obtaining a copy
*of this software and associated documentation files (the "Software"), to deal
*in the Software without restriction, including without limitation the rights
*to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*copies of the Software, and to permit persons to whom the Software is
*furnished to do so, subject to the following conditions:
*
*The above copyright notice and this permission notice shall be included in
*all copies or substantial portions of the Software.
*
*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
*THE SOFTWARE.
*/


package org.jahap.business.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jahap.business.res.resstate_i;
//import org.jahap.entities.Country;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.ResState;
import org.jahap.entities.base.state;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sebastian Russ
 */


public class resstatebean extends DatabaseOperations implements resstate_i {

 JahapDatabaseConnector dbhook;
    private ResourcenManager jk;



    private static List<ResState> allrecordlist;
Logger log = LoggerFactory.getLogger(resstatebean.class);
    /**
     *
     */
    public resstatebean(){

         log.debug("Function entry resstatebean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();


        try {

            query_AllDbRecords = dbhook.getEntity().createQuery("select t from ResState t ORDER BY t.id");
            List<resstatebean>alladdresseslist= query_AllDbRecords.getResultList();
            log.trace("Amount of Records:" + String.valueOf(alladdresseslist.size()));
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }

        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  ResState t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();

        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }


        log.debug("Function entry resstatebean");

    }

    public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }
     public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }

    public List<ResState>SearchForresstate(String searchstring){

         log.debug("Function entry SearchForresstate");



        log.debug("Function exit SearchForresstate ");
        return allrecordlist;
    }

    public List<String>getAllResstate(){

        log.debug("Function entry SearchForresstate");



        log.debug("Function exit SearchForresstate ");
        return allrecordlist.stream().map(ResState -> ResState.getTranslation()).collect(Collectors.toList());
    }


       public void createNewEmptyRecord() {

          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<ResState>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;

        }

        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }

               ResState emptyacc = new ResState();


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

    private void RefreshAllRecords(){

         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from resstate t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }

         log.debug("Function exit RefreshAllRecords");
    }


     public ResState getDataRecord(long id){
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


    public ResState getRecordFromTranslatedState(String translatedstate){
        if(translatedstate==null)return null;
        log.debug("Function entry getRecordFromTranslatedState");
        int inl=-1;

        try {
            do {



                inl++;
            } while (allrecordlist.get(inl).getTranslationString() != translatedstate && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        log.debug("Function exit getRecordFromTranslatedState " + String.valueOf(currentRecordNumber) );
        return allrecordlist.get(currentRecordNumber);

    }

    public ResState getLastPosition(){
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

        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from resstate t ORDER BY t.id"); // Refresh list
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
            dbhook.getEntity().refresh(dbhook.getEntity().find(ResState.class,allrecordlist.get(currentRecordNumber).getId() ));


            dbhook.getEntity().getTransaction().commit();
        }

           log.debug("Function exit saveOldRecord");
    }

	
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setState(String state) {
		// TODO Auto-generated method stub
	   
	}

	@Override
	public String getStateType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStateType(String StateType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIsFree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIsFree(String isFree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTranslationString() {
		// TODO Auto-generated method stub
		return null;
	}


     

 





}
