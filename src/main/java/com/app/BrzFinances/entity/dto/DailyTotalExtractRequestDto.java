package com.app.BrzFinances.entity.dto;

import com.app.BrzFinances.entity.DailyTotalExtract;

import java.util.Date;

public record DailyTotalExtractRequestDto(Date date, Long brzUser) {

    public DailyTotalExtract toDailyTotalExtract(){
        return new DailyTotalExtract(date, )
    }
}
