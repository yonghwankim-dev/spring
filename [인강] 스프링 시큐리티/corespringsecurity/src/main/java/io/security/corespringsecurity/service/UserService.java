package io.security.corespringsecurity.service;

import java.util.List;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;

public interface UserService {
	void createUser(Account account);

	List<Account> getUsers();

	void modifyUser(AccountDto accountDto);

	AccountDto getUser(Long id);

	void deleteUser(Long id);
}
