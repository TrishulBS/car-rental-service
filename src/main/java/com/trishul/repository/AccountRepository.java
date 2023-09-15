package com.trishul.repository;
import com.trishul.exception.AccountDoesNotExistsException;
import com.trishul.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistsException;
}