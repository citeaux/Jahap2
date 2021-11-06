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
package org.jahap.gui.base;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.jahap.business.base.Locationbean;
import org.jahap.business.base.addressbean;
import org.jahap.entities.base.Location;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class LocationListController implements Initializable {
    Logger log = LoggerFactory.getLogger(LocationListController.class);
    @FXML
    private Button PrintButton;
    @FXML
    private TableView dataTable;
    private Locationbean locbean;
    private addressbean adbean;
    private List<Location>searchLocList;
      private  ResourcenManager jk;        
    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
              Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        initTable();
    }    

      private void initTable(){
        log.debug("Function entry initTable");
        adbean = new addressbean();
        locbean = new Locationbean();
        searchLocList=locbean.SearchForLocation(null);
        ObservableList<Location> data= FXCollections.observableList(searchLocList);
        
        
        // -----------------  id
        TableColumn<Location,String> IdCol = new TableColumn<Location,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Location, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Location, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- Building ----------------------- 
    
        TableColumn<Location,String> building = new TableColumn<Location,String>(jk.getFxLangString("tableColumn.Building"));
     building.setCellValueFactory(new Callback<CellDataFeatures<Location, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Location, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getBuilding());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(building );
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- Floor --------------------------------
      
       TableColumn<Location,String> floor = new TableColumn<Location,String>(jk.getFxLangString("tableColumn.Floor"));
     floor.setCellValueFactory(new Callback<CellDataFeatures<Location, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Location, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getFloor());
     }
     
             
      });
       dataTable.getColumns().add(floor);
       
       
       //---------------------------------- RevAdsress --------------------------------
        
      TableColumn<Location,String> address = new TableColumn<Location,String>(jk.getFxLangString("tableColumn.Address"));
     address.setCellValueFactory(new Callback<CellDataFeatures<Location, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Location, String> p) {
         return new ReadOnlyObjectWrapper(adbean.getDataRecord(p.getValue().getAddressId()).getName());
     }
             
      });
       dataTable.getColumns().add(address);
       
      
        
    dataTable.setItems(data);
        log.debug("Function exit initTable");
    }
    
    
    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
         log.debug("Function entry MouseClicked");
        Location reva=(Location) dataTable.getSelectionModel().getSelectedItem();
        short id;
        id=reva.getId();
       if(event.getClickCount()==2){
       Stage stage = new Stage();
       
         Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/setup/LocationGuiFx.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.setup.i18n.LocationGuiFx"));   
	    
	    
    
       
     
        AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Title"));

        
       
     

        
        stage.setScene(scene);
        locationFx controller= loader.<locationFx>getController();
       controller.init(id);
       
        
        stage.showAndWait();
        
        }
        log.debug("Function exit MouseClicked");
    }
    
}
