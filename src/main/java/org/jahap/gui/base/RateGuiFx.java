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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;

import org.jahap.CurrentUser;
import org.jahap.business.acc.revaccountsbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.ratesbean;
import org.jahap.business.base.vattypesbean;
import org.jahap.entities.base.Rates;
import org.jahap.sreport.ratereports;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class RateGuiFx implements Initializable, SearchResultListener {
         @FXML
	private TextField ratecode_fxtextfield;
         @FXML
	 private TextField ratename_fxtextfield;
          @FXML           
	 private TextField RateBasePrice_fxtextfield;
    @FXML
    private Button search;

    private static ratesbean rates;
    private List<TextField> textfields;
    private SearchResult searchresults;
    private static final long ratesid=0;
     @FXML
    private CheckBox OvernightRate_fxCheckBox;
    @FXML
    private Button firstRecord_fxbutton;
    @FXML
    private Button oneRecordBackward_fxbutton;
    @FXML
    private Button oneRecordForward_fxbutton;
    @FXML
    private Button lastRecord_fxbutton;
    private CurrentUser cuser;
    @FXML
    private ChoiceBox<String> revAccount;
    @FXML
    private ChoiceBox<String> vat;
    @FXML
    private Button newRate;
    @FXML
    private Button saveRate;
    private revaccountsbean revAccbean;
    
    private vattypesbean vbean;
	@FXML
	private TextField name;
	@FXML
	private ChoiceBox<?> type;
	@FXML
	private TextArea jobdefinition;
	@FXML
	private Button printRate;
	
	private Hotelbean hbean;
	
	private  DecimalFormat DecFormatter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbean=new vattypesbean();
        textfields=new ArrayList<>();
         textfields.add(ratecode_fxtextfield);
         revAccbean= new revaccountsbean();
         hbean= new Hotelbean();
         DecFormatter= new DecimalFormat(hbean.getHotelNumberformat());
         
         textfields.add(ratename_fxtextfield);
         
         textfields.add(RateBasePrice_fxtextfield);
         
         rates= new ratesbean();
         searchresults = new SearchResult();
         searchresults.addIDListener(this);
         cuser= CurrentUser.getCurrentUser();
         if(!cuser.isIsAdmin())saveRate.setVisible(false);
         
        ObservableList<String> data= FXCollections.observableList(vbean.SearchForVatTypeString(null));
         ObservableList<String> datas= FXCollections.observableList(revAccbean.SearchForRevAccountString());
         revAccount.setItems(datas);
         vat.setItems(data);
         
    }    


    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        rates.nextRecordBackward();
        FillWithSelectedData();
        
        
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        rates.nextRecordForeward();
        FillWithSelectedData();
    }


    @FXML
    private void searchAdress(ActionEvent event) {
    }

    /**
     *
     * @param event
     * @throws JRException
     */
    @FXML
    protected void printAdress(ActionEvent event) throws JRException {
         List<Rates> adl= new ArrayList<>();
       adl= rates.getCurrentRate();
        
        ratereports ARP = new ratereports();
        ARP.singleRateReport(adl);
        
    }
    
    
    public void init(long id){
        rates = new ratesbean();
        rates.movetoDataRecordId(id);
                      
              FillWithSelectedData();
        
        
        
    }

    public void idinfo(SearchResultEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void FillWithSelectedData() {
        ratename_fxtextfield.setText(rates.getName());
        ratecode_fxtextfield.setText(rates.getCode());
        vat.getSelectionModel().select(this.DecFormatter.format(rates.getVattype().getPercentage()));
       
        //vat.setValue(this.DecFormatter.format(rates.getVattype().getPercentage()).toString());
        revAccount.setValue(rates.getRevaccount().getName());
        RateBasePrice_fxtextfield.setText(DecFormatter.format(rates.getPrice()));
        OvernightRate_fxCheckBox.setSelected(rates.getOvernight());
        
    }

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

    @FXML
    private void newRate(ActionEvent event) {
        ratecode_fxtextfield.setText("");
        ratename_fxtextfield.setText("");
        RateBasePrice_fxtextfield.setText("");
        rates.createNewEmptyRecord();
        
    }

    @FXML
    private void saveRate(ActionEvent event) throws ParseException {
        rates.setCode(ratecode_fxtextfield.getText());
        rates.setName(ratename_fxtextfield.getText());
        rates.setOvernight(OvernightRate_fxCheckBox.selectedProperty().get());
        rates.setPrice(DecFormatter.parse(RateBasePrice_fxtextfield.getText()).doubleValue());
        rates.setVattype(vat.getSelectionModel().getSelectedIndex()+1);
        rates.setRevaccount(revAccount.getSelectionModel().getSelectedIndex()+1);
        rates.saveRecord();
    }
    
}
