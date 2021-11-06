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
import org.jahap.entities.jobs.Jobs;
import org.jahap.jobs.Jobsbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class joblistcontroller implements Initializable {
	 Logger log = LoggerFactory.getLogger(joblistcontroller.class);
	@FXML
	private Button PrintButton;
	@FXML
	private TableView dataTable;
	@FXML
	private Button newJob;
	@FXML
	private Button deleteJob;
	@FXML
	private Button editJob;
	@FXML
	private Button Ok;
	@FXML
	private Button Cancel;
        private SearchResult jobsearchresult;
	Jobsbean jbean;
	List<Jobs> jobs;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initTable();
	}	

	private void initTable(){
        log.debug("Function entry initTable");
      
        jbean = new Jobsbean();
        jobs=jbean.SearchForJobs(null);
        ObservableList<Jobs> data= FXCollections.observableList(jobs);
        
        
        // -----------------  id
        TableColumn<Jobs,String> IdCol = new TableColumn<Jobs,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Jobs, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Jobs, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- Categorie name ----------------------- 
    
         TableColumn<Jobs,String> Jobtype = new TableColumn<Jobs,String>("Type");
      Jobtype.setCellValueFactory(new Callback<CellDataFeatures<Jobs, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Jobs, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getType());
     }
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(Jobtype );
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- Category Description --------------------------------
      
      TableColumn<Jobs,String> Jobname = new TableColumn<Jobs,String>("Name");
      Jobname.setCellValueFactory(new Callback<CellDataFeatures<Jobs, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Jobs, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
             
      });  
        
       dataTable.getColumns().add(Jobname);
       
       
       
      
        
    dataTable.setItems(data);
        log.debug("Function exit initTable");
    }
	
	public void init(SearchResult jobSearchResult){
		this.jobsearchresult=jobSearchResult;
	}
	
	@FXML
	private void PrintReport(ActionEvent event) {
	}

	@FXML
	private void MouseClicked(MouseEvent event) throws IOException {
		long id;
		Jobs ad=(Jobs) dataTable.getSelectionModel().getSelectedItem();
                id=ad.getId();
      //if(isOverviewDialog==false){searchresult.setDbRecordId(id, "Rooms");}
       
       if(event.getClickCount()==2){
             Stage stage = new Stage();
             String fxmlFile = "/fxml/jobs/JobConfigFx.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        JobsController controller= loader.<JobsController>getController();
       controller.init(id);
       
        
        stage.showAndWait();
      
      
       }
		
	}

	@FXML
	private void newJob(ActionEvent event) {
	}

	@FXML
	private void deleteJob(ActionEvent event) {
	}

	@FXML
	private void editJob(ActionEvent event) {
	}

	@FXML
	private void OkAction(ActionEvent event) {
		long id;
//	   if(isOverviewDialog==false){
//            Stage stage = (Stage) Ok.getScene().getWindow();
//            stage.close();
//        }
        
       //if(isOverviewDialog==true){
           Jobs ad=(Jobs) dataTable.getSelectionModel().getSelectedItem();
    id=ad.getId();
    
          try {
                jobsearchresult.setDbRecordId(id,"w");
            } catch (Exception e) {
            }
            Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
       //}
		
		
	}

	@FXML
	private void CancelAction(ActionEvent event) {
	}
	
}
