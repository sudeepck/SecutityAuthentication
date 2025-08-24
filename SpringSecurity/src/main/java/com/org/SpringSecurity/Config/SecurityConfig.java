    package com.org.SpringSecurity.Config;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.AuthenticationProvider;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity //--> to remove default Login form
    public class SecurityConfig {

        @Autowired
        private  UserDetailsService userDetailsService;
        @Autowired
        private JwtAuthFilter jwtAuthFilter;
        @Bean
        public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
            System.out.println("securityCongfig");
            return http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(request -> request
                            .requestMatchers(
                                    "/auth/signup",
                                    "/auth/updatePassword",
                                    "/auth/login"
                            ).permitAll()
                            .anyRequest().authenticated())
                    .httpBasic(Customizer.withDefaults())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class) // Uncomment when ready
                    .build();
        }

        @Bean
        public AuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));// not to use any password encoder;
            daoAuthenticationProvider.setUserDetailsService(userDetailsService);
            return  daoAuthenticationProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return  authenticationConfiguration.getAuthenticationManager();
        }


    // issue with this is not connecting to DB
//    @Bean
//    public  UserDetailsService userDetailsService(){
//        UserDetails user = User
//                .withDefaultPasswordEncoder()
//                .username("kiran")
//                .password("k@123")
//                .roles("USER")
//                .build();
//        return  new InMemoryUserDetailsManager(user);
//    }

}
