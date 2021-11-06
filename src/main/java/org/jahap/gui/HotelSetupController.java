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

package org.jahap.gui;

import org.jahap.gui.base.ListDialogAddressController;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.jahap.business.base.Choicebean;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.choicegroups;
import org.jahap.business.base.country;
import org.jahap.business.base.countrybean;
import org.jahap.business.base.currency;
import org.jahap.business.base.currencybean;
import org.jahap.business.base.language;
import org.jahap.business.base.languagebean;
import org.jahap.gui.res.InterResSearchResult;
import org.jahap.gui.res.InterResSearchResultEvent;
import org.jahap.gui.res.InterResSearchResultListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
public class HotelSetupController implements i18n, Initializable, InterResSearchResultListener {
    Logger log = LoggerFactory.getLogger(HotelSetupController.class);
    
    @FXML
    private TextField hotelStreet;
    @FXML
    private TextField hotelZipCode;
    @FXML
    private TextField hotelCity;
    @FXML
    private ChoiceBox<String> hotelCountryChoice;
    @FXML
    private TextField hotelPhone;
    @FXML
    private TextField hotelEmail;
    @FXML
    private TextField hotelInternet;
    @FXML
    private TextField hotelFax;
    @FXML
    private TextField hotelCode;
    @FXML
    private TextField hotelName;
    @FXML
    private ChoiceBox<String> hotelLanguageChoice;
    @FXML
    private ChoiceBox<String> hotelCurrencyChoice;
    @FXML
    private ChoiceBox<String> DateFormat;
    @FXML
    private ChoiceBox<String> DecimalFormat;
    @FXML
    private TextArea footerText;
    @FXML
    private TextArea bankAccountData1;
    @FXML
    private TextArea BankAccountData2;
    @FXML
    private Button search;
    @FXML
    private Button create;
    @FXML
    private Button save;
    private addressbean addresses;
    private InterResSearchResult ressearchresult;
    private int HotelAdressId;
    private Hotelbean hbean;
    private countrybean counBean;
     private currencybean currBean;
     private languagebean  langBean;
     private Choicebean choicebean;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        log.debug("Function entry initialize");
                
              
                        
         
        addresses= new addressbean();
        hbean =  new Hotelbean();
         ressearchresult=new InterResSearchResult();
            ressearchresult.addIDListener(this);
        
            choicebean= new Choicebean();
         counBean = new countrybean();
        langBean = new languagebean();
        currBean = new currencybean();
         ObservableList<String> data= FXCollections.observableList(counBean.SearchForCountry(country.name));
     ObservableList<String> datal= FXCollections.observableList(langBean.SearchForLanguage(language.name));
     ObservableList<String> datac= FXCollections.observableList(currBean.SearchForCurrency(currency.name));
     ObservableList<String> datap= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.dateformat)); 
     ObservableList<String> dataz= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.numberformat)); 
        hotelCountryChoice.setItems(data);
        hotelCurrencyChoice.setItems(datac);
        hotelLanguageChoice.setItems(datal);
        DateFormat.setItems(datap);
        DecimalFormat.setItems(dataz);
        hbean.getDataRecord(1);
        
        log.debug("Function exit initialize ");
        fillDialog();
    }    

    @FXML
    private void searchAddress(ActionEvent event) throws IOException {
                Stage stage = new Stage();
        String fxmlFile = "/fxml/base/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
       

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(ressearchresult,this,"hoteladdress");
       
        
        stage.showAndWait();
        
    }

    @FXML
    private void createAddress(ActionEvent event) {
    }

    private void fillDialog(){
        log.debug("Function entry fillDialog");
            
            hotelName.setText(hbean.getHotelName());
            hotelCode.setText(hbean.getHotelCode());
            BankAccountData2.setText(hbean.getHotelBankaccountdata2());
            bankAccountData1.setText(hbean.getHotelBankaccountdata1());
            footerText.setText(hbean.getHotelFootertext());
            
            HotelAdressId = hbean.getId();
            
           try {
            hotelCity.setText(hbean.getHotelAdress().getCity());
            hotelZipCode.setText(hbean.getHotelAdress().getZipcode());
            
            hotelStreet.setText(hbean.getHotelAdress().getStreet());
            hotelInternet.setText(hbean.getHotelAdress().getHomepage());
            
            hotelPhone.setText(hbean.getHotelAdress().getPhone());
            
            hotelCountryChoice.setValue(hbean.getHotelCountry().getCountryName());
            hotelCurrencyChoice.setValue(hbean.getHotelCurrency().getCurrencyName());
            hotelLanguageChoice.setValue(hbean.getHotelLanguage().getLanguageName());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
             DateFormat.setValue(hbean.getHoteldateformat());
             DecimalFormat.setValue(hbean.getHotelNumberformat());
             
       
        log.debug("Function exit fillDialog");
                
    }
    
    @FXML
    private void saveHotelData(ActionEvent event) {
         log.debug("Function entry saveHotelData");
        
         hbean.setHotelAdress(addresses.getDataRecord(HotelAdressId));
         hbean.setHotelBankaccountdata1(bankAccountData1.getText());
         hbean.setHotelBankaccountdata2(BankAccountData2.getText());
         hbean.setHotelCode(hotelCode.getText());
         hbean.setHotelFootertext(footerText.getText());
         hbean.setHotelCountry(hotelCountryChoice.getSelectionModel().getSelectedIndex()+1);
         hbean.setHotelLanguage(hotelLanguageChoice.getSelectionModel().getSelectedIndex()+1);
         hbean.setHotelCurrency(hotelCurrencyChoice.getSelectionModel().getSelectedIndex()+1);
         hbean.setHotelName(hotelName.getText());
         hbean.setHotelNumberformat(DecimalFormat.getSelectionModel().getSelectedItem());
         hbean.setHoteldateformat(DateFormat.getSelectionModel().getSelectedItem());
         hbean.saveRecord();
         log.debug("Function exit saveHotelData");
                 
    }

    @Override
    public void idinfo(InterResSearchResultEvent e) {
        log.debug("Function entry InterResSearchResultEvent ");
        if(e.getTableNameofSource()=="hoteladdress"){
                 hotelName.setText(addresses.getDataRecord(e.getDbRecordId()).getName());
                 hotelCity.setText(addresses.getDataRecord(e.getDbRecordId()).getCity());
                 hotelEmail.setText(addresses.getDataRecord(e.getDbRecordId()).getEmail());
                 hotelInternet.setText(addresses.getDataRecord(e.getDbRecordId()).getHomepage());
                 hotelPhone.setText(addresses.getDataRecord(e.getDbRecordId()).getPhone());
                 hotelStreet.setText(addresses.getDataRecord(e.getDbRecordId()).getStreet());
                 hotelZipCode.setText(addresses.getDataRecord(e.getDbRecordId()).getZipcode());
                 hotelCountryChoice.setValue(addresses.getDataRecord(e.getDbRecordId()).getCountry().getCountryName());
                 hotelCurrencyChoice.setValue(addresses.getDataRecord(e.getDbRecordId()).getCurrency().getCurrencyName());
                  hotelLanguageChoice.setValue(addresses.getDataRecord(e.getDbRecordId()).getLanguage().getLanguageName());
                  
                  HotelAdressId=(int) e.getDbRecordId();
        }  
        
        log.debug("Function exit InterResSearchResultEvent");
        
        
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
}
