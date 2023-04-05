package com.cnstn.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cnstn.dao.EmployeeRepository;
import com.cnstn.dao.RoleRepository;
import com.cnstn.entities.Employee;
import com.cnstn.entities.approle;
import com.cnstn.service.BackServiceEmployee;
@Service
@Transactional
public class BackServiceEmployeeImpl implements BackServiceEmployee {
	@Autowired
	EmployeeRepository employeeRepository ;
	@Autowired
	RoleRepository roleRepository ;
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	

	@Override
	public List<Employee> AfficherListEmp() {
		List<Employee> Listemp=employeeRepository.findAll();
		return Listemp;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return employeeRepository.save(employee);
	}

	@Override
	public Employee LoadEmployeeById(Long id) {
		Optional<Employee> optionalemployee = employeeRepository.findById(id);
        return optionalemployee.get();
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee existingEmployee = employeeRepository.findById(employee.getId()).get();
        existingEmployee.setNom(employee.getNom());
        existingEmployee.setPrenom(employee.getPrenom());
        existingEmployee.setAdresse(employee.getAdresse());
        existingEmployee.setTel(employee.getTel());
        existingEmployee.setCin(employee.getCin());
        existingEmployee.setMatricule(employee.getMatricule());
        existingEmployee.setPoste(employee.getPoste());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return updatedEmployee;
	}

	@Override
	public approle addrole(approle role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(Long id, String RoleName) {
		Employee employee = employeeRepository.getById(id);
		approle role = roleRepository.findByRoleName(RoleName);
		employee.getApproles().add(role);
		
	}

	

	

	

	

	


}
