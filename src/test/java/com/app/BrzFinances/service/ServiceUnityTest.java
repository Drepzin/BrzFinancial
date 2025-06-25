package com.app.BrzFinances.service;

import com.app.BrzFinances.entity.BrzUser;
import com.app.BrzFinances.entity.dto.BrzUserRequestDto;
import com.app.BrzFinances.exception.CpfOrEmailAlreadyExistException;
import com.app.BrzFinances.repository.BrzUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.Collections;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@Profile("test")
public class ServiceUnityTest {

    @InjectMocks
    private BrzUserServiceImpl brzUserServiceImpl;

    @Mock
    private BrzUserRepository brzUserRepository;

    @Test
    @DisplayName("assert that user is created")
    void assertThatUserIsCreated(){
        var requestDto = new BrzUserRequestDto("Pedro", "Ribeiro",
                "525.444.333-22", "pedrinho@gmail.com", "ablable");

        var brzUser = requestDto.toUser();

        Mockito.when(brzUserRepository.save(Mockito.any(BrzUser.class))).thenReturn(brzUser);

        Mockito.when(brzUserRepository.findUserByCpfOrEmail(requestDto.cpf(), requestDto.email()))
                .thenReturn(Optional.empty());

        var userSaved = brzUserServiceImpl.addBrzUser(requestDto);

        Assertions.assertNotNull(userSaved);
        Assertions.assertEquals(brzUser.getFirstName(), requestDto.firstName());
    }


    @Test
    @DisplayName("assert that list have one registry")
    void assertThatListHaveOneRegistry(){
        var user = new BrzUser("drepo", "ribeiro", "777.333.666-22",
                "dreporibeiro@gmail.com", "homicomh");

        Mockito.when(brzUserRepository.findAll()).thenReturn(Collections.singletonList(user));
        var allUsers = brzUserServiceImpl.findAllUsers();
        Assertions.assertEquals(1, allUsers.size());
    }

    @Test
    @DisplayName("assert that error will be throw")
    void assertThatUserExist(){
        var user = new BrzUserRequestDto("drepo", "ribeiro", "777.333.666-22",
                "dreporibeiro@gmail.com", "homicomh");
        Mockito.when(brzUserRepository.findUserByCpfOrEmail(user.cpf(), user.email())).thenReturn(Optional.of(user.toUser()));
        Assertions.assertThrows(CpfOrEmailAlreadyExistException.class, () -> brzUserServiceImpl.addBrzUser(user));
    }


}
