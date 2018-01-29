package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @see https://spring.io/guides/gs/relational-data-access/
 *
 */
@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Querying for customer records where first_name like 'J%':");
        jdbcTemplate.query(
                "SELECT uscd, fnam, lnam FROM us WHERE fnam LIKE ? LIMIT ?", new Object[] { "J%", 10 },
                (rs, rowNum) -> new User(rs.getString("uscd"), rs.getString("fnam"), rs.getString("lnam"))
        ).forEach(user -> log.info(user.toString()));
    }
}
