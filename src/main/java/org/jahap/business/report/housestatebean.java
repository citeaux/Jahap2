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
package org.jahap.business.report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jahap.entities.JahapDatabaseConnector;

/**
 *
 * @author Sebastian
 */
public class housestatebean {
      JahapDatabaseConnector dbhook;
      
      class housefigures{
          int alldepartures;
            int allarrivals;
            int allrooms;
            int occrooms;
            int freerooms;

        public int getAlldepartures() {
            return alldepartures;
        }

        public void setAlldepartures(int alldepartures) {
            this.alldepartures = alldepartures;
        }

        public int getAllarrivals() {
            return allarrivals;
        }

        public void setAllarrivals(int allarrivals) {
            this.allarrivals = allarrivals;
        }

        public int getAllrooms() {
            return allrooms;
        }

        public void setAllrooms(int allrooms) {
            this.allrooms = allrooms;
        }

        public int getOccrooms() {
            return occrooms;
        }

        public void setOccrooms(int occrooms) {
            this.occrooms = occrooms;
        }

        public int getFreerooms() {
            return freerooms;
        }

        public void setFreerooms(int freerooms) {
            this.freerooms = freerooms;
        }
          
      }
    
     housefigures figures; 
      
    public housestatebean() throws SQLException {
        dbhook = JahapDatabaseConnector.getConnector();
         getFreshFigures();
    
        
    }
      
    public int getFreeRooms(){
        return figures.getFreerooms();
    }
    
    public int getAllDepartures(){
        return figures.getAlldepartures();
    }
    
    public int getAllarrivals(){
        return figures.getAllarrivals();
    }
    
    public int getAllOccRooms(){
        return figures.getOccrooms();
        
        
    }
    
    
    public int getAllRooms(){
        
        return figures.getAllrooms();
    }
      
    
    public void getFreshFigures() throws SQLException{
         Connection con= dbhook.getclassicConnection2();
         figures = new housefigures();
        Statement stmt = null;
        String query = "SELECT * FROM \"ArrDepAll\" ";
        try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            figures.setAlldepartures(rs.getInt("alldepartures"));
            figures.setAllarrivals(rs.getInt("allarrivals"));
            figures.setAllrooms(rs.getInt("allrooms"));
            figures.setOccrooms(rs.getInt("occrooms"));
            
            
        }
    } catch (SQLException e ) {
        System.out.printf(e.getMessage());
    } finally {
        if (stmt != null) { stmt.close(); }
    }
        
        query = "SELECT * FROM \"freerooms\" ";
        try {
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            figures.setFreerooms(rs.getInt("freeroooms"));
           
            
            
        }
    } catch (SQLException e ) {
        System.out.printf(e.getMessage());
    } finally {
        if (stmt != null) { stmt.close(); }
    }
    }
    
}
