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
package org.jahap.gui.base;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.jahap.entities.views.Dayclose;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.jobs.Dayclosebean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class DaycloseguiController implements Initializable, SearchResultListener {
	 Logger log = LoggerFactory.getLogger(DaycloseguiController.class);
	
	@FXML
	private TableView  jobtabel;
	@FXML
	private Button start;
	@FXML
	private Button addjob;
	@FXML
	private Button removejob;
	@FXML
	private Button cancel;
	@FXML
	private Button save;
	private  ResourcenManager jk;
        List<Dayclose>dclist;
	SearchResult jobSearchResult;
	Dayclosebean dayclose;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initTable();
		
	}	

	 private void initTable(){
        log.debug("Function entry initTable");
      
        dayclose = new Dayclosebean();
        dclist=dayclose.getListOfDaycloseJobs();
        ObservableList<Dayclose> data= FXCollections.observableList(dclist);
        
        
        // -----------------  id
        TableColumn<Dayclose,String> IdCol = new TableColumn<Dayclose,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Dayclose, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Dayclose, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- Job name ----------------------- 
    
        TableColumn<Dayclose,String> jobname = new TableColumn<Dayclose,String>("Job");
     jobname.setCellValueFactory(new Callback<CellDataFeatures<Dayclose, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Dayclose, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      jobtabel.getColumns().add(jobname );
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- Jobdef-id --------------------------------
      
       TableColumn<Dayclose,String> jobid = new TableColumn<Dayclose,String>("Floor");
     jobid.setCellValueFactory(new Callback<CellDataFeatures<Dayclose, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Dayclose, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getIdJob());
     }
     
             
      });
      
       
        //------------------------------------- Jobscheduler-id --------------------------------
      
       TableColumn<Dayclose,String> jobschedulerid = new TableColumn<Dayclose,String>("Floor");
     jobschedulerid.setCellValueFactory(new Callback<CellDataFeatures<Dayclose, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Dayclose, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getIdJobscheduler());
     }
     
             
      });
      
     
        //------------------------------------- Position number--------------------------------
      
       TableColumn<Dayclose,String> position = new TableColumn<Dayclose,String>("Floor");
    position.setCellValueFactory(new Callback<CellDataFeatures<Dayclose, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Dayclose, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getPosition());
     }
     
             
      });
    
     
        
   jobtabel.setItems(data);
        log.debug("Function exit initTable");
    }
	
	@FXML
	private void startDayclose(ActionEvent event) {
		dayclose.startDayclosJobs();
	}

	@FXML
	private void addjob(ActionEvent event) throws IOException {
		
		Stage stage = new Stage();
             
       
       Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/jobs/JobsList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.job.i18n.JobsList"));   
	    
	    
    
       
     
        AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Title"));       
	  
	  
	
	   

        
        stage.setScene(scene);
        joblistcontroller controller= loader.<joblistcontroller>getController();
       controller.init(jobSearchResult);
       
        
        stage.showAndWait();
	}

	@FXML
	private void removejob(ActionEvent event) {
	}

	@FXML
	private void cancel(ActionEvent event) {
	}

	@FXML
	private void save(ActionEvent event) {
	}

	@Override
	public void idinfo(SearchResultEvent e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@FXML
	private void TabelMouseclicked(MouseEvent event) throws IOException {
		long id;
		Dayclose ad=(Dayclose) jobtabel.getSelectionModel().getSelectedItem();
                id=Long.valueOf(ad.getId());
		
		Stage stage = new Stage();
            
       
          Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/jobs/JobConfigFx.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.jobs.i18n.JobConfigFx"));   
	    
	    
    
       
     
        AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
     
        stage.setTitle(jk.getFxLangString("window.Title"));
        
        stage.setScene(scene);
        JobsController controller= loader.<JobsController>getController();
       controller.init(id);
       
        
        stage.showAndWait();
		
	}
	
}
