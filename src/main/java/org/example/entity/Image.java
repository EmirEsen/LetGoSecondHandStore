package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String url;
    @ManyToOne
    @JoinColumn
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
