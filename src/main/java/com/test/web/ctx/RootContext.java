package com.test.web.ctx;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"com.test.web"})
@MapperScan(basePackages = {"com.test.web"})
//@EnableAspectJAutoProxy
//@EnableTransactionManagement
//@Import({
//	MybatisContext.class, ServletContext.class
//})
	
public class RootContext {
//	@Bean
//	public DataSource dataSource() {
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
//		hikariConfig.setJdbcUrl("jdbc:mariadb://boyseos.myds.me:3307/test?serverTimezone=UTC");
//		hikariConfig.setUsername("");
//		hikariConfig.setPassword("");
//		
//		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//		return dataSource;
//	}
}
