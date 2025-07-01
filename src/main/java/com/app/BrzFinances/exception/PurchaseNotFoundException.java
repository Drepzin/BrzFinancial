package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PurchaseNotFoundException extends BrzFinanceException{

    private String detail;

    public PurchaseNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Purchase not found in the system");
        pb.setDetail(detail);
        return pb;
    }
}
