package com.dailystudy.swinglab.service.business.domain;

import lombok.Data;

@Data
public class JwtToken
{
    Long userSid;
    String accessToken;
    String refreshToken;
}
