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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.Paymenttypesbean;
import org.jahap.entities.acc.Paymenttypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class PaymentguiController implements Initializable {
    @FXML
    private TextField totalSum;
    @FXML
    private ComboBox<String> paymentType;
    @FXML
    private Button PayAndPrint;
    Logger log = LoggerFactory.getLogger(PaymentguiController.class);
    private InterAccSearchResult iAccResult;
    private Paymenttypesbean pTypes;
    private ObservableList<String> options;
    private HashMap total;
    private Hotelbean hbean;
 	
    private  DecimalFormat DecFormatter;
    private  SimpleDateFormat DFormat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    
    public void init(double totalamount, InterAccSearchResult iAccResult){
        this.iAccResult=iAccResult;
        hbean= new Hotelbean();
      	 DecFormatter= new DecimalFormat(hbean.getHotelNumberformat());
           
           DFormat= new SimpleDateFormat(hbean.getHoteldateformat());
        total=new HashMap();
       pTypes=new Paymenttypesbean();
        totalSum.setText(DecFormatter.format(totalamount));
        total.put("total", totalamount);
        options = FXCollections.observableArrayList(pTypes.SearchForPaymenttypes());
        pTypes.SearchForPaymenttypes().forEach(name->System.out.println(name.toString()));
        try {
          for(String mjj:options){  
             paymentType.getItems().add(mjj);
          }
        } catch (Exception e) {
            log.debug("Yeah");
            e.printStackTrace();
        }
        
        
        
        
        
    }

    @FXML
    private void PayAndPrint(ActionEvent event) {
        List<Paymenttypes> hhh=new ArrayList<>();

        if(!"".equals(paymentType.getValue())){
                  hhh=pTypes.SearchForPaymenttypes(paymentType.getValue());
        }
        
    
       try {
            iAccResult.setDbRecordId(hhh.get(0).getId(), "paymenttype", total);
        } catch (Exception e) {
        }
        
        Stage jimbo= (Stage) PayAndPrint.getScene().getWindow();
         
        jimbo.close();
    }
    
}
