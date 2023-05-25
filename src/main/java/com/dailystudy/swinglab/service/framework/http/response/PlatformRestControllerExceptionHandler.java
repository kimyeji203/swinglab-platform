package com.dailystudy.swinglab.service.framework.http.response;

import com.dailystudy.swinglab.service.framework.http.request.HttpRequestThreadLocal;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabBadRequestException;
import com.dailystudy.swinglab.service.framework.http.response.exception.http.SwinglabHttpException;
import com.dailystudy.swinglab.service.framework.http.response.domain.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RestExceptionHandler is only applied for RestController exceptions.
 *
 * @author yjkim
 */
@Slf4j
@RestControllerAdvice
public class PlatformRestControllerExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception)
    {
        log.error("Exception handler executed", exception);

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorResponse.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        String message = "Internal Server Error" ; //exception.getMessage();
        List<String> detailMessageList = new ArrayList<>();
        detailMessageList.add(ExceptionUtils.getStackTrace(exception)); // TODO exceptionHandler : 개발 완료후 제거

        errorResponse.setErrorMessage(message);
        errorResponse.setDetails(detailMessageList);

        HttpRequestThreadLocal.setRestApiResponse(PlatformHttpStatus.INTERNAL_SERVER_ERROR, errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    /**
     * [Swinglab] com.innerwave.Swinglab.core.exception.http.SwinglabHttpException.class
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(SwinglabHttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(SwinglabHttpException exception)
    {
        log.error("[Swinglab] SwinglabHttpException handler executed", exception);

        ErrorResponse errorResponse = new ErrorResponse();
        if (StringUtils.isNotEmpty(exception.getErrorCode()))
        {
            errorResponse.setErrorCode(exception.getErrorCode());
        } else
        {
            errorResponse.setErrorCode(String.valueOf(exception.getStatusCode().value()));
        }

        if (StringUtils.isEmpty(exception.getTitle()))
        {
            errorResponse.setTitle(exception.getStatusCode().getReasonPhrase());
        } else
        {
            errorResponse.setTitle(exception.getTitle());
        }

        errorResponse.setErrorMessage(exception.getMessage());

        List<String> detailMessageList = new ArrayList<>();
        errorResponse.setDetails(detailMessageList);

        HttpRequestThreadLocal.setRestApiResponse(PlatformHttpStatus.valueOf(exception.getStatusCode().value()),
            errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpServletRequest request,
                                                                               HttpMessageNotReadableException exception)
    {
        
        logRequestBody(request);
        log.error("HttpMessageNotReadableException handler executed", exception);

        ErrorResponse errorMessage = new ErrorResponse();
        errorMessage.setErrorCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
        errorMessage.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        String message = exception.getMessage();
        if (message == null || message.length() > 10)
        {
            message = exception.getClass().getSimpleName();
        }
        errorMessage.setErrorMessage(message);

        HttpRequestThreadLocal.setRestApiResponse(PlatformHttpStatus.BAD_REQUEST, errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpServletRequest request,
        MethodArgumentNotValidException exception)
    {
        logRequestBody(request);
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors())
        {
            String message = "Invalid Parameters";
            List<String> details = bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

            SwinglabBadRequestException badRequestException = new SwinglabBadRequestException(message, details);
            return handleHttpException(badRequestException);
        }

        HttpRequestThreadLocal.setRestApiResponse(PlatformHttpStatus.BAD_REQUEST);
        return handleException(exception);
    }

    private void logRequestBody(HttpServletRequest request)
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("[METHOD] >>> ").append(request.getMethod())
        .append(" [URI] >>> ").append(request.getRequestURI())
        .append(" [PARAMS] >>> ").append(HttpRequestThreadLocal.getRestApiResponse().getRequestBody());
        log.error(sb.toString());
    }
}
