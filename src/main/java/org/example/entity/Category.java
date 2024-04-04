package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enums.Categories;
import org.example.entity.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Enumerated(value = EnumType.STRING)
    Categories category;
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
