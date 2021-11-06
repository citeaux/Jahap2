package org.jahap.business.base;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.Misc.RandomString;
import org.jahap.TestDatabase;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Country;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import org.jahap.config.ClientConfig;


import static org.junit.Assert.*;

/**
 * Created by russ on 27.10.2015.
 */
public class HotelbeanTest {





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
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\base\\hotel.xml")); //To change body of generated methods, choose Tools | Templates.
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


        DatabaseOperation.CLEAN_INSERT.execute(databaseTester.getConnection(), dataSet);




    }

    @Test
    public void testSearchForHotel() throws Exception {
            Hotelbean instance = new Hotelbean();
        assertNotNull(instance.SearchForHotel("k"));
    }

    @Test
    public void testGetDataRecord() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getDataRecord(instance.getLastPosition().getId()));
    }

    @Test
    public void testGetLastPosition() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getLastPosition());

    }





    @Test
    public void testGetHotelAdress() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelAdress());

    }

    @Test
    public void testGetHotelBankaccountdata1() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelBankaccountdata1());
    }

    @Test
    public void testGetHotelBankaccountdata2() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelBankaccountdata2());

    }

    @Test
    public void testGetHotelCode() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelCode());

    }

    @Test
    public void testGetHotelCountry() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelCountry());
    }

    @Test
    public void testGetHotelCurrency() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelCurrency());
    }

    @Test
    public void testGetHotelFootertext() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelFootertext());
    }

    @Test
    public void testGetHotelLanguage() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelLanguage());
    }

    @Test
    public void testGetHotelName() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelName());
    }

    @Test
    public void testGetId() throws Exception  {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelName());

    }

    @Test
    public void testSetHotelAdress() throws Exception {
        Hotelbean instance = new Hotelbean();
        addressbean instance1 =  new addressbean();
        instance.setHotelAdress(instance1.getCurrentAddress());
        instance.saveRecord();
        assertEquals(instance.getHotelAdress(), instance1.getCurrentAddress());

    }

    @Test
    public void testSetHotelBankaccountdata1() throws Exception {
        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(10);
        String one= dd.nextString();
        instance.setHotelBankaccountdata1(one);
        instance.saveRecord();
        assertEquals(instance.getHotelBankaccountdata1(),  one);

    }

    @Test
    public void testSetHotelBankaccountdata2() throws Exception
    {
        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(10);
        String one= dd.nextString();instance.setHotelBankaccountdata2(one);
        instance.saveRecord();
        assertEquals(instance.getHotelBankaccountdata2(),  one);

    }

    @Test
    public void testSetHotelCode() throws Exception {
        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(3);
        String one= dd.nextString();
        instance.setHotelCode(one);
        instance.saveRecord();
        assertEquals(instance.getHotelCode(),  one);


    }

    @Test
    public void testSetHotelCountry() throws Exception {
        Hotelbean instance = new Hotelbean();
        countrybean instance1= new countrybean();
        instance.setHotelCountry(instance1.getLastPosition());


        instance.saveRecord();
        assertEquals(instance.getHotelCountry(), instance1.getLastPosition());



    }


    @Test
    public void testSetHotelCurrency() throws Exception {

        Hotelbean instance = new Hotelbean();
        currencybean instance1= new currencybean();
        instance.setHotelCurrency(instance1.getLastPosition());


        instance.saveRecord();
        assertEquals(instance.getHotelCurrency(), instance1.getLastPosition());

    }



    @Test
    public void testSetHotelFootertext() throws Exception {
        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(20);
        String one= dd.nextString();
        instance.setHotelFootertext(one);
        instance.saveRecord();
        assertEquals(instance.getHotelFootertext(),  one);


    }

    @Test
    public void testSetHotelLanguage() throws Exception {

        Hotelbean instance = new Hotelbean();
        languagebean instance1= new languagebean();
        instance.setHotelLanguage(instance1.getLastPosition());


        instance.saveRecord();
        assertEquals(instance.getHotelLanguage(), instance1.getLastPosition());



    }



    @Test
    public void testSetHotelName() throws Exception {
        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(20);
        String one= dd.nextString();
        instance.setHotelName(one);
        instance.saveRecord();
        assertEquals(instance.getHotelName(), one);


    }

    @Test
    public void testGetOperationdate() throws Exception {

        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getOperationdate());

    }



    @Test
    public void testIncrementOperationdate() throws Exception {
        Hotelbean instance = new Hotelbean();
        Date test= new Date();
        test=instance.getOperationdate();
        instance.incrementOperationdate();
        assertNotSame(test,instance.getOperationdate());

    }

    @Test
    public void testGetHotelNumberformat() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHotelNumberformat());
    }

    @Test
    public void testGetHoteldateformat() throws Exception {
        Hotelbean instance = new Hotelbean();
        assertNotNull(instance.getHoteldateformat());
    }

    @Test
    public void testSetHotelNumberformat() throws Exception {
        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(20);
        String one= dd.nextString();
        instance.setHotelNumberformat(one);
        instance.saveRecord();
        assertEquals(instance.getHotelNumberformat(), one);

    }

    @Test
    public void testSetHoteldateformat() throws Exception {

        Hotelbean instance = new Hotelbean();
        RandomString dd=new RandomString(20);
        String one= dd.nextString();
        instance.setHoteldateformat(one);
        instance.saveRecord();
        assertEquals(instance.getHoteldateformat(), one);

    }
}