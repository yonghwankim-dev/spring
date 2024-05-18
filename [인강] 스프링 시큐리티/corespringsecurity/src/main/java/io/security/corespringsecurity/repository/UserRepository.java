package io.security.corespringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.security.corespringsecurity.domain.entity.Account;

public interface UserRepository extends JpaRepository<Account, Long> {

	@Query("select a from Account a where a.username = :username")
	Account findByUsername(@Param("username") String username);
}
