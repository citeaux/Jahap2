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
package org.jahap.business.res;


import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.TestDatabase;
import org.jahap.business.base.addressbean;
import org.jahap.business.base.roomsbean;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Address;
import org.jahap.entities.base.Rooms;
import org.jahap.entities.res.Occ;
import org.jahap.entities.res.Res;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author russ
 */
public class occbeanTest {

    static Logger log = LoggerFactory.getLogger(occbeanTest.class);

    public occbeanTest() {
    }
    

    
    @Before
    public void setUp() throws Exception {
        log.debug("Function Begin: setUp");
       ClientConfig nc= ClientConfig.getInstance();
        TestDatabase hh= TestDatabase.getInstance();
        JahapDatabaseConnector con=JahapDatabaseConnector.getConnector(hh.getUser(),hh.getPassword(),nc.getConfigitemAndSet("test"));

            File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
        log.debug("Function End: setUp");
         cleanlyInsertDataset(dataSet);

    }
    
    protected static IDataSet getDataSet() throws Exception {

        log.debug("Function Begin: getDataSet");
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\res\\occ.xml")); //To change body of generated methods, choose Tools | Templates.
    }
    
    @After
    public void tearDown() throws Exception {
        log.debug("Function Begin: tearDown");
         File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
        log.debug("Function End: tearDown");
        cleanlyInsertDataset(dataSet);



        
    }
    
     private static void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
         log.debug("Function Begin: cleanlyInsertDataset");
         ClientConfig nc= ClientConfig.getInstance();
         TestDatabase hh= TestDatabase.getInstance();
         IDatabaseTester databaseTester = new JdbcDatabaseTester(
                 nc.getConfigitemAndSet("test").getDatabase_driver() , nc.getConfigitemAndSet("test").getDatabase_url(),hh.getUser(),hh.getPassword());
  //databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
 
  DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet); 
  //databaseTester.setDataSet(dataSet);
  //databaseTester.onSetup();

        // log.debug("Function End: cleanlyInsertDataset");
  
}
    
    //@Test
    public void createNewOccRecord(){
        log.trace("Function Begin: createNewOccRecord");
         System.out.println("createNewOccRecord");
         occbean instance = new occbean();
         addressbean adds=new addressbean();
         resbean res=new resbean();
         roomsbean room=new roomsbean();
         DateTime arrivaldate = new DateTime();
         DateTime departuredate= new DateTime();
         departuredate=arrivaldate.plus(Period.days(2));
         
         instance.createNewEmptyRecord();
         instance.setArrivaldate(arrivaldate.toDate());
         instance.setDeparturedate(departuredate.toDate());
         instance.setGuest(adds.getDataRecord(1));
         instance.setRes(res.getDataRecord(1));
         instance.setRoom(room.getDataRecord((long)1));
         List<String>overlaps=new ArrayList<String>(); 
         overlaps=instance.saveRecord(true);
        // TODO: Here something happens...
         assertEquals("Exits an overlaping res1",overlaps,null);
        log.trace("Function End: createNewOccRecord");

    }
    
    
    
    //@Test
    public void createSecondNewOccRecord(){

        log.trace("Function Entry: createSecondNewOccRecord");
         occbean instance = new occbean();
         addressbean adds=new addressbean();
         resbean res=new resbean();
         roomsbean room=new roomsbean();
         DateTime arrivaldate = new DateTime();
         DateTime departuredate= new DateTime();
         arrivaldate=arrivaldate.plus(Period.days(3));
         departuredate=arrivaldate.plus(Period.days(6));
         
         instance.createNewEmptyRecord();
         instance.setArrivaldate(arrivaldate.toDate());
         instance.setDeparturedate(departuredate.toDate());
         instance.setGuest(adds.getDataRecord(1));
         instance.setRes(res.getDataRecord(1));
         instance.setRoom(room.getDataRecord((long)1));
         List<String>overlaps=new ArrayList<String>(); 
         overlaps=instance.saveRecord(true);
         assertEquals("Exits an overlaping res2",overlaps,null);
        log.trace("Function End: createSecondNewOccRecord");
    }
    
    
    //@Test
    public void createErrorOccRecord(){

        log.trace("Function Begin: createErrorOccRecord");
         occbean instance = new occbean();
         addressbean adds=new addressbean();
         resbean res=new resbean();
         roomsbean room=new roomsbean();
         DateTime arrivaldate = new DateTime();
         DateTime departuredate= new DateTime();
         departuredate=arrivaldate.plus(Period.days(2));
         
         instance.createNewEmptyRecord();
         instance.setArrivaldate(arrivaldate.toDate());
         instance.setDeparturedate(departuredate.toDate());
         instance.setGuest(adds.getDataRecord(1));
         instance.setRes(res.getDataRecord(1));
         instance.setRoom(room.getDataRecord((long)1));
         List<String>overlaps=new ArrayList<String>(); 
         overlaps=instance.saveRecord(true);
         assertEquals("Exits an overlaping res3",overlaps.size(),1);
         log.debug(String.valueOf(overlaps.size()));
        log.trace("Function End: createErrorOccRecord");
    }
    
    @Test
    public void testForOverlapping(){
        createNewOccRecord();
        log.debug("********* 1st");
        createSecondNewOccRecord();
        log.debug("********* 2st");
        createErrorOccRecord();
        log.debug("********** 3rd");
        // // TODO: integrate ResState in xmls 
    }



    /**d
     * Test of SearchForOcc method, of class occbean.
     */
    @Test
    public void testSearchForOcc() {
        System.out.println("SearchForOcc");
        String searchstring = "x";
        occbean instance = new occbean();
        List expResult = null;
        List result = instance.SearchForOcc(searchstring);
        assertNotNull(result);
         

    }

    /**
     * Test of SearchForOccforRes method, of class occbean.
     */
    @Test
    public void testSearchForOccforRes() {
        System.out.println("SearchForOccforRes");
        resbean rsm= new resbean();
        Res res = rsm.getLastRecord();
        occbean instance = new occbean();

        List expResult = null;
        List <Occ> result = instance.SearchForOccforRes(res);
        assertEquals(res, result.get(0).getRes());
         

    }

    /**
     * Test of createNewEmptyRecord method, of class occbean.
     * done
     */
    @Test
    public void testCreateNewEmptyRecord() {
        System.out.println("createNewEmptyRecord");
        occbean instance = new occbean();
        int a,b;
        a=instance.SearchForOcc("x").size();

        instance.createNewEmptyRecord();
        b=instance.SearchForOcc("x").size();
        assertNotSame(a,b);
    }

    /**
     * Test of CheckForOverlappingReservations method, of class occbean.
     */
    

    /**
     * Test of nextRecordBackward method, of class occbean.
     */
    @Test
    public void testNextRecordBackward() {
        System.out.println("nextRecordBackward");
        occbean instance = new occbean();
        Occ test,oo;
        test=instance.getDataRecord(4);
        instance.nextRecordBackward();
        oo=instance.getDataRecord(instance.currentRecordNumber);
        assertSame(test,oo);
    }

    /**
     * Test of nextRecordForeward method, of class occbean.
     */
   @Test
    public void testNextRecordForeward() {
        System.out.println("nextRecordForeward");
        occbean instance = new occbean();
        Occ test,oo;
        test=instance.getDataRecord(4);
        instance.nextRecordForeward();
        oo=instance.getDataRecord(instance.currentRecordNumber);
        assertSame(test,oo);

    }



    /**
     * Test of saveRecord method, of class occbean.
     * Tested above
     */
    
    public void testSaveRecord_boolean() {

    }

    /**
     * Test of getId method, of class occbean.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        occbean instance = new occbean();
        Long expResult = Long.valueOf(1);
        Long result = instance.getId();
        assertEquals(expResult, result);
         

    }



    /**
     * Test of getDataRecord method, of class occbean.
     */
    @Test
    public void testGetDataRecord() {
        System.out.println("getDataRecord");
        long id = 1L;
        occbean instance = new occbean();
        Occ expResult = instance.getOcc();
        Occ result = instance.getDataRecord(id);
        assertEquals(expResult, result);
         

    }

    //************************************ Getters *******************************

    /**
     * Test of getArrivaltime method, of class occbean.
     */
    @Test
    public void testGetArrivaltime() {
        System.out.println("getArrivaltime");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse("2013-12-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        occbean instance = new occbean();
        Date expResult = date;
        Date result = instance.getArrivaltime();
        assertEquals(expResult, result);
         

    }

    /**
     * Test of getDeparturetime method, of class occbean.
     */
    
    public void testGetDeparturetime() {
        System.out.println("getDeparturetime");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getDeparturetime();
        assertEquals(expResult, result);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRes method, of class occbean.
     */
    
    public void testGetRes() {
        System.out.println("getRes");
        occbean instance = new occbean();
        Res expResult = null;
        Res result = instance.getRes();
        assertEquals(expResult, result);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoom method, of class occbean.
     */
    
    public void testGetRoom() {
        System.out.println("getRoom");
        occbean instance = new occbean();
        Rooms expResult = null;
        Rooms result = instance.getRoom();
        assertEquals(expResult, result);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrivaldate method, of class occbean.
     */
   
    public void testGetArrivaldate() {
        System.out.println("getArrivaldate");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getArrivaldate();
        assertEquals(expResult, result);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDeparturedate method, of class occbean.
     */
   
    public void testGetDeparturedate() {
        System.out.println("getDeparturedate");
        occbean instance = new occbean();
        Date expResult = null;
        Date result = instance.getDeparturedate();
        assertEquals(expResult, result);
         
        fail("The test case is a prototype.");
    }


    // **************************** Setters ********************************

    /**
     * Test of setRes method, of class occbean.
     */
   
    public void testSetRes() {
        System.out.println("setRes");
        Res res = null;
        occbean instance = new occbean();
        instance.setRes(res);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoom method, of class occbean.
     */
    
    public void testSetRoom() {
        System.out.println("setRoom");
        Rooms room = null;
        occbean instance = new occbean();
        instance.setRoom(room);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaldate method, of class occbean.
     */
    
    public void testSetArrivaldate_Date() {
        System.out.println("setArrivaldate");
        Date arrivaldate = null;
        occbean instance = new occbean();
        instance.setArrivaldate(arrivaldate);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaldate method, of class occbean.
     */
    
    public void testSetArrivaldate_String() {
        System.out.println("setArrivaldate");
        String arrivaldate = "";
        occbean instance = new occbean();
        instance.setArrivaldate(arrivaldate);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturedate method, of class occbean.
     */
    
    public void testSetDeparturedate_Date() {
        System.out.println("setDeparturedate");
        Date departuredate = null;
        occbean instance = new occbean();
        instance.setDeparturedate(departuredate);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturedate method, of class occbean.
     */
    
    public void testSetDeparturedate_String() {
        System.out.println("setDeparturedate");
        String departuredate = "";
        occbean instance = new occbean();
        instance.setDeparturedate(departuredate);
         
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaltime method, of class occbean.
     */
    
    public void testSetArrivaltime_Date() {
        System.out.println("setArrivaltime");
        Date arrivaltime = new Date();
        occbean instance = new occbean();
        instance.setArrivaltime(arrivaltime);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArrivaltime method, of class occbean.
     */
   
    public void testSetArrivaltime_String() {
        System.out.println("setArrivaltime");
        String arrivaltime = "";
        occbean instance = new occbean();
        instance.setArrivaltime(arrivaltime);
    
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturetime method, of class occbean.
     */
   
    public void testSetDeparturetime_Date() {
        System.out.println("setDeparturetime");
        Date departuretime = null;
        occbean instance = new occbean();
        instance.setDeparturetime(departuretime);
       
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDeparturetime method, of class occbean.
     */
   
    public void testSetDeparturetime_String() {
        System.out.println("setDeparturetime");
        String departuretime = "";
        occbean instance = new occbean();
        instance.setDeparturetime(departuretime);
  
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuest method, of class occbean.
     */
   
    public void testGetGuest() {
        System.out.println("getGuest");
        occbean instance = new occbean();
        Address expResult = null;
        Address result = instance.getGuest();
        assertEquals(expResult, result);
       
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGuest method, of class occbean.
     */
    
    public void testSetGuest() {
        System.out.println("setGuest");
        Address guest = null;
        occbean instance = new occbean();
        instance.setGuest(guest);
       
        fail("The test case is a prototype.");
    }
}