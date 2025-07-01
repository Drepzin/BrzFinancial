package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProductNotFoundException extends BrzFinanceException{

    private String detail;

    public ProductNotFoundException(String detail) {
        this.detail = detail;
    }

    public ProblemDetail toProblemDetail(){
        var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        //
        pd.setTitle("Product not found");
        pd.setDetail(detail);
        return pd;
    }
}
