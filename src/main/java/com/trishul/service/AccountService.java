package com.trishul.service;

import com.trishul.exception.AccountDoesNotExistsException;
import com.trishul.model.account.Account;
import com.trishul.model.account.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password,
                       AccountType accountType) throws AccountDoesNotExistsException;
}
