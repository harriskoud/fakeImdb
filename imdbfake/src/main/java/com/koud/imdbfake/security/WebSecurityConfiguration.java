package com.koud.imdbfake.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		/*
		 * web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
		 * "/swagger-resources/**", "/configuration/**", "/swagger-ui.html",
		 * "/webjars/**");
		 * 
		 * web.ignoring().antMatchers(HttpMethod.GET, "/v1/poem/getListOfUserPoems/**");
		 * web.ignoring().antMatchers(HttpMethod.GET,
		 * "/v1/poem/getListOfWeeklyPoems/**");
		 * web.ignoring().antMatchers(HttpMethod.GET, "/v1/poem/getListOfMBCPoems/**");
		 * web.ignoring().antMatchers(HttpMethod.GET,
		 * "/v1/poem/getPoemLines/poemId/**");
		 */

		 web.ignoring().antMatchers(HttpMethod.POST, "/user");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
	}

}