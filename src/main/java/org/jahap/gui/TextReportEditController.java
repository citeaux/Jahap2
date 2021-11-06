/*
 * The MIT License
 *
 * Copyright 2018 Open Jahap Community.
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

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import net.sf.jasperreports.engine.JRException;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.jahap.business.base.Choicebean;
import org.jahap.business.base.currencybean;
import org.jahap.business.base.language;
import org.jahap.business.base.languagebean;
import org.jahap.entities.Reports;

import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;
import org.jahap.sreport.reportsbean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * FXML Controller class
 *
 * @author demokrite
 */
public class TextReportEditController implements Initializable {
    Logger log = LoggerFactory.getLogger(TextReportEditController.class);

    @FXML
    private Button newbutton;
    @FXML
    private Button print;
    @FXML
    private Button cancel;
    @FXML
    private BorderPane BorderPane;
    @FXML
    private Button compile;
    @FXML
    private Button save;
    private CodeArea codeArea;

    private  ResourcenManager jk; 
    
    private reportsbean reportsb;
    
    
    private static final Pattern XML_TAG = Pattern.compile("(?<ELEMENT>(</?\\h*)(\\w+)([^<>]*)(\\h*/?>))"
    		+"|(?<COMMENT><!--[^<>]+-->)");
     private static final Pattern ATTRIBUTES = Pattern.compile("(\\w+\\h*)(=)(\\h*\"[^\"]+\")");
    
    private static final int GROUP_OPEN_BRACKET = 2;
    private static final int GROUP_ELEMENT_NAME = 3;
    private static final int GROUP_ATTRIBUTES_SECTION = 4;
    private static final int GROUP_CLOSE_BRACKET = 5;
    private static final int GROUP_ATTRIBUTE_NAME = 1;
    private static final int GROUP_EQUAL_SYMBOL = 2;
    private static final int GROUP_ATTRIBUTE_VALUE = 3;
    
    private static final String sampleCode = String.join("\n", new String[] {
    		"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
    		"<!-- Sample XML -->",
    		"< orders >",
    		"	<Order number=\"1\" table=\"center\">",
    		"		<items>",
    		"			<Item>",
    		"				<type>ESPRESSO</type>",
    		"				<shots>2</shots>",
    		"				<iced>false</iced>",
    		"				<orderNumber>1</orderNumber>",
    		"			</Item>",
    		"			<Item>",
    		"				<type>CAPPUCCINO</type>",
    		"				<shots>1</shots>",
    		"				<iced>false</iced>",
    		"				<orderNumber>1</orderNumber>",
    		"			</Item>",
    		"			<Item>",
    		"			<type>LATTE</type>",
    		"				<shots>2</shots>",
    		"				<iced>false</iced>",
    		"				<orderNumber>1</orderNumber>",
    		"			</Item>",
    		"			<Item>",
    		"				<type>MOCHA</type>",
    		"				<shots>3</shots>",
    		"				<iced>true</iced>",
    		"				<orderNumber>1</orderNumber>",
    		"			</Item>",
    		"		</items>",
    		"	</Order>",
    		"</orders>"
    		});
    
    
    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
    	
