package com.icommerce.product.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created on 6/14/20
 *
 * @author dangkhoa.phung
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    /**
     * Catch all for any other exceptions...
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class, MissingServletRequestParameterException.class, ServletRequestBindingException.class, ConversionNotSupportedException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, HttpMessageNotWritableException.class, MissingServletRequestPartException.class, BindException.class, NoHandlerFoundException.class,Exception.class})
    @ResponseBody
    public ResponseEntity<?> handleAnyException(Exception e) {
        LOG.error("handleAnyException :" + e.getMessage());
        LOG.error("handleAnyException :", e);
        Throwable throwable = new Throwable("Internal Server Error :" + e.getMessage());
        return errorResponse(throwable, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionMessage> errorResponse(Throwable throwable, HttpStatus status) {
        if (null != throwable) {
            return response(new ExceptionMessage(throwable), status);
        } else {
            return response(null, status);
        }
    }

    private <T> ResponseEntity<T> response(T body, HttpStatus status) {
        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }
}
