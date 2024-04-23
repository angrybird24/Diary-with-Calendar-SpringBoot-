package com.codehows.diary.Config;

import com.codehows.diary.Config.jwt.TokenProvider;
import com.codehows.diary.Config.oauth.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.codehows.diary.Config.oauth.OAuth2SuccessHandler;
import com.codehows.diary.Config.oauth.OAuth2UserCustomService;
import com.codehows.diary.Repository.RefreshTokenRepository;
import com.codehows.diary.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebOAuthSecurityConfig {

    private final OAuth2UserCustomService oAuth2UserCustomService;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/img/**", "/css/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) ->
                        csrf.disable()
                )
                .httpBasic((httpBasic) ->
                        httpBasic.disable()
                )
                .formLogin((formLogin) ->
                        formLogin.disable()
                )
                .logout((logout) ->
                        logout.disable()
                );

        http
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests()
                .requestMatchers("/api/token").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll();


        http
                .oauth2Login((oauth2Login) ->
                        oauth2Login
                                .loginPage("/login")
                                .authorizationEndpoint(authorization -> authorization
                                        .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository()))
                                .successHandler(oAuth2SuccessHandler())
                                .userInfoEndpoint(userInfo -> userInfo
                                        .userService(oAuth2UserCustomService)));



        http
                .logout((logout) ->
                        logout
                                .logoutSuccessUrl("/login"));


        http
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),new AntPathRequestMatcher("/api/**")));

        return http.build();
    }


    @Bean
    public OAuth2SuccessHandler oAuth2SuccessHandler() {
        return new OAuth2SuccessHandler(tokenProvider,
                refreshTokenRepository,
                oAuth2AuthorizationRequestBasedOnCookieRepository(),
                userService
        );
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter(tokenProvider);
    }

    @Bean
    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
//@RequiredArgsConstructor
//@Configuration
//public class WebOAuthSecurityConfig {
//
//    private final OAuth2UserCustomService oAuth2UserCustomService;
//    private final TokenProvider tokenProvider;
//    private final RefreshTokenRepository refreshTokenRepository;
//    private final UserService userService;
//
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/img/**", "/css/**", "/js/**");
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .httpBasic().disable()
//                .formLogin().disable()
//                .logout().disable();
//
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//
//        http.authorizeRequests()
//                .requestMatchers("/api/token").permitAll()
//                .requestMatchers("/api/**").authenticated()
//                .anyRequest().permitAll();
//
//        http.oauth2Login()
//                .loginPage("/login")
//                .authorizationEndpoint()
//                .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository())
//                .and()
//                .successHandler(oAuth2SuccessHandler())
//                .userInfoEndpoint()
//                .userService(oAuth2UserCustomService);
//
//        http.logout()
//                .logoutSuccessUrl("/login");
//
//
//        http.exceptionHandling()
//                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
//                        new AntPathRequestMatcher("/api/**"));
//
//
//        return http.build();
//    }
//
//
//    @Bean
//    public OAuth2SuccessHandler oAuth2SuccessHandler() {
//        return new OAuth2SuccessHandler(tokenProvider,
//                refreshTokenRepository,
//                oAuth2AuthorizationRequestBasedOnCookieRepository(),
//                userService
//        );
//    }
//
//    @Bean
//    public TokenAuthenticationFilter tokenAuthenticationFilter() {
//        return new TokenAuthenticationFilter(tokenProvider);
//    }
//
//    @Bean
//    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
//        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
//@RequiredArgsConstructor@Configuration
//public class WebOAuthSecurityConfig {
//
//    // 이전과 동일한 필드 및 빈 선언
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//            http
//                    .csrf((csrf) ->
//                            csrf.disable())
//                    .httpBasic((httpBasic) ->
//                            httpBasic.disable())
//                    .formLogin((formLogin) ->
//                            formLogin.disable())
//                    .logout((logout) ->
//                            logout.disable());
//
//
//        http.sessionManagement(sessionmSg->{
//            sessionmSg.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        });
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//
//
//
//        http.authorizeRequests(authorizeRequests ->
//                authorizeRequests
//                        .requestMatchers().antMatchers("/api/token").permitAll()
//                        .requestMatchers().antMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll()
//        );
//
//        http.oauth2Login(oauth2 ->{
//
//            oauth2.loginPage("/login")
//                    .authorizationEndpoint()
//                    .authorizationRequestRepository(oAuth2AuthorizationRequestBasedOnCookieRepository());
//
//                    oauth2.successHandler(oAuth2SuccessHandler()).userInfoEndpoint()
//                            .userService(oAuth2UserCustomService);
//
//                }
//        );
//
//        http.logout(logout->logout.logoutSuccessUrl("/login"));
//        http.exceptionHandling(handle->{
//            handle.defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), new AntPathRequestMatcher("/api/**"));
//        });
//
//        return http.build();
//    }
//}
//
//
//
//@Bean
//    public OAuth2SuccessHandler oAuth2SuccessHandler() {
//        return new OAuth2SuccessHandler(tokenProvider,
//                refreshTokenRepository,
//                oAuth2AuthorizationRequestBasedOnCookieRepository(),
//                userService
//        );
//    }
//
//    @Bean
//    public TokenAuthenticationFilter tokenAuthenticationFilter() {
//        return new TokenAuthenticationFilter(tokenProvider);
//    }
//
//    @Bean
//    public OAuth2AuthorizationRequestBasedOnCookieRepository oAuth2AuthorizationRequestBasedOnCookieRepository() {
//        return new OAuth2AuthorizationRequestBasedOnCookieRepository();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}