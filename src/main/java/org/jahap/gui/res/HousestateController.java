/*
 * The MIT License
 *
 * Copyright 2016 Open Jahap Community.
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
package org.jahap.gui.res;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.jahap.business.report.housestatebean;

/**
 * FXML Controller class
 *
 * @author Sebastian
 */
public class HousestateController implements Initializable {

    @FXML
    private Label current_date;
    @FXML
    private Label total_amount_of_rooms;
    @FXML
    private Label total_free_rooms;
    @FXML
    private Label total_booked_rooms;
    @FXML
    private Label amount_of_arrivals;
    @FXML
    private Label amount_of_departures;
    @FXML
    private TableView<?> cat_table;
    @FXML
    private TableColumn<?, ?> cat_name;
    @FXML
    private TableColumn<?, ?> cat_free_rooms;
    @FXML
    private TableColumn<?, ?> cat_occ_rooms;
  
     housestatebean housestate;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO Dialog not finished, still in development
            housestate= new housestatebean();
            total_amount_of_rooms.setText(String.valueOf(housestate.getAllRooms()));
            total_free_rooms.setText(String.valueOf(housestate.getFreeRooms()));
            total_booked_rooms.setText(String.valueOf(housestate.getAllOccRooms()+ housestate.getAllarrivals()));
            amount_of_arrivals.setText(String.valueOf(housestate.getAllarrivals()));
            amount_of_departures.setText(String.valueOf(housestate.getAllDepartures()));
            
        } catch (SQLException ex) {
            Logger.getLogger(HousestateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    

    @FXML
    private void print(ActionEvent event) {
    }

    @FXML
    private void close(ActionEvent event) {
    }
    
}
