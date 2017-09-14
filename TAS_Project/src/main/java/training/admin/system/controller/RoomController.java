package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Room;
import training.admin.system.repository.RoomRepository;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomRepository roomRepository;
	
	@RequestMapping(value="/all")
	public Page<Room> findAll(Pageable pageable){
		return roomRepository.findAll(pageable);
	}
}
