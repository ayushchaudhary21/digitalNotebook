package com.note.Notes.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

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
   // Create the another column in the Notes Table with the foreign key.
    private User userId;
}
