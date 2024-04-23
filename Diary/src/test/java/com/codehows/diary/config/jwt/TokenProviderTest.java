package com.codehows.diary.config.jwt;

import com.codehows.diary.Config.jwt.JwtProperties;
import com.codehows.diary.Config.jwt.TokenProvider;
import com.codehows.diary.Domain.User;
import com.codehows.diary.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("GenerateToken : 유저정보와 만료기간을 전달해 토큰만들수잇음")
    @Test
    void generateToken(){
        User testUser = userRepository.save(User.builder()
                .email("test@test.com")
                .password("test")
                .build());
        //when
        String token = tokenProvider.generateToken(testUser , Duration.ofDays(14));

        //then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id",Long.class);

        assertThat(userId).isEqualTo(testUser.getId());

    }


    @DisplayName("valid Token: 만료된 토큰엔 유효성 검증 실패")
    @Test
    void validToken_invalidToken(){
        //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis())).build().createToken(jwtProperties);

        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isFalse();
    }



    @DisplayName("valid Token 유효한 토큰 유효성 검증 성공")
    @Test
    void validToken_validToken(){
        //given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validToken(token);
        //then
        assertThat(result).isTrue();
    }


    @DisplayName("getAuthentication 토큰 기반 인증 정보 가져옴")
    @Test
    void getAuthentication() {
        // given
        String userEmail = "test@test.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        assertThat(userDetails.getUsername()).isEqualTo(userEmail);
    }


    @DisplayName("getUserId 토큰 으로 유저 ID 가져올수있다")
    @Test
    void getUserId(){
        Long userId = 1L;
        String  token = JwtFactory.builder()
                .claims(Map.of("id",userId))
                .build()
                .createToken(jwtProperties);

        Long userIdByToken = tokenProvider.getUserId(token);
        assertThat(userIdByToken).isEqualTo(userId);
    }

}
