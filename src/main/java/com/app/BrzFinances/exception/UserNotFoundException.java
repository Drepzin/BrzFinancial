package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotFoundException extends BrzFinanceException{

    private String detail;

    public UserNotFoundException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("User don't exist!");
        pb.setDetail(detail);
        return pb;
    }
}
