package ru.sberbank.final_task.babbler.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String textMessage;

    @NonNull
    private String nameUser;

    @NonNull
    private LocalDateTime dateMessage;

    @NonNull
    private Long idFrom;

//    @NonNull
//    @DateTimeFormat(pattern = "dd.MM.yyyy")
//    @Temporal(TemporalType.DATE)
//    private Date birthDate;

//    @NonNull
//    @Builder.Default
//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.EAGER,
//            mappedBy = "message")
//    @OrderBy("start_date desc")
//    private List<Message> messages = new ArrayList<>();
}
