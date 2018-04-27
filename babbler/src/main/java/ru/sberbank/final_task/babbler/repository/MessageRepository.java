package ru.sberbank.final_task.babbler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sberbank.final_task.babbler.domain.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByIdFromUser(Long id);
    
    List<Message> findByIdToUser(Long id);
    
    @Query("SELECT m FROM Message m WHERE (m.idFromUser = :idFromUser AND m.idToUser = :idToUser) " +
            "OR (m.idFromUser = :idToUser AND m.idToUser = :idFromUser) ORDER BY m.dateMessage ASC")
    List<Message> findDialog(@Param("idFromUser") Long idFromUser, @Param("idToUser") Long idToUser);
    
    @Query("SELECT m FROM Message m WHERE (m.idFromUser = :idUser OR m.idToUser = :idUser) " +
            "AND m.textMessage LIKE %:textSearch% ORDER BY m.dateMessage ASC")
    List<Message> searchMessagesByText(@Param("idUser") Long idUser, @Param("textSearch") String textSearch);
    
    @Query("SELECT m FROM Message m WHERE (m.idFromUser = :idFromUser AND id = (SELECT MAX(id) FROM Message))")
    Message findMaxIdFromMessageWhereFromUser(@Param ("idFromUser") Long idFromUser);
}
