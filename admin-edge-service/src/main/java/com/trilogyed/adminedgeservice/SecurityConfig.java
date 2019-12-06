package com.trilogyed.adminedgeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    // This method configures Spring Security to use the default JDBC schema that we have setup to
    // hold our user accounts and their associated authorities (aka roles).
    //
    // The DataSource is configured in the application.properties file and injected into this class. The
    // AuthenticationManagerBuilder is also injected into this class by Spring. It is the tool we use to
    // configure Spring Security to use our schema.
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);

    }

    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                //EMPLOYEE AUTHORITIES
                .mvcMatchers(HttpMethod.GET, "/product/{id}").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/invoiceItem/{id}").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/levelUp/{id}").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/invoice/{id}").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.GET, "/customer/{id}").hasAuthority("ROLE_EMPLOYEE")
                .mvcMatchers(HttpMethod.PUT, "/product/{id}").hasAuthority("ROLE_EMPLOYEE")
                //TEAM LEAD AUTHORITIES
                .mvcMatchers(HttpMethod.PUT, "/product/{id}").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/invoiceItem/{id}").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/levelUp/{id}").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/invoice/{id}").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.PUT, "/customer/{id}").hasAuthority("ROLE_TEAMLEAD")
                .mvcMatchers(HttpMethod.POST, "/customer").hasAuthority("ROLE_TEAMLEAD")
                //MANAGER AUTHORITIES
                .mvcMatchers(HttpMethod.POST, "/product").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/invoiceItem").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/invoice").hasAuthority("ROLE_MANAGER")
                .mvcMatchers(HttpMethod.POST, "/levelUp").hasAuthority("ROLE_MANAGER")
                //ADMIN AUTHORITIES
                .mvcMatchers(HttpMethod.DELETE, "/product/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/invoiceItem/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/levelUp/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/invoice/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/customer/{id}").hasAuthority("ROLE_ADMIN");


        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone").deleteCookies("JSESSIONID").deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }


}

