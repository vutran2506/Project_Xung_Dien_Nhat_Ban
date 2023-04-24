package com.example.slg_corporation_be.repository;

import com.example.slg_corporation_be.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
}
