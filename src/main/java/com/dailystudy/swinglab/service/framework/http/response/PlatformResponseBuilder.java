package com.dailystudy.swinglab.service.framework.http.response;

import com.dailystudy.swinglab.service.framework.http.response.domain.PaginationResponse;
import com.dailystudy.swinglab.service.framework.http.response.domain.SuccessResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
public class PlatformResponseBuilder implements InitializingBean
{
    @Autowired(required = false)
    private PlatformResponseWrapper templateWrapper;

    private static PlatformResponseWrapper wrapper;

    private final static PlatformResponseWrapper defaultWrapper = val -> new ResponseEntity(val, HttpStatus.OK);

    public static ResponseEntity<SuccessResponse> build()
    {
        SuccessResponse val = new SuccessResponse();
        if (wrapper == null)
        {
            return defaultWrapper.wrap(val);
        } else
        {
            return wrapper.wrap(val);
        }
    }


    public static <T> ResponseEntity<SuccessResponse<List<T>>> build(Page<T> data)
    {
        SuccessResponse<List<T>> val = new SuccessResponse<>(data.getContent(), new PaginationResponse(data.getPageable(), data.getNumberOfElements(), (int) data.getTotalElements()));
        if (wrapper == null)
        {
            return defaultWrapper.wrap(val);
        } else
        {
            return wrapper.wrap(val);
        }
    }

    public static <T> ResponseEntity<SuccessResponse<T>> build(T data)
    {
        SuccessResponse<T> val = new SuccessResponse<T>(data);
        if (wrapper == null)
        {
            return defaultWrapper.wrap(val);
        } else
        {
            return wrapper.wrap(val);
        }
    }
    public static <T> ResponseEntity<SuccessResponse<T>> build(T data, PaginationResponse page)
    {
        SuccessResponse<T> val = new SuccessResponse<T>(data, page);
        if (wrapper == null)
        {
            return defaultWrapper.wrap(val);
        } else
        {
            return wrapper.wrap(val);
        }
    }

    public static <T> ResponseEntity<SuccessResponse<T>> build(SuccessResponse<T> val)
    {
        if (wrapper == null)
        {
            return defaultWrapper.wrap(val);
        } else
        {
            return wrapper.wrap(val);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        PlatformResponseBuilder.wrapper = templateWrapper;
    }
}
