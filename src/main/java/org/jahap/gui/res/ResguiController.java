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
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.jahap.gui.i18n;

import com.lowagie.text.pdf.PdfName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;

import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.cscbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.resstatebean;
import org.jahap.business.base.roomsbean;
import org.jahap.business.res.occbean;
import org.jahap.business.res.occcatbean;
import org.jahap.business.res.resbean;
import org.jahap.business.res.resnobean;
import org.jahap.entities.acc.Accounts;
import org.jahap.entities.res.Occ;
import org.jahap.gui.acc.SimpelAccountingController;
import org.jahap.gui.base.AdressGuiFx;
import org.jahap.gui.base.ListDialogAddressController;
import org.jahap.gui.base.RateGuiFx;
import org.jahap.gui.base.RateListController;
import org.jahap.gui.base.RoomListController;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.sreport.resreports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class ResguiController implements i18n, Initializable, InterResSearchResultListener {
    //private CalendarView clview;



    Logger log = LoggerFactory.getLogger(ResguiController.class);
    @FXML
    private DatePicker datapickerFrom;
    @FXML
    private DatePicker datapickerTo;
    @FXML
    private Label DASH_ResOrderer_txt;
    @FXML
    private Font x1;
    @FXML
    private Label DASH_ResNo_fxtxt;
    @FXML
    private Label DASH_ResArrival_fxtxt;
    @FXML
    private Font x2;
    @FXML
    private Label DASH_ResState_fxtxt;
    @FXML
    private Label DASH_ResPax_fxtxt;
    @FXML
    private Label DASH_ResDeparture_fxtxt;
    @FXML
    private TextField Orderer_Name_fxtxtfield;
    @FXML
    private TextField Orderer_FirstName_fxtxtfield;
    @FXML
    private TextField Orderer_Street_fxtxtfield;
    @FXML
    private TextField Orderer_ZipCode_fxtxtfield;
    @FXML
    private TextField Orderer_City_fxtxtfield;
    @FXML
    private TextField Orderer_Country_fxtxtfield;
    @FXML
    private Button Orderer_Seach_fxbuton;
    @FXML
    private Button Orderer_Details_fxbutton;
    @FXML
    private TextField Guest_firstName_fxtxtfield;
    @FXML
    private TextField Guest_Country_fxtxtfield;
    @FXML
    private TextField Guest_City_fxtxtfield;
    @FXML
    private TextField Guest_Street_fxtxtfield;
    @FXML
    private TextField Guest_ZipCode_fxtxtfield;
    @FXML
    private TextField Guest_Name_fxtxtfield;
    @FXML
    private Button Guest_Search_fxbutton;
    @FXML
    private Button Guest_Details_fxbutton;
    @FXML
    private TextField Room_Code_fxtxtfield;
    @FXML
    private Button Room_Search_fxbutton;
    @FXML
    private TextField ACC_No_fxtxtfield;
    @FXML
    private TextField ACC_Balance_fxtxtfield;
    @FXML
    private Button ACC_OpenAcc_fxbutton;
    @FXML
    private Button Save_fxbutton;
    @FXML
    private Button Exit_fxbutton;
    @FXML
    private Button Print_fxbutton;
    @FXML
    private Button Search_fxbutton;
    @FXML
    private Button Toolbox_FirstRecord_fxButton;
    @FXML
    private Button Toolbox_BackwardRecord_fxbutton;
    @FXML
    private ChoiceBox<?> Toolbox_Sorting_fxchoicebox;
    @FXML
    private Button Toolbox_ForewardRecord_fxbutton;
    @FXML
    private Button Toolbox_LastRecord_fxbutton;
    @FXML
    private ChoiceBox<String> resstatechoice;
    @FXML
    private TextArea rescomment;
    @FXML
    private ChoiceBox<String> paxnumber;
    @FXML
    private DatePicker optionDate;

    private InterResSearchResult ressearchresult;
    private resbean res;
    private ratesbean rate;
    private occbean occ;
    private roomsbean room;
    private addressbean address;
    private accountsbean accs;
    private cscbean cscs;
    private occcatbean occcat;
    @FXML
    private Tooltip resstatetooltip;
    private resstatebean resstate;
    private resnobean rno;


    @FXML
    private Font x3;
    @FXML
    private Button New_fxbutton;
    @FXML
    private TextField RATE_Name_fxtxtfield;
    @FXML
    private Button RATE_Search_fxbutton;
    @FXML
    private Button RATE_Details_fxtxtfield;
    @FXML
    private Label DASH_ResNewCreated_fxtxt;

    /**
     * Initializes the controller class.
     */
    private boolean createNewRecord = false;
    private long roomid = 0;
    private long ordererid = 0;
    private long guestid = 0;
    private long rateid = 0;
    private long accountid = 0;
    private  long resno=0;
    private long catid=0;
    private List<TextField> textfields;
    private ResourcenManager jk;
    private Hotelbean hbean;

    private DecimalFormat DecFormatter;
    private SimpleDateFormat DFormat;
    @FXML
    private TextField Room_Cat_Code_fxtxtfield;
    @FXML
    private Button Room_Cat_Search_fxbutton;

    /**
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("Function entry initialize");

        hbean = new Hotelbean();
        LocalDate today = LocalDate.now();
        DecFormatter = new DecimalFormat(hbean.getHotelNumberformat());

        DFormat = new SimpleDateFormat(hbean.getHoteldateformat());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(hbean.getHoteldateformat());
        textfields = new ArrayList<TextField>();
        DASH_ResArrival_fxtxt.setText(today.format(formatter));
        DASH_ResDeparture_fxtxt.setText(today.plusDays(1).format(formatter));
        datapickerFrom.setValue(today);
        datapickerTo.setValue(today.plusDays(1));
        paxnumber.setValue("1");
        res = new resbean();
        address = new addressbean();
        occ = new occbean();
        room = new roomsbean();
        rate = new ratesbean();
        accs = new accountsbean();
        cscs = new cscbean();
        occcat = new occcatbean();
        DASH_ResNo_fxtxt.setText("0");
        resstate = new resstatebean();
        ObservableList<String> data = FXCollections.observableList(resstate.getAllResstate());

        resstatechoice.setItems(data);
        ressearchresult = new InterResSearchResult();
        createNewRecord = true;
        ressearchresult.addIDListener(this);
        log.debug("Function exit initialize");
    }

    @FXML
    private void Search_Orderer(ActionEvent event) throws IOException {
        Resourcen kk = new Resourcen();
        jk = kk.getResourcenManager();
        log.debug("Function entry Search_Orderer");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Stage stage = new Stage();


        URL fxmlURL = classLoader.getResource("fxml/base/AddressList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL, jk.getFxResourceBundle("fxml.base.i18n.AddressList"));

        AnchorPane page = (AnchorPane) loader.load();


        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title"));


        stage.setScene(scene);
        ListDialogAddressController controller = loader.<ListDialogAddressController>getController();
        controller.init(ressearchresult, this, "orderaddress");


        stage.showAndWait();

        log.debug("Function exit Search_Orderer");

    }

    @FXML
    private void Details_Orderer(ActionEvent event) throws IOException {
        log.debug("Function entry Details_Orderer");
        Stage stage = new Stage();
        Resourcen kk = new Resourcen();
        jk = kk.getResourcenManager();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL, jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));
        AnchorPane page = (AnchorPane) loader.load();


        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title"));


        stage.setScene(scene);
        AdressGuiFx controller = loader.<AdressGuiFx>getController();
        controller.init(ordererid);


        stage.showAndWait();


        log.debug("Function entry Details_Orderer");

    }

    @FXML
    private void Search_Guest(ActionEvent event) throws IOException {
        log.debug("Function entry Search_Guest ");
        Stage stage = new Stage();

        Resourcen kk = new Resourcen();
        jk = kk.getResourcenManager();


        //String fxmlFile = "/fxml/base/AddressList.fxml";


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL fxmlURL = classLoader.getResource("fxml/base/AddressList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL, jk.getFxResourceBundle("fxml.base.i18n.AddressList"));


        AnchorPane page = (AnchorPane) loader.load();


        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title"));


        stage.setScene(scene);
        ListDialogAddressController controller = loader.<ListDialogAddressController>getController();
        controller.init(ressearchresult, this, "guestaddress");


        stage.showAndWait();
        log.debug("Function exit Search_Guest");
    }

    @FXML
    private void Details_guest(ActionEvent event) throws IOException {

        log.debug("Function entry Details_guest");
        Resourcen kk = new Resourcen();
        jk = kk.getResourcenManager();
        Stage stage = new Stage();


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL fxmlURL = classLoader.getResource("fxml/base/AdressGuiFx.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL, jk.getFxResourceBundle("fxml.base.i18n.AdressGuiFx"));


        AnchorPane page = (AnchorPane) loader.load();


        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title"));


        stage.setScene(scene);
        AdressGuiFx controller = loader.<AdressGuiFx>getController();
        controller.init(guestid);


        stage.showAndWait();
        log.debug("Function exit Details_guest");
    }

    @FXML
    private void Search_room(ActionEvent event) throws IOException {
        log.debug("Function entry Search_room");

        Stage stage = new Stage();

        Resourcen kk = new Resourcen();
        jk = kk.getResourcenManager();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL fxmlURL = classLoader.getResource("fxml/base/RoomList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL, jk.getFxResourceBundle("fxml.base.i18n.RoomList"));


        AnchorPane page = (AnchorPane) loader.load();


        Scene scene = new Scene(page);
        stage.setTitle(jk.getFxLangString("window.Title"));


        stage.setScene(scene);
        RoomListController controller = loader.<RoomListController>getController();
        controller.init(ressearchresult, this, "rooms", datapickerFrom.getValue().atStartOfDay().toLocalDate(), datapickerTo.getValue().atStartOfDay().toLocalDate());


        stage.showAndWait();
        log.debug("Function exit Search_room");
    }

    @FXML
    private void Open_Account(ActionEvent event) throws IOException {


        log.debug("Function entry Open_Account");

        Stage stage = new Stage();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL fxmlURL = classLoader.getResource("fxml/acc/simpelAccounting.fxml");
        Resourcen kk = new Resourcen();
        jk = kk.getResourcenManager();

        FXMLLoader loader = new FXMLLoader(fxmlURL, jk.getFxResourceBundle("fxml.acc.i18n.simpelAccounting"));

        AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);


        stage.setScene(scene);
        stage.setTitle(jk.getFxLangString("window.Title"));
        SimpelAccountingController controller = loader.<SimpelAccountingController>getController();
        controller.init(accountid);


        stage.showAndWait();

        log.debug("Function exit Open_Account");
    }

    @FXML
    private void Save(ActionEvent event) {
        // only for changing Res

        log.debug("Function entry Save, CreateNewRecord=" + createNewRecord);
        int tomanypaxinroom=0;
        //*********************** If  res exits ******************

        if (createNewRecord == false) {
            log.debug("Save:oldreservation");
            saveExistingReservation(tomanypaxinroom);

        }

        // ************************ If Res does not exits already ************************

        if (createNewRecord == true) {
            log.debug("save:new reservation");
            saveNewReservation();

           // DASH_ResNo_fxtxt.setText("0");
        }
        log.debug("Function exit Save");
    }

    private boolean validateDialogEntrys() {
        log.debug("Function Save: validateDialogEntrys");

        return true;
    }


    private boolean checkPaxRooms() {
        log.debug("checkrooms entry");
        int tomanypaxinroom;

        if (occ.getRoom().getPax() >= Integer.valueOf(paxnumber.getValue())) {
            occ.setPax(Integer.valueOf(paxnumber.getValue()));
            return true;
        } else if (occ.getRoom().getPax() < Integer.valueOf(paxnumber.getValue())) {
            tomanypaxinroom = getShowDialogToManyPaxinRoom(String.valueOf(occ.getRoom().getPax()));
            return false;
        }

        log.debug("checkrooms exit");

        return false;
    }

    private boolean checkForOverlapping() {
        log.debug("function checkForOverlapping entry");
        List<String> overlaps = new ArrayList<String>();
        overlaps = occ.CheckForOverlappingReservations();

        if (overlaps == null) {
            return true;
        } else if (overlaps != null) {
            if (overlaps.size() >= 1) {
                String Test = overlaps.get(0);
                int i;
                for (i = 0; i == overlaps.size(); i++) {
                    Test = Test + ", " + overlaps.get(i);
                }
                int iol = showDialogRoomisoccupied(Test);
                switch (iol) {
                    case 0: { // create Reservation
                        return true;
                    }
                    case 1:{  // drop Reservation  - empty dialog
                        clearDialog();

                        return false;
                    }case 2: {  // cancel
                        return false;
                    }

                }


                return false;
            }
        }
        return false;
    }


    private void finishReservation() {
        log.debug("finischRes");

        if (createNewRecord == true) {
            rno= new resnobean();
            rno.createNewEmptyRecord();
            rno.saveRecord();
                 resno=rno.getResno();
        }




        res.setResnointern(resno);

        res.setResno(String.valueOf(resno));
        res.setArrivaldate(occ.getArrivaldate());
        res.setDeparturedate(occ.getDeparturedate());


        res.saveRecord();
        accs.setReservation(res.GetCurrentRes());
        accs.saveRecord();
        occ.setRes(res.getLastRecord());
        List<String> overlaps=new ArrayList<String>();
        overlaps= occ.saveRecord(true);

        DASH_ResNo_fxtxt.setText(String.valueOf(resno));
        cscs.saveRecord();

    }


    private void dropReservationSetup(){
        res.dropReservationSetup();
         occ.dropOccSetup();
         accs.dropAccSetup();
        cscs.dropCscSetup();
    }





    private void saveNewReservation() {
        log.debug("Function Save: Newrecord");
        //Date SaveFromDate=occ.getArrivaldate();
        //Date SaveToDate =occ.getDeparturedate();
        //long SaveRoomId=occ.getRoom().getId();
        //long SaveAddressId =occ.getGuest().getId();
        preSetupNewReservation();


        boolean nextstep=false;
        if(validateDialogEntrys()){
            nextstep=true;
        }

        if(checkPaxRooms()){
            nextstep=true;
        }







        if( nextstep) {
            preSetupReservation();
            if(checkForOverlapping()){
                  finishReservation();

            }
            if(checkForOverlapping()==false){
                  dropReservationSetup();
            }

        }


                           log.debug("Function Save:newrecord exit");

             }





    /**
     *  creates basic entries in the main tables:
     *  Res : Reservation
     *  Occ : Occupancy
     *  Acc: Accounting
     *  Cscs: countiniuous Service charging
     *
     */

    private void preSetupNewReservation() {
        res.createNewEmptyRecord();
        occ.createNewEmptyRecord();
        accs.createNewEmptyRecord();
        cscs.createNewEmptyRecord();

        // for  occ validation

        occ.setArrivaldate(Date.from(datapickerFrom.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        occ.setDeparturedate(Date.from(datapickerTo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        occ.setRoom(room.getDataRecord(roomid));
        occ.setGuest(address.getDataRecord(guestid));

        occ.setPax(Integer.valueOf(paxnumber.getValue()));
        
        
        
    }


    private void preSetupReservation() {

        res.setAddresses(address.getDataRecord(ordererid));  // Set Addressrecord
        res.setState(resstate.getRecordFromTranslatedState(resstatechoice.getValue()));
        res.setComment(rescomment.getText());
        if(optionDate.getValue()!=null) {
            res.setOptiondate(Date.from(optionDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        }
        // Set dates for  OCC

        occ.setArrivaldate(Date.from(datapickerFrom.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        occ.setDeparturedate(Date.from(datapickerTo.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        occ.setRoom(room.getDataRecord(roomid));
        occ.setGuest(address.getDataRecord(guestid));

        occ.setPax(Integer.valueOf(paxnumber.getValue()));

        cscs.setRate(rate.getDataRecord(rateid));

        accs.setAddress(address.getDataRecord(guestid));
        // accs.saveRecord();
        occ.setAccount(accs.getLastRecord()); // has accs  to be persist?
    }

    private void saveExistingReservation(int tomanypaxinroom) {
        log.debug("Function Save: oldrecord");
        Date SaveFromDate=occ.getArrivaldate();
        Date SaveToDate=occ.getDeparturedate();
        long SaveRoomId=occ.getRoom().getId();
        long SaveAddressId=occ.getGuest().getId();

        boolean nextstep=false;
        if(validateDialogEntrys()){
            nextstep=true;
        }

        if(checkPaxRooms()){
            nextstep=true;
        }







        if( nextstep) {
            preSetupReservation();
            if(checkForOverlapping()==true){
                finishReservation();

            }
            if(checkForOverlapping()==false){
                dropReservationSetup();
            }

        }





        // checks for overlaps




        log.debug("Function Save:OldRecord exit");
    }

    /**
     * @param possiblePaxinRoom
     * @return 0,1,2
     *
     * 0 create Reservation
     * 1 delete Reservation
     * 2 cancel
     */


    private int getShowDialogToManyPaxinRoom(String possiblePaxinRoom) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("To many persons in room");
        alert.setHeaderText("There are to many person in this room. This room allows only:");
        alert.setContentText(possiblePaxinRoom);


        ButtonType buttonTypeTwo = new ButtonType("create Reservation");
        ButtonType buttonTypeThree = new ButtonType("drop Reservation");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);


        alert.getButtonTypes().setAll( buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeTwo){
            return 0; // create - Adjust Res
        } else if (result.get() == buttonTypeThree) {
            return 1; // Remove Reservation
        }  else {
            return 2; // cancel
        }
    }

    /**
     * shows a Dialog with Overlapping Res
     *
     * @param  OverlappingReservation String
     * @return 0,1,2
     *
     * 0 create Reservation
     * 1 delete Reservation
     * 2 cancel
     */
    private int showDialogRoomisoccupied(String OverlappingReservation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rooms is Occupied");
        alert.setHeaderText("Rooms is Occupied by:");
        alert.setContentText(OverlappingReservation);


        ButtonType buttonTypeTwo = new ButtonType("create Reservation");
        ButtonType buttonTypeThree = new ButtonType("drop Reservation");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeTwo) {
            return 0; // create - Adjust Res
        } else if (result.get() == buttonTypeThree) {
            return 1; // Remove Reservation
        } else {
            return 2; // cancel
        }
        //JOptionPane.showOptionDialog(null, OverlappingReservation, "Rooms is Occupied by", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, new String[]{"create Reservation", "drop Reservation", "Cancel"}, "Cancel");


    }

    @FXML
    private void Exit(ActionEvent event) {
    }

    @FXML
    private void Print(ActionEvent event) throws JRException, SQLException {
	    resreports k=new resreports();
	    k.singleResReport(res.getResno());
	    
    }

    @FXML
    private void Search(ActionEvent event) {
    }

    @FXML
    private void Toolbox_FirstRecord(ActionEvent event) {
    }

    @FXML
    private void Toolbox_BackwardRecord(ActionEvent event) {
        res.nextRecordBackward();
        FillWithSelectedData();
    }

    @FXML
    private void Toolbox_ForewardRecord(ActionEvent event) {
        res.nextRecordForeward();
        FillWithSelectedData();
        
    }

    @FXML
    private void Toolbox_LastRecord(ActionEvent event) {
    }
    
    /**
     *
     * @param id
     */
    public void init(long id){
     log.debug("Function entry init id" + String.valueOf(id));
        textfields=new ArrayList<TextField>();



        res = new resbean();
        address=new addressbean();
        occ=new occbean();
        room=new roomsbean();
        rate=new ratesbean();
        accs=new accountsbean();
        cscs=new cscbean();
        ressearchresult=new InterResSearchResult();
        res.setDataRecordId(id);
                      ressearchresult.addIDListener(this);
              FillWithSelectedData();
        resstate= new resstatebean();
        ObservableList<String> data= FXCollections.observableList(resstate.getAllResstate());

        resstatechoice.setItems(data);
        resstatechoice.setValue(res.getState().getTranslation());
        
        // Add Textfields 
          paxnumber.setValue("1");
        textfields.add(Orderer_Name_fxtxtfield);
          textfields.add(Orderer_FirstName_fxtxtfield);
          textfields.add(Orderer_Street_fxtxtfield);
          textfields.add(Orderer_ZipCode_fxtxtfield);
          textfields.add(Orderer_City_fxtxtfield);
          textfields.add(Guest_firstName_fxtxtfield);
          textfields.add(Guest_City_fxtxtfield);
          textfields.add(Guest_Street_fxtxtfield);
          textfields.add(Guest_ZipCode_fxtxtfield);
          textfields.add(Guest_Name_fxtxtfield);
          textfields.add(Room_Code_fxtxtfield);
          textfields.add(ACC_No_fxtxtfield);
          textfields.add(ACC_Balance_fxtxtfield);
          textfields.add(RATE_Name_fxtxtfield);


          
          
          log.debug("Function exit init");
        
    }

    private void FillWithSelectedData() {
        // init DASH Board
        log.debug("Function entry FillWithSelectedData ");
        DASH_ResArrival_fxtxt.setText(DFormat.format(res.getArrivaldate()));
        DASH_ResDeparture_fxtxt.setText(DFormat.format(res.getDeparturedate()));
        DASH_ResNo_fxtxt.setText(res.getResno());
        DASH_ResOrderer_txt.setText(res.getAddresses().getName());
        DASH_ResPax_fxtxt.setText("1"); //DEV : Paximplenetion
        DASH_ResState_fxtxt.setText(res.getState().getTranslation());
        
        // init Order infos
        fillOrderer();
        
        //DEV :Init Guest infos
       
        fillGuest();
        //Res
        List<Occ> gh=new ArrayList<Occ>();
	    Occ mb= new Occ();
        gh=occ.SearchForOccforRes(res.GetCurrentRes());
	    mb=gh.get(0);
        roomid=occ.getRoom().getId();
        Room_Code_fxtxtfield.setText(mb.getRoom().getCode() + " " + mb.getRoom().getName());
        paxnumber.setValue(String.valueOf(occ.getPax()));
        //RATE_Name_fxtxtfield.setText(;
        fillDates();
        rescomment.setText(res.getComment());
        if( res.getOptiondate()!=null)
            optionDate.setValue(LocalDateTime.ofInstant(res.getOptiondate().toInstant(), ZoneId.systemDefault()).toLocalDate());

        // ACC
        
        
        ACC_Balance_fxtxtfield.setText("33");
        accs.moveToRecordwithRes(res.GetCurrentRes());
        Accounts hhf=new Accounts();
        hhf=accs.getAccount();
        cscs.moveToRecordwithAccount(accs.getAccount());
        RATE_Name_fxtxtfield.setText(cscs.getRate().getCode() + "  " + cscs.getRate().getName());
        ACC_No_fxtxtfield.setText(accs.getId().toString() + " " + accs.getAddress().getName());
        this.rateid=cscs.getRate().getId();
        this.accountid=accs.getId();
	
	    log.debug("Function exit FillWithSelectedData");
       
    }

    private void fillOrderer(){
	    log.debug("Function entry fillOrderer");   
	    
         Orderer_Name_fxtxtfield.setText(res.getAddresses().getName());
        Orderer_FirstName_fxtxtfield.setText(res.getAddresses().getChristianname());
        Orderer_Street_fxtxtfield.setText(res.getAddresses().getStreet());
        Orderer_ZipCode_fxtxtfield.setText(res.getAddresses().getZipcode());
        Orderer_City_fxtxtfield.setText(res.getAddresses().getCity());
        ordererid=res.getAddresses().getId();
	
	    log.debug("Function exit fillOrderer");
    }
    
    private void fillOrderer(long addressid){
	    
	    log.debug("Function entry fillOrderer" + String.valueOf(addressid));
        
         Orderer_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Orderer_FirstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Orderer_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Orderer_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Orderer_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
        ordererid=addressid;
	    log.debug("Function exit fillOrderer");
    }
    
    
    private void fillGuest(){
	    log.debug("Function entry fillGuest ");
         Guest_Name_fxtxtfield.setText(occ.getGuest().getName());
        Guest_firstName_fxtxtfield.setText(occ.getGuest().getChristianname());
        Guest_Street_fxtxtfield.setText(occ.getGuest().getStreet());
        Guest_ZipCode_fxtxtfield.setText(occ.getGuest().getZipcode());
        Guest_City_fxtxtfield.setText(occ.getGuest().getCity());
        guestid=occ.getGuest().getId();
              log.debug("Function exit fillGuest");
    }
    
    private void fillGuest(long addressid){
	    log.debug("Function entry fillGuest" + String.valueOf(addressid));
         Guest_Name_fxtxtfield.setText(address.getDataRecord(addressid).getName());
        Guest_firstName_fxtxtfield.setText(address.getDataRecord(addressid).getChristianname());
        Guest_Street_fxtxtfield.setText(address.getDataRecord(addressid).getStreet());
        Guest_ZipCode_fxtxtfield.setText(address.getDataRecord(addressid).getZipcode());
        Guest_City_fxtxtfield.setText(address.getDataRecord(addressid).getCity());
       guestid=addressid;
       log.debug("Function exit fillGuest");
    }
    
    private void fillRoom(long roomid){
        
        Room_Code_fxtxtfield.setText(room.getDataRecord(roomid).getCode()+ " " + room.getDataRecord(roomid).getName());
        this.roomid=roomid;
    }
    
    private void fillCategory(long roomid){
        
        Room_Cat_Code_fxtxtfield.setText(room.getCategory().getCatName());
        catid=room.getCategory().getId();
    }
    
     private void fillRate(long rateid){
        RATE_Name_fxtxtfield.setText(rate.getDataRecord(rateid).getCode()+ " " + rate.getDataRecord(rateid).getName());
        this.rateid=rateid;
     }
     
     
     
     private void fillDates(){
         datapickerFrom.setValue(LocalDateTime.ofInstant(occ.getArrivaldate().toInstant(), ZoneId.systemDefault()).toLocalDate());
         datapickerTo.setValue(LocalDateTime.ofInstant(occ.getDeparturedate().toInstant(),ZoneId.systemDefault()).toLocalDate());
     }
    
    /**
     *
     * @param e
     */
    public void idinfo(InterResSearchResultEvent e) {
     if(e.getTableNameofSource()=="orderaddress"){
         fillOrderer(e.getDbRecordId());
     }   
     
     if(e.getTableNameofSource()=="guestaddress"){
           fillGuest(e.getDbRecordId());
     }
     if(e.getTableNameofSource()=="rooms"){
           fillRoom(e.getDbRecordId());
     }
     
     if(e.getTableNameofSource()=="rate"){
           fillRate(e.getDbRecordId());
     }
     
     
    }

    @FXML
    private void New(ActionEvent event) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        clearDialog();
        
    }


    private void clearDialog() {
        for(TextField kk:textfields){
            kk.setText("");
        }

        DASH_ResNo_fxtxt.setText("");
        DASH_ResArrival_fxtxt.setText("");
        DASH_ResDeparture_fxtxt.setText("");
        DASH_ResOrderer_txt.setText("");
        DASH_ResPax_fxtxt.setText("");
        DASH_ResState_fxtxt.setText("");

        rescomment.setText("");
        createNewRecord=true;
    }

    @FXML
    private void SearchForRate(ActionEvent event) throws IOException {
	    log.debug("Function entry SearchForRate");
        Stage stage = new Stage();
        Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        
        
        String fxmlFile = "/fxml/base/RatesList.fxml";
        
       
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/RatesList.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RatesList"));   
 	    
 	    
     
        
      
         AnchorPane page = (AnchorPane) loader.load();
 	    
       
             Scene scene = new Scene(page);
             stage.setTitle(jk.getFxLangString("window.Title")) ;   

       

        
        stage.setScene(scene);
        RateListController controller= loader.<RateListController>getController();
       controller.init(ressearchresult,this,"rate");
       
        
        stage.showAndWait();
	    log.debug("Function exit SearchForRate");
    }

    @FXML
    private void RateDetails(ActionEvent event) throws IOException {
	    log.debug("Function entry RateDetails ");
        Stage stage = new Stage();
        Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
        
        
        
        
        String fxmlFile = "/fxml/base/RateGuiFx.fxml";
       
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        
 	   URL fxmlURL = classLoader.getResource("fxml/base/RateGuiFx.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.base.i18n.RateGuiFx"));   
 	    
 	    
     
        
      
         AnchorPane page = (AnchorPane) loader.load();
 	    
       
             Scene scene = new Scene(page);
             stage.setTitle(jk.getFxLangString("window.Title")) ;   
       

        
        stage.setScene(scene);
        RateGuiFx controller= loader.<RateGuiFx>getController();
       controller.init(rateid);
       
        
        stage.showAndWait();
	    log.debug("Function exit RateDetails");
        
        
    }

    @FXML
    private void Action_From_Date(ActionEvent event) {
        
       
    }

    @FXML
    private void Action_To_Date(ActionEvent event) {
        
        
    }

    @Override
    public String getI18NDateformat(Date cdate) {
        return DFormat.format(cdate);
    }

    @Override
    public String getI18NDecformat(double number) {
        return DecFormatter.format(number);
    }

    @Override
    public String getI18NTranslation(String totranslate) {


        ResourcenManager jk;
        Resourcen kk= new Resourcen();
        jk=kk.getResourcenManager();
        jk.getFxResourceBundle("i18n.Systexts");
        return jk.getFxLangString(totranslate);
    }
}
