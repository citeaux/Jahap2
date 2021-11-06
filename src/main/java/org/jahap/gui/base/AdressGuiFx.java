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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import org.jahap.business.base.*;
import org.jahap.entities.base.Address;
import org.jahap.sreport.addressreports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/*
 * @author russ
 */
public class AdressGuiFx implements Initializable, AddressSearchResultListener {
    
    Logger log = LoggerFactory.getLogger(AdressGuiFx.class);
    
       @FXML
    private Button lastRecord_fxbutton;

    @FXML
    private TextField christianname_fxtextfield;

    @FXML
    private TextField phoneno_fxtextfield;

    @FXML
    private ComboBox<String> greeting;

    @FXML
    private TextField email_fxtextfield;

    @FXML
    private TextField name_fxtextfield;

    @FXML
    private Button printAdress;

    @FXML
    private Button firstRecord_fxbutton;

    @FXML
    private Button oneRecordForward_fxbutton;

    @FXML
    private ComboBox<String> titel;

    @FXML
    private Button search;

    @FXML
    private TextField city_fxtextfield;

    @FXML
    private ChoiceBox<String> CurrencyChoiceBox;

    @FXML
    private Button newadress;

    @FXML
    private ChoiceBox<String> CountryChoiceBox;

    @FXML
    private ChoiceBox<String> addresstype;

    @FXML
    private ChoiceBox<String> LanguageChoiceBox;

    @FXML
    private Button Save;

    @FXML
    private ComboBox<String> salutation;

    @FXML
    private TextField street_fxtextfield;

    @FXML
    private TextField zipcode_fxtextfield;

    @FXML
    private TextArea remarks;

    @FXML
    private Button oneRecordBackward_fxbutton;

    @FXML
    private TextField homepage;
    
    
     public static addressbean addresses;
     private List<TextField> textfields;
     private AddressSearchResult searchresults;
     private long addressid=0;
     private countrybean counBean;
     private currencybean currBean;
     private languagebean  langBean;
     private Choicebean choicebean;

    public void initialize(URL url, ResourceBundle rb) {
        addresses = new addressbean(); 
        setupform();
        addresses.createNewEmptyRecord();
        log.debug("Function exit initialize");
        
    }


