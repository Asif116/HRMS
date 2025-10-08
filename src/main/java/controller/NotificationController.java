package controller;
import dto.NotificationDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor

public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@Valid @RequestBody NotificationDTO dto) {
        NotificationDTO created = notificationService.createNotification(dto);
        return ResponseEntity.ok(created);
    }
//    @GetMapping("/all")
//    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
//        List<NotificationDTO> allNotifications = notificationService.getAllNotifications();
//        return ResponseEntity.ok(allNotifications);
//    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@PathVariable Long employeeId) {
        return ResponseEntity.ok(notificationService.getNotificationsForEmployee(employeeId));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
