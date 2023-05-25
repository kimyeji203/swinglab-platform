package com.dailystudy.swinglab.service.framework.http.response.domain;

import lombok.Data;

@Data
public class LoginResponse
{
    private String loginResult;
    private String errorMessage;
}
