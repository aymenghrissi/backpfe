package com.cnstn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnstn.entities.Service;



public interface ServiceRepository extends JpaRepository<Service, Long> {

}
