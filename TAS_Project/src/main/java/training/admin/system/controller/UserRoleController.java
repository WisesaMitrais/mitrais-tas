package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.UserRole;
import training.admin.system.repository.UserRoleRepository;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {

	@Autowired
	UserRoleRepository userRoleRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<UserRole> findAll(){
		return userRoleRepository.findAll();				
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<UserRole> findAll(Pageable pageable){
		return userRoleRepository.findAll(pageable);				
	}
}
