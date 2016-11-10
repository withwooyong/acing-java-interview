package com.wiley.javainterviewsexposed.chapter16;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(
  "classpath:com/wiley/javainterviewsexposed/" +
          "chapter16/databaseTestApplicationContext.xml")
public class SpringDatabaseTransactionTest extends
                             AbstractTransactionalJUnit4SpringContextTests {

    private int rowCount;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void checkRowsInTable() {
        this.rowCount = countRowsInTable("user");
    }

    @Before
    public void checkUsersForTestDoNotExist() {
        //final int count = this.simpleJdbcTemplate.queryForInt(
    	final int count = jdbcTemplate.queryForObject(
                "select count(*) from user where username in (?, ?, ?)",
                Integer.class, 
                "Alice",
                "Bob",
                "Charlie");
        assertEquals(0, count);
    }

    @Test
    public void runInTransaction() {

        final List<Object[]> users = Arrays.asList(
                new Object[]{UUID.randomUUID().toString(), "Alice"},
                new Object[]{UUID.randomUUID().toString(), "Bob"},
                new Object[]{UUID.randomUUID().toString(), "Charlie"}
        );

        //this.simpleJdbcTemplate.batchUpdate("insert into user values (?, ?)", users);
        jdbcTemplate.batchUpdate("insert into user values (?, ?)", users);

        assertEquals(rowCount + users.size(), countRowsInTable("user"));
    }
}
