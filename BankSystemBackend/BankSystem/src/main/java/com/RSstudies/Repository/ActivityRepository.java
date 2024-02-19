package com.RSstudies.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RSstudies.models.Account;
import com.RSstudies.models.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	
}
