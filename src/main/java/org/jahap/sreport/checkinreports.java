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


package org.jahap.sreport;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.jahap.entities.base.Address;
import org.jahap.entities.views.Checkin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */
public class checkinreports {
    
    Logger log = LoggerFactory.getLogger(checkinreports.class);

    /**
     *
     */
    public checkinreports() {
        
        
        
    }
    
    /**
     *
     * @param adlist
     * @throws JRException
     */
    public void CheckinReport(List<Checkin>adlist) throws JRException{
          checkinDataSource adSource=new checkinDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			  reportsbean rbean=new reportsbean();
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.SearchForReport("Checkin List").getReport());              
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        

    
      //jhjh.getContentPane().add(viewer);
       
      
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
    /**
     *
     * @param adlist
     * @param ReportID
     * @throws JRException
     */
    public void CheckinReport(List<Checkin>adlist, int ReportID) throws JRException{
           checkinDataSource adSource=new checkinDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			  reportsbean rbean=new reportsbean();
                          rbean.setDataRecordId(ReportID);
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.getReport());              
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        

    
      //jhjh.getContentPane().add(viewer);
       
     
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
    /**
     *
     * @param adlist
     * @throws JRException
     */
    public void RegCardReport(List<Checkin>adlist) throws JRException{
        log.trace("RegCardReport");
        
          checkinDataSource adSource=new checkinDataSource(adlist);
  
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			  reportsbean rbean=new reportsbean();
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.SearchForReport("Reg Card").getReport());              
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
             OutputStream os = null;
            log.debug("XXX" + jp.getName());
            JasperFillManager.fillReportToStream(bis, os, new HashMap(), adSource);
            log.trace(os.toString());
            
        } catch (JRException jRException) {
            jRException.printStackTrace();
        }
                        

    
      //jhjh.getContentPane().add(viewer);
       
      
              
                        
               //JasperViewer.viewReport(jp,false);
           
        viewReport(jp);
    } 
     
    /**
     *
     * @param adlist
     * @param ReportID
     * @throws JRException
     */
    public void RegCardReport(List<Checkin>adlist, int ReportID) throws JRException{
           checkinDataSource adSource=new checkinDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
			  reportsbean rbean=new reportsbean();
                          rbean.setDataRecordId(ReportID);
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.getReport());              
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        

    
      //jhjh.getContentPane().add(viewer);
       
     
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
    public void viewReport(JasperPrint jp){
          JFrame frame = new JFrame("Jasper report");
           JRViewer viewer=new JRViewer(jp);
          frame.add(viewer);
        frame.setSize(new Dimension(500, 400));
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    } 
    
 }
    
    
    
    
    

