package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import training.admin.system.model.Office;
import training.admin.system.model.Room;
import training.admin.system.repository.RoomRepository;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomRepository roomRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Room> findAll( ){
		return roomRepository.findAll();
	}
	
	@RequestMapping(value="/{id}/office", method=RequestMethod.GET)
	public Office findOffice(@PathVariable ("id") Long idUser){
		return roomRepository.findOne(idUser).getOffice();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Room> findAll(Pageable pageable){
		return roomRepository.findAll(pageable);
	}
}
