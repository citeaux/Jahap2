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


package org.jahap.gui;

/**
 *
 * @author russ
 */


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

import org.jahap.CurrentUser;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.report.housestatebean;

import org.jahap.config.*;

import org.jahap.gui.res.HousestateController;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@FxmlView("../../../Maingui.fxml")
public class MainGuiFx implements i18n, Initializable {

    ResourcenManager jk;
    private MenuItem newAddress;


 
    @FXML
    private MenuItem AdressSearch;
    @FXML
    private MenuItem Rates;
    @FXML
    private MenuItem Exit;
    @FXML
    private MenuItem Rooms;
    @FXML
    private MenuItem FO_SearchForReservation_fxmenuitem;
    @FXML
    private MenuItem searchForGuestAccount_fxmenuitem;
    @FXML
    private MenuItem DayClose_fxmenuitem;
    @FXML
    private MenuItem occplan_fxmenuitem;
    @FXML
    private MenuItem checkin;
    @FXML
    private MenuItem Checkcout;
    @FXML
    private MenuItem newReservation;
  
    @FXML
    private MenuItem HotelPrefMenuItem;
    @FXML
    private MenuItem revAccounts;
    @FXML
    private MenuItem taxes;
    @FXML
    private MenuItem arrivals;
    @FXML
    private MenuItem departures;
    @FXML
    private MenuItem roomCleaning;
    @FXML
    private MenuItem roomMaintenance;
    @FXML
    private MenuItem dirtyRooms;
    @FXML
    private MenuItem maintainReport;
    @FXML
    private MenuItem chefInfoReport;
    @FXML
    private MenuItem categorie;
    @FXML
    private MenuItem location;
    Logger log = LoggerFactory.getLogger(MainGuiFx.class);
	@FXML
	private Label hotelday;
	@FXML
	private MenuItem Jobs;
    @FXML
    private Label database_name;
    @FXML
    private MenuItem housestate_fxmenuitem;

    @FXML
    private void newAddress(ActionEvent event) throws IOException,InvocationTargetException {
     
      
	    
	    
	      Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
	log.debug(String.valueOf(fxmlURL));
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();     
        
    }

    public void initialize(URL url, ResourceBundle rb) {
        
           try {
               housestatebean xx = new housestatebean();
           } catch (SQLException ex) {
               java.util.logging.Logger.getLogger(MainGuiFx.class.getName()).log(Level.SEVERE, null, ex);
           }
        
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
         CurrentUser cu=CurrentUser.getCurrentUser();
	 Hotelbean hbean=new Hotelbean();
	 LocalDate hdate= LocalDate.fromDateFields(hbean.getOperationdate());
	 this.hotelday.setText(hdate.toString("dd.MM.yy"));
        //config cf= config.getInstance();
        ClientConfig cf = ClientConfig.getInstance();
        
        this.database_name.setText(cf.getUsedbconfig().getDatabase_title());

        System.out.print(cu.isIsAdmin());
         
         System.out.print(com.sun.javafx.runtime.VersionInfo.getVersion());
         
         
    } 

    @FXML
    private void AdressSearch(ActionEvent event) throws IOException,InvocationTargetException {
     

	    
	    
	      Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/base/AddressList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.AddressList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();     
        
        
    }

    @FXML
    private void OpenRatesSearch(ActionEvent event) throws IOException {
    
        
	      Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/base/RatesList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RatesList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();     
	    
	    
        
        
    }

    @FXML
    private void RoomsSearch(ActionEvent event) throws IOException {
        
        
        
	    
	    Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/base/RoomList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RoomList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();     
	    
	    
	    
    }

    @FXML
    private void FO_SearchForReservation(ActionEvent event) throws IOException {
        Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/ResList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.ResList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();      
	    
	    
	    
         
        
    }

    @FXML
    private void searchForGuestAccount(ActionEvent event) throws IOException {
      
	    
	 Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/acc/AccList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.acc.i18n.AccList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();         
	    
	    
        
    }

