package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.entity.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    Integer price;
    String location;
    @ManyToOne
    @JoinColumn
    User user;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    Category category;
    @OneToMany(mappedBy = "ad", cascade = {CascadeType.DETACH, CascadeType.MERGE}, fetch = FetchType.EAGER)
    List<FavoriteAd> favoriteAds;
    @OneToMany(mappedBy = "ad")
    List<Image> images;
    @OneToMany(mappedBy = "ad")
    List<Message> messages;
    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp createdAt;
    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Timestamp updatedAt;
    @Builder.Default
    @Enumerated(EnumType.ORDINAL)
    Status status = Status.ACTIVE;


}
