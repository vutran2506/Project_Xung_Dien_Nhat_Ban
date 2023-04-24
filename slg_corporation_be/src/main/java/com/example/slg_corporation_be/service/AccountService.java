package com.example.slg_corporation_be.service;


import com.example.slg_corporation_be.model.Account;
import com.example.slg_corporation_be.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    /**
     * HoangNM
     */
    @Autowired
    IAccountRepository accountRepository;



    @Override
    public Account findAccountByAppUserEmail(String username) {
        return this.accountRepository.findAccountByAppUserEmail(username);
    }

    /**
     * HoangNM
     */
    @Override
    public void saveNewPassword(String newPassword,Long accountId) {
        accountRepository.saveNewPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(12)),accountId);
    }

    /**
     * HoangNM
     */
    @Override
    public boolean checkPassword(String password, String oldPassword) {
        return BCrypt.checkpw(password,oldPassword);
    }


}
