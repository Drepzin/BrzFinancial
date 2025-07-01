package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidPaymentMethodException extends BrzFinanceException{

    private final String detail;

    public InvalidPaymentMethodException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("Invalid Payment Method");
        pb.setDetail(detail);
        return pb;
    }
}
