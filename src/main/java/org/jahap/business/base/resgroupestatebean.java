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

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.ResGroupState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Sebastian Russ
 */


public class resgroupestatebean extends DatabaseOperations implements ResGroupeState_i {

 JahapDatabaseConnector dbhook;




    private static List<ResGroupState> allrecordlist;
Logger log = LoggerFactory.getLogger(resgroupestatebean.class);
    /**
     *
     */
    public resgroupestatebean(){

         log.debug("Function entry resgroupestatebean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();


        try {

            query_AllDbRecords = dbhook.getEntity().createQuery("select t from resgroupestate t ORDER BY t.id");
            List<resgroupestatebean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }

        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  resgroupestate t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();

        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }


        log.debug("Function entry resgroupestatebean");

    }

    public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }
     public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }

    public List<ResGroupState>SearchForresgroupestate(String searchstring){

         log.debug("Function entry SearchForresgroupestate");



        log.debug("Function exit SearchForresgroupestate ");
        return allrecordlist;
    }



       public void createNewEmptyRecord() {

          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<ResGroupState>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;

        }

        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }

        ResGroupState emptyacc = new ResGroupState();


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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from resgroupestate t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }

         log.debug("Function exit RefreshAllRecords");
    }


     public ResGroupState getDataRecord(long id){
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

    public ResGroupState getLastPosition(){
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from resgroupestate t ORDER BY t.id"); // Refresh list
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
            dbhook.getEntity().refresh(dbhook.getEntity().find(ResGroupState.class,allrecordlist.get(currentRecordNumber).getId() ));


            dbhook.getEntity().getTransaction().commit();
        }

           log.debug("Function exit saveOldRecord");
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
