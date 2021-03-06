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

package org.jahap.gui;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import org.jahap.business.acc.accountsbean;
import org.jahap.business.acc.cscbean;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.res.occbean;
import org.jahap.entities.acc.Csc;
import org.jahap.entities.res.Occ;
import org.jahap.gui.acc.viewAccountPositionsProperty;

/**
 * FXML Controller class
 *
 * @author russ
 */
public class CscGuiFx implements i18n, Initializable {
    @FXML
    private TitledPane x1;
        private TableColumn<viewAccountPositionsProperty, String>id_csc_tablefx;  
    @FXML
    private TableColumn<viewCSCpositionProperty, String> overnight_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> from_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> to_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> amount_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> service_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> price_csc_tablefx;
    @FXML
    private TableColumn<viewCSCpositionProperty, String> total_csc_tablefx;
    @FXML
    private Button ok;
    @FXML
    private Button addRate;
    @FXML
    private Button editRate;
    @FXML
    private Button CancelRate;
    @FXML
    private Button printRate;

    private cscbean cscrates;
    private List<Csc> cscpos;
    private  accountsbean acc;
    final ObservableList<viewCSCpositionProperty> datam=FXCollections.observableArrayList();;
    private List<viewCSCpositionProperty> haku = new ArrayList<viewCSCpositionProperty>();
    @FXML
    private TableView  csc_fxview;
    @FXML
    private TextField resfrom_Textfield;
    @FXML
    private TextField resto_Textfield;
    @FXML
    private TextField room_Textfield;
    @FXML
    private TextField guest_Textfield;
    private occbean occ;
    private Hotelbean hbean;
	
    private  DecimalFormat DecFormatter;
    private  SimpleDateFormat DFormat;
    @FXML
    private TitledPane x2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    public void init(long id){
    	hbean= new Hotelbean();
    	 DecFormatter= new DecimalFormat(hbean.getHotelNumberformat());
         
         DFormat= new SimpleDateFormat(hbean.getHoteldateformat());
    	
    	acc=new accountsbean();
        acc.getDataRecord(id);
        occ = new occbean();
        List<Occ> occlist=new ArrayList<Occ>();
        inittable();
        occlist=occ.SearchForOccforRes(acc.getReservation());
        resfrom_Textfield.setText(DFormat.format(acc.getReservation().getArrivaldate()));
        resto_Textfield.setText(DFormat.format(acc.getReservation().getDeparturedate()));
        guest_Textfield.setText(acc.getAddress().getName());
        room_Textfield.setText(occlist.get(1).getRoom().getCode());
        
    }
    
    void inittable(){
        Csc zw= new Csc();
        viewCSCpositionProperty kl;
        
        
        
        for(Iterator<Csc> iCscPos=acc.getCscCollection().iterator();iCscPos.hasNext();){
             kl = new viewCSCpositionProperty();
             zw= iCscPos.next();
             kl.setAmount(zw.getAmount());
             kl.setPrice(zw.getPrice());
             kl.setFrom(zw.getFromdate());
             kl.setTo(zw.getTodate());
             kl.setOvernight(zw.getRate().getOvernight());
             kl.setRateId(zw.getRate().getId());
             kl.setService(zw.getService());
             datam.add(kl) ;
        }
        
        
        // #################  ID       
            id_csc_tablefx  = new TableColumn<viewAccountPositionsProperty, String>("idx");
             id_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewAccountPositionsProperty, String>("idx")); 
        
         // ############### Overnight
                
                 
             overnight_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("overnightCsC"));
             overnight_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                              
                                setText(item);
                               
                          
                         }           

                         
                          
                         
                      };
                  }
                });  
             // ############### Price
             
              price_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("priceCsC"));
             price_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                                 
                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                              
                               setText(item);
                               
                          
                         }   
                         
                          
                         
                      };
                  }
                });  
              
             // ############### amount
             
              amount_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("amountCsC"));
             amount_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                          }
                         
                         @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                              setText(item);

                               
                          
                         }   
                          
                         
                      };
                  }
                });  
        
             
             
             // ############### from
        
              from_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("fromDate"));
             from_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                                 
//                       @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                               setText(item);

                               
                          
                         }   
                         
                          
                         
                      };
                  }
                });  
             
              
             // ############### from
        
              to_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("toDate"));
             to_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                                
                          @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                              setText(item);

                               
                          
                         }   

                         
                          
                         
                      };
                  }
                });  
             
                
             // ############### service
        
              service_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("serviceCsC"));
             service_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                                 
                    @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                               setText(item);

                               
                          
                         }   
                         
                          
                         
                      };
                  }
                });  
             
             
             // ############### total
        
              total_csc_tablefx.setCellValueFactory(new PropertyValueFactory<viewCSCpositionProperty, String>("totalCsC"));
             total_csc_tablefx .setCellFactory(new Callback<TableColumn<viewCSCpositionProperty, String>, TableCell<viewCSCpositionProperty, String>>() {
                  @Override
                  public TableCell<viewCSCpositionProperty, String> call(TableColumn<viewCSCpositionProperty, String> param) {
                      return new TableCell<viewCSCpositionProperty, String>() {
//                                 

                       @Override
                         public void updateItem(String item, boolean empty) {
                             
                             
                              super.updateItem(item, empty);
                              
                              setText(item);

                               
                          
                         }     
                          
                         
                      };
                  }
                });  
             
             csc_fxview.setItems(datam);
    }
    

    @FXML
    private void ok(ActionEvent event) {
    }

    @FXML
    private void addRate(ActionEvent event) {
    }

    @FXML
    private void editRate(ActionEvent event) {
    }

    @FXML
    private void CancelRate(ActionEvent event) {
    }

    @FXML
    private void printRate(ActionEvent event) {
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
