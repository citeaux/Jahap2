package org.jahap.business.res;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.TestDatabase;
import org.jahap.business.base.roomsbean;

import org.jahap.entities.JahapDatabaseConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;

/**
 * Created by russ on 13.11.2015.
 */
public class MaintenanceBeanTest {

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

    protected static IDataSet getDataSet() throws Exception {

        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\res\\maintenanceblock.xml")); //To change body of generated methods, choose Tools | Templates.
    }


    @Test
    public void testSearchForMaintenanceBean() throws Exception {
        MaintenanceBean instance = new MaintenanceBean();


        assertNotNull(instance.SearchForMaintenanceBlock("ff"));
    }


    @Test
    public void testGetDataRecord() throws Exception {
        MaintenanceBean instance = new MaintenanceBean();


        assertNotNull(instance.getDataRecord(instance.getLastPosition().getId()));
    }

    @Test
    public void testGetLastPosition() throws Exception {
        MaintenanceBean instance = new MaintenanceBean();
        assertNotNull(instance.getLastPosition());
    }

    @Test
    public void testNewMaintenanceBlock() throws Exception {

        roomsbean jj= new roomsbean();
        occbean instance2= new occbean();
        LocalDate from= LocalDate.now();
        LocalDate to=from.plusDays(5);
        Instant instant = from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2 = to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        MaintenanceBean instance = new MaintenanceBean();
        // test by roomid
        instance.newMaintenanceBlock(from, to, jj.getId(), "test");
        instance.saveRecord();
        assertEquals(instance.getLastPosition().getComment(), "test");
        instance.getLastPosition();
        instance2.SearchForOccforMaintenanceBlock(instance.getLastPosition());
        assertEquals(instance2.SearchForOccforMaintenanceBlock(instance.getLastPosition()).get(0).getArrivaldate(), Date.from(instant));
        assertEquals(instance2.SearchForOccforMaintenanceBlock(instance.getLastPosition()).get(0).getDeparturedate(), Date.from(instant2));

    }

    @Test
    public void testNewMaintenanceBlock1() throws Exception {
        roomsbean jj= new roomsbean();
        occbean instance2= new occbean();
        LocalDate from= LocalDate.now();
        LocalDate to=from.plusDays(5);
        Instant instant = from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2 = to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        MaintenanceBean instance = new MaintenanceBean();
        // test by room
        instance.newMaintenanceBlock(from, to, jj.getCurrentRoom().get(0), "test");
        instance.saveRecord();
        assertEquals(instance.getLastPosition().getComment(), "test");
        assertEquals(instance2.SearchForOccforMaintenanceBlock(instance.getLastPosition()).get(0).getArrivaldate(), Date.from(instant));
        assertEquals(instance2.SearchForOccforMaintenanceBlock(instance.getLastPosition()).get(0).getDeparturedate(), Date.from(instant2));
    }

    @Test
    public void testGetMaintenanceOverview() throws Exception {
        MaintenanceBean instance = new MaintenanceBean();
        assertNotNull(instance.getMaintenanceOverview());
    }
}