package com.raju.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raju.entity.PersonalInfo;
@Repository
public interface PersonalInfoRepo extends JpaRepository<PersonalInfo, Integer> {

}
