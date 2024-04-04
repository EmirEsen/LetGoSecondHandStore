package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String content;
    @ManyToOne
    User sender;
    @ManyToOne
    Ad ad;
    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp createdAt;
    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp updatedAt;
    @Enumerated(EnumType.ORDINAL)
    Status status;


}
