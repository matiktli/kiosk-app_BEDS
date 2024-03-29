package com.kiosk.model;

import com.kiosk.model.type.LogActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "logs")
@NoArgsConstructor
@AllArgsConstructor
public class Log extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @Column(name = "action_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LogActionType type;

    @Column(name = "action", nullable = false)
    private String action;

}
