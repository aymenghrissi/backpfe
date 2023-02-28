package com.cnstn.service;

import java.util.List;


import com.cnstn.entities.Service;


public interface BackServiceService {
	List<Service> AfficherListService();
	Service addService(Service service );
	Service LoadServiceById(Long id);
	void deleteService(Long id);
	Service updateService(Service service);


}
