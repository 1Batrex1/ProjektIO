package projekt.edziekanat.io.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource)
    {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select id_osoba,haslo,aktywne from osoba where cast(id_osoba as varchar) = ?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select id_osoba,rola from rola where cast(id_osoba as varchar) = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET,"/").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET, "/sprawdz-oceny/**").hasRole("STUDENT")
                                .anyRequest().authenticated()


        );

        http.httpBasic();

        http.csrf().disable();

        return http.build();
    }
}
