package com.ninePixels.poetry.authserver;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
//@EnableDiscoveryClient
@RestController
public class AuthServerApplication {

	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.driverClassName}")
	private String jdbcDriverClassName;
	@Value("${spring.datasource.username}")
	private String jdbcUserName;
	@Value("${spring.datasource.password}")
	private String jdbcPassword;
	


	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@RequestMapping(value = { "/user" }, produces = "application/json")
	public Map<String, Object> user(OAuth2Authentication user) {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getAuthorities()));

		return userInfo;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUserName);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}
	


}
