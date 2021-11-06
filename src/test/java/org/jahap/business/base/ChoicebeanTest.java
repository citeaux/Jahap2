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
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;

/**
 * Created by russ on 26.10.2015.
 */
public class ChoicebeanTest {

    @Before
    public void setUp() throws Exception {
        ClientConfig nc= ClientConfig.getInstance();
        TestDatabase hh= TestDatabase.getInstance();
        JahapDatabaseConnector con=JahapDatabaseConnector.getConnector(hh.getUser(),hh.getPassword(),nc.getConfigitemAndSet("test"));


        IDataSet dataSet = getDataSet();
        System.out.print(dataSet.getTableNames());
        cleanlyInsertDataset(dataSet);


    }

    protected static IDataSet getDataSet() throws Exception {
        File F = new File("w");
        System.out.print(F.getAbsolutePath());
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\base\\choice.xml")); //To change body of generated methods, choose Tools | Templates.
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
    public void testSearchForChoice() throws Exception {
          Choicebean instance = new Choicebean();
          assertNotNull(instance.SearchForChoice());
    }

    @Test
    public void testSearchForChoiceString() throws Exception {
        Choicebean instance = new Choicebean();
        assertNotNull(instance.SearchForChoiceString("addresstype"));
    }

    @Test
    public void testGetDataRecord() throws Exception {
        Choicebean instance = new Choicebean();
        assertNotNull(instance.getDataRecord(instance.getLastPosition().getId()));
    }

    @Test
    public void testGetLastPosition() throws Exception {
        Choicebean instance = new Choicebean();
        assertNotNull(instance.getLastPosition().getId());
    }



}