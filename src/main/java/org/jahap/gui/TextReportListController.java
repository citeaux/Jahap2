/*
 * The MIT License
 *
 * Copyright 2017 Open Jahap Community.
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
package org.jahap.gui;

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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.jahap.business.base.languagebean;
import org.jahap.entities.Reports;
import org.jahap.gui.res.InterResSearchResult;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.sreport.reportgroups;
import org.jahap.sreport.reportsbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author demokrite
 */
public class TextReportListController implements Initializable  {
    Logger log = LoggerFactory.getLogger(TextReportListController.class);

 
    private  ResourcenManager jk; 
    
    private reportsbean reportsb;
    private List<Reports> reportlist;
    private String reportgroup;
    //private boolean isOverviewDialog=false;
    private SelectedReport sreports;
    @FXML
    private TableView reports;
    @FXML
    private Button edit;
    @FXML
    private Button newbutton;
    @FXML
    private Button print;
    @FXML
    private Button ok;
    @FXML
    private Button cancel;
    
    private languagebean  langbean;

    private ObservableList<Reports> data;
    @FXML
    private MenuItem setasStandard;
    @FXML
    private MenuItem copyreport;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         log.debug("Function entry init URL url, ResourceBundle rb");
        Resourcen kk= new Resourcen();
 	    jk=kk.getResourcenManager();
        reportsb= new reportsbean();
        langbean = new languagebean();

        initTable();
        
        

    }    
    
    public void init(SelectedReport sreport,String Reportgroup){
    	    log.debug("Function entry init SelectedReport,Reportgroup");
	    Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
            reportsb= new reportsbean();
        //RoomSearchResult searchresult with check for occ
          reportgroup=Reportgroup;
        //isOverviewDialog=true;
            sreports=sreport;
         initTable();
    }
    
    
    
     private void initTable(){
	    log.debug("Function entry initTable");
	  
        reportlist=reportsb.SearchForReportgroup(this.reportgroup);
        log.trace(this.reportgroup);
         data= FXCollections.observableList(reportlist);
        
        // -----------------  id
        TableColumn<Reports,String> IdCol = new TableColumn<Reports,String>("Id");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Reports, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Reports, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getId());
     }
     
             
      });  
      
      //dataTable.getColumns().add(IdCol);
      
      // ----------------- Name
    
      TableColumn<Reports,String> Name = new TableColumn<Reports,String>(jk.getFxLangString("tableColumn.name"));
      Name.setCellValueFactory(new Callback<CellDataFeatures<Reports, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Reports, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getName());
     }
     
             
      });  
      
      reports.getColumns().add(Name);
      
       // -----------------  Language
        TableColumn<Reports,String> Language = new TableColumn<Reports,String>(jk.getFxLangString("tableColumn.language"));
      Language.setCellValueFactory(new Callback<CellDataFeatures<Reports, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Reports, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getLanguage().getLanguageName());
     }
      
     
      
    
             
      });  
      
      // ------------------------
      
      reports.getColumns().add(Language);
     
       
      
       reports.setItems(data);
    
       
    
    
    }
    
     
     
    
    
    @FXML
    private void edit(ActionEvent event) throws IOException {
        int id;
        Reports ad=(Reports) reports.getSelectionModel().getSelectedItem();
        id=ad.getId();
        
        log.trace(String.valueOf(id));
         Stage stage = new Stage();
       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
       
	   URL fxmlURL = classLoader.getResource("fxml/TextReportEdit.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("i18n.TextReportEdit"));    
       
       

    AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
	stage.setTitle(jk.getFxLangString("window.Title"));
        TextReportEditController controller= loader.<TextReportEditController>getController();
       controller.init(id);
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void newbutton_pressed(ActionEvent event) {
    }

    @FXML
    private void print(ActionEvent event) {
        int id;
        Reports ad=(Reports) reports.getSelectionModel().getSelectedItem();
    id=ad.getId();
    
          try {
                
                //ResSearchresult.setDbRecordId(id, "Reports");
            } catch (Exception e) {
            }
            Stage stage = (Stage) print.getScene().getWindow();
        stage.close();
        
        
    }

//    @FXML
//    private void setasstandardreport(ActionEvent event) {
//        int id;
//        Reports ad=(Reports) reports.getSelectionModel().getSelectedItem();
//    id=ad.getId();
//    
//          try {
//                this.reportsb.getDataRecord(id);
//                this.reportsb.setStandardreport(Boolean.TRUE);
//                this.reportsb.saveRecord();
//                //ResSearchresult.setDbRecordId(id, "Reports");
//            } catch (Exception e) {
//            }
//            Stage stage = (Stage) print.getScene().getWindow();
//        stage.close();
//        
//    }

    @FXML
    private void ok(ActionEvent event) {
        int id;
        Reports ad=(Reports) reports.getSelectionModel().getSelectedItem();
    id=ad.getId();
    
          try {
                 
                 sreports.setReport(this.reportsb.getDataRecord(id), Boolean.TRUE);
                //ResSearchresult.setDbRecordId(id, "Reports");
            } catch (Exception e) {
            }
            Stage stage = (Stage) print.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void cancel(ActionEvent event) {
    }


    @FXML
    private void copyreport(ActionEvent event) {
        int id;
        Reports ad=(Reports) reports.getSelectionModel().getSelectedItem();
    id=ad.getId();
        reportsb.copyReport(id);
        data.clear();
        initTable();
        
    }

    @FXML
    private void setasStandard(ActionEvent event) {
        
        //TODO setStandardReport 
    }

    
}
