/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jahap.gui.acc;

/**
 *
 * @author russ
 */



import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;

/**
 *
 * @author russ
 */
public class BillTabs{
        
        @FXML
         private TableColumn<viewAccountPositionsProperty, String>id_Account_tablefx;                                                   
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> date_Account_tablefx;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cService_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cAmount_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dService_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dAmount_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cPrice_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dPrice_Account_tablefxColumn;
    
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> cTotal_Account_tablefxColumn;
        @FXML
        private TableColumn<viewAccountPositionsProperty, String> dTotal_Account_tablefxColumn;
        
	@FXML
	private  TableColumn CreditMainColumn;
	
	@FXML
	private  TableColumn DebitMainColumn;
	
	 Logger log = LoggerFactory.getLogger(BillTabs.class);
         private ObservableList<viewAccountPositionsProperty> datamk=FXCollections.observableArrayList();
         private long billno;
         private Tab billtab;
        private TableView ggk;
          private  ResourcenManager jk; 
          
         
        public BillTabs(long billno) {
        	 log.debug("Function entry BillTabs(billno)"); 
            Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
           ggk = new TableView();
             ggk.setPrefHeight(501);
             ggk.setPrefWidth(829);
             this.setBillno(billno);
             
             billtab = new Tab();
            billtab.setText(String.valueOf(billno));
            billtab.setContent(ggk);
            log.debug("Function exit BillTabs(billno)"); 
        }     
        
        
        public String getBillname(){
            return this.billtab.getText();
        }
        
        public List<viewAccountPositionsProperty> removeSelectedPosiions(){
        	 log.debug("Function entry removeSelectedPositions"); 
            ObservableList<viewAccountPositionsProperty>  gg = FXCollections.observableArrayList();
            gg=ggk.getSelectionModel().getSelectedItems();
            boolean jk=false;
            // checks for billed position which are not temp bills
            for(Iterator<viewAccountPositionsProperty>jjk=gg.iterator();jjk.hasNext();){
                  if(jjk.next().getBillno()!=0){
                      jk=true;
                  }
            }
            if(jk==false){
             ggk.getItems().remove(gg);
            }
            log.debug("Function exit removeSelectedPositions"); 
            return gg;
        }
        
        
        
        public BillTabs(String billname) {
        	 log.debug("Function entry BilltabS(billname)"); 
		          Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
           ggk = new TableView();
             ggk.setPrefHeight(501);
             ggk.setPrefWidth(829);
             this.setBillno(billno);
             
             billtab = new Tab();
            billtab.setText(billname);
            billtab.setContent(ggk);
            log.debug("Function exit Billtabs(billname)"); 
        }     

        public TableView getGgk() {
            return ggk;
        }

        public void setGgk(TableView ggk) {
            this.ggk = ggk;
        }
             
        
        
        public long getBillno() {
            return billno;
        }

        public void setBillno(long billno) {
            this.billno = billno;
        }

        public Tab getBilltab() {
            return billtab;
        }

        public void setBilltab(Tab billtab) {
            this.billtab = billtab;
        }
        
        public void addPosition(viewAccountPositionsProperty jj){
        	 log.debug("Function entry addposition"); 
            try {
                this.datamk.add(jj);
            } catch (Exception e) {
                System.out.println("---<BillTabs>---");
                System.out.println(e.toString());               
            }
            
            log.debug("Function exit addposition"); 
        }
        
        public void addPositions(ObservableList<viewAccountPositionsProperty> jj){
        	 log.debug("Function entry addposition(s)"); 
            
            boolean addAll = this.datamk.addAll(jj);
//            ggk.getItems().removeAll();
//            
//            ggk.getItems().add(datamk);
//            billtab.setContent(ggk);
            
            this.ggk.setVisible(false);
            this.ggk.setVisible(true);
            
            log.debug("Function exit addposition(s)"); 
        }
        
        
        public ObservableList<viewAccountPositionsProperty>getList(){
            return this.datamk;
        }
        
        public void createTabel(){
            buildTable(this.ggk);
        }
        
        public void refreshTab(){
             
        }
        
        
          private void buildTable(TableView ko){
        	  log.debug("Function entry buidlTable"); 
        	  
              // #################  ID       
            this.id_Account_tablefx  = new TableColumn<viewAccountPositionsProperty, String>("idx");
             this.id_Account_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("idx"));
                     
           
            
             
             
