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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String username;
    @Column(unique = true)
    String password;
    @Column(unique = true)
    String email;
    String phone;
    String imgURL;
    String location;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.DETACH})
    List<Ad> ads;
    @OneToMany(mappedBy = "user",cascade =  {CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    List<FavoriteAd> favoriteAds;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.PERSIST)
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
