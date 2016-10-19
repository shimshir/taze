package de.admir.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import static de.admir.Constants.API_REST_BASE_PATH;

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http/*.authorizeRequests().antMatchers(API_REST_BASE_PATH + "/sessions", API_REST_BASE_PATH + "/sessions*//*").denyAll().and()
                .authorizeRequests().antMatchers(API_REST_BASE_PATH + "/carts", API_REST_BASE_PATH + "/carts*//*").denyAll().and()
                .authorizeRequests().antMatchers(API_REST_BASE_PATH + "/cartEntries", API_REST_BASE_PATH + "/cartEntries*//*").denyAll().and()*/
                .authorizeRequests().anyRequest().permitAll().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();
    }
}
