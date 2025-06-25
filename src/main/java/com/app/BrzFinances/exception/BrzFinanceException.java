package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BrzFinanceException extends RuntimeException{

    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Internal server error");

        return pb;
    }
}
