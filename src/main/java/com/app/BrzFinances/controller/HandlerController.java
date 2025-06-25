package com.app.BrzFinances.controller;

import com.app.BrzFinances.exception.BrzFinanceException;
import com.app.BrzFinances.exception.CpfOrEmailAlreadyExistException;
import com.app.BrzFinances.exception.ProductAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(BrzFinanceException.class)
    public ProblemDetail CpfOrEmailAlreadyExistHandler(CpfOrEmailAlreadyExistException e){
        return e.toProblemDetail();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail invalidFieldsHandler(MethodArgumentNotValidException e){
        var invalidFieldList = e.getFieldErrors().stream().map(f -> new FieldError(f.getField(), f.getCode()));

        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problemDetail.setTitle("Field can't be blanks");
        problemDetail.setProperty("Invalid fields", invalidFieldList);

        return problemDetail;
    }

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ProblemDetail productAlreadyExist(ProductAlreadyExistException e){
       return e.toProblemDetail();
    }

    private class FieldError{

        private String detail;

        private String code;

        public FieldError(String detail, String code) {
            this.detail = detail;
            this.code = code;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
