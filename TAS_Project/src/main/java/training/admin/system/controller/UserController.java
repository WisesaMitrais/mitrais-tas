package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import training.admin.system.model.User;
import training.admin.system.repository.UserRepository;

@RestController
@RequestMapping ("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping (value="", method=RequestMethod.GET)
	public ModelAndView root(){
		return new ModelAndView("redirect:/user/all"); 
	}
	
	@RequestMapping (value="/all", method=RequestMethod.GET)
	public Iterable<User> findAll(){
		return userRepository.findAll();
	}
	
	@RequestMapping (value="/allPage", method=RequestMethod.GET)
	public Page<User> findAll(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public String add(@RequestBody User user) {
		userRepository.save(user);
		return "Test";
	}
	
}
