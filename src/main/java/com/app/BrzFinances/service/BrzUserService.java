package com.app.BrzFinances.service;

import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import com.app.BrzFinances.entity.dto.BrzUserResponseDto;

import java.util.List;

public interface BrzUserService {

    BrzUserResponseDto addUser(BrzUserRequestDto brzUserRequestDto);

    void deleteUserById(Long id);

    BrzUserResponseDto updateUserById(Long id, BrzUserRequestDto brzUserRequestDto);

    List<BrzUserResponseDto> returnAllUsers();
}
