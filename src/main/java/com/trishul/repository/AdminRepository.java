package com.trishul.repository;


import com.trishul.model.account.Account;
import com.trishul.model.account.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminRepository implements AccountRepository {
    public static Map<String, Admin> adminMap = new HashMap<>();
    public static List<Admin> admins = new ArrayList<>();

    public Account createAccount(Account account) {
        adminMap.putIfAbsent(account.getEmail(), (Admin) account);
        return account;
    }

    public void resetPassword(String userId, String password) {

    }
}