package org.jahap.business.base;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.TestDatabase;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;

/**
 * Created by russ on 22.10.2015.
 */
public class addressbeanTest {

    @Before
    public void setUp() throws Exception {
        ClientConfig nc= ClientConfig.getInstance();
        TestDatabase hh= TestDatabase.getInstance();
        JahapDatabaseConnector con=JahapDatabaseConnector.getConnector(hh.getUser(),hh.getPassword(),nc.getConfigitemAndSet("test"));

        File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
        cleanlyInsertDataset(dataSet);
    }

    protected static IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\base\\address.xml")); //To change body of generated methods, choose Tools | Templates.
    }

    @After
    public void tearDown() throws Exception {
        File F = new File("w");
        System.out.print(F.getAbsolutePath());
        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
        cleanlyInsertDataset(dataSet);


}

    private static void cleanlyInsertDataset(IDataSet dataSet) throws Exception {
        ClientConfig nc= ClientConfig.getInstance();
        TestDatabase hh= TestDatabase.getInstance();
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                nc.getConfigitemAndSet("test").getDatabase_driver() , nc.getConfigitemAndSet("test").getDatabase_url(),hh.getUser(),hh.getPassword());
        //databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);

        DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet);
        //databaseTester.setDataSet(dataSet);
        //databaseTester.onSetup();



    }

    @Test
    public void testSearchForAddress() throws Exception {
          addressbean instance = new addressbean();
          assertNotNull(instance.SearchForAddress("+"));


    }

    @Test
    public void testGetCurrentAddress() throws Exception {
        addressbean instance = new addressbean();
        assertNotNull(instance.getCurrentAddress());
    }

//    @Test
//    public void testCreateNewEmptyRecord() throws Exception {
//
//    }
//
//    @Test
//    public void testSaveRecord() throws Exception {
//
//    }



    @Test
    public void testNextRecordBackward() throws Exception {
        addressbean instance = new addressbean();
        Address add=new Address();
        add=instance.getDataRecord(instance.numberOfLastRecord);
        instance.nextRecordBackward();
        assertNotSame(add,instance.getCurrentAddress());
   }
//
//    @Test
//    public void testQuitDBaccess() throws Exception {
//
//    }
//
     @Test
     public void testGetId() throws Exception {
         addressbean instance = new addressbean();
         assertNotNull(instance.getId());
    }

    @Test
    public void testGetDataRecord() throws Exception {

        addressbean instance = new addressbean();
        assertNotNull(instance.getDataRecord(instance.getCurrentAddress().getId()));

    }

    @Test
    public void testGetAdressData() throws Exception {
        addressbean instance = new addressbean();
        assertNotNull(instance.getName());
        assertNotNull(instance.getStreet());
        assertNotNull(instance.getZipcode());

    }



    @Test
    public void testCreateAdress() throws Exception {
        addressbean instance = new addressbean();
        countrybean instance2 = new countrybean();
        languagebean instance3= new languagebean();
        currencybean instance4= new currencybean();
        instance.createNewEmptyRecord();
        instance.setName("Stravinski");
        instance.setChristianname("Igor");
        instance.setRemarks("Notes");
        instance.setCountry(instance2.getDataRecord(1));
        instance.setLanguage(instance3.getDataRecord(1));
        instance.setCurrency(instance4.getDataRecord(1));
        instance.setGreeting("Mr");
        instance.saveRecord();
        instance.jumptolastrecord();
        assertNotNull(instance.getName());
        assertNotNull(instance.getChristianname());
        assertNotNull(instance.getRemarks());
        assertNotNull(instance.getCountry());
        assertNotNull(instance.getLanguage());
        assertNotNull(instance.getCurrency());
        assertNotNull(instance.getGreeting());


    }


}