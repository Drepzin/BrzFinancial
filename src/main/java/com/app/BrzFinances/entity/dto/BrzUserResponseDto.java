package com.app.BrzFinances.entity.dto;

import com.app.BrzFinances.entity.BrzUser;

import java.util.List;

public record BrzUserResponseDto (String firstName, String secondName, String cpf, String email){

    public static class convert {

        public static BrzUserResponseDto toDto(BrzUser brzUser){
            return new BrzUserResponseDto(brzUser.getFirstName(), brzUser.getSecondName(), brzUser.getCpf(), brzUser.getEmail());
        }

        public static List<BrzUserResponseDto> toList(List<BrzUser> brzUsers){
            return brzUsers.stream().map(convert::toDto).toList();
        }
    }
}
