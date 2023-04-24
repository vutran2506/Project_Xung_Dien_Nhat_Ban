package com.example.slg_corporation_be.service;


import com.example.slg_corporation_be.model.Account;

public interface IAccountService {
    /**
     * HoangNM
     */
    Account findAccountByAppUserEmail(String username);

    /**
     * HoangNM
     */
    void saveNewPassword(String newPassword,Long accountId);

    /**
     * HoangNM
     */
    boolean checkPassword(String password, String oldPassword);
}
