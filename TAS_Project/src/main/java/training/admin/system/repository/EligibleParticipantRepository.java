package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.EligibleParticipant;

@Component
public interface EligibleParticipantRepository extends JpaRepository<EligibleParticipant, Long>{
	List <EligibleParticipant> findByIdTraining(Long idTraining);
	List <EligibleParticipant> findByIdUser(Long idUser);
	List <EligibleParticipant> findByIdTrainingAndIdUser(Long idTraining, Long idUser);
}