    @FXML
    private void DayClose(ActionEvent event) throws IOException {
	
	    
	    
	     Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/jobs/Dayclosegui.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.jobs.i18n.Dayclosegui"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();      
	    
	    
	    
	    
    }

    @FXML
    private void DoCheckin(ActionEvent event) throws IOException {
        log.debug("EntryDoCheckin");
         Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/CheckinList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.CheckinList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();     
        
    }

    @FXML
    private void DoCheckout(ActionEvent event) {
    }

    @FXML
    private void createNewReservation(ActionEvent event) throws IOException {
        Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/resgui.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.resgui"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();      
	    
	    
	    
	    
      
    }

    @FXML
    private void OpenOccplan(ActionEvent event) throws IOException {
      
	    
	    
	    Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/occplan.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.occplan"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();      
	    
        
    }


    @FXML
    private void HotelPrefMenu(ActionEvent event) throws IOException {
        Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/setup/hotelSetup.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.setup.i18n.hotelSetup"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle("Hotel Setup");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void revAccounts(ActionEvent event) throws IOException {
        
        
	    
	    
	     Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
        URL fxmlURL = classLoader.getResource("fxml/setup/RevAccList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.setup.i18n.RevAccList"));
      log.debug("AnchorPane taxes");
        
          AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle("Revenue Accounts");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void taxes(ActionEvent event) throws IOException {
           Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
        URL fxmlURL = classLoader.getResource("fxml/setup/VatTypes.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.setup.i18n.VatTypes"));
      log.debug("AnchorPane taxes");
        
          AnchorPane page = (AnchorPane) loader.load();
	    
      
            Scene scene = new Scene(page);
            stage.setTitle("Tax Setup");
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void getArrivals(ActionEvent event) {
    }

    @FXML
    private void getDepartures(ActionEvent event) {
    }

    @FXML
    private void openRoomCleaning(ActionEvent event) throws IOException {
      
	    
	    
	    Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/HousKeepingList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.HousKeepingList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();         
	    
	    
	    
    }

    @FXML
    private void openRoomMaintenance(ActionEvent event) throws IOException {
	  Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/MainTenanceList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.MainTenanceList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();         
	    
	    
	    
       
        
    }

    @FXML
    private void getDirtyRoomsReport(ActionEvent event) {
    }

    @FXML
    private void getMaintainReport(ActionEvent event) {
    }

    @FXML
    private void getChefInfoReport(ActionEvent event) {
    }

    @FXML
    private void categorie(ActionEvent event) throws IOException {
	    
	 Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/setup/CatList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.setup.i18n.CatList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();      
	    
	    
	    
       
    }

    @FXML
    private void location(ActionEvent event) throws IOException {
	         Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/setup/LocationList.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.setup.i18n.LocationList"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();   
	    
	    
	    
       
    }

	@FXML
	private void Jobs(ActionEvent event) throws IOException {
		Stage tage = new Stage();
        String fxmlFile = "/fxml/jobs/JobsList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
      
        
           AnchorPane page = (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));
      
            Scene tene = new Scene(page);
            tage.setTitle("Rates");
            tage.setScene(tene);
            tage.show();
		
	}

    @Override
    public String getI18NDateformat(Date cdate) {
        return null;
    }

    @Override
    public String getI18NDecformat(double number) {
        return null;
    }

    @Override
    public String getI18NTranslation(String totranslate) {
        return null;
    }

    @FXML
    private void OpenHouseState(ActionEvent event) throws IOException {
         Stage stage = new Stage();
	 Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/res/Housestate.fxml");
       FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.res.i18n.Housestate"));
      
        
           AnchorPane page = (AnchorPane) loader.load();
      
            Scene scene = new Scene(page);
            stage.setTitle(jk.getFxLangString("window.Titel"));
            stage.setScene(scene);
            stage.show();   
        
    }
}
