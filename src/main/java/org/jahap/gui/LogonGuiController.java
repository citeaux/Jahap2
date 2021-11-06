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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

import org.jahap.CurrentUser;
import org.jahap.MainEventResult;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;

import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.jahap.config.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>
 */
@Component
@FxmlView("../../../fxml/LogonGui.fxml")
public class LogonGuiController implements i18n {
    Logger log = LoggerFactory.getLogger(LogonGuiController.class);



    @FXML
    private Button LoginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<String> loginName;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private ChoiceBox<String> database_title;

    @FXML
    private TextField testtext;


    private ClientConfig configlogin;
    private MainEventResult mEv;
    ResourcenManager jk;
	@FXML
	private ChoiceBox<Locale> language;
    /**
     * Initializes the controller class.
     */

    @FXML
    public void initialize() {
	    log.debug("Function entry initialize ");
	  Resourcen kl=new Resourcen();
	  jk=kl.getResourcenManager();
      configlogin=ClientConfig.getInstance();
        final ObservableList<String> datamr=FXCollections.observableArrayList(configlogin.getConfigListUser());     
        final ObservableList<String> datams=FXCollections.observableArrayList(configlogin.getConfigList());
	  final ObservableList<Locale> datam=FXCollections.observableArrayList(jk.getAvailableLocales());
        passwordfield.setEditable(true);
        passwordfield.setText("_");
         language.setItems(datam);

         loginName.setItems(datamr);
         try {
            loginName.setValue(configlogin.getConfigLastUser().getUserloginsetup_login());
        } catch (Exception e) {
            log.debug("Error whiel setting login prefrence ");
        }
       
        try {
            language.setValue(datam.stream().filter(u->u.getLanguage().equals(configlogin.getConfigLastUser().getUserloginsetup_language())).findFirst().get());
        }catch (Exception e){
             log.debug("Error while setting language prefernce");
             e.printStackTrace();
                     
        }
                 
         
         //datam.stream().filter(u->u.getLanguage().equals(configlogin.getConfigLastUser().getUserloginsetup_language()));
         database_title.setItems(datams);
         database_title.setValue(configlogin.getConfigLastUser().getUserloginsetup_prefereddatabase());
	    log.debug("Function exit initialize");
    }    

    public void init(MainEventResult mEv){
	    log.debug("Function entry init");

       this.mEv=mEv;

	    log.debug("Function exit init");

    }



    @FXML
    private void loginEvent(ActionEvent event) {
        
	    log.debug("Function entry loginEvent");
	     this.login();
        log.debug("Function exit loginEvent ");
    }

    
    private void login(){
        log.debug("Function entry login");

        jk.activateLocale(language.getValue());
        log.debug("Get PasswordText" + passwordfield.getText());
        passwordfield.setText("TEST");
        log.debug("Get PasswordText" + testtext.getText());
        cancelButton.setText(passwordfield.getText());
        JahapDatabaseConnector hhh=JahapDatabaseConnector.getConnector(loginName.getValue(), passwordfield.getText(),configlogin.getConfigitemAndSet(database_title.getValue()));
           
         Stage jimbo= (Stage) LoginButton.getScene().getWindow();
         boolean admin=false;
         if(loginName.getValue().equals("root")) admin=true;
         CurrentUser hh = CurrentUser.getCurrentUser(loginName.getValue(), admin);
         
         this.mEv.setDbRecordId(true, "WW");
        jimbo.close();
    
        log.debug("Function exit login ");
    
    }
    
    
    @FXML
    private void cancelAction(ActionEvent event) {
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


    private void PasswordKeyReleased(KeyEvent event) {
        log.debug("Get PasswordText" + passwordfield.getText() +"--");
       // passwordfield.getProperties(currentValue)
       //if (event.getCode().equals(KeyCode.ENTER)){this.login();}
        
    }
}
