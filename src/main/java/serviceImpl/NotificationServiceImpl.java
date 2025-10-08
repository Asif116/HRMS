package serviceImpl;

import dto.NotificationDTO;
import entity.Notification;
import entity.NotificationType;
import org.springframework.beans.BeanUtils;
import repository.NotificationRepository;
import service.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationServiceImpl  implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(dto, notification);
        if (notification.getCreatedAt() == null) {
            notification.setCreatedAt(java.time.LocalDateTime.now());
        }
        Notification saved = notificationRepository.save(notification);
        return convertToDTO(saved);
    }

    @Override
    public List<NotificationDTO> getNotificationsForEmployee(Long employeeId) {
        List<Notification> notifications = notificationRepository
                .findByEmployeeIdOrType(employeeId, NotificationType.SYSTEM);

        return notifications.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setReadStatus(true);
            notificationRepository.save(notification);
        });
    }

    @Override
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = NotificationDTO.builder().build();
        BeanUtils.copyProperties(notification, dto);
        return dto;
    }
}
