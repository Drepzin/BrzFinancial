package com.app.BrzFinances.service;

import com.app.BrzFinances.entity.BrzUser;
import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import com.app.BrzFinances.entity.dto.BrzUserResponseDto;
import com.app.BrzFinances.exception.BrzFinanceException;
import com.app.BrzFinances.exception.CpfOrEmailAlreadyExistException;
import com.app.BrzFinances.repository.BrzUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrzUserServiceImpl implements BrzUserService{

    private final BrzUserRepository brzUserRepository;

    public BrzUserServiceImpl(BrzUserRepository brzUserRepository) {
        this.brzUserRepository = brzUserRepository;
    }

    @Override
    public BrzUserResponseDto addUser(BrzUserRequestDto brzUserRequestDto) {
        BrzUser brzUser = brzUserRequestDto.toUser();
        var consult = brzUserRepository.findUserByCpfOrEmail(brzUser.getCpf(), brzUser.getEmail());
        if(consult.isPresent()){
            throw new CpfOrEmailAlreadyExistException("Cpf or email already in use!");
        }
        brzUserRepository.save(brzUser);
        return BrzUserResponseDto.convert.toDto(brzUser);
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public BrzUserResponseDto updateUserById(Long id, BrzUserRequestDto brzUserRequestDto) {
        return null;
    }

    @Override
    public List<BrzUserResponseDto> returnAllUsers() {
        var list = brzUserRepository.findAll();
        return list.stream().map(BrzUserResponseDto.convert::toDto).toList();
    }


    public Optional<BrzUser> findUserByCpfOrEmail(String cpf, String email){
        var optUser = brzUserRepository.findUserByCpfOrEmail(cpf, email);
        if(optUser.isEmpty()){
            throw new BrzFinanceException();
        }
        return optUser;
    }
}
