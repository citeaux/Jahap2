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

package org.jahap.sreport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Language;
import org.jahap.entities.Reports;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class reportsbean  extends DatabaseOperations  implements reports_i{
   Logger log = LoggerFactory.getLogger(reportsbean.class);
     JahapDatabaseConnector dbhook;
    private static List<Reports> allrecordlist; 

    public reportsbean() {
               long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Reports t ORDER BY t.id");
            List<Reports>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Reports t ORDER BY t.id");
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
    
    
    @Override
    public void createNewEmptyRecord() {
         if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        Reports emptyroom = new Reports();
        
       
        allrecordlist.add(emptyroom);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected     
        
    }

    @Override
    public void nextRecordBackward() {
          if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
        
    }

    @Override
    public void nextRecordForeward() {
         if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
        
    }

    @Override
    public void saveRecord() {
                if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
        
    }
    
     private void saveOldRecord(){
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Reports.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
     
     public Reports getLastRecord(){
             return  allrecordlist.get(allrecordlist.size()-1);
        
    } 
     
    private void saveNewRecord(){
        if ( newEmptyRecordCreated==true){
            try{
             log.trace("persist");
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(currentRecordNumber));
            dbhook.getEntity().getTransaction().commit();

            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Reports t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
        }  
     
    public void  setDataRecordId(int id){
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
    
 public Reports SearchForReport(String reportname){
                if(!"".equals(reportname)){
                         for (Reports kk:allrecordlist){
                           if(kk.getName().equals(reportname)){
                               return kk;
                           }
        }                     
                }
        return null;
    }
 

 
 
 
 public List<Reports> SearchForReportgroup(String reportgroup){
             List<Reports> am= new ArrayList<>();   
             if(!"".equals(reportgroup)){
                         for (Reports kk:allrecordlist){
                           if(kk.getReportGroup().equals(reportgroup)){
                               am.add(kk);
                           }
        }                     
                }
        return am;
    }
    
  public List<String>SearchForReports(){
                   
            List<String>hh=new ArrayList<>();
            for (Reports kk:allrecordlist){
                     hh.add(kk.getName());
        }
            
            
            
        return hh;
    }
  
   public Reports getDataRecord(int id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return allrecordlist.get(currentRecordNumber);
   }    
 
   
   public void copyReport(int id){
       int inl=-1;
        try {
            do {
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
        }
       
        Reports nrp = new Reports();
        nrp.setLanguage(allrecordlist.get(inl).getLanguage());
        nrp.setName("New_" + allrecordlist.get(inl).getReportGroup() + "_report");
        nrp.setLanguage(allrecordlist.get(inl).getLanguage());
        nrp.setDescription(allrecordlist.get(inl).getDescription());
        // Report not set, has to be recompiled
        nrp.setReportGroup(allrecordlist.get(inl).getReportGroup());
        nrp.setReportLayout(allrecordlist.get(inl).getReportLayout());
        
        allrecordlist.add(nrp);
        currentRecordNumber=allrecordlist.size()-1;
        setNewEmptyRecordCreadted();
        log.trace(allrecordlist.get(currentRecordNumber).getName());
        saveNewRecord();
        
        
        
   }
   
   
   public void compileReport() throws JRException, IOException{ 
        InputStream is = new  ByteArrayInputStream(allrecordlist.get(currentRecordNumber).getReportLayout().getBytes());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
           JasperCompileManager.compileReportToStream(is, os);
       } catch (JRException jRException) {
           jRException.printStackTrace();
       }
        os.close();
        
        allrecordlist.get(currentRecordNumber).setReportStream(os.toByteArray());
        this.saveOldRecord();
   }
 
    public void quitDBaccess() {
        dbhook.getEntity().close();
    }

   
  

    @Override
    public String getDescription() {
     if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getDescription();
        }
        return "";
    }

    @Override
    public Integer getId() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getId();
        }
        return (int) 0;
    }

    @Override
    public String getName() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getName();
        }
        return "";
    }

    @Override
    public byte[] getReport() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getReport();
        }
        return null;
    }

    @Override
    public String getReportGroup() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getReportGroup();
        }
        return "";
        
        
    }

    @Override
    public String getReportLayout() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getReportLayout();
        }
        return null;
    }

    @Override
    public void setDescription(String description) {
       if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setDescription(description);
        
    }

    @Override
    public void setName(String name) {
            if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setName(name);
    }

    @Override
    public void setReport(byte[] report) {
          throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReportFile(File file) throws IOException {
       if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setReportFile(file);
    }
    
    
    

    @Override
    public void setReportGroup(String reportGroup) {
         if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setReportGroup(reportGroup);
      
    }

    

    

    @Override
    public Language getLanguage() {
       if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getLanguage();
        }
        return null;
    }

    @Override
    public void setLanguage(Language language) {
        if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setLanguage(language);
    }

    @Override
    public void setReportLayoutFile(String ReportLayout) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getStandardreport() {
        if( tabelIsEmpty!=true){ 
              return allrecordlist.get(currentRecordNumber).getStandardreport();
        }
        return null;
    }

    @Override
    public void setStandardreport(Boolean standardreport) {
        if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setStandardreport(standardreport);
    }

    @Override
    public void setReportLayout(String reportLayout) {
        if (tabelIsInit==false || tabelIsEmpty==true){
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        }
        allrecordlist.get(currentRecordNumber).setReportLayout(reportLayout);
    }
    
}
