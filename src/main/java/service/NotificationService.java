package service;

import dto.NotificationDTO;

import java.util.List;

public interface NotificationService {
    NotificationDTO createNotification(NotificationDTO dto);
    List<NotificationDTO> getNotificationsForEmployee(Long employeeId);
    void markAsRead(Long notificationId);
    void deleteNotification(Long notificationId);
}
