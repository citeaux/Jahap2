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

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Rates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author russ
 */
public class resreports {
	Logger log = LoggerFactory.getLogger(resreports.class);
	Statement statement;
        ResultSet resultSet;

	public resreports() {
	}
	
	
	
    public void singleResReport(String resnumber) throws JRException, SQLException{
	   String querys="select * from Reservations t where t.resno='" + resnumber+ "'";
	        
	     Connection dbhook;
	       dbhook=JahapDatabaseConnector.GetClassicDbConnection();
	       
		statement = dbhook.createStatement();  
		resultSet = statement.executeQuery(querys);  
		JRResultSetDataSource resultSetDataSource = new  
		JRResultSetDataSource(resultSet);  
		
		
	        // Get Report form Database
	        reportsbean rbean=new reportsbean();

		HashMap<String, String> parameter =
		new HashMap<String, String>();
		parameter.put("aParameter", "Hallo Welt");
		
	        JasperPrint jp= new JasperPrint();
		ByteArrayInputStream bis = new ByteArrayInputStream(rbean.SearchForReport("Res").getReport());               try {
		    jp = JasperFillManager.fillReport(bis, new HashMap(), resultSetDataSource);
            
		    
		     //http://www.coderanch.com/t/523321/open-source/JasperReport-Dynamic-SQL-Query-queryString
		
		
		    
		} catch (JRException jRException) {
        }
                        
                        
                         
                
                          
                          
               
			
                    
			
    
      
    
      //jhjh.getContentPane().add(viewer);
       
       System.out.println("Hallo");
              
                        
               JasperViewer.viewReport(jp,false);
       
		//log.trace(resultSet.getArray("orderer_name"));
        
    } 
    
     public void multiRateReport(List<Rates>adlist) throws JRException{
          ratesDataSource adSource=new ratesDataSource(adlist);
        
        
          HashMap<String, String> parameter =
                new HashMap<String, String>();
          parameter.put("aParameter", "Hallo Welt");
          //Collection<Address> hjhj=adlist;
          //JRBeanCollectionDataSource hhh=new JRBeanCollectionDataSource(hjhj,true);
           
        
			
			JasperPrint jp= new JasperPrint();
                        reportsbean rbean=new reportsbean();
			       ByteArrayInputStream bis = new ByteArrayInputStream(rbean.SearchForReport("RateList").getReport());            
                    
                         
                        try {
            jp = JasperFillManager.fillReport(bis, new HashMap(), adSource);
            
        } catch (JRException jRException) {
        }
                        
                        
                         
                
                          
                          
               
			
                    
			
    
      
    
      //jhjh.getContentPane().add(viewer);
       
       System.out.println("Hallo");
              
                        
               JasperViewer.viewReport(jp,false);
        
        
    } 
    
    
    
 }
    
    
    
    
    

