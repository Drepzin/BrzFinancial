package com.app.BrzFinances.controller;

import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import com.app.BrzFinances.entity.dto.BrzUserResponseDto;
import com.app.BrzFinances.service.BrzUserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BrzUser")
public class BrzUserController {

    private final BrzUserServiceImpl brzUserServiceImpl;

    public BrzUserController(BrzUserServiceImpl brzUserServiceImpl) {
        this.brzUserServiceImpl = brzUserServiceImpl;
    }

    @PostMapping
    public ResponseEntity<BrzUserResponseDto> addBrzUser(@Valid @RequestBody BrzUserRequestDto brzUserRequestDto) {
        return new ResponseEntity<>(brzUserServiceImpl.addBrzUser(brzUserRequestDto), HttpStatusCode.valueOf(201));
    }
}
