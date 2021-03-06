package org.jahap.gui.res;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.res.checkinbean;
import org.jahap.entities.Reports;
import org.jahap.entities.res.Res;
import org.jahap.entities.views.Checkin;
import org.jahap.gui.base.RateGuiFx;
import org.jahap.gui.base.RoomGuiFx;
import org.jahap.gui.base.SearchResult;
import org.jahap.gui.i18n;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;

import org.jahap.gui.SelectedReport;
import org.jahap.gui.SelectedReportEvent;
import org.jahap.gui.SelectedReportListner;
import org.jahap.gui.TextReportListController;
import org.jahap.gui.base.AdressGuiFx;
import org.jahap.sreport.checkinreports;
import org.jahap.sreport.reportgroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckinListController implements i18n, Initializable, SelectedReportListner{
    
    Logger log = LoggerFactory.getLogger(CheckinListController.class);
	@FXML
	private Button PrintButton;
	@FXML
	private Button checkin;
	@FXML
	private Button printRegCard;
	@FXML
	private TableView dataTable;
	@FXML
	private Button Ok;
	@FXML
	private Button Cancel;
        private InterResSearchResult reportsearchresult;
         private  ResourcenManager jk;
         
         private boolean isOverviewDialog=true;
         
         SearchResult searchresult;
         
         private checkinbean checkinbean;
         
         
         
        private long id; 
         
         private List checkinlist;
    @FXML
    private MenuItem reservation_mitem;
    @FXML
    private MenuItem address_mitem;
    @FXML
    private MenuItem room_mitem;
    @FXML
    private MenuItem checkin_mitem;
    @FXML
    private MenuItem choosereport;
    @FXML
    private MenuItem chooseregcardreport;
    @FXML
    private TextField DialogID;
    
    private SelectedReport sreport;
    @FXML
    private MenuButton reportsdetails;
    private Reports SelectedRegCardReport;
    private Reports SelectedCheckinReport;
    
    private checkinreports checkinreports;

	// Event Listener on Button[#PrintButton].onAction
	@FXML
	public void PrintReport(ActionEvent event) {
		
            this.checkinreports = new checkinreports();
        try {
            
            if (SelectedCheckinReport!=null){
                log.debug("Selected Report:" + SelectedCheckinReport.getId().toString());
                this.checkinreports.CheckinReport(checkinlist,SelectedCheckinReport.getId());
            
            }else{
                log.debug("Fallback Report:" + SelectedCheckinReport.getId().toString());
                this.checkinreports.CheckinReport(checkinlist);
            }
        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(CheckinListController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
	}
	// Event Listener on TableView[#dataTable].onMouseClicked
	@FXML
	public void MouseClicked(MouseEvent event) throws IOException {
		
                
                
                if(event.getClickCount()==2){
                         openResDialog();
                }
                
	}
	// Event Listener on Button[#Ok].onAction
	@FXML
	public void OkAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#Cancel].onAction
	@FXML
	public void CancelAction(ActionEvent event) {
		// TODO Autogenerated
	}
        
         public void initialize(URL url, ResourceBundle rb) {
             sreport = new SelectedReport();
             sreport.addIDListener(this);
             //reportsearchresult= new InterResSearchResult();
             //reportsearchresult.addIDListener(this);
             
             log.debug("Init Table");
             searchresult= new SearchResult();
             isOverviewDialog=true;
	    checkinbean = new checkinbean();
	     Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
            
            initTable();
            
            
            
         }
         
         
         
         
         
         private void initTable(){
    	
        
	     Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        
        checkinlist= checkinbean.getCheckinList();
        ObservableList<Checkin> data= FXCollections.observableList(checkinlist);
        
        // -----------------  id
        TableColumn<Checkin,String> IdCol = new TableColumn<Checkin,String>("ID");
      IdCol.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getOccId());
     }
     
             
      });  
      
      //dataTable.getColumns().add(IdCol);
      
       // -----------------  Checked in
        TableColumn<Checkin,String> checkedin = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.checkedIn"));
      checkedin.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getOccCheckin().booleanValue());
     }
     
             
      });  
      
      dataTable.getColumns().add(checkedin);
      
      // -----------------  guestgreeting
        TableColumn<Checkin,String> greeting = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.guestgreeting"));
      greeting.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getGuestAdressGreeting());
     }
     
             
      });  
      
      dataTable.getColumns().add(greeting);
      
       // -----------------  Name
        TableColumn<Checkin,String> NameCol = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.guestname"));
      NameCol.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getGuestAdressName());
     }
     
             
      });  
      
      dataTable.getColumns().add(NameCol);
      
       // -----------------  Surename
        TableColumn<Checkin,String> surename = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.guestsurename"));
      surename.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         return new ReadOnlyObjectWrapper(p.getValue().getGuestAdressChristianname());
     }
     
      
             
      });  
      
      dataTable.getColumns().add(surename);
      
       // -----------------  groom
        TableColumn<Checkin,String> groom = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.guestroom"));
      groom.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         
         return new ReadOnlyObjectWrapper(p.getValue().getRoomCode());

     }
     
             
      });  
      
      dataTable.getColumns().add(groom);
        
      
      
        // -----------------  groomcat
        TableColumn<Checkin,String> groomcat = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.roomtype"));
      groomcat.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         
         return new ReadOnlyObjectWrapper(p.getValue().getCatName());

     }
     
             
      });  
      
      dataTable.getColumns().add(groomcat);
        
     
      
      
        // -----------------  arrivaldate
        TableColumn<Checkin,String> arrivaldate = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.arrivaldate"));
      arrivaldate.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         
         return new ReadOnlyObjectWrapper(p.getValue().getOccArrivaldate());

     }
     
             
      });  
      
      dataTable.getColumns().add(arrivaldate);
        
      
      
       // -----------------  resno
        TableColumn<Checkin,String> resno = new TableColumn<Checkin,String>(jk.getFxLangString("tableColumn.ResNo"));
      resno.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         
         return new ReadOnlyObjectWrapper(p.getValue().getResno());

     }
     
             
      });  
      
      dataTable.getColumns().add(resno);
      
      
      
       // -----------------  resid
        TableColumn<Checkin,String> resid = new TableColumn<Checkin,String>(jk.getFxLangString("resid"));
      resid.setCellValueFactory(new Callback<CellDataFeatures<Checkin, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Checkin, String> p) {
         
         return new ReadOnlyObjectWrapper(p.getValue().getResId());

     }
     
             
      });  
      
      //dataTable.getColumns().add(resno);
      
      
      
       dataTable.setItems(data);
            
    
    
    }
         
    private void openResDialog() throws IOException{
        Checkin ad = (Checkin) dataTable.getSelectionModel().getSelectedItem();
                id= ad.getResId();
               
                Stage stage = new Stage();
       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
       
	   URL fxmlURL = classLoader.getResource("fxml/res/resgui.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.resgui"));    
       
       

    AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
	stage.setTitle(jk.getFxLangString("window.Title"));
        ResguiController controller= loader.<ResguiController>getController();
       controller.init(id);
       
        
        stage.showAndWait();
        
        
    }     
    
    
    
    private void openAddressDialog() throws IOException{
        Checkin ad = (Checkin) dataTable.getSelectionModel().getSelectedItem();
           id = ad.getGuest_adress_id();
               
                Stage stage = new Stage();
       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
       
	   URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));    
       
       

    AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
	stage.setTitle(jk.getFxLangString("window.Title"));
        AdressGuiFx controller= loader.<AdressGuiFx>getController();
       controller.init(id);
       
        
        stage.showAndWait();
        
        
    }     
    
    
    private void openReportDialog(List<Reports> reportlist, String standardreport) throws IOException{
        
        log.debug("openReportDialog");
        Checkin ad = (Checkin) dataTable.getSelectionModel().getSelectedItem();
                id= ad.getResId();
               
                Stage stage = new Stage();
       ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
       
	   URL fxmlURL = classLoader.getResource("fxml/TextReportList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.i18n.TextReportList"));    
       
       

    AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
	stage.setTitle(jk.getFxLangString("window.Title"));
        ResguiController controller= loader.<ResguiController>getController();
       controller.init(id);
       
        
        stage.showAndWait();
    }
            
         

    @FXML
    private void click_menutitem_reservation(ActionEvent event) throws IOException {
        
        openResDialog();
    }
    
    
    private void openRoomDialog() throws IOException{
        Checkin ad = (Checkin) dataTable.getSelectionModel().getSelectedItem();
                id= ad.getRoom_id();
                Stage stage = new Stage();
             String fxmlFile = "/fxml/base/RoomsGuiFx.fxml";
       
	     
	     
	  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/base/RoomsGuiFx.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RoomsGuiFx"));        
	     
	     
        AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     stage.setTitle(jk.getFxLangString("window.Title"));

        
        stage.setScene(scene);
        RoomGuiFx controller= loader.<RoomGuiFx>getController();
       controller.init(id);
       
        
        stage.showAndWait();
                
                
    }
    

    @FXML
    private void click_menutitem_address(ActionEvent event) throws IOException {
          openAddressDialog();
    }

    @FXML
    private void click_menutitem_zimmer(ActionEvent event) throws IOException {
        openRoomDialog();
    }

    @FXML
    private void click_menutitem_checkin(ActionEvent event) {
         checkin();
        
    }
    
    private void checkin(){
        log.debug("Function entry checkin");
         long occid;
           Checkin ad = (Checkin) dataTable.getSelectionModel().getSelectedItem();
           occid=  ad.getOccId();
           checkinbean.checkin(occid);
                
                
    }

    @FXML
    private void choosereport(ActionEvent event) throws IOException {
        Stage stage = new Stage();
             
         Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	     
	     
	  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/TextReportList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("i18n.TextReportList"));    
       log.trace("choosereport");
	     
	  
        AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     
        stage.setTitle(jk.getFxLangString("window.Title"));   
        
        stage.setScene(scene);
        TextReportListController controller= loader.<TextReportListController>getController();
       controller.init(this.sreport, reportgroups.checkin.toString());
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void chooseregcardreport(ActionEvent event) throws IOException {
  Stage stage = new Stage();
             
         Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	     
	     
	  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
       
	   URL fxmlURL = classLoader.getResource("fxml/TextReportList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("i18n.TextReportList"));    
       log.trace("chooseregcardreport");
	     
	  
        AnchorPane page = (AnchorPane) loader.load();

        
        Scene scene = new Scene(page);
     
        stage.setTitle(jk.getFxLangString("window.Title"));   
        
        stage.setScene(scene);
        TextReportListController controller= loader.<TextReportListController>getController();
       controller.init(this.sreport, reportgroups.regcard.toString());
       
        
        stage.showAndWait();
        
    }

    @Override
    public String getI18NDateformat(Date cdate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getI18NDecformat(double number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getI18NTranslation(String totranslate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onTextChanged(Reports e) {
        if(e.getName()=="regcardreport"){
         //fillOrderer(e.getDbRecordId());
     }   
     
     if(e.getReportGroup()=="report"){
           //fillGuest(e.getDbRecordId());
     }
    }

    @Override
    public void idinfo(SelectedReportEvent e) {
        log.debug("idinfo");
       if (e.getRep().getReportGroup().toString().equals(reportgroups.regcard.toString())){
           SelectedRegCardReport = e.getRep();
       }
       
       if (e.getRep().getReportGroup().toString().equals(reportgroups.checkin.toString())){
           SelectedCheckinReport = e.getRep();
       }
        
    }

    @FXML
    private void printRegCard(ActionEvent event)  {
        this.checkinreports = new checkinreports();
        try {
            
            if (SelectedRegCardReport!=null){
                log.debug("Selected Report:" + SelectedRegCardReport.getId().toString());
                this.checkinreports.RegCardReport(checkinlist,SelectedCheckinReport.getId());
            
            }else{
                log.debug("Fallback Report:" + SelectedRegCardReport.getId().toString());
                this.checkinreports.RegCardReport(checkinlist);
            }
            

        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(CheckinListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void checkinButton(ActionEvent event) {
        checkin();
    }
}
