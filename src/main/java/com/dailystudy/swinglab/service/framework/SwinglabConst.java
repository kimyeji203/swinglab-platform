package com.dailystudy.swinglab.service.framework;

import lombok.Getter;

public class SwinglabConst
{
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TOKEN = "Bearer ";
    public static final String TIME_ZONE = "Asia/Seoul";
    public static final String COOKIE_REFRESH_TOKEN_KEY = "refresh_token";
    public static final String DAY_FORMAT = "yyyy-MM-dd";
    public static final String DT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm";
    public static final String UTF8 = "UTF-8";
    public static final String TXID = "TXID";

    public static final Integer DF_MIN = 15;

    /*
     * 토큰
     */
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L; // 1시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 30 * 24 * 60 * 60 * 1000L; // 30일

    public static final String USER_ID = "userId";
    public static final String LOGIN_ID = "loginId";
    public static final String USER_NM = "userNm";

    @Getter
    public enum JWT_EXCEPT
    {
        EXPIRED("TokenExpiredError", "만료된 토큰입니다."),
        TAMPERED("TokenTamperedError", "위/변조된 토큰입니다."),
        NULL("TokenIsNullError", "유효하지 않은 토큰입니다.")
        ;

        private String title;
        private String errorMessage;

        JWT_EXCEPT(String title, String errorMessage)
        {
            this.title = title;
            this.errorMessage = errorMessage;
        }


    }
}
