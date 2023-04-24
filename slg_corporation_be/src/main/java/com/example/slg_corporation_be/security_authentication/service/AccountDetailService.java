package com.example.slg_corporation_be.security_authentication.service;


import com.example.slg_corporation_be.model.Account;
import com.example.slg_corporation_be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailService implements UserDetailsService {

    @Autowired
    private IAccountService iAccountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)  {
        Account account = iAccountService.findAccountByAppUserEmail(username);
        if(account==null){
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        return AccountDetails.build(account);
    }
}