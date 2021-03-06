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



package org.jahap.gui.res;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import net.sf.jasperreports.engine.JRException;

import org.jahap.business.base.Hotelbean;
import org.jahap.business.res.resbean;
import org.jahap.entities.res.Res;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.sreport.ratereports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class ResListController implements Initializable {
    
    Logger log = LoggerFactory.getLogger(ResListController.class);
    @FXML
    private Button PrintButton;
    @FXML
    private TableView  dataTable;
    @FXML
    private Button Ok;
    @FXML
    private Button Cancel;
    private resbean res;
    private List DialogResSearchResult;
    private long id=0;
	private Hotelbean hbean;
	 private  SimpleDateFormat DFormat;
    /**
     * Initializes the controller class.
     */
    private boolean isOverviewDialog=false;
    ResSearchResult searchresult;
    private  ResourcenManager jk;
    
    private void initTable(){
    	
    	log.debug("Enter initTable");
        res= new resbean();
        
    	hbean= new Hotelbean();
    	DFormat= new SimpleDateFormat(hbean.getHoteldateformat());
    	log.trace(DFormat.getDateFormatSymbols().toString());
    	DialogResSearchResult= res.SearchForReservations("*");
        ObservableList<Res> data= FXCollections.observableList(DialogResSearchResult);
        
        // -----------------  id
        TableColumn<Res,String> IdCol = new TableColumn<Res,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Res, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Res, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
      //dataTable.getColumns().add(IdCol);
      
       // -----------------  Arrival
        TableColumn<Res,String> ResNoCol = new TableColumn<Res,String>(jk.getFxLangString("tableColumn.ResNo"));
     ResNoCol.setCellValueFactory(new Callback<CellDataFeatures<Res, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Res, String> p) {
       return new ReadOnlyObjectWrapper(p.getValue().getResno());
    }
   
            
     });  
    
      dataTable.getColumns().add(ResNoCol);
     
      
      // -----------------  Arrival

      
      TableColumn<Res,String> ArrDateCol = new TableColumn<Res,String>(jk.getFxLangString("tableColumn.Arrival"));
      ArrDateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Res, String>, ObservableValue<String>>() {
    @Override	  
     public ObservableValue<String> call(CellDataFeatures<Res, String> p) {
    	 SimpleStringProperty property = new SimpleStringProperty();
    	 if(p.getValue().getArrivaldate()!=null){
    	 property.setValue( DFormat.format(p.getValue().getArrivaldate()));
    	 }else{
    		 property.setValue("Error"); 
    	 }
    	 return property;
         // return new ReadOnlyObjectWrapper(  DFormat.format(p.getValue().getArrivaldate()));
     }
     
             
      });  
      
      dataTable.getColumns().add(ArrDateCol);
      
      
      
       // -----------------  Name
        TableColumn<Res,String> NameCol = new TableColumn<Res,String>(jk.getFxLangString("tableColumn.GuestName"));
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Res, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Res, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getAddressid().getName());
     }
     
             
      });  
      
      dataTable.getColumns().add(NameCol);
      
       // -----------------  Christianname
        TableColumn<Res,String> CNameCol = new TableColumn<Res,String>(jk.getFxLangString("tableColumn.GuestFirstName"));
      CNameCol.setCellValueFactory(new Callback<CellDataFeatures<Res, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Res, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getAddressid().getChristianname());
     }
     
      
             
      });  
      
      dataTable.getColumns().add(CNameCol);
      
      
        
       dataTable.setItems(data);
            
       log.debug("Exit initTable");
    
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    
	     Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        searchresult=new ResSearchResult();
        initTable();
   }    

    @FXML
    private void PrintReport(ActionEvent event) throws JRException {
        List<Res> jj=new ArrayList<Res>();
        jj=res.SearchForReservations("*");
        ratereports hh=new ratereports() ;
        //hh.multiRateReport(jj);
        
    }

    @FXML
    private void MouseClicked(MouseEvent event) throws IOException {
        
       
         Res ad=(Res) dataTable.getSelectionModel().getSelectedItem();
         id=ad.getId();  
         System.out.println("---->"+event.getClickCount());
         if (isOverviewDialog==false){ searchresult.setDbRecordId(id, "res");}
        
     
       if (event.getClickCount()==2){
       
	 Stage stage = new Stage();
       
         Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/res/resgui.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.resgui"));   
	    
	    
    
       
     
        AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Title"));       
	       
	       
	       
	       
     

        
        stage.setScene(scene);
      ResguiController controller;
      controller = loader.<ResguiController>getController();
            log.debug("Function entry" + id);
            
      controller.init(id);
       
        
        stage.showAndWait();
    }
    }

    @FXML
    private void OkAction(ActionEvent event){
    }

    @FXML
    private void CancelAction(ActionEvent event) {
    }
}
