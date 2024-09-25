////package com.course.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig{
////
////    private static final String[] WHITE_LIST_URL= {"/api/v1/register","/api/v1/validate"};
////
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http
////                .csrf(AbstractHttpConfigurer::disable)
////                .cors(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(request -> request.requestMatchers("/auth/login", "/cloudinary/upload")
////                        .permitAll()
////                        .requestMatchers(WHITE_LIST_URL)
////                        .permitAll()
////                        .requestMatchers("/auth/google")
////                        .permitAll()
////                        .requestMatchers(HttpMethod.POST, "/users")
////                        .permitAll()
////                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
////
////                        .requestMatchers(HttpMethod.GET)
////                        .permitAll()
////                        .anyRequest()
////                        .authenticated())
//////                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
//////                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//////                .authenticationProvider(authenticationProvider())
//////                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
////                .build();
////    }
////
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////}
//
//package com.course.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/api/v1/register", "/api/v1/validate").permitAll()
//                .anyRequest().authenticated();
//        return http.build();
//    }
//
//    @Bean
//    public  BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}