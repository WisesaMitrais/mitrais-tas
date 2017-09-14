package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Role;
import training.admin.system.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping(value = "/all")
	public Page<Role> findAll(Pageable pageable){
		return roleRepository.findAll(pageable);
	}
}
