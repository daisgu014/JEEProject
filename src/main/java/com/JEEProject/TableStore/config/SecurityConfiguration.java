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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import static com.JEEProject.TableStore.Auth.user.Permission.*;
import static com.JEEProject.TableStore.Auth.user.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
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
                                .requestMatchers(antMatcher("/components/**")).permitAll()
                                .requestMatchers(antMatcher("/messageNotLogin/**")).permitAll()
                                .requestMatchers(antMatcher("/catalog/**")).permitAll()
                                .requestMatchers(antMatcher("/productDetail/**")).permitAll()
                                .requestMatchers(antMatcher("/user/**")).permitAll()
                                .requestMatchers(antMatcher("/login/**")).permitAll()
                                .requestMatchers(antMatcher("/errorPage/**")).permitAll()
                                .requestMatchers(antMatcher("/accessDenied/**")).permitAll()
                                .requestMatchers(antMatcher("/api/v1/auth/**")).permitAll()
                                .requestMatchers(antMatcher("/cart/**")).permitAll()
                                .requestMatchers(antMatcher("/api/v1/product/**")).hasRole(ADMIN.name())
                                .anyRequest()
                                .authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) ->{
                                    SecurityContextHolder.clearContext();
                                    response.sendRedirect("/login");
                                } )

                ).exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint((request, response, authException) -> {
                    // Xử lý khi bị từ chối đăng nhập
                    response.sendRedirect("/login"); // Chuyển hướng về trang đăng nhập
                }).accessDeniedPage("/accessDenied")
                        .defaultAccessDeniedHandlerFor((request, response, accessDeniedException) -> {
                            // Xử lý khi bị từ chối truy cập (bao gồm lỗi 404)
                            response.sendRedirect("/errorPage"); // Chuyển hướng về trang lỗi tùy chỉnh (404.html)
                        }, new AntPathRequestMatcher("/errorPage"))
        );

        return http.build();
    }
}
