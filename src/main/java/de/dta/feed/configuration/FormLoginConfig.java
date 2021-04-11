package de.dta.feed.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class FormLoginConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //super.configure(http);
        http
                .httpBasic()
                .and()
               . csrf().disable()
                .headers()
                    .frameOptions().disable()
                    .cacheControl().disable()
                .and()
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/api/admin/**").authenticated()
                    .antMatchers(HttpMethod.GET,"/api/feedback/**").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
       auth.inMemoryAuthentication().withUser("daniela.wolff").password(passwordEncoder().encode("MAxtor10"))
                .authorities("ROLE_USER");
        auth.inMemoryAuthentication().withUser("thomas.wolff").password(passwordEncoder().encode("MAxtor10"))
                .authorities("ROLE_ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();
    }
}
