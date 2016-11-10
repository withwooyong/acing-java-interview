package com.wiley.javainterviewsexposed.chapter16;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        "classpath:com/wiley/javainterviewsexposed/chapter16/jdbcTemplateContext.xml")
public class JdbcTemplateUsage {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void retrieveData() {
        //final int rowCount = jdbcTemplate.queryForInt("select count(*) from user");
    	final int rowCount = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
        

//        final List<User> userList = jdbcTemplate.query(
//                "select id, username from user",
//                new RowMapper<User>() {
//
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return new User(rs.getString("id"), rs.getString("username"));
//            }
//        });
    	
        final List<User> userList = jdbcTemplate.query("select id, username from user",
        		new org.springframework.jdbc.core.RowMapper<User>() {
        	@Override
        	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        		return new User(rs.getString("id"), rs.getString("username"));
        	}
        });	
        
        assertEquals(rowCount, userList.size());
    }

}
