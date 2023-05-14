package com.eksamen.intec_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.io.Console;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Controller // This means that this class is a Controller
@RequestMapping(path="/intec") // This means URL's start with /intec (after Application path)
public class MainController {
    @GetMapping(path="/homepage")
    public String showLoginPage() {
        return "homepage";
    }

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public Resource getImage(@PathVariable String filename) {
        return new ClassPathResource("images/" + filename);
    }



    @Controller
    public class CompanyController {
        @GetMapping("/companies")
        @ResponseBody
        public List<String> getCompanyNames() {
            return companyDAO.getAllCompanyNames();
        }

        @Autowired
        private CompanyDAO companyDAO;

        @PostMapping("/add-company")
        public String addCompany(@RequestParam("companyName") String companyName) {
            // Check if the company already exists in the database
            if (companyDAO.companyExists(companyName)) {
                return null;
            }
            // Add the company to the database
            companyDAO.addCompany(companyName);

            return null;
        }
        @PostMapping("/add-dk")
        public String addDK(@RequestParam("chauffeur") String chauffeur, @RequestParam("companyName") String companyName) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            companyDAO.addDataToDKTable(chauffeur, companyName, ZoneId.systemDefault().getId(), now.format(formatter));
            return null;
        }
        @PostMapping("/add-us")
        public String addUS(@RequestParam("chauffeur") String chauffeur, @RequestParam("companyName") String companyName) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            companyDAO.addDataToUSTable(chauffeur, companyName, ZoneId.systemDefault().getId(), now.format(formatter));
            return null;
        }
    }



    @Repository
    public class CompanyDAO {

        private final JdbcTemplate jdbcTemplate;

        public CompanyDAO(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

        public boolean companyExists(String companyName) {
            String sql = "SELECT COUNT(*) FROM companies WHERE name = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, companyName);
            return count > 0;
        }

        public void addCompany(String companyName) {
            String sql = "INSERT INTO companies (name) VALUES (?)";
            jdbcTemplate.update(sql, companyName);
        }

        public void addDataToUSTable(String chauffeurName, String company, String location, String timeOfArrival) {
            String sql = "INSERT INTO us (chauffeur_name, company, location, time_of_arrival) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, chauffeurName, company, location, timeOfArrival);
        }

        public void addDataToDKTable(String chauffeurName, String company, String location, String timeOfArrival) {
            String sql = "INSERT INTO dk (chauffeur_name, company, location, time_of_arrival) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, chauffeurName, company, location, timeOfArrival);
        }

        public List<String> getAllCompanyNames() {
            List<String> companyNames = jdbcTemplate.queryForList("SELECT name FROM companies", String.class);
            return companyNames;
        }
    }

}