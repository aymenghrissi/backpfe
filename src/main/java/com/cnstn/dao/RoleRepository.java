package com.cnstn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnstn.entities.approle;

public interface RoleRepository extends JpaRepository<approle, Long>  {
approle  findByRoleName(String roleName);
}
