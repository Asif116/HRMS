package config;
import entity.Notification;
import entity.NotificationType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
@Component
@RequiredArgsConstructor
public class DataLoader {

    private final NotificationRepository notificationRepository;

    @PostConstruct
    public void load() {
        if (notificationRepository.count() == 0) {
            Notification sys = Notification.builder()
                    .type(NotificationType.SYSTEM)
                    .title("System Maintenance")
                    .message("System will be down for maintenance on Sunday 2 AM - 4 AM.")
                    .createdAt(LocalDateTime.now().minusDays(1))
                    .build();

            Notification e1 = Notification.builder()
                    .type(NotificationType.EMPLOYEE)
                    .employeeId(1L)
                    .title("Policy Update")
                    .message("Please review the updated leave policy.")
                    .createdAt(LocalDateTime.now().minusHours(3))
                    .build();

            Notification e2 = Notification.builder()
                    .type(NotificationType.EMPLOYEE)
                    .employeeId(2L)
                    .title("Training Reminder")
                    .message("Mandatory training scheduled for Friday.")
                    .createdAt(LocalDateTime.now().minusHours(5))
                    .build();

            notificationRepository.saveAll(List.of(sys, e1, e2));
            System.out.println("Sample notifications inserted.");
        }
    }
}