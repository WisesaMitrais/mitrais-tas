package training.admin.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import training.admin.system.PeriodData;
import training.admin.system.model.Training;

@Component
public interface TrainingRepository extends JpaRepository<Training, Long>{
	List<Training> findByOpenEnrollment(Boolean openEnrollment);
	List<Training> findByActive(Boolean active);
	
	@Query(value="SELECT p.id_training AS idTraining, "
			+ "p.training_name AS trainingName, "
			+ "p.active AS active, "
			+ "(SELECT Count (id_training) FROM tb_schedule WHERE id_training = p.id_training), "
			+ "p.start_date AS startDate, "
			+ "p.end_date AS endDate, "
			+ "(SELECT name FROM tbm_user where id_user = p.created_by) AS createdBy, "
			+ "(SELECT name FROM tbm_user where id_user = p.updated_by) AS updatedBy,"
			+ "p.open_enrollment AS openEnrollment,"
			+ "p.bcc_training AS bccTraining "
			+ "FROM tb_training p", nativeQuery = true) 
	List <PeriodData> _findAllPeriod();
}
