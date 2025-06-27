package com.app.BrzFinances.entity.dto;

import com.app.BrzFinances.entity.BrzUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record BrzUserRequestDto(@NotBlank @JsonProperty("first_name") String firstName,
                                @NotBlank @JsonProperty("second_name") String secondName,
                                @NotBlank String cpf, @NotBlank String email, @NotBlank String password) {

    public BrzUser toUser(){
        return new BrzUser(firstName, secondName, cpf, email, password);
    }
}
