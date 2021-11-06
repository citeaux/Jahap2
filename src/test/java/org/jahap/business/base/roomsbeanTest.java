package org.jahap.business.base;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.jahap.TestDatabase;
import org.jahap.business.res.HousekeepingBean;
import org.jahap.business.res.MaintenanceBean;

import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Rooms;
import org.jahap.entities.views.Housekeeping;
import org.jahap.entities.views.Maintenance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.jahap.config.ClientConfig;

import static org.junit.Assert.*;

/**
 * Created by russ on 02.11.2015.
 */
public class roomsbeanTest {

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

    @Test
    public void testSearchForRooms() throws Exception {
        roomsbean instance = new roomsbean();
        assertNotNull(instance.SearchForRooms("+"));

    }

    protected static IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(".\\src\\test\\java\\org\\jahap\\business\\base\\rooms.xml")); //To change body of generated methods, choose Tools | Templates.
    }

    @Test
    public void testSetRoomsinListclean() throws Exception {
        roomsbean instance = new roomsbean();
        List<Rooms> gg= new ArrayList<Rooms>();
        List<Rooms> tg= new ArrayList<Rooms>();
        gg= instance.SearchForRooms("ww");
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListclean(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isClean(), true);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isClean(), true);



    }

    @Test
    public void testSetRoomsinListcleanHSK() throws Exception {
        roomsbean instance = new roomsbean();
        HousekeepingBean instance2 = new HousekeepingBean();

        List<Housekeeping> gg= new ArrayList<Housekeeping>();
        List<Housekeeping> tg= new ArrayList<Housekeeping>();
        gg= instance2.getHousekeepingOverview();
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListcleanHSK(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isClean(), true);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isClean(), true);



    }

    @Test
    public void testSetRoomsinListdirtyHSK() throws Exception {
        roomsbean instance = new roomsbean();
        HousekeepingBean instance2 = new HousekeepingBean();

        List<Housekeeping> gg= new ArrayList<Housekeeping>();
        List<Housekeeping> tg= new ArrayList<Housekeeping>();
        gg= instance2.getHousekeepingOverview();
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListdirtyHSK(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isClean(), false);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isClean(), false);


    }

    @Test
    public void testSetRoomsinListdirty() throws Exception {
        roomsbean instance = new roomsbean();
        List<Rooms> gg= new ArrayList<Rooms>();
        List<Rooms> tg= new ArrayList<Rooms>();
        gg= instance.SearchForRooms("ww");
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListdirty(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isClean(), false);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isClean(), false);


    }

    @Test
    public void testSetRoomsinListNotunderMaintenance() throws Exception {
        roomsbean instance = new roomsbean();
        List<Rooms> gg= new ArrayList<Rooms>();
        List<Rooms> tg= new ArrayList<Rooms>();
        gg= instance.SearchForRooms("ww");
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListNotunderMaintenance(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isNo_maintenance(), true);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isNo_maintenance(), true);

    }

    @Test
    public void testSetRoomsinListNotunderMaintenanceMN() throws Exception {
        roomsbean instance = new roomsbean();
        MaintenanceBean instance2 = new MaintenanceBean();

        List<Maintenance> gg= new ArrayList<Maintenance>();
        List<Maintenance> tg= new ArrayList<Maintenance>();
        gg= instance2.getMaintenanceOverview();
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListNotunderMaintenanceMN(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isNo_maintenance(), true);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isNo_maintenance(), true);


    }

    @Test
    public void testSetRoomsinListunderMaintenance() throws Exception {
        roomsbean instance = new roomsbean();
        List<Rooms> gg= new ArrayList<Rooms>();
        List<Rooms> tg= new ArrayList<Rooms>();
        gg= instance.SearchForRooms("ww");
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListunderMaintenance(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isNo_maintenance(), false);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isNo_maintenance(), false);
    }

    @Test
    public void testSetRoomsinListunderMaintenanceMN() throws Exception {
        roomsbean instance = new roomsbean();
        MaintenanceBean instance2 = new MaintenanceBean();

        List<Maintenance> gg= new ArrayList<Maintenance>();
        List<Maintenance> tg= new ArrayList<Maintenance>();
        gg= instance2.getMaintenanceOverview();
        tg.add(gg.get(0));
        tg.add(gg.get(1));
        instance.setRoomsinListunderMaintenanceMN(tg);
        instance.saveRecord();
        assertEquals(instance.getDataRecord(tg.get(0).getId()).isNo_maintenance(), false);
        assertEquals(instance.getDataRecord(tg.get(1).getId()).isNo_maintenance(), false);


    }

    @Test
    public void testGetDataRecord() throws Exception {
        roomsbean instance = new roomsbean();
        assertNotNull(instance.getDataRecord(instance.getId()));
    }
}