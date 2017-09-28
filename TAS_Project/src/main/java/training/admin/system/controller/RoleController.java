package training.admin.system.controller;

import java.util.List;

import javax.websocket.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Office;
import training.admin.system.model.Role;
import training.admin.system.model.Room;
import training.admin.system.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Role> findAll( ){
		return roleRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Role> findAll(Pageable pageable){
		return roleRepository.findAll(pageable);
	}
}
