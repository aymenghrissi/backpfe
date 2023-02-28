package com.cnstn.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cnstn.entities.Demande_mat_info;



public interface DemandeRepository extends JpaRepository <Demande_mat_info ,Long>   {
	

}
