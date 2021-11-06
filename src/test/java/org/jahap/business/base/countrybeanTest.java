package org.jahap.business.base;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.TestDatabase;

import org.jahap.entities.JahapDatabaseConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static org.jahap.business.base.country.*;
import org.jahap.config.ClientConfig;
import static org.junit.Assert.*;

/**
 * Created by russ on 26.10.2015.
 */
public class countrybeanTest {

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
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\base\\country.xml")); //To change body of generated methods, choose Tools | Templates.
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
    public void testSearchForCountry() throws Exception {
        countrybean instance = new countrybean();
        assertNotNull(instance.SearchForCountry("*"));


    }

    @Test
    public void testSearchForCountry1() throws Exception {
        countrybean instance = new countrybean();
        assertNotNull(instance.SearchForCountry(code));
    }

    @Test
    public void testGetDataRecord() throws Exception {
        countrybean instance = new countrybean();
        assertNotNull(instance.getDataRecord(instance.getLastPosition().getId()));
    }

    @Test
    public void testGetLastPosition() throws Exception {
        countrybean instance = new countrybean();
        assertNotNull(instance.getLastPosition());
    }


    @Test
    public void testCreateAndRead() throws Exception {
        countrybean instance = new countrybean();
        instance.createNewEmptyRecord();
        languagebean instance2 = new languagebean();
        currencybean instance3 = new currencybean();
        instance.setContryLanguage(instance2.getLastPosition());
        instance.setCountryCurrency(instance3.getLastPosition());
        instance.setCountryCode("xxx");
        instance.setCountryName("www");
        instance.saveRecord();
        assertEquals(instance.getLastPosition().getContryLanguage(), instance2.getLastPosition());
        assertEquals(instance.getLastPosition().getCountryCurrency(), instance3.getLastPosition());
        assertEquals(instance.getLastPosition().getCountryCode().toString(),"xxx");
        assertEquals(instance.getLastPosition().getCountryName().toString(),"www");
    }


}