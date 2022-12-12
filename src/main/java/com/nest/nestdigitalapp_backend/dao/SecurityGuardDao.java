package com.nest.nestdigitalapp_backend.dao;

import com.nest.nestdigitalapp_backend.model.EmployeeModel;
import com.nest.nestdigitalapp_backend.model.SecurityGuardModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecurityGuardDao extends CrudRepository<SecurityGuardModel,Integer> {

    @Query(value = "SELECT * FROM `securityguards` WHERE `username` =:username AND `password` =:password",nativeQuery = true)
    List<SecurityGuardModel> secGuardLogin(String username, String password);

    @Query(value = "SELECT * FROM `securityguards` WHERE `id`= :id",nativeQuery = true)
    List<SecurityGuardModel> viewSecProfile(int id);
}
