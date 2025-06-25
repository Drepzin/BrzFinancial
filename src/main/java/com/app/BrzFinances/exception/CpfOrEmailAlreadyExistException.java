package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CpfOrEmailAlreadyExistException extends BrzFinanceException{

    private String detail;

    public CpfOrEmailAlreadyExistException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Cpf or Email unavailable");
        pb.setDetail(detail);

        return pb;
    }
}
