package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Achievement;

@Component
public interface AchievementRepository extends JpaRepository<Achievement, Long>{
	
}
