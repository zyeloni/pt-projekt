package me.kacperlukasik.klptl2;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .accessDeniedPage("/error403");

        http
                .formLogin()//uwierzytelnienie poprzez formularz logowania
                .loginPage("/login")//formularz dostępny jest pod URL
                .successForwardUrl("/visits/index")
                .permitAll();//przyznaj dostęp wszystkim użytkownikom

        http.logout().permitAll();

        http.authorizeRequests()
                .antMatchers("/static/**", "/", "/visits/index", "/medicalVisit/search").permitAll()
                .antMatchers("/visits/details").hasRole("USER")
                .antMatchers("/visits/showForm").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        User.UserBuilder userBuilder = User.builder();

        UserDetails user = userBuilder
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();

        UserDetails admin = userBuilder
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails superadmin = userBuilder
                .username("superadmin")
                .password(passwordEncoder.encode("superadmin"))
                .roles("SUPERADMIN", "USER", "ADMIN")
                .build();

        manager.createUser(user);
        manager.createUser(admin);
        manager.createUser(superadmin);

        return manager;
    }

}
