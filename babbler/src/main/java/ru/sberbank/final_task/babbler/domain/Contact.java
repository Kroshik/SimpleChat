package ru.sberbank.final_task.babbler.domain;


import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Contact")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(name = "friendId")
    private Long friendId;
}
