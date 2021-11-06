package org.jahap.business.base;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.Misc.RandomString;
import org.jahap.TestDatabase;
import org.jahap.business.acc.revaccountsbean;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.acc.Revaccounts;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;

/**
 * Created by russ on 02.11.2015.
 */
public class ratesbeanTest {

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
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\base\\rates.xml")); //To change body of generated methods, choose Tools | Templates.
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
    public void testGetCurrentRate() throws Exception {
        ratesbean instance = new ratesbean();
        assertNotNull(instance.getCurrentRate());


    }

    @Test
    public void testSearchForRate() throws Exception {
        ratesbean instance = new ratesbean();
        assertNotNull(instance.SearchForRate("e"));
    }

    @Test
    public void testGetDataRecord() throws Exception {
        ratesbean instance = new ratesbean();
        assertNotNull(instance.getDataRecord(instance.getId()));

    }

    @Test
    public void testCreateAndRead() throws Exception {
        ratesbean instance = new ratesbean();
        revaccountsbean instance2 = new revaccountsbean();
        vattypesbean instance3= new vattypesbean();
        instance.createNewEmptyRecord();
        RandomString gg= new RandomString(10);
        String name=gg.nextString();
        String code=gg.nextString();

        instance.setName(name);
        instance.setCode(code);
        instance.setOvernight(true);
        instance.setPrice(123.56);
        instance.setRevaccount(instance2.getRevAccount(instance2.getId()));
        instance.setVattype(instance3.SearchForVatType(instance3.currentRecordNumber));

        instance.saveRecord();
        instance.jumptolastrecord();

        assertEquals(instance.getName(), name);
        assertEquals(instance.getCode(), code);
        assertEquals(instance.getOvernight(), true);
        assertEquals(instance.getPrice(), 123.56,0.001);
        assertEquals(instance.getRevaccount(),instance2.getRevAccount(instance2.getId()));
        assertEquals(instance.getVattype(),instance3.SearchForVatType(instance3.currentRecordNumber));

    }

}