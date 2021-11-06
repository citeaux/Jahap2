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

package org.jahap.gui.acc;


import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
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

import org.jahap.business.acc.AccountInfo;
import org.jahap.business.acc.accountsbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class AccListController implements Initializable {
    @FXML
    private Button PrintButton;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
     private  List ll;
   
    	private Hotelbean hbean;
    	private  SimpleDateFormat DFormat;
    	private  DecimalFormat DecFormatter;
    	  private  ResourcenManager jk;
  Logger log = LoggerFactory.getLogger(AccListController.class);
     
 
    
    private  accountsbean accbean;
	@FXML
	private Button newJob;
	@FXML
	private Button deleteJob;
	@FXML
	private Button editJob;
      
              
    
       private void initTable(){
    		hbean= new Hotelbean();
            DecFormatter= new DecimalFormat(hbean.getHotelNumberformat());
            
   DFormat= new SimpleDateFormat(hbean.getHoteldateformat());
          log.debug("Function entry initTable");
        
        accbean  = new accountsbean();
      
        ll=accbean.getAccOverview();
       ObservableList<AccountInfo> cc=FXCollections.observableList(ll);
   
        
//         -----------------  id
        TableColumn<AccountInfo,String> IdCol = new TableColumn<AccountInfo,String>("id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
//     dataTable.getColumns().add(IdCol);
    
      TableColumn<AccountInfo,String> arrival = new TableColumn<AccountInfo,String>(jk.getFxLangString("tableColumn.Arrivaldate"));
      arrival.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
    	@Override
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
    		SimpleStringProperty property = new SimpleStringProperty();
    		 if(p.getValue().getArrivaldate()!=null){
       		  property.setValue( DFormat.format(p.getValue().getArrivaldate())); 
       	  }else{
       		  
       	  }
    	 
         return property;
     }
     
             
      });  
      
      dataTable.getColumns().add(arrival);
      
       TableColumn<AccountInfo,String> departure = new TableColumn<AccountInfo,String>(jk.getFxLangString("tableColumn.Departuredate"));
      departure.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
      @Override	  
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
    	  SimpleStringProperty property = new SimpleStringProperty();
    	  if(p.getValue().getDeparturedate()!=null){
    		  property.setValue( DFormat.format(p.getValue().getDeparturedate())); 
    	  }else{
    		  
    	  }
    	  
         return property;
     }
     
             
      });  
      
      dataTable.getColumns().add(departure);
      
       TableColumn<AccountInfo,String> name = new TableColumn<AccountInfo,String>(jk.getFxLangString("tableColumn.Name"));
      name.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      dataTable.getColumns().add(name);
      
      
        TableColumn<AccountInfo,String> balance = new TableColumn<AccountInfo,String>(jk.getFxLangString("tableColumn.Balance"));
      balance.setCellValueFactory(new Callback<CellDataFeatures<AccountInfo, String>, ObservableValue<String>>() {
      @Override	  
     public ObservableValue<String> call(CellDataFeatures<AccountInfo, String> p) {
    	  SimpleStringProperty property = new SimpleStringProperty();
     
     	 property.setValue(DecFormatter.format(p.getValue().getBalance()));
     
    	  
         return property;
     }
     
             
      });  
      
      dataTable.getColumns().add(balance);
      
      
      dataTable.setItems(cc);
      log.debug("Function exit initTabel");
  }  
    
       
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
          log.debug("Function enter initialize");
          Resourcen kk= new Resourcen();
  	    jk=kk.getResourcenManager();
        
        
       
        
        initTable();
        log.debug("Function exit initialize");
    }    

    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        log.debug("Function enter MouseClicked");
        AccountInfo Ai=(AccountInfo) dataTable.getSelectionModel().getSelectedItem();
        int id;
        id= Integer.valueOf(Ai.getId());
    Stage stage = new Stage();
             ClassLoader classLoader = Thread.currentThread().getContextClassLoader(); 
       URL fxmlURL = classLoader.getResource("fxml/acc/simpelAccounting.fxml");
              Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.acc.i18n.simpelAccounting"));   

          AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        SimpelAccountingController controller= loader.<SimpelAccountingController>getController();
       controller.init(id);
        stage.setTitle(jk.getFxLangString("window.Title")) ;   
      
        stage.showAndWait();
        log.debug("Function exit MouseClicked");
    }

    @FXML
    private void OkAction(ActionEvent event) {
    }

    @FXML
    private void CancelAction(ActionEvent event) {
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
    
}
