package com.JEEProject.TableStore.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.AntPathMatcher;

import static com.JEEProject.TableStore.Auth.user.Permission.*;
import static com.JEEProject.TableStore.Auth.user.Role.ADMIN;
import static com.JEEProject.TableStore.Auth.user.Role.MANAGER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//       http
//               .authorizeHttpRequests((authorize) -> authorize
//               .dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
//                       .requestMatchers(antMatcher("/css/**")).permitAll()
//                       .requestMatchers(antMatcher("/js/**")).permitAll()
//                       .requestMatchers(antMatcher("/images/**")).permitAll()
//       );
//      return http.build();
//    }
private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
                                .requestMatchers(antMatcher("/css/**")).permitAll()
                                .requestMatchers(antMatcher("/js/**")).permitAll()
                                .requestMatchers(antMatcher("/images/**")).permitAll()
                                .requestMatchers(antMatcher("/catalog/**")).permitAll()
                                .requestMatchers(antMatcher("/user/**")).permitAll()
                                .requestMatchers(antMatcher("/login/**")).permitAll()
                                .requestMatchers(antMatcher("/errorPage/**")).permitAll()
                                .requestMatchers(antMatcher("/accessDenied/**")).permitAll()
                                .requestMatchers(antMatcher("/api/v1/auth/**")).permitAll()
                                .requestMatchers(antMatcher("/admin/products/**")).hasRole(ADMIN.name())
                                .requestMatchers(antMatcher(GET,"/admin/products/**")).hasAuthority(ADMIN_READ.name())
                                .requestMatchers(antMatcher(POST,"/admin/products/**")).hasAuthority(ADMIN_CREATE.name()).anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                ).exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                    // Xử lý khi bị từ chối đăng nhập
                    response.sendRedirect("/login"); // Chuyển hướng về trang đăng nhập
                }).accessDeniedPage("/accessDenied")
        );

        return http.build();
    }
}
