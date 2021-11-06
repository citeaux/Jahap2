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
package org.jahap.gui.res;

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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.MaintenanceBean;
import org.jahap.entities.views.Maintenance;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListMaintenanceController implements Initializable {
    Logger log = LoggerFactory.getLogger(ListMaintenanceController.class);
    @FXML
    private Button PrintButton;
    @FXML
    private TableView dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
     private  List<Maintenance> mList;
    private MaintenanceBean mbean;
    private  ResourcenManager jk;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
       initTable();
    }    

     void initTable(){
        log.debug("Function entry initTable");
        mbean= new MaintenanceBean();
        mList=mbean.getMaintenanceOverview();
        
	if(mList!=null){
	
         ObservableList<Maintenance> data= FXCollections.observableList(mList);
        
        
        // -----------------  id
        TableColumn<Maintenance,String> IdCol = new TableColumn<Maintenance,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Maintenance, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Maintenance, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- roomcode ----------------------- 
    
        TableColumn<Maintenance,String> roomcode = new TableColumn<Maintenance,String>(jk.getFxLangString("tableColumn.Room"));
     roomcode.setCellValueFactory(new Callback<CellDataFeatures<Maintenance, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Maintenance, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCode());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(roomcode);
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- roomlocation --------------------------------
      
       TableColumn<Maintenance,String> roomlocation = new TableColumn<Maintenance,String>(jk.getFxLangString("tableColumn.Floor"));
     roomlocation.setCellValueFactory(new Callback<CellDataFeatures<Maintenance, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Maintenance, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getFloor());
     }
     
             
      });
       dataTable.getColumns().add(roomlocation);
       
       
        //------------------------------------- roomcat --------------------------------
      
       TableColumn<Maintenance,String> roomcat = new TableColumn<Maintenance,String>(jk.getFxLangString("tableColumn.Category"));
      roomcat.setCellValueFactory(new Callback<CellDataFeatures<Maintenance, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Maintenance, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCatName());
     }
     
             
      });
       dataTable.getColumns().add( roomcat);
       
       //------------------------------------- mainetenacne state --------------------------------
      
       TableColumn<Maintenance,String> mblock = new TableColumn<Maintenance,String>(jk.getFxLangString("tableColumn.MaintanceRoomblock"));
      mblock.setCellValueFactory(new Callback<CellDataFeatures<Maintenance, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Maintenance, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getBlocks());
     }
     
             
      });
       dataTable.getColumns().add( mblock);
       
        //------------------------------------- mainetenacne state --------------------------------
      
       TableColumn<Maintenance,String> cleaningstate = new TableColumn<Maintenance,String>(jk.getFxLangString("tableColumn.MaintnanceState"));
       
      cleaningstate.setCellValueFactory(new Callback<CellDataFeatures<Maintenance, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Maintenance, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getNoMaintenance());
     }
     
             
      });
       dataTable.getColumns().add( cleaningstate);
       
       dataTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    dataTable.setItems(data);
	}
        log.debug("Function exit initTable");
        
        
    }
    
    @FXML
    private void PrintReport(ActionEvent event) {
    }

    @FXML
    private void setMaintenace(ActionEvent event) {
        log.debug("Function entry setMaintenace");
         ObservableList<Maintenance> rms=dataTable.getSelectionModel().getSelectedItems();
	 roomsbean rbean= new roomsbean();
        rbean.setRoomsinListunderMaintenanceMN(rms);
        log.debug("Function exit setMaintenace");  
    }

    @FXML
    private void setFree(ActionEvent event) {
        log.debug("Function entry setFree");
         ObservableList<Maintenance> rms=dataTable.getSelectionModel().getSelectedItems();
	 roomsbean rbean= new roomsbean();
        rbean.setRoomsinListNotunderMaintenanceMN(rms);
        log.debug("Function exit setFree");  
    }

    @FXML
    private void blockRoom(ActionEvent event) throws IOException {
        log.debug("Function entry blockRoom");
	  Maintenance rms=(Maintenance) dataTable.getSelectionModel().getSelectedItem();
	  
	  
	   Stage stage = new Stage();
       
         Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/res/MaintenanceGuiFx.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.MaintenanceGuiFx"));   
	    
	    
    
       
     
        AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Title"));       
	  
	  
	
	   

        
        stage.setScene(scene);
        maintenancegui controller;
		controller= loader.<maintenancegui>getController();
	if(rms!=null){
       controller.init(rms.getId(),true);
       	}else{
	controller.init(0,true);	
	}        
        stage.showAndWait();
         log.debug("Function exit blockRoom");  
    }

    @FXML
    private void MouseClicked(MouseEvent event) {
    }

    @FXML
    private void OkAction(ActionEvent event) {
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }
    
}
