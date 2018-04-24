package ru.sberbank.final_task.babbler.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@Table(name = "Message", schema="spring_jpa_message")
//@NamedQuery(name = "Message.findDialog",
//        query = "SELECT msg FROM Message msg WHERE msg.idFromUser = :idFromUser"
//)
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "textMessage", nullable = false)
    @NonNull
    private String textMessage;

    @Column(name = "nameUser", nullable = false)
    @NonNull
    private String nameUser;

    @Column(name = "dateMessage", nullable = false)
    @NonNull
    private LocalDateTime dateMessage;

    @Column(name = "idFromUser", nullable = false)
    @NonNull
    private Long idFromUser;

    @Column(name = "idToUser", nullable = false)
    @NonNull
    private Long idToUser;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "file")
    private byte[] file;
//
//    @Column(name = "status")
//    private Boolean status; // 0 - не прочитано 1 - прочитано
}
