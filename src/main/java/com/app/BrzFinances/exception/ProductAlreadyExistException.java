package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ProductAlreadyExistException extends BrzFinanceException{

    private String detail;

    public ProductAlreadyExistException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.valueOf(422));

        pb.setTitle("Product already exist!");
        pb.setDetail(detail);
        return pb;
    }
}