        Matcher matcher = XML_TAG.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        while(matcher.find()) {
        	
        	spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
        	if(matcher.group("COMMENT") != null) {
        		spansBuilder.add(Collections.singleton("comment"), matcher.end() - matcher.start());
        	}
        	else {
        		if(matcher.group("ELEMENT") != null) {
        			String attributesText = matcher.group(GROUP_ATTRIBUTES_SECTION);
        			
        			spansBuilder.add(Collections.singleton("tagmark"), matcher.end(GROUP_OPEN_BRACKET) - matcher.start(GROUP_OPEN_BRACKET));
        			spansBuilder.add(Collections.singleton("anytag"), matcher.end(GROUP_ELEMENT_NAME) - matcher.end(GROUP_OPEN_BRACKET));

        			if(!attributesText.isEmpty()) {
        				
        				lastKwEnd = 0;
        				
        				Matcher amatcher = ATTRIBUTES.matcher(attributesText);
        				while(amatcher.find()) {
        					spansBuilder.add(Collections.emptyList(), amatcher.start() - lastKwEnd);
        					spansBuilder.add(Collections.singleton("attribute"), amatcher.end(GROUP_ATTRIBUTE_NAME) - amatcher.start(GROUP_ATTRIBUTE_NAME));
        					spansBuilder.add(Collections.singleton("tagmark"), amatcher.end(GROUP_EQUAL_SYMBOL) - amatcher.end(GROUP_ATTRIBUTE_NAME));
        					spansBuilder.add(Collections.singleton("avalue"), amatcher.end(GROUP_ATTRIBUTE_VALUE) - amatcher.end(GROUP_EQUAL_SYMBOL));
        					lastKwEnd = amatcher.end();
        				}
        				if(attributesText.length() > lastKwEnd)
        					spansBuilder.add(Collections.emptyList(), attributesText.length() - lastKwEnd);
        			}

        			lastKwEnd = matcher.end(GROUP_ATTRIBUTES_SECTION);
        			
        			spansBuilder.add(Collections.singleton("tagmark"), matcher.end(GROUP_CLOSE_BRACKET) - lastKwEnd);
        		}
        	}
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
}
    @FXML
    private TextField reportName;
    @FXML
    private TextField reportGroup;
    @FXML
    private TextField reportDescription;
    @FXML
    private ChoiceBox<String> reportLanguage;
    
     private languagebean  langBean;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Resourcen kk= new Resourcen();
 	    jk=kk.getResourcenManager();
        reportsb= new reportsbean();
        
        
        
        CodeArea codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText));
        });
        codeArea.replaceText(0, 0, sampleCode);
        
        BorderPane.setCenter(new VirtualizedScrollPane<>(codeArea));
        //BorderPane.getStylesheets().add(TextReportEditController.class.getResource("xml-highlighting.css").toExternalForm());
        
    
    }    

    public void init( int selectedreport){
    	    log.debug("Function entry init SelectedReport,Reportgroup");
	    Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
            reportName.setText("NewReport");
              langBean = new languagebean();   
      
            
            reportsb= new reportsbean();
           
            ObservableList<String> datal= FXCollections.observableList(langBean.SearchForLanguage(language.name));
            reportLanguage.setItems(datal);
            
            
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));

        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            codeArea.setStyleSpans(0, computeHighlighting(newText));
        });
        reportsb.setDataRecordId(selectedreport);
        
        reportName.setText("New " + reportsb.getName());
        reportGroup.setText(reportsb.getReportGroup());
        reportDescription.setText(reportsb.getDescription());
        reportLanguage.setValue(reportsb.getLanguage().getLanguageName());
        
        if(reportsb.getReportLayout()== null){
            codeArea.replaceText(0, 0, sampleCode);
          
        } else{
           codeArea.replaceText(0, 0, reportsb.getReportLayout());
        }
        BorderPane.setCenter(new VirtualizedScrollPane<>(codeArea));
log.trace(TextReportEditController.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        try {
            BorderPane.getStylesheets().add(TextReportEditController.class.getResource("/styles/xml-highlighting.css").toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
   
    }
    

    @FXML
    private void newbutton_pressed(ActionEvent event) {
    }

    @FXML
    private void print(ActionEvent event) {
    }


    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void compile(ActionEvent event) {
        try {
            reportsb.compileReport();
        } catch (JRException ex) {
            java.util.logging.Logger.getLogger(TextReportEditController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TextReportEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(ActionEvent event) {
        reportsb.setLanguage(langBean.getDataRecord(reportLanguage.getSelectionModel().getSelectedIndex()+1));
        reportsb.setDescription(reportDescription.getText());
        reportsb.setName(reportName.getText());
        reportsb.setReportLayout(codeArea.getText());
        reportsb.saveRecord();
    }
    
}
