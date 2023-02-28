package com.cnstn.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cnstn.entities.Demande_mat_info;
import com.cnstn.entities.Materiels;





public interface MaterielsRepository extends JpaRepository < Materiels ,Long>   {
	

}
