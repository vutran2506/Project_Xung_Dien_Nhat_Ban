package com.example.slg_corporation_be.service;

import com.example.slg_corporation_be.model.AppUser;

public interface IAppUserService {
    AppUser findByEmail(String email);
}
