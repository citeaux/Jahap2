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
package org.jahap.gui.acc;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.jahap.business.acc.revaccountsbean;
import org.jahap.business.base.Choicebean;
import org.jahap.business.base.choicegroups;
import org.jahap.entities.acc.Revaccounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author russ
 */
public class RevGuiFx implements Initializable {
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    @FXML
    private TextField accountno;
    @FXML
    private TextField accountName;
    private revaccountsbean revaccbean;
    @FXML
    private AnchorPane accgroup;
    @FXML
    private ComboBox<String> RevAccountGroup;
    @FXML
    private Button printRevAcc;
    @FXML
    private Button newRevAcc;
    @FXML
    private Button saveRevAcc;
    private Choicebean choicebean;
    
    Logger log = LoggerFactory.getLogger(RevGuiFx.class);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    

    public void init(long id){
        log.debug("Function entry init" + id);
          Revaccounts rf=new Revaccounts();      
       revaccbean= new revaccountsbean();
        choicebean = new Choicebean();
         ObservableList<String> datap= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.revenuegroup));
        rf=revaccbean.SearchForRevAccount(id);
        accountName.setText(rf.getName());
        accountno.setText(String.valueOf(rf.getRevaccnumber()));
        RevAccountGroup.setItems(datap);
        RevAccountGroup.setValue(rf.getRev_group());
        log.debug("Function exit init");
    }
    
    private void FillDialog(){
        
        accountName.setText(revaccbean.getName());
         accountno.setText(String.valueOf(revaccbean.getRevaccnumber()));
        RevAccountGroup.setValue(revaccbean.getRev_group());
    }
    
    
    @FXML
    private void goFirstRecord(ActionEvent event) {
        revaccbean.jumpToFirstRecord();
        FillDialog();
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        revaccbean.nextRecordBackward();
        FillDialog();
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        revaccbean.nextRecordForeward();
        FillDialog();
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
        revaccbean.jumpToLastRecord();
        FillDialog();
    }


    @FXML
    private void printRevAcc(ActionEvent event) {
        log.debug("Function entry printRevAcc");
        
        log.debug("Function exit printRevAcc");
    }

    @FXML
    private void newRevAcc(ActionEvent event) {
        log.debug("Function entry newRevAcc");
        revaccbean.createNewEmptyRecord();
         accountName.setText("");
         accountno.setText("");
        RevAccountGroup.setValue("");
        log.debug("Function exit newRevAcc ");
    }

    @FXML
    private void saveRevAcc(ActionEvent event) {
        revaccbean.setName(accountName.getText());
        revaccbean.setRev_group(RevAccountGroup.getValue());
        revaccbean.setRevaccnumber(Long.valueOf(accountno.getText()));
        revaccbean.saveRecord();
    }
    
}
