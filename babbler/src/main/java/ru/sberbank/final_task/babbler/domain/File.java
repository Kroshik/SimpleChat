package ru.sberbank.final_task.babbler.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class File {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "name", length=100, nullable = false)
    @NonNull
    private String name;

    @NonNull
    @Column(name="type", length=100, nullable=false)
    private String type;

    @NonNull
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="content", nullable=false)
    private byte[] content;

//    @NonNull
//    @OneToOne(optional = false)
//    @JoinColumn(name = "message")
//    private Message message;
}
