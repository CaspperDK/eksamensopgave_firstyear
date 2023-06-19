package com.eksamen.intec_project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class ArrivalTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    LocalDateTime startTime = LocalDateTime.parse("2023-06-06T00:00:00");
    LocalDateTime endTime = LocalDateTime.parse("2023-06-18T00:00:00");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void testChauffeurWithTimeOfArrivalsDK() {
        String sql = "SELECT chauffeur_name, company, time_of_arrival FROM dk WHERE time_of_arrival >= ? AND time_of_arrival < ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, new Object[]{startTime.format(formatter), endTime.format(formatter)});

        System.out.println("Chauffeur names and time of arrival within the specified time-interval:");
        for (Map<String, Object> row : results) {
            String chauffeurName = (String) row.get("chauffeur_name");
            String company = (String) row.get("company");
            String timeOfArrival = (String) row.get("time_of_arrival");
            System.out.println("Chauffeur: " + chauffeurName + "(" + company + "), " + "Time of Arrival: " + timeOfArrival);
        }
    }
    @Test
    void testChauffeurOrTimeOfArrivalDK() {
        String sql = "SELECT chauffeur_name, company, time_of_arrival FROM dk WHERE time_of_arrival >= ? AND time_of_arrival < ?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, new Object[]{startTime.format(formatter), endTime.format(formatter)});


        String expectedChauffeurName = "John KnutKnut";
        String expectedTimeOfArrival = "2023-06-06 22:24:45";

        System.out.println("Chauffeur names and time of arrival within the specified time-interval:");
        for (Map<String, Object> row : results) {
            String chauffeurName = (String) row.get("chauffeur_name");
            String company = (String) row.get("company");
            String timeOfArrival = (String) row.get("time_of_arrival");
            System.out.println("Chauffeur: " + chauffeurName + "(" + company + "), " + "Time of Arrival: " + timeOfArrival);

            if (chauffeurName.equals(expectedChauffeurName) || timeOfArrival.equals(expectedTimeOfArrival)) {
                System.out.println("The expected chauffeur: " + expectedChauffeurName + " at "+ timeOfArrival);
                Assertions.assertTrue(true);
                return;
            }
        }

        Assertions.fail("Expected result not found within the specified time-interval");
    }
}


