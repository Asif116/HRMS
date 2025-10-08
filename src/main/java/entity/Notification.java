package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String title;

    @Column(length = 1000)
    private String message;

    // Target employee (null if system-wide)
    private Long employeeId;

    private boolean readStatus = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}