    private void setupform(){
        log.debug("Function entry initialize");
        textfields=new ArrayList<TextField>();

        textfields.add(city_fxtextfield);
        textfields.add(phoneno_fxtextfield);
        textfields.add(email_fxtextfield);
        textfields.add(christianname_fxtextfield);
        textfields.add(zipcode_fxtextfield);
        textfields.add(street_fxtextfield);
        textfields.add(name_fxtextfield);


        
        searchresults= new AddressSearchResult();
        counBean = new countrybean();
        langBean = new languagebean();
        currBean = new currencybean();
        choicebean= new Choicebean();

        ObservableList<String> data= FXCollections.observableList(counBean.SearchForCountry(country.name));
        ObservableList<String> datal= FXCollections.observableList(langBean.SearchForLanguage(language.name));
        ObservableList<String> datac= FXCollections.observableList(currBean.SearchForCurrency(currency.name));
        ObservableList<String> datap= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.title));
        ObservableList<String> dataz= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.addresstype));
        ObservableList<String> dataui= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.greeting));
        ObservableList<String> dataop= FXCollections.observableList(choicebean.SearchForChoiceString(choicegroups.salutation));
        titel.setItems(datap);
        CountryChoiceBox.setItems(data);
        LanguageChoiceBox.setItems(datal);
        CurrencyChoiceBox.setItems(datac);
        addresstype.setItems(dataz);
        greeting.setItems(dataui);
        salutation.setItems(dataop);
        searchresults.addIDListener(this);
    }

    public void init(long id){
        log.debug("Function entry init  " + String.valueOf(id));
       addresses = new addressbean();
        addresses.setDataRecordId(id);
       setupform();
	
            FillWithSelectedData();
//        
        
        log.debug("Function exit init");
    }

    @FXML
    private void save(ActionEvent event) {
        
        log.debug("Function entry save");
        
        addresses.setChristianname(christianname_fxtextfield.getText());
        addresses.setCity(city_fxtextfield.getText());
        addresses.setEmail(email_fxtextfield.getText());
        addresses.setName(name_fxtextfield.getText());
        addresses.setPhone(phoneno_fxtextfield.getText());
        addresses.setStreet(street_fxtextfield.getText());
        addresses.setZipcode(zipcode_fxtextfield.getText());
        addresses.setCountry(CountryChoiceBox.getSelectionModel().getSelectedIndex()+1);
        addresses.setCurrency(CurrencyChoiceBox.getSelectionModel().getSelectedIndex()+1);
        addresses.setLanguage(LanguageChoiceBox.getSelectionModel().getSelectedIndex()+1);
        addresses.setTitel(titel.getValue());
        addresses.setRemarks(remarks.getText());
        addresses.setAddresstype(addresstype.getValue());
        addresses.setGreeting(greeting.getValue());
        addresses.setSalutation(salutation.getValue());
        addresses.saveRecord();
        
        log.debug("Function exit save");
    }

    @FXML
    private void newadress(ActionEvent event) {
        log.debug("Function entry newadress");
        
        for(int i=0;i<textfields.size();i++){
            textfields.get(i).setText("");
        }
        addresses.createNewEmptyRecord();
       
        log.debug("Function exit newadress");
    }

    @FXML
    private void searchAdress(ActionEvent event) throws IOException {
        
        log.debug("Function entry searchadress");
        Stage stage = new Stage();
        String fxmlFile = "/fxml/base/AddressList.fxml";
       
        FXMLLoader loader = new FXMLLoader();
        AnchorPane page= (AnchorPane) loader.load(getClass().getResourceAsStream(fxmlFile));

        
        Scene scene = new Scene(page);
     

        
        stage.setScene(scene);
        ListDialogAddressController controller= loader.<ListDialogAddressController>getController();
       controller.init(searchresults,this);
       
        
        stage.showAndWait();
        log.debug("Function exit searchadress");
    }

    @FXML
    private void goFirstRecord(ActionEvent event) {
    }

    @FXML
    private void goOneRecordBackward(ActionEvent event) {
        log.debug("Function entry goOneRecordBackward");
        addresses.nextRecordBackward(); 
        FillWithSelectedData();
        log.debug("Function exit goOneRecordBackward");
    }

    @FXML
    private void goOneRecordForward(ActionEvent event) {
        log.debug("Function entry goOneRecordForward");
        addresses.nextRecordForeward();
        FillWithSelectedData();
        log.debug("Function exit goOneRecordForward");
    }

    private void FillWithSelectedData(){
        log.debug("Function entry FillWithSelectedData");
        
        city_fxtextfield.setText(addresses.getCity());
        email_fxtextfield.setText(addresses.getEmail()); 
        name_fxtextfield.setText(addresses.getName());
        remarks.setText(addresses.getRemarks());
        christianname_fxtextfield.setText(addresses.getChristianname());
        phoneno_fxtextfield.setText(addresses.getPhone());
        street_fxtextfield.setText(addresses.getStreet());
        zipcode_fxtextfield.setText(addresses.getZipcode());
         try {
            CountryChoiceBox.setValue(addresses.getCountry().getCountryName());            
        } catch (Exception e) {
        }
      
        try {
            LanguageChoiceBox.setValue(addresses.getLanguage().getLanguageName());
        } catch (Exception e) {
        }
        try {
            CurrencyChoiceBox.setValue(addresses.getCurrency().getCurrencyName());
        } catch (Exception e) {
        }
        titel.setValue(addresses.getTitel());
        greeting.setValue(addresses.getGreeting());
        addresstype.setValue(addresses.getAddresstype());
        salutation.setValue(addresses.getSalutation());
        
        log.debug("Function exit FillWithSelectedData");
    }   

    @FXML
    private void goLastRecord(ActionEvent event) {
    }

    @FXML
    private void printAdress(ActionEvent event) throws JRException {
        log.debug("Function entry printAdress");
        Address adl= new Address();
        adl=addresses.getCurrentAddress();
        List<Address> jjk= new ArrayList<>();
        jjk.add(adl);

        addressreports ARP = new addressreports();
        ARP.singleAdressReport(jjk);
        
        log.debug("Function exit printAdress");
    }

       public  void idinfo(AddressSearchResultEvent e) {
           log.debug("Function entry idinfo");
                 //JOptionPane.showMessageDialog(null,e.getDbRecordId()+ e.getTableNameofSource() );
          if (e.getTableNameofSource()=="Address"){
              this.addressid=e.getDbRecordId();
              addresses.setDataRecordId(addressid);
                      
              FillWithSelectedData();
              
              
              log.debug("Function exit idinfo"); 
              
          }
              
         
          
        
        
    }

   
   

}
