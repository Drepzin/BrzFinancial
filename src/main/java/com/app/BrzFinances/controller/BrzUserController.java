package com.app.BrzFinances.controller;

import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import com.app.BrzFinances.entity.dto.BrzUserResponseDto;
import com.app.BrzFinances.service.BrzUserService;
import com.app.BrzFinances.service.BrzUserServiceImpl;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/BrzUser")
public class BrzUserController {

    private final BrzUserService brzUserService;

    public BrzUserController(BrzUserServiceImpl brzUserServiceImpl) {
        this.brzUserService= brzUserServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<BrzUserResponseDto>> getAllBrzUsers(){
        return new ResponseEntity<>(brzUserService.returnAllUsers(), HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<BrzUserResponseDto> addBrzUser(@Valid @RequestBody BrzUserRequestDto brzUserRequestDto) {
        return new ResponseEntity<>(brzUserService.addUser(brzUserRequestDto), HttpStatusCode.valueOf(201));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BrzUserResponseDto> updateBrzUser(@PathVariable("id") Long id, @RequestBody BrzUserRequestDto brzUserRequestDto){
        return new ResponseEntity<>(brzUserService.updateUserById(id, brzUserRequestDto), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{id}")
    public void deleteBrzUser(@PathVariable("id") Long id){
        brzUserService.deleteUserById(id);
    }

}
