package com.dailystudy.swinglab.service.framework.auth;

import com.dailystudy.swinglab.service.business.auth.service.CustomUserDetailsService;
import com.dailystudy.swinglab.service.business.domain.JwtToken;
import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.business.repository.user.UserRepository;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider
{
    @Value("${security.jwt.key}")
    private String securityJwtKey;

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1 * 60 * 1000l;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000l;

    @PostConstruct
    protected void init ()
    {
        securityJwtKey = Base64.getEncoder().encodeToString(securityJwtKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Token 생성
     *
     * @param user
     * @return
     */
    public JwtToken generateJwtToken (User user)
    {
        long todayTime = new Date().getTime();
        Map<String, Object> header = this.createHeader();
        Map<String, Object> claims = this.createClaims(user);

        String accessToken = generateToken(header, claims, user.getUserId(), new Date(todayTime + ACCESS_TOKEN_EXPIRE_TIME));
        String refreshToken = generateToken(header, claims, user.getUserId(), new Date(todayTime + REFRESH_TOKEN_EXPIRE_TIME));

        JwtToken result = new JwtToken();
        result.setAccessToken(accessToken);
        result.setRefreshToken(refreshToken);
        result.setUserSid(user.getUserId());
        return result;
    }

    /**
     * Access token 재발급
     *
     * @param token
     * @return
     */
    public JwtToken refreshAccessToken (String token)
    {
        boolean isValidToken = this.isValidToken(token);
        if (!isValidToken)
        {
            log.error("The refreshToken is invalid");
            return null;
        }

        String loginId = this.getLoginIdFromToken(token);
        User user = userRepository.findByLoginIdAndDelYnFalse(loginId);

        long todayTime = new Date().getTime();
        Map<String, Object> header = this.createHeader();
        Map<String, Object> claims = this.createClaims(user);

        String accessToken = generateToken(header, claims, user.getUserId(), new Date(todayTime + ACCESS_TOKEN_EXPIRE_TIME));

        JwtToken result = new JwtToken();
        result.setAccessToken(accessToken);
        result.setRefreshToken(token);
        result.setUserSid(user.getUserId());
        return result;
    }

    /**
     * JWT의 "헤더" 값을 생성해주는 메서드
     *
     * @return HashMap<String, Object>
     */
    private Map<String, Object> createHeader ()
    {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    /**
     * 사용자 정보를 기반으로 클래임을 생성해주는 메서드
     *
     * @param user 사용자 정보
     * @return Map<String, Object>
     */
    private Map<String, Object> createClaims (User user)
    {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        log.info("loginId :" + user.getLoginId());
        log.info("userNm :" + user.getName());

        claims.put("userId", user.getUserId());
        claims.put("loginId", user.getLoginId());
        claims.put("userNm", user.getName());
        return claims;
    }

    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     *
     * @param header
     * @param claims
     * @param userId
     * @param expDate
     * @return
     */
    public String generateToken (Map<String, Object> header, Map<String, Object> claims, Long userId, Date expDate)
    {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setHeader(header)  // Header
                .setClaims(claims)  // Payload - Claims 구성
                .setSubject(String.valueOf(userId)) // Payload - Subject 구성
                .signWith(SignatureAlgorithm.HS256, createSignature())  // Signature 구성
                .setIssuedAt(new Date()) // 발생시간
                .setExpiration(expDate); // Expired Date 구성
        return builder.compact();
    }

    /**
     * JWT "서명(Signature)" 발급을 해주는 메서드
     *
     * @return Key
     */
    private Key createSignature ()
    {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(securityJwtKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * Header 내에 토큰을 추출합니다.
     *
     * @param header 헤더
     * @return String
     */
    public String getTokenFromHeader (String header)
    {
        if (!header.startsWith(SwinglabConst.BEARER_TOKEN))
        {
            return StringUtils.EMPTY;
        }
        return header.split(" ")[1];
    }

    /**
     * 토큰 정보를 기반으로 Claims 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return Claims : Claims
     */
    private Claims getClaimsFormToken (String token)
    {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(securityJwtKey))
                .parseClaimsJws(token).getBody();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return String : 사용자 아이디
     */
    public String getLoginIdFromToken (String token)
    {
        Claims claims = getClaimsFormToken(token);
        return claims.get("loginId").toString();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return String : 사용자 아이디
     */
    public String getUserIdFromToken (String token)
    {
        Claims claims = getClaimsFormToken(token);
        return claims.get("userId").toString();
    }

    /**
     * 유효한 토큰인지 확인 해주는 메서드
     *
     * @param token
     * @return
     */
    public boolean isValidToken (String token)
    {
        try
        {
            Claims claims = getClaimsFormToken(token);

            log.info("expireTime :" + claims.getExpiration());
            log.info("userId :" + claims.get("userId"));
            log.info("loginId :" + claims.get("loginId"));
            log.info("userNm :" + claims.get("userNm"));

            return true;
        } catch (ExpiredJwtException exception)
        {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception)
        {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception)
        {
            log.error("Token is null");
            return false;
        }
    }

    /**
     * @param loginId
     * @return
     */
    public Authentication getAuthenticationFromToken (String loginId)
    {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginId);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}

