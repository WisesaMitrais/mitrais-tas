package training.admin.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import training.admin.system.model.Room;

@Component
public interface RoomRepository extends JpaRepository<Room, Long>{
	Room findByName(String name);
}
