package com.example.nobsv2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }

    // @Bean
    // public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    //     UserDetails admin = User
    //             .withUsername("admin")
    //             .authorities("BASIC", "SPECIAL")
    //             .roles("superuser")
    //             .password(encoder.encode("1"))
    //             .build();
        
    //     UserDetails user = User
    //             .withUsername("user")
    //             .authorities("BASIC")
    //             .roles("basicuser")
    //             .password(encoder.encode("2"))
    //             .build();

    //     return new InMemoryUserDetailsManager(admin, user);
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        try {
            return httpSecurity
            //.csrf(csrf -> csrf.disable())
            // Allows for POST, PUT, and DELETE mappings with authentication
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> {
                authorize.requestMatchers("/createnewuser").permitAll();

                // Must be at the bottom
                authorize.anyRequest().authenticated();


                // authorize.requestMatchers("/open").permitAll();
                // authorize.requestMatchers("/closed").authenticated();
                // // Have to disable CSRF first for PUT, DELETE, and POST
                // authorize.requestMatchers(HttpMethod.POST, "/product").authenticated();

                // // Adding authority
                // authorize.requestMatchers(HttpMethod.GET, "/special").hasAuthority("SPECIAL");
                // authorize.requestMatchers(HttpMethod.GET, "/basic").hasAnyAuthority("SPECIAL", "BASIC");

                // authorize.requestMatchers(HttpMethod.GET, "/special").hasRole("superuser");
                })
                // .httpBasic(Customizer.withDefaults())
                .addFilterBefore(
                    new BasicAuthenticationFilter(authenticationManager(httpSecurity)),
                    UsernamePasswordAuthenticationFilter.class
                )
                .build();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
