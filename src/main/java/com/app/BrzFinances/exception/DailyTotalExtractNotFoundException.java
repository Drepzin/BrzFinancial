package com.app.BrzFinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

public class DailyTotalExtractNotFoundException extends BrzFinanceException{

    private String detail;

    public DailyTotalExtractNotFoundException(String detail){
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pb.setTitle("Daily total extract not found");
        pb.setDetail(detail);
        return pb;
    }
}
