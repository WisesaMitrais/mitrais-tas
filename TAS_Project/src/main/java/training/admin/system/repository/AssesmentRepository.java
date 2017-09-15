package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Assesment;

@Component
public interface AssesmentRepository extends JpaRepository<Assesment, Long>{

}
