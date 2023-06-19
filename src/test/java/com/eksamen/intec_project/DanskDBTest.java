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

        // Assert the expected chauffeur names
        List<String> expectedChauffeurNames = List.of("John Doe", "Jane Smith", "Michael Johnson");
        assertEquals(expectedChauffeurNames, actualChauffeurNames);

        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
            fail("Assertion failed."); // Mark the test as an error
        }

    }
        @Test
        void testGetAllNamesFromDKTable() {
            try {
            String sql = "SELECT chauffeur_name FROM dk";
            List<String> chauffeurNames = jdbcTemplate.queryForList(sql, String.class);

            // Assert the expected number of chauffeur names
            int expectedCount = 3;
            assertEquals(expectedCount, chauffeurNames.size());


            // Print all the chauffeur names
            System.out.println("Chauffeur Names:");
            for (String name : chauffeurNames) {
                System.out.println(name);

                //Catching the 4000 errors thrown & marks the test as an error.
            }} catch (AssertionError e) {
                    System.err.println("Test failed: " + e.getMessage());
                fail("Assertion failed."); // Mark the test as an error
                System.out.println("This test fails on purpose.");
                }
    }
}