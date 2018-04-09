package com.square.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.square.bank.model.Movimentation;

@Repository
public interface MovimentationRepository extends JpaRepository<Movimentation, Integer>{

}
