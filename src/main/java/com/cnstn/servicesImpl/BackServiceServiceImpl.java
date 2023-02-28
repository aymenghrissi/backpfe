package com.cnstn.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cnstn.dao.ServiceRepository;
import com.cnstn.entities.Direction;
import com.cnstn.entities.Service;
import com.cnstn.service.BackServiceService;


@org.springframework.stereotype.Service
public class BackServiceServiceImpl implements BackServiceService{
	@Autowired
	ServiceRepository serviceRepository;

	  
	    

	@Override
	public List<Service> AfficherListService() {
		List<Service> services= serviceRepository.findAll();
		return services;
	}

	
	

	

	@Override
	public void deleteService(Long id) {
		serviceRepository.deleteById(id);
		
	}

	@Override
	public Service updateService(Service service) {
		Service existingService = serviceRepository.findById(service.getId()).get();
        existingService.setDescription(service.getDescription());
        existingService.setNom(service.getNom());
        Service updatedService = serviceRepository.save(existingService);
        return updatedService;
	}






	@Override
	public Service addService(Service service) {
		return serviceRepository.save(service);
	}






	@Override
	public Service LoadServiceById(Long id) {
		Optional<Service> optionalservice = serviceRepository.findById(id);
        return optionalservice.get();
	}

	







}
