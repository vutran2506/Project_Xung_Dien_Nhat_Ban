package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.AppUser;
import com.example.slg_corporation_be.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements IAppUserService {
    @Autowired
    private IAppUserRepository appUserRepository;
    @Override
    public AppUser findByEmail(String email) {
        return this.appUserRepository.findByEmail(email);
    }
}
