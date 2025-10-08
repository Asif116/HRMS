package repository;
import entity.Notification;
import entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEmployeeIdOrType(Long employeeId, NotificationType type);
    List<Notification> findByEmployeeId(Long employeeId);
}
