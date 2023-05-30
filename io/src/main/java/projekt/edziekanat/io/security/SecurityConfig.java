package projekt.edziekanat.io.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
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
                "SELECT id_osoba, haslo, aktywne FROM osoba WHERE CAST(id_osoba as varchar) = ?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT id_osoba, rola FROM rola WHERE CAST(id_osoba as varchar) = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET,"/").hasAnyRole("STUDENT", "WYKŁADOWCA","PRACOWNIK_DZIEKANATU")
                                .requestMatchers(HttpMethod.GET, "/sprawdz-oceny/**").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET, "/wybor-przedmiotu/**").hasRole("STUDENT")
                                .requestMatchers(HttpMethod.GET, "/wybierzGrupe/**").hasRole("WYKŁADOWCA")
                                .requestMatchers(HttpMethod.GET, "/listaStudentow/**").hasRole("WYKŁADOWCA")
                                .anyRequest().authenticated()


        ).formLogin(form ->
                form
                        .loginPage("/showLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
        )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/access-denied"))
        ;

        http.httpBasic();

        http.csrf().disable();

        return http.build();
    }


}
