package com.jackpot.persistence;


import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void estConnection() {
		String url = "jdbc:mysql://49.50.165.92:3306/social_dog_db";
		try(Connection con = DriverManager.getConnection(url,"yoo","gooddogDB777!")){
			log.info(con);
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
}
