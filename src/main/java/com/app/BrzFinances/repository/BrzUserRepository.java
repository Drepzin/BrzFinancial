package com.app.BrzFinances.repository;

import com.app.BrzFinances.entity.BrzUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrzUserRepository extends JpaRepository<BrzUser, Long> {

    Optional<BrzUser> findUserByCpfOrEmail(String cpf, String email);
}
