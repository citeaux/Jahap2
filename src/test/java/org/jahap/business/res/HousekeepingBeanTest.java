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
import java.util.Calendar;
import java.util.Date;
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;

/**
 * Created by russ on 10.11.2015.
 */
public class HousekeepingBeanTest {

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
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\res\\housekeeping.xml")); //To change body of generated methods, choose Tools | Templates.
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
    public void testGetHousekeepingOverview() throws Exception {
        HousekeepingBean instance = new HousekeepingBean();
        assertNotNull(instance.getHousekeepingOverview());


    }

    @Test
    public void testGetHousekeepingblocksForThisRoom() throws Exception {
        HousekeepingBean instance = new HousekeepingBean();
        occbean obean= new occbean();

        assertNotNull(instance.getHousekeepingblocksForThisRoom(obean.SearchForOccforHskBlock(instance.getLastPosition()).get(0).getRoom().getId()));
    }

    @Test
    public void testSearchForHousekeepingBlock() throws Exception {
        HousekeepingBean instance = new HousekeepingBean();


        assertNotNull(instance.SearchForHousekeepingBlock("ff"));
    }



    @Test
    public void testNewHouskeepingBlock() throws Exception {

        roomsbean jj= new roomsbean();
        occbean instance2= new occbean();
        LocalDate from= LocalDate.now();
        LocalDate to=from.plusDays(5);
        Instant instant = from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2 = to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        HousekeepingBean instance = new HousekeepingBean();
        // test by roomid
        instance.newHouskeepingBlock(from,to,jj.getId(),"test");
        instance.saveRecord();
        assertEquals(instance.getLastPosition().getComment(), "test");
        instance.getLastPosition();
        instance2.SearchForOccforHskBlock(instance.getLastPosition());
        assertEquals(instance2.SearchForOccforHskBlock(instance.getLastPosition()).get(0).getArrivaldate(), Date.from(instant));
        assertEquals(instance2.SearchForOccforHskBlock(instance.getLastPosition()).get(0).getDeparturedate(), Date.from(instant2));
    }

    @Test
    public void testNewHouskeepingBlock1() throws Exception {

        roomsbean jj= new roomsbean();
        occbean instance2= new occbean();
        LocalDate from= LocalDate.now();
        LocalDate to=from.plusDays(5);
        Instant instant = from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Instant instant2 = to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        HousekeepingBean instance = new HousekeepingBean();
        // test by room
        instance.newHouskeepingBlock(from,to,jj.getCurrentRoom().get(0),"test");
        instance.saveRecord();
        assertEquals(instance.getLastPosition().getComment(), "test");
        assertEquals(instance2.SearchForOccforHskBlock(instance.getLastPosition()).get(0).getArrivaldate(), Date.from(instant));
        assertEquals(instance2.SearchForOccforHskBlock(instance.getLastPosition()).get(0).getDeparturedate(), Date.from(instant2));

    }

    @Test
    public void testGetDataRecord() throws Exception {
        HousekeepingBean instance = new HousekeepingBean();


        assertNotNull(instance.getDataRecord(instance.getLastPosition().getId()));

    }

    @Test
    public void testGetLastPosition() throws Exception {
        HousekeepingBean instance = new HousekeepingBean();


        assertNotNull(instance.getLastPosition());
    }
}