package org.avlasov.photoapp.api.gateway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.url.path.login")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.url.path.registration")).permitAll()
                .antMatchers(environment.getProperty("api.url.path.h2console")).permitAll()
                .antMatchers(environment.getProperty("api.url.path.zuul.actuator")).permitAll()
                .antMatchers(environment.getProperty("api.url.path.users.actuator")).permitAll()
                .anyRequest().authenticated()
                .and().addFilter(authenticationFilter());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private Filter authenticationFilter() throws Exception {
        return new AuthorizationFilter(authenticationManager(), environment);
    }



}
