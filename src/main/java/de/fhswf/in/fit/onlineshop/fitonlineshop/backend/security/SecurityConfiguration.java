package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Die Klasse SecurityConfiguration ist für den Login und Logout von Benutzern
 * der Projektverwaltungs-Applikation zuständig. Zusätzlich wird die Autorisierung
 * für die einzelnen URLs festgelegt.
 */

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .requestCache().requestCache(new CustomRequestCache())
                .and()
                .authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/....").hasAuthority("USER")
//                .antMatchers("/...").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .successHandler(OnlineshopUrlAuthenticationSuccessHandler())
                .failureUrl(LOGIN_FAILURE_URL)
                .and()
                .logout()
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    /**
     * Die Methode ProjectManagerAuthenticationSuccessHandler ist für die Weiterleitung
     * des Nutzers nach dem Login, in Abhängigkeit von seiner Rolle / Authentication,
     * zuständig.
     *
     * @return  ProjectManagerUrlAuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler OnlineshopUrlAuthenticationSuccessHandler(){
        return new OnlineshopUrlAuthenticationSuccessHandler();
    }

    /**
     * Der UserDetailsService generiert Testbenutzer für die Applikation
     *
     * @return Gibt eine Reihe von Benutzern für die Applikation zurück.
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        UserDetails admin =
                User
                    .withUsername("admin")
                    .password("{noop}password")
                    .roles("ADMIN")
                    .authorities("ADMIN")
                    .build();

        UserDetails user =
                User
                    .withUsername("user")
                    .password("{noop}password")
                    .roles("USER")
                    .authorities("USER")
                    .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    /**
     * Schließt die Vaadin-Framework-Kommunikation und statische Assets
     * von Spring Security aus
     * @param web   ...
     */

    @Override
    public void configure (WebSecurity web){

        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**",
                "/api/**");
    }
}
