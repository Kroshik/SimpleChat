package ru.sberbank.final_task.babbler.domain.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.sberbank.final_task.babbler.domain.Contact;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "firstName")
    private String firstName;

    @NonNull
    @Column(name = "lastName")
    private String lastName;

    @NonNull
    @Column(name = "login")
    private String login;

    @NonNull
    @Column(name = "email")
    private String email;

    @NonNull
    @Column(name = "password")
    private String password;

    @NonNull
    @Column(name = "lastSeen")
    private LocalDateTime lastSeen;

    @Column(name = "status")
    private String status = "online";


//    @JoinTable(name = "contact",
//            joinColumns = @JoinColumn(
//                    name = "user_id",
//                    referencedColumnName = "id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "friend_id",
//                    referencedColumnName = "id"
//            ))
    @Column(name = "contacts")
    @ElementCollection
    private Set<Contact> contacts = new LinkedHashSet<>();

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "avatarType")
    private String avatarType;

}