package com.data.session10.repository;

import com.data.session10.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByCccd(String cccd);
}