package ru.sberbank.final_task.babbler.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.sberbank.final_task.babbler.domain.auth.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByLogin(String login);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.firstName =:newFirstName, u.lastName =:newLastName, " +
            "u.login =:newLogin, u.password =:newPassword, u.avatar =:avatar, u.avatarType =:avatarType" +
            " WHERE u.email =:email")
    void updateUserInfo(@Param("email") String email, @Param("newFirstName") String newFirstName,
                        @Param("newLastName") String newLastName,
                        @Param("newLogin") String newLogin,
                        @Param("newPassword") String newPassword,
                        @Param("avatar") byte[] avatar,
                        @Param("avatarType") String avatarType);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.lastSeen =:lastSeen, u.status =:status WHERE u.email =:email")
    void updateLastSeen(@Param("email") String email, @Param("lastSeen") LocalDateTime lastSeen,
                        @Param("status") String status);

//    @Transactional
//    @Modifying
//    @Query("UPDATE User u SET u.contacts =:contacts WHERE u.id =:id")
//    void updateUserContacts(@Param("id") Long id, @Param("contacts")Set<User> contacts);

    @Query("SELECT u FROM User u WHERE u.login LIKE %:textSearch%")
    List<User> searchUsersByLogin(@Param("textSearch") String textSearch);

//    @Query("SELECT u FROM User u WHERE u.id =:idUser AND u.friend_id =:idContact")
//    User containContact(@Param("idUser") Long idUser, @Param("idContact") Long idContact);
//
//    @Query("SELECT u FROM User u WHERE u.id =:idUser")
//    Set<User> getContacts(@Param("idUser") Long idUser);

}
