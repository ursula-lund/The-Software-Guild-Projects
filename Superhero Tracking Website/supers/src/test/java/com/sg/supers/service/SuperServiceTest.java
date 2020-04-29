/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supers.service;

import com.sg.supers.DTOs.Location;
import com.sg.supers.DTOs.Organization;
import com.sg.supers.DTOs.Power;
import com.sg.supers.DTOs.Sighting;
import com.sg.supers.DTOs.Super;
import com.sg.supers.daos.LocationDao;
import com.sg.supers.daos.OrganizationDao;
import com.sg.supers.daos.PowerDao;
import com.sg.supers.daos.SightingDao;
import com.sg.supers.daos.SuperDao;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static net.bytebuddy.matcher.ElementMatchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author ursul
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SuperServiceTest {

    @Autowired
    SuperService service;

    public SuperServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        LocationDao lDao = new LocationInMemDao();
        OrganizationDao oDao = new OrganizationInMemDao();
        PowerDao pDao = new PowerInMemDao();
        SightingDao sightDao = new SightingInMemDao();
        SuperDao superDao = new SuperInMemDao();

        service = new SuperService(lDao, oDao, pDao, sightDao, superDao);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addSighting method, of class SuperService.
     */
    @Test
    public void testGetAllSupers() {

        List<Super> toCheck = service.getAllSupers();

        Super one = toCheck.get(0);

        assertEquals(1, one.getSuperId());
        assertEquals("Test", one.getSuperName());
        assertEquals("Test", one.getSuperDesc());
        assertEquals(1, one.getPowerId());
        assertEquals(false, one.getIsDeleted());

        Super two = toCheck.get(1);

        assertEquals(2, two.getSuperId());
        assertEquals("Test2", two.getSuperName());
        assertEquals("Test 2", two.getSuperDesc());
        assertEquals(2, two.getPowerId());
        assertEquals(false, two.getIsDeleted());

    }

    @Test
    public void testAddSuper() {

        Location loc = new Location();

        loc.setLocationId(1);
        loc.setAddress("Test");
        loc.setLatitude(new BigDecimal("1034"));
        loc.setLongitude(new BigDecimal("5698"));
        loc.setIsDeleted(false);

        Sighting first = new Sighting();
        first.setSightingId(1);
        first.setIsDeleted(false);
        first.setSightDate(LocalDate.of(2020, Month.MARCH, 19));
        first.setLoc(loc);

        List<Sighting> sightList = new ArrayList<>();
        sightList.add(first);

        Power pow = new Power();
        pow.setPowerId(1);
        pow.setIsDeleted(false);
        pow.setPowerDesc("Test");
        pow.setPowerName("Test");

        List<Organization> orgs = new ArrayList<>();

        Super toAdd = new Super();

        toAdd.setSuperId(1);
        toAdd.setSuperName("Test");
        toAdd.setSuperDesc("Test");
        toAdd.setSuperPower(pow);
        toAdd.setPowerId(1);
        toAdd.setOrganizationsList(orgs);
        toAdd.setIsDeleted(false);

        List<Super> orgSupers = new ArrayList<>();

        Organization org = new Organization();
        org.setOrganizationId(1);
        org.setIsDeleted(false);
        org.setDescription("Test");
        org.setPhone("111-111-1111");
        org.setSupersList(orgSupers);

        orgSupers.add(toAdd);

    }

    @Test
    public void testGetSuperByid() {
    }

    /**
     * Test of deleteSuper method, of class SuperService.
     */
    @Test
    public void testDeleteSuper() {
    }

    @Test
    public void testAddSighting() {

        /**
         * Test of addSighting method, of class SuperService.
         */
    }

    @Test
    public void testGetAllOrganizations() {
    }

    /**
     * Test of updateOrganization method, of class SuperService.
     */
    @Test
    public void testUpdateOrganization() {
    }

    /**
     * Test of addOrganization method, of class SuperService.
     */
    @Test
    public void testAddOrganization() {
    }

    /**
     * Test of getAllLocations method, of class SuperService.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of addLocation method, of class SuperService.
     */
    @Test
    public void testAddLocation() {
    }

    /**
     * Test of updateLocation method, of class SuperService.
     */
    @Test
    public void testUpdateLocation() {
    }

    /**
     * Test of deleteLocation method, of class SuperService.
     */
    @Test
    public void testDeleteLocation() {
    }

    /**
     * Test of getallPowers method, of class SuperService.
     */
    @Test
    public void testGetallPowers() {
    }

    /**
     * Test of addPower method, of class SuperService.
     */
    @Test
    public void testAddPower() {
    }

    /**
     * Test of updatePower method, of class SuperService.
     */
    @Test
    public void testUpdatePower() {
    }

    /**
     * Test of deletePower method, of class SuperService.
     */
    @Test
    public void testDeletePower() {
    }

    /**
     * Test of getAllSightings method, of class SuperService.
     */
    @Test
    public void testGetAllSightings() {
    }

    /**
     * Test of updateSighting method, of class SuperService.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of deleteSighting method, of class SuperService.
     */
    @Test
    public void testDeleteSighting() {
    }

    /**
     * Test of getSightingById method, of class SuperService.
     */
    @Test
    public void testGetSightingById() {
    }

    /**
     * Test of getLocationById method, of class SuperService.
     */
    @Test
    public void testGetLocationById() {
    }

    /**
     * Test of getOrganizationById method, of class SuperService.
     */
    @Test
    public void testGetOrganizationById() {
    }

    /**
     * Test of getPowerById method, of class SuperService.
     */
    @Test
    public void testGetPowerById() {
    }

    /**
     * Test of getSuperByid method, of class SuperService.
     */
    /**
     * Test of deleteOrganization method, of class SuperService.
     */
    @Test
    public void testDeleteOrganization() {
    }

}
