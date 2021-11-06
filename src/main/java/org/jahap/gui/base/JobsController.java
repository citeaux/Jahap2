/*
 * The MIT License
 *
 * Copyright 2015 Open Jahap Community.
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jahap.jobs.Jobsbean;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class JobsController implements Initializable {
	@FXML
	private Button firstRecord_fxbutton;
	@FXML
	private Button oneRecordBackward_fxbutton;
	@FXML
	private Button oneRecordForward_fxbutton;
	@FXML
	private Button lastRecord_fxbutton;
	@FXML
	private TextField name;
	@FXML
	private ChoiceBox<String> type;
	@FXML
	private TextArea jobdefinition;
	@FXML
	private Button search;
	@FXML
	private Button printRate;
	@FXML
	private Button newRate;
	@FXML
	private Button saveRate;
	
	private Jobsbean jbean;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}	

	
	public void init(long id ){
		
		jbean=new Jobsbean();
		filldialog(id);
		ObservableList<String> data= FXCollections.observableList(jbean.SearchForJobtypes());
		this.type.setItems(data);
	}
	
	private void filldialog(){
		
		this.name.setText(jbean.getName());
		this.jobdefinition.setText(jbean.getDefinition());
		this.type.setValue(jbean.getType());
	}
	
	private void filldialog(long id){
		
		String h=jbean.getDataRecord(id).getName();
		this.name.setText(jbean.getName());
		this.jobdefinition.setText(jbean.getDefinition());
		this.type.setValue(jbean.getType());
	}
	
	@FXML
	private void goFirstRecord(ActionEvent event) {
		jbean.jumpToFirstRecord();
		filldialog();
	}

	@FXML
	private void goOneRecordBackward(ActionEvent event) {
		jbean.nextRecordBackward();
		filldialog();
		
	}

	@FXML
	private void goOneRecordForward(ActionEvent event) {
		jbean.nextRecordForeward();
		filldialog();
		
	}

	@FXML
	private void goLastRecord(ActionEvent event) {
		jbean.jumpToLastRecord();
		filldialog();
		
	}

	@FXML
	private void searchAdress(ActionEvent event) {
	}

	@FXML
	private void printAdress(ActionEvent event) {
	}

	@FXML
	private void newRate(ActionEvent event) {
	}

	@FXML
	private void saveRate(ActionEvent event) {
	}
	
}
