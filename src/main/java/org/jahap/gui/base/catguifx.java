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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.jahap.business.base.Catbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class catguifx implements Initializable {
    Logger log = LoggerFactory.getLogger(catguifx.class);
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
    private TextField catname;
    @FXML
    private TextField catdescription;
    @FXML
    private Button printCatdesc;
    @FXML
    private Button newCat;
    @FXML
    private Button saveCat;
    private Catbean catbean;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    
    public void init(int id){
        catbean= new Catbean();
        catname.setText(catbean.getDataRecord((long)id).getCatName());
        catdescription.setText(catbean.getDataRecord((long) id).getCatDescription());
    }

    private void fillDialog(){
        catname.setText(catbean.getCatName());
        catdescription.setText(catbean.getCatDescription());
    }
    
    @FXML
    private void goFirstRecord(ActionEvent event) {
        catbean.jumpToFirstRecord();
        fillDialog();
        
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        catbean.nextRecordBackward();
        fillDialog();
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        catbean.nextRecordForeward();
        fillDialog();
        
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
        catbean.jumpToLastRecord();
        fillDialog();
        
    }

    @FXML
    private void printCatdesc(ActionEvent event) {
    }

    @FXML
    private void newCat(ActionEvent event) {
          log.debug("Function entry newCat");
        catbean.createNewEmptyRecord();
         catname.setText("");
         catdescription.setText("");
        
        log.debug("Function exit newCat ");
        
        
    }

    @FXML
    private void saveCat(ActionEvent event) {
        log.debug("Function entry saveCat");
        catbean.setCatDescription(catdescription.getText());
        catbean.setCatName(catname.getText());
        
    }
    
}
