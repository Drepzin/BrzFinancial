package com.app.BrzFinances.service.impl;

import com.app.BrzFinances.entity.BrzUser;
import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import com.app.BrzFinances.entity.dto.BrzUserResponseDto;
import com.app.BrzFinances.exception.BrzFinanceException;
import com.app.BrzFinances.exception.CpfOrEmailAlreadyExistException;
import com.app.BrzFinances.exception.UserNotFoundException;
import com.app.BrzFinances.repository.BrzUserRepository;
import com.app.BrzFinances.service.BrzUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrzUserServiceImpl implements BrzUserService {

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
        var optUser = findUserById(id);
        brzUserRepository.delete(optUser);
    }

    @Override
    public BrzUserResponseDto updateUserById(Long id, BrzUserRequestDto brzUserRequestDto) {
        var optUser = findUserById(id);
        Updater.updateUser(optUser, brzUserRequestDto);
        brzUserRepository.save(optUser);
        return BrzUserResponseDto.convert.toDto(optUser);
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

    public BrzUser findUserById(Long id){
        var optUser = brzUserRepository.findById(id);
        if(optUser.isEmpty()){
            throw new UserNotFoundException("This id don't belong to any user");
        }
        return optUser.get();
    }

    private class Updater{

        public static void updateUser(BrzUser user1, BrzUserRequestDto user2){

            String firstName = user2.firstName();
            String secondName = user2.secondName();
            String cpf = user2.cpf();
            String email = user2.email();
            String password = user2.password();

            if(firstName != null){
                user1.setFirstName(firstName);
            }
            if(secondName != null){
                user1.setSecondName(secondName);
            }
            if(cpf != null){
                user1.setCpf(cpf);
            }
            if(email != null){
                user1.setEmail(email);
            }
            if(password != null){
                user1.setPassword(password);
            }
        }
    }
}
