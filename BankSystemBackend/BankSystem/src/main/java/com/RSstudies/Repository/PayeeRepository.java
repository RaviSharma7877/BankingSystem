package com.RSstudies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RSstudies.models.Payee;

public interface PayeeRepository extends JpaRepository<Payee, Integer> {

}
