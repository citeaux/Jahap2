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


package org.jahap.gui.base;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.addressbean;
import org.jahap.entities.base.Address;
import org.jahap.gui.HotelSetupController;
import org.jahap.gui.res.InterResSearchResult;
import org.jahap.gui.res.ResguiController;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.sreport.addressreports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class ListDialogAddressController implements Initializable{
    Logger log = LoggerFactory.getLogger(ListDialogAddressController.class);
    
    private addressbean addresses;
    private List searchlistAddresses; // All Records of 
    @FXML
    private Button PrintButton;
     @FXML
     private TableView dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private long id=0;
    private boolean isOverviewDialog=false;
   
    private SearchResult sk;
    private AddressSearchResult searchresult;
    private InterResSearchResult ResSearchresult;
    private String guisource;
    private  ResourcenManager jk;   
    
    
    private void initTable(){
        log.debug("Function entry initTable");
                
        addresses = new addressbean();
        searchlistAddresses=addresses.SearchForAddress("*");
        ObservableList<Address> data= FXCollections.observableList(searchlistAddresses);
        
        // -----------------  id
        TableColumn<Address,String> IdCol = new TableColumn<Address,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
        
     
        
        
       //----------------------------------- Christian Name  ----------------------- 
    
        TableColumn<Address,String> firstNameCol = new TableColumn<Address,String>(jk.getFxLangString("tableColumn.FirstName"));
      firstNameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getChristianname());
     }
     
             
      });  
        
      //TableColumn<Address, String> col1 = new TableColumn<Address, String>("Name");        
    //col1.setCellValueFactory(new PropertyValueFactory<Address, String>("Name"));  
        
      
      
      dataTable.getColumns().add(firstNameCol);
       //dataTable.getColumns().add(col1);
      
      //------------------------------------- Name --------------------------------
      
       TableColumn<Address,String> NameCol = new TableColumn<Address,String>(jk.getFxLangString("tableColumn.Name"));
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });
       dataTable.getColumns().add(NameCol);
       
       
       //---------------------------------- Street --------------------------------
        
         TableColumn<Address,String> StreetCol = new TableColumn<Address,String>(jk.getFxLangString("tableColumn.Street"));
      StreetCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(TableColumn.CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getStreet());
     }
     
             
      });
       dataTable.getColumns().add(StreetCol);
       
        //---------------------------------- City --------------------------------
        
         TableColumn<Address,String>CityCol = new TableColumn<Address,String>(jk.getFxLangString("tableColumn.City"));
      CityCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getCity());
     }
     
             
      });
       dataTable.getColumns().add(CityCol);
       
           //---------------------------------- ZipCode --------------------------------
        
         TableColumn<Address,String>ZipCol = new TableColumn<Address,String>(jk.getFxLangString("tableColumn.ZipCode"));
      ZipCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getZipcode());
     }
     
             
      });
       dataTable.getColumns().add(ZipCol);
       
       
         //---------------------------------- Phone --------------------------------
        
         TableColumn<Address,String>PhoneCol = new TableColumn<Address,String>(jk.getFxLangString("tableColumn.PhoneCol"));
      PhoneCol.setCellValueFactory(new Callback<CellDataFeatures<Address, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Address, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getPhone());
     }
     
             
      });
       dataTable.getColumns().add(PhoneCol);
        
    dataTable.setItems(data);
        log.debug("Function exit initTable");
    }
    
    
    
    /**
     * Initializes the controller class. Call from a Address Dialog,
     * Sets selected Address ID in Oberver
     */
    
 
	
    
    public void init(AddressSearchResult searchresults,AdressGuiFx zi){
        log.debug("Function entry init -address");
                
          Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
         isOverviewDialog=true;
         searchresult= new AddressSearchResult();
         searchresult=searchresults;
        initTable();
        log.debug("Function exit init -adddress");
    }
    
    public void init(SearchResult searchresults){
        log.debug("Function entry init -address");
             isOverviewDialog=false;   
          Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
         
        
         sk=searchresults;
        initTable();
        log.debug("Function exit init -adddress");
    }
    
    
    public void init(InterResSearchResult ResSearchresults,ResguiController zi,String guisource){
        log.debug("Function entry init -res");
         isOverviewDialog=true;
           Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
         this.ResSearchresult=ResSearchresults;
         this.guisource=guisource;
        initTable();
        log.debug("Function exit init -res ");
    }
    
    public void init(InterResSearchResult ResSearchresults,HotelSetupController zi,String guisource){
        log.debug("Function entry init -hotel");   
         isOverviewDialog=true;
           Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
         this.ResSearchresult=ResSearchresults;
         this.guisource=guisource;
        initTable();
        log.debug("Function exit init -hotel");
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("Function entry initialize");
	  Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
       searchresult= new AddressSearchResult();
       initTable();
        log.debug("Function exit initialize");
               
    }    

    @FXML
    private void PrintReport(ActionEvent event) {
        log.debug("Function entry PrintReport");
         List<Address> adl= new ArrayList<Address>();
        adl=searchlistAddresses;
        
        addressreports ARP = new addressreports();
        try {
            ARP.multiAdressReport(adl);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        log.debug("Function exit PrintReport ");
    }

    @FXML
    private void OkAction(ActionEvent event) throws IOException {
        log.debug("Function entry OkAction");
        if(isOverviewDialog==false){
        Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
        }
        if(isOverviewDialog==true){
             Address ad=(Address) dataTable.getSelectionModel().getSelectedItem();
                id=ad.getId();
            try {
                ResSearchresult.setDbRecordId(id, guisource);
            } catch (Exception e) {
            }
            Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
        }
        log.debug("Function exit OkAction");
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }

     @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        
     log.debug("Function entry MouseClicked");   
    
        
    Address ad=(Address) dataTable.getSelectionModel().getSelectedItem();
    id=ad.getId();
    if (isOverviewDialog==false){ 
        log.debug("no overview dialog");
        try {
            searchresult.setDbRecordId(id, "Address");
        } catch (Exception e) {
            log.debug(String.valueOf(e.getCause()));
        }
        try {
            sk.setDbRecordId(id, "Address");
        } catch (Exception e) {
            log.debug(String.valueOf(e.getCause()));
        }
    }
    
    
    
    if (event.getClickCount()==2){
		log.debug("double click / dialog call");
		Stage stage = new Stage();

	      Resourcen kk= new Resourcen();
		    jk=kk.getResourcenManager();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
		log.debug(String.valueOf(fxmlURL));
	       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));



		    AnchorPane page = (AnchorPane) loader.load();


		    Scene scene = new Scene(page);
		     stage.setScene(scene);
	            stage.setTitle(jk.getFxLangString("window.Title"));
	        AdressGuiFx controller= loader.<AdressGuiFx>getController();
	       controller.init(id);


		stage.show();


    }
        log.debug("Function exit MouseClicked");
       }
    }

   


    
    

