package com.square.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.square.bank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
