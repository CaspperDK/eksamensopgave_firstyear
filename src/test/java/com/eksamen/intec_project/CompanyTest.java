package com.eksamen.intec_project;

import com.eksamen.intec_project.MainController.CompanyDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyTest {

    @Autowired
    private CompanyDAO companyDAO;

    @Test
    void testGetAllCompanyNames() {
        try {
        // Calling getAllCompanyNames() method and print the result
        List<String> actualCompanyNames = companyDAO.getAllCompanyNames();
        System.out.println("Actual company names: " + actualCompanyNames);

        // Asserting expectations.
        List<String> expectedCompanyNames = List.of("Company A", "Company 333");
        assertEquals(expectedCompanyNames, actualCompanyNames);

        //Catching the 4000 errors thrown & marks the test as an error.
        } catch (AssertionError e) {
            System.err.println("Test failed: " + e.getMessage());
            fail("Assertion failed."); // Mark the test as an error
        }

    }

    @Test
    void testCorrectCompanyNames() {
        // Calling getAllCompanyNames() method and print the result
        List<String> actualCompanyNames = companyDAO.getAllCompanyNames();
        System.out.println("Actual company names: " + actualCompanyNames);

        // Asserting expectations.
        List<String> expectedCompanyNames = List.of("GLS", "yallah", "Klud");
        System.out.println("Your expected Companies: " + expectedCompanyNames);
        assertEquals(expectedCompanyNames, actualCompanyNames);
    }
}

