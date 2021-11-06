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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.Catbean;
import org.jahap.business.base.Locationbean;
import org.jahap.business.base.roomsbean;
import org.jahap.entities.base.Rooms;
import org.jahap.sreport.roomreports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class RoomGuiFx implements Initializable, RoomSearchResultListener {
    @FXML
    private TextField roomcode_fxtextfield;
    @FXML
    private TextField roomname_fxtextfield;
    @FXML
    private ChoiceBox<String> roomcategory_fxtextfield;
    @FXML
    private TextField pax;

    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private Button search;
    @FXML
    private Button printAdress;

    private static roomsbean rooms;
    private List<TextField> textfields;
    private RoomSearchResult searchresults;
    private long ratesid=0;
    @FXML
    private Button newRoom;
    @FXML
    private Button saveRoom;
    Logger log = LoggerFactory.getLogger(RoomGuiFx.class);
    @FXML
    private ToggleButton Housekeeping_dirty;
    @FXML
    private ToggleGroup HouseKeeping;
    @FXML
    private ToggleButton Housekeeping_clean;
    @FXML
    private ToggleButton maintenance_blocked;
    @FXML
    private ToggleGroup maintenance;
    @FXML
    private ToggleButton maintenance_free;
    @FXML
    private ChoiceBox<String> location;
    private Catbean cbean;
    private Locationbean lbean;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("Function entry initialize ");
        textfields=new ArrayList<TextField>();
         cbean= new Catbean();
         lbean= new Locationbean();
         ObservableList<String> datas=FXCollections.observableList(lbean.SearchForLocation());
         ObservableList<String> data=FXCollections.observableList(cbean.SearchForCat());
         roomcategory_fxtextfield.setItems(data);
         location.setItems(datas);
         textfields.add(roomcode_fxtextfield);
         textfields.add(roomname_fxtextfield);
       
         rooms= new roomsbean();
         searchresults = new RoomSearchResult();
         searchresults.addIDListener(this);
         log.debug("Function entry initialize");
    }    

    @FXML
    private void goFirstRecord(ActionEvent event) {
        rooms.jumpToFirstRecord();
        FillWithSelectedData();
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        rooms.nextRecordBackward();
        FillWithSelectedData();
        
        
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        rooms.nextRecordForeward();
        FillWithSelectedData();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
        rooms.jumpToLastRecord();
        FillWithSelectedData();
        
    }
       @FXML
        private void printRoom(ActionEvent event) throws JRException {
         List<Rooms> adl= new ArrayList<Rooms>();
       adl= rooms.getCurrentRoom();
        
        roomreports ARP = new roomreports();
        ARP.singleRoomReport(adl);
        
    }
    
    
    public void init(long id){
        log.debug("Function exit init  ");
        rooms = new roomsbean();
        rooms.setDataRecordId(id);
                      
              FillWithSelectedData();
        
        log.debug("Function entry init");  
        
    }

    

    private void FillWithSelectedData() {
        log.debug("Function exit FillWithSelectedData");
        roomname_fxtextfield.setText(rooms.getName());
        roomcode_fxtextfield.setText(rooms.getCode());
        location.setValue(rooms.getLocation().getFloor());
        roomcategory_fxtextfield.setValue(rooms.getCategory().getCatName());
        pax.setText(String.valueOf(rooms.getPax()));
        if(rooms.isClean()){
            Housekeeping_clean.setSelected(true);
        }else if(!rooms.isClean()){
            Housekeeping_dirty.setSelected(true);
        }
        
        if(rooms.isNo_maintenance()){
            maintenance_free.setSelected(true);
            
        }else if(!rooms.isNo_maintenance()){
           
            maintenance_blocked.setSelected(true);
        }
        log.debug("Function entry   FillWithSelectedData");
    }

    public void idinfo(RoomSearchResultEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void searchRoom(ActionEvent event) {
    }

    @FXML
    private void newRoom(ActionEvent event) {
        log.debug("Function entry newroom");
        rooms.createNewEmptyRecord();
        location.setValue("");
        roomcategory_fxtextfield.setValue("");
        roomcode_fxtextfield.setText("");
        roomname_fxtextfield.setText("");
        HouseKeeping.selectToggle(Housekeeping_dirty);
        maintenance.selectToggle(maintenance_blocked);
        pax.setText("");
        
        log.debug("Function exit newroom");  
    }

    @FXML
    private void saveRoom(ActionEvent event) {
        log.debug("Function entry saveroom");
        rooms.setCode(roomcode_fxtextfield.getText());
        rooms.setName(roomname_fxtextfield.getText());
        rooms.setPax(Byte.valueOf(pax.getText()));
        rooms.setCategory(cbean.getDataRecord(roomcategory_fxtextfield.getSelectionModel().getSelectedIndex()+1));
        rooms.setLocaton(lbean.getDataRecord(location.getSelectionModel().getSelectedIndex()+1));
        if(HouseKeeping.getSelectedToggle().equals(Housekeeping_clean)){
        rooms.setClean(true);
        }
        if(maintenance.getSelectedToggle().equals(maintenance_free)){
            rooms.setNo_maintenance(true);
        }
        rooms.saveRecord();
        log.debug("Function exit saveroom");
        
    }



}
