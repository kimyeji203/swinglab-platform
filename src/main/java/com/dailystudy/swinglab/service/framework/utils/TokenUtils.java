package com.dailystudy.swinglab.service.framework.utils;

import com.dailystudy.swinglab.service.business.domain.entity.user.User;
import com.dailystudy.swinglab.service.framework.SwinglabConst;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 관련된 토큰 Util
 *
 * @author lee
 * @fileName TokenUtils
 * @since 2022.12.23
 */
@Slf4j
public class TokenUtils
{
    // 2023-rlvdmstodrkr-tmdnldfoq-vmffotvha-tlzmfltzl
    private static final String JWT_SECRET_KEY = "MjAyMy1ybHZkbXN0b2Rya3ItdG1kbmxkZm9xLXZtZmZvdHZoYS10bHptZmx0emw=";

    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     *
     * @param user user : 사용자 정보
     * @return String : 토큰
     */
    public static String generateJwtToken (User user)
    {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())                              // Header 구성
                .setClaims(createClaims(user))                       // Payload - Claims 구성
                .setSubject(String.valueOf(user.getUserId()))        // Payload - Subject 구성
                .signWith(SignatureAlgorithm.HS256, createSignature())  // Signature 구성
                .setExpiration(createExpiredDate());                    // Expired Date 구성
        return builder.compact();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환 해주는 메서드
     *
     * @param token String : 토큰
     * @return String : 사용자 정보
     */
    public static String parseTokenToUserInfo (String token)
    {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 유효한 토큰인지 확인 해주는 메서드
     *
     * @param token String  : 토큰
     * @return boolean      : 유효한지 여부 반환
     */
    public static boolean isValidToken (String token)
    {
        try
        {
            Claims claims = getClaimsFormToken(token);

            log.info("expireTime :" + claims.getExpiration());
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
     * Header 내에 토큰을 추출합니다.
     *
     * @param header 헤더
     * @return String
     */
    public static String getTokenFromHeader (String header)
    {
        if (!header.startsWith(SwinglabConst.BEARER_TOKEN))
        {
            return StringUtils.EMPTY;
        }
        return header.split(" ")[1];
    }

    /**
     * 토큰의 만료기간을 지정하는 함수
     *
     * @return Calendar
     */
    private static Date createExpiredDate ()
    {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 8);     // 8시간
        // c.add(Calendar.DATE, 1);         // 1일
        return c.getTime();
    }

    /**
     * JWT의 "헤더" 값을 생성해주는 메서드
     *
     * @return HashMap<String, Object>
     */
    private static Map<String, Object> createHeader ()
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
     * @param userDto 사용자 정보
     * @return Map<String, Object>
     */
    private static Map<String, Object> createClaims (User userDto)
    {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        log.info("loginId :" + userDto.getLoginId());
        log.info("userNm :" + userDto.getName());

        claims.put("loginId", userDto.getLoginId());
        claims.put("userNm", userDto.getName());
        return claims;
    }

    /**
     * JWT "서명(Signature)" 발급을 해주는 메서드
     *
     * @return Key
     */
    private static Key createSignature ()
    {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 토큰 정보를 기반으로 Claims 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return Claims : Claims
     */
    private static Claims getClaimsFormToken (String token)
    {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET_KEY))
                .parseClaimsJws(token).getBody();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return String : 사용자 아이디
     */
    public static String getLoginIdFromToken (String token)
    {
        Claims claims = getClaimsFormToken(token);
        return claims.get("loginId").toString();
    }
}