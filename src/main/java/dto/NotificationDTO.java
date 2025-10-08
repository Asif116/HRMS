package dto;

import lombok.*;
import entity.NotificationType;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    private Long id;
    private NotificationType type;
    private String title;
    private String message;
    private Long employeeId;
    private boolean readStatus;
    private LocalDateTime createdAt;
}
