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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jahap.business.base.Locationbean;
import org.jahap.business.base.addressbean;
import org.jahap.entities.base.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class locationFx implements Initializable, SearchResultListener {
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private AnchorPane accgroup;
    @FXML
    private TextField address;
    @FXML
    private TextField building;
    @FXML
    private Button searchaddresse;
    @FXML
    private TextField floor;
    @FXML
    private Button printCatdesc;
    @FXML
    private Button newCat;
    @FXML
    private Button saveCat;
Logger log = LoggerFactory.getLogger(locationFx.class);     private Locationbean locbean;
     private addressbean adbean;
     private long addressid;
     private SearchResult sr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    public void init(short id){
          sr=new SearchResult();
          sr.addIDListener(this);
          locbean= new Locationbean();
          adbean= new addressbean();
          Location lj=new Location();
          lj=locbean.getDataRecord(id);
          building.setText(lj.getBuilding());
          floor.setText(lj.getFloor());
          address.setText(adbean.getDataRecord(lj.getAddressId()).getName());
          addressid=lj.getAddressId();
    }
    
    private void fillDialog(){
    building.setText(locbean.getBuilding());
          floor.setText(locbean.getFloor());
          address.setText(adbean.getDataRecord(locbean.getAddressId()).getName());
          addressid=locbean.getAddressId();
    
        
    }
    
    
    @FXML
    private void goFirstRecord(ActionEvent event) {
        locbean.jumpToFirstRecord();
        fillDialog();
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        locbean.nextRecordBackward();
        fillDialog();
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        locbean.nextRecordForeward();
        fillDialog();
        
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
        locbean.jumpToLastRecord();
        fillDialog();
        
    }

    @FXML
    private void searchaddress(ActionEvent event) throws IOException {
         Stage stage = new Stage();
        String fxmlFile = "/fxml/base/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(sr);
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void printLocdesc(ActionEvent event) {
    }

    @FXML
    private void newLoc(ActionEvent event) {
        log.debug("Function entry newRevAcc");
        locbean.createNewEmptyRecord();
         building.setText("");
         floor.setText("");
        address.setText("");
        log.debug("Function exit newRevAcc ");
    }

    @FXML
    private void saveLoc(ActionEvent event) {
        locbean.setBuilding(building.getText());
        locbean.setFloor(floor.getText());
        locbean.setAddressId((Long) addressid);
        locbean.saveRecord();
    }

    @Override
    public void idinfo(SearchResultEvent e) {
        log.debug("Function entry recordid" + e.getDbRecordId());
        addressid=e.getDbRecordId();
        address.setText(adbean.getDataRecord(addressid).getName());
        
    }
    
}
