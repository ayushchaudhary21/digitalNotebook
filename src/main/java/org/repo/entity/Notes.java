package org.repo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  long notesId;
    @Column(nullable = false,unique = true)
   private  String title;


   private  String content;

   private LocalDateTime timeStamp=LocalDateTime.now();


   @ManyToOne
   @JoinColumn(name="user_id")
   @JsonBackReference
   // Create another column in the Note Table with the foreign key.
    private User userId;
}
