package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.EligibleParticipant;
import training.admin.system.model.Training;
import training.admin.system.model.User;

@Component
public interface EligibleParticipantRepository extends JpaRepository<EligibleParticipant, Long>{
	List <EligibleParticipant> findByTraining(Training training);
	List <EligibleParticipant> findByUser(User user);
	List <EligibleParticipant> findByTrainingAndUser(Training training, User user);
}
