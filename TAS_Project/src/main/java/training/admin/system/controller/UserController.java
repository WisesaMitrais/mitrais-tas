package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.User;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping ("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public Page<User> findAll(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	@RequestMapping (value="/test", method=RequestMethod.GET)
	public String test(){
		return "Test";
	}
	
}
