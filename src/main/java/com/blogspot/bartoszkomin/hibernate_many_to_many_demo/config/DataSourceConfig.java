package com.blogspot.bartoszkomin.hibernate_many_to_many_demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource dataSource(){
		//jdbc:hsqldb:mem:testdb
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
			.build();
		return db;
	}

}
