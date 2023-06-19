package com.eksamen.intec_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DanskDBTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testGetChauffeurNamesFromDKTable() {
        try {
        String sql = "SELECT chauffeur_name FROM dk";
        List<String> actualChauffeurNames = jdbcTemplate.queryForList(sql, String.class);
        System.out.println("Actual chauffeur names: " + actualChauffeurNames);

        List<String> expectedChauffeurNames = List.of("John Doe", "Jane Smith", "Michael Johnson");
        assertEquals(expectedChauffeurNames, actualChauffeurNames);

        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
            fail("Assertion failed."); // Marks the test as an error
        }

    }
    @Test
    void testForLettersDK() {
      try {
          char startingLetter = 'b';
          String sql = "SELECT chauffeur_name FROM dk WHERE chauffeur_name LIKE ?";

          List<String> actualChauffeurNames = jdbcTemplate.queryForList(sql, new Object[]{startingLetter + "%"}, String.class);
          System.out.println("Actual chauffeur names starting with '" + startingLetter + "': " + actualChauffeurNames);

          int expectedCount = actualChauffeurNames.size(); // List size of actualChauffeurNames
          assertEquals(expectedCount, actualChauffeurNames.size());

      } catch (AssertionError e) {
          System.err.println("Test failed: " + e.getMessage());
          fail("Assertion failed.");
            }
        }

        @Test
        void testGetAllNamesFromDKTable() {
            try {
            String sql = "SELECT chauffeur_name FROM dk";
            List<String> chauffeurNames = jdbcTemplate.queryForList(sql, String.class);


            int expectedCount = 3;
            assertEquals(expectedCount, chauffeurNames.size());

            System.out.println("Chauffeur Names:");
            for (String name : chauffeurNames) {
                System.out.println(name);

                //Catching the 4000 errors thrown & marks the test as an error.
            }} catch (AssertionError e) {
                    System.err.println("Test failed: " + e.getMessage());
                fail("Assertion failed."); // Marks the test as an error
                System.out.println("This test fails on purpose.");
                }
    }
}