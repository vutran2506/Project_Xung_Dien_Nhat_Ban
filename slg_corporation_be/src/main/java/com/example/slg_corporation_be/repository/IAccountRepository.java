package com.example.slg_corporation_be.repository;


import com.example.slg_corporation_be.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface IAccountRepository extends JpaRepository<Account,Long> {

    /**
     * HoangNM
     */
    @Query(value = "select a.* from account a join app_user e on a.id_app_user = e.id where e.email = ?1",nativeQuery = true)
    Account findAccountByAppUserEmail(String username);


    /**
     * HoangNM
     */
    @Modifying
    @Query(value = "update account set password =?1 where id =?2 ",nativeQuery = true)
    void saveNewPassword(String newPassword, Long id);
}
