package com.tanmay.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.tanmay.blog.security.CustomUserDetailService;
import com.tanmay.blog.security.JwtAuthenticationEntryPoint;
import com.tanmay.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig 
{
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	        http.csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests()
	                .requestMatchers("/auth/**").permitAll() .requestMatchers("/auth/api-docs").permitAll()
	                .requestMatchers(HttpMethod.GET).permitAll()
	                .anyRequest()
	                .authenticated()
	                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }
	
	 
	 @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 
	 @Bean
	    public DaoAuthenticationProvider daoAuthenticationProvider() {

	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setUserDetailsService(this.customUserDetailService);
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;

	    }

	    @Bean
	    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
	        return configuration.getAuthenticationManager();
	    }

	 
}