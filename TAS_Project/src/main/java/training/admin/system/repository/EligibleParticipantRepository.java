package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.EligibleParticipant;

@Component
public interface EligibleParticipantRepository extends JpaRepository<EligibleParticipant, Long>{
	
}
