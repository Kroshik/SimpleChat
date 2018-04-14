package ru.sberbank.final_task.babbler.domain.warehouse;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class StockItemAudit implements Serializable {
    /**
     * As PK should be itemId + date
     * But id should be enough for the first time
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long userId;

    @Column
    private String itemName;

    @Column
    private Long count;
}