              // ############### Date
             this.date_Account_tablefx=new TableColumn<viewAccountPositionsProperty, String>("Date");  
             date_Account_tablefx.setCellValueFactory(cellData -> cellData.getValue().getRateDateStringProperty());
             date_Account_tablefx.setText(jk.getFxLangString("TableColumn.rateDateString"));
             this.date_Account_tablefx .setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
                  public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             Tooltip tol=new Tooltip("Info");
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                                try {
									if(tl<=datamk.size()-1){
									 if(datamk.get(tl).isDebit()==true){
									 // DEV: Stylsheet implement
									 // setStyle("-fx-font-style: italic;");
									 
									 }
									 if(datamk.get(tl).isCanceled()==true){
									  setTextFill(Color.RED);
									   tol.setText("This position is canceled");
									    Tooltip.install(this, tol);
									 }
									 if(datamk.get(tl).isBilled()==true || datamk.get(tl).getBillnamestring()!="" && !datamk.get(tl).getBillnamestring().contentEquals("ZEROBILL")){
									    setTextFill(Color.GREY);
									    String texttip=new String();
									    texttip="This position is billed";
									      if(datamk.get(tl).isCanceled()==true){
									         texttip= texttip + " and canceled";
									      }
									    tol.setText(texttip);
									    Tooltip.install(this, tol);
									 }
									 setText(item);
                        }
								} catch (Exception e) {
									
									e.printStackTrace();
								}
                               
                                setText(item);
//                               }
                          }
                         
                         
                          
                         
                      };
                  }
                });  
            ko.getColumns().add(this.date_Account_tablefx);
                          
              // #############cAmount
            this.cAmount_Account_tablefxColumn=new TableColumn<viewAccountPositionsProperty, String>("Amount"); 
            cAmount_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().camountstringProperty());
            cAmount_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.camountstring"));
             this.cAmount_Account_tablefxColumn.setCellFactory(new Callback<TableColumn<viewAccountPositionsProperty, String>, TableCell<viewAccountPositionsProperty, String>>() {
                  @Override
              public TableCell<viewAccountPositionsProperty, String> call(TableColumn<viewAccountPositionsProperty, String> param) {
                      return new TableCell<viewAccountPositionsProperty, String>() {
                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                               int tl=getIndex();

                                try {
									if(tl<=datamk.size()-1){
									 if(datamk.get(tl).isBilled()==true || datamk.get(tl).getBillnamestring()!="" && !datamk.get(tl).getBillnamestring().contentEquals("ZEROBILL")){
									 setTextFill(Color.GREY);
									    String texttip=new String();
									    texttip="This position is billed";
									     
									 }
									 setText(item);
                      
                   }
								} catch (Exception e) {
									
									e.printStackTrace();
								}
                          
                         }
                      };
                  }
                });  
           ko.getColumns().add(this.cAmount_Account_tablefxColumn);   
        
             // ################ cPosname
           this.cService_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("Posname");  
           cService_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().cpositionnameProperty());
           cService_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.cPosition"));
           ko.getColumns().add(this.cService_Account_tablefxColumn);   
             
             
              // #############cPrice
           this.cPrice_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("cPrice");
           cPrice_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().cpricestringProperty());
           cPrice_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.cpricestring"));
             ko.getColumns().add(this.cPrice_Account_tablefxColumn);   
              // ################ cTotal
             this.cTotal_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("cTotal");
             cTotal_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().ctotalProperty());
             cTotal_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.ctotal")); 
              ko.getColumns().add(this.cTotal_Account_tablefxColumn);   
             
             
          
             
            
             
             // ##############dPosname
              this.dService_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("Posname");
              dService_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().dpositionnameProperty());
              dService_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.dpositionname"));
              
             
              this.dService_Account_tablefxColumn.setStyle("-fx-table-cell-border-color: grey");
              ko.getColumns().add(this.dService_Account_tablefxColumn);   
          
             // #################dAmount
              this.dAmount_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("dAmount");
              dAmount_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().damountstringProprty());
              dAmount_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.damountstring"));
            ko.getColumns().add(this.dAmount_Account_tablefxColumn);   
              
                // #############dPrice
            this.dPrice_Account_tablefxColumn  = new TableColumn<viewAccountPositionsProperty, String>("dPrice");
            dPrice_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().dpricestringProperty());
            dPrice_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.dpricestring"));
             ko.getColumns().add(this.dPrice_Account_tablefxColumn);   
              // ################ dTotal
             this.dTotal_Account_tablefxColumn = new TableColumn<viewAccountPositionsProperty, String>("dTotal");       
             dTotal_Account_tablefxColumn.setCellValueFactory(cellData -> cellData.getValue().dtotalProperty());
             dTotal_Account_tablefxColumn.setText(jk.getFxLangString("TableColumn.dtotal"));
           ko.getColumns().add(this.dTotal_Account_tablefxColumn);   
              
             
           
              
          
  
             
             
          // List<viewAccountPositionsProperty> accview=new ArrayList<viewAccountPositionsProperty>();
   
    
   
   
   
             
  
       ko.setItems(this.datamk);
       log.debug("Function exit buildtable"); 
    }
        
    }
