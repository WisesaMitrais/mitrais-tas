package training.admin.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.admin.system.model.Office;
import training.admin.system.repository.OfficeRepository;

@RestController
@RequestMapping("/office")
public class OfficeController {

	@Autowired 
	OfficeRepository officeRepository;
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Office> findAll( ){
		return officeRepository.findAll();
	}
	
	@RequestMapping(value="/allPage", method=RequestMethod.GET)
	public Page<Office> findAll(Pageable pageable){
		return officeRepository.findAll(pageable);
	}
	
	@RequestMapping (value="/create", method=RequestMethod.POST)
	public void add(@RequestBody Office office) {
		officeRepository.save(office);
	}
	
	@RequestMapping (value="/update",method = RequestMethod.POST)
	public void update (@RequestBody Office paramOffice) {
		Office office = officeRepository.findOne(paramOffice.getIdOffice());
		office.setName(paramOffice.getName());
		office.setAddress(paramOffice.getAddress());
		office.setCity(paramOffice.getCity());
		officeRepository.save(office);
	}
	
	@RequestMapping (value="/delete", method = RequestMethod.GET)
	public void delete (@RequestParam ("id") Long idOffice) {
		officeRepository.delete(idOffice);
	}
}
