package training.admin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Office;
import training.admin.system.repository.OfficeRepository;

@RestController
@RequestMapping("/office")
public class OfficeController {

	@Autowired 
	OfficeRepository officeRepository;
	
	@RequestMapping (value = "/all")
	public Page<Office> findAll(Pageable pageable){
		return officeRepository.findAll(pageable);
	}
}
