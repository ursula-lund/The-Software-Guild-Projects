/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ChrisList.daos;

import com.sg.ChrisList.models.Condition;
import com.sg.ChrisList.models.Keyword;
import com.sg.ChrisList.models.Listing;
import com.sg.ChrisList.models.Role;
import com.sg.ChrisList.models.User;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author ursul
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ListingDaoDBTest {

    @Autowired
    ListingDao listingDao = new ListingDaoDB();

    @Autowired
    ConditionDao conditionDao = new ConditionDaoDB();

    @Autowired
    KeywordDao keywordDao = new KeywordDaoDB();

    @Autowired
    RoleDao roleDao = new RoleDaoDB();

    @Autowired
    UserDao userDao = new UserDaoDB();

    @Autowired
    JdbcTemplate template;

    public ListingDaoDBTest() {
    }

    @Before
    public void setUpClass() {
   

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    
       
    }


    /**
     * Test of getListingsByCondition method, of class ListingDaoDB.
     */
    @Test
    public void testGetListingsByConditionUsed() {

        List<Listing> used = listingDao.getListingsByCondition("Used");

        LocalDate toCheck = LocalDate.of(2016, Month.APRIL, 06);
        Listing indexOne = used.get(0);

        assertEquals("Old Socks", indexOne.getTitle());
        assertEquals("Bloomington", indexOne.getCity());
        assertEquals(toCheck, indexOne.getDate());
        assertEquals("These are my old socks. I do not want them in my house anymore.", indexOne.getContent());
        assertEquals(false, indexOne.getIsDeleted());
        assertEquals(3, indexOne.getListingCondition().getConditionId());
        assertEquals(3, indexOne.getUserId());

        LocalDate otherCheck = LocalDate.of(2016, Month.APRIL, 04);

        Listing indexTwo = used.get(1);

        assertEquals("Old Ladder", indexTwo.getTitle());
        assertEquals("Richfield", indexTwo.getCity());
        assertEquals(otherCheck, indexTwo.getDate());
        assertEquals("Old ladder. It is old, but is still a ladder.", indexTwo.getContent());
        assertEquals(false, indexTwo.getIsDeleted());
        assertEquals(3, indexTwo.getListingCondition().getConditionId());
        assertEquals(3, indexTwo.getUserId());
    }

    @Test
    public void testGetListingsByConditionLikeNew() {

        LocalDate likeNewCheck = LocalDate.of(2016, Month.APRIL, 05);
        List<Listing> likeNew = listingDao.getListingsByCondition("Like New");
        Listing ln = likeNew.get(0);

        assertEquals("Anime Figure", ln.getTitle());
        assertEquals("Hopkins", ln.getCity());
        assertEquals(likeNewCheck, ln.getDate());
        assertEquals("This is a Dragonball Z character. Selling it becuase I don't identify with the character anymore", ln.getContent());
        assertEquals(false, ln.getIsDeleted());
        assertEquals(2, ln.getListingCondition().getConditionId());
        assertEquals(5, ln.getUserId());

    }

    @Test
    public void testGetListingsByConditionNew() {

        LocalDate newCheck = LocalDate.of(2016, Month.APRIL, 06);
        List<Listing> newCondition = listingDao.getListingsByCondition("New");
        Listing n = newCondition.get(0);

        assertEquals("Cool Lamp", n.getTitle());
        assertEquals("Minneapolis", n.getCity());
        assertEquals(newCheck, n.getDate());
        assertEquals("This is a cool lamp. New. Is in the shape of a Cool Lamp", n.getContent());
        assertEquals(false, n.getIsDeleted());
        assertEquals(1, n.getListingCondition().getConditionId());
        assertEquals(4, n.getUserId());

        LocalDate checked = LocalDate.of(2016, Month.MARCH, 05);
        Listing m = newCondition.get(1);

        assertEquals("Waffle Maker", m.getTitle());
        assertEquals("Hopkins", m.getCity());
        assertEquals(checked, m.getDate());
        assertEquals("New, but I like Pancakes better", m.getContent());
        assertEquals(false, m.getIsDeleted());
        assertEquals(1, m.getListingCondition().getConditionId());
        assertEquals(5, m.getUserId());
    }

    @Test
    public void testEditListingGoldenPath() {

    }

    @Test
    public void testGetListingsByKeywordGoldenPath() {

        LocalDate toCheck = LocalDate.of(2016, Month.APRIL, 06);
        List<Listing> byKeyword = listingDao.getListingsByKeyword("furniture");
        Listing a = byKeyword.get(0);

        assertEquals("Cool Lamp", a.getTitle());
        assertEquals("Minneapolis", a.getCity());
        assertEquals(toCheck, a.getDate());
        assertEquals("This is a cool lamp. New. Is in the shape of a Cool Lamp", a.getContent());
        assertEquals(false, a.getIsDeleted());
        assertEquals(1, a.getListingCondition().getConditionId());
        assertEquals(4, a.getUserId());

    }

  

}
